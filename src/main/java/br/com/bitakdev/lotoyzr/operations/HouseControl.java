package br.com.bitakdev.lotoyzr.operations;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.models.Member;
import br.com.bitakdev.lotoyzr.util.HouseUtil;

@Model
public class HouseControl {
	
	Member member = new Member();
	
	@Inject
	HouseDAO houseDAO;
	@Inject
	MemberDAO memberDAO;
	@Inject
	HouseUtil houseUtil;
	
	@Transactional
	public String createHouse(House house){
		System.out.println(house.toString());
		try{
			if(houseUtil.checkHouseIntegrity(house).equals("tudo_certo")){
			houseDAO.createHouse(house);
			return "House "+house.getHouse_id()+" created";
				}
			else
				{
				System.out.println("Um dos administradores associados a casa gerencia mais de quatro casas");
				return "Um dos administradores associados a casa gerencia mais de quatro casas";
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		return "unexpected";
	}
	
	public void removeHouseById(int house){
		houseDAO.removeHouseById(house);
	}
	
	public void updateHouseById(int house_id, House house){
		houseDAO.updateHouseById(house_id, house);
	}
		
	public House loadHouseById(int house_id){
		return houseDAO.loadHouseById(house_id);
	}
	
}
