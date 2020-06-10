/**
 * 
 */

$(document).on("click", "#bookMark", function(){
	loginCheck();
}); //북마크는 로그인필요


function loginCheck (){
	alert("로그인이 필요합니다");
	history.go(0);
};