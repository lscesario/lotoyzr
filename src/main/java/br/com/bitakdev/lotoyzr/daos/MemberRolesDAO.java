package br.com.bitakdev.lotoyzr.daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bitakdev.lotoyzr.models.Member;
import br.com.bitakdev.lotoyzr.models.MemberRoles;

public class MemberRolesDAO {
	
	
	
	@Inject
	MemberDAO memberDAO;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	CriteriaBuilder criteriaBuilder;
    CriteriaQuery<MemberRoles> criteriaQuery;
    Root<MemberRoles> queryRoot;
    
    
	
	public List<MemberRoles> loadMemberRolesByMemberId(int member_id){
		//select r from MemberRoles r join r.role_id a where a.member_id=:member_id
		//select h from House h join h.house_administrators a where a.member_id=:member
		//select a.firstName, a.lastName from Book b join b.authors a where b.id = :id
		//createQuery("from Ofertas o inner join fetch o.curso c where c.id_curso = " + id_curso + " order by campo_escolhido");
		//TypedQuery<MemberRoles> query = manager.createQuery("select mr from MemberRoles mr join mr.role_id m where Member.member_id=:member_id", MemberRoles.class)
		//TypedQuery<MemberRoles> query = manager.createQuery("select mr from MemberRoles mr join fetch mr.role_id m where m.member_id=:member_id", MemberRoles.class)
		
		System.out.println("MemberRolesDAO - loadMemberRolesByMemberId - Start");
		TypedQuery<Member> query = manager.createQuery("select m from Member m join fetch m.member_roles mr where m.member_id=:member_id", Member.class)
				  .setParameter("member_id", member_id);
		List<MemberRoles> mr=query.getSingleResult().getMember_roles();
		System.out.println("MemberRolesDAO - loadMemberRolesByMemberId - MemberRoles: "+mr.toString());
		System.out.println("MemberRolesDAO - loadMemberRolesByMemberId - End");
		return mr;
	}
}
