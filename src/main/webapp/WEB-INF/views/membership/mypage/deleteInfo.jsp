<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Insert title here</title>
<script>
$(document).ready(function(){
	var result = "${result}";
	if (result=="false")
		alert("비밀번호가 틀렸습니다");
	else if (result=="error")
		alert("error. 다시 시도해주세요");
	
});

</script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">회원탈퇴</h2>
<br><br>
<div style="width:300px; margin:0 auto; " >
<h4 align="center"><b>정말 탈퇴하시겠습니까?</b></h4>
<br><br>
<form method="post" name="deleteUser"  action="/onlyKeyboardShop/deleteUser"> 
  <div class="form-group">
    <label for="uPw">비밀번호 입력</label>
    <input type="password" class="form-control" id="uPw" name="uPw" placeholder="Enter Passwoard">
    <small class="form-text text-muted">비밀번호를 입력하세요.</small>
  </div>
  
  <input type="hidden" name="uId" value="${currentUserName}"/>
  <button type="submit" class="btn btn-danger btn-block">회원 탈퇴하기</button>
  <br>
</form>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout-form" action="${logoutUrl}" method="POST" style="display:none;"> 
	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
</form>

</div>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>