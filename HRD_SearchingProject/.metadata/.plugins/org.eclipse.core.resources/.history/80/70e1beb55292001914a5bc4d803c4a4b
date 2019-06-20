<%@page import="MemberDAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDAO dao = MemberDAO.getInstance();

	request.setCharacterEncoding("UTF-8");	
	
	String str_bbsID = request.getParameter("bbsID");
	int bbsID = Integer.parseInt(str_bbsID);
	String bbsTitle = request.getParameter("bbsTitle");
	String bbsContent = request.getParameter("bbsContent");

	int rs = dao.App_BoardModify(bbsID, bbsTitle, bbsContent); 
	
	out.print(rs);
	
%>