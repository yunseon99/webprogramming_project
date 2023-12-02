package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupdb.dao.teamupDAO;
import com.teamup.model.User;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 폼에서 로그인 데이터 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        teamupDAO dao = new teamupDAO();
        User user = dao.getUserById(id);

        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공 처리, 세션 설정(id저장), 메인 페이지로 리디렉션 
        	HttpSession session = request.getSession();
        	session.setAttribute("id", user.getId());
            //response.sendRedirect("main.jsp");
        } else {
            // 로그인 실패 처리: 예를 들어 에러 메시지 출력
            response.getWriter().println("Invalid username or password");
        }
    }
}