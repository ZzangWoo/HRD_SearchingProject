package MemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberDAO.MemberDAO;

public class MemberCheckPWDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("회원 수정 전에 비밀번호 확인");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		HttpSession hs = request.getSession();
		
		String userID = (String) hs.getAttribute("sessionID");
		String userPW = request.getParameter("userPW");
		
		int rs = dao.checkPW(userID, userPW);
		
		if (rs == 1) { // 비밀번호가 일치하면
			request.setAttribute("url", "/MemberUpdate.jsp");
			System.out.println("비밀번호 일치");
		} else {
			System.out.println("비밀번호 다름");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('틀린 비밀번호입니다')");
			script.println("history.back()");
			script.println("</script>");
		}
		
	}

}
