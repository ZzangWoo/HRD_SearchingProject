<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String userID = request.getParameter("userID");

	int rs = dao.App_BoardWrite(title, content, userID); 
	
	out.print(rs);
	
%>