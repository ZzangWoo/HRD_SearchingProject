<%@page import="MemberDTO.MemberBBS_DTO"%>
<%@page import="BBS.bbsDAO"%>
<%@page import="BBS.bbsDTO"%>
<%@page import="java.io.PrintWriter"%>
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
<title>게시글 확인</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>

<%
	String userID = null;
	if(session.getAttribute("sessionID") != null) {
		userID = (String) session.getAttribute("sessionID"); 
	}
	
	int bbsID = 0;
	if(request.getParameter("bbsID") != null)
	{ bbsID = Integer.parseInt(request.getParameter("bbsID")); }
	
	if(bbsID == 0)
	{
		PrintWriter sc = response.getWriter();
		sc.println("<script> alert('유효하지 않은 글') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}
	
	MemberBBS_DTO dto = (MemberBBS_DTO) request.getAttribute("viewDTO");
	
%>
<h1><img src ="image/bbs.png"> </h1>
<div class="container">
	<div class="row">	

		<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
			<thead>
				<tr> <th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th> </tr>
			</thead>
			
			<tbody>
				<tr>
					<td style="whidth: 20%;">글 제목</td>
					<!-- 크로스 사이트 스크립팅 공격 방지 // 에러 출력으로 지움. 추가 요망 -->
					<td colspan="2"><%= dto.getBbsTitle() %></td>
				</tr>
				<tr>
					<td style="whidth: 20%;">작성자</td>
					<%-- <td colspan="2"><%= dto.getUserId() %></td> --%>
					<td colspan="2"><%= dto.getUserID() %></td>
				</tr>
				<tr>
					<td style="whidth: 20%;">작성일</td>
					<td colspan="2"><%= dto.getBbsDate() %></td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td colspan="2" style="min-height: 200px; text-align: left;">
					<!-- 글 내용에 특수문자 출력 가능하게 -->
					<%= dto.getBbsContent() %></td>
				</tr>
			</tbody>		
		</table>
		<!-- 목록으로 돌아감 -->
		<a href="BBSMain.do" class="btn btn-primary">목록</a>
<%
		/* 해당 글의 작성자라면, 글을 수정할 수 있는 부분이 출력 */
		if( userID != null && userID.equals(dto.getUserID()) )
		{
%>
	<a href="BBSUpdate.do?bbsID=<%=bbsID%>&updateWay=1" class="btn btn-primary">수정</a>
	<a onclick="return confirm('삭제 하시겠습니까?')" href="BBSDelete.do?bbsID=<%=bbsID%>" class="btn btn-primary">삭제</a>
<%
		}
%>

<!-- root 권한 -->
<%
		/* 해당 글의 작성자라면, 글을 수정할 수 있는 부분이 출력 */
		if( userID != null && userID.equals("root") )
		{
%>
			<a href="BBSUpdate.do?bbsID=<%=bbsID%>&updateWay=1" class="btn btn-primary">root수정</a>
			<a onclick="return confirm('삭제 하시겠습니까?')" href="BBSDelete.do?bbsID=<%=bbsID%>" class="btn btn-primary">root삭제</a>
<%
		}
%>




	</div>
</div>

 <div>
  <%@ include file = "footer.jsp" %>	
  </div>
</body>
</html>