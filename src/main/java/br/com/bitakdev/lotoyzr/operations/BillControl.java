package br.com.bitakdev.lotoyzr.operations;

import java.util.Calendar;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bitakdev.lotoyzr.daos.BillDAO;
import br.com.bitakdev.lotoyzr.models.Bill;

@Model
public class BillControl {
	
	@Inject
	BillDAO billDAO;
	Calendar cal=Calendar.getInstance();
	
	@Transactional
	public void createBill(Bill bill){
		System.out.println(bill.toString());
		try{
		billDAO.createBill(bill);
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
	}
	
	public void removeBillById(int bill){
		billDAO.removeBillById(bill);
	}
	
	public void updateBillById(int bill_id, Bill bill){
		bill.setBill_last_update_date(cal);
		billDAO.updateBillById(bill_id, bill);
	}
		
	public Bill loadBillById(int bill_id){
		return billDAO.loadBillById(bill_id);
	}

}
