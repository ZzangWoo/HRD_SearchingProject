<%@page import="BBS.bbsDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 인코딩
    //request.setCharacterEncoding("euc-kr");
	request.setCharacterEncoding("utf-8");
%>


<jsp:useBean id="bbs" class="BBS.bbsDTO" scope="page"/>
<jsp:setProperty property="bbsTitle" name="bbs"/>
<jsp:setProperty property="bbsContent" name="bbs"/>


<!DOCTYPE html>
<html>
<head>
<!-- 필수 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>

<%

	PrintWriter sc = response.getWriter();
	
	String userId = null;
	if(session.getAttribute("sessionID") != null)
	{ userId = (String) session.getAttribute("sessionID"); }
	
	
	if(userId == null)
	{
		sc.println("<script> alert('로그인 요구') </script>");
		sc.println("<script> location.href = 'Memberlogin.jsp' </script>");
	}	
	else
	{
		//request.getParameter("bbsContent");
		
		// 글 제목, 내용 없을 경우
		if(bbs.getBbsTitle() == null || bbs.getBbsContent() == null)
		{		
			sc.println("<script> alert('글 제목 or 내용이 없음') </script>");
			sc.println("<script> history.back() </script>");
		}
		else
		{	
			bbsDAO dao = new bbsDAO();
			int result = dao.write( bbs.getBbsTitle(), userId, bbs.getBbsContent() ); /* 에러 */
			System.out.println("dao빠져나옴 / result 값 : " + result);
			
			// DB 오류
			if(result == -1)
			{	
				sc.println("<script> alert('글 생성 실패') </script>");
				sc.println("<script> history.back() </script>");
			}
			// 글쓰기 성공
			else
			{
				sc.println("<script> location.href='BbsMain.jsp' </script>");
			}
		}
	}

%>


  <div>
  <%@ include file = "footer.jsp" %>	
  </div>
</body>
</html>