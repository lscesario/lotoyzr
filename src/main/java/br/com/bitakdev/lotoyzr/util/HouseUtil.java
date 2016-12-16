package br.com.bitakdev.lotoyzr.util;

import java.util.Iterator;

import javax.inject.Inject;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.models.Member;

public class HouseUtil {
	
	Member member = new Member();
	
	@Inject
	HouseDAO houseDAO;
	@Inject
	MemberDAO memberDAO;
	
	
	public String checkHouseIntegrity(House house){
		Iterator<Member> adminIterator = house.getHouse_administrators().iterator();
		int counter=0;
		while(adminIterator.hasNext()){
			System.out.println("Checking member: "+house.getHouse_administrators().get(counter).getMember_id());
			if(checkIfAdminOwnsFiveHouses(house.getHouse_administrators().get(counter).getMember_id()).equals("tudo_certo")){
				counter++;
				adminIterator.next();
				}
			else{
				System.out.println("membro_"+house.getHouse_administrators().get(counter).getMember_id()+"_administra_mais_de_4_casas - checkHouseIntegrity");
				return "membro_"+house.getHouse_administrators().get(counter).getMember_id()+"_administra_mais_de_4_casas";
				}
			}
		System.out.println("Tudo certo! checkHouseIntegrity");
		return "tudo_certo";
		}
		
	
	public String checkIfAdminOwnsFiveHouses(int member_id){
		member=memberDAO.loadMemberById(member_id);
		System.out.println("Usuario "+member_id+" administra: "+houseDAO.sumMemberHouseAdminCount(member));
		if(houseDAO.sumMemberHouseAdminCount(member)>Constants.ADMIN_HOUSES_MAX){
			System.out.println("membro_"+member.getMember_id()+"_administra_mais_de_4_casas - checkIfAdminOwnsFiveHouses");
			return "membro_"+member.getMember_id()+"_administra_mais_de_4_casas";
		}
		System.out.println("Tudo certo! checkIfAdminOwnsFiveHouses");
		return "tudo_certo";
	}
	
}
