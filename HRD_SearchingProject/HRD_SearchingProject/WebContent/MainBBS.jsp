<%@page import="MemberDTO.MemberBBS_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">

  
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>



<%	
	ArrayList<MemberBBS_DTO> bbsLists = (ArrayList<MemberBBS_DTO>) request.getAttribute("bbsLists");
	
	String wholePageNum = String.valueOf(request.getAttribute("wholePageNum"));
	int wholePageNumber = Integer.parseInt(wholePageNum);
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
			
				for(MemberBBS_DTO dto : bbsLists) {
					
			%>
				<tr>
					<td><%= dto.getBbsID() %></td>
					<td> <a href="BBSView.do?bbsID=<%= dto.getBbsID() %>">
						<%= dto.getBbsTitle() %></a></td>
					<td><%= dto.getUserID() %></td>
					<td><%= dto.getBbsDate() %></td>
				</tr>
			<%
				}
			%>		
				
			</tbody>	
		</table>	
		
		<div style="width:100%;	text-align:center">
				 <!-- 페이지 보이는 곳 --> 
				 <%
				 	for (int i = 1; i <= wholePageNumber; i++) {
				 %>
				 		<a href = "BBSMain.do?pageNum=<%= i%>"> [<%= i %>] </a>
				 <%
				 	}
				 %>
		
		
		</div>
				<a href="WriteBBS.jsp" class="btn btn-primary pull-right">글쓰기</a>
						
		
			
			
	</div>
</div>

  <div>
  <%@ include file = "footer.jsp" %>	
  </div>
</body>
</html>