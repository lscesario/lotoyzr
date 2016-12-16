package br.com.bitakdev.lotoyzr.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer house_id;
	private String house_name;
	private String house_address;
	private Calendar house_since;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "house_administrator", joinColumns = {
			@JoinColumn(name = "house_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "member_id",
					nullable = false, updatable = false) })
	private List<Member> house_administrators;
		
	public List<Member> getHouse_administrators() {
		return house_administrators;
	}
	public void setHouse_administrators(List<Member> house_administrators) {
		this.house_administrators = house_administrators;
	}
	public Integer getHouse_id() {
		return house_id;
	}
	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
	public String getHouse_address() {
		return house_address;
	}
	public void setHouse_address(String house_address) {
		this.house_address = house_address;
	}	
	public Calendar getHouse_since() {
		return house_since;
	}
	public void setHouse_since(Calendar house_since) {
		this.house_since = house_since;
	}
	
	
	@Override
	public String toString() {
		return "House [house_id=" + house_id + ", house_name=" + house_name + ", house_address=" + house_address
				+ ", house_since=" + house_since + ", house_administrators=" + house_administrators + "]";
	}
	public House(){
		this.house_since=Calendar.getInstance();
	}
	
	
}
	
	
	
