/**
 * 
 */


function formLoginCheck(){
	alert("화면넘어가기");
	return true;
};


$(document).on("click", "#bookMark", function(){
	
	var uId = $('#bookMark').attr('value');
	var pId = $('#pId').attr('value');
	var temp = "uId=";
	var query=temp.concat(uId).concat("&pId=").concat(pId);
	
	//alert(query);
	
	const xhr = new XMLHttpRequest();
	
	xhr.open('POST','addBookmark');
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			if(xhr.responseText === 'success'){
				alert("정상적으로 추가되었습니다.");
				history.go(0);
			}
			else if(xhr.responseText === 'alreadyAdded')
			{
				alert("이미 추가된 항목입니다.");
				history.go(0);
			}
			else{ //실패했을때
				alert(xhr.responseText);
			}
		}
	}
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(query);
});


/*$(document).on("click", "#bookMark", function(){
	var value = document.getElementById("bookMark");
	
	alert("bookmark");
	//alert($('#bookMark').attr('value')+"없나");
	
	//북마츠
});
*/
$(document).on("click", "#basket", function(){
	alert("basket");
	//장바구니
});

function formLoginCheck(){
	//어? 이건 ajax 할필요 없잖아. 그냥
	alert("확인")
	return true;
};



