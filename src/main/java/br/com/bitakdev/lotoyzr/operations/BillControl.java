package br.com.bitakdev.lotoyzr.operations;

import java.util.Calendar;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.daos.BillDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Bill;
import br.com.bitakdev.lotoyzr.models.Member;
import br.com.bitakdev.lotoyzr.security.LoginControl;
import br.com.bitakdev.lotoyzr.util.BillUtil;
import br.com.bitakdev.lotoyzr.util.HouseUtil;

@Model
public class BillControl {
	
	Member member=new Member();
	
	@Inject
	BillDAO billDAO;
	@Inject
	MemberDAO memberDAO;
	@Inject
	LoginControl lc;
	@Inject
	HouseUtil hu;
	@Inject
	BillUtil bu;
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
	
	@Transactional
	public void updateBillById(int bill_id, Bill bill){
		bill.setBill_last_update_date(cal);
		billDAO.updateBillById(bill_id, bill);
	}
		
	public Bill loadBillById(int bill_id){
		return billDAO.loadBillById(bill_id);
	}

	public Response associateBillResponsible(int bill_id, int house_id, int member_id, String JWT) {
		System.out.println("BillControl - associateBill - Start");
		String member_email=lc.getLoggedMember(JWT);
		if(member_email.equals("invalid_token")){
			return Response.status(400).entity("invalid_token").build();
		}
		System.out.println("BillControl - associateBill - Got JWT: "+JWT);
		if(hu.checkIfHouseAdmin(member_email, house_id).equals(Constants.RETURN_METHOD_OK)){
			System.out.println("BillControl - associateBill - User Admin proceeding to associate Bill");
			bu.associateBillResponsible(bill_id, house_id, member_id);
			System.out.println("BillControl - associateBill - End");
		    return Response.status(201).entity("bill_associated_to_member_id_"+member_id).build();
		}
		return Response.status(400).entity("logged_user_not_admin").build();
	}

}
