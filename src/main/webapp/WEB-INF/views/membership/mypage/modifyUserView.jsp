<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/>
<sec:authentication property="principal.uPw" var="uPw"/>
<sec:authentication property="principal.uName" var="uName"/>
<sec:authentication property="principal.uPhone" var="uPhone"/>
<sec:authentication property="principal.uBirth" var="uBirth"/>
<sec:authentication property="principal.uGender" var="uGender"/>
<sec:authentication property="principal.uEmail" var="uEmail"/>
<sec:authentication property="principal.uAdress" var="uAdress"/> 
</sec:authorize>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
$(document).ready(function(){
	var phone="${uPhone}";
	var phone1=phone.substring(0,3);
	var phone2=phone.substring(3,7);
	var phone3=phone.substring(7,11);
	
	$('#phone1').attr("value",phone1);
	$('#phone2').attr("value",phone2);
	$('#phone3').attr("value",phone3);
	
});

function moveFocus(num,fromform,toform){
	  var str = fromform.value.length;
	  if(str == num) {
	    toform.focus();
	  }   
}

function modifyForm(){ //onsubmit
	
	var pw = $('#uPw').val();
	var pwCheck = $('#uPwCheck').val();
	
	if(pw==null || pw==undefined || pw==""){
		alert("패스워드를 입력해주세요");
		$('#uPw').focus();
		return false;
	}
	
	if(pw!=pwCheck){
		alert("패스워드가 서로 다릅니다");
		return false;
	}
	
	var check=confirm("수정하시겠습니까?");
	if(check==false)
		return false;
	
    var phone1 = document.getElementById("phone1");
    var phone2 = document.getElementById("phone2");
    var phone3 = document.getElementById("phone3");
    
	var phoneData = phone1.value+phone2.value+phone3.value;
	$('#submitPhone').attr("value",phoneData);
	
	return true;
}
</script>

<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">회원정보</h2>
<br><br>

<div style="width:400px;margin:0 auto;">
<form onsubmit="return modifyForm()" method="post" action="/onlyKeyboardShop/doModifyUserInfo">
<table class="table table-bordered">
  <thead>
    <tr>
      <th scope="col" style="text-align: center;width:30%;">항목</th>
      <th scope="col" style="text-align: center;">정보</th>
          </tr>
  </thead>
  <tbody>
  
    <tr>
      <th scope="row" style="text-align: center;">이름</th>
      <td>${uName}</td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">비밀번호<br>수정</th>
      <td><p>pw입력:<input  type="password" name="uPw" id="uPw"></p>
      pw확인:<input  type="password" id="uPwCheck">
      </td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">전화번호</th>
      <td><div class="row">
    	<div class="col">
      		<input type="text" class="form-control" id="phone1" >
    	</div>
    	<div class="col">
      		<input type="text" class="form-control" id="phone2">
    	</div>
    	<div class="col">
      		<input type="text" class="form-control" id="phone3">
    	</div>
    	<input type="hidden" id="submitPhone" name="uPhone">
  	</div>
  	</td>

    <tr>
      <th scope="row" style="text-align: center;">생년월일</th>
      <td>${uBirth}</td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">성별</th>
      <td>${uGender}</td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">email</th>
      <td><input id="email" type="text" name="uEmail" value="${uEmail}"></td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">주소</th>
      <td><input type="text" name="uAdress" value="${uAdress}"></td>
    </tr>
  </tbody>
</table>
<button class='btn btn-info btn-block' ">수정하기</button>
<input type="hidden" name="uId" value="${currentUserName}">
</form>
</div>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>