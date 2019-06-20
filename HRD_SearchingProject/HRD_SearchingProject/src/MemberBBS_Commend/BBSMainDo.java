package MemberBBS_Commend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;
import MemberDTO.MemberBBS_DTO;

public class BBSMainDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("�Խ��� ���������� (�Խ��� ��ȸ)");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int bbsNum = dao.Find_BBS_Number(); // �Խñ� ���� ����
		/************** ��ü ������ ��� *************/
		int wholePageNum = 0;
		if (bbsNum % 10 == 0) {
			wholePageNum = bbsNum / 10;
		} else {
			wholePageNum = bbsNum / 10 + 1;
		}
		System.out.println("wholePageNum : " + wholePageNum);
		System.out.println("bbsNum : " + bbsNum);
		/*****************************************/
		
		int pageNum = 1; // �Խ��� ó�� ���� �� ���������� 1 ( 1�� ó�� ���°� ���ϰ� ���� �ֱ� �� 10���� ����� �� �ְ� ���� )
		// ������ ���õǸ�
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			System.out.println("���Դ�1");
		}
		System.out.println("pageNum : " + pageNum);
		
		ArrayList<MemberBBS_DTO> bbsLists = dao.BBS_Lists(pageNum);
		
		request.setAttribute("bbsLists", bbsLists);
		request.setAttribute("wholePageNum", wholePageNum);
		request.setAttribute("url", "/MainBBS.jsp");
		
	}

}
