<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var time = "${board.fbTime}";
$(document).ready(function(){
	time = time.substring(2,16);
	$('#time').append(time);
});

function clickGood(){
	
	if($('#clickGood').attr('class')=='btn btn-outline-danger btn-sm'){
		
		$.ajax({
		    url: "/onlyKeyboardShop/freeboardLikePlus",
		    type: "POST",
		    cache: false,
		    async: false,
		    data: {"fbId" : "${board.fbId}"},
		    success: function(data){
		    	$('#clickGood').attr('class','btn btn-danger btn-sm');
				$('#clickGood').html('추천 ♥');
		    },
		    error: function(data){
		    	alert("다시시도해주세요");
		    }
		});
	}
	else if($('#clickGood').attr('class')=='btn btn-danger btn-sm'){
		$.ajax({
		    url: "/onlyKeyboardShop/freeboardLikeMinus",
		    type: "POST",
		    cache: false,
		    async: false,
		    data: {"fbId" : "${board.fbId}"},
		    success: function(data){
		    	$('#clickGood').attr('class','btn btn-outline-danger btn-sm');
				$('#clickGood').html('추천');
		    },
		    error: function(data){
		    	alert("다시시도해주세요");
		    }
		});
	}
}

function modifyBoard(){
	if("${board.uId}"!="${currentUserName}"){
		alert("본인만 수정할 수 있습니다");
		return;
	}	
	$('#freeboardModifyView').append(
			"<input type='hidden' name='fbId' value='${board.fbId}'/>"+
			"<input type='hidden' name='fbTitle' value='${board.fbTitle}'/>"+
			"<input type='hidden' name='fbContent' value='${board.fbContent}'/>"+
			"<input type='hidden' name='page' value='${page}'/>");
	
	$('#freeboardModifyView').submit();
	
	
	//post로 ! 가기는 member로 가면 될것 같다..
	//뭘 받아야할까? 기존 title, 기존 content정도.
}
function deleteBoard(){
	if("${board.uId}"!="${currentUserName}"){
		alert("본인만 삭제할 수 있습니다");
		return;
	}
	var check = confirm("정말 삭제하시겠습니까?");
	if (check==false)
		return;
	
	$('#DeleteFreeboard').append(
			"<input type='hidden' name='fbId' value='${board.fbId}'/>"+
			"<input type='hidden' name='page' value='${page}'/>");
	
	$('#DeleteFreeboard').submit();
}

</script>


</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">자유게시판</h2>
<br><br><br>
<div style="width:800px; margin:0 auto; " >
	<div class="card">
	  <h5 class="card-header">${board.fbTitle}</h5>
	  <table class="card-header">
	  <tr>
	  	<td style="text-align:center;width:100px;"><span>${board.uName}</span></td>
	   <td  id="time" style="text-align:center;width:140px;"><span></span></td>
	   <td><span>&nbsp</span></td>
	   <td style="text-align:center;width:90px;"><span>조회수 ${board.fbHit}</span></td>
	  <td style="text-align:center;width:90px;"> <span>댓글 ${board.fbReplys}개</span></td>
	  <td style="text-align:center;width:90px;"> <span>추천수 ${board.fbgood}</span></td>
	  </tr>
	  </table>
	  
	  <div class="card-body" style="height:300px;">
	  
	    <p class="card-text">${board.fbContent}</p>
	    
	  </div>
	  <div class="card-footer">
	  <span style='float:left'>
	   <button id="clickGood" class="btn btn-outline-danger btn-sm" onclick="clickGood()">추천</button>
	  </span>
	  
	  <span style='float:right'>
	  <button id="modifyButton" class="btn btn-outline-dark btn-sm" onclick="modifyBoard()">수정하기</button> 
	  <button id="deleteButton" class="btn btn-outline-dark btn-sm" onclick="deleteBoard()">삭제하기</button> 
	  <button id="gotoListButton" class="btn btn-outline-dark btn-sm" onclick="location.href='/onlyKeyboardShop/freeboardList?page=${page}' ">목록으로</button>
	  </span></div>
	</div>
	<form id="freeboardModifyView" method="post" action="member/freeboardModifyView"></form>
	<form id="DeleteFreeboard" method="post" action="/onlyKeyboardShop/deleteFreeboard"></form>

</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>