<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<sec:authorize access="isAnonymous()">
</sec:authorize>
 
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>


<script>

	function loginCheck(){
		if("${currentUserName}" == ""){
			alert("로그인이 필요합니다");
		}
	}
	
	function lookupOrder(){
		loginCheck();
	
		location.href='member/lookupOrder';
	}
	function myboard(){
		loginCheck();
		
		location.href='member/myboard';
	}
	function modifyInfo(){
		loginCheck();
		
		location.href='member/modifyInfo';
	}
	function deleteInfo(){
		loginCheck();
		
		location.href='member/deleteInfo';
	}
	
</script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">MY Page</h2>
<br><br>
<div style='width:500px;margin:0 auto;'>
<button class="btn btn-outline-primary btn-block" onclick="lookupOrder();">주문내역조회 </button><br>
<button class="btn btn-outline-primary btn-block" onclick="location.href='/onlyKeyboardShop/basket'; ">장바구니</button><br>
<button class="btn btn-outline-primary btn-block" onclick="myboard();">나의 게시글</button><br>
<button class="btn btn-outline-primary btn-block" onclick="modifyInfo();">회원정보 수정</button> <br>
<button class="btn btn-outline-primary btn-block" onclick="deleteInfo();">회원 탈퇴 </button> <br>
</div>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>