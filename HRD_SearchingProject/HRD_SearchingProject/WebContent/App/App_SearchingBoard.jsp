<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");
	
	String content = request.getParameter("content");

	String rs = dao.App_SearchingBoard(content); // 그냥 원래 있던 update 메소드 사용하면 될듯
	
	out.print(rs);
	
%>