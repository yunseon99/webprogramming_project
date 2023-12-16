package com.teamup.servlet;

import com.teamupmodels.beans.Teambean;
import com.teamupDB.dao.teamupDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class GetTeamsByClassNameAndMasterIdServlet extends HttpServlet {

    private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    public GetTeamsByClassNameAndMasterIdServlet() {
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 매개변수 추출
        String masterUserId = request.getParameter("masteruserid");
        String className = request.getParameter("classname");

        // TeamUpDAO를 사용하여 Team 배열을 가져오는 로직
        Teambean[] teams = teamDAO.getTeamsByMasterUserAndClass(masterUserId, className);

        // 결과를 요청 속성에 저장
        request.setAttribute("teams", teams);
        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("MyPage.jsp");
        dispatcher.forward(request, response);
    }
}
