package br.com.bitakdev.lotoyzr.tests;

import java.util.Calendar;

import org.junit.Test;

import br.com.bitakdev.lotoyzr.models.Bill;
import br.com.bitakdev.lotoyzr.operations.BillControl;
import br.com.bitakdev.lotoyzr.resources.BillResource;

public class Tests {
	
	@Test
	public void testDAO(){
		Bill bill = new Bill();
		
		bill.setBill_name("mimp");
		bill.setBill_status("mimp");
		bill.setBill_id(1);
		
		
	}
	
	
}
