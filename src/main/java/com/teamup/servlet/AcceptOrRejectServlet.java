package com.teamup.servlet;

import java.io.IOException;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptOrRejectServlet {

	 private teamupDAO teamDAO; // TeamUpDAO 인스턴스

	    public AcceptOrRejectServlet() {
	        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 요청으로부터 매개변수 추출
	    	
	    	String applicant_id = request.getParameter("want_join_id");
	    	String team_id = request.getParameter("team_id");
	    	String choice = request.getParameter("choice");
	 	    
	    	request.setAttribute("search_name", classname);
	        request.setAttribute("page", page);
	        request.setAttribute("check", isappliable);
	        
	        //
	        
	        if(choice == "수락") {
	        	//수락이라면 팀 명단에 추가
	        	//team_id와 user_id로 Apply객체 반환
	        	//반환된 Apply객체에서 intro 추출
	        	//team_id, user_id, intro로 makeMatch
	        	//Apply테이블에서 삭제
	        	teamDAO.makeMatch();
	        }else {
	        	//거절이라면 대기 명단에서 삭제
	        	teamDAO.deleteApply(team_id, applicant_id);
	        }
	        
	        
	        response.sendredirect("MyPage.jsp");
	    }
}
