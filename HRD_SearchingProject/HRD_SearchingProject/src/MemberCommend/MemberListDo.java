package MemberCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberDAO.MemberDAO;
import MemberDTO.MemberDTO;

public class MemberListDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("회원조회");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		ArrayList<MemberDTO> memberLists = dao.Member_List();
		
		// DB 데이터
		request.setAttribute("memberLists", memberLists);
		System.out.println("DAO -> DB data");
		
		// URL
		request.setAttribute("url", "/MemberList.jsp");
		System.out.println("Show the MemberList.jsp");
		
	}

}
