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
</sec:authorize>

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var page = "${page}";

//board info
var nbTime = "${notice.nbTime}";
var nbId ="${notice.nbId}";
var nbTitle = "${notice.nbTitle}";
var nbContent = "${notice.nbContent}";


//curruent user id
var currentUserName = "${currentUserName}";


$(document).ready(function(){
	
	time = nbTime.substring(2,16);
	$('#time').append(time);
	
});
	

function modifyNotice(){
		
	$('#noticeModifyView').append(
			"<input type='hidden' name='nbId' value='"+nbId+"'/>"+
			"<input type='hidden' name='nbTitle' value='"+nbTitle+"'/>"+
			"<input type='hidden' name='nbContent' value='"+nbContent+"'/>"+
			"<input type='hidden' name='page' value='"+page+"'/>");
	
	$('#noticeModifyView').submit();
	
}
function deleteNotice(){
	
	var check = confirm("정말 삭제하시겠습니까?");
	if (check==false)
		return;
	
	$('#DeleteNotice').append(
			"<input type='hidden' name='nbId' value='"+nbId+"'/>"+
			"<input type='hidden' name='page' value='"+page+"'/>");
	
	$('#DeleteNotice').submit();
}

function date_to_str(format){
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    if(month<10) month = '0' + month;
    var date = format.getDate();
	if(date<10) date = '0' + date;
    var hour = format.getHours();
    if(hour<10) hour = '0' + hour;
    var min = format.getMinutes();
    if(min<10) min = '0' + min;
    var sec = format.getSeconds();
    if(sec<10) sec = '0' + sec;
	return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
}
</script>


</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center"><b>공지사항</b></h2>
<br><br><br>
<div style="width:800px; margin:0 auto; " >
	<div class="card">
	  <h5 class="card-header">${notice.nbTitle}</h5>
	  <table class="card-header">
	  <tr>
	  	<td style="text-align:center;width:100px;"><span>운영자</span></td>
	   <td  id="time" style="text-align:center;width:140px;"><span></span></td>
	   <td><span>&nbsp</span></td>
	   <td style="text-align:center;width:90px;"><span>조회수 ${notice.nbHit}</span></td>
	  </tr>
	  </table>
	  
	  <div class="card-body" style="height:300px;">
	  
	    <p class="card-text">${notice.nbContent}</p>
	    
	  </div>
	  <div class="card-footer">
	  	  
	  <span style='float:right'>
	  <c:if test="${currentUserName == 'admin'}">
	  <button id="modifyButton" class="btn btn-outline-dark btn-sm" onclick="modifyNotice()">수정하기</button> 
	  <button id="deleteButton" class="btn btn-outline-dark btn-sm" onclick="deleteNotice()">삭제하기</button>
	  </c:if> 
	  <button id="gotoListButton" class="btn btn-outline-dark btn-sm" onclick="location.href='/onlyKeyboardShop/noticeList?page=${page}' ">목록으로</button>
	  </span></div>
	</div>
	<form id="noticeModifyView" method="post" action="admin/noticeModifyView"></form>
	<form id="DeleteNotice" method="post" action="/onlyKeyboardShop/deleteNotice"></form>




<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>