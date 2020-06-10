<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<script>

$(document).ready(function(){
	var items =JSON.parse(sessionStorage.getItem("basketItems"));
	if(items[0] ==null){ //추가된 상품이 없습니다. 띄워주기
		$('#empty').append(
				"<div style="+
				"'width:400px; height:300px; margin:0 auto; background-color: #d1d1d1; text-align: center; line-height: 300px; font-weight: bolder;font-size: x-large; '>"+
				"장바구니가 비어있습니다 </div>"
		);
		$('#purchaseButton').attr('disabled',true);
	}
	else{
		//추가된 상품을 보이기.
		for(var i=0;i<items.length;i++){
			var name = items[i].name;
			var image = items[i].image;
			var color = items[i].color;
			var numOf = items[i].numOf;
			var price = items[i].price;
			$('#basketContent').append(
					"<div style='text-align:center; width:50%; margin:0 auto;'>"+
					"<div style='width:250px; display:inline-block;'>"+
						"<img src='"+image+"' width='100%'/>"+
					"</div>"+
					"<div style='width:100px; display:inline-block;'></div>"+

					"<div style='width:50%; display:inline-block; text-align:left;'>"+
					
						"<div style='float:left;'>"+
						"<h4>"+name+"</h4>"+
						"<h5>"+color+"</h5></div>"+
						
						"<div style='float:right;'>"+
						"<button id='"+name+"And"+color+"' onclick='removeBasket(this.id)'>X</button></div>"+
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
		
	}
});

function removeBasket(nameColor){
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

function show(){ //no use
	var items =JSON.parse(sessionStorage.getItem("basketItems"));

	for(var i=0;i<items.length;i++){
		alert(items[i].name+", "+items[i].image+", "+items[i].color+", "+items[i].numOf+", "+items[i].price);
	}
}
</script>


</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<br>
<h2 align="center">장바구니</h2>
<br>
<br>
<div id="empty"> </div>
<div id="basketContent">
</div>
<br><br>
<div style='width:400px; margin:0 auto; text-align: center; '>
<div><button class='btn btn-light btn-block' onclick="location.href='home'">쇼핑 계속하기</button></div>
<br>
<div><button class='btn btn-light btn-block' onclick="history.go(-1);">이전페이지</button></div>
<br>
<div><button class='btn btn-success btn-block' id='purchaseButton' onclick="location.href='member/buyPage'">구매하기</button></div>
</div>
<br><br><br><br><br><br><br><br>


<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>