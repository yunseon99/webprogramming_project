package com.findteam;

public class Team_info{
	private String class_name;//수업명
	private int total;//총인원
	private int count;//총인원중 참가인원
	private String introduction;//팀소개
	private String requirement;//팀원조건
	private String user;//팀 리더 아이디

	boolean check;//사용자가 이 팀에 신청했는지 여부 true=이미 신청함, false=신청안함
	
	public Team_info(){
		this.class_name=null;
		this.total=0;
		this.count=0;
		this.introduction=null;
		this.requirement=null;
		this.check=false;
		this.user=null;
	}
	
	public String getclass_name() {
		return class_name;
	}
	
	public void setclass_name(String class_name) {
		this.class_name=class_name;
	}
	
	public int gettotal() {
		return total;
	}
	
	public void settotal(int total) {
		this.total=total;
	}
	public int getcount() {
		return count;
	}
	
	public void setcount(int count) {
		this.count=count;
	}
	public String getintroduction() {
		return introduction;
	}
	
	public void setintroduction(String introduction) {
		this.introduction=introduction;
	}
	public String getrequirement() {
		return requirement;
	}
	
	public void setrequirement(String requirement) {
		this.requirement=requirement;
	}
	public String getuser() {
		return user;
	}
	
	public void setuser(String user) {
		this.user=user;
	}
	public boolean getcheck() {
		return check;
	}
	
	public void setcheck(boolean check) {
		this.check=check;
	}
}
