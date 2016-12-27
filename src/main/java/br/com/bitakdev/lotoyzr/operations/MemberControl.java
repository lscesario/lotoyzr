package br.com.bitakdev.lotoyzr.operations;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Member;

@Model
@ApplicationScoped
public class MemberControl {
	
	Member op_member = new Member();
	

	@Inject
	MemberDAO memberDAO;
	@Inject
	HouseDAO houseDAO;
	
	@Transactional
	public void createMember(Member member){
		System.out.println(member.toString());
		try{
		memberDAO.createMember(member);
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
	}
	
	public void removeMemberById(int member){
		memberDAO.removeMemberById(member);
	}
	
	public void updateMemberById(int member_id, Member member){
		memberDAO.updateMemberById(member_id, member);
	}
		
	public Member loadMemberById(int member_id){
		return memberDAO.loadMemberById(member_id);
	}
	
	public Member loadMemberByFbId(String member_fb_id){
		return memberDAO.loadMemberByFbId(member_fb_id);
	}

	public Response syncFbMember(Member member) {
		try{
		op_member=memberDAO.loadMemberByFbId(member.getMember_fb_id());
		System.out.println("Syncing user, creating new if non existant:"+member.getMember_id());
		System.out.println("Got user_id:"+op_member.getMember_id());
		if(op_member.getMember_id()==Constants.VOCE_NAO_VALE_NADA){
			System.out.println("Member inexistent, creating....");
			memberDAO.createMember(member);
			System.out.println("Created new member: "+member.getMember_id());
			String result="member_inexistent_but_created";
			return Response.status(201).entity(result).build();
			}
		System.out.println("User already exists: "+op_member.toString());
		String result="member_already_registered";
		return Response.status(201).entity(result).build();
		}catch(NullPointerException e){
			System.out.println(e);
			return Response.status(500).entity("unexpected_error").build();
		}	
	}		
}	
	

