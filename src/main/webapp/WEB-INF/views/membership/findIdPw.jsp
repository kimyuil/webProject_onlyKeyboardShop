<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">아이디 비밀번호 찾기</h2>
<br>
<div style="width:300px; margin:0 auto; " >
<form>
  <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email">
    <small id="emailHelp" class="form-text text-muted">Email을 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">본인이름</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="enter your name">
  </div>
  <!-- <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div> -->
  <br>
  <button type="submit" class="btn btn-info btn-block">아이디 비밀번호 찾기</button>
  <br>
  <a href="/onlyKeyboardShop/login">login</a>
  
</form>
</div>

<br><br><br>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>