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
		
		System.out.println("�Խ��� �۾���");
		
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession hs = request.getSession();
		
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		String userID = (String) hs.getAttribute("sessionID");
		
		int rs = dao.Write_BBS(bbsTitle, bbsContent, userID);
		
		if (rs == 1) { // �۾��� ����
			
			System.out.println("�Խ��� �۾��� ����!!");
			request.setAttribute("url", "/BBS_Write_Success.jsp");
			/*
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�Խù��� ����Ǿ����ϴ�');");
			script.println("location.href = 'BBSMain.do';");
			script.println("</script>");
			*/
			
		} else {
			
			System.out.println("�۾��� ����");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�۾��� ����');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			
		}
		
	}

}
