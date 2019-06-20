package MemberCommend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberDAO.MemberDAO;
import MemberDTO.MemberDTO_forAttendance;

public class MemberAttendanceDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("출석부 조회");
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberDTO_forAttendance> attendanceLists = 
				new ArrayList<MemberDTO_forAttendance>(); // 출석부 받아올 리스트
				
		HttpSession hs = request.getSession();
		String sessionName = (String) hs.getAttribute("sessionName");
		String sessionID = (String) hs.getAttribute("sessionID");
		
		/**************** 세션값 받아와서 관리자계정인지 아닌지 판별 **************/
		int checkID = 0; // 관리자 계정인지 아닌지 판별해줄 변수
		if (sessionID.equals("root")) 
			checkID = 1; // 관리자 계정이면 1
		/*************************************************************/
		
		// idxType : userID냐 userName이냐 판별
		// idxMonth : 조회 기간 판별
		int idxType = 0, idxMonth = 0;
		String selectType = request.getParameter("selectType");
		String selectMonth = request.getParameter("selectMonth");
		String findName = request.getParameter("findName"); // ID나 Name 저장해주는 변수
		
		/********************* 판별하는 기능 ***************************/
		if (selectType.equals("userID")) idxType = 1; // 아이디로 검색이면 1
		else if (selectType.equals("userName")) idxType = 2; // 이름으로 검색이면 2
		
		if (selectMonth.equals("wholeSelect")) idxMonth = 1; // 전체조회면 1
		else if (selectMonth.equals("month2")) idxMonth = 2; // 2월~3월이면 2
		else if (selectMonth.equals("month3")) idxMonth = 3; // 3월~4월이면 3
		else if (selectMonth.equals("month4")) idxMonth = 4; // 4월~5월이면 4
		/************************************************************/
		
		if (checkID == 0) { // 일반 계정일 때
			
			/**************** 학생 이름을 숫자로 변경 ( 출석부를 불러오기 위해 ) ****************/
			String[] stuNameLists = {"고준혁","곽현석","권상욱","김범필","김태욱","복승현","성지훈","신수경",
					"우주희","유동희","윤종민","임은수","장진열","정주현","정진호","조수민","조창우","홍인기"};
			
			int checkName = -1;
			
			for (int i = 0; i < stuNameLists.length; i++) {
				if (stuNameLists[i].equals(sessionName)) 
					checkName = i;
			}
			// 배열 인덱스는 0부터 시작, 학생 테이블에서 번호는 1부터 시작하기 때문에 1을 더해줘야함
			if (checkName > -1)
				checkName++;
			/**********************************************************************/
			
			if (idxType == 1) { // 아이디로 검색했을 떄
				
				if (findName.equals("root")) {
					
					System.out.println("관리자 아이디로 검색");
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('관리자 아이디로는 검색이 불가능합니다')");
					script.println("history.back()");
					script.println("</script>");
					
				} else {
					
					if (sessionID.equals(findName)) {
						
						attendanceLists = 
								dao.Attendance_Lists(idxMonth, checkID, checkName);
						
						request.setAttribute("attendanceLists", attendanceLists);
						
						/********* 출석부 계산 jsp로 보내는 부분 ***********/
						cal_Attendance_Rate(idxMonth, attendanceLists, request, response);
						/*******************************************/
						
						request.setAttribute("url", "/board.jsp");
						
					} else {
						
						System.out.println("본인 아이디랑 다름");
						
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('본인 아이디와 다릅니다')");
						script.println("history.back()");
						script.println("</script>");
						
					}
					
				}
				
			} else if (idxType == 2) { // 이름으로 검색했을 떄
				
				if (sessionName.equals(findName)) {
					
					attendanceLists = 
							dao.Attendance_Lists(idxMonth, checkID, checkName);
					
					request.setAttribute("attendanceLists", attendanceLists);
					
					/********* 출석부 계산 jsp로 보내는 부분 ***********/
					cal_Attendance_Rate(idxMonth, attendanceLists, request, response);
					/*******************************************/
					
					request.setAttribute("url", "/board.jsp");
					
				} else {
					
					System.out.println("본인 이름이랑 다름");
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('본인 이름과 다릅니다')");
					script.println("history.back()");
					script.println("</script>");
					
				}
				
			} else {
				System.out.println("출석부조회 에러");
			}
			
		} else { // 관리자 계정일 떄
			
			if (idxType == 1) { // 아이디로 검색했을 때
				
				if (findName.equals("root")) {
					
					System.out.println("관리자 아이디로 검색");
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('관리자 아이디로는 검색이 불가능합니다')");
					script.println("history.back()");
					script.println("</script>");
					
				} else {
					
					int checkName = dao.userID_To_userName(findName);
					
					if (checkName != 100) {
						
						attendanceLists = 
								dao.Attendance_Lists(idxMonth, checkID, checkName);
						
						request.setAttribute("attendanceLists", attendanceLists);
						
						/********* 출석부 계산 jsp로 보내는 부분 ***********/
						cal_Attendance_Rate(idxMonth, attendanceLists, request, response);
						/*******************************************/
						
						request.setAttribute("url", "/board.jsp");
						
					} else {
						
						System.out.println("학생 아이디 없음");
						
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('입력하신 학생 아이디와 일치하는 아이디가 없습니다')");
						script.println("history.back()");
						script.println("</script>");
						
					}
					
				}
				
			} else if (idxType == 2) { // 이름으로 검색했을 때

				int checkName = dao.userNameCheck(findName);
				
				if (checkName != 100) {
					
					attendanceLists = 
							dao.Attendance_Lists(idxMonth, checkID, checkName);
					
					request.setAttribute("attendanceLists", attendanceLists);
					
					/********* 출석부 계산 jsp로 보내는 부분 ***********/
					cal_Attendance_Rate(idxMonth, attendanceLists, request, response);
					/*******************************************/
					
					request.setAttribute("url", "/board.jsp");
					
				} else {
					
					System.out.println("학생 이름이 없음");
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('입력하신 학생 이름과 일치하는 이름이 없습니다')");
					script.println("history.back()");
					script.println("</script>");
					
					
				}
				
			}
			
		}
		
	}
	
	public String cal_Attendance_Rate (int idxMonth, ArrayList<MemberDTO_forAttendance> attendanceLists,
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		String rs = "";
		int attendanceCount = 0; // 출석 수 세주는 변수
		int otherCount = 0; // 조퇴, 외출 이런거 세주는 변수 (3번 되면 0으로 초기화됨)
		int otherCount2 = 0; // 조퇴, 외출 이런거 세주는 변수 (누적으로 세줌)
		int absenceCount = 0; // 결석 수 세주는 변수
		int wholeAttendance = 0; // 전체 수업일 수
		
		if (idxMonth == 1) { // 전체조회
			
			System.out.println("전체조회 계산");
			rs = "전체조회는 출석에 대한 계산이 적용되지 않습니다";
			request.setAttribute("attendanceRate", rs);
			
		} else if (idxMonth == 2) { // 2월 19일 ~ 3월 18일
			
			wholeAttendance = 19;
			
			/****************** 출석, 조퇴, 결석 수 세는 부분 *****************/
			for (MemberDTO_forAttendance dto : attendanceLists) {
				
				if (dto.getChkNum() == 0) { // 출석이면
					
					attendanceCount ++;
					
				} else if (dto.getChkNum() == 1) { // 조퇴, 외출 이런거면
					
					otherCount ++;
					otherCount2 ++;
					if (otherCount == 3) { // 조퇴, 외출... 등등 3번이면 결석 1번
						otherCount = 0;
						absenceCount ++;
					}
					
				} else { // 결석이면
				
					absenceCount ++;
					
				}
				
			}
			/********************************************************/
			
			float attendanceRate = (float) attendanceCount / (float) wholeAttendance * 100;
			rs = attendanceRate + "";
			
			/***************** 출석부 계산한거 jsp로 보내는 부분 ****************/
			//출석률
			request.setAttribute("attendanceRate", rs);
			//전체수업일수
			request.setAttribute("wholeAttendance", wholeAttendance + "");
			// 출석 수 세주는 변수
			request.setAttribute("attendanceCount", attendanceCount + "");
			//조퇴, 외출 이런거 세주는 변수 (누적으로 세줌)
			request.setAttribute("otherCount2", otherCount2 + "");
			// 결석 수 세주는 변수
			request.setAttribute("absenceCount", absenceCount + "");
			/***********************************************************/
			
			
		} else if (idxMonth == 3) { // 3월 19일 ~ 4월 18일
			
			wholeAttendance = 23;
			
			/****************** 출석, 조퇴, 결석 수 세는 부분 *****************/
			for (MemberDTO_forAttendance dto : attendanceLists) {
				
				if (dto.getChkNum() == 0) { // 출석이면
					
					attendanceCount ++;
					
				} else if (dto.getChkNum() == 1) { // 조퇴, 외출 이런거면
					
					otherCount ++;
					otherCount2 ++;
					if (otherCount == 3) { // 조퇴, 외출... 등등 3번이면 결석 1번
						otherCount = 0;
						absenceCount ++;
					}
					
				} else { // 결석이면
				
					absenceCount ++;
					
				}
				
			}
			/********************************************************/
			
			float attendanceRate = (float) attendanceCount / (float) wholeAttendance * 100;
			rs = attendanceRate + "";
			
			/***************** 출석부 계산한거 jsp로 보내는 부분 ****************/
			request.setAttribute("attendanceRate", rs);
			request.setAttribute("wholeAttendance", wholeAttendance + "");
			request.setAttribute("attendanceCount", attendanceCount + "");
			request.setAttribute("otherCount2", otherCount2 + "");
			request.setAttribute("absenceCount", absenceCount + "");
			/***********************************************************/
			
		} else if (idxMonth == 4) { // 4월 19일 ~ 5월 9일
			
			System.out.println("4월 19일 ~ 5월 9일 계산");
			rs = "데이터 부족으로 계산이 적용되지 않습니다";
			request.setAttribute("attendanceRate", rs);
			
		}
		
		return rs;
		
	}

}
