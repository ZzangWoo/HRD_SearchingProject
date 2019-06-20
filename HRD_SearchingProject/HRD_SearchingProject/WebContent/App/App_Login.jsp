<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	

	String userID = request.getParameter("userID");
	String userPW = request.getParameter("userPW");
	
	System.out.printf("아이디 : " + userID + " | 비밀번호 " + userPW);
	
	int rs = dao.App_Login(userID, userPW);
	
	out.print(rs);
	
%>