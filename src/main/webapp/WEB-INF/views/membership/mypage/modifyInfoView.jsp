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
function gotoModify(){
	var inputpw=prompt("비밀번호 확인");
	if(inputpw != "${uPw}"){
		alert("비밀번호가 틀립니다")
		return;
	}
	else{
		location.href='/onlyKeyboardShop/member/modifyUserView'
	}
}

</script>

<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">회원정보</h2>
<br><br>
<div style="width:400px;margin:0 auto;">
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
      <th scope="row" style="text-align: center;">전화번호</th>
      <td>${uPhone}</td>
    </tr>
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
      <td>${uEmail}</td>
    </tr>
    <tr>
      <th scope="row" style="text-align: center;">주소</th>
      <td>${uAdress}</td>
    </tr>
  </tbody>
</table>
<button class='btn btn-info btn-block' onclick="gotoModify(); ">수정하기</button>
</div>
<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>