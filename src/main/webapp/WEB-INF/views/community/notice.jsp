<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center"><b>공지사항</b></h2>
<br><br><br>
<div style='width:900px;margin:0 auto;'>
<table class="table" id="noticeboard">
<thead class="thead-dark">
	<tr>
      <th scope="col" style="width:50px;text-align: center;">no</th>
      <th scope="col" style="text-align: center;">제목</th>
      <th scope="col" style="width:100px;text-align: center;">작성자</th>
      <th scope="col" style="width:120px;text-align: center;">작성일</th>
      <th scope="col" style="width:80px;text-align: center;">조회수</th>
    </tr>
</thead>
<tbody>
<c:forEach items="${notices}" var="notices" begin="${pageInfo.currentPageFirstNum}" end="${pageInfo.currentPageLastNum}" varStatus="status">
  <tr>
  	<th scope="row" style="text-align: center;">${status.index*(-1)+pageInfo.lastPageNum}
  	</th>
	<td>
	<a href="/onlyKeyboardShop/noticeboardContentView?nbId=${notices.nbId}&page=${pageInfo.currentPage}">
	${notices.nbTitle} 
	</a></td>
	<td style="text-align: center;">운영자</td>
	<td style="text-align: center;">${notices.nbTime}</td>
	<td style="text-align: center;">${notices.nbHit}</td>
</tr>
</c:forEach>
<tr>
<td style="text-align: center;" colspan="5">
	<c:if test="${pageInfo.blockStartNum != 1}" >
		<a href="/onlyKeyboardShop/noticeList?page=${pageInfo.blockStartNum-1}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${pageInfo.blockStartNum}" end="${pageInfo.blockLastNum}" >
	 	<a href="/onlyKeyboardShop/noticeList?page=${i}">
	 		<c:if test="${i==pageInfo.currentPage}" >
	 		<b>${i}</b>
	 		</c:if>
	 		<c:if test="${i!=pageInfo.currentPage}" >
	 		${i}
	 		</c:if>
	 	</a> 
	</c:forEach>
	<c:if test="${pageInfo.blockLastNum != pageInfo.realLastBlockNum}" >
		<a href="/onlyKeyboardShop/noticeList?page=${pageInfo.blockLastNum+1}">다음</a>
	</c:if>
	
	<c:if test="${currentUserName == 'admin'}">
	<span style="float:right">
	<button class='btn btn-outline-dark btn-sm' 
	onclick="location.href ='/onlyKeyboardShop/member/freeboardWriteView?page=${pageInfo.currentPage}'">글쓰기</button>
	</span>
	</c:if>
</td>
</tr>
</tbody>
</table>
</div>


<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%></body>
</html>