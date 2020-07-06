/**
 * 
 */
function getList(){ //초기 리스트와 초기 페이지정보를 저장해두기
	beforeList=[];
	afterList=[];
	$.ajax({
	    url: "/onlyKeyboardShop/getTotalOrderList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : curruntUserName},
	    success: function(data){ //beforeList, afterList
	    	
	    	//before list 받아오기
	    	for(var i =0;i<data.beforeList.length;i++){
	    		var state, button;
	    		switch(data.beforeList[i].state){
	    		case "purchased":
	    			state="<b>구매요청</b>";
	    			button="<button class='btn btn-outline-secondary' "+
	    			"onclick='sendItem("+data.beforeList[i].purId+")'>발송하기</button>"
	    			break;
	    		case "shipping":
	    			state="<b>배송진행중</b>";
	    			button="<button class='btn btn-outline-success' "+
	    			"onclick='shippedItem("+data.beforeList[i].purId+")'>배송완료</button>"
	    			break;
	    		case "delivered":// btn btn-dark btn-block
	    			state="배송완료"
	    			button="사용자 확인 <br> 대기중"
	    			break;
	    		};
	    		var date = new Date(data.beforeList[i].purTime);
	    		var dateString = date_to_str(date);
	    		
	    		var uAdress = data.beforeList[i].uAdress;
	
	    		
	    		var item = {purId:data.beforeList[i].purId, userId:data.beforeList[i].userId, 
	    		productId:data.beforeList[i].productId, pName:data.beforeList[i].pName, pColor:data.beforeList[i].pColor, 
	    		pImageRoute:data.beforeList[i].pImageRoute,	purTime:dateString,  
	    		uName:data.beforeList[i].uName, state:state,
	    		uAdress:data.beforeList[i].uAdress,uPhone:data.beforeList[i].uPhone,
	    		purMessage:data.beforeList[i].purMessage,pNum:data.beforeList[i].pNum, button:button};
	    		
	    		beforeList.push(item);
	    		
	    	};
	    	
	    	//after list 받아오기
	    	for(var i =0;i<data.afterList.length;i++){
	    		var state;
	    		switch(data.afterList[i].state){
	    		case "checkedDelivery":
	    			state="사용자<br>배송확인";
	    			break;
	    		case "writeReview":
	    			state="<a href='/onlyKeyboardShop/productPage?pId="+data.afterList[i].productId+"'><b>사용자<br>리뷰작성</b></a>";
	    			break;
	    		};
	    		var date = new Date(data.afterList[i].purTime);
	    		var dateString = date_to_str(date);
	    		
	    		var item = {purId:data.afterList[i].purId, userId:data.afterList[i].userId, 
	    		productId:data.afterList[i].productId, pName:data.afterList[i].pName, pColor:data.afterList[i].pColor, 
	    		pImageRoute:data.afterList[i].pImageRoute,	purTime:dateString,  
	    		uName:data.afterList[i].uName, state:state,
	    		uAdress:data.afterList[i].uAdress,uPhone:data.afterList[i].uPhone,
	    		purMessage:data.afterList[i].purMessage,pNum:data.afterList[i].pNum};
	    	
	    		afterList.push(item);
	    		
	    	};
	      	
	    },
	  });
}


function showLists(){ //실질적인 출력 담당
//userId, state??, uName, uAdress, uPhone, purMessage, button	
	$('#beforeList').html( //before list 페이지 내용
			'<tr>'+
			'<td style="width:50px;text-align: center;">No</td>'+
			'<td style="width:100px;text-align: center;">image</td>'+
			'<td>구매한 상품</td>'+
			'<td style="width:120px; text-align: center;">구매날짜</td>'+
			'<td style="width:120px;text-align: center;">상태</td>'+
			'<td style="width:120px;text-align: center;">체크</td>'+
		'</tr>');
		
	for(var i=0 ; i<beforeList.length ;i++){  
		$('#beforeList').append(
		'<tr>'+
		'<td rowspan="2" style="text-align: center;">'+(Number(i)+1)+'</td>'+
		'<td ><img src="'+beforeList[i].pImageRoute+'" style="width:100%"/></td>'+
		'<td>'+
		beforeList[i].pName+'('+beforeList[i].pColor+')'+ beforeList[i].pNum+'개'+ 
		'</td>'+
		'<td style="text-align: center;">'+beforeList[i].purTime+'</td>'+
		'<td style="text-align: center;" >'+beforeList[i].state+'</td>'+
		'<td style="text-align: center;" >'+beforeList[i].button+'</td>'+
		'</tr>'+
		'<tr class="border_bottom">'+
		'<td style="text-align: center;">'+beforeList[i].userId+'<br>'+beforeList[i].uName+'</td>'+
		'<td colspan="4" style="text-align: center;">'+
		'<b>주소</b> : '+beforeList[i].uAdress+'<br>'+'<b>메세지</b> : '+beforeList[i].purMessage+'&nbsp'+' <b>전화번호</b> : '+beforeList[i].uPhone+
		'</td></tr>'
    	);
			
	};
	if(beforeList==null || beforeList.length==0){
		$('#beforeList').append("<tr><td colspan='6'><b>EMPTY</b></td></tr>");
	}
	$('#beforeList').append("<tr><td colspan='6'>admin page</td></tr>");
	
 
	$('#afterList').html(//after list 페이지 내용
			'<tr>'+
			'<td style="width:50px;text-align: center;">No</td>'+
			'<td style="width:100px;text-align: center;">image</td>'+
			'<td>구매한 상품</td>'+
			'<td style="width:120px; text-align: center;">구매날짜</td>'+
			'<td style="width:120px;text-align: center;">후기</td>'+
		'</tr>');
	
	for(var i=afterList.length-1 ; i>=0 ;i--){ //list 페이지 내용
		var obj = JSON.stringify(afterList[i]);
		//alert(obj);
		$('#afterList').append(
		'<tr>'+
		'<td style="text-align: center;">'+(Number(i)+1)+'</td>'+
		'<td ><img src="'+afterList[i].pImageRoute+'" style="width:100%"/></td>'+
		'<td>'+
		afterList[i].pName+'('+afterList[i].pColor+')'+ afterList[i].pNum+'개'+ 
		'</td>'+
		'<td style="text-align: center;">'+afterList[i].purTime+'</td>'+
		
		'<td style="text-align: center;" >'+afterList[i].state+'</td>'+		
	'</tr>'
	   	);
   }
	$('#afterList').append("<tr><td colspan='5'>수정 및 삭제는 mypage > 나의게시글 메뉴에서 가능합니다</td></tr>");
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

function sendItem(purId){
	$.ajax({
	    url: "/onlyKeyboardShop/adminSendItemModify",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"purId" :purId},
	    success: function(data){
	    		getList();
	    		showLists();
	    }
	});
	
}
function shippedItem(purId){
	$.ajax({
	    url: "/onlyKeyboardShop/adminShippedItemModify",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"purId" :purId},
	    success: function(data){
    		getList();
    		showLists();
	    }
	});
}
