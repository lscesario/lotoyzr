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
<<<<<<< HEAD
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
=======
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
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
		
=======
	private String bill_description;
	@ManyToOne
	private Member bill_responsible;
	@ManyToOne
	private House bill_house_owned;
	private float bill_current_month_value;
	private String bill_updated_by;
	
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
=======
	public String getBill_description() {
		return bill_description;
	}
	public void setBill_description(String bill_description) {
		this.bill_description = bill_description;
	}
>>>>>>> refs/remotes/origin/master
	public Member getBill_responsible() {
		return bill_responsible;
	}
	public void setBill_responsible(Member bill_responsible) {
		this.bill_responsible = bill_responsible;
	}
<<<<<<< HEAD
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
=======
	public float getBill_current_month_value() {
		return bill_current_month_value;
	}
	public void setBill_current_month_value(float bill_current_month_value) {
		this.bill_current_month_value = bill_current_month_value;
	}	
	public House getBill_house_owned() {
		return bill_house_owned;
	}
	public void setBill_house_owned(House bill_house_owned) {
		this.bill_house_owned = bill_house_owned;
	}
	public String getBill_updated_by() {
		return bill_updated_by;
	}
	public void setBill_updated_by(String bill_updated_by) {
		this.bill_updated_by = bill_updated_by;
	}
	
	
	
>>>>>>> refs/remotes/origin/master
	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", bill_name=" + bill_name + ", bill_status=" + bill_status
				+ ", bill_last_update_date=" + bill_last_update_date + ", bill_created_date=" + bill_created_date
				+ ", bill_description=" + bill_description + ", bill_responsible=" + bill_responsible
				+ ", bill_house_owned=" + bill_house_owned + ", bill_current_month_value=" + bill_current_month_value
				+ ", bill_updated_by=" + bill_updated_by + "]";
	}
	public Bill(){
		this.bill_created_date=Calendar.getInstance();
	}
}

