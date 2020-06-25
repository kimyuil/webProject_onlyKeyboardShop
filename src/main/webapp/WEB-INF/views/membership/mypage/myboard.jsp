<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
</sec:authorize>

<script>
var reviewsList = new Array();
var qnaList = new Array();
var freeboardList = new Array();

var currentUserName= "${currentUserName}"

$(document).ready(function(){
	getReviewList();
	showReviewList();
	
	QNAList();
	showQNAList();
	
	getfreeboardList();
	showFreeboardList();
});

function getfreeboardList(){
	freeboardList=[];
	$.ajax({
	    url: "/onlyKeyboardShop/userFreeboardList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : currentUserName},
	    success: function(data){
	    	
	    	//후기게시판, 페이징정보 가져오기
	    	for(var i =0;i<data.boards.length;i++){
	    		var datet=new Date(data.boards[i].fbTime);
	    		var time = date_to_str(datet);
	    		
	    		var item = {fbId:data.boards[i].fbId, fbTitle:data.boards[i].fbTitle, 
	    		fbContent:data.boards[i].fbContent, fbTime:time,  
	    		uId:data.boards[i].uId,	uName:data.boards[i].uName,  
	    		fbHit:data.boards[i].fbHit, fbReplys:data.boards[i].fbReplys, fbgood:data.boards[i].fbgood};
	    		
	    		freeboardList.push(item);
	    	};
	    },	    
	 });
}

function showFreeboardList(){
	$('#freeboardTable').html(
			'<tr>'+
			'<td style="width:35px;text-align: center; background-color:#dedede" ><b>no</b></td>'+
			'<td style="width:75px; text-align: center;background-color:#dedede"><b>작성자</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>제목</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:80px; text-align: center;background-color:#dedede"><b>조회수</b></td>'+
			'<td style="width:80px; text-align: center;background-color:#dedede"><b>추천수</b></td>'+
		'</tr>');
	
	//var lastNum=0;
	
	for(var i=0 ; i<freeboardList.length ;i++){ //list 페이지 내용
    	$('#freeboardTable').append("<tr>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'>"+freeboardList[i].uName+"</td>"+
    		"<td><a href='/onlyKeyboardShop/freeboardContentView?fbId="+freeboardList[i].fbId+"&page=1'>"+freeboardList[i].fbTitle+
    				" <small style='color:gray;'>["+freeboardList[i].fbReplys+"]</small></a></td>"+
    		"<td style='text-align: center;'>"+freeboardList[i].fbTime +"</td>"+
    		"<td style='text-align: center;'><b>"+freeboardList[i].fbHit +"</b></td>"+
    		"<td style='text-align: center;'><b>"+freeboardList[i].fbgood +"</b></td>"+
    		"</tr>"
    	);
   }
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
	return year + "-" + month + "-" + date;// + " " + hour + ":" + min + ":" + sec;
}

</script>

<script src="/onlyKeyboardShop/resources/js/myboard/myboardReview.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/myboard/myboardQnA.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">나의게시물</h2>
<br><br><br>


<div style='width:750px;margin:0 auto;'>
	<h4><b>작성한 후기</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="reviewTable">
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;' >
	<h4><b>Q&A</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="qnaTable">
	</table>
</div>
<form id="QnaModifyView" method="post" action="userModifyQnaView"></form>
<form id="DeleteQna" method="post" action="/onlyKeyboardShop/userDeleteQna"></form>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>자유게시판 게시글</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="freeboardTable">
	<tr><td>1</td><td>2</td></tr>
	</table>
</div>

<br><br>


<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>