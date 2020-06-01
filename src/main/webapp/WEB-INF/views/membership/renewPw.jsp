<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/onlyKeyboardShop/resources/js/findActions.js"></script>


</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">비밀번호를 다시 설정하세요</h2>
<br>
<div style="width:300px; margin:0 auto; " >
<form onsubmit="return validateFindPwCheck();" method="post" name="checkform" action="renewPw" >
  <div class="form-group">
    <label for="pw">비밀번호</label>
    <input type="password" class="form-control" id="pw" name="uPw" placeholder="password">
  </div>
  <div class="form-group">
    <label for="checkpw">비밀번호 확인</label>
    <input type="password" class="form-control" id="checkpw" placeholder="password check">
    <small id="pwHelp" class="form-text text-muted">pw는 4~12자의 영어와 소문자로 입력</small>
  </div>
  <br>
  <button type="submit" class="btn btn-info btn-block">비밀번호 변경하기</button>
  <br>
</form>
</div>

<br><br><br>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>