package br.com.bitakdev.lotoyzr.daos;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;

import br.com.bitakdev.lotoyzr.models.Member;

@Dependent
@Stateful
public class MemberDAO {
	
	private static final Logger LOGGER = Logger.getLogger( MemberDAO.class.getName());
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void createMember(Member member){
		try{
		manager.persist(member);
		LOGGER.log(Level.FINE, "Member "+member.toString()+" created");
		}
		catch(PersistenceException e){
			System.out.println(e);
			LOGGER.log(Level.SEVERE,"Member could not be created", e);
		}
	}

	public void updateMemberById(int member_id, Member member) {
		System.out.println("Updating member: "+member_id+" with Values "+member.toString());
		Member member_to_be=loadMemberById(member_id);
		System.out.println("Member to be has been recovered: "+member_to_be.toString());
		member_to_be=member;
		member_to_be.setMember_id(member_id);
		System.out.println("Got values after swaping: "+member.toString());
		manager.merge(member_to_be);
		System.out.println("Member: "+member_id+" updated");
	}

	public void removeMemberById(int member_id) {
		Member member=loadMemberById(member_id);
		manager.remove(member);
		System.out.println("Member: "+member.toString()+" removed");
	}
	
	public Member loadMemberById(int member_id){
		System.out.println("Retrieving member id: "+member_id);
		return manager.find(Member.class, member_id);
	}
		
}
