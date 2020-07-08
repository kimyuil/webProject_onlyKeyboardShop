/**
 * 
 */
function getReviewList(){
	reviewsList=[];
	$.ajax({
	    url: "/onlyKeyboardShop/userReviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : currentUserName},
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
	    		reContent:data.reviews[i].reContent, reGrade:grade, purId:data.reviews[i].purId};
	    		
	    		reviewsList.push(item);
	    	};
	    },
	    
	  });
}
function showReviewList(){ //실질적인 출력 담당
	$('#reviewTable').html(
			'<tr>'+
			'<td style="width:35px;text-align: center; background-color:#dedede" ><b>no</b></td>'+
			'<td style="width:75px; text-align: center;background-color:#dedede"><b>상품명</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>내용</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:80px; text-align: center;background-color:#dedede"><b>별점</b></td>'+
			'<td style="width:140px; text-align: center;background-color:#dedede"><b>--</b></td>'+
		'</tr>');
	
	var lastNum=0;
	
	for(var i=0 ; i<reviewsList.length ;i++){ //list 페이지 내용
    	$('#reviewTable').append("<tr>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'><a href='/onlyKeyboardShop/productPage?&pId="+reviewsList[i].pId+"#review'>"+reviewsList[i].pName+"</a></td>"+
    		"<td>"+reviewsList[i].reContent+"</td>"+
    		"<td style='text-align: center;'>"+reviewsList[i].reDate +"</td>"+
    		"<td style='text-align: center;'><b>"+reviewsList[i].reGrade +"</b></td>"+
    		"<td style='text-align: center;'>"+
    		"<button class='btn btn-outline-primary' onclick='reviewWritePopup("+i+")'>수정</button>&nbsp"+
    		"<button class='btn btn-outline-secondary' onclick='removeReview("+i+")'>삭제</button></td>"+
    		"</tr>"
    	);
   }
}


function reviewWritePopup(index){
	var item=reviewsList[index];
		
	var popUrl = "/onlyKeyboardShop/modifyReviewView?reId="+item.reId;
	var popOption = "width=450, height=360, resizable=no, scrollbars=no, status=no;"; 
	window.open(popUrl,"",popOption);
	//window.open(popUrl,"",popOption);	
}

function removeReview(index){
	var remove = confirm("정말 삭제하시겠습니까?");
	if(remove == false)
		return;
			
	var item=reviewsList[index];
	 $.ajax({
	    url: "/onlyKeyboardShop/deleteUserReview",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"reId" : item.reId, "purId" : item.purId},
	    success: function(data){
	    	alert("삭제되었습니다 ");
	    	getReviewList();
	    	showReviewList();
	    } 
	});
}