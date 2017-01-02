package br.com.bitakdev.lotoyzr.util;

import java.util.Calendar;

import javax.inject.Inject;

import br.com.bitakdev.lotoyzr.daos.BillDAO;
import br.com.bitakdev.lotoyzr.daos.HouseDAO;
import br.com.bitakdev.lotoyzr.daos.MemberDAO;
import br.com.bitakdev.lotoyzr.models.Bill;
import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.models.Member;

public class BillUtil {

	@Inject
	HouseDAO houseDAO;
	@Inject
	MemberDAO memberDAO;
	@Inject
	BillDAO billDAO;
	
	Calendar cal = Calendar.getInstance();
	
	public String associateBillResponsible(int bill_id, int house_id, int member_id) {
		House house=houseDAO.loadHouseById(house_id);
		Member member=memberDAO.loadMemberById(member_id);
		Bill bill=billDAO.loadBillById(bill_id);
		
		bill.setBill_house_owned(house);
		bill.setBill_responsible(member);
		bill.setBill_updated_by(member.getMember_email());
		bill.setBill_last_update_date(cal);
		billDAO.updateBillById(bill.getBill_id(), bill);
		return "bill_updated";
	}

}
