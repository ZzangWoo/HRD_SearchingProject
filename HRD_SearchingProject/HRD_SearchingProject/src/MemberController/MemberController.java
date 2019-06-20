package MemberController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberBBS_Commend.BBSDeleteDo;
import MemberBBS_Commend.BBSMainDo;
import MemberBBS_Commend.BBSUpdateDo;
import MemberBBS_Commend.BBSViewDo;
import MemberBBS_Commend.BBSWriteDo;
import MemberCommend.MemberAttendanceDo;
import MemberCommend.MemberCheckPWDo;
import MemberCommend.MemberCheckedID;
import MemberCommend.MemberCheckedName;
import MemberCommend.MemberDeleteDo;
import MemberCommend.MemberJoinDo;
import MemberCommend.MemberListDo;
import MemberCommend.MemberLoginDo;
import MemberCommend.MemberLogoutDo;
import MemberCommend.MemberUpdateDo;
import MemberCommend.QueryCommend;
import MemberDAO.MemberDAO;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/****** URI에서 필요한 부분만 나눠서 따로 변수에 저장하는 부분 ******/
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String uriFinal = uri.substring(path.length()+1, uri.length()-3);
		/*************************************************/

		// 컨트롤러가 View(jsp) 페이지로 request, response를 전송
		String url = "";
		
		QueryCommend action = null;
		
		if (uriFinal.equals("MemberJoin")) { // 회원가입
			action = new MemberJoinDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberList")){ // 회원조회
			action = new MemberListDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberLogin")) { // 로그인
			action = new MemberLoginDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("CheckPW")) { // 회원 수정하기 전에 비밀번호 확인
			action = new MemberCheckPWDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberUpdate")) { // 회원 수정
			action = new MemberUpdateDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberCheckedID")) { // 아이디 체크
			action = new MemberCheckedID();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberCheckedName")) { // 이름 체크
			action = new MemberCheckedName();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberDelete")) { // 회원 삭제
			action = new MemberDeleteDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberLogout")) { // 로그아웃
			action = new MemberLogoutDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("MemberAttendance")) { // 출석부 조회
			action = new MemberAttendanceDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} 
		/******************** 게시판 controller ************************/
		else if (uriFinal.equals("BBSMain")) { // 게시판 메인페이지 (게시판 조회)
			action = new BBSMainDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("BBSWrite")) { // 게시판 쓰기
			action = new BBSWriteDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("BBSView")) { // 게시판 글 확인
			action = new BBSViewDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("BBSDelete")) { // 게시판 글 삭제
			action = new BBSDeleteDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		} else if (uriFinal.equals("BBSUpdate")) { // 게시판 글 수정
			action = new BBSUpdateDo();
			action.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		}
		/*************************************************************/
		else {
			System.out.println("에러에러");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response); // url(View) 페이지로 request, response를 전송
		
	}
	
	
}