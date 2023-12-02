package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.teamupdb.dao.teamupDAO;
import com.teamup.model.Team;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GetTeamsByClassNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String className = request.getParameter("className");

        try {
            teamupDAO dao = new teamupDAO();
            List<Team> teams = dao.getTeamsByClassName(className);
            	
            List<Team> filteredTeams = new ArrayList<>();

            // count가 total과 같지 않은 팀만 필터링
            for (Team team : teams) {
                if (team.getCount() != team.getTotal()) {
                    filteredTeams.add(team);
                }
            }
            // 결과를 request 속성에 추가
            request.setAttribute("info", filteredTeams);

            // 결과를 표시하는 JSP 페이지로 포워딩
            //request.getRequestDispatcher("showTeams.jsp").forward(request, response);
        } catch (SQLException e) {
            // SQL 예외 처리
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
