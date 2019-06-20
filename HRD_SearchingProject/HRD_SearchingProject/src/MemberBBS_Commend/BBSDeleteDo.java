package MemberBBS_Commend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;

public class BBSDeleteDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("게시글 삭제");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String str_bbsID = String.valueOf(request.getParameter("bbsID"));
		int bbsID = Integer.parseInt(str_bbsID);
		
		int rs = dao.Delete_BBS(bbsID);
		
		if (rs == 1) {
			System.out.println("게시글 삭제 성공");
			
			request.setAttribute("url", "/BBS_Delete_Success.jsp");
		} else {
			System.out.println("게시글 삭제 실패");
		}
		
	}

}
