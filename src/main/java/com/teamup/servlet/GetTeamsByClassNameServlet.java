package com.teamup.servlet;

import com.teamupmodels.beans.Teambean;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class GetTeamsByClassNameServlet extends HttpServlet {

	// 세션 가져오기
    HttpSession session = request.getSession();

    // 세션에서 사용자 아이디 가져오기
    String userId = (String) session.getAttribute("userId");
    
    private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    public GetTeamsByClassNameServlet() {
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 매개변수 추출
    
        String className = request.getParameter("search_input");
        String pageinfo = request.getParameter("page");
        // TeamUpDAO를 사용하여 Team 배열을 가져오는 로직
        Teambean[] teams = null;
		try {
			teams = teamDAO.getTeamsByClassName(className);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//팀 배열들 중에서 사용자가 이미 참여 중인 팀이면은 그 index의 팀의 check = false;
		

        // 결과를 요청 속성에 저장
        request.setAttribute("info", teams);
        request.setAttribute("page", pageinfo);
        request.setAttribute("search_input",className);
        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("FindTeam.jsp");
        dispatcher.forward(request, response);
    }
}
