package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.teamupdb.dao.teamupDAO;
import com.teamup.model.User;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 폼에서 데이터 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        
        // 비밀번호 일치 확인
        if (!password.equals(confirmPassword)) {
            // 비밀번호가 일치하지 않을 경우의 처리
            response.getWriter().println("Passwords do not match.");
            return; // 여기서 처리를 중단
        }

        // User 객체 생성 및 데이터 설정
        User newUser = new User(id, password, name, phone);

        // DAO를 통해 데이터베이스에 사용자 추가
        teamupDAO dao = new teamupDAO();
        boolean result = dao.addUser(newUser);

        if (result) {
            // 회원가입 성공 처리: 로그인 페이지나 메인 페이지로 리디렉션
            response.sendRedirect("login.jsp"); // LOGIN URL
        } else {
            // 회원가입 실패 
            response.getWriter().println("Registration failed");
           
        }
    }
}
