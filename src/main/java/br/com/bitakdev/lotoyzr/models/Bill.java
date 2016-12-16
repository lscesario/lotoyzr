package br.com.bitakdev.lotoyzr.models;



import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlRootElement
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bill_id;
	@XmlElement(name="bill_name")
	private String bill_name;
	@XmlElement(name="bill_status")
	private String bill_status;
	private Calendar bill_last_update_date;
	private Calendar bill_created_date;

	
	public String getBill_status() {
		return bill_status;
	}
	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}

	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public String getBill_name() {
		return bill_name;
	}
	public void setBill_name(String bill_name) {
		this.bill_name = bill_name;
	}
	public Calendar getBill_last_update_date() {
		return bill_last_update_date;
	}
	public void setBill_last_update_date(Calendar bill_last_update_date) {
		this.bill_last_update_date = bill_last_update_date;
	}	
	public Calendar getBill_created_date() {
		return bill_created_date;
	}
	public void setBill_created_date(Calendar bill_created_date) {
		this.bill_created_date = bill_created_date;
	}
	
	@Override
	public String toString(){
		    return "Name: '" + this.bill_name + "', Id: '" + this.bill_id + "', Status: '" + this.bill_status+"'";
		} 
		
	public Bill(){
		this.bill_created_date=Calendar.getInstance();
	}
}

