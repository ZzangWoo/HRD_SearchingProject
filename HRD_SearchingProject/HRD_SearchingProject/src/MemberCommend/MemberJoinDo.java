package MemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberDAO.MemberDAO;

public class MemberJoinDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("회원 가입");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		int stuNum = dao.userNameCheck(userName);
		System.out.println(stuNum);
		
		if (stuNum == 100) {
			/************** 학생 이름이 존재하지 않을 때 ******************/
			System.out.println("회원가입 실패");	
			request.setAttribute("url", "/Name_Duplication.jsp");
			/*
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('해당 학생이 없습니다')");
			script.println("history.back()");
			script.println("</script>");
			*/
			/*********************************************/
		} else {
			
			int rs = dao.insert(userID, userPW, userName, userEmail);
			System.out.println(rs);
			
			if (rs == 1) {
				
				System.out.println("회원가입 성공");
				request.setAttribute("url", "/JoinSuccess.jsp");
				/*
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원가입에 성공했습니다.');");
				script.println("location.href = 'index.jsp';");
				script.println("</script>");
				*/
				
			} 
			
		}
			
	}

}
