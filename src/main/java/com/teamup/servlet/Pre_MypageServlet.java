package com.teamup.servlet;

import com.teamupmodels.beans.Teambean;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class Pre_MypageServlet extends HttpServlet {

    private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    public Pre_MypageServlet() {
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
    }

    HttpSession session = request.getSession(false); // false: 새 세션 생성 방지
	String user_Id = null;

	    if (session != null) {
	       user_Id = (String) session.getAttribute("id");
	    }

	    if (masteruserId == null) {
	        // 로그인하지 않은 경우 처리
	        response.sendRedirect("login.jsp"); // 로그인 페이지로 리디렉션
	        return; // 메소드 종료
	    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String[] masters = null; //팀장의 이름들
    	Teambean[] teams = null; // 로그인한 사용자가 참가 중인 팀들
    	
		//Need to be implemented..!!

        // 결과를 요청 속성에 저장
        request.setAttribute("leader", masters);
        request.setAttribute("teams", teams);
        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("MyPage.jsp");
        dispatcher.forward(request, response);
    }
}
