<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<c:if test="${pwChange=='ok'}" >
<script>
alert("성공적으로 변경되었습니다. 다시 로그인해주시기 바랍니다");
</script>

</c:if>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">login page</h2>
<br><br>
<div style="width:300px; margin:0 auto; " >
<form method="post" action="<c:url value="j_spring_security_check"/>">
  <div class="form-group">
    <label for="id">ID</label>
    <input type="text" class="form-control" id="id" placeholder="Enter ID" name="uId">
    <small id="idHelp" class="form-text text-muted">id를 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="pw">Password</label>
    <input type="password" class="form-control" id="pw" placeholder="Password" name="uPw">
  </div>
  <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
  <br>
  <button type="submit" class="btn btn-primary btn-block">Login</button>
  <br>
  <div style="float:left;">
  	<a href="/onlyKeyboardShop/findIdView">id찾기</a>/<a href="/onlyKeyboardShop/findPwView">pw찾기</a>
  </div>
  <div style="float:right;">
  <a href="/onlyKeyboardShop/joinView">회원가입하기</a>
  </div>
  
</form>
</div>
<div style="width:300px; height:40px; margin:0 auto; " ></div>
<div style="width:400px; margin:0 auto; text-align: center; " >
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <font color="red" >
        <p>아이디 또는 비밀번호가 맞지 않습니다.</p>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
    </font>
</c:if>
</div>


<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>