package MemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberDAO.MemberDAO;

public class MemberDeleteDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("회원탈퇴 페이지");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String newUserID = request.getParameter("userID"); // 회원탈퇴 텍스트 박스에 있는 ID
		String newUserPW = request.getParameter("userPW"); // 회원탈퇴 텍스트 박스에 있는 PW
		
		HttpSession hs = request.getSession();
		
		String oldUserID = (String) hs.getAttribute("sessionID");
		String oldUserPW = (String) hs.getAttribute("sessionPW");
		
		if (newUserID.equals(oldUserID) && newUserPW.equals(oldUserPW)) {
			
			int rs = dao.delete(newUserID, newUserPW);
			
			if (rs == 1) {
				System.out.println("회원탈퇴 성공");
				hs.invalidate();
				request.setAttribute("url", "/index_login.jsp");
			} else {
				System.out.println("회원탈퇴 실패");
			}
			
		} else {
			
			System.out.println("아이디랑 비밀번호 일치하지 않음");
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디와 비밀번호가 일치하지 않습니다')");
			script.println("history.back()");
			script.println("</script>");
			
		}
		
		
		
	}

}
