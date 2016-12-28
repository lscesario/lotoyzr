package br.com.bitakdev.lotoyzr.resources;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.security.LoginControl;

@Path("/login")
public class LoginResource {
	
	@Inject
	LoginControl lc;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@HeaderParam("member_email") String member_email,
								 @HeaderParam("member_password") String member_password){
		System.out.println("LoginResource - Authenticating user: "+member_email);
		if (member_email == null) {
			throw new BadRequestException();
		    }
		if (member_password == null){
			throw new BadRequestException();
		}
		return lc.authenticate(member_email, member_password);
	}
	
	
	//Método de exemplo de autenticação com JWT
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getVersion(@HeaderParam("token") String JWT){
		System.out.println(LoginResource.class.getName()+"- Version" + "Start");
		if(JWT==null){
			System.out.println(LoginResource.class.getName()+"- Version" + "Empty Token");
			return Response.status(400).entity("token_inexistent").build();
		}
		if(lc.authenticateToken(JWT).getStatus()==400){
			System.out.println(LoginResource.class.getName()+"- Version" + "Token authentication error, return error");
			return lc.authenticateToken(JWT);
		}
		System.out.println(LoginResource.class.getName()+"- Version" + "Token authenticated, proceed to next method");
		return lc.authenticateToken(JWT);
	}
	
}
