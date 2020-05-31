<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="/onlyKeyboardShop/">Only Keyboard</a>

  <!-- Links -->
  <ul class="navbar-nav">
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Brand
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="/onlyKeyboardShop/about">about</a>
        <a class="dropdown-item" href="/onlyKeyboardShop/location">location</a>
      </div>
    </li>
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Product
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="/onlyKeyboardShop/88keyboard">88Keyboard</a>
        <a class="dropdown-item" href="/onlyKeyboardShop/76keyboard">76Keyboard</a>
        <a class="dropdown-item" href="/onlyKeyboardShop/61keyboard">61Keyboard</a>
      </div>
    </li>
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Community
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="/onlyKeyboardShop/freeboard">자유게시판</a>
        <a class="dropdown-item" href="/onlyKeyboardShop/notice">공지사항</a>
      </div>
    </li>
  </ul>
  
  <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
  	<ul class="navbar-nav ml-auto">
  	<sec:authorize access="isAnonymous()">
  		<li class="nav-item">
  			<a class="nav-link" href="/onlyKeyboardShop/login">login</a>
  		</li>
  	</sec:authorize>
  	
  	<sec:authorize access="isAuthenticated()">
  		 <li class="nav-item">
  			<span class="navbar-text"><b><sec:authentication property="principal.uName"/></b>님, 반갑습니다!</span>
  		</li> 
  		<li class="nav-item">
  			<a class="nav-link" href="#" onclick="document.getElementById('logout-form').submit();">로그아웃</a>
  		</li>
  	</sec:authorize>
			
			
 		
  		<li class="nav-item">
  			<a class="nav-link" href="/onlyKeyboardShop/mypage">mypage</a>
  		</li>
  	</ul>
  </div>
</nav>
<form id="logout-form" action="j_spring_security_logout" method="POST" style="display:none;"> 
   				<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			</form>
<br>

</body>
</html>