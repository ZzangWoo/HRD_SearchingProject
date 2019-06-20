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
		
		System.out.println("게시판 수정");
		
		MemberDAO dao = MemberDAO.getInstance();
		String str_bbsID = String.valueOf(request.getParameter("bbsID"));
		int bbsID = Integer.parseInt(str_bbsID);
		System.out.println("에러3");
		/********************** updateWay 설명 ***************************/
		// updateWay = 1 => ViewBBS (게시글 확인 페이지)에서 수정페이지로 넘어갈 때 사용
		// updateWay = 2 => 수정페이지에서 수정되고 DB에 수정된거 저장할 때 사용
		String str_updateWay = String.valueOf(request.getParameter("updateWay"));
		System.out.println("에러4");
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
				System.out.println("게시판 수정 성공");
				
				request.setAttribute("url", "/BBS_Update_Success.jsp");
			}
			
		} else {
			System.out.println("있을 수 없는 일이오");
		}
		
		
	}

}
