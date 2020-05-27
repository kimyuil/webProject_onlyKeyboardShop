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
<h2 align="center">회원 가입</h2>
<br>
<div style="width:300px; margin:0 auto; " >
<form>
  <div class="form-group">
    <label for="exampleInputEmail1">id 입력</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="id">
          <!-- 아이디중복확인 -->
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">비밀번호</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">비밀번호 확인</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password check">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">전화번호</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="홍길동">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">생년월일 앞자리</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="홍길동">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">성별</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="홍길동">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">email (필수)</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="홍길동">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">주소</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="홍길동">
  </div>
  
  <br>
  <button type="submit" class="btn btn-info btn-block">회원가입</button>
  <br>
  
</form>
</div>

<br><br><br>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>