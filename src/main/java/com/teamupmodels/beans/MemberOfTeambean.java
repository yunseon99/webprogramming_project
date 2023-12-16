package com.teamupmodels.beans;

public class MemberOfTeambean {
	private String id;
	private String name;
	private String phone;
	private String intro;
	
	public MemberOfTeambean() {}
	public MemberOfTeambean(String id, String name, String phone, String intro) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.intro = intro;
	}
	
	//Getter
	public String getMem_id() { return id;}
	public String getMem_name() { return name;}
	public String getMem_phone() { return phone;}
	public String getMem_intro() { return intro;}
	
	//Setter
	public void setMem_id(String id) {this.id = id;}
	public void setMem_name(String name) {this.name = name;}
	public void setMem_phone(String phone) {this.phone = phone;}
	public void setMem_intro(String intro) {this.intro = intro;}
}