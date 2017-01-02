package br.com.bitakdev.lotoyzr.models;



import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	@OneToOne
	private Member bill_responsible;
	@OneToOne
	private Member bill_last_updated_by;
	@XmlElement(name="bill_member_percentage_amount")
	@MapKeyColumn(name="bill_member_percentage_id")
    @Column(name="bill_member_percentage_amount")
	@ElementCollection 
	private Map<Integer, Integer> bill_member_percentage_amount=new HashMap<Integer, Integer>();
	@ManyToOne
	@XmlElement(name="bill_house_owner")
	private House bill_house_owner;
		
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
	public Member getBill_responsible() {
		return bill_responsible;
	}
	public void setBill_responsible(Member bill_responsible) {
		this.bill_responsible = bill_responsible;
	}
	public Member getBill_last_updated_by() {
		return bill_last_updated_by;
	}
	public void setBill_last_updated_by(Member bill_last_updated_by) {
		this.bill_last_updated_by = bill_last_updated_by;
	}
	public Map<Integer, Integer> getBill_member_percentage_amount() {
		return bill_member_percentage_amount;
	}
	public void setBill_member_percentage_amount(Map<Integer, Integer> bill_member_percentage_amount) {
		this.bill_member_percentage_amount = bill_member_percentage_amount;
	}
	public House getBill_house_owner() {
		return bill_house_owner;
	}
	public void setBill_house_owner(House bill_house_owner) {
		this.bill_house_owner = bill_house_owner;
	}
	@Override
	public String toString(){
		    return "Name: '" + this.bill_name + "', Id: '" + this.bill_id + "', Status: '" + this.bill_status+"'";
		} 
		
	public Bill(){
		this.bill_created_date=Calendar.getInstance();
	}
}

