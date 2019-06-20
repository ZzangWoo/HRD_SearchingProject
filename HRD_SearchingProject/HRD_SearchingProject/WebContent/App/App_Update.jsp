<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	

	String userID = request.getParameter("userID");
	String userPW = request.getParameter("userPW");
	String userName = request.getParameter("userName");
	String userEmail = request.getParameter("userEmail");
	
	int rs = dao.update(userID, userPW, userName, userEmail); // 그냥 원래 있던 update 메소드 사용하면 될듯
	
	out.print(rs);
	
%>