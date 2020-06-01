/**
 * 
 */

document.write("<script src='/onlyKeyboardShop/resources/js/check.js'></script>");

function validateFindPwCheck() {
	var re = /^[a-zA-Z0-9]{4,12}$/
	var pw = document.getElementById("pw");
	var message = "패스워드는 4~12자의 영문 대소문자와 숫자로만 입력";
	
	pwCheck(re,pw,message,checkform.checkpw);
	
	alert("회원가입에 성공하였습니다. 로그인화면으로 이동합니다.");
	
};


function validateId(){
	var email = document.getElementById("email");
	var name = document.getElementById("name");
	
	if(email.value==""){
		alert("이메일을 입력해주세요")
		email.focus();
		return false;
	}
	if(name.value==""){
		alert("이름을 입력해주세요")
		name.focus();
		return false;
	}
	
}

function validatePw(){
	var email = document.getElementById("email");
	var id = document.getElementById("id");
	
	if(email.value==""){
		alert("이메일을 입력해주세요")
		email.focus();
		return false;
	}
	if(id.value==""){
		alert("아이디를 입력해주세요")
		id.focus();
		return false;
	}
	
}