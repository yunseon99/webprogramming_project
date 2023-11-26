package com.findteam;

public class MyPage_info {
	private String applicant;
	private String class_name;
	private String phonenumber;
	
	public MyPage_info() {
		this.applicant=null;
		this.class_name=null;
		this.phonenumber=null;
	}
	public String getapplicant() {
		return applicant;
	}
	public void setapplicant(String applicant) {
		this.applicant=applicant;
	}
	public String getclass_name() {
		return class_name;
	}
	public void setclass_name(String class_name) {
		this.class_name=class_name;
	}
	public String getphonenumber() {
		return phonenumber;
	}
	public void setphonenumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}
}
