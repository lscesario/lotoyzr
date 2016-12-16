package br.com.bitakdev.lotoyzr.daos;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;

import br.com.bitakdev.lotoyzr.models.Bill;

@Dependent
@Stateful
public class BillDAO {
	
	private static final Logger LOGGER = Logger.getLogger( BillDAO.class.getName());
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void createBill(Bill bill){
		try{
		manager.persist(bill);
		LOGGER.log(Level.FINE, "Bill "+bill.toString()+" created");
		}
		catch(PersistenceException e){
			System.out.println(e);
			LOGGER.log(Level.SEVERE,"Bill could not be created", e);
		}
	}

	public void updateBillById(int bill_id, Bill bill) {
		System.out.println("Updating bill: "+bill_id+" with Values "+bill.toString());
		Bill bill_to_be=loadBillById(bill_id);
		System.out.println("Bill to be has been recovered: "+bill_to_be.toString());
		bill_to_be=bill;
		bill_to_be.setBill_id(bill_id);
		System.out.println("Got values after swaping: "+bill.toString());
		manager.merge(bill_to_be);
		System.out.println("Bill: "+bill_id+" updated");
	}

	public void removeBillById(int bill_id) {
		Bill bill=loadBillById(bill_id);
		manager.remove(bill);
		System.out.println("Bill: "+bill.toString()+" removed");
	}
	
	public Bill loadBillById(Integer bill_id){
		System.out.println("Retrieving bill id: "+bill_id);
		return manager.find(Bill.class, bill_id);
	}
}
