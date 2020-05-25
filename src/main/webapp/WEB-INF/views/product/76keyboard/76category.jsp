<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h1 align="center">${category} list</h1>
<br><br><br>

<c:forEach items="${products}" var="dto">

<div style="text-align:center;">
	<div style="width:35%; display:inline-block;">
		<img src="${dto.pImageRoute}" width="100%"/>
	</div>
	<div style="width:100px; display:inline-block;"></div>

	<div style="width:35%; display:inline-block; text-align:left;">
		<a href="/onlyKeyboardShop/productPage?category=${category}&pId=${dto.pId}"><h2>${dto.pName}</h2></a>
		<br>
		<h4>${dto.pBreifComment}</h4>
		가격 : <b>${dto.pPrice}</b> (배송비 : ${dto.pDeliveryPrice} ) <br><br>
		상세설명 : ${dto.pInformation} / 색상 : ${dto.pColors}
		<br><br>
		
	</div>
</div>
	<div style="clear:both;"></div>

</c:forEach>
<br><br><br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>
</body>
</html>