package br.com.bitakdev.lotoyzr.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.models.Member;

@Dependent
@Stateful
public class HouseDAO {
	
	private static final Logger LOGGER = Logger.getLogger( HouseDAO.class.getName());
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;

	public void createHouse(House house){
		try{
		manager.persist(house);
		LOGGER.log(Level.FINE, "House "+house.toString()+" created");
		}
		catch(PersistenceException e){
			System.out.println(e);
			LOGGER.log(Level.SEVERE,"House could not be created", e);
		}
	}

	public void updateHouseById(int house_id, House house) {
		System.out.println("Updating house: "+house_id+" with Values "+house.toString());
		House house_to_be=loadHouseById(house_id);
		System.out.println("House to be has been recovered: "+house_to_be.toString());
		house_to_be=house;
		house_to_be.setHouse_id(house_id);
		System.out.println("Got values after swaping: "+house.toString());
		manager.merge(house_to_be);
		System.out.println("House: "+house_id+" updated");
	}

	public void removeHouseById(int house_id) {
		House house=loadHouseById(house_id);
		manager.remove(house);
		System.out.println("House: "+house.toString()+" removed");
	}
	
	public House loadHouseById(Integer house_id){
		System.out.println("Retrieving house id: "+house_id);
		return manager.find(House.class, house_id);
	}
	
	public int sumMemberHouseAdminCount(Member member) {
		TypedQuery<House> query = manager.createQuery("select h from House h join fetch h.house_administrators a where a.member_id=:member", House.class)
				  .setParameter("member", member.getMember_id());
		System.out.println("Numero de casas administradas: "+query.getResultList().size());
		return query.getResultList().size();
	}
}
