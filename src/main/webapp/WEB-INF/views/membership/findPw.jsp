<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/onlyKeyboardShop/resources/js/findAction.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">비밀번호 찾기</h2>
<br>
<div style="width:300px; margin:0 auto; " >
<form onsubmit="return validatePw();" method="post" name="findPwForm"  action="findPw"> 
  <div class="form-group">
    <label for="email">Email</label>
    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter Email">
    <small id="emailHelp" class="form-text text-muted">Email을 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="id">본인 ID</label>
    <input type="text" class="form-control" id="id" name="id" placeholder="enter your Id">
  </div>
  <!-- <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div> -->
  <br>
  <button type="submit" class="btn btn-info btn-block">비밀번호 찾기</button>
  <br>
</form>
  <div style="float:left;">
  	<a href="/onlyKeyboardShop/findIdView">id찾기</a>
  </div>
  <div style="float:right;">
  <a href="/onlyKeyboardShop/login">login</a>
  </div>  

</div>

<br><br><br>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>