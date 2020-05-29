/**
 * 
 */
function moveFocus(num,fromform,toform){
  var str = fromform.value.length;
  if(str == num) {
    toform.focus();
  }   
}


  
 $(document).ready(function(){
	  $("#id").keyup(function(){
		  $("#result").text("아이디를 확인해주십시오.");
			 $("#result").attr("style", "color:#000");
			 
			 $("#submit").attr("disabled", "disabled");
			 sessionStorage.removeItem("idCheck");
			 
	  });
	});

$(document).on("click", "#idCheckButton", function(){
	
	var id = document.getElementById("id");
	var temp = "uId=";
	var query = temp.concat(id.value);
	
	//alert(query);
const xhr = new XMLHttpRequest();
	
	xhr.open('POST','idCheck');
	
	xhr.onreadystatechange=function(){
	
		if(xhr.responseText === 'fail'){
			document.querySelector('#result').style.color = "#fc1c03";
			document.querySelector('#result').innerHTML ="중복된 값이 있습니다";
			$('#submit').attr("disabled","disabled");
			sessionStorage.removeItem("idCheck");
		}
		else{
			document.querySelector("#result").style.color = "#0377fc";
			document.querySelector('#result').innerHTML ="인증되었습니다";
			$('#submit').removeAttr("disabled","disabled");
			sessionStorage.setItem("idCheck","ok");
		}
	}
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(query);
	});

 
function validate() {
       var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
       //var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       // 이메일이 적합한지 검사할 정규식

       var id = document.getElementById("id");
       var pw = document.getElementById("pw");
       var email = document.getElementById("email");
       var birth = document.getElementById("birth");
       var phone1 = document.getElementById("phone1");
       var phone2 = document.getElementById("phone2");
       var phone3 = document.getElementById("phone3");

       var arrbirth = new Array(); // 주민번호 앞자리숫자 6개를 담을 배열
      

       // ------------ 이메일 까지 -----------

       if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
           return false;
       }
       
       if(sessionStorage.getItem("idCheck")==null){
    	   alert("id중복검사를 해주세요");
    	   return false;
       }

       if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
           return false;
       }

       if(joinForm.pw.value != joinForm.checkpw.value) {
           alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
           pw.focus();
           return false;
       }
       
       if(joinForm.name.value=="") {
           alert("이름을 입력해 주세요");
           joinForm.name.focus();
           return false;
       }
       
       if(joinForm.birth.value=="") { 
           alert("주민번호를 입력해 주세요");
           joinForm.birth.focus();
           return false;
       }
       if(birth.value.length!=6){
    	   alert("올바른 주민등록번호 6자리가 아닙니다");
    	   birth.value="";
    	   birth.focus();
           return false;
       }
       
       if(joinForm.gender.value=="") { //성별 select문으로할까? 
           alert("성별을 입력해 주세요");
           joinForm.gender.focus();
           return false;
       }

       if(email.value=="") {
           alert("이메일을 입력해 주세요");
           email.focus();
           return false;
       }
       if(joinForm.adress.value=="") { //여기 좀더 수정해야함
           alert("주소를 입력해 주세요");
           joinForm.adress.focus();
           return false;
       }
       //--------------전화번호 문자열 작업 ------------
       
       var phone = phone1.value+phone2.value+phone3.value;
       joinForm.submitPhone.value=phone;
       
       // -------------- 주민번호 -------------
       
       
       
       
       for (var i=0; i<birth.value.length; i++) {
    	   arrbirth[i] = birth.value.charAt(i);
       } // 주민번호 앞자리를 배열에 순서대로 담는다.
       
     	// ------------ 생일 자동 등록 -----------
     //YYYY-MM-DDs string
       if(arrbirth[0]==9 || arrbirth[0]==8) {
    	
           var y = birth.value.substring(0,2);
           var m = birth.value.substring(2,4);
           var d = birth.value.substring(4,6);
           var text = 19+y+"-"+m+"-"+d; 
           joinForm.submitBirth.value=text;
       }
       else if(arrbirth[0]==0 || arrbirth[0]==1) {
    	
    	   var y = birth.value.substring(0,2);
           var m = birth.value.substring(2,4);
           var d = birth.value.substring(4,6);
           var text = 20+y+"-"+m+"-"+d; 
           joinForm.submitBirth.value=text;
       }
       sessionStorage.removeItem("idCheck");
       alert("회원가입에 성공하였습니다. 로그인화면으로 이동합니다.");
       
 }

 function check(re, what, message) {
       if(re.test(what.value)) {
           return true;
       }
       alert(message);
       what.value = "";
       what.focus();
       //return false;
 }
 
// $(document).on("click",".mydiv",function(){
//	 alert("hello");
// });
// 