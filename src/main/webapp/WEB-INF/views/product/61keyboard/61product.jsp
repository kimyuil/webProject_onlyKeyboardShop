<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="/onlyKeyboardShop/resources/js/productAction.js"></script>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script><meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h1 align="center">${product.pName}</h1>
<br><br><br>
<div style="clear:both;"></div>

<div style="display:table; width:100%; ">
<div style="display:table-cell; width:100%; text-align: center; "><!-- vertical-align: middle; -->
	<div style="width:400px; display:inline-block; margin-top: 0; "> <!-- 왼쪽이미지 -->
		<img src="${product.pImageRoute}" width="100%" style="vertical-align: unset;" /> 
	</div>
	<div style="width:100px; height:10px; display:inline-block;"></div>

	<div style="width:400px; display:inline-block;text-align:left; "> <!-- 오른쪽 정보 -->
	
	<button class="btn btn-light" onclick="javascript:loginCheck()"> 북마크하기 </button> <!-- user정보변경 / 로긴체크js-->
	<br><br>
	<form action="buyPage"  method="post"> <!-- hidden으로 user id도 보내야함. -->
		<input type="hidden" name="pId" value="${product.pId}"/>
		<h4>${product.pName}</h4>
		<p>category : ${product.pCategory}</p>
		<p>${product.pBreifComment}</p>
		가격 : <b>${product.pPrice}</b> <br> 
		배송비 : <b>${product.pDeliveryPrice}</b>  <br>
		색상 : <select name="color">
				<c:forEach items="${colorsArray}" var="colors">
				<option value="${colors}">${colors}</option>
				</c:forEach>
			</select> <br>
		
		개수 : <input type="number" value="1" name="numOf" />
		<br><br>
		
		<div style="float:left;">
		<input type="submit" value="구매하기" class="btn btn-light" />
		</div>
	</form>
		<div style="float:left;">	
		<button class="btn btn-light" onclick="javascript:loginCheck()">장바구니</button> <!-- user정보변경 / 로긴체크js-->
		</div>
		
	
	</div>
</div>
</div>
	<div style="clear:both;"></div>
	<br><br>
<!-- 상세정보 탭 -->
<div align="center">
	<div style="width:50%; margin:0 auto; background-color:#ebebeb; height:60px; line-height:60px;font-size:large; ">
	상세정보</div>

	<img src="${product.pExplainImageRoute}" style="width:65%;"/>
</div>

<div style="width:40%; margin:0 auto;">
<p>
	상세설명 : ${product.pInformation} 
	색상 : <c:forEach items="${colorsArray}" var="colors">${colors}</c:forEach>
</p>
</div>


<br><br><br>

 <div class="container">
      <div class="row">
        <div class="col">

<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link active" data-toggle="tab" href="#review">후기 게시판</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="tab" href="#qna">Q&A 게시판</a>
  </li>
</ul>
<div class="tab-content">
  <div class="tab-pane fade show active" id="review">
    <p>후기 게시판입니다. 별점을 매길 수 있습니다.</p>
  </div>
  <div class="tab-pane fade" id="qna">
    <p>큐앤에이 게시판입니다. 질문을 할 수 있습니다.</p>
  </div>
</div>

</div>
</div>
</div>


<br><br><br>


<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>