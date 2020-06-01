/**
 * 
 */
function pwCheck(re, pw, message, checkpw){ 
	if(!check(re,pw,message)) {
        return false;
    }
	
	if(pw.value != checkpw.value) {
        alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
        pw.focus();
        return false;
    }
	
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
