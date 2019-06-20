package MemberBBS_Commend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;
import MemberDTO.MemberBBS_DTO;

public class BBSUpdateDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("�Խ��� ����");
		
		MemberDAO dao = MemberDAO.getInstance();
		String str_bbsID = String.valueOf(request.getParameter("bbsID"));
		int bbsID = Integer.parseInt(str_bbsID);
		System.out.println("����3");
		/********************** updateWay ���� ***************************/
		// updateWay = 1 => ViewBBS (�Խñ� Ȯ�� ������)���� ������������ �Ѿ �� ���
		// updateWay = 2 => �������������� �����ǰ� DB�� �����Ȱ� ������ �� ���
		String str_updateWay = String.valueOf(request.getParameter("updateWay"));
		System.out.println("����4");
		int updateWay = Integer.parseInt(str_updateWay);
		/***************************************************************/
		
		System.out.println("updateWay : " + updateWay);
		
		if (updateWay == 1) {
			
			System.out.println("ViewBBS.jsp -> UpdateBBS.jsp");
			MemberBBS_DTO dto = dao.View_BBS(bbsID);
			request.setAttribute("preUpdateDTO", dto);
			request.setAttribute("url", "/UpdateBBS.jsp");
			
		} else if (updateWay == 2) {
			
			System.out.println("UpdateBBS.jsp -> BBSMain.do");
			
			String bbsTitle = String.valueOf(request.getParameter("bbsTitle"));
			String bbsContent = String.valueOf(request.getParameter("bbsContent"));
			
			int rs = dao.Update_BBS(bbsID, bbsTitle, bbsContent);
			
			if (rs == 1) {
				System.out.println("�Խ��� ���� ����");
				
				request.setAttribute("url", "/BBS_Update_Success.jsp");
			}
			
		} else {
			System.out.println("���� �� ���� ���̿�");
		}
		
		
	}

}
