<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>


<script>

	function checkOrder(){
			
		location.href='/onlyKeyboardShop/admin/checkOrder';
	}
	function checkQnA(){
				
		location.href='/onlyKeyboardShop/admin/checkQnA';
	}
	
</script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br><br>
<h2 align="center"><b>ADMIN PAGE</b></h2>
<br><br><br>
<div style='width:500px;margin:0 auto;'>
<button class="btn btn-outline-success btn-block" onclick="checkOrder();">주문확인 </button><br>
<button class="btn btn-outline-success btn-block" onclick="checkQnA();">Q&A확인</button><br>
</div>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>