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
<title>글쓰기</title>
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

%>

<script>

	function writeBBS() {
		
		var writeForm = document.writeForm;
		
		if (writeForm.bbsTitle.value == null || writeForm.bbsTitle.value == "") {
			alert("제목을 입력해주세요");
			writeForm.bbsTitle.focus();
			return false;
		}
		
		if (writeForm.bbsContent.value == null || writeForm.bbsContent.value == "") {
			alert("내용을 입력해주세요");
			writeForm.bbsContent.focus();
			return false;
		}
		
		writeForm.submit();
		
	}

</script>
	
<h1><img src ="image/bbs.png"> </h1>
<div class="container">
	<div class="row">	
	<form action="BBSWrite.do" method="post" name = "writeForm">
		<table class="table table-striped" style="text-align: center; border 1px solid #dddddd;">
			<thead>
				<tr> <th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th> </tr>
			</thead>
			
			<tbody>
				<tr><td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"></td></tr>
				<tr><td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;"></textarea></td></tr>	
			</tbody>		
		</table>
		<input type="button" onclick = "writeBBS()" class="btn btn-primary pull-right" value="글쓰기">
	</form>	
	</div>
</div>


<div>
  <%@ include file = "footer.jsp" %>	
  </div>

</body>
</html>