<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>

<script>

//$(document).ready(function(){
	/* $('#freeboard').html(
			' <thead><tr>'+    
      '<th scope="col" style="width:50px;text-align: center;">no</th>'+
      '<th scope="col" style="text-align: center;">제목</th>'+
      '<th scope="col" style="width:100px;text-align: center;">작성자</th>'+
      '<th scope="col" style="width:100px;text-align: center;">작성일</th>'+
      '<th scope="col" style="width:80px;text-align: center;">조회수</th>'+
      '<th scope="col" style="width:70px;text-align: center;">추천</th>'+
    '</tr></thead>');
	
	$('#freeboard').append('<tbody>');
	$('#freeboard').append('<tr><th scope="row" style="text-align: center;">1</th>'+
		      '<td>제목</td>'+
		      '<td style="text-align: center;">홍길이</td>'+
		      '<td style="text-align: center;">오늘</td>'+
		      '<td style="text-align: center;">1000</td>'+
		      '<td style="text-align: center;">50</td>'+
		    '</tr>');
	$('#freeboard').append('</tbody>'); */
//});

</script>


</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">자유게시판</h2>
<br><br><br>
<div style='width:900px;margin:0 auto;'>
<table class="table" id="freeboard">
<thead>
	<tr>
      <th scope="col" style="width:50px;text-align: center;">no</th>
      <th scope="col" style="text-align: center;">제목</th>
      <th scope="col" style="width:100px;text-align: center;">작성자</th>
      <th scope="col" style="width:120px;text-align: center;">작성일</th>
      <th scope="col" style="width:80px;text-align: center;">조회수</th>
      <th scope="col" style="width:70px;text-align: center;">추천</th>
    </tr>
</thead>
<tbody>
<c:forEach items="${boards}" var="boards" begin="${pageInfo.currentPageFirstNum}" end="${pageInfo.currentPageLastNum}" varStatus="status">
  <tr>
  	<th scope="row" style="text-align: center;">${status.index*(-1)+pageInfo.lastPageNum}
  	</th>
	<td>
	<a href="/onlyKeyboardShop/freeboardContentView?fbId=${boards.fbId}&page=${pageInfo.currentPage}">
	${boards.fbTitle} 
	<c:if test="${boards.fbReplys != 0}">
	<small style="color:gray;">[${boards.fbReplys}]</small>
	</c:if> 	
	</a></td>
	<td style="text-align: center;">${boards.uName}</td>
	<td style="text-align: center;">${boards.fbTime}</td>
	<td style="text-align: center;">${boards.fbHit}</td>
	<td style="text-align: center;">${boards.fbgood}</td>
</tr>
</c:forEach>
<tr>
<td style="text-align: center;" colspan="6">
	<c:if test="${pageInfo.blockStartNum != 1}" >
		<a href="/onlyKeyboardShop/freeboardList?page=${pageInfo.blockStartNum-1}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${pageInfo.blockStartNum}" end="${pageInfo.blockLastNum}" >
	 	<a href="/onlyKeyboardShop/freeboardList?page=${i}">
	 		<c:if test="${i==pageInfo.currentPage}" >
	 		<b>${i}</b>
	 		</c:if>
	 		<c:if test="${i!=pageInfo.currentPage}" >
	 		${i}
	 		</c:if>
	 	</a> 
	</c:forEach>
	<c:if test="${pageInfo.blockLastNum != pageInfo.realLastBlockNum}" >
		<a href="/onlyKeyboardShop/freeboardList?page=${pageInfo.blockLastNum+1}">다음</a>
	</c:if>
	
	<span style="float:right">
	<button class='btn btn-outline-dark btn-sm' 
	onclick="location.href ='/onlyKeyboardShop/member/freeboardWriteView?page=${pageInfo.currentPage}'">글쓰기</button>
	</span>
</td>
</tr>
</tbody>
</table>
</div>

	<%--
	
		<c:forEach items="${list}" var="dto" begin="${pageInfo.currentPageFirstNum}" end="${pageInfo.currentPageLastNum}" varStatus="status">
	<tr>
		<td>${status.index*(-1)+pageInfo.lastPageNum}</td>
		<td>${dto.bname}</td>
		<td>
			<a href="content_view.do?bId=${dto.bid}&page=${pageInfo.currentPage}">${dto.btitle}</a>
		</td>
		<td>${dto.bdate}</td>
		<td>${dto.bhit}</td>
	</tr>
	</c:forEach>
	<tr>
	<td colspan="5">
	<c:if test="${pageInfo.blockStartNum != 1}" >
		<a href="list.do?page=${pageInfo.blockStartNum-1}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${pageInfo.blockStartNum}" end="${pageInfo.blockLastNum}" >
	 	<a href="list.do?page=${i}">${i}</a> 
	</c:forEach>
	<c:if test="${pageInfo.blockLastNum != pageInfo.realLastBlockNum}" >
		<a href="list.do?page=${pageInfo.blockLastNum+1}">다음</a>
	</c:if>
	</td>
	</tr>
	
	이제 여깋면되는데 일단 체크항번
	<tr>
	<td colspan="5"><a href="write_view.do?page=${pageInfo.currentPage}">글작성</a></td>
	</tr>--%>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>