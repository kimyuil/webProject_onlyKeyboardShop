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
var reviewsList = new Array();
var qnaList = new Array();
var freeboardList = new Array();

var currentUserName= "${currentUserName}"

$(document).ready(function(){
	getReviewList();
	showReviewList();
	
	QNAList();
	showQNAList();
	
	getfreeboardList();
	showFreeboardList();
});


</script>

<script src="${pageContext.request.contextPath}/resources/js/myboard/myboardReview.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/myboard/myboardQnA.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/myboard/myboardFreeboard.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">나의게시물</h2>
<br><br><br>


<div style='width:750px;margin:0 auto;'>
	<h4><b>작성한 후기</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="reviewTable">
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;' >
	<h4><b>Q&A</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="qnaTable">
	</table>
</div>
<form id="QnaModifyView" method="post" action="userModifyQnaView"></form>
<form id="DeleteQna" method="post" action="/onlyKeyboardShop/userDeleteQna"></form>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>자유게시판 게시글</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="freeboardTable">
	<tr><td>1</td><td>2</td></tr>
	</table>
</div>

<br><br>


<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>