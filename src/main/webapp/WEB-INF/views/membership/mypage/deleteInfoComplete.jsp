<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Insert title here</title>
<script>
$(document).ready(function(){
	
	setTimeout("logout()", 3000); 
});
function logout(){
	document.getElementById('logout-form').submit();
}

</script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<div style="width:300px; margin:0 auto; " >
<h2 align="center">회원탈퇴</h2>
<br><br>
<h4 align="center"><b>정상적으로 처리되었습니다</b></h4>
<h4 align="center">이용해주셔서 감사합니다</h4>
<p align="center"><b>3초뒤</b> 자동으로 main이동</p>
<button class="btn btn-primary btn-block" onclick="location.href='/onlyKeyboardShop/home'">메인으로</button>
</div>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout-form" action="${logoutUrl}" method="POST" style="display:none;"> 
	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
</form>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>