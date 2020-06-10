<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.spring.webProject.dto.AdditionalPrice" %>
<%-- <%@ page import="java.util.*"%> --%>

<!DOCTYPE html>
<html>
<head>
<%!int deliveryPirce; %>
<%deliveryPirce = AdditionalPrice.deliveryPrice; %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script><meta charset="EUC-KR">
  
<title>Insert title here</title>


<sec:authorize access="isAnonymous()">
<script language=javaScript src="/onlyKeyboardShop/resources/js/productActionInvalid.js"></script>
</sec:authorize>
 

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
<script language=javaScript src="/onlyKeyboardShop/resources/js/productActionValid.js"></script>
</sec:authorize>


<script>
var originPrice = ${product.pPrice};
var deliveryPrice = <%=deliveryPirce%>;
var numofIndex=0;
var optionList=new Array(); //현재 선택된 옵션들의 "값"
var indexList=new Array(); //현재 선택된 옵션들의 "index"들

$(document).on("change", "#colorSelect", function(){ //옵션을 선택해야 결제진행
	
	if(optionList!=null){
	for( var i = 0 ; i < optionList.length ; i++){  //중복검사
		if($("#colorSelect option:selected").val() == optionList[i]){
			alert("이미 선택한 옵션입니다");
			return;
			}
		}
	}
	optionList.push($("#colorSelect option:selected").val());
	indexList.push(numofIndex);
	
	if($("#colorSelect option:selected").val()!="none"){
		//alert($("#colorSelect option:selected").val());
		$("#productDetails").append(
			"<li id='item"+numofIndex+"' value='"+$("#colorSelect option:selected").val()+"'>"+
			"<div style='float:left;'><b>${product.pName}</b><br>"+$("#colorSelect option:selected").val()+"</div>"+
			
			"<div style='float:right;  line-height: 50px'>"+
			"<span style='vertical-align: middle;' id='thisPrice"+numofIndex+"' value='"+originPrice+"'>"+originPrice+"원</span>	</div>"+
			
			"<div style='float:right;'>		&nbsp<button style='margin-top: 10px' id='removeSelected"+numofIndex+"' onclick='removeItem(this.id)' >x</button>&nbsp</div>"+
			
			"<div style='float:right;  line-height: 50px'>"+
			"<input type='number' id='numOf"+numofIndex+"' value='1' name='numOf' min='1' onchange='numChange(this.id)' style='width:60px; height:30px; vertical-align: middle; display:inline-block;'/></div>"+
			
			"<div style='height:50px'></div><hr>"+"</li>"																			
			
		);				
		numofIndex++;
		$("#colorSelect option:eq(0)").prop("selected", true);
		total();
		
	} 
});		

function removeItem(thisid){
	var id=thisid.substring(14);
	var itemId="item".concat(id); //<li>태그의 id
	
	var content = $("#"+itemId).attr('value'); //배열에서의 "값" 지우기
	const idx = optionList.indexOf(content);
	if (idx > -1) optionList.splice(idx, 1);
				
	content = id; //배열에서의 "인덱스" 지우기
				
	const idx2 = indexList.indexOf(Number(content));
	if (idx2 > -1) indexList.splice(idx2, 1);
			
	$("#colorSelect option:eq(0)").prop("selected", true);//selectbox 초기화
	$("#"+itemId).remove();//실제 요소지우기
	//numofIndex=Number(id);
	total();
}

function numChange(thisid){
	var id=thisid.substring(5);
	var priceId="thisPrice".concat(id);
	var Price= originPrice;//$("#"+priceId).attr('value');
	var num = $("#"+thisid).val();
	Price *= num; //개수를 올린만큼 가격도 곱해져서 출력하기
	$("#"+priceId).html(Price+"원");
	//출력만 하지말고 value 값도 바꿔줘야함.
	$("#"+priceId).attr('value',Price);
	total();
}

function total(){
	var num=0;
	Number(num);
	for (var i=0;i < indexList.length ; i++){ 
		num = Number(num) + Number($('#numOf'+indexList[i]).val());
	}
	
	var totalPrice=0;
	Number(totalPrice);
	for (var i=0;i < indexList.length ; i++){
		totalPrice = Number(totalPrice) + Number($('#thisPrice'+indexList[i]).attr('value')); 
	}
	if(totalPrice!=0)
		totalPrice+=deliveryPrice;
	
	$("#totalPrice").html(totalPrice+"원");
	$("#totalNum").html(num+"개");
}

//장바구니를 누르면 세션으로.. 일단 되었다..
$(document).on("click", "#basket", function(){
	
	//JSON을 이용해 String 형식으로 만들어 SessionStorage에 저장
	//sessionStorage.setItem("orderDetail", JSON.stringify(orderDetail));
	//SessionStorage에서 가져와 JSON을 통해 변환
	//var orderDetail = JSON.parse(sessionStorage.getItem("orderDetail"));
	if (optionList[0] == null){
		alert("옵션을 선택해주세요");
		return;
	}
	
	for (var i=0;i < indexList.length ; i++){ //맨처음 눌렀을때
		
		if(sessionStorage.getItem("basketItems")==null){
			var items = [];
			
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
					numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
				items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
			for(var j=0;j<items.length;j++){
				if( items[j].name=="${product.pName}" && items[j].color== $('#item'+indexList[i]).attr('value')){
					items[j].numOf = Number(items[i].numOf) + Number($('#numOf'+indexList[i]).val()); //개수더해주고
					items[j].price = Number(items[i].price) + Number($('#thisPrice'+indexList[i]).attr('value'));
					sessionStorage.setItem("basketItems", JSON.stringify(items));		
					$("#myModal").modal();
					return;
				}
			}
		
			//이름, 이미지, 색상정보, 개수, 가격을 담은 객체
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
				numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		}
		
		
	}
	$("#myModal").modal();
});

function gotoBasket(){
	 location.href="/onlyKeyboardShop/basket";
}

function formLoginCheck(){//확인후 member/*로 이동시키면 됨.
	
	if (optionList[0] == null){
		alert("옵션을 선택해주세요");
		return false;
	}
	else if("${currentUserName}"==null){
		alert("로그인이 필요합니다")
		return false;
	}
	
	for (var i=0;i < indexList.length ; i++){ //장바구니가 빈 상태였을때
		
		if(sessionStorage.getItem("basketItems")==null){
			var items = [];
			
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
					numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
				items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
			for(var j=0;j<items.length;j++){
				if( items[j].name=="${product.pName}" && items[j].color== $('#item'+indexList[i]).attr('value')){
					items[j].numOf = Number(items[i].numOf) + Number($('#numOf'+indexList[i]).val()); //개수더해주고
					items[j].price = Number(items[i].price) + Number($('#thisPrice'+indexList[i]).attr('value'));
					sessionStorage.setItem("basketItems", JSON.stringify(items));		
					$("#myModal").modal();
					return;
				}
			}
		
			//이름, 이미지, 색상정보, 개수, 가격을 담은 객체
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
				numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		}
		
		
	}
		
	return true;
};

/* var sessionData = "mineItRecord";
sessionStorage.setItem("mineSession", sessionData ); // 저장
sessionStorage.getItem("mineSession"); // mineItRecord
sessionStorage.length; // 1
sessionStorage.key(0); // mineItRecord
sessionStorage.removeItem("mineSession"); // 삭제
sessionStorage.clear(); // 전체삭제 */

//JSON을 이용해 String 형식으로 만들어 SessionStorage에 저장
//sessionStorage.setItem("orderDetail", JSON.stringify(orderDetail));
//SessionStorage에서 가져와 JSON을 통해 변환
//var orderDetail = JSON.parse(sessionStorage.getItem("orderDetail"));

</script>
	
</head>

<body>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>

<br>
<h1 align="center">${product.pName}</h1>
<br><br><br>
<div style="clear:both;"></div>

<!-- <button type="button" class="btn btn-info btn-lg" id="myBtn">Open Modal</button>

<script>
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});
</script> -->

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
<div style="display:table-cell; width:100%; text-align: center; "><!-- vertical-align: middle; -->
	<div style="width:400px; display:inline-block; margin-top: 0; vertical-align: top;"> <!-- 왼쪽이미지 -->
		<img src="${product.pImageRoute}" width="100%" style="vertical-align: top;" /> 
	</div>
	<div style="width:100px; height:10px; display:inline-block;"></div>

	<div style="width:400px; display:inline-block;text-align:left; "> <!-- 오른쪽 정보 -->
	
	<button class="btn btn-light" id="bookMark" value="${currentUserName}"> 북마크하기 </button><small style='vertical-align: bottom; color:gray;'>(로그인필요)</small> <!-- username은 id-->
	<br><br>
	<form action="member/buyPage"  method="post" id="buyForm" onsubmit="return formLoginCheck()"> <!-- hidden으로 user id도 보내야함. -->
		<input type="hidden" id="pId" name="pId" value="${product.pId}"/>
		<h4 id="pName" value="${product.pName}">${product.pName}</h4>
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
		<input type="submit" value="구매하기" class="btn btn-light" />
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
	  <div class="tab-pane fade show active" id="review">
	    <p>후기 게시판입니다. 별점을 매길 수 있습니다.</p>
	  </div>
	  <div class="tab-pane fade" id="qna">
	    <p>큐앤에이 게시판입니다. 질문을 할 수 있습니다.</p>
	  </div>
	</div>
	
	</div>
	</div>
	</div>
	
	
	<br><br><br>
		
	<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
	
</body>
</html>