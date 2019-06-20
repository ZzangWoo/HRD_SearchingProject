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

</head>
<body>
	<div class="container-fluid">
		<img class='img-responsive'
			src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">
		<nav id="grey">

			<%@ include file="header.jsp"%>
		</nav>




<h1><img src ="image/memberedit.png"> </h1>

		<div style="width: 100%; height: 80%">
		<p class="edit_info"><%=session.getAttribute("sessionName")%>님의 회원 정보를 수정 할 수 있습니다. 비밀번호를 입력 해주세요.</p>
			<form action="CheckPW.do" method="post" id="join">
			<div class="info_wrap">
<table>
                            <tbody>
                            <tr>
                                <th scope="row">아이디</th>
                                <td><%=session.getAttribute("sessionID")%></td>
                            </tr>
                            <tr>
                                <th scope="row">비밀번호</th>
                                <td><input type="password" name="userPW" id="userPW"></td>
                            </tr>

                        </tbody>
                        </table>
                        <input type="submit" value="수정" class="edit_btn"> 

</div>
			</form>
		</div>





		<div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>