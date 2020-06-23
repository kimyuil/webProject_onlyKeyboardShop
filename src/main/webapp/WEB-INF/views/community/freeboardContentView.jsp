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
<h2 align="center">자유게시판</h2>
<br><br><br>
<div style="width:800px; margin:0 auto; " >
	<div class="card">
	  <h5 class="card-header">${board.fbTitle}</h5>
	  <div class="card-body">
	  <p>${board.uName} ${board.fbTime} 조회수 ${board.fbHit} 댓글 ${board.fbReplys}개 추천수 ${board.fbgood}</p>
	    <p class="card-text">${board.fbContent}</p>
	  </div>
</div>

</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>