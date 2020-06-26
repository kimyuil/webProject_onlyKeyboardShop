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
	
	if("${result}"=="fail"){
		alert("수정에 실패했습니다. 다시 시도해주세요")
		location.href="/onlyKeyboardShop/home";
		return
	}
	else{
		setTimeout("f()", 3000);
	}
});
function f(){location.href='/onlyKeyboardShop/home'}


</script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<div style="width:400px; margin:0 auto; " >
<h2 align="center">회원정보 수정</h2>
<br><br>
<h4 align="center"><b>정상적으로 처리되었습니다</b></h4>
<h4 align="center"><i>다시 로그인</i>해주시기 바랍니다</h4>
<p align="center"><b>3초뒤</b> 자동으로 main이동</p>
<button class="btn btn-primary btn-block" onclick="location.href='/onlyKeyboardShop/home'">메인으로</button>
</div>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>