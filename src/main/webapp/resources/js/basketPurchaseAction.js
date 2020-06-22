/**
 * 
 */
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
			if(checkItems[i].pId==pid)
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
			
			var item = {pId:pid,name:pName,image:pImageRoute,color:$('#item'+indexList[i]).attr('value'),
					numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
									
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
			var flag=false;
			for(var j=0;j<items.length;j++){ //중복검사. 같으면 새롭게 추가가 아니라 기존 아이템의 개수를 더해주기
				if( items[j].pId==pid && items[j].color== $('#item'+indexList[i]).attr('value')){
					items[j].numOf = Number(items[i].numOf) + Number($('#numOf'+indexList[i]).val()); //개수더해주고
					items[j].price = Number(items[i].price) + Number($('#thisPrice'+indexList[i]).attr('value'));
					sessionStorage.setItem("basketItems", JSON.stringify(items));		
					flag=true;
				
				}
			}
			if(flag==true){ //기존 아이템 개수를 더해주었다면 for문 다시 시작
				continue;
			}
			
			//이름, 이미지, 색상정보, 개수, 가격을 담은 객체
			var item = {pId:pid,name:pName ,image: pImageRoute ,color:$('#item'+indexList[i]).attr('value'),
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




//구매페이지로 넘어가기전 form check 

function formLoginCheck(){//확인후 member/*로 이동시키면 됨. spring security
	
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
			if(checkItems[i].pId==pid)
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
	
	
	if(currentUserName==null || currentUserName==""){ //로그인체크
		alert("로그인이 필요합니다")
		return false;
	}
	
	
	for (var i=0;i < indexList.length ; i++){ 
		if(sessionStorage.getItem("basketItems")==null){//장바구니가 빈 상태였을때
			
			var items = [];
			
			var item = {pId: pid,name: pName ,image: pImageRoute ,color:$('#item'+indexList[i]).attr('value'),
				numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
				items.push(item);
			
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		} 
		else{ //이전에 추가해둔 장바구니가 있을때.
			
			var items = JSON.parse(sessionStorage.getItem("basketItems"));
			
		var flag=false;
			for(var j=0;j<items.length;j++){
								
				if( items[j].pId==pid && items[j].color== $('#item'+indexList[i]).attr('value')){
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
			var item = {pId:pid,name: pName ,image: pImageRoute ,color:$('#item'+indexList[i]).attr('value'),
				numOf:$('#numOf'+indexList[i]).val(),price: $('#thisPrice'+indexList[i]).attr('value')};
			items.push(item);
			sessionStorage.setItem("basketItems", JSON.stringify(items));
		}
	}
		
	return true;
};






