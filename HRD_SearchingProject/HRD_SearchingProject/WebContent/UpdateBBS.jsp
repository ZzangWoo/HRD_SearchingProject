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
<!-- 필수 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">
<meta charset="EUC-KR">
<title>게시판 수정</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>


<%
	MemberBBS_DTO dto = (MemberBBS_DTO) request.getAttribute("preUpdateDTO");
%>

<script>

	function updateOK() {
		
		var updateForm = document.updateForm;
		
		if (updateForm.bbsTitle.value == null || updateForm.bbsTitle.value == "") {
			alert("제목을 입력해주세요");
			updateForm.bbsTitle.focus();
			return false;
		}
		
		if (updateForm.bbsContent.value == null || updateForm.bbsContent.value == "") {
			alert("내용을 입력해주세요");
			updateForm.bbsContent.focus();
			return false;
		}
		
		updateForm.submit();
		
	}

</script>

<div class="container">
	<div class="row">	
	<form action="BBSUpdate.do?bbsID=<%=dto.getBbsID()%>&updateWay=2" method="post" name = "updateForm">
		<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
			<thead>
				<tr> <th colspan="2" style="background-color: #eeeeee; text-align: center;">글 수정 양식</th> </tr>
			</thead>
			
			<tbody>
				<tr><td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value=<%= dto.getBbsTitle() /* 수정 전의 글 제목 확인 */ %>></td></tr>
				<tr><td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" 
					style="height: 350px" > <%= dto.getBbsContent() /* 수정 전의 글 내용 확인 */ %> </textarea></td></tr>	
			</tbody>		
		</table>
		<input type="button" onclick = "updateOK()" class="btn btn-primary pull-right" value="글 수정">
	</form>	
	</div>
</div>

<div>
  <%@ include file = "footer.jsp" %>	
  </div>

</body>
</html>