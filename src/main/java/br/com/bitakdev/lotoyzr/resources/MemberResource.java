package br.com.bitakdev.lotoyzr.resources;



import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.models.Member;
import br.com.bitakdev.lotoyzr.operations.MemberControl;

@Path("/members")
public class MemberResource {
	
	@Inject
	MemberControl mc;
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createMember(Member member){
		if (member == null) throw new BadRequestException();
		mc.createMember(member);
		String result="Member created: "+member;
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/sync")
	public Response checkFbMember(Member member){
		if (member == null) throw new BadRequestException();
		return mc.syncFbMember(member);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{member_id}")
	public Response loadMemberById(@PathParam("member_id")int member_id){
		if(member_id==0){
			System.out.println("Member id = 0");
			throw new BadRequestException();
		}
		return Response.status(200).entity(mc.loadMemberById(member_id)).build();
	  }
	
	
	@DELETE
	@Path("remove/{member_id}")
	public Response removeMemberById(@PathParam("member_id")int member_id){
		if(member_id==0){
			System.out.println("Member id = 0");
			throw new BadRequestException();
		}
		mc.removeMemberById(member_id);
		String result="Member Id deleted: "+member_id;
		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("update/{member_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMemberById(@PathParam("member_id")int member_id, Member member){
		mc.updateMemberById(member_id, member);
		String result="Member Id altered: "+member;
		return Response.status(200).entity(result).build();
	}
	
}


