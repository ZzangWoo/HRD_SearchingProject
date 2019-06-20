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

	if(userId == null)
	{ 
%>
			
			
	<ul class="dropdown-menu">
		<li><a href="login.jsp">로그인</a></li>
		<li><a href="join.jsp">회원가입</a></li>
	</ul>
    <% System.out.println("write 1 : userId == null"); %>
	
<%
	} 
	else 
	{
%>
	<ul class="dropdown-menu">
		<li><a href="logoutAction.jsp">로그아웃</a></li>
	</ul>
	<% System.out.println("write 2 : userId == null"); %>
<%
	}
%>		
<h1><img src ="image/bbs.png"> </h1>
<div class="container">
	<div class="row">	
	<form action="BbsWriteAction.jsp" method="post">
		<table class="table table-striped" style="text-align: center; border 1px solid #dddddd;">
			<thead>
				<tr> <th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th> </tr>
			</thead>
			
			<tbody>
				<tr><td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"></td></tr>
				<tr><td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;"></textarea></td></tr>	
			</tbody>		
		</table>
		<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
	</form>	
	</div>
</div>


<div>
  <%@ include file = "footer.jsp" %>	
  </div>

</body>
</html>