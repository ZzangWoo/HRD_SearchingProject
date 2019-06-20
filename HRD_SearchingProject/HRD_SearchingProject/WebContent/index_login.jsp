<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<title>Home Page</title>

<!-- 필수 -->
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



		<div class="main_list">
			<h2>
				<img src="image/system_step.png">
			</h2>
			<ul>
				<li><img src="image/login_box.png"></li>
				<li><img src="image/click_box.png"></li>
				<li><img src="image/seach_box.png"></li>
				<li><img src="image/view_box.png"></li>
			</ul>
		</div>

		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>