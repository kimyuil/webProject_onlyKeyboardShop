<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.uName" var="uName"/> 
</sec:authorize>

<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<script>

$(document).ready(function(){
	if("${isSecret}"=="true")
		$('#secretCheck').prop( 'checked', true );
});

function qnaSubmit(){
	
	if($('#secretCheck').is(':checked'))
		$('#secretCheck').val('1');
	else
		$('#secretCheck').val('0');
	
	
	//alert($('#qnaSubmit').secretCheck.value);
	return true;
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h2 align="center">Q&A 수정하기</h2>
<h4 align="center">상품명 : ${pName}</h4>
<br><br>
<div style="width:700px; margin:0 auto; " >
<form action="/onlyKeyboardShop/ModifyQnA" method="post" id="qnaSubmit" onsubmit="return qnaSubmit()">
  <div class="form-group">
    <label for="Qtitle">${uName}님, 제목을 입력하세요</label>
    <input type="text" class="form-control" id="Qtitle" name="qnaTitle" value="${qnaTitle}"/>
  </div>
  <div class="form-group">
    <label for="Qcontent">문의사항을 입력해주세요 (250자)</label>
    <textarea class="form-control" id="Qcontent" rows="6" name="qnaContent">${qnaContent}</textarea>
  </div>
  <div class="form-group row">
    <div class="col-sm-2">Check Secret</div>
    <div class="col-sm-10">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="secretCheck" name="isSecret" vlaue="notOn">
        <label class="form-check-label" for="secretCheck">
          	비밀글 여부
        </label>
      </div>
    </div>
  </div>
  
  <br>
  <button type="submit" class="btn btn-info btn-block">작성완료</button>
  <br>
  <input name="qnaId" value="${qnaId}" type="hidden"/>
  <input name="pId" value="${pId}" type="hidden"/>
    
</form>
</div>

<br><br><br>
<br><br><br>

<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>


</body>
</html>