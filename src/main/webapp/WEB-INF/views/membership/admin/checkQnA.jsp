<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
var currentUserName = "${currentUserName}";
var bflag="before";
var aflag="after";
var dubleQuotor = "\"";
var qnaBeforeList = new Array();
var qnaAfterList = new Array();

$(document).ready(function(){
	
	QNAList();
	showQNAList();
	
});

</script>
<script src="${pageContext.request.contextPath}/resources/js/admin/adminQnA.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center"><b>check Q&A</b></h2>
<br><br>
<br><br>

<div style='width:750px;margin:0 auto;' >
	<h4><b>Q&A 답변대기</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="qnaBeforeTable">
	</table>
	<br><br><br>
	<h4><b>Q&A 답변완료</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="qnaAfterTable">
	</table>
</div>
<form id="QnaModifyView" method="post" action="userModifyQnaView"></form>
<form id="DeleteQna" method="post" action="/onlyKeyboardShop/userDeleteQna"></form>

<br><br>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>


</body>
</html>