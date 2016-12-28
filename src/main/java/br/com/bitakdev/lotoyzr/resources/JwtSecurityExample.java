package br.com.bitakdev.lotoyzr.resources;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.bitakdev.lotoyzr.daos.BillDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Bill;
import br.com.bitakdev.lotoyzr.models.Member;

@Path(value = "/security")
public class JwtSecurityExample {
	
	@Inject
	BillDAO billDAO;
	@Inject
	MemberDAO memberDAO;
	
	static List<JsonWebKey> jwkList=null;
	
	static {    
		System.out.println("Inside static initializer...");
	    jwkList = new LinkedList<>(); 
	    for (int kid = 1; kid <= 3; kid++) { 
	      JsonWebKey jwk = null;
	      try {
	        jwk = RsaJwkGenerator.generateJwk(2048); 
	        System.out.println("PUBLIC KEY (" + kid + "): "
	          + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
	      } catch (JoseException e) {
	        e.printStackTrace();
	      } 
	      jwk.setKeyId(String.valueOf(kid));  
	      jwkList.add(jwk); 
	    } 
	  }
	
	public JsonWebKey createJWT(){
		JsonWebKey jwk=null;
		try {
			jwk = RsaJwkGenerator.generateJwk(2048);
			System.out.println("Public Key (): "
					+ jwk.toJson((JsonWebKey.OutputControlLevel.PUBLIC_ONLY)));
		}catch(JoseException e){
			e.printStackTrace();
		}
		return jwk;
	}
	
	@Path("/status")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "JwtSecurityExample Status is OK...";
	}
	
	@Path("/authenticate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(@HeaderParam("username")
	String username,
			@HeaderParam("password") String password)
		    throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println("Authenticating User Credentials...");
		if (username == null) {
			throw new BadRequestException();
		    }
		if (password == null){
			throw new BadRequestException();
		}
		
		Member member = new Member();
		member.setMember_name(username);
		member.setMember_password(password);
		
		if(authenticate(username, password).equals("authentication_error")){
			System.out.println("Erro de autenticação para o usuario " + username);
			return Response.status(403).entity("authentication_error").build();
		};
		
		JsonWebKey jwk = createJWT();
		RsaJsonWebKey senderJwk=(RsaJsonWebKey)jwkList.get(0);
		senderJwk.setKeyId("1");
		System.out.println("JWK (1) ===> " + senderJwk.toJson());
		
		JwtClaims claims = new JwtClaims();
		claims.setIssuer("lotoyzr");
		claims.setExpirationTimeMinutesInTheFuture(10);
		claims.setGeneratedJwtId();
		claims.setIssuedAtToNow();
		claims.setNotBeforeMinutesInThePast(2);
		claims.setSubject(member.getMember_name());
		claims.setStringListClaim("roles", member.getMember_roles());
		
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());
		
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA256);
		
		String jwt = null;
				try{
					jwt=jws.getCompactSerialization();
				} catch (JoseException e){
					e.printStackTrace();
				}
		return Response.status(200).entity(jwt).build();
		}
	
	@Path("/finditembyid")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findBillById(@HeaderParam("token")String token,
								 @QueryParam("bill_id") int bill_id){
		Bill bill = null;
		
		System.out.println("Inside findBillById");
		
		if (token == null){
			return Response.status(403).entity("FORBIDDEN").build();
		}
		
		JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
		JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
		System.out.println("JWK (1) ===> " + jwk.toJson());
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer("lotoyzr")
				.setVerificationKey(jwk.getKey())
				.build();
		try{
		//  Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			System.out.println("JWT validation suceeded!" + jwtClaims);
		}catch(InvalidJwtException e){
			e.printStackTrace();
			System.out.println("Invalid JWT" + e);
			return Response.status(403).entity("FORBIDDEN").build();
		}
		
		bill = billDAO.loadBillById(bill_id);
		System.out.println("Bill retornada: "+bill.toString());
		return Response.status(200).entity(bill.toString()).build();
	}
	
	  String authenticate(String username, String password){
			Member member = new Member();
			member=memberDAO.loadMemberByUsername(username);
			if(member==null){
				return "member_not_found";
			}
			if(!member.getMember_password().equals(password)){
				return "authentication_error";
			}
			return "member_authenticated";
			}
		}
		
	
		
