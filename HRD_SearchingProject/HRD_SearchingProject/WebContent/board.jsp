<%@page import="MemberDTO.MemberDTO_forAttendance"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
 <link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>출석조회</title>

<%
	ArrayList<MemberDTO_forAttendance> attendanceLists = 
		(ArrayList<MemberDTO_forAttendance>) request.getAttribute("attendanceLists");
	
	String wholeAttendance = (String) request.getAttribute("wholeAttendance");
	String attendanceCount = (String) request.getAttribute("attendanceCount");
	String otherCount2 = (String) request.getAttribute("otherCount2");
	String absenceCount = (String) request.getAttribute("absenceCount");
	System.out.println(wholeAttendance);
	
	String attendanceRate = (String) request.getAttribute("attendanceRate");
	System.out.println(attendanceRate);
%>

<script>

	function find() {
		
		var form = document.attendanceForm;
		
		if (form.findName.value == null || form.findName.value == "") {
			alert("아이디 및 이름을 입력해주세요");
			form.findName.focus();
			return false;
		} else {
			form.submit();
		}
		
	}

</script>

</head>
<body>

<div class="container-fluid" > 
<img class='img-responsive' src="https://upload.wikimedia.org/wikipedia/commons/3/39/Cloud_banner.jpg">  
  <nav id="grey"> 
   
    <%@ include file = "header.jsp" %>
</nav>

   <div class="view_wrap">
   <div class="search_wrap">
    
    	<form action = "MemberAttendance.do" method = "POST" name = "attendanceForm">

			<select name = "selectType">
				<option value = "userName">이름</option>
				<option value = "userID">아이디</option>
			</select>
			
			<select name = "selectMonth">
				<option value = "wholeSelect">전체조회</option>
				<option value = "month2">2월19일 ~ 3월 18일</option>
				<option value = "month3">3월19일 ~ 4월 18일</option>
				<option value = "month4">4월19일 ~ 5월 18일</option>
			</select>    
			
			<input type = "text" name = "findName">
			<input type = "button" onclick = "find()" value = "검색">				
    	
    	</form>
    
    </div>
        
    <!-- 출석률 적을 자리 -->

    <div class="data_wrap">
    	
		<div class="data_view">

			<%
				if (attendanceRate != null) {
					if (wholeAttendance != null) {
					%>
					<p class="left_txt">총 수업일 수 : <span> <%=wholeAttendance %> 일</span></p>
					<p class="left_txt">출석일 수 : <span> <%=attendanceCount %> 일</span></p>
					<p class="left_txt">조퇴, 외출 등등 : <span> <%=otherCount2 %> 일</span></p>
					<p class="left_txt">결석일 수 : <span> <%=absenceCount %> 일</span></p>
					<p class="left_txt">출석률 : <span> <%=attendanceRate %></span></p> 
					<%
				} else if (wholeAttendance == null || wholeAttendance == ""){
					%>					
				
					<%=attendanceRate %> <br>
					<%
					}
				}
			%>

		</div>

		<div class="data_view2">
			<%
            if (attendanceRate != null) {
               if (wholeAttendance != null) {
            if(Double.parseDouble(attendanceRate)/100 <0.80){      
            
            %>
                  	출석일수가 부족해 훈련수당을 받을 수 없습니다.<br>
            <%
               }else if(Double.parseDouble(attendanceRate)/100 >=0.80){
            %>
               <% 
               if(Integer.parseInt(attendanceCount)<=20){
               %>
               	 <p class="left_txt">훈련 수당 :<span><%=Integer.parseInt(attendanceCount)*14800 %>원</span></p>
                 	
                 <p class="left_txt">식비&교통비 :<span><%=Integer.parseInt(attendanceCount)*5800 %>원</span></p>
                 	
                 <p class="left_txt">총 훈련수당 :<span><%=Integer.parseInt(attendanceCount)*20600%>원</span></p>
            <%
               }else if(Integer.parseInt(attendanceCount)>20){
                  %>
                      <p class="left_txt"> 한달 최대 훈련수당은 296,000 원 입니다.</p>
          	           <p class="left_txt">훈련 수당      = 296,000 원</p>
                         <p class="left_txt">식비&교통비 = 114,000 원 </p>
              			 <p class="left_txt">총 훈련수당  = 400,000 원  </p>
           		 <% 
                  }
                  }
               }
            }
            %>  
		</div>
    </div>
    </div> 
    
    
		
    <div class="table_wrap">
    	
    	
    	<!-- 출석부 목록 -->
    	<!-- 타이틀이미지 -->
    	 <h1><img src ="image/tit_board1.png"> </h1>
    	<table style = "border-collapse:collapse" >
    		<tr>
    			<th style="text-align: center;"> 수업날짜 </th>
    			<th style="text-align: center;"> 출결여부 </th>
    			<th style="text-align: center;"> 입실시간 </th>
    			<th style="text-align: center;"> 퇴실시간 </th>
    		</tr>
    		
    		<%
    			if (attendanceLists != null) {
    				for (MemberDTO_forAttendance dto : attendanceLists) {
    		%>
    		
    		<tr>
    			<td> <%=dto.getDate() %> </td>
    			<td> <%=dto.getChk() %> </td>
    			<td> <%=dto.getStartTime() %> </td>
    			<td> <%=dto.getEndTime() %> </td>
    		</tr>
    		
    		<%
    				}
    					}
    		%>
    	</table>
    	
    </div>

    
   <div>
  <%@ include file = "footer.jsp" %>	
  </div>

</body>
</html>