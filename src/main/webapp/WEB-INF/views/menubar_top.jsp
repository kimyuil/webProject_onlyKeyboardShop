<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<style>
	.upper_space{
		height : 75px;
	}
	li{list-style:none;}
	a{text-decoration:none;}
	
	.header{
		background-color:blue;
		height : 75px;
		border:1px;
		position:fixed;
		width:100%;
		z-index:9999;
		top:0;
		left:0;
	}
	.menu_wrapper{
		margin: 0 auto;
		width: 1170px;
		/* height : 75px; */
		
	}
	.menu_wrapper li{font-size: x-large}
	
	.dropdown {
		
	  position: relative;
	  /* display : inline-block; */
	  
	}
		
	.bottom{
		display: none;
  		position: absolute;
  		background-color: #f9f9f9;
  		min-width: 160px; 
  		box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  		z-index: 1;
		height : 75px;
		top:75px; 
		width: 700;
		left: 40;
	}
	.bottom .left:hover{
		background-color: #f1f1f1
	}
	
	.dropdown-content a {
  		color: black;
  		padding: 12px 16px;
  		display: inline;
	}

	
	
	
	.left{
		display : inline-block;
		cursor: pointer;
		float : left;
		top : 10px;
		/*color:red;
		line-height:75px; */
	}
	.left li{float : left;}
	.left a{
		linge-height:75px;
		color: white;
		padding: 0 15px;
		display : inline-block;
	}
	
	.right{
		position:absolute;
		right : 0;
		/* float:right; */
	}
	.right li{float : left;}
	.right a{
		linge-height:75px;
		color: white;
		padding: 0 15px;
		display : block;
	}
	
	
	.dropdown:hover .bottom {
  		display: block;
	}

</style>
<script src="//code.jquery.com/jquery-3.3.1.min.js">
</script>

</head>
<body>

<div class="header">
	<div class="menu_wrapper">
		<nav class="dropdown">
			<ul class="left">
				<li><a href="/onlyKeyboardShop/">onlykeyboard</a></li> 	
				<li><a href="/onlyKeyboardShop/brand_about">brand_about</a></li> 
				<li><a href="#">product</a> </li>
				<li><a href="#">community</a></li>
			</ul>
		
			<div class="bottom">
				<a href="#">Link 1</a>
  				<a href="#">Link 2</a>
  				<a href="#">Link 3</a>
			</div>  
		
		</nav>
		
		<nav>
		<ul class="right">
			<li><a href="#">login</a></li>
			<li><a href="#">장바구니</a></li> 
		</ul>
		</nav>
		 
	</div>	
</div>

<div class="upper_space"></div>



</body>
</html>