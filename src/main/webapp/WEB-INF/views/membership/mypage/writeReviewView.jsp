<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script>
var grade;
$(document).ready(function(){
	
	if("${submit}"=="success"){
		alert("작성이 완료되었습니다. 감사합니다");
		self.close();
	}
	else if("${submit}"=="error"){
		alert("실패. 다시 시도해주세요");
	}
	
	$('.starRev span').on("click",(function(){
	
		  $(this).parent().children('span').removeClass('on');
		  $(this).addClass('on').prevAll('span').addClass('on');
		  grade = $(this).attr("value");
		  return false;
	})
	);

});

function reviewWrite(){
	
	if($('#reContent').val()==""){
		alert("내용을 입력해주세요");
		$('#reContent').focus();
		return false;
	}
	if(grade==null){
		alert("별점을 입력해주세요");
		return false;
	}
	
	$('#reGrade').attr('value',grade);
	
	return true;
}
</script>

<style>
.starR{
  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
  background-size: auto 100%;
  width: 30px;
  height: 30px;
  display: inline-block;
  text-indent: -9999px;
  cursor: pointer;
}
.starR.on{background-position:0 0;}
</style> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  
</head>
<body>
<h2 align="center">후기작성</h2>

<form action="writeReview" method="post" onsubmit="return reviewWrite()"> 
<table cellpadding="5" cellspacing="0" border="1" style="margin:0 auto; width:100%;">
	<tr>
		<td colspan="2" style="text-align: center;">
			후기를 작성해주세요(200자)
		</td>
	</tr>
	<tr>
		<td style="text-align: center;">내용</td>
		<td><textarea name="reContent" rows="6" cols="40"  id="reContent"></textarea></td>
	</tr>
	<tr>
		<td style="text-align: center;">별점</td>
		<td>
			<div class="starRev">
  				<span value="1" class="starR">별1</span>
				<span value="2" class="starR">별2</span>
  				<span value="3" class="starR">별3</span>
  				<span value="4" class="starR">별4</span>
  				<span value="5" class="starR">별5</span>
  			</div>
		</td>
		
	</tr>
	
	<tr>
		<td colspan="2"><button class="btn btn-primary btn-block">작성완료</button>
		</td>
	</tr>
</table>
<input type="hidden" name="purId" id="purId" value="${purId}"/>
<input type="hidden" name="pId" id="pId" value="${pId}"/>
<input type="hidden" name="uId" id="uId" value="${uId}"/>
<input type="hidden" name="pName" id="pName" value=" ${pName}"/>
<input type="hidden" name="pColor" id="pColor" value="${pColor}"/>
<input type="hidden" name="uName" id="uName" value="${uName}"/>
<input type="hidden" name="reGrade" id="reGrade"/>
</form>


</body>
</html>