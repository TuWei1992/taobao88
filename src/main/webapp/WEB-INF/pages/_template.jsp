<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник	Taobao</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
    <script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
    <![endif]-->
<!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="/css/ie.css"/>
    <![endif]-->
    <script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
    <script type="text/javascript">
    	$(function() {
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
		${topMenu.menuDescription}
	</div>
	
	<!-- FOOTER -->
	<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>