<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	

	String userID = request.getParameter("userID");
	
	String rs = dao.App_SearchingNameEmail(userID);
	
	out.print(rs);
	
%>