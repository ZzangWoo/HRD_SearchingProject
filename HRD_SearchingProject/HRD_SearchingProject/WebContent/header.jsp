<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<%
		if (session.getAttribute("sessionID") == null) { // 로그인 안된 상태의 헤더
			%>
			<a href = "index.jsp" class="bg_blue">  홈   </a>
			<a href = "MemberLogin.jsp" class="bg_blue"> 로그인  </a>
			<a href = "MemberJoin.jsp" class="bg_blue"> 회원가입  </a>
			<%
		} else {
			if (session.getAttribute("sessionID").equals("root")) {
				%>
			<a href = "index_login.jsp" class="bg_blue"> 홈   </a>
			<a href = "MemberLogout.do" class="bg_blue"> 로그아웃  </a>
			<a href = "board.jsp" class="bg_blue">출석조회  </a>
			<a href = "ConfirmLogin.jsp" class="bg_blue">내정보 </a>
			<a href = "ToGoUpdate.jsp" class="bg_blue"> 회원 정보 수정 </a>
			<a href = "MemberDelete.jsp" class="bg_blue"> 회원 탈퇴 </a> 
			<a href = "MemberList.do" class="bg_blue"> 회원 조회 </a>
			<a href = "BBSMain.do" class = "bg_blue"> 게시판 </a>
			<!-- <a href = "BbsMain.jsp" class="bg_blue">게시판</a>  -->			
			<div class="user_name"><%=session.getAttribute("sessionName") %>(관리자)님 로그인 중</div>
				<%
			} else {
				%>
			<a href = "index_login.jsp" class="bg_blue">홈 </a>
			<a href = "MemberLogout.do" class="bg_blue">로그아웃</a>
			<a href = "board.jsp" class="bg_blue">출석조회 </a>
			<a href = "ConfirmLogin.jsp" class="bg_blue">내정보</a>
			<a href = "ToGoUpdate.jsp" class="bg_blue"> 회원 정보 수정 </a>
			<a href = "MemberDelete.jsp" class="bg_blue">회원 탈퇴</a>
			<a href = "BBSMain.do" class = "bg_blue"> 게시판 </a>
			<!-- <a href = "BbsMain.jsp" class="bg_blue">게시판</a>  -->		
			<div class="user_name"><%=session.getAttribute("sessionName") %>님 로그인 중</div>
			<%
			}
		
		}
	%>
  
</div>
<!-- 타이틀이미지 -->
  <h1 class="big_tit"><img src ="image/title_index.png"> </h1>
  
  <hr class="hr">