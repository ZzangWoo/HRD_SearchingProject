<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>내정보 보기</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>
</div>

<h1><img src ="image/myinfo.png"> </h1>
<div class="info_wrap">
<table>
                            <tbody>
                            <tr>
                                <th scope="row">아이디</th>
                                <td><%=session.getAttribute("sessionID") %></td>
                            </tr>
                            <tr>
                                <th scope="row">비밀번호</th>
                                <td><%=session.getAttribute("sessionPW") %></td>
                            </tr>
                            <tr>
                                <th scope="row">이름</th>
                                <td><%=session.getAttribute("sessionName") %></td>
                                </tr>
                                <tr>
                                <th scope="row">이메일</th>
                                <td><%=session.getAttribute("sessionEmail") %></td>
                                </tr>
                        </tbody>
                        </table>


	<!--  <p><span>아이디</span><span> <%=session.getAttribute("sessionID") %></span> </p>
	<p><span>비밀번호 </span><span> <%=session.getAttribute("sessionPW") %> </span> </p>
	<p><span>이름 </span><span> <%=session.getAttribute("sessionName") %> </span> </p>
	<p><span>이메일 </span><span> <%=session.getAttribute("sessionEmail") %> </span> </p>
	-->
</div>


 <div>
  <%@ include file = "footer.jsp" %>	
  </div>	
</body>
</html>