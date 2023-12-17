package com.teamupDB.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamupmodels.beans.*;

public class teamupDAO {
	
	//DB URL
	private String dbUrl = "jdbc:mysql://localhost:3306/TeamUp_DB";

	//CONNECT TO DB 
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
    
	/* USER TABLE CRUD */
	//(CREATE) 새로운 user 등록
	public boolean addUser(Userbean user) {
	    String checkDuplicationQuery = "SELECT COUNT(*) FROM User WHERE id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmtCheck = conn.prepareStatement(checkDuplicationQuery)) {
	        
	        // 사용자 ID 중복 확인
	        pstmtCheck.setString(1, user.getId());
	        ResultSet rs = pstmtCheck.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false; // 중복되는 ID가 있으면 false 반환
	        }

	        // 중복되는 ID가 없으면 사용자 추가
	        String sql = "INSERT INTO User (id, password, name, phone) VALUES (?, ?, ?, ?)";
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

    
    //(READ) return user by id
    public Userbean getUserById(String userId) {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Userbean user = new Userbean(rs.getString("id"),
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
    
    //(UPDATE) User 정보 업데이트
    public boolean updateUser(Userbean user) {
        String sql = "UPDATE User SET password = ?, name = ?, phone = ? WHERE id = ?";
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
    
    //(DELETE) id로 User 삭제
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM User WHERE id = ?";
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
    /* USER TABLE CRUD END */
    
    /* TEAM TABLE CRUD */
    // (CREATE) create team
    public boolean addTeam(Teambean team) {
        String sql = "INSERT INTO Team (class_name, masteruser_id, introduction, requirement, count, total) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getClassName());
            pstmt.setString(2, team.getMasteruser());
            pstmt.setString(3, team.getIntro());
            pstmt.setString(4, team.getReq());
            pstmt.setInt(5, team.getCount());
            pstmt.setInt(6, team.getTotal());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // team객체가아닌 string들로 team 생성
    public boolean addTeam(String class_name, String masteruser_id, String introduction, 
		      String requirement, int count, int total) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = getConnection(); // 데이터베이스 연결
		
		// 3단계: masteruser_id와 class_name을 바탕으로 makeMatch 함수 수행
		Matchbean match = new Matchbean(class_name + masteruser_id, masteruser_id, introduction);
		if (addMatch(match)) {
		// 4단계: makematch가 true이면 새로운 팀을 만들고 데이터베이스에 저장
		String insertQuery = "INSERT INTO Team (class_name, masteruser_id, introduction, requirement, count, total) VALUES (?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(insertQuery);
		pstmt.setString(1, class_name);
		pstmt.setString(2, masteruser_id);
		pstmt.setString(3, introduction);
		pstmt.setString(4, requirement);
		pstmt.setInt(5, count);
		pstmt.setInt(6, total);
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
    
    // (READ) return team by id
    public Teambean getTeamById(String teamId) {
        String sql = "SELECT * FROM Team WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Teambean team = new Teambean(
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
    
    // (UPDATE) TEAM INFO UPDATE
    public boolean updateTeam(Teambean team) {
        String sql = "UPDATE Team SET class_name = ?, masteruser_id = ?, introduction = ?, requirement = ?, count = ?, total = ? WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getClassName());
            pstmt.setString(2, team.getMasteruser());
            pstmt.setString(3, team.getIntro());
            pstmt.setString(4, team.getReq());
            pstmt.setInt(5, team.getCount());
            pstmt.setInt(6, team.getTotal());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // (DELETE) teamid로 Team 정보 삭제
    public boolean deleteTeam(String teamId) {
        String sql = "DELETE FROM Team WHERE team_id = ?";
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
    /* TEAM TABLE CRUD END */
    
    /* MATCH TABLE CRUD */
    // (CREATE) add match
    public boolean addMatch(Matchbean match) {
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
    
    // (READ) Return MATCH by teamId and userId
    public Matchbean getMatch(String teamId, String userId) {
        String query = "SELECT * FROM Match WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                     rs.getString("intro"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Matchbean[] getMatchesByUserId(String userId) {
        String query = "SELECT * FROM Match WHERE id = ?";
        List<Matchbean> matches = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Matchbean match = new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Convert the list to an array and return
        return matches.toArray(new Matchbean[0]);
    }
    
    public Matchbean[] getMatchesByTeamId(String teamId) {
        String query = "SELECT * FROM Match WHERE team_id = ?";
        List<Matchbean> matches = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teamId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Matchbean match = new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Convert the list to an array and return
        return matches.toArray(new Matchbean[0]);
    }
    // (UPDATE) MATCH INFO UPDATE
    public boolean updateMatch(Matchbean match) {
        String query = "UPDATE Match SET intro = ? WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, match.getIntro());
            pstmt.setString(2, match.getTeamId());
            pstmt.setString(3, match.getUserId());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (DELETE) teamid와 userid로 match 삭제
    public boolean deleteMatch(String teamId, String userId) {
        String query = "DELETE FROM Match WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* MATCH TABLE CRUD END */
    
    /* APPLY TABLE CRUD */
    // (CREATE) ADD APPLY
    public boolean addApply(Applybean apply) {
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
    // (READ) GET APPLY BY TEAMID AND USERID
    public Applybean getApply(String teamId, String userId) {
        String query = "SELECT * FROM Apply WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Applybean(rs.getString("team_id"), rs.getString("id"),
                                     rs.getString("intro"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Applybean[] getApplyByTeamId(String userId) {
        String query = "SELECT * FROM Apply WHERE team_id = ?";
        List<Applybean> applies = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Applybean match = new Applybean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    applies.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Convert the list to an array and return
        return applies.toArray(new Applybean[0]);
    }
    
    // (UPDATE) APPLY INFO UPDATE
    public boolean updateApply(Applybean apply) {
        String query = "UPDATE Apply SET intro = ? WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, apply.getIntro());
            pstmt.setString(2, apply.getTeamId());
            pstmt.setString(3, apply.getUserId());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // (DELETE) DELETE APPLY
    public boolean deleteApply(String teamId, String userId) {
        String query = "DELETE FROM Apply WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* APPLY TABLE CRUD END */
    
    /* MEMBEROFTEAM TABLE CRUD */
    // (CREATE) 
    public boolean addMemberOfTeam(MemberOfTeambean member) {
        String query = "INSERT INTO MemberOfTeam (id, team_id, name, phone, intro, grade, studentNum, major) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, member.getMem_id());
            pstmt.setString(2, member.getMem_teamid());
            pstmt.setString(3, member.getMem_name());
            pstmt.setString(4, member.getMem_phone());
            pstmt.setString(5, member.getMem_intro());
            pstmt.setString(6, member.getMem_Grade());
            pstmt.setString(7, member.getMem_studentNum());
            pstmt.setString(8, member.getMem_Major());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (READ) RETURN MEMOFTEAM BY ID AND TEAMID
    public MemberOfTeambean getMemberOfTeam(String id, String teamId) {
        String query = "SELECT * FROM MemberOfTeam WHERE id = ? AND team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberOfTeambean(rs.getString("id"), rs.getString("team_id"),
                                            rs.getString("name"), rs.getString("phone"),
                                            rs.getString("intro"), rs.getString("grade"),
                                            rs.getString("studentNum"), rs.getString("major"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // (READ 2) Return all MemberOfTeam records for a given userId as an array
    public MemberOfTeambean getMembersOfTeamByUserId(String userId) {
        String query = "SELECT * FROM MemberOfTeam WHERE id = ?";
        MemberOfTeambean members = new MemberOfTeambean();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MemberOfTeambean member = new MemberOfTeambean(rs.getString("id"), rs.getString("team_id"),
                                                                  rs.getString("name"), rs.getString("phone"),
                                                                  rs.getString("intro"), rs.getString("grade"),
                                                                  rs.getString("studentNum"), rs.getString("major"));
                    members = member;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list to an array and return
        return members;
    }
    // (UPDATE) UPDATE MEMBEROFTEAM INFO
    public boolean updateMemberOfTeam(MemberOfTeambean member) {
        String query = "UPDATE MemberOfTeam SET team_id = ?, name = ?, phone = ?, intro = ?, grade = ?, studentNum = ?, major = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, member.getMem_teamid());
            pstmt.setString(2, member.getMem_name());
            pstmt.setString(3, member.getMem_phone());
            pstmt.setString(4, member.getMem_intro());
            pstmt.setString(5, member.getMem_Grade());
            pstmt.setString(6, member.getMem_studentNum());
            pstmt.setString(7, member.getMem_Major());
            pstmt.setString(8, member.getMem_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (DELETE) DELETE MEMBEROFTEAM INFO
    public boolean deleteMemberOfTeam(String id, String teamId) {
        String query = "DELETE FROM MemberOfTeam WHERE id = ? AND team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, teamId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* MEMBEROFTEAM TABLE CRUD END */
    
    /***************************************************************************************************/
    
    /* 기타 연산 */
    
    /***************************************************************************************************/
    
    //수업명을 바탕으로 해당 수업의 팀 배열 리턴
    public Teambean[] getTeamsByClassName(String className) throws SQLException {
        List<Teambean> teamList = new ArrayList<>();
        String sql = "SELECT * FROM Team WHERE class_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, className);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Teambean team = new Teambean();
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
        Teambean[] teams = new Teambean[teamList.size()];
        teams = teamList.toArray(teams);

        return teams;
    }

    //해당 유저가 그 수업의 어떤 팀에라도 속해있는지를 검사
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
    
    //로그인 성공 여부(id에 따른 비밀번호 일치 여부 검사)
    public boolean login(String userId, String password) {
        String query = "SELECT password FROM User WHERE id = ?";
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
    
   
}


