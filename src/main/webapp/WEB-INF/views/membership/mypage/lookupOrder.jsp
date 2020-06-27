<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>


<script>
var beforeList=new Array();
var afterList=new Array();
var currentUserName = "${currentUserName}";

$(document).ready(function(){
	getList();
	showLists();
	
});


</script>
<script src="${pageContext.request.contextPath}/resources/js/LookupOrderAction.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">주문배송조회</h2>
<br><br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>구매한 상품</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="beforeList">
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>배송완료 제품 후기를 작성해주세요!</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="afterList">
	</table>
</div>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>