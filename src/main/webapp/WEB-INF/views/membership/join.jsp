<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script src="/onlyKeyboardShop/resources/js/joinAction.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">회원 가입</h2>
<br>
<div style="width:300px; margin:0 auto; " >
<form onsubmit="return validate();" method="post" name="joinForm" action="join" >

	<div class="form-row">
	  <div class="form-group col-md-9">
	    <label for="id">id 입력</label>
	    <input type="text" class="form-control" id="id" name="uId" placeholder="id">
	    <small id="idHelp" class="form-text text-muted">id는 4~12자의 영어와 소문자로 입력</small>
	    <samll><span id="result"></span></samll>
	  </div>
	  <div class="form-group col-md-3">
	    <br>
	    <button type="button" class="btn btn-primary" id="idCheckButton" onclick="javascript:idCheck()" >id<br>확인</button>
	  </div>
	   
	</div>
          
  <div class="form-group">
    <label for="pw">비밀번호</label>
    <input type="password" class="form-control" id="pw" name="uPw" placeholder="password">
  </div>
  <div class="form-group">
    <label for="checkpw">비밀번호 확인</label>
    <input type="password" class="form-control" id="checkpw" placeholder="password check">
    <small id="pwHelp" class="form-text text-muted">pw는 4~12자의 영어와 소문자로 입력</small>
  </div>
  
  <div class="form-group">
    <label for="name">이름</label>
    <input type="text" class="form-control" id="name" name="uName" placeholder="이름">
          <!-- 아이디중복확인 -->
  </div>
  <div class="form-group">
    <label for="phone">전화번호</label>
    
    <div class="row">
    	<div class="col">
      		<input type="text" class="form-control" id="phone1" placeholder="010">
    	</div>
    	<div class="col">
      		<input type="text" class="form-control" id="phone2" placeholder="1234">
    	</div>
    	<div class="col">
      		<input type="text" class="form-control" id="phone3" placeholder="5678">
    	</div>
    	<input type="hidden" id="submitPhone" name="uPhone">
  </div>
    <small id="phoneHelp" class="form-text text-muted">입력해 주시면 유용한 정보를 보내드립니다</small>
  </div>
  <div class="form-group">
    <label for="birth">주민등록번호 앞자리</label> <!-- 폼을 바꿔야 하지 않나? -->
    <input type="text" class="form-control" id="birth" placeholder="ex) 950101">
    <input type="hidden" class="form-control" id="submitBirth" name="uBirth">
  </div>
  <div class="form-group">
  <label for="gender">성별</label>
      <select id="gender" name="uGender" class="form-control">
        <option selected value="남자">남자</option>
        <option value="여자">여자</option>
      </select>
  
    <!-- <label for="gender">성별</label> 여기는 select로 하는게 맞지 않는가?
    <input type="text" class="form-control" id="gender" name="uGender" placeholder="남자 or 여자"> -->
  </div>
  
  
  
  <div class="form-group">
    <label for="email">email (필수)</label>
    <input type="text" class="form-control" id="email" name="uEmail" placeholder="example@gmail.com">
    <small id="emailHelp" class="form-text text-muted">필수사항입니다</small>
  </div>
  <div class="form-group">
    <label for="adress">주소</label>
    <input type="text" class="form-control" autocomplete="false" id="adress" name="uAdress" placeholder="경기도 광명시">
    <small id="emailHelp" class="form-text text-muted">간단히 입력하셔도 됩니다</small>
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