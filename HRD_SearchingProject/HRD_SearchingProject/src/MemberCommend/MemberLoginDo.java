package MemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberDAO.MemberDAO;

public class MemberLoginDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("로그인 페이지");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		System.out.println(userName);
		
		int rs = dao.login(userID, userPW, request, response);
		
		if (rs == 1) {
			System.out.println("로그인 성공");
			
			request.setAttribute("url", "/index_login.jsp");
		} else if (rs == 2) {
			System.out.println("비밀번호 다름");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('틀린 비밀번호입니다')");
			script.println("history.back()");
			script.println("</script>");
		} else if (rs == 3) {
			System.out.println("아이디가 없음");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('없는 아이디입니다')");
			script.println("history.back()");
			script.println("</script>");
		}
		
	}

}
