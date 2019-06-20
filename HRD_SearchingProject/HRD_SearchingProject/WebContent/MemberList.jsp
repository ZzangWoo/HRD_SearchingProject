<%@page import="MemberDTO.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<!-- 필수 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">

<!-- 테이블 리스트 저장한거  페이지에 출력하기 위해 ArrayList 변수에 담아주기 -->
<%
	ArrayList<MemberDTO> memberLists = (ArrayList<MemberDTO>) request.getAttribute("memberLists");
	%>

</head>

<body>


<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>
		<div style="width: 100%; height: 80%">
			
			<h1><img src ="image/memberlist.png"> </h1>
			 <div class="table_wrap">
			<table
				style="border-collapse: collapse">
				<tr>
					<th style="text-align: center;">아이디</th>
					<th style="text-align: center;">비밀번호</th>
					<th style="text-align: center;">이름</th>
					<th style="text-align: center;">이메일</th>
				</tr>

				<%
			for (MemberDTO dto : memberLists) {
		%>

				<tr>
					<td><%=dto.getUserID() %></td>
					<td><%=dto.getUserPW() %></td>
					<td><%=dto.getUserName() %></td>
					<td><%=dto.getUserEmail() %></td>
				</tr>

				<%
			}
		%>

			</table>
			</div>
		</div>

		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>