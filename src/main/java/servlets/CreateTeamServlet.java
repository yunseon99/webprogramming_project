package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupdb.dao.teamupDAO;
import com.teamup.model.Team;
import java.io.IOException;

public class CreateTeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false); // false: 새 세션 생성 방지
    	String masteruserId = null;

    	    if (session != null) {
    	        masteruserId = (String) session.getAttribute("id");
    	    }

    	    if (masteruserId == null) {
    	        // 로그인하지 않은 경우 처리
    	        response.sendRedirect("login.jsp"); // 로그인 페이지로 리디렉션
    	        return; // 메소드 종료
    	    }

        // 폼에서 나머지 팀 데이터 추출
        String teamId = request.getParameter("teamId");
        String className = request.getParameter("className");
        String introduction = request.getParameter("introduction");
        String requirement = request.getParameter("requirement");
        int count = Integer.parseInt(request.getParameter("count"));
        int total = Integer.parseInt(request.getParameter("total"));

        // Team 객체 생성 및 데이터 설정
        Team newTeam = new Team(teamId, className, masteruserId, introduction, requirement, count, total);

        // DAO를 통해 데이터베이스에 팀 추가
        teamupDAO dao = new teamupDAO();
        boolean result = dao.addTeam(newTeam);

        if (result) {
            // 팀 생성 성공 처리:  팀 목록 페이지로 리디렉션
            response.sendRedirect("teamList.jsp"); // 예시 URL
        } else {
            // 팀 생성 실패 처리: 에러 메시지 출력
            response.getWriter().println("Failed to create team");
        }
    }
}