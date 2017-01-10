package br.com.bitakdev.lotoyzr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	private String role_description;
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_description() {
		return role_description;
	}
	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}
	
	@Override
	public String toString() {
		return "MemberRoles [role_id=" + role_id + ", role_description=" + role_description + "]";
	}
	
	
	
}
