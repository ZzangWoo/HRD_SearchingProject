<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정 창</title>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/style.css">
<script>

	function update() {
		
		var updateForm = document.updateForm;
		var userPW = updateForm.userPW.value;
		var userName = updateForm.userName.value;
		var userEmail = updateForm.userEmail.value;
		
		if (userPW == null || userPW == "") {
			alert("비밀번호를 입력해주세요");
			updateForm.userPW.focus();
			return false
		}
		
		if (userName == null || userName == "") {
			alert("이름을 입력해주세요");
			updateForm.userName.focus();
			return false
		}
		
		if (userEmail == null || userEmail == "") {
			alert("이메일을 입력해주세요");
			updateForm.userEmail.focus();
			return false
		}
		
		updateForm.submit();
		
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

		<div style="width: 100%; height: 80%">
			<h1><img src ="image/memberedit.png"> </h1>
			<form action="MemberUpdate.do" method="post" name="updateForm"
				id="update">
				<div class="info_wrap">
				<p class="edit_info"><%=session.getAttribute("sessionName")%>님의 회원 개인 정보를 수정 하세요.</p>
<table>
                            <tbody>
                            <tr>
                                <th scope="row">아이디</th>
                                <td><%=session.getAttribute("sessionID") %></td>
                            </tr>
                            <tr>
                                <th scope="row">비밀번호</th>
                                <td><input type="password" name="userPW" id="userPW"
					value=""></td>
                            </tr>
                            <tr>
                                <th scope="row">이름</th>
                                <td><input type="text" name="userName"
					value=<%=session.getAttribute("sessionName") %>></td>
                                </tr>
                                <tr>
                                <th scope="row">이메일</th>
                                <td><input type="text" name="userEmail"
					value=<%=session.getAttribute("sessionEmail") %>></td>
                                </tr>
                        </tbody>
                        </table>
                        <input type="button" onclick="update()" value="회원수정" class="edit_btn">

</div>

			</form>
		</div>

		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>