package br.com.bitakdev.lotoyzr.daos;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import br.com.bitakdev.lotoyzr.conf.Constants;
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
		System.out.println("MemberDAO - updateMemberById - Updating member: "+member_id+" with Values "+member.toString());
		Member member_to_be=loadMemberById(member_id);
		System.out.println("MemberDAO - updateMemberById - Member to be has been recovered: "+member_to_be.toString());
		member_to_be=member;
		member_to_be.setMember_id(member_id);
		System.out.println("MemberDAO - updateMemberById - Got values after swaping: "+member.toString());
		manager.merge(member_to_be);
		System.out.println("MemberDAO - updateMemberById - Member: "+member_id+" updated");
	}

	public void removeMemberById(int member_id) {
		Member member=loadMemberById(member_id);
		manager.remove(member);
		System.out.println("MemberDAO - removeMemberById - Member: "+member.toString()+" removed");
	}
	
	public Member loadMemberById(int member_id){
		System.out.println("MemberDAO - loadMemberById - Retrieving member id: "+member_id);
		return manager.find(Member.class, member_id);
	}

	public Member loadMemberByFbId(String member_fb_id) {
		 System.out.println("MemberDAO - loadMemberByFbId - Retrieving Facebook member id: "+member_fb_id);		 
		 try{
		 String query = "select a from Member a where a.member_fb_id=:member_fb_id";
		 		Member i = manager.createQuery(query, Member.class)
				 					.setParameter("member_fb_id", member_fb_id)
				 					.getSingleResult();
		 		System.out.println("MemberDAO - loadMemberByFbId - Got Member: "+i.toString());
		 		return loadMemberById(i.getMember_id());
		 }
		 catch(NoResultException e){
			 Member m = new Member();
			 m.setMember_id(Constants.VOCE_NAO_VALE_NADA);
			 return m;
		 }
	}

	public Member loadMemberByEmail(String member_email) {
		System.out.println("MemberDAO - loadMemberByEmail - Start");
		System.out.println("MemberDAO - loadMemberByEmail - Retrieving user by username: "+ member_email);
		try{
		String query = "select a from Member a where a.member_email=:member_email";
				Member m = manager.createQuery(query, Member.class)
						.setParameter("member_email", member_email)
	 					.getSingleResult();
				System.out.println("Got Member: "+m.toString());
				return m;
		}catch(NoResultException e){
			Member m = new Member();
			m.setMember_id(Constants.VOCE_NAO_VALE_NADA);
			System.out.println("MemberDAO - loadMemberByEmail - End");
			return m;
		}	
	}

}
