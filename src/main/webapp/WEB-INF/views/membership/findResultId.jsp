<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${resultId=='false'}" >
<script>
alert("해당하는 아이디가 존재하지 않습니다");
history.go(-1);
</script>

</c:if>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>

<br>
<h2 align="center">아이디 찾기</h2>
<br>
<div style="width:350px; margin:0 auto; text-align: center; " >
<div class="card" style="width: 100%;">
<div class="card-body">
    <h5 class="card-title">아이디 찾기 결과</h5>
    <h6 class="card-subtitle mb-2 text-muted">아이디중 일부는 생략되어 표시됩니다</h6>
    <br>
    <h5 class="card-text">${resultId}</h5>
    <br> <hr>
    <div style="float:left;">
  	<a href="/onlyKeyboardShop/findPwView">&nbsp pw찾기</a>
  </div>
  <div style="float:right;">
  <a href="/onlyKeyboardShop/login">login &nbsp</a>
  </div>
  </div>
</div>
</div>

<br><br><br>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>


</body>
</html>