<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/style.css">


<%
	String valUserID = "";
	String valUserPW = "";
	
	if (request.getParameter("userID") != null) {
		valUserID = request.getParameter("userID");
	}
%>

<script>

	function deleteCheck() {
		
		console.log("들어왔다");
		
		var deleteForm = document.deleteForm;
		// 텍스트 박스에 입력된 ID, PW 변수에 저장
		var NewUserID = deleteForm.userID.value;
		var NewUserPW = deleteForm.userID.value;
		// 로그인 한 ID, PW 변수에 저장
		
		
		console.log(NewUserID);
		
		if (NewUserID == null || NewUserID == "" || NewUserPW == null || NewUserPW == "") {
			alert("아이디와 비밀번호를 모두 입력해주세요");
			NewUserID = "";
			NewUserPW = "";
			deleteForm.userID.focus();
			return false;
		} else {
			deleteForm.submit();
		}
		
	}

</script>

</head>
<body>

	<div class="container-fluid">
		<img class='img-responsive'
			src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">
		<nav id="grey">

			<%@ include file="header.jsp"%>
		</nav>


<h1><img src ="image/memberdelete.png"> </h1>
		<div style="width: 100%; height: 80%;">

			<form action="MemberDelete.do" method="post" name="deleteForm"
				id="delete">
				<div class="info_wrap">
					<p class="edit_info"><%=session.getAttribute("sessionName") %>님
						회원탈퇴를 원하시면 등록하신 아이디와 비밀번호를 입력해주세요.
					</p>
					<table>
						<tbody>
							<tr>
								<th scope="row">아이디</th>
								<td><input type="text" name="userID" id="userID" value="<%=valUserID%>"></td>
							</tr>
							<tr>
								<th scope="row">비밀번호</th>
								<td><input type="password" name="userPW" id="userPW" value="<%=valUserPW%>"></td>
							</tr>

						</tbody>
					</table>
					<input type="button" onclick="deleteCheck()" value="회원탈퇴" class="edit_btn">

				</div>

			</form>
		</div>

		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>