<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник	Taobao</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/item.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
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
	<jsp:include page="../modal/alert_modal.jsp" />
	<div id="wrapper">
		<jsp:include page="../partials/index_header.jsp"/>
	</div>
	<div class="width">
		<jsp:include page="../partials/horizontal_menu.jsp"/>
		<div class="">
			<c:forEach items="${other_menu}" var="menu">
				<c:if test="${menu.level == 0}">
					<div class="item-meta" style="float: left; margin-right: 25px; width: 250px">
						<ul>
							<li><a href="${menu.menuHref}" target="_blank"><b>${menu.menuName}</b></a></li>
							<c:forEach items="${menu.children}" var="menu">
								<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a></li>
								<c:forEach items="${menu.children}" var="menu">
									<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a></li>
									<c:forEach items="${menu.children}" var="menu">
										<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a></li>
									</c:forEach>					
								</c:forEach>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../partials/index_footer.jsp"/>
</body>
</html>