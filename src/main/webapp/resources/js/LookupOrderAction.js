/**
 * 
 */
function getList(){ //초기 리스트와 초기 페이지정보를 저장해두기
	beforeList=[];
	afterList=[];
	$.ajax({
	    url: "/onlyKeyboardShop/getOrderList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : currentUserName},
	    success: function(data){ //beforeList, afterList
	    	
	    	//before list 받아오기
	    	for(var i =0;i<data.beforeList.length;i++){
	    		var state;
	    		switch(data.beforeList[i].state){
	    		case "purchased":
	    			state="발송 준비중"; 
	    			break;
	    		case "shipping":
	    			state="배송 진행중";
	    			break;
	    		case "delivered":// btn btn-dark btn-block
	    			state="배송완료!<br>"+
	    			"<button class='btn btn-dark' onclick='checkDelivery("+data.beforeList[i].purId+")'>배송확인</button>";
	    			break;
	    		};
	    		var date = new Date(data.beforeList[i].purTime);
	    		var dateString = date_to_str(date);
	    		var item = {purId:data.beforeList[i].purId, userId:data.beforeList[i].userId, 
	    		productId:data.beforeList[i].productId, pName:data.beforeList[i].pName, pColor:data.beforeList[i].pColor, 
	    		pImageRoute:data.beforeList[i].pImageRoute,	purTime:dateString,  
	    		uName:data.beforeList[i].uName, state:state,
	    		uAdress:data.beforeList[i].uAdress,uPhone:data.beforeList[i].uPhone,
	    		purMessage:data.beforeList[i].purMessage,pNum:data.beforeList[i].pNum};
	    		
	    		beforeList.push(item);
	    		
	    	};
	    	
	    	//after list 받아오기
	    	for(var i =0;i<data.afterList.length;i++){
	    		var state;
	    		switch(data.afterList[i].state){
	    		case "checkedDelivery":
	    			state="<button class='btn btn-dark' "+
	    			"onClick='reviewWritePopup("+i+")'>후기쓰기</button>"; 
	    			break;
	    		case "writeReview":
	    			state="리뷰작성<br> 감사합니다!";
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
	
	$('#beforeList').html( //before list 페이지 내용
			'<tr>'+
			'<td style="width:50px;text-align: center;">No</td>'+
			'<td style="width:100px;text-align: center;">image</td>'+
			'<td>구매한 상품</td>'+
			'<td style="width:120px; text-align: center;">구매날짜</td>'+
			'<td style="width:120px;text-align: center;">배송조회</td>'+
		'</tr>');
		
	for(var i=0 ; i<beforeList.length ;i++){  
		$('#beforeList').append(
		'<tr>'+
		'<td style="text-align: center;">'+(Number(i)+1)+'</td>'+
		'<td ><img src="'+beforeList[i].pImageRoute+'" style="width:100%"/></td>'+
		'<td>'+
		beforeList[i].pName+'('+beforeList[i].pColor+')'+ beforeList[i].pNum+'개'+ 
		'</td>'+
		'<td style="text-align: center;">'+beforeList[i].purTime+'</td>'+
		'<td style="text-align: center;" >'+beforeList[i].state+'</td>'+
		'</tr>'
    	);
			
	};
	if(beforeList==null || beforeList.length==0){
		$('#beforeList').append("<tr><td colspan='5'>구매한 상품이 없습니다.</td></tr>");
	}
	$('#beforeList').append("<tr><td colspan='5'>물건을 수령하셨다면, 배송확인을 눌러주세요!</td></tr>");
	
 
	$('#afterList').html(//after list 페이지 내용
			'<tr>'+
			'<td style="width:50px;text-align: center;">No</td>'+
			'<td style="width:100px;text-align: center;">image</td>'+
			'<td>구매한 상품</td>'+
			'<td style="width:120px; text-align: center;">구매날짜</td>'+
			'<td style="width:120px;text-align: center;">후기</td>'+
		'</tr>');
	
	for(var i=0 ; i<afterList.length ;i++){ //list 페이지 내용
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
	return year + "-" + month + "-" + date; // + " " + hour + ":" + min + ":" + sec
}

function checkDelivery(purId){
	 $.ajax({
	    url: "/onlyKeyboardShop/checkDelivery",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"purId" : purId},
	    success: function(data){
	    	
	    }
	    
	}); 
	 
	getList();
	showLists();
}

function reviewWritePopup(index){
	var item = afterList[index];//이거안됨. 걍 노가다 ㄱ	
	
	var popUrl = "/onlyKeyboardShop/writeReviewView?pId="+item.productId+"&uId="+item.userId+
	"&pName="+item.pName+"&pColor="+item.pColor+"&uName="+item.uName+"&purId="+item.purId;
	var popOption = "width=450, height=360, resizable=no, scrollbars=no, status=no;"; 
	var target = "rivewWriteView";
	window.open(popUrl,"",popOption);
	//window.open(popUrl,"",popOption);
	
	
}