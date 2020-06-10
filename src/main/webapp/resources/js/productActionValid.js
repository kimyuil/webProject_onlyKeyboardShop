/**
 * 
 */




$(document).on("click", "#bookMark", function(){ //북마크기능구현
	
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






