<%@page import="BBS.bbsDAO"%>
<%@page import="BBS.bbsDTO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 인코딩
    //request.setCharacterEncoding("euc-kr");
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 필수 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>


<%
	PrintWriter sc = response.getWriter();

	String userId = null;
	if(session.getAttribute("sessionID") != null)
	{ userId = (String) session.getAttribute("sessionID"); }
	
	// 로그인 상태 확인
	if(userId == null)
	{
		sc.println("<script> alert('로그인 요구') </script>");
		sc.println("<script> location.href = 'Memberlogin.jsp' </script>");
	}

	int bbsId = 0;
	if (request.getParameter("bbsId") != null)
	{ bbsId = Integer.parseInt(request.getParameter("bbsId").trim()); }
	// 왜 bbsId 값을 int 가 아니라 String 으로 받아오지?
	
	
	if(bbsId == 0)
	{
		sc.println("<script> alert('유효하지 않은 글') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}
	
 	bbsDTO dto = new bbsDAO().getBbs(bbsId);
	// 글 작성자인지 확인
/* 	if( !userId.equals(dto.getUserId()) )
	{
		sc.println("<script> alert('권한 필요') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}  */
%>



<div class="container">
	<div class="row">	
	<form action="BbsUpdateAction.jsp?bbsId=<%= bbsId %>" method="post">
		<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
			<thead>
				<tr> <th colspan="2" style="background-color: #eeeeee; text-align: center;">글 수정 양식</th> </tr>
			</thead>
			
			<tbody>
				<tr><td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value="<%= dto.getBbsTitle() /* 수정 전의 글 제목 확인 */ %>"></td></tr>
				<tr><td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;" <%= dto.getBbsContent() /* 수정 전의 글 내용 확인 */ %> ></textarea></td></tr>	
			</tbody>		
		</table>
		<input type="submit" class="btn btn-primary pull-right" value="글 수정">
	</form>	
	</div>
</div>

<div>
  <%@ include file = "footer.jsp" %>	
  </div>

</body>
</html>