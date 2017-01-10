package br.com.bitakdev.lotoyzr.util;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.daos.MemberRolesDAO;
import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.models.Member;
import br.com.bitakdev.lotoyzr.models.MemberRoles;

public class HouseUtil {
	
	Member member = new Member();
	House house = new House();
	
	@Inject
	HouseDAO houseDAO;
	@Inject
	MemberDAO memberDAO;
	@Inject
	MemberRolesDAO memberRolesDAO;
	
	
	public String checkHouseIntegrity(House house){
		Iterator<Member> adminIterator = house.getHouse_administrators().iterator();
		int counter=0;
		while(adminIterator.hasNext()){
			System.out.println("Checking member: "+house.getHouse_administrators().get(counter).getMember_id());
			if(checkIfAdminOwnsFiveHouses(house.getHouse_administrators().get(counter).getMember_id()).equals(Constants.RETURN_METHOD_OK)){
				counter++;
				adminIterator.next();
				}
			else{
				System.out.println("membro_"+house.getHouse_administrators().get(counter).getMember_id()+"_administra_mais_de_4_casas - checkHouseIntegrity");
				return "membro_"+house.getHouse_administrators().get(counter).getMember_id()+"_administra_mais_de_4_casas";
				}
			}
		System.out.println("Tudo certo! checkHouseIntegrity");
		return Constants.RETURN_METHOD_OK;
		}
		
	
	public String checkIfAdminOwnsFiveHouses(int member_id){
		member=memberDAO.loadMemberById(member_id);
		System.out.println("Usuario "+member_id+" administra: "+houseDAO.sumMemberHouseAdminCount(member));
		if(houseDAO.sumMemberHouseAdminCount(member)>Constants.ADMIN_HOUSES_MAX){
			System.out.println("membro_"+member.getMember_id()+"_administra_mais_de_4_casas - checkIfAdminOwnsFiveHouses");
			return "membro_"+member.getMember_id()+"_administra_mais_de_4_casas";
		}
		System.out.println("Tudo certo! checkIfAdminOwnsFiveHouses");
		return Constants.RETURN_METHOD_OK;
	}
	
	public String checkIfHouseAdmin(String member_email, int house_id){
	   house=houseDAO.loadHouseById(house_id);
	   member=memberDAO.loadMemberByEmail(member_email);
	   List<MemberRoles> member_roles=memberRolesDAO.loadMemberRolesByMemberId(member.getMember_id());
	   
	   for(int i=0; i<member_roles.size(); i++)
	   if(member_roles.get(i).getRole_id()==Constants.ROLE_HOUSE_ADMINISTRATOR){
	    		return Constants.RETURN_METHOD_OK;
	    	}
	   
	   return Constants.USER_NOT_ADMINISTRATOR;
   }

}
