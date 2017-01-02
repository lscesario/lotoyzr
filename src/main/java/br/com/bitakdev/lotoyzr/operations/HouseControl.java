package br.com.bitakdev.lotoyzr.operations;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.conf.Constants;
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
	HouseUtil hu;
	
	@Transactional
	public Response createHouse(House house){
		String response;
		System.out.println(house.toString());
		try{
<<<<<<< HEAD
			if(houseUtil.checkHouseIntegrityOnCreate(house).equals("tudo_certo")){
=======
			if(hu.checkHouseIntegrity(house).equals(Constants.RETURN_METHOD_OK)){
>>>>>>> refs/remotes/origin/master
			houseDAO.createHouse(house);
			response="house_"+house.getHouse_id()+"_created";
			return Response.status(200).entity(response).build();
				}
			else
				{
				System.out.println("Um dos administradores associados a casa gerencia mais de quatro casas");
<<<<<<< HEAD
				response = "member_exceed_administrator_limit";
				return Response.status(400).entity(response).build();
=======
				return "admin_max_houses_reached";
>>>>>>> refs/remotes/origin/master
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
<<<<<<< HEAD
		response="unexpected_error";
		return Response.status(500).entity(response).build();
=======
		return Constants.RETURN_METHOD_UNEXPECTED;
>>>>>>> refs/remotes/origin/master
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
