<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	System.out.println("여기는 idChecked.jsp");

	String rs = (String) request.getAttribute("resultAfterIDCheck");
	String userID = (String) request.getAttribute("userID");
	System.out.println(userID);
	String userName = (String) request.getAttribute("userName");
	String userEmail = (String) request.getAttribute("userEmail");
	String Checked = (String) request.getAttribute("Checked");
	
	if (rs.equals("1")) {
		%>
		<script>
			alert("중복아이디 입니다");
			location.href = "MemberJoin.jsp"
		</script>
		<%
	} else if (rs.equals("0")) {
		//request.setAttribute("userID", userID);
		//request.setAttribute("userName", userName);		
		//request.setAttribute("userEmail", userEmail);		
		//request.setAttribute("Checked", Checked);		
		%>
		<script>
			alert("사용할 수 있는 아이디입니다");
			location.href = "MemberJoin.jsp?userID=<%=userID%>&Checked=<%=Checked%>"
					+ "&userName=<%=userName%>&userEmail=<%=userEmail%>";
		</script>
		<%
	}

%>