package com.teamupDB.dao;

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
	    String checkDuplicationQuery = "SELECT COUNT(*) FROM users WHERE id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmtCheck = conn.prepareStatement(checkDuplicationQuery)) {
	        
	        // 사용자 ID 중복 확인
	        pstmtCheck.setString(1, user.getId());
	        ResultSet rs = pstmtCheck.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false; // 중복되는 ID가 있으면 false 반환
	        }

	        // 중복되는 ID가 없으면 사용자 추가
	        String sql = "INSERT INTO users (id, password, name, phone) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, user.getId());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getPhone());
	            int affectedRows = pstmt.executeUpdate();
	            return affectedRows > 0;
	        }

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

    // create team
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
    
    public Team[] getTeamsByClassName(String className) throws SQLException {
        List<Team> teamList = new ArrayList<>();
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
                    teamList.add(team);
                }
            }
        }

        // 리스트를 배열로 변환
        Team[] teams = new Team[teamList.size()];
        teams = teamList.toArray(teams);

        return teams;
    }

    
    public boolean isUserInClassTeam(String userId, String className) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 데이터베이스 연결 메소드 호출
            // 1단계: class_name을 바탕으로 Team 테이블에서 해당 수업의 팀들을 조회
            String teamQuery = "SELECT team_id FROM Team WHERE class_name = ?";
            pstmt = conn.prepareStatement(teamQuery);
            pstmt.setString(1, className);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String teamId = rs.getString("team_id");

                // 2단계: 조회된 team_id와 넘겨받은 user_id를 이용하여 Match 테이블에서 확인
                String matchQuery = "SELECT COUNT(*) FROM Match WHERE team_id = ? AND id = ?";
                PreparedStatement pstmtMatch = conn.prepareStatement(matchQuery);
                pstmtMatch.setString(1, teamId);
                pstmtMatch.setString(2, userId);
                ResultSet rsMatch = pstmtMatch.executeQuery();
                if (rsMatch.next() && rsMatch.getInt(1) > 0) {
                    return true; // 사용자가 해당 클래스의 팀에 속해 있음
                }
            }
            return false; // 사용자가 해당 클래스의 어떤 팀에도 속해있지 않음
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean makeMatch(Matchbean match) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 데이터베이스 연결

            // 1단계: team_id를 바탕으로 class_name 추출
            String classQuery = "SELECT class_name FROM Team WHERE team_id = ?";
            pstmt = conn.prepareStatement(classQuery);
            pstmt.setString(1, match.getTeamId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String className = rs.getString("class_name");

                // 2단계: user_id와 class_name을 바탕으로 isUserInClassTeam 함수를 이용해 검사
                if (!isUserInClassTeam(match.getUserId(), className)) {
                    // 3단계: false일 경우 매치 생성
                    String insertQuery = "INSERT INTO Match (team_id, id, intro) VALUES (?, ?, ?)";
                    PreparedStatement pstmtInsert = conn.prepareStatement(insertQuery);
                    pstmtInsert.setString(1, match.getTeamId());
                    pstmtInsert.setString(2, match.getUserId());
                    pstmtInsert.setString(3, match.getIntro());
                    int result = pstmtInsert.executeUpdate();
                    return result > 0; // 매치 생성 성공 여부 반환
                }
            }
            return false; // 이미 다른 팀에 속해있거나, class_name을 찾을 수 없는 경우
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean createTeam(String class_name, String masteruser_id, String introduction, 
		      String requirement, int count, int total) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = getConnection(); // 데이터베이스 연결
		
		// 3단계: masteruser_id와 class_name을 바탕으로 makeMatch 함수 수행
		Match match = new Match(class_name + masteruser_id, masteruser_id, introduction);
		if (makeMatch(match)) {
		// 4단계: makematch가 true이면 새로운 팀을 만들고 데이터베이스에 저장
		String insertQuery = "INSERT INTO Team (team_id, class_name, masteruser_id, introduction, requirement, count, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(insertQuery);
		pstmt.setString(1, class_name + masteruser_id);
		pstmt.setString(2, class_name);
		pstmt.setString(3, masteruser_id);
		pstmt.setString(4, introduction);
		pstmt.setString(5, requirement);
		pstmt.setInt(6, count);
		pstmt.setInt(7, total);
		int result = pstmt.executeUpdate();
		return result > 0; // 팀 생성 성공 여부 반환
		}
		return false; // 이미 다른 팀에 속해있어 팀을 만들 수 없는 경우
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		} finally {
		// 자원 정리
		try {
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	}

    
    public boolean login(String userId, String password) {
        String query = "SELECT password FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return dbPassword.equals(password); // 비밀번호 비교
            } else {
                return false; // 사용자 ID가 없음
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean makeApply(Applybean apply) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 데이터베이스 연결

            // 1단계: team_id를 바탕으로 class_name 추출
            String classQuery = "SELECT class_name FROM Team WHERE team_id = ?";
            pstmt = conn.prepareStatement(classQuery);
            pstmt.setString(1, apply.getTeamId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String className = rs.getString("class_name");

                // 2단계: user_id와 class_name을 바탕으로 isUserInClassTeam 함수를 이용해 검사
                if (!isUserInClassTeam(apply.getUserId(), className)) {
                    // 3단계: false일 경우 매치 생성
                    String insertQuery = "INSERT INTO Match (team_id, id, intro) VALUES (?, ?, ?)";
                    PreparedStatement pstmtInsert = conn.prepareStatement(insertQuery);
                    pstmtInsert.setString(1, apply.getTeamId());
                    pstmtInsert.setString(2, apply.getUserId());
                    pstmtInsert.setString(3, apply.getIntro());
                    int result = pstmtInsert.executeUpdate();
                    return result > 0; // 매치 생성 성공 여부 반환
                }
            }
            return false; // 이미 다른 팀에 속해있거나, class_name을 찾을 수 없는 경우
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //teamid와 userid로 match 삭제
    public boolean deleteApply(String teamId, String userId) {
        String sql = "DELETE FROM Apply WHERE team_id = ? AND id = ?";
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
}


