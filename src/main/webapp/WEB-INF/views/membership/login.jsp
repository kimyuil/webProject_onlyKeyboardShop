<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">login page</h2>
<br><br>
<div style="width:300px; margin:0 auto; " >
<form>
  <div class="form-group">
    <label for="exampleInputEmail1">ID</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter ID">
    <small id="emailHelp" class="form-text text-muted">id를 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <!-- <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div> -->
  <br>
  <button type="submit" class="btn btn-primary btn-block">Login</button>
  <br>
  <div style="float:left;">
  	<a href="/onlyKeyboardShop/findMembership">id/pw 찾기</a>
  </div>
  <div style="float:right;">
  <a href="/onlyKeyboardShop/joinView">회원가입하기</a>
  </div>
  
</form>
</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>