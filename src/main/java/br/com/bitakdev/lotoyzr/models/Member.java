package br.com.bitakdev.lotoyzr.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer member_id; 
	private String member_name;
	private Integer member_age;
	private String member_bank;
	private String member_bank_account;
	private Calendar member_created_date;
	
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public Integer getMember_age() {
		return member_age;
	}
	public void setMember_age(Integer member_age) {
		this.member_age = member_age;
	}
	public String getMember_bank() {
		return member_bank;
	}
	public void setMember_bank(String member_bank) {
		this.member_bank = member_bank;
	}
	public String getMember_bank_account() {
		return member_bank_account;
	}
	public void setMember_bank_account(String member_bank_account) {
		this.member_bank_account = member_bank_account;
	}
	
	public Calendar getMember_created_date() {
		return member_created_date;
	}
	public void setMember_created_date(Calendar member_created_date) {
		this.member_created_date = member_created_date;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_name=" + member_name + ", member_age=" + member_age
				+ ", member_bank=" + member_bank + ", member_bank_account=" + member_bank_account + "]";
	}
	
	public Member(){
		this.member_created_date=Calendar.getInstance();
	}
	
	

}
