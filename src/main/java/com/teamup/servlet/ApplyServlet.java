package com.teamup.servlet;

import com.teamupmodels.beans.Teambean;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ApplyServlet extends HttpServlet {

    private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    public ApplyServlet() {
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 매개변수 추출
    	
    	String name_input = request.getParameter("name_input");
    	String major_input = request.getParameter("major_input");
    	String student_num = request.getParameter("student_num");
    	String grade_input = request.getParameter("grade_input");
    	String self_introduction = request.getParameter("self_introduction");
    	String team_id = request.getParameter("team_id");
    	String page = request.getParameter("page");
    	String classname = request.getParameter("search_name");
    	
        boolean isappliable = false;
        
        // 세션 가져오기
        HttpSession session = request.getSession();

        // 세션에서 사용자 아이디 가져오기
        String userId = (String) session.getAttribute("userId");
        
     // userId가 null이 아닌 경우, 사용자가 로그인한 것으로 간주
        if (userId != null) {
            Applybean ap = new Applybean(team_id,userId,self_introduction);
            isappliable = teamDAO.makeApply(ap);
        } 
        
        // 결과를 요청 속성에 저장
        request.setAttribute("search_name", classname);
        request.setAttribute("page", page);
        request.setAttribute("check", isappliable);
        
        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("FindTeam.jsp");
        dispatcher.forward(request, response);
    }
}
