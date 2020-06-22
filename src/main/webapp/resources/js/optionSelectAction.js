/**
 * 
 */
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
		
		$("#productDetails").append(
			"<li id='item"+numofIndex+"' value='"+$("#colorSelect option:selected").val()+"'>"+
			"<div style='float:left;'><b>"+pName+"</b><br>"+$("#colorSelect option:selected").val()+"</div>"+
			
			"<div style='float:right;  line-height: 50px'>"+
			"<span style='vertical-align: middle;' id='thisPrice"+numofIndex+"' value='"+originPrice+"'>"+originPrice+"원</span>	</div>"+
			
			"<div style='float:right;'>		&nbsp<button style='margin-top: 10px' id='removeSelected"+numofIndex+"' onclick='removeItem(this.id)' >x</button>&nbsp</div>"+
			
			"<div style='float:right;  line-height: 50px'>"+
			"<input type='number' id='numOf"+numofIndex+
			"' value='1' name='numOf' min='1' onchange='numChange(this.id)' style='width:60px; height:30px; vertical-align: middle; display:inline-block;'/></div>"+
			
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


function numChange(thisid){ //한 색깔옵션에 대해 개수 올리는 function
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