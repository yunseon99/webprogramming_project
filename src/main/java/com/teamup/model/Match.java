package com.teamup.model;

public class Match {
    private String teamId;
    private String userId;

    // 기본 생성자
    public Match() {}
    public Match(String teamId, String userId) {
    	this.teamId = teamId;
    	this.userId = userId;
    }
    // Getter와 Setter 메소드
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
