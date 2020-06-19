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

$(document).ready(function(){
	getReviewList();
	showReviewList();
});

function getReviewList(){
	$.ajax({
	    url: "/onlyKeyboardShop/userReviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : "${currentUserName}"},
	    success: function(data){
	    	
	    	//후기게시판, 페이징정보 가져오기
	    	for(var i =0;i<data.reviews.length;i++){
	    		var grade;
	    		switch(data.reviews[i].reGrade){
	    		case 1:
	    			grade="\★\☆\☆\☆\☆"; 
	    			break;
	    		case 2:
	    			grade="\★\★\☆\☆\☆";
	    			break;
	    		case 3:
	    			grade="\★\★\★\☆\☆";
	    			break;
	    		case 4:
	    			grade="\★\★\★\★\☆";
	    			break;
	    		case 5:
	    			grade="\★\★\★\★\★";
	    			break;
	    		}
	    		
	    		var item = {reId:data.reviews[i].reId, pId:data.reviews[i].pId, 
	    		uId:data.reviews[i].uId, pName:data.reviews[i].pName, pColor:data.reviews[i].pColor, 
	    		reDate:data.reviews[i].reDate,	uName:data.reviews[i].uName,  
	    		reContent:data.reviews[i].reContent, reGrade:grade};
	    		
	    		reviewsList.push(item);
	    	};
	    },
	    
	  });
}
function showReviewList(){ //실질적인 출력 담당
	$('#reviewTable').html(
			'<tr>'+
			'<td style="width:50px;text-align: center; background-color:#dedede" ><b>no</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>내용</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:80px; text-align: center;background-color:#dedede"><b>별점</b></td>'+
			'<td style="width:140px; text-align: center;background-color:#dedede"><b>버튼</b></td>'+
		'</tr>');
	
	var lastNum=0;
	
	for(var i=0 ; i<reviewsList.length ;i++){ //list 페이지 내용
    	$('#reviewTable').append("<tr>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td><a href='#'>"+reviewsList[i].reContent+"</a></td>"+
    		"<td style='text-align: center;'>"+reviewsList[i].reDate +"</td>"+
    		"<td style='text-align: center;'><b>"+reviewsList[i].reGrade +"</b></td>"+
    		"<td style='text-align: center;'>"+
    		"<button class='btn btn-outline-primary' onclick='modifyReview("+i+")'>수정</button>&nbsp"+
    		"<button class='btn btn-outline-secondary'>삭제</button></td>"+
    		"</tr>"
    	);
   }
}

function modifyReview(index){
	var item=reviewsList[index];
	//alert(item.reId);
	$.ajax({
	    url: "/onlyKeyboardShop/userReviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"reId" : item.reId},//??????
	    success: function(data){
	    	
	    } 
	});
}

</script>

</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">나의게시물</h2>
<br><br><br>


<div style='width:750px;margin:0 auto;'>
	<h4><b>작성한 후기</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="reviewTable">
	<tr><td>1</td><td>2</td></tr>
	<%-- <tr>
		<td style="width:50px;text-align: center;">No</td>
		<td style="width:100px;text-align: center;">image</td>
		<td>구매한 상품</td>
		<td style="width:120px; text-align: center;">구매날짜</td>
		<td style="width:80px;text-align: center;">배송조회</td>
	</tr>
	
	<c:forEach items="${beforeCheckList}" var="beforeList" varStatus="status">
	<tr>
		<td style='text-align: center;'>${status.index+1}</td>
		<td><img src="${beforeList.pImageRoute}" style="width:100%"/></td>
		<td>
			${beforeList.pName}(${beforeList.pColor})
		</td>
		<td style='text-align: center;'>${beforeList.purTime}</td>
		<td style='text-align: center;' id="beforeListState" >${beforeList.state}</td>
	</tr>
	</c:forEach> --%>
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>Q&A</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="afterList">
	<tr><td>1</td><td>2</td></tr>

	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>자유게시판 게시글</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="afterList">
	<tr><td>1</td><td>2</td></tr>
	</table>
</div>

<br><br>

<div style='width:750px;margin:0 auto;'>
	<h4><b>자유게시판 댓글</b></h4>
	<table style='width:100%' cellpadding="5" cellspacing="0" border="1" id="afterList">
	<tr><td>1</td><td>2</td></tr>

	</table>
</div>

<br><br><br><br><br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>