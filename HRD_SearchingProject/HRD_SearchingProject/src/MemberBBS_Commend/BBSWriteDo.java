package MemberBBS_Commend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;

public class BBSWriteDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("게시판 글쓰기");
		
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession hs = request.getSession();
		
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		String userID = (String) hs.getAttribute("sessionID");
		
		int rs = dao.Write_BBS(bbsTitle, bbsContent, userID);
		
		if (rs == 1) { // 글쓰기 성공
			
			System.out.println("게시판 글쓰기 성공!!");
			request.setAttribute("url", "/BBS_Write_Success.jsp");
			/*
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('게시물이 게재되었습니다');");
			script.println("location.href = 'BBSMain.do';");
			script.println("</script>");
			*/
			
		} else {
			
			System.out.println("글쓰기 실패");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글쓰기 실패');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			
		}
		
	}

}
