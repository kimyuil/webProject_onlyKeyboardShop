<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script>
function checkSession(){
	sessionStorage.setItem("hi","hi");
	
}
function checkSession2(){
	
	alert(sessionStorage.length);
}
</script>
<title>Insert title here</title>

<style type="text/css">
a:link {text-decoration: none; color: #333333;}
a:visited {text-decoration: none; color: #333333;}
a:active {text-decoration: none; color: #333333;}
a:hover {text-decoration: underline; color: red;}
</style>
</head>
<body>

<div style="float:left; width: 50%;">
	<div align="center"> 개발자 이름 : 김유일</div>
	<div align="center"> <b>phone Number</b> : 010 2350 0688</div>
	<div align="center"> <b>email</b> - il3575@naver.com & il3575il6892@gmail.com </div>
</div>

<div style="float:right;width: 50%;">
	<div align="center"> <b>github about this project</b> : 
	<a href="https://github.com/kimyuil/webProject_onlyKeyboardShop">https://github.com/kimyuil/webProject_onlyKeyboardShop</a> 
	</div>
	
	<br>

	<div align="center"> <b>tistory</b> : 
	<a href="https://only61.tistory.com/">https://only61.tistory.com/</a> </div>
	</div>
<br><br><br><br>
<br><br>



</body>
</html>