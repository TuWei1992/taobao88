<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>taobao88. Мои посылки</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
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
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
        });      
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
					<span><a href="${pageContext.request.contextPath}/privateOffice/toPackages">Мои посылки</a></span>
					<span>></span>
					<span><a href="${pageContext.request.contextPath}/privateOffice/showPackage?idPackage=${packageT.idPackage}">Посылка №${packageT.idPackage}</a></span>
				</div>
	    		<div class="side">
					<div class="side-chek">
						<p>Состояние посылки:</p>
						<ul>
							<li>
								<c:choose>
									<c:when test="${packageT.status.approvePackage == 'true'}">
										Посылка подтверждена<i class ="chek"></i>
									</c:when>
									<c:otherwise>
										Ожидает подтверждения<i class ="error"></i>
									</c:otherwise>
								</c:choose>
							</li>
							<li>
								<c:choose>
									<c:when test="${packageT.status.payPackage == 'true'}">
										Платёж совершен<i class ="chek"></i>
									</c:when>
									<c:otherwise>
										Ожидается платёж<i class ="error"></i>
									</c:otherwise>
								</c:choose>
							</li>
							<li>
								<c:choose>
									<c:when test="${packageT.status.ransomPackage == 'true'}">
										Товар выкуплен<i class ="chek"></i>
									</c:when>
									<c:otherwise>
										Происходит выкуп товара<i class ="error"></i>
									</c:otherwise>
								</c:choose>
							</li>
							<li>
								<c:choose>
									<c:when test="${packageT.status.readyPackage == 'true'}">
										Товар находится на офисе отправки<i class ="chek"></i>
									</c:when>
									<c:otherwise>
										Товар находится на офисе отправки<i class ="error"></i>
									</c:otherwise>
								</c:choose>
							</li>
							<!-- <li>Ожидает ответа на спорные впоросы<i class ="chek"></i></li>
							<li>Посылка дополняется<i class ="chek"></i></li>
							<li>Посылка подготовлена<i class ="error"></i></li> -->
							<li>
								<c:choose>
									<c:when test="${packageT.status.importPackage == 'true'}">
										Посылка отправлена заказчику<i class ="chek"></i>
									</c:when>
									<c:otherwise>
										Подготовка к отправке заказчику<i class ="error"></i>
									</c:otherwise>
								</c:choose>
							</li>
    					</ul>
					</div>
				</div>
				<div class="main">
					<div class="col-md-offset-1">
						<span>Сервис по отслеживанию посылок - </span><span class="label label-success"><a href="http://post-tracker.ru/" target="_blank">Post-Tracker</a></span>
					</div>
					<div class="control">
						<table class="orders">
                			<thead>
                    			<tr>
                        			<th colspan="2">Товары в посылке</th>
                        			<th>Состояние</th>
                        			<th>Стоимость</th>
                        			<th>Номер отправления</th>
									<th></th>
                    			</tr>
                			</thead>
							<tbody>
								<c:forEach items="${orders}" var="order">
                       				<tr>
										<td><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${order.idOrder}" class="product-img">
												<img src="${pageContext.request.contextPath}/resources/img/buy.png"/>
											</a>
										</td>
								    	<td class="product">
											<h2><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${order.idOrder}">${order.nameGoods}</a></h2>
						     				<div class="property">
												<span class="order">№: ${order.idOrder}</span>  
												<div> Цвет: <span class="color">${order.colorGoods}</span></div>
												<div> Количество: <span class="size">${order.amountGoods}</span></div>
											</div>
					        			</td>  
										<td>
											<span>
												<c:choose>
													<c:when test="${packageT.status.approvePackage == 'false'}">
														Подтверждение наличия всех товаров в посылке
													</c:when>
													<c:when test="${packageT.status.payPackage == 'false'}">
														Ожидание Вашей оплаты за все товары посылки
													</c:when>
													<c:when test="${packageT.status.ransomPackage == 'false'}">
														Товар находится на стадии выкупа
													</c:when>
													<c:when test="${packageT.status.readyPackage == 'false'}">
														Посылка готова и находится на офисе
													</c:when>
													<c:when test="${packageT.status.importPackage == 'false'}">
														Посылка отправлена заказчику
													</c:when>
												</c:choose>
											</span>
										</td>
										<td>
											<div>
												<span>$ <span class="price">${order.fullPrice}</span></span>                            
											</div>
										</td>
										<td>
											<div>
												<span>${packageT.tracknumber}</span>                  
											</div>
										</td>
										<td>
											<a href="${pageContext.request.contextPath}/privateOffice/deleteOrder?idOrderForDelete=${order.idOrder}"><img src="${pageContext.request.contextPath}/resources/img/card.png"></a>
										</td>
									</tr>
								</c:forEach>
           					</tbody>
       					</table>
	    				<div class="btn-card">
							<a href="${pageContext.request.contextPath}/privateOffice/payment?idPackage=${packageT.idPackage}">Оплатить</a>
							<p>Итого: <span>$ ${packageT.fullPrice}</span></p>
						</div>
					</div>			
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>