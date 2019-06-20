
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
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	PrintWriter sc = response.getWriter();

	
	String userId = null;
	if(session.getAttribute("sessionID") != null)
	{ userId = (String) session.getAttribute("sessionID"); }
	
	if(userId == null)
	{
		sc.println("<script> alert('로그인 요구') </script>");
		sc.println("<script> location.href = 'MemberLogin.jsp' </script>");
	}
	
	
	int bbsId = 0;
	if(request.getParameter("bbsId") != null)
	{ bbsId = Integer.parseInt(request.getParameter("bbsId")); }
	
	if(bbsId == 0)
	{
		PrintWriter pwr = response.getWriter();
		pwr.println("<script> alert('유효하지 않은 글') </script>");
		pwr.println("<script> location.href = 'BbsMain.jsp' </script>");
	}
	
	bbsDTO dto = new bbsDAO().getBbs(bbsId);
	
	 if(userId != null && userId.equals("root"))
	{
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
		
		bbsDAO dao = new bbsDAO();
		int result = dao.delete(bbsId); // 글 삭제 실행
		
		System.out.println("root.delete / result 값 : " + result);
		
	}			
	
	// 글 작성자인지 확인
//	if( !userId.equals(dto.getUserId()) )		
	if( !userId.equals(dto.getUserId()) )
	{		
		sc.println("<script> alert('권한 필요') </script>");
		sc.println("<script> location.href = 'BbsMain.jsp' </script>");
	}		
	else
	{
		bbsDAO dao = new bbsDAO();
		int result = dao.delete(bbsId); // 글 삭제 실행
		
		System.out.println("dao.delete 탈출 / result 값 : " + result);
			
		// DB 오류
		if(result == -1)
		{	
			sc.println("<script> alert('글 삭제 실패') </script>");
			sc.println("<script> history.back() </script>");
		}
		else // 글 수정 성공
		{
			sc.println("<script> location.href='BbsMain.jsp' </script>");
		}
				
	}

%>



</body>
</html>