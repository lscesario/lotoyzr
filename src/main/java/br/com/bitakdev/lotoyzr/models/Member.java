package br.com.bitakdev.lotoyzr.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
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
	private String member_fb_id;
	private String member_password;
	@ElementCollection(targetClass=String.class)
	private List<String> member_roles;
	private String member_email;
	
	
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
	public String getMember_fb_id() {
		return member_fb_id;
	}
	public void setMember_fb_id(String member_fb_id) {
		this.member_fb_id = member_fb_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public List<String> getMember_roles() {
		return member_roles;
	}
	public void setMember_roles(List<String> member_roles) {
		this.member_roles = member_roles;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_name=" + member_name + ", member_age=" + member_age
				+ ", member_bank=" + member_bank + ", member_bank_account=" + member_bank_account
				+ ", member_created_date=" + member_created_date + ", member_fb_id=" + member_fb_id + "]";
	}
	public Member(){
		this.member_created_date=Calendar.getInstance();
	}
	
	

}
