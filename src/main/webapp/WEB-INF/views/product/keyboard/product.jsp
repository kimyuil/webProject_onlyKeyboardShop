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

</script>

<script src="/onlyKeyboardShop/resources/js/productOptionSelectAction.js"></script>
<script src="/onlyKeyboardShop/resources/js/productBasketPurchaseAction.js"></script>
<script src="/onlyKeyboardShop/resources/js/productReviewAction.js"></script>
<script src="/onlyKeyboardShop/resources/js/productQnaAction.js"></script>
	
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