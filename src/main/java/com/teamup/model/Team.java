package com.teamup.model;

public class Team {
	private String team_id;	//팀 고유 id
	private String class_name;	//팀의 수업이름
	private String masteruser_id; 	//팀장의 아이디. user테이블의 id필드
	private String introduction;	//팀 소개
	private String requirement;	//팀원 조건
	int count; 		//현재 팀 인원
	int total;		//최대 파티 인원

    public Team() {}
    public Team(String team_id, String class_name, 
    		String masteruser_id, String introduction, String requirement,
    		int count, int total) {
    	this.team_id = team_id;
    	this.class_name = class_name;
    	this.masteruser_id = masteruser_id;
    	this.introduction = introduction;
    	this.requirement = requirement;
    	this.count = count;
    	this.total = total;
    }
    // Getter와 Setter 메소드
    public String getTeamId() { return team_id; }

    public void setTeamId(String teamId){this.team_id = teamId;}

    public String getClassName() {return class_name;}

    public void setClassName(String teamName) {this.class_name = teamName;}

    public String getMasteruser() {return masteruser_id;}

    public void setMasteruser(String masteruser_id) {
        this.masteruser_id = masteruser_id;
    }
    
    public String getIntro() {return introduction;}

    public void setIntro(String introduction) {
        this.introduction = introduction;
    }
    
    public String getReq() {return requirement;}

    public void setReq(String req) {
        this.requirement = req;
    }
    
    public int getCount() {return count;}
    
    public void setCount(int count) {this.count = count;}
    
    public int getTotal() {return total;}
    
    public void setTotal(int tot) {this.total = tot;}
}