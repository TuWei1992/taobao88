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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slider.css" type="text/css">
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
	<jsp:include page="modal/alert_modal.jsp" />
	<div id="wrapper">
		<jsp:include page="partials/index_header.jsp"/>
	</div>
	<div class="width">
		<jsp:include page="partials/horizontal_menu.jsp"/>
		<div class="side">
			<div class="side-menu">
				<jsp:include page="info_block/side_menu.jsp"/>
			</div>
			<div class="side-best">
				<ul>
					<li><a href="#">Самое популярное</a></li>
					<c:forEach items="${banner}" var="item">
						<div class="best1" style="background: url(/images/${item.photo}) no-repeat; background-size: cover;">
							<a href="${item.href}">
								<p>${item.price}$</p>${item.description}
							</a>
						</div>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="main">
			<c:choose>
				<c:when test="${recomendations != null}">
					<jsp:include page="info_block/index_block.jsp" />
				</c:when>
				<c:when test="${brands != null }">
					<jsp:include page="info_block/brands_block.jsp" />
				</c:when>
				<c:when test="${discount != null }">
					<jsp:include page="info_block/discount_block.jsp" />
				</c:when>
				<c:when test="${free != null }">
					<jsp:include page="info_block/free_block.jsp" />
				</c:when>
				<c:when test="${comments != null }">
					<jsp:include page="info_block/comments_block.jsp" />
				</c:when>
			</c:choose>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>