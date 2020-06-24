<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/>
<sec:authentication property="principal.uName" var="uName"/> 
</sec:authorize>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">자유게시판 글수정하기</h2>
<br><br><br>

<div style="width:800px; margin:0 auto; " >
<form action="/onlyKeyboardShop/freeboardModify" method="post" id="freeboardSubmit" > <!-- onsubmit="return freeboardSubmit()" -->
  <div class="form-group">
    <label for=FQtitle">${uName}님, 제목을 입력하세요</label>
    <input type="text" class="form-control" id="Ftitle" value="${fbTitle}" name="fbTitle">
  </div>
  <div class="form-group">
    <label for="Fcontent">내용을 입력해주세요 (500자)</label>
    <textarea class="form-control" id="Fcontent" rows="6" name="fbContent">${fbContent}</textarea>
  </div>

  <br>
  <button type="submit" class="btn btn-info btn-block">수정완료</button>
  <br>
  <input name="fbId" value="${fbId}" type="hidden"/>
  <input name="page" value="${page}" type="hidden"/>
    
</form>	 
	 <div style="float:right;">
	 <button class='btn btn-secondary' onclick="location.href='/onlyKeyboardShop/freeboardList?page=${page}' ">목록으로</button>
	 </div>
</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>