<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.spring.webProject.dto.AdditionalPrice" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="user"/>
</sec:authorize>


<script>
$(document).ready(function(){
	var phone = "${user.uPhone}"
	var phone01 = phone.substr(0,3);
	var phone02 = phone.substr(3,4);
	var phone03 = phone.substr(7,4);
	
	<%!int deliveryPirce; %>
	<%deliveryPirce = AdditionalPrice.deliveryPrice; %>
	var deliveryPrice = <%=deliveryPirce%>;
	var sumPrice=0;
	var items =JSON.parse(sessionStorage.getItem("basketItems"));
	if(items[0] ==null){ //하긴 했지만 실행되지 않을 영역
		$('#submitButton').attr('disabled',true);
	}
	else{
		//추가된 상품을 보이기.
		for(var i=0;i<items.length;i++){
			var pId = items[i].pId;
			var name = items[i].name;
			var image = items[i].image;
			var color = items[i].color;
			var numOf = items[i].numOf;
			var price = items[i].price;
			sumPrice = Number(sumPrice) + Number(price);
			$('#basketContent').append(
					"<div style='text-align:center; width:65%; margin:0 auto;'>"+
					"<div style='width:150px; display:inline-block;'>"+
						"<img src='"+image+"' width='100%'/>"+
					"</div>"+
					"<div style='width:100px; display:inline-block;'></div>"+

					"<div style='width:60%; display:inline-block; text-align:left;'>"+
					
						"<div style='float:left;'>"+
						"<h4>"+name+"</h4>"+
						"<h5>"+color+"</h5></div>"+
						
						"<div style='float:right;'>"+
						"<h4>"+price+"원</h4></div>"+
						"<div style='float:right; width:20px;'><h1>&nbsp</h1></div>"+
						"<div style='float:right;'>"+
						"<h5>"+numOf+"개</h5></div>"+
						"</div>"+
						"<hr>"+
					"</div>"+
				"<div style='clear:both;'></div>"
			); 
		}
		//sumPrice도 넣어주고
		//배송비도 붙여주고
		//더한 것도 붙여주기
		$('#sumPrice').html(sumPrice+'원');
		$('#deliverPrice').html(deliveryPrice+'원');
		var totalPrice = Number(sumPrice)+Number(deliveryPrice);
		$('#totalPrice').html('<b>'+totalPrice+'원</b>');
		$('#uPhone1').attr('value',phone01);
		$('#uPhone2').attr('value',phone02);
		$('#uPhone3').attr('value',phone03);
	}
});

function removeBasket(nameColor){ //no use
	var items =JSON.parse(sessionStorage.getItem("basketItems"));
	var str = nameColor.split('And');
	var name=str[0]; 
	var color=str[1];
	
	for(var i=0;i<items.length;i++){
		if(items[i].name == name && items[i].color == color){
			items.splice(i,1);
			sessionStorage.setItem("basketItems", JSON.stringify(items));
			history.go(0);
	
			return;
		}
			//remove and pagereload and return
	}
}

function removeAll(){
	sessionStorage.removeItem("basketItems"); 
	alert("구매가 취소되었습니다");
	location.href="/onlyKeyboardShop/home";
}

function moveFocus(num,fromform,toform){
	  var str = fromform.value.length;
	  if(str == num) {
	    toform.focus();
	  }   
}

$(document).on("click", "#userCheckButton", function(){
	
	var temp = "uId=${user.uId}&uPw=";
	var uPw = document.getElementById("uPw");
	
	var query = temp.concat(uPw.value);
	
	
const xhr = new XMLHttpRequest();
	
	xhr.open('POST','/onlyKeyboardShop/userCheck'); //여기는 member/가 붙어서 이렇게..
	
	xhr.onreadystatechange=function(){
	
		if(xhr.responseText === 'success'){
			document.querySelector("#result").style.color = "#0377fc";
			document.querySelector('#result').innerHTML ="인증되었습니다";
			sessionStorage.setItem("purchaseUserCheck","ok");
		}
		else{
			document.querySelector('#result').style.color = "#fc1c03";
			document.querySelector('#result').innerHTML ="인증이 실패했습니다";
			sessionStorage.removeItem("purchaseUserCheck");
			
		}
	}
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(query);
});


function validatePurchase(){
	
	var phone1 = document.getElementById("uPhone1");
    var phone2 = document.getElementById("uPhone2");
    var phone3 = document.getElementById("uPhone3");
    
    if(purchaseForm.uName.value==""){
    	alert("수취인 이름을 입력해주세요");
    	purchaseForm.uName.focus();
    	return false;
    }
    
    if(purchaseForm.uAdress.value==""){
    	alert("주소를 입력해주세요");
    	purchaseForm.uAdress.focus();
    	return false;
    }
    if(phone1=="" || phone2=="" || phone3=="" ){
    	alert("전화번호를 입력해주세요");
    	phone1.focus();
    	return false;
    }
    
    if(sessionStorage.getItem("purchaseUserCheck")!="ok"){
 	   alert("본인확인을 해주세요");
 	  purchaseForm.uPw.focus();
 	   return false;
    }
    
    var phone = phone1.value+phone2.value+phone3.value;
    purchaseForm.uPhone.value=phone;
    
    var pIds = new Array();
    var names = new Array();
    var images = new Array();
    var colors = new Array();
    var numOfs = new Array();
    
    var items =JSON.parse(sessionStorage.getItem("basketItems"));
	if(items[0] ==null){ //하긴 했지만 실행되지 않을 영역
		alert("상품이 없습니다");
		return false;
	}
	else{
		//추가된 상품
		for(var i=0;i<items.length;i++){
			pIds.push(items[i].pId);
			names.push(items[i].name);
			images.push(items[i].image);
			colors.push(items[i].color);
			numOfs.push(items[i].numOf);
			
		}
		
		purchaseForm.pId.value=pIds;
		purchaseForm.pName.value=names;
		purchaseForm.pImage.value=images;
		purchaseForm.pColor.value=colors;
		purchaseForm.pNumof.value=numOfs;
	}
    
    sessionStorage.removeItem("basketItems");
    sessionStorage.removeItem("purchaseUserCheck");
    
	return true;
}

</script>

</head>
<body>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<br>
<h2 align="center"><b>구매 확인</b></h2>
<br>
<hr>
<br>

<div id="basketContent">
</div>
<table style='width:50%; margin:0 auto;'>
		<tr>
			<td style='width:33%;'>
				<button class='btn btn-light btn-block' onclick="removeAll();">구매취소 (장바구니비우기)</button>
			</td>
			<td style='width:33%;'>
				<button class='btn btn-light btn-block' onclick="location.href='/onlyKeyboardShop/basket'">장바구니로 이동</button>
			</td>
			<td style='width:33%;'>
				<button class='btn btn-light btn-block' onclick="history.go(-1);">이전페이지</button>
			</td>
		</tr>
	</table>

 <br>
<br>

<div style='text-align:center; width:50%; margin:0 auto;'>
	<table style='width:100%;' border="1">
		<tr>
			<td style='width:33%;'><h5>가격</h5></td>
			<td style='width:33%;'><h5>배송비</h5></td>
			<td style='width:33%;'><h5><b>총 금액</b></h5></td>
		</tr>
		<tr>
			<td style='height:70px;' id='sumPrice'></td>
			<td id='deliverPrice'></td>
			<td id='totalPrice'></td>
		</tr>
	</table>
</div>
<br><br>

<!-- *****************  폼 정보 제출  **************** -->

<div style='text-align:center; width:50%; margin:0 auto;'>
<form method='post' onsubmit="return validatePurchase();" name="purchaseForm" action="buyAction">
<input type="hidden" name="uId" value="${user.username}"/>
<input type="hidden" id="pId" name="pId" />
<input type="hidden" id="pName" name="pName" />
<input type="hidden" id="pColor" name="pColor" />
<input type="hidden" id="pImage" name="pImage" />
<input type="hidden" id="pNumof" name="pNumof" />


	<table style='width:100%;' border="1">
		<tr>
			<td colspan="2"><h5>주문 상세정보</h5></td>
		</tr>
		<tr>
			<td style='width:33%;'><h5>수취인</h5></td>
			<td style='width:67%;'>
				<input type="text" value="${user.uName}" id="uName" name="uName" style="width:100%;"/>
			</td>
		</tr>
		<tr>
			<td><h5>배송지</h5></td>
			<td>
				<input type="text" value="${user.uAdress}" id="uAdress" name="uAdress" style="width:100%;"/>
			</td>
		</tr>
		<tr>
			<td><h5>휴대전화</h5></td>
			<td>
				<input type="text"  id="uPhone1" style="width:20%;" maxlength="3"/>-
				<input type="text"  id="uPhone2" style="width:20%;" maxlength="4"/>-
				<input type="text"  id="uPhone3" style="width:20%;" maxlength="4"/>
				<input type="hidden" id="uPhone" name="uPhone"/>
			</td>
		</tr>
		<tr>
			<td><h5>배송메세지</h5></td>
			<td>
				<input type="text"  id="deliverMessage" name="deliverMessage" style="width:100%;"  placeholder="배송전 연락 부탁드립니다"/>
			</td>
		</tr>
		<tr>
			<td><h5>본인확인</h5>(비밀번호 입력)</td>
			<td>
				<input type="password" id="uPw" name="uPw" style="width:25%;"/>
				<button type="button" id="userCheckButton">본인확인</button>
				<span id="result"></span>
			</td>
		</tr>
	</table>

</div>
<br><br>
<div style='width:400px; margin:0 auto; text-align: center; '>
 <div><button type="submit" class='btn btn-success btn-block' id="submitButton">구매하기</button></div>
</div>
</form>
<br><br><br><br><br><br><br><br>


<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>