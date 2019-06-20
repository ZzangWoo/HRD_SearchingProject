package MemberBBS_Commend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;
import MemberDTO.MemberBBS_DTO;

public class BBSViewDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("�Խñ� Ȯ��");
		
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession hs = request.getSession();
		
		String strBbsID = String.valueOf(request.getParameter("bbsID"));
		int bbsID = Integer.parseInt(strBbsID);
		
		MemberBBS_DTO dto = dao.View_BBS(bbsID);
		
		if (dto != null) {
			
			System.out.println("�Խ��� �� Ȯ�� ����");
			
			request.setAttribute("viewDTO", dto);
			request.setAttribute("url", "/ViewBBS.jsp");
			
		} else {
			
			System.out.println("�Խ��� �� Ȯ�� ����");
			
		}
		
		
		
	}

}
