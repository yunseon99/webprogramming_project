package com.teamupdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamup.model.User;
import com.teamup.model.Team;
import com.teamup.model.Match;

public class teamupDAO {
	private String dbUrl = "jdbc:mysql://localhost:3306/TeamUp_DB";

	// 데이터베이스 연결 메소드 
	public Connection getConnection() {
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         return DriverManager.getConnection(dbUrl,
	        		 "root", "0000");
	     } catch (SQLException | ClassNotFoundException e) {
	         e.printStackTrace();
	         return null;
	     }
	}
    
	//add user
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (id, password, name, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getPhone());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //return user by id
    public User getUserById(String userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("id"),
                		rs.getString("password"),
                		rs.getString("name"),
                		rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // add team
    public boolean addTeam(Team team) {
        String sql = "INSERT INTO teams (team_id, class_name, masteruser_id, introduction, requirement, count, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getTeamId());
            pstmt.setString(2, team.getClassName());
            pstmt.setString(3, team.getMasteruser());
            pstmt.setString(4, team.getIntro());
            pstmt.setString(5, team.getReq());
            pstmt.setInt(6, team.getCount());
            pstmt.setInt(7, team.getTotal());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //return team by id
    public Team getTeamById(String teamId) {
        String sql = "SELECT * FROM teams WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Team team = new Team(rs.getString("team_id"),
                		rs.getString("class_name"),
                		rs.getString("masteruser_id"),
                		rs.getString("introduction"),
                		rs.getString("requirement"),
                		rs.getInt("count"),rs.getInt("total"));
                return team;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // User 정보 업데이트
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET password = ?, name = ?, phone = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // id로 User 정보 삭제
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Team 정보 업데이트
    public boolean updateTeam(Team team) {
        String sql = "UPDATE teams SET class_name = ?, masteruser_id = ?, introduction = ?, requirement = ?, count = ?, total = ? WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getClassName());
            pstmt.setString(2, team.getMasteruser());
            pstmt.setString(3, team.getIntro());
            pstmt.setString(4, team.getReq());
            pstmt.setInt(5, team.getCount());
            pstmt.setInt(6, team.getTotal());
            pstmt.setString(7, team.getTeamId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // teamid로 Team 정보 삭제
    public boolean deleteTeam(String teamId) {
        String sql = "DELETE FROM teams WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // add match
    public boolean addMatch(Match match) {
        String sql = "INSERT INTO match (team_id, id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, match.getTeamId());
            pstmt.setString(2, match.getUserId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Match getMatchByUserId(String userId) {
        String sql = "SELECT * FROM match WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Match match = new Match();
                    match.setTeamId(rs.getString("team_id"));
                    match.setUserId(rs.getString("id"));
                    return match;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //teamid와 userid로 match 삭제
    public boolean deleteMatch(String teamId, String userId) {
        String sql = "DELETE FROM match WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Team> getTeamsByClassName(String className) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams WHERE class_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, className);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Team team = new Team();
                    team.setTeamId(rs.getString("team_id"));
                    team.setClassName(rs.getString("class_name"));
                    team.setMasteruser(rs.getString("masteruser_id"));
                    team.setIntro(rs.getString("introduction"));
                    team.setReq(rs.getString("requirement"));
                    team.setCount(rs.getInt("count"));
                    team.setTotal(rs.getInt("total"));
                    teams.add(team);
                }
            }
        }
        return teams;
    }
}


