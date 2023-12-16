package com.teamup.servlet;

import java.io.IOException;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuitTeamServlet {

	 private teamupDAO teamDAO; // TeamUpDAO 인스턴스

	    public QuitTeamServlet() {
	        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 요청으로부터 매개변수 추출
	    	
	    	String team_id = request.getParameter("team_id");
	 	    
	    	// 세션 가져오기
	        HttpSession session = request.getSession();

	        // 세션에서 사용자 아이디 가져오기
	        String userId = (String) session.getAttribute("userId");
	        
	        teamDAO.deleteMatch(team_id, userId);
	        
	        response.sendredirect("Pre_MypageServlet.java");
	    }
}
