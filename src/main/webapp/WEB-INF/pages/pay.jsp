<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>taobravo.com. Мои посылки</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
	<link rel="shortcut icon" href="http://taobravo.com/favicon.ico">
<!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
    <script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
    <![endif]-->
<!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="/css/ie.css"/>
    <![endif]-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
	<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
	<script type="text/javascript">
        $(function() {
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
            
            $('.print_btn').click(function() {
            	printDiv('print_method');
            })
        });   
        
        function printDiv(divName) {
            var printContents = document.getElementById(divName).innerHTML;
            var originalContents = document.body.innerHTML;
            document.body.innerHTML = printContents;
            window.print();
            document.body.innerHTML = originalContents;
       }
    </script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="partials/index_header.jsp"/>
		<div class="content">
			<div class="width">
				<jsp:include page="partials/horizontal_menu_logged.jsp"/>
				<div class="position">
					<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
					<span>></span> 
					<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
					<span>></span>
					<span><a href="${pageContext.request.contextPath}/privateOffice/payment?idPackage=${packageT.idPackage}">Мой счёт</a></span>
				</div>
				<div class="side">
					<div class="side-menu">
						<ul>
							<c:forEach items="${paymentMethods}" var="method">
								<li>
									<a href="${pageContext.request.contextPath}/privateOffice/showBalance?method=${method.id}">${method.methodName}</a>
								</li>								
							</c:forEach>
    					</ul>
					</div>
				</div>
				<div class="main">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<h2>Добро пожаловать, ${pageContext.request.userPrincipal.name}</h2>
					</c:if>
					<div class="shop-ttl frst-ttl"><span>Методы оплаты</span></div>
					<div class="prepouse">
						Ваш счет: $${balance}
					</div>
					<c:if test="${not empty method}">
						<div class="prepouse" id="print_method" style="margin-top: 20px;">
							<h4>${method.methodName}</h4>
							<p>
								${method.methodDescription}
							</p>
						</div>
						<div class="btn-card">
							<a href="javascript:void(0);" class="print_btn">Распечатать</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>