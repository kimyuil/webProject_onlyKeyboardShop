<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>
<title>Insert title here</title>

<style>
tr.border_bottom td {
  border-bottom:2pt solid black;
}
</style>

<script>

var beforeList=new Array();
var afterList=new Array();
var curruntUserName = "${currentUserName}";

$(document).ready(function(){
	getList();
	showLists();
	
});


</script>
<script src="${pageContext.request.contextPath}/resources/js/admin/adminShipping.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center"><b>check Order</b></h2>
<br><br>
<div style='width:750px;margin:0 auto;'>
	<h4><b>구매요청 제품들</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="beforeList">
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>배송완료 제품 & 후기작성여부</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="afterList">
	</table>
</div>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>