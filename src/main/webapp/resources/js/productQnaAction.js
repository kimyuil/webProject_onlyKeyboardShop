/**
 * 
 */
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
		    		"<td style='text-align: center; color:gray;'>비공개</td>"+
		    		"<td style='color:gray;'>비공개입니다</td>"+
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
			"<br><br><b>답변</b><hr>"+qnaList[id].qnaAnswer+"<br></td></tr>");
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