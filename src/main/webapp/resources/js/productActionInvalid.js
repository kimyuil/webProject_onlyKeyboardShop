/**
 * 
 */

$(document).on("click", "#bookMark", function(){
	loginCheck();
});

$(document).on("click", "#basket", function(){
	loginCheck();
});

/*$("#buyForm").on("submit", function() {
	loginCheck ();
    return false;
 });
*/
function formLoginCheck(){
	loginCheck();
	return false;
};

function loginCheck (){
	alert("로그인이 필요합니다");
	history.go(0);
};