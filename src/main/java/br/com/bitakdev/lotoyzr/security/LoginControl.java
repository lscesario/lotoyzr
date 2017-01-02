package br.com.bitakdev.lotoyzr.security;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Member;

public class LoginControl {
	
	@Inject
	MemberDAO memberDAO;
	
	Member member = new Member();
	
	static JsonWebKey static_jwk;
	public static Member session_user;
	
	static {
		System.out.println("Initializing JWT public key");
		try {
			static_jwk=RsaJwkGenerator.generateJwk(2048);
			System.out.println("pk: "+static_jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
		} catch (JoseException e) {
			e.printStackTrace();
		}		
	}

	public Response authenticate(String member_email, String member_password){
		member=memberDAO.loadMemberByEmail(member_email);
		if(checkPassword(member_email, member_password).equals("authentication_error")){
			System.out.println("Erro de autenticação para o usuario " + member_email);
			return Response.status(403).entity("authentication_error").build();
		};
		
		RsaJsonWebKey senderJwk=(RsaJsonWebKey)static_jwk;
		System.out.println("JWK (1) ===> " + senderJwk.toJson());
		
		JwtClaims claims = new JwtClaims();
		claims.setIssuer(Constants.DOMAIN_NAME);
		claims.setExpirationTimeMinutesInTheFuture(Constants.SESSION_TIME);
		claims.setGeneratedJwtId();
		claims.setIssuedAtToNow();
		claims.setNotBeforeMinutesInThePast(5);
		claims.setSubject(member.getMember_email());
		//claims.setStringListClaim(Constants.STRING_LIST_CLAIM, memberDAO.loadMemberRolesByMemberEmail(member.getMember_email()).stream().toArray(String[]::new));
		
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA256);
		
		String jwt = null;
		try{
			jwt=jws.getCompactSerialization();
		}catch (JoseException e){
			e.printStackTrace();
		}
		session_user=member;
		return Response.status(200).entity(jwt).build();
	}
	
	public String checkPassword(String member_email, String member_password) {
		member=memberDAO.loadMemberByEmail(member_email);
		if(member==null){
			return "member_not_found";
		}
		if(!member.getMember_password().equals(member_password)){
			return "authentication_error";
		}
		return "member_authenticated";
		}
	

	public Response authenticateToken(String JWT) {
		System.out.println("LoginControl - authenticateToken - Start");
		if(JWT==null){
			System.out.println("LoginControl - authenticateToken - Invalid or Inexistent Token");
			return Response.status(400).entity("missing_token").build();
		}
		
		JsonWebKey jwk = static_jwk;
		System.out.println("LoginControl - authenticateToken - JsonWebKey: "+jwk.toJson());
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer(Constants.DOMAIN_NAME)
				.setVerificationKey(jwk.getKey())
				.build();
		
		try {
			JwtClaims jwtClaims = jwtConsumer.processToClaims(JWT);
			System.out.println("LoginControl - authenticateToken - Token Validated Succesfully "+jwtClaims);
			return Response.status(200).entity("token_validated").build();
			
		}catch(InvalidJwtException e){
		System.out.println("LoginControl - authenticateToken -Invalid Token "+e);
		return Response.status(400).entity("invalid_token").build();
		}
		
	}
	
	public String getLoggedMember(String JWT) {
		System.out.println("LoginControl - authenticateToken - Start");
		if(JWT==null){
			System.out.println("LoginControl - authenticateToken - Invalid or Inexistent Token");
			return "missing_token";
		}
		JsonWebKey jwk = static_jwk;
		System.out.println("LoginControl - authenticateToken - JsonWebKey: "+jwk.toJson());
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer(Constants.DOMAIN_NAME)
				.setVerificationKey(jwk.getKey())
				.build();
		try {
			JwtClaims jwtClaims = jwtConsumer.processToClaims(JWT);
			System.out.println("LoginControl - authenticateToken - Token Validated Succesfully "+jwtClaims);
			try {
				return jwtConsumer.processToClaims(JWT).getSubject();
			} catch (MalformedClaimException e) {
				System.out.println("LoginControl - authenticateToken -Invalid Claim "+e);
				e.printStackTrace();
				return "invalid_claim";
			}
			
		}catch(InvalidJwtException e){
		System.out.println("LoginControl - authenticateToken -Invalid Token "+e);
		return "invalid_token";
		}
		
	}
}
