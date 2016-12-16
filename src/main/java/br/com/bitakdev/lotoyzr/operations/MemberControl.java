package br.com.bitakdev.lotoyzr.operations;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Member;

@Model
@ApplicationScoped
public class MemberControl {
	
	Member member = new Member();
	
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
	

}
