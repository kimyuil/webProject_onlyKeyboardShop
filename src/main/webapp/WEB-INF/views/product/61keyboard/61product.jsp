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


<sec:authorize access="isAnonymous()">
<script language=javaScript src="/onlyKeyboardShop/resources/js/productActionInvalid.js"></script>
</sec:authorize>
 

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="currentUserName"/> 
<script language=javaScript src="/onlyKeyboardShop/resources/js/productActionValid.js"></script>
</sec:authorize>


<script>
//
var originPrice = ${product.pPrice};
var deliveryPrice = <%=deliveryPirce%>;
var numofIndex=0;
var optionList=new Array(); //현재 선택된 옵션들의 "값"
var indexList=new Array(); //현재 선택된 옵션들의 "index"들
var stock = "${product.pStock}";

var reviewsList = new Array();
var pageInfo;

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
	 
})


function showReviewList(){ //실질적인 출력 담당
	$('#reviewTable').html(
			'<tr>'+
			'<td style="width:50px;text-align: center; background-color:#dedede" ><b>번호</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>작성자</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>내용</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:80px; text-align: center;background-color:#dedede"><b>별점</b></td>'+
		'</tr>');
	
	var lastNum=0;
	if(pageInfo.currentPageLastNum==pageInfo.lastPageNum){
		lastNum=Number(pageInfo.currentPageLastNum)-1;
	}
	else{
		lastNum = pageInfo.currentPageLastNum;
	}
	for(var i=pageInfo.currentPageFirstNum ; i<=lastNum ;i++){ //list 페이지 내용
    	$('#reviewTable').append("<tr>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'>"+reviewsList[i].uName+"</td>"+
    		"<td>"+reviewsList[i].reContent+"</td>"+
    		"<td style='text-align: center;'>"+reviewsList[i].reDate +"</td>"+
    		"<td style='text-align: center;'><b>"+reviewsList[i].reGrade +"</b></td>"
    	);
   }
 
	 var pageBlock = "";
	 for(var j = pageInfo.blockStartNum; j <=pageInfo.blockLastNum; j++){
		 pageBlock = pageBlock + "<a href='#review' onclick='renewPage("+Number(j)+")'>&nbsp"+j+"&nbsp</a>"; 
	 }
	 
	 $('#reviewTable').append("<tr><td  colspan='5' align='center' style='background-color:#e6e6e6'>"+
			 (pageInfo.blockStartNum != 1 ? "<a href='#review' onclick='renewPage("+(Number(pageInfo.blockStartNum)-1)+")'>이전&nbsp</a>": "")+
			 pageBlock+
			 (pageInfo.blockLastNum != pageInfo.realLastBlockNum ? "<a href='#review' onclick='renewPage("+(Number(pageInfo.blockLastNum)+1)+")'>&nbsp다음</a>":"")+
	 " </td></tr>");
 
}
	
function renewPage(page){ //이후 페이지 넘길때 페이지정보만! 가져온후 showReviewList 호출.
	$.ajax({
	    url: "/onlyKeyboardShop/reviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"reviewPage" : page, "pId" : "${product.pId}"},
	    success: function(data){
	    	
	    	pageInfo={blockStartNum:data.pageInfo.blockStartNum, blockLastNum:data.pageInfo.blockLastNum,
	    			lastPageNum:data.pageInfo.lastPageNum, realLastBlockNum:data.pageInfo.realLastBlockNum,
	    			currentPage:data.pageInfo.currentPage, currentPageFirstNum:data.pageInfo.currentPageFirstNum,
	    			currentPageLastNum:data.pageInfo.currentPageLastNum};
	      	
	      	showReviewList();
	    },
	    
	    error: function (request, status, error){   
	    	alert("정보를 불러오는데 실패했습니다. 관리자에게 문의해주세요.");              
	    }
	  });
	
}

function reviewList(page){ //초기 리스트와 초기 페이지정보를 저장해두기
	$.ajax({
	    url: "/onlyKeyboardShop/reviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"reviewPage" : page, "pId" : "${product.pId}"},
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
	    	pageInfo={blockStartNum:data.pageInfo.blockStartNum, blockLastNum:data.pageInfo.blockLastNum,
	    			lastPageNum:data.pageInfo.lastPageNum, realLastBlockNum:data.pageInfo.realLastBlockNum,
	    			currentPage:data.pageInfo.currentPage, currentPageFirstNum:data.pageInfo.currentPageFirstNum,
	    			currentPageLastNum:data.pageInfo.currentPageLastNum};
	      	//alert(JSON.stringify(pageInfo));
	    },
	    
	    
	  });
}


$(document).on("change", "#colorSelect", function(){ //옵션을 선택해야 결제진행
	
	if(optionList!=null){
	for( var i = 0 ; i < optionList.length ; i++){  //중복검사
		if($("#colorSelect option:selected").val() == optionList[i]){
			alert("이미 선택한 옵션입니다");
			return;
			}
		}
	}
	
	if($("#colorSelect option:selected").val()!="none"){
		
		optionList.push($("#colorSelect option:selected").val());
		indexList.push(numofIndex);
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
	
	
	if(Number(num)<=Number(stock)){
		$("#totalNum").html(num+"개");
		$("#totalPrice").html(totalPrice+"원");
	}else{
		$("#totalNum").html("<span style='color:red;'>"+num+"개 (재고 초과)</span>");
		$("#totalPrice").html("<span style='color:red;'>"+totalPrice+"원</span>");
	}
}

//장바구니 로직.
$(document).on("click", "#basket", function(){
	
	if (optionList[0] == null){ //옵션체크
		alert("옵션을 선택해주세요");
		return;
	}
	
	//수량체크
	var num=0; //현재 구매페이지의 신청물량
	var bascketNum=0; //장바구니에 담겨있는 물건의 물량
	if(sessionStorage.getItem("basketItems")==null){ //처음 장바구니에 담을때
		for (var i=0;i < indexList.length ; i++){ 
			num = Number(num)+Number($('#numOf'+indexList[i]).val());
		}
		if(Number(num)>Number(stock)){
			alert("재고 이상을 구매할 수 없습니다");
			return;
		}
	}
	else{//전에 담아둔 장바구니가 있을때
		var checkItems = JSON.parse(sessionStorage.getItem("basketItems"));
		
		for(var i = 0 ; i < checkItems.length;i++){ //장바구니에 현 아이템이 몇개 담겼는지
			if(checkItems[i].pId=="${product.pId}")
				bascketNum = Number(bascketNum)+Number(checkItems[i].numOf);
		}
		for (var i=0;i < indexList.length ; i++){ //신청페이지에 담고자했던 아이템은 몇개인지
			num = Number(num)+Number($('#numOf'+indexList[i]).val());
		}
		
		
		if( Number(bascketNum)+Number(num) >Number(stock)){
			alert("재고 이상을 구매할 수 없습니다. 장바구니를 확인해주세요");
			return;
		}
	}
	
	
	
	for (var i=0;i < indexList.length ; i++){ //세션에  품목넣기시작
		
		if(sessionStorage.getItem("basketItems")==null){//맨처음 장바구니를 눌렀떄
			
			var items = [];
			
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
					numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
			
						
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
			var flag=false;
			for(var j=0;j<items.length;j++){ //중복검사. 같으면 개수를 더해주기
				if( items[j].pId=="${product.pId}" && items[j].color== $('#item'+indexList[i]).attr('value')){
					items[j].numOf = Number(items[i].numOf) + Number($('#numOf'+indexList[i]).val()); //개수더해주고
					items[j].price = Number(items[i].price) + Number($('#thisPrice'+indexList[i]).attr('value'));
					sessionStorage.setItem("basketItems", JSON.stringify(items));		
					flag=true;
					//return;
				}
			}
			if(flag==true){
				continue;
			}
			
			//이름, 이미지, 색상정보, 개수, 가격을 담은 객체
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
				numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			
			items.push(item);
			sessionStorage.setItem("basketItems", JSON.stringify(items));
			
		}
	}
	$("#myModal").modal(); //장바구니 확인창
});

function gotoBasket(){
	 location.href="/onlyKeyboardShop/basket";
}

function formLoginCheck(){//확인후 member/*로 이동시키면 됨.
	
	if (optionList[0] == null){ //옵션체크
		alert("옵션을 선택해주세요");
		return false;
	}
	
	
	//수량체크
	var num=0; //현재 구매페이지의 신청물량
	var bascketNum=0; //장바구니에 담겨있는 물건의 물량
	if(sessionStorage.getItem("basketItems")==null){ //처음 장바구니에 담을때
		for (var i=0;i < indexList.length ; i++){ 
			num = Number(num)+Number($('#numOf'+indexList[i]).val());
		}
		if(Number(num)>Number(stock)){
			alert("재고 이상을 구매할 수 없습니다");
			return false;
		}
	}
	else{//전에 담아둔 장바구니가 있을때
		var checkItems = JSON.parse(sessionStorage.getItem("basketItems"));
		
		for(var i = 0 ; i < checkItems.length;i++){ //장바구니에 현 아이템이 몇개 담겼는지
			if(checkItems[i].pId=="${product.pId}")
				bascketNum = Number(bascketNum)+Number(checkItems[i].numOf);
		}
		for (var i=0;i < indexList.length ; i++){ //신청페이지에 담고자했던 아이템은 몇개인지
			num = Number(num)+Number($('#numOf'+indexList[i]).val());
		}
		
		
		if( Number(bascketNum)+Number(num) >Number(stock)){
			alert("재고 이상을 구매할 수 없습니다. 장바구니를 확인해주세요");
			return false;
		}
	}
	
	
	if("${currentUserName}"==null){ //로그인체크
		alert("로그인이 필요합니다")
		return false;
	}
	
	
	for (var i=0;i < indexList.length ; i++){ 
		if(sessionStorage.getItem("basketItems")==null){//장바구니가 빈 상태였을때
			
			var items = [];
			
			var item = {pId:"${product.pId}",name:"${product.pName}",image:"${product.pImageRoute}",color:$('#item'+indexList[i]).attr('value'),
					numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
				items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
			
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
		var flag=false;
			for(var j=0;j<items.length;j++){
								
				if( items[j].pId=="${product.pId}" && items[j].color== $('#item'+indexList[i]).attr('value')){
					//alert("check 구매하기form내부임")
					items[j].numOf = Number(items[j].numOf) + Number($('#numOf'+indexList[i]).val()); //개수더해주고
					items[j].price = Number(items[j].price) + Number($('#thisPrice'+indexList[i]).attr('value'));
					sessionStorage.setItem("basketItems", JSON.stringify(items));		
					flag=true;
				}
			}
			if(flag==true){
				continue;
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
	
	<button class="btn btn-light" id="bookMark" value="${currentUserName}"> 북마크하기 </button><small style='vertical-align: bottom; color:gray;'>(로그인필요)</small> <!-- username은 id-->
	<br><br>
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
	    <table width="850" cellpadding="0" cellspacing="0" border="1" style="margin:0 auto;" id="reviewTable">
		</table>
		<br>
		<h5 align="center">상품을 구매 후 솔직한 후기를 작성해주세요!</h5>
	  </div>

	  <div class="tab-pane fade" id="qna"> <!-- Q&A게시판 -->
	  
	  <br>
	    <h3 align="center">Q & A</h3>
	  <br>  
	    <table width="850" cellpadding="0" cellspacing="0" border="1" style="margin:0 auto;" id="qnaTable">
		</table>
		<br>
		<h5 align="center">문의사항을 친절히 답변해 드립니다</h5>  
	    
	  </div>
	</div>
	
</div>
</div>
</div>
	
	
	<br><br><br>
		
	<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
	
</body>
</html>