/**
 * 
 */
function showQNAList(){ //qna 게시판 뿌려주는 메소드

	
	$('#qnaBeforeTable').html(
			'<tr>'+
			'<td style="width:50px;text-align: center; background-color:#dedede" ><b>번호</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>작성자</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>상품</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>제목</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>답변</b></td>'+
		'</tr>');
	
	
	for(var i=0 ; i<qnaBeforeList.length ;i++){ //list 페이지 내용
				
    	$('#qnaBeforeTable').append("<tr class='tr"+i+"'>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'>"+qnaBeforeList[i].uName+"<br>"+qnaBeforeList[i].uId+"</td>"+
    		'<td style="text-align: center;"><a href="/onlyKeyboardShop/productPage?pId='+qnaBeforeList[i].pId+'">'+qnaBeforeList[i].pName+'</a></td>'+
    		"<td><a href='javascript:void(0)' onclick='showContent("+i+","+dubleQuotor+bflag+dubleQuotor+")'>"+qnaBeforeList[i].qnaTitle+"</a></td>"+
    		"<td style='text-align: center;'>"+qnaBeforeList[i].qnaDate +"</td>"+
    		"<td style='text-align: center;'>"+qnaBeforeList[i].isAnswered +"</td></tr>"
    	);
   } 
	$('#qnaBeforeTable').append("<tr><td colspan='6'>답변대기 항목들</td></tr>");
	//구분선-------------------------
	$('#qnaAfterTable').html(
			'<tr>'+
			'<td style="width:50px;text-align: center; background-color:#dedede" ><b>번호</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>작성자</b></td>'+
			'<td style="width:80px;text-align: center;background-color:#dedede"><b>상품</b></td>'+
			'<td style="text-align: center;background-color:#dedede"><b>제목</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>날짜</b></td>'+
			'<td style="width:100px; text-align: center;background-color:#dedede"><b>답변</b></td>'+
		'</tr>');
	
	
	for(var i=qnaAfterList.length-1 ; i>=0 ;i--){ //list 페이지 내용
				
    	$('#qnaAfterTable').append("<tr class='atr"+i+"'>"+
    		"<td style='text-align: center;'>"+(Number(i)+1)+"</td>"+
    		"<td style='text-align: center;'>"+qnaAfterList[i].uName+"<br>"+qnaAfterList[i].uId+"</td>"+
    		'<td style="text-align: center;"><a href="/onlyKeyboardShop/productPage?pId='+qnaAfterList[i].pId+'">'+qnaAfterList[i].pName+'</a></td>'+
    		"<td><a href='javascript:void(0)' onclick='showContent("+i+","+dubleQuotor+aflag+dubleQuotor+")'>"+qnaAfterList[i].qnaTitle+"</a></td>"+
    		"<td style='text-align: center;'>"+qnaAfterList[i].qnaDate +"</td>"+
    		"<td style='text-align: center;'>"+qnaAfterList[i].isAnswered +"</td></tr>"
    	);
   } 
	$('#qnaAfterTable').append("<tr><td colspan='6'>답변완료 항목들</td></tr>");
}


function QNAList(){ //QNA 게시판 리스트
	qnaAfterList=[];
	qnaBeforeList=[];
	$.ajax({
	    url: "/onlyKeyboardShop/adminQnaList",
	    type: "POST",
	    cache: false,
	    dataType : "json",
	    async: false,
	    data: {"uId" : currentUserName},
	    success: function(data){
	    	
	    	//후기게시판, 페이징정보 가져오기
	    	for(var i =0;i<data.qnas.length;i++){
	    		
	    		var isAnswered="<b>답변대기중</b>";
	    		if (data.qnas[i].isAnswered==1) isAnswered="답변완료";
	    		
	    		
	    		var isSecret=false;
	    		if (data.qnas[i].isSecret==1) isSecret=true;
	    		
	    		var date = new Date(data.qnas[i].qnaDate);
	    		var dateString = date_to_str(date);
	    		
	    		var item = {qnaId:data.qnas[i].qnaId, uId:data.qnas[i].uId, 
	    		pId:data.qnas[i].pId, pName:data.qnas[i].pName, qnaTitle:data.qnas[i].qnaTitle, 
	    		qnaContent:data.qnas[i].qnaContent,	qnaDate:dateString,  
	    		isSecret:isSecret, isAnswered: isAnswered,
	    		qnaAnswer:data.qnas[i].qnaAnswer, uName:data.qnas[i].uName};
	    		
	    		if(isAnswered=="<b>답변대기중</b>")
	    			qnaBeforeList.push(item);
	    		else
	    			qnaAfterList.push(item);
	    			
	    	};
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

function showContent(id,flag){ //toggle content view

	var qnaList = new Array();
	
	if(flag=="before"){
		qnaList=qnaBeforeList;
		
		if($('.tr'+id).attr('content')==undefined){
			$('.tr'+id).after(
				"<tr><td colspan='6' style='height:100px;'>"+
				qnaList[id].qnaContent+
				"<br><br><div align='right'>"+
				"<button class='btn btn-dark btn-sm' onclick='answerQnA("+id+")'>답변하기</button> "+
				"</td></tr>");
			
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
	else{
		
		qnaList=qnaAfterList;
		
		if($('.atr'+id).attr('content')==undefined){//답변이 달렸을때
			
			$('.atr'+id).after("<tr><td colspan='6' style='height:100px;'>"+qnaList[id].qnaContent+
			"<br><br><b>답변</b><hr>"+qnaList[id].qnaAnswer+"<br>"+
			"<div align='right'>"+
			"</td></tr>");
						
			$('.atr'+id).attr('content',"true");
		}	
		else if($('.atr'+id).attr('content')=="true"){
			
			$('.atr'+id).next('tr').hide();
			$('.atr'+id).attr('content',"false");
		}
		else if($('.atr'+id).attr('content')=="false") {
			
			$('.atr'+id).next('tr').show();
			$('.atr'+id).attr('content',"true");
		} 
	}	
}

function answerQnA(id){
	
	var answer = prompt("답변작성");
	if(answer=="" || answer==null ||answer==undefined)
		return;
	var check=confirm(answer+ ": 이 내용으로 답변을 달겠습니까?");
	if(check==false)
		return;
	
	$.ajax({
	    url: "/onlyKeyboardShop/adminQnaAnswer",
	    type: "POST",
	    cache: false,
	    async: false,
	    data: {"qnaId" : qnaBeforeList[id].qnaId, "qnaAnswer":answer},
	    success: function(data){
	    	if(data=="success"){
	    		QNAList();
	    		showQNAList();
	    	}
	    }
	});
	
}