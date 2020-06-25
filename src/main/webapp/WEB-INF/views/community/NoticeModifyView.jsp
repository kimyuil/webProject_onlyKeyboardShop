<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/>
</sec:authorize>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function noticeSubmit(){
	return true;
}
</script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">공지사항 작성 [운영자용]</h2>
<br><br><br>

<div style="width:800px; margin:0 auto; " >
<form action="/onlyKeyboardShop/modifyNotice" method="post" id="noticeSubmit" onsubmit="return noticeSubmit()">
  <div class="form-group">
    <label for="Ntitle">제목 입력</label>
    <input type="text" class="form-control" id="Ntitle"  value="${nbTitle}" name="nbTitle">
  </div>
  <div class="form-group">
    <label for="Ncontent">내용 입력</label>
    <textarea class="form-control" id="Ncontent" rows="6" name="nbContent">${nbContent }</textarea>
  </div>

  <br>
  <button type="submit" class="btn btn-info btn-block">작성완료</button>
  <br>
   <input name="page" value="${page}" type="hidden"/>
   <input name="nbId" value="${nbId}" type="hidden"/>  
</form>
<div style="float:right;">
	 <button class='btn btn-secondary' onclick="location.href='/onlyKeyboardShop/noticeList?page=${page}' ">목록으로</button>
	 </div>
</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>