
<%@page import="BBS.bbsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BBS.bbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 인코딩
   // request.setCharacterEncoding("euc-kr");
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">

  
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>



<%	
	String userId = null;
	if(session.getAttribute("sessionID") != null)
	{ userId = (String) session.getAttribute("sessionID"); }
	
	int pageNumber = 1; // 1 페이지
	if(request.getParameter("pageNumber") != null)
	{	pageNumber = Integer.parseInt( request.getParameter("pageNumber") ); }
%>
<h1><img src ="image/bbs.png"> </h1>
<div class="container">
	<div class="row">
		<table class="table table-striped" style="text-align : center; border 1px solid #dddddd">
			<thead>
				<tr>
					<th style="background-color: #eeeeee; text-align: center;">번호</th>
					<th style="background-color: #eeeeee; text-align: center;">제목</th>
					<th style="background-color: #eeeeee; text-align: center;">작성자</th>
					<th style="background-color: #eeeeee; text-align: center;">작성일</th>
				</tr>
			</thead>
			<tbody>
			<%
				// 게시글을 가져올 수 있게
				bbsDAO dao = new bbsDAO();
			 	// 글 목록을 가져옴
				ArrayList<bbsDTO> lists = dao.getList(pageNumber);
				
				// 글 목록 출력
				for(int i = 0; i < lists.size(); i++)
				{			
					
			%>
				<tr>
					<td><%= lists.get(i).getBbsId() %></td>
					<td> <a href="BbsView.jsp?bbsId=<%= lists.get(i).getBbsId() %>"><%= lists.get(i).getBbsTitle() %></a></td>
					<td><%= lists.get(i).getUserId() %></td>
					<td><%= lists.get(i).getBbsDate() %></td>
				</tr>
			<%
				}
			%>			
			</tbody>	
		</table>	
		
			<!-- 11개 이상의 글이 생겼을 시, 페이지 이전/다음 보인다. -->
			<%
				if(pageNumber != 1)
				{
			%>
				<a href="BbsMain.jsp?pageNumber=<%=pageNumber -1 %>" class="btn btn-success btn-arrow-left">이전</a>
			<%
				}
				if(dao.nextPage(pageNumber+1))
				{	
			%>
				<a href="BbsMain.jsp?pageNumber=<%=pageNumber + 1 %>" class="btn btn-success btn-arrow-left">다음</a>
			<%
				}
			%>
			<a href="BbsWrite.jsp" class="btn btn-primary pull-rught">글쓰기</a>
			
	</div>
</div>

  <div>
  <%@ include file = "footer.jsp" %>	
  </div>
</body>
</html>