<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="container-fluid">
		<img class='img-responsive'
			src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">
		<nav id="grey">

			<%@ include file="header.jsp"%>
		</nav>




		<div>
			<h1>
				<img src="image/login.png">
			</h1>
			<form action="MemberLogin.do" method="post" id="login">
				<div class="info_wrap">
					<table>
						<tbody>
							<tr>
								<th scope="row">아이디</th>
								<td><input type="text" name="userID" id="userID"
									placeholder="아이디"></td>
							</tr>
							<tr>
								<th scope="row">비밀번호</th>
								<td><input type="password" name="userPW" id="userPW"
									placeholder="비밀번호"></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="로그인" class="edit_btn">

				</div>
			</form>
		</div>



		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>