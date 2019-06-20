<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	

	String userName = request.getParameter("userName");
	String idxMonth = request.getParameter("idxMonth");
	
	/**************** 학생 이름을 숫자로 변경 ( 출석부를 불러오기 위해 ) ****************/
	String[] stuNameLists = {"고준혁","곽현석","권상욱","김범필","김태욱","복승현","성지훈","신수경",
			"우주희","유동희","윤종민","임은수","장진열","정주현","정진호","조수민","조창우","홍인기"};
	
	int checkName = -1;
	
	for (int i = 0; i < stuNameLists.length; i++) {
		if (stuNameLists[i].equals(userName)) 
			checkName = i;
	}
	// 배열 인덱스는 0부터 시작, 학생 테이블에서 번호는 1부터 시작하기 때문에 1을 더해줘야함
	if (checkName > -1)
		checkName++;
	/**********************************************************************/
	
	String rs = dao.App_StudentAttendance(checkName, idxMonth);
	
	out.print(rs);
	
%>