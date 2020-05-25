<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=77443487-FD58-35A0-8633-FB8E85DAB6C7"></script>

	

</head>
<body>

<%@ include file="/WEB-INF/views/menubar_top.jsp"%>


	<div style="width: 50%; float:left;">
		 <div class="btn-group">
			<button type="button" class="btn btn-dark" onclick="javascript:move(14124500.590359,4503100.7639686,13);" >회사위치</button>
  			<button type="button" class="btn btn-dark" onclick="javascript:move(14146300.590359,4527600.7639686,13);" >개발자 학교</button>
   
		</div>
		 <div id="vmap" style="width:100%;height:400px;left:0px;top:0px"></div>
		 
		 <script> 
		 
		 camera = vw.ol3.CameraPosition;
		 camera.center = [14124500.590359,4503100.7639686];
		 camera.zoom=16;
		 
		 vw.ol3.MapOptions = {
			      basemapType: vw.ol3.BasemapType.GRAPHIC
			    , controlDensity: vw.ol3.DensityType.EMPTY
			    , interactionDensity: vw.ol3.DensityType.BASIC
			    , controlsAutoArrange: true
			    , homePosition: vw.ol3.CameraPosition
			    , initPosition: camera
			   }; 
			      
	   	var vmap = new vw.ol3.Map("vmap",  vw.ol3.MapOptions);
	   	
	   	var markerLayer = new vw.ol3.layer.Marker(vmap);
	    vmap.addLayer(markerLayer);
	    addMarker();
				 
		function addMarker() {
		 vw.ol3.markerOption = {
		  x : 126.882, //14123900.590359,4503100.7639686,13
		  y : 37.458,
		  epsg : "EPSG:4326",
		  title : '회사위치',
		  contents : '저희 집근처로 표시했습니다.',
		  iconUrl : 'http://map.vworld.kr/images/ol3/marker_blue.png', 
		  text : {
		      offsetX: 0.5, //위치설정
		      offsetY: 20,   //위치설정
		      font: '12px Calibri,sans-serif',
		      fill: {color: '#000'},
		      stroke: {color: '#fff', width: 2},
		      text: '회사위치'
		  },
		  attr: {"id":"maker01","name":"속성명1"}    
		 };
		 markerLayer.addMarker(vw.ol3.markerOption);
		 
		 vw.ol3.markerOption = {
				  x : 127.0776, //14123900.590359,4503100.7639686,13
				  y : 37.63090,
				  epsg : "EPSG:4326",
				  title : '학교위치',
				  contents : '제가 졸업한 학교입니다.',
				  iconUrl : 'http://map.vworld.kr/images/ol3/marker_blue.png', 
				  text : {
				      offsetX: 0.5, //위치설정
				      offsetY: 20,   //위치설정
				      font: '12px Calibri,sans-serif',
				      fill: {color: '#000'},
				      stroke: {color: '#fff', width: 2},
				      text: '학교위치'
				  },
				  attr: {"id":"maker01","name":"속성명1"}    
				 };
				 markerLayer.addMarker(vw.ol3.markerOption);
		 
		 this.markerLayer.showAllMarker();
		} 


		//document.write(vw.ol3.CameraPosition.center);
		function move(x,y,z){
		 var _center = [ x, y ];
		  
		 var z = z;
		 var pan = ol.animation.pan({
		  duration : 3000, //3초간에니메이션으로 위치이동
		  source : (vmap.getView().getCenter())
		 });
		 vmap.beforeRender(pan);
		 vmap.getView().setCenter(_center);
		 setTimeout("fnMoveZoom()", 4000);//4초후 15만큼 zoom
		 
		 
		}
			   
		function fnMoveZoom() {
		  zoom = vmap.getView().getZoom();
		  if (16 > zoom) {
		  vmap.getView().setZoom(15); //숫자가 클수록 zoom
		  }
			   
		};
			 
			 
 </script>
	</div>
	
	<div style="width: 48%; float:right; margin-top: 20px; ">
		<h2>오시는 길</h2>
		<br>
		저희 only keyboard shop의 회사위치는,<br> 개발자인 <u>제가 사는 곳 주변</u>으로 표시해 두었습니다.<br>
		옆의 지도에 마커를 표시해 두었습니다.<br><br>
		저희 회사의 위치는 광명쪽으로, <b>철산역</b>에서 버스로 이동할 수 있습니다. <br>
		많은 버스 노선들이 저희 회사를 경유하고 있습니다. 그래서 <b>교통이 편리</b>합니다.<br><br>
		추가적으로 제가 졸업한 학교 위치도 표시해 두었습니다.. <br>
		저는 <b>서울과학기술대학교를 졸업</b>하였습니다.<br>
		<u>왼쪽 상단의 버튼</u>을 통해서 해당 위치로 이동하실 수 있습니다.<br><br>
		<b>가성비 키보드 only keyboard shop!</b> 많이 애용해주시길 부탁드립니다. 감사합니다.
		
	</div>

<div style="clear:both;"></div>
<br>
<%@ include file="/WEB-INF/views/infobar_bottom.jsp"%>

</body>
</html>