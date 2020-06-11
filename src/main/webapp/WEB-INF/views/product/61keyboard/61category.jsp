<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.webProject.dto.AdditionalPrice" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
.isSoldout {
	font-weight: bold;
	color: red;
	font-size: x-large;
}

</style>
</head>
<body>
<%!int deliveryPirce; %>
<%deliveryPirce = AdditionalPrice.deliveryPrice; %>
<%@ include file="/WEB-INF/views/menubar_top.jsp"%>
<br>
<h1 align="center">${category} list</h1>
<br><br><br>

<c:forEach items="${products}" var="dto">

<div style="text-align:center;">
	<div style="width:35%; display:inline-block; vertical-align: top;">
		<img src="${dto.pImageRoute}" width="100%"  />
	</div>
	<div style="width:100px; display:inline-block;"></div>

	<div style="width:35%; display:inline-block; text-align:left;">
		<h2 style="display: inline;"><a href="/onlyKeyboardShop/productPage?category=${category}&pId=${dto.pId}">${dto.pName}</a></h2>
		&nbsp&nbsp&nbsp
		<c:set var="stock" value="${dto.pStock}" />
		<fmt:formatNumber value="${stock}" type="number" var="stockNum" />
		<c:if test="${stockNum <= 0}">
		<span class="isSoldout">품절입니다</span>
		</c:if>
		<br>
		<h4>${dto.pBreifComment}</h4>
		가격 : <b>${dto.pPrice}</b> (배송비 : <%=deliveryPirce%> ) <br><br>
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