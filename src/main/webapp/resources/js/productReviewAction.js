
function showReviewList(){ //후기 게시판 뿌려주는 메소드
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
	    data: {"reviewPage" : page, "pId" : pid},
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

function reviewList(page){ //후기 게시판 리스트와 초기 페이지정보를 저장해두기 처음한번 실행
	$.ajax({
	    url: "/onlyKeyboardShop/reviewList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"reviewPage" : page, "pId" : pid},
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
	    	pageInfo={blockStartNum:data.pageInfo.blockStartNum, blockLastNum:data.pageInfo.blockLastNum,
	    			lastPageNum:data.pageInfo.lastPageNum, realLastBlockNum:data.pageInfo.realLastBlockNum,
	    			currentPage:data.pageInfo.currentPage, currentPageFirstNum:data.pageInfo.currentPageFirstNum,
	    			currentPageLastNum:data.pageInfo.currentPageLastNum};
	      	
	    },
	    
	    
	  });
}
