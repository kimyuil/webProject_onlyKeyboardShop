<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.spring.webProject.dto.AdditionalPrice" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<%!int deliveryPirce; %>
<%deliveryPirce = AdditionalPrice.deliveryPrice; %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script><meta charset="EUC-KR">
  
<title>Insert title here</title>
 

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/>
<sec:authentication property="principal.uName" var="uName"/> 
</sec:authorize>



<script>
//장바구니 선택시 사용될 변수들 (basketAction.js)
var numofIndex=0;
var optionList=new Array(); //현재 선택된 옵션들의 "값" ex)블랙, 화이트 등
var indexList=new Array(); //현재 선택된 옵션들의 "index"들

var currentUserName = "${currentUserName}" //로그인 되있을때 id값

//product info
var deliveryPrice = <%=deliveryPirce%>;
var originPrice = ${product.pPrice};
var stock = "${product.pStock}";
var pid = "${product.pId}";
var pName = "${product.pName}";
var pImageRoute ="${product.pImageRoute}";

//review table용 
var reviewsList = new Array();
var pageInfo;

//q&a table 용
var qnaList = new Array();
var QpageInfo;

$(document).ready(function(){
	if(Number(stock)<=0){
		$('#basket').attr('disabled',true);
		$('#buyButton').attr('disabled',true);
		$('#isSoldout').append("<span style='font-weight: bold;	color: red;	font-size: x-large;'>품절입니다</span>");
	}
	else{
		$('#isSoldout').append("<span style='font-size: large;'>남은 재고 : "+stock+"</span>");
	}
	//1page의 list를 불러옴
	
	reviewList(1);//처음에 1페이지
	showReviewList();
	
	QNAList(1); //처음에 1페이지
	showQNAList();
})


function showQNAList(){ //후기 게시판 뿌려주는 메소드
	$('#qnaTable').html(
			'<tr>'+
			'<td style="width:50px;text-align: center; background-color:#dedede" ><b>번호</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>작성자</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>제목</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>답변</b></td>'+
		'</tr>');
	
	var lastNum=0;
	if(QpageInfo.currentPageLastNum==QpageInfo.lastPageNum){
		lastNum=Number(QpageInfo.currentPageLastNum)-1;
	}
	else{
		lastNum = QpageInfo.currentPageLastNum;
	}
	
	for(var i=QpageInfo.currentPageFirstNum ; i<=lastNum ;i++){ //list 페이지 내용
		if(qnaList[i].isSecret==1 && currentUserName!=qnaList[i].uId){
			$('#qnaTable').append("<tr class='tr"+i+"'>"+
		    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
		    		"<td style='text-align: center;'>비공개</td>"+
		    		"<td>비공개입니다</td>"+
		    		"<td style='text-align: center;'>"+qnaList[i].qnaDate +"</td>"+
		    		"<td style='text-align: center;'>"+qnaList[i].isAnswered +"</td></tr>"
		    	);
			continue;
		}
		
    	$('#qnaTable').append("<tr class='tr"+i+"'>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'>"+qnaList[i].uName+"</td>"+
    		"<td><a href='javascript:void(0)' onclick='showContent("+i+")'>"+qnaList[i].qnaTitle+"</a></td>"+
    		"<td style='text-align: center;'>"+qnaList[i].qnaDate +"</td>"+
    		"<td style='text-align: center;'>"+qnaList[i].isAnswered +"</td></tr>"
    	);
   }
 
	 var pageBlock = "";
	 for(var j = QpageInfo.blockStartNum; j <=QpageInfo.blockLastNum; j++){
		 pageBlock = pageBlock + "<a href='#qna' onclick='renewQPage("+Number(j)+")'>&nbsp"+j+"&nbsp</a>"; 
	 }
	 
	 $('#qnaTable').append("<tr><td  colspan='5' align='center' style='background-color:#e6e6e6'>"+
			 (QpageInfo.blockStartNum != 1 ? "<a href='#qna' onclick='renewQPage("+(Number(QpageInfo.blockStartNum)-1)+")'>이전&nbsp</a>": "")+
			 pageBlock+
			 (QpageInfo.blockLastNum != QpageInfo.realLastBlockNum ? "<a href='#qna' onclick='renewQPage("+(Number(QpageInfo.blockLastNum)+1)+")'>&nbsp다음</a>":"")+
	 " </td></tr>"); 
 
}

function renewQPage(page){ //이후 페이지 넘길때 페이지정보만! 가져온후 showQNAList 호출.
	$.ajax({
	    url: "/onlyKeyboardShop/qnaList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"qnaPage" : page, "pId" : pid},
	    success: function(data){
	    	
	    	QpageInfo={blockStartNum:data.QpageInfo.blockStartNum, blockLastNum:data.QpageInfo.blockLastNum,
	    			lastPageNum:data.QpageInfo.lastPageNum, realLastBlockNum:data.QpageInfo.realLastBlockNum,
	    			currentPage:data.QpageInfo.currentPage, currentPageFirstNum:data.QpageInfo.currentPageFirstNum,
	    			currentPageLastNum:data.QpageInfo.currentPageLastNum};
	      	
	    	showQNAList();
	    },
	    
	    error: function (request, status, error){   
	    	alert("정보를 불러오는데 실패했습니다. 관리자에게 문의해주세요.");              
	    }
	  });
}

function QNAList(page){ //QNA 게시판 리스트와 초기 페이지정보를 저장해두기 처음한번 실행
	$.ajax({
	    url: "/onlyKeyboardShop/qnaList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"qnaPage" : page, "pId" : pid},
	    success: function(data){
	    	
	    	//후기게시판, 페이징정보 가져오기
	    	for(var i =0;i<data.qnas.length;i++){
	    		
	    		var isSecret=false;
	    		var isAnswered="답변대기중";
	    		if (data.qnas[i].isSecret==1) isSecret=true;
	    		if (data.qnas[i].isAnswered==1) isAnswered="<b>답변완료</b>";
	    		
	    		var date = new Date(data.qnas[i].qnaDate);
	    		var dateString = date_to_str(date);
	    		
	    		var item = {qnaId:data.qnas[i].qnaId, uId:data.qnas[i].uId, 
	    		pId:data.qnas[i].pId, pName:data.qnas[i].pName, qnaTitle:data.qnas[i].qnaTitle, 
	    		qnaContent:data.qnas[i].qnaContent,	qnaDate:dateString,  
	    		isSecret:isSecret, isAnswered: isAnswered,
	    		qnaAnswer:data.qnas[i].qnaAnswer, uName:data.qnas[i].uName};
	    		
	    		qnaList.push(item);
	    	};
	    	QpageInfo={blockStartNum:data.QpageInfo.blockStartNum, blockLastNum:data.QpageInfo.blockLastNum,
	    			lastPageNum:data.QpageInfo.lastPageNum, realLastBlockNum:data.QpageInfo.realLastBlockNum,
	    			currentPage:data.QpageInfo.currentPage, currentPageFirstNum:data.QpageInfo.currentPageFirstNum,
	    			currentPageLastNum:data.QpageInfo.currentPageLastNum};
	    },
	    
	    
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
	return year + "-" + month + "-" + date; // + " " + hour + ":" + min + ":" + sec
}

function showContent(id){ //toggle content view
	
	if($('.tr'+id).attr('content')==undefined){
		if(qnaList[id].isAnswered=="<b>답변완료</b>"){ //답변이 달렸을때
			$('.tr'+id).after("<tr><td colspan='5' style='height:100px;'>"+qnaList[id].qnaContent+
			"<br><br>"+qnaList[id].qnaAnswer+"</td></tr>");
		} 
		else{ 
			$('.tr'+id).after(
				"<tr><td colspan='5' style='height:100px;'>"+
				qnaList[id].qnaContent+
				"<br><br><div align='right'>"+
				"<button class='btn btn-dark btn-sm' onclick='modifyQnA("+id+")'>수정</button> "+
				"<button class='btn btn-dark btn-sm' onclick='deleteQnA("+id+")'>삭제</button></div>"+
				"</td></tr>");
		}
		$('.tr'+id).attr('content',"true");
	}	
	else if($('.tr'+id).attr('content')=="true"){
		
		$('.tr'+id).next('tr').hide();
		$('.tr'+id).attr('content',"false");
	}
	else if($('.tr'+id).attr('content')=="false") {
		
		$('.tr'+id).next('tr').show();
		$('.tr'+id).attr('content',"true");
	} 
	
}

function modifyQnA(id){
	if(qnaList[id].uId!=currentUserName){
		alert("본인만 수정이 가능합니다");
		return;
	}
	$('#QnaModifyView').append(
			"<input type='hidden' name='qnaId' value='"+qnaList[id].qnaId+"'/>"+
			"<input type='hidden' name='pId' value='"+qnaList[id].pId+"'/>"+
			"<input type='hidden' name='pName' value='"+qnaList[id].pName+"'/>"+
			"<input type='hidden' name='qnaTitle' value='"+qnaList[id].qnaTitle+"'/>"+
			"<input type='hidden' name='qnaContent' value='"+qnaList[id].qnaContent+"'/>"+
			"<input type='hidden' name='isSecret' value='"+qnaList[id].isSecret+"'/>"
			);
	$('#QnaModifyView').submit();
}

function deleteQnA(id){
	if(qnaList[id].uId!=currentUserName){
		alert("본인만 삭제가 가능합니다");
		return;
	}
	var check = confirm("정말삭제하시겠습니까?");
	if(check == false)
		return;
	$('#DeleteQna').append(
			"<input type='hidden' name='qnaId' value='"+qnaList[id].qnaId+"'/>"+
			"<input type='hidden' name='pId' value='"+qnaList[id].pId+"'/>"
			);
	$('#DeleteQna').submit();
}

</script>

<script src="/onlyKeyboardShop/resources/js/optionSelectAction.js"></script>
<script src="/onlyKeyboardShop/resources/js/basketPurchaseAction.js"></script>
<script src="/onlyKeyboardShop/resources/js/reviewAction.js"></script>
	
</head>

<body>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>

<br>
<h1 align="center">${product.pName}</h1>
<br><br><br>
<div style="clear:both; "></div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">장바구니</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
      		<br><br>
      		<h3 align="center">장바구니에 담겼습니다</h3>
      		<br><br>
      </div>
       <div class="modal-footer">
       	<button type="button" class="btn btn-default" data-dismiss="modal" onclick="gotoBasket()">장바구니 이동</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">계속 쇼핑하기</button>
      </div>
     </div>
  </div>
</div>


<div style="display:table; width:100%; ">
<div style="display:table-cell; width:100%; text-align: center; ">
	<div style="width:400px; display:inline-block; margin-top: 0; vertical-align: top;"> <!-- 왼쪽이미지 -->
		<img src="${product.pImageRoute}" width="100%" style="vertical-align: top;" /> 
	</div>
	<div style="width:100px; height:10px; display:inline-block;"></div>

	<div style="width:400px; display:inline-block;text-align:left; "> <!-- 오른쪽 정보 -->
	
	
	<form action="member/buyPage"  method="post" id="buyForm" onsubmit="return formLoginCheck()"> <!-- hidden으로 user id도 보내야함. -->
		<input type="hidden" id="pId" name="pId" value="${product.pId}"/>
		<h4 id="pName" value="${product.pName}" style="display: inline;">${product.pName}</h4>
		&nbsp&nbsp&nbsp
		<span id="isSoldout"></span>
		<p>category : ${product.pCategory}</p>
		<p>${product.pBreifComment}</p>
		가격 : <b>${product.pPrice}</b> <br> 
		배송비 : <b><%=deliveryPirce%></b>  <br>
		색상 : <select name="color" id="colorSelect">
				<option id="colorOption" value="none">[필수] 색상을 선택해주세요</option>
				<option id="colorOption" value="none">----------</option>
				<c:forEach items="${colorsArray}" var="colors">
				<option id="pColor" value="${colors}">${colors}</option>
				</c:forEach>
			</select> <br>
		
		<script> //상품 세부정보
		
		
		</script>
		<hr>
			<ul id="productDetails" style="list-style-type : none; padding-left: 0;">
			</ul>  <!-- js로 데이터 받아옴 -->
		<br>
		<br>
		<div>주문 총 가격 : <span id="totalPrice" style="font-weight:bold; font-size: 25px;">0원</span>(<span id="totalNum">0개</span>)</div>
		
		<br>
		<div style="float:left;">
		<input type="submit" value="구매하기" id="buyButton" class="btn btn-light" />
		</div>
	</form>
		<div style="float:left; width:10px;">&nbsp</div>
		<div style="float:left;">	
		<button class="btn btn-light" id="basket">장바구니</button> <!-- user정보변경 / 로긴체크js-->
		</div>
		
	
	</div>
	</div>
	</div>
		<div style="clear:both;"></div>
		<br><br>
	<!-- 상세정보 탭 -->
	<div align="center">
		<div style="width:50%; margin:0 auto; background-color:#ebebeb; height:60px; line-height:60px;font-size:large; ">
		상세정보</div>
	
		<img src="${product.pExplainImageRoute}" style="width:65%;"/>
	</div>
	
	<div style="width:40%; margin:0 auto;">
	<p>
		상세설명 : ${product.pInformation} 
		색상 : <c:forEach items="${colorsArray}" var="colors">${colors}</c:forEach>
	</p>
	</div>
	
	
	<br><br><br>
	
<div class="container">
<div class="row">
<div class="col">
	
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" data-toggle="tab" href="#review">후기 게시판</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#qna">Q&A 게시판</a>
	  </li>
	</ul>
	<div class="tab-content">
	  <div class="tab-pane fade show active" id="review"> <!-- 후기게시판 -->
	  <br>
	    <h3 align="center">REVIEW</h3>
	  <br>  
	    <table width="850" cellpadding="5" cellspacing="0" border="1" style="margin:0 auto;" id="reviewTable" >
		</table>
		<br>
		<h5 align="center">상품을 구매 후 솔직한 후기를 작성해주세요!</h5>
		<small align="center">mypage -> 구매목록 페이지에서 작성할 수 있습니다</small>
	  </div>

	  <div class="tab-pane fade" id="qna"> <!-- Q&A게시판 -->
	  
	  <br>
	    <h3 align="center">Q & A</h3>
	  <br>  
	    <table width="850" cellpadding="5" cellspacing="0" border="1" style="margin:0 auto;" id="qnaTable">
		</table>
		<br>
		<h5 align="center">문의사항을 친절히 답변해 드립니다. <a href='#qna' onclick='writeQnA()'><b>문의하기</b></a></h5>
		  <form id="QnaWriteView" method="post" action="member/QnaWriteView">
		  <input type="hidden" name="pId" value="${product.pId}"/>
		  <input type="hidden" name="pName" value="${product.pName}"/>
		  <input type="hidden" name="uId" value="${currentUserName}"/>
		  <input type="hidden" name="uName" value="${uName}"/>
		  </form>
		  <script>function writeQnA(){$('#QnaWriteView').submit();}</script>
		<form id="QnaModifyView" method="post" action="member/QnaModifyView"></form>
		<form id="DeleteQna" method="post" action="/onlyKeyboardShop/deleteQna"></form>
	  </div>
	</div>
	
</div>
</div>
</div>
	
	
	<br><br><br>
		
	<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
	
</body>
</html>