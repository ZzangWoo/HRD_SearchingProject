
<%@page import="BBS.bbsDAO"%>
<%@page import="BBS.bbsDTO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
    // 인코딩
    //request.setCharacterEncoding("euc-kr");
 	request.setCharacterEncoding("utf-8");
%>   


<!DOCTYPE html>
<html>
<head>
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
	
	int bbsId = 0;
	if (request.getParameter("bbsId") != null)
	{ bbsId = Integer.parseInt(request.getParameter("bbsId").trim()); }
	
	if(bbsId == 0)
	{
		sc.println("<script> alert('유효하지 않은 글') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}

	
	
	
	if( userId != null && userId.equals("root") )
	{
		bbsDAO dao = new bbsDAO();
		int result = dao.update( bbsId, request.getParameter("bbsTitle"), request.getParameter("bbsContent") );
		System.out.println("dao.update 탈출 / result 값 : " + result);
		
		if(result == -1)
		{	
			sc.println("<script> alert('글 수정 실패') </script>");
			sc.println("<script> history.back() </script>");
		}
		else
		{
			sc.println("<script> location.href='BbsMain.jsp' </script>");
		}
	}
	
	
	
	
	
	
	
	bbsDTO dto = new bbsDAO().getBbs(bbsId);
	// 글 작성자인지 확인
	if( !userId.equals(dto.getUserId()) )
	{
		
		sc.println("<script> alert('권한 필요') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}
	else
	{
		//request.getParameter("bbsContent");
		// 글 제목, 내용 없을 경우
		if
		(	// 조건절 : 너무 길어서 나눔.
			request.getParameter("bbsTitle") == null
			|| request.getParameter("bbsContent") == null
			|| request.getParameter("bbsTitle").equals("")
			|| request.getParameter("bbsContent").equals("") 			
		)
		//if(dto.getBbsTitle() == null || dto.getBbsContent() == null)
		{		
			sc.println("<script> alert('글 제목 or 내용이 없음') </script>");
			sc.println("<script> history.back() </script>");
		}
		else
		{	
			bbsDAO dao = new bbsDAO();
			int result = dao.update( bbsId, request.getParameter("bbsTitle"), request.getParameter("bbsContent") );
			System.out.println("dao.update 탈출 / result 값 : " + result);
			
			if(result == -1)
			{	
				sc.println("<script> alert('글 수정 실패') </script>");
				sc.println("<script> history.back() </script>");
			}
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