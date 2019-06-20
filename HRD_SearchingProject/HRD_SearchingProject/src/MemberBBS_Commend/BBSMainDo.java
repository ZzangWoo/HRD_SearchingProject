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

		System.out.println("게시판 메인페이지 (게시판 조회)");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int bbsNum = dao.Find_BBS_Number(); // 게시글 개수 저장
		/************** 전체 페이지 계산 *************/
		int wholePageNum = 0;
		if (bbsNum % 10 == 0) {
			wholePageNum = bbsNum / 10;
		} else {
			wholePageNum = bbsNum / 10 + 1;
		}
		System.out.println("wholePageNum : " + wholePageNum);
		System.out.println("bbsNum : " + bbsNum);
		/*****************************************/
		
		int pageNum = 1; // 게시판 처음 들어올 때 변수설정은 1 ( 1은 처음 들어온걸 뜻하고 제일 최근 글 10개를 출력할 수 있게 해줌 )
		// 페이지 선택되면
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			System.out.println("들어왔다1");
		}
		System.out.println("pageNum : " + pageNum);
		
		ArrayList<MemberBBS_DTO> bbsLists = dao.BBS_Lists(pageNum);
		
		request.setAttribute("bbsLists", bbsLists);
		request.setAttribute("wholePageNum", wholePageNum);
		request.setAttribute("url", "/MainBBS.jsp");
		
	}

}
