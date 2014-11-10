<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник	Taobao</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.min.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slider.css" type="text/css">
	<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
    <script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
    <![endif]-->
<!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="/css/ie.css"/>
    <![endif]-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
	<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.cslider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
	<script type="text/javascript">
        $(function() {
            $('#da-slider').cslider({
                autoplay	: true,
                bgincrement	: 450
            });
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
        });      
    </script>
</head>
<body>
	<div id="wrapper">
		<!-- HEADER -->
		<jsp:include page="partials/index_header.jsp"/>
	</div>
	<div class="width">
		<div class="shop-ttl frst-ttl"><span>404</span></div>
			<div class="prepouse">
				<div class="goods-list">
					<p><img src="${pageContext.request.contextPath}/resources/img/404.jpg" /></p>
				</div>
			</div>
	</div>
</body>
</html>