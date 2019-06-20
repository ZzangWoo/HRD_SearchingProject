<!DOCTYPE html>
<html lang="en" >
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
 
  <title>Home Page</title>
  
 <!-- 필수 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>
</div>
 <!-- 필수 -->



  
  <div  class="main_list">
  <h2><img src ="image/system_step.png"></h2>
  <ul>
   <li><img src ="image/login_box.png"></li>
   <li><img src ="image/click_box.png"> </li>
   <li><img src ="image/seach_box.png"> </li>
   <li><img src ="image/view_box.png"> </li>
  </ul></div>

  
  
  
  <!-- 필수 -->
  <div>
  <%@ include file = "footer.jsp" %>	
  </div>
   <!-- 필수 -->
  

  


</body>

</html>
