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
var time = "${board.fbTime}";
var fbId ="${board.fbId}";
var uId = "${board.uId}"
var fbTitle = "${board.fbTitle}";
var fbContent = "${board.fbContent}";


//curruent user id
var currentUserName = "${currentUserName}";

var commentsList = new Array();
var recommentsList = new Array();

$(document).ready(function(){
	
	time = time.substring(2,16);
	$('#time').append(time);
	
	commentList();
	showComment();
});
	
function clickGood(){
	
	if($('#clickGood').attr('class')=='btn btn-outline-danger btn-sm'){
		
		$.ajax({
		    url: "/onlyKeyboardShop/freeboardLikePlus",
		    type: "POST",
		    cache: false,
		    async: false,
		    data: {"fbId" : fbId},
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
		    data: {"fbId" : fbId},
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
	if(uId != currentUserName){
		alert("본인만 수정할 수 있습니다");
		return;
	}	
	$('#freeboardModifyView').append(
			"<input type='hidden' name='fbId' value='"+fbId+"'/>"+
			"<input type='hidden' name='fbTitle' value='"+fbTitle+"'/>"+
			"<input type='hidden' name='fbContent' value='"+fbContent+"'/>"+
			"<input type='hidden' name='page' value='"+page+"'/>");
	
	$('#freeboardModifyView').submit();
	
}
function deleteBoard(){
	if(uId != currentUserName){
		alert("본인만 삭제할 수 있습니다");
		return;
	}
	var check = confirm("정말 삭제하시겠습니까?");
	if (check==false)
		return;
	
	$('#DeleteFreeboard').append(
			"<input type='hidden' name='fbId' value='"+fbId+"'/>"+
			"<input type='hidden' name='page' value='"+page+"'/>");
	
	$('#DeleteFreeboard').submit();
}

//여기부터 comment~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


function commentList(){ //댓글데이터 ajax로 가져오기
	commentsList = [];
	recommentsList = [];
	$.ajax({
	    url: "/onlyKeyboardShop/commentList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"fbId" : fbId},
	    success: function(data){
	    	
	    	//댓글 데이터 가져오기
	    	for(var i =0;i<data.comments.length;i++){
	    		var timet=new Date(data.comments[i].cTime);
	    		var time=date_to_str(timet);
	    		
	    		
	    			    		
	    		var item = {cId:data.comments[i].cId, cParentId:data.comments[i].cParentId, 
	    		fbId:data.comments[i].fbId, cName:data.comments[i].cName, cPw:data.comments[i].cPw, 
	    		cComment:data.comments[i].cComment, cReplys:0,	  
	    		cTime:time, IsReplyComment:data.comments[i].IsReplyComment};
	    		
	    		commentsList.push(item);
	    	};
	    	//대댓글 데이터 가져오기
	    	for(var i =0;i<data.recomments.length;i++){
	    		var timet=new Date(data.recomments[i].cTime);
	    		var time=date_to_str(timet);
	    		//var timet=data.recomments[i].cTime;
	    		//var time=timet.substring(0,16);
	    			    			    		
	    		var item = {cId:data.recomments[i].cId, cParentId:data.recomments[i].cParentId, 
	    		fbId:data.recomments[i].fbId, cName:data.recomments[i].cName, cPw:data.recomments[i].cPw, 
	    		cComment:data.recomments[i].cComment, cReplys:0,	  
	    		cTime:time, IsReplyComment:data.recomments[i].IsReplyComment};
	    		
	    		recommentsList.push(item);
	    	};
	    	
	    	//대댓글 개수 구하기
	    	for(var i = 0 ; i<commentsList.length; i++){
	    		var replysNum=0;
	    		for(var j=0;j<recommentsList.length;j++){
	    			if(commentsList[i].cId==recommentsList[j].cParentId){
	    				replysNum=replysNum+1;
	    			}
	    		}
	    		commentsList[i].cReplys=replysNum;
	    	}
	    	
	    }	    
	  });	
}

//var commentsList = new Array();
//var recommentsList = new Array();

function showComment(){ //댓글데이터 화면에 뿌려주기
	
	$('#accordion').html("<div></div>");//1번 초기화
	
	for(var i = 0 ; i<commentsList.length; i++){
		
		$('#accordion').append(
			' <!-- ----------댓글----------- -->'+
			'<div class="card">'+
			'<div class="card-header bg-transparent" id="heading'+commentsList[i].cId+'">'+
			'<table><tr>'+
			'<td style="width:100px; text-align: center;"><b>'+commentsList[i].cName+'</b></td>'+
	      	'<td style="width:600px;">'+commentsList[i].cComment+'</td>'+
	      	'<td style="width:100px; text-align: center;">'+commentsList[i].cTime+'</td>'+
	      	'</tr></table>'+
	      	
	      	'<div>'+
	      	'<button class="btn btn-link collapsed" style="padding:0;" data-toggle="collapse"'+ 
	         'data-target="#collapse'+commentsList[i].cId+'" aria-expanded="false" aria-controls="collapse'+commentsList[i].cId+'">'+
	         ' <small>댓글 '+commentsList[i].cReplys+'개</small>'+
	         ' </button>'+
	         '<span style="float:right; width:20;">&nbsp</span>'+
	         '<span style="float:right"><a href="#"><small>수정</small></a>&nbsp'+
	         ' <a href="#"><small>X</small></span> </a>'+
	         ' </div></div>'+
	         
	         ' <!-- ----------대댓글----------- -->'+
	         '<div id="collapse'+commentsList[i].cId+'" class="collapse" aria-labelledby="heading'+commentsList[i].cId+'" data-parent="#accordion">'+
		     '<div class="card-body">'+
		     '<table id="recomentTable'+commentsList[i].cId+'">'+
		     ' <!-- ----------jquery로 append 추후붙임----------- -->'+
		     '</table>'+
		     
		     ' <!-- ----------대댓글쓰기----------- -->'+
		     '<br><form><div class="form-row">'+
	  		  '<div class="form-group col-md-6">'+
	  		  '  <label for="cName_re'+commentsList[i].cId+'">이름</label>'+
	  		  '  <input type="text" class="form-control" id="cName_re'+commentsList[i].cId+'">'+
	  		  '</div>'+
	  		  '<div class="form-group col-md-6">'+
	  		  '  <label for="cPw_re'+commentsList[i].cId+'">Password</label>'+
	  		  '  <input type="password" class="form-control" id="cPw_re'+commentsList[i].cId+'" placeholder="Password">'+
	  		  '</div>'+
	  		  '</div>'+
	  
	  		'<div class="form-group">'+
	  		'  <label for="cComment_re'+commentsList[i].cId+'">대댓글 작성</label>'+
	  		'  <textarea class="form-control" id="cComment_re'+commentsList[i].cId+'" rows="1"></textarea>'+
	  		'</div>'+
			
			 '</form><button type="button" class="btn btn-primary" onclick="writeReComment('+commentsList[i].cId+');">대댓글 등록</button>'+
			'</div></div></div>'
		);
		
	}
	for(var i = 0 ; i<commentsList.length; i++){ //대댓글 list
		for(var j=0;j<recommentsList.length;j++){
			if(commentsList[i].cId==recommentsList[j].cParentId){
				
				$('#recomentTable'+commentsList[i].cId).append(
					'<tr>'+
		      		'<td style="width:100px; text-align: center; border-bottom: 1px solid #d1d1d1;"><b>'+recommentsList[j].cName+'</b></td>'+
		      		'<td style="width:580px; border-bottom: 1px solid #d1d1d1;">'+recommentsList[j].cComment+'</td>'+
		      		'<td style="width:20px; text-align: center; border-bottom: 1px solid #d1d1d1;">'+
		      		'<span style="float:right; width:20;">&nbsp</span>'+
			         ' <a href="#"><small>X</small></span> </a>'+
			         '</td>'+
		      		'<td style="width:100px; text-align: center; border-bottom: 1px solid #d1d1d1;">'+recommentsList[j].cTime+'</td>'+
		      	   '</tr>'	
				);
			}
		}
	}
	
	$('#accordion').append(
	'<br><br><h5>댓글작성</h5><hr>'+
	'	<form onsubmit="return false;">'+
	'  <div class="form-row">'+
	'    <div class="form-group col-md-6">'+
	'      <label for="cName">이름</label>'+
	'      <input type="text" class="form-control" id="cName">'+
	'    </div>'+
	'    <div class="form-group col-md-6">'+
	'      <label for="cPw">Password</label>'+
	'      <input type="password" class="form-control" id="cPw" placeholder="Password">'+
	'    </div>'+
	'  </div>'+
	  
	'  <div class="form-group">'+
	'    <label for="cComment">댓글 입력</label>'+
	'    <textarea class="form-control" id="cComment" rows="3"></textarea>'+
	'  </div>'+
	'</form>'+
	'  <button type="button" onclick="writeComment();" class="btn btn-primary">댓글등록</button>'
	);
}//



function writeComment(){
	var cName=$('#cName').val();
	var cPw=$('#cPw').val();
	var cComment=$('#cComment').val();
	
	//fbId, cName, cPw, cComment, 
 	$.ajax({
	    url: "/onlyKeyboardShop/writeComment",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"fbId" : fbId,"cName":cName, "cPw":cPw, "cComment":cComment},
	    success: function(data){
	    	alert("댓글 작성완료");
	    	commentList();
	    	showComment();
	    },
	    error: function(data){
	    	alert("다시시도해주세요");
	    }
	}); 
}

function writeReComment(parrentId){
	var cParentId=parrentId; 
	var cName=$('#cName_re'+parrentId).val();
	var cPw=$('#cPw_re'+parrentId).val();
	var cComment=$('#cComment_re'+parrentId).val();
	
	// cParentId, fbId, cName, cPw, cComment,
	$.ajax({
	    url: "/onlyKeyboardShop/writeReComment",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"fbId" : fbId, "cParentId":cParentId,"cName":cName, "cPw":cPw, "cComment":cComment },
	    success: function(data){
	    	alert("대댓글 작성완료");
	    	commentList();
	    	showComment();
	    },
	    error: function(data){
	    	alert("다시시도해주세요");
	    }
	});
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


<div style="text-align: center; height:50px; line-height: 50px; font-size: large;"><b>댓글</b></div>
<hr style="border: 3px solid #5c6cd6;border-radius: 5px; margin:0;">


<!-- -------------------------댓글---------------------------- -->


	<div id="accordion">
<%-- 	<c:forEach items="${comments}" var="comments" varStatus="status">
	
	<div class="card">
    <div class="card-header bg-transparent" id="heading${comments.cId}">
      <table><tr>
      	<td style="width:100px;">${comments.cName}</td>
      	<td style="width:600px;">${comments.cComment}</td>
      	
      	<td style="width:100px; text-align: center;">${comments.cTime}</td>
      	
      </tr></table>
      <div style="margin : 10 auto;">${comments.cComment} ${comments.cTime}</div> 
      <div>
        <button class="btn btn-link collapsed" style="padding:0;" data-toggle="collapse" 
        data-target="#collapse${comments.cId}" aria-expanded="false" aria-controls="collapse${status.current}">
          	<small>댓글 ${comments.cReplys}개</small>
        </button>
      </div>
    </div>
    <div id="collapse${comments.cId}" class="collapse" aria-labelledby="heading${status.current}" data-parent="#accordion">
      <div class="card-body">
      <table>
      <!-- ----------대댓글----------- -->
      	<c:forEach items="${recomments}" var="recomments" >
      	<c:if test="${comments.cId == recomments.cParentId}">
      		<tr>
      			<td style="width:100px; text-align: center; border-bottom: 1px solid #d1d1d1;">${recomments.cName}</td>
      			<td style="width:600px; border-bottom: 1px solid #d1d1d1;">${recomments.cComment}</td>
      			<td style="width:100px; text-align: center; border-bottom: 1px solid #d1d1d1;">${recomments.cTime}</td>
      	   </tr>
      	   
      	</c:if>
      	</c:forEach>
      </table>
    
    <!-- ---------------대댓글작성--------------- -->
    <br>  
      <form>
  		<div class="form-row">
  		  <div class="form-group col-md-6">
  		    <label for="cName_re${comments.cId}">이름</label>
  		    <input type="text" class="form-control" id="cName_re${comments.cId}">
  		  </div>
  		  <div class="form-group col-md-6">
  		    <label for="cPw_re${comments.cId}">Password</label>
  		    <input type="password" class="form-control" id="cPw_re${comments.cId}" placeholder="Password">
  		  </div>
  		</div>
  
  		<div class="form-group">
  		  <label for="cComment_re${comments.cId}">대댓글 작성</label>
  		  <textarea class="form-control" id="cComment_re${comments.cId}" rows="3"></textarea>
  		</div>
		
		  <button type="submit" class="btn btn-primary">대댓글 등록</button>
		</form>
      
      
      </div>
    </div>
  	</div>
  
	</c:forEach>
	 --%>
	<!-- -------------------------댓글쓰기---------------------------- -->
	<br><br>
	<h5>댓글작성</h5>
	<hr>
	<form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="cName">이름</label>
      <input type="text" class="form-control" id="cName">
    </div>
    <div class="form-group col-md-6">
      <label for="cPw">Password</label>
      <input type="password" class="form-control" id="cPw" placeholder="Password">
    </div>
  </div>
  
  <div class="form-group">
    <label for="cComment">댓글 입력</label>
    <textarea class="form-control" id="cComment" rows="3"></textarea>
  </div>

  <button type="button" onclick="writeComment()" class="btn btn-primary">댓글등록</button>
</form>
	

</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>