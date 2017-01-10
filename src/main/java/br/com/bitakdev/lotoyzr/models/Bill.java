package br.com.bitakdev.lotoyzr.models;



import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private String bill_description;
	@ManyToOne
	private Member bill_responsible;
	@ManyToOne
	private House bill_house_owned;
	private float bill_current_month_value;
	private String bill_updated_by;
	
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
	public String getBill_description() {
		return bill_description;
	}
	public void setBill_description(String bill_description) {
		this.bill_description = bill_description;
	}
	public Member getBill_responsible() {
		return bill_responsible;
	}
	public void setBill_responsible(Member bill_responsible) {
		this.bill_responsible = bill_responsible;
	}
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
	
	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", bill_name=" + bill_name + ", bill_status=" + bill_status
				+ ", bill_description=" + bill_description + ", bill_responsible=" + bill_responsible
				+ ", bill_house_owned=" + bill_house_owned + ", bill_current_month_value=" + bill_current_month_value
				+ ", bill_updated_by=" + bill_updated_by + "]";
	}
	public Bill(){
		this.bill_created_date=Calendar.getInstance();
	}
}

