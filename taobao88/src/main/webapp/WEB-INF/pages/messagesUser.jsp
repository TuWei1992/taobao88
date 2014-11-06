<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>taobao88. Мой аккаунт</title>
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<!-- CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/item.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">


<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="css/ie6.css"/>
<script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
<![endif]-->
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="css/ie.css"/>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
<script type="text/javascript">
$(function() {
    $('#basket').text('${basket.size()}');
    $('#translate').click(function() {
    	translate();
    });
});    
</script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="partials/index_header.jsp"/>
	</div>
		<div class="width">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
				<span>></span>
				<span><a href="${pageContext.request.contextPath}/privateOffice/showMessages">Мои сообщения</a></span>
			</div>
			<div class="main">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h1>Добро пожаловать,
						${pageContext.request.userPrincipal.name}</h1>
				</c:if>
				
				<div class="container">
					<div class="row col-sm-6 col-md-6">
						<div class="panel-group" id="accordion">
							<c:forEach items="${packages}" var="packageT">
								<div class="panel panel-default">
  									<div class="panel-heading">
  										<h4 class="panel-title">
  											<a data-toggle="collapse" data-parent="#accordion" href="#collapse${packageT.idPackage}">Посылка №${packageT.idPackage}</a>
  										</h4>
  									</div>
  									<div id="collapse${packageT.idPackage}" class="panel-collapse collapse">
  										<div class="panel-body">
    										<c:forEach items="${packageT.messages}" var="message">
    											<div class="panel <c:if test="${message.fromUser.nameUser == 'admin'}">panel-success</c:if> panel-primary">
    												<div class="panel-heading">
    													От: <b>${message.fromUser.nameUser}</b> 
    													Кому: <b>${message.toUser.nameUser}</b>
    													<span class="pull-right">Отправлено: <b>${message.createdAt}</b></span>
    												</div>
  													<div class="panel-body">
  														<p>${message.message}</p>
  													</div>
    											</div>
    										</c:forEach>
    										<form role="form" method="post" accept-charset="utf-8" action="${pageContext.request.contextPath}/privateOffice/confirmMessage">
												<input type="hidden" name="toUser" value="1">
												<input type="hidden" name="fromUser" value="${currentIdUser}">
												<input type="hidden" name="idpackage" value="${packageT.idPackage}">
												<div class="form-group">
													<label class="divider"></label>
													<textarea class="form-control order-control" id="message" name="message" placeholder="Введите текст сообщения..."></textarea>
												</div>
												<div class="form-group">
													<button role="button" class="btn btn-success" type="submit">Отправить</button>
												</div>
											</form>
  										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>