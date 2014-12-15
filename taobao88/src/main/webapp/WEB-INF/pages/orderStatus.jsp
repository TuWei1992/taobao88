<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            $('[data-toggle="tooltip"]').tooltip();
        });      
    </script>
</head>
<body>
	<div class="wrapper">
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
					<span><a href="${pageContext.request.contextPath}/privateOffice/showPackage?idPackage=${orderT.packageT.idPackage}">Посылка №${orderT.packageT.idPackage}</a></span>
					<span>></span>
					<span><a href="${pageContext.request.contextPath}/privateOffice/showPackage?idOrder=${orderT.idOrder}">Заказ №${orderT.idOrder}</a></span>
				</div>
				
				<div class="side">
					<div class="side-chek">
						<p>Состояние заказа:</p>
						<ul>
							<c:forEach begin="0" end="${orderT.ordersStatuses.size() - 1}" var="loop">
								<c:forEach items="${allStatuses}" var="status">
									<c:set var="oStatus" value="${orderT.ordersStatuses.get(loop)}"/>
									<c:if test="${status.id == oStatus.status.id}">
										<li>
											${oStatus.status.statusName} <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${oStatus.createdAt}"/>
											<c:if test="${loop == orderT.ordersStatuses.size() - 1}"><i class ="error"></i></c:if>
											<c:if test="${loop != orderT.ordersStatuses.size() - 1}"><i class ="chek"></i></c:if>
										</li>
									</c:if>
								</c:forEach>
							</c:forEach>
    					</ul>
					</div>
				</div>
				<div class="main">
					<div class="col-md-offset-1">
						<span>Ваш баланс: </span><span class="label label-success">$${balance}</span>
					</div>
					<div class="control">
						<table class="orders">
                			<thead>
                    			<tr>
                        			<th colspan="2">Товар</th>
                        			<th>Состояние</th>
                        			<th>Стоимость</th>
									<th>Действия</th>
                    			</tr>
                			</thead>
							<tbody>
                       				<tr>
										<td><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${orderT.idOrder}" class="product-img">
												<img src="${pageContext.request.contextPath}/resources/img/buy.png"/>
											</a>
										</td>
								    	<td class="product">
											<h2><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${orderT.idOrder}">${goods.nameGoods}</a></h2>
						     				<div class="property">
												<span class="order">№: ${orderT.idOrder}</span>  
												<div> Цвет: <span class="color">${goods.colorGoods}</span></div>
												<div> Количество: <span class="size">${goods.amountGoods}</span></div>
												<div> Размер: <span class="size">${goods.sizeGoods}</span></div>
												<c:if test="${orderT.changed == 1}">
													<div><span class="label label-danger">Замена</span></div>
												</c:if>
												<div> 
													<c:if test="${not empty goods.complexGoods}">
														<span class="label label-success" data-toggle="tooltip" data-placement="top" title="${goods.complexGoods}">Примечания</span>
													</c:if>
												</div>
											</div>
					        			</td>  
										<td>
											<span>
												<c:set var="i" value="${orderT.ordersStatuses.size()}"/>
												${orderT.ordersStatuses.get(i - 1).status.statusName}<br>
												<span class="label label-warning"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${orderT.ordersStatuses.get(i - 1).createdAt}"/></span>
											</span>
										</td>
										<td>
											<div>
												<span>$ <span class="price">${orderT.fullPrice}</span></span>                            
											</div>
										</td>
										<td>
											<c:if test="${orderT.packageT.purchased == 0}">
												<a href="${pageContext.request.contextPath}/privateOffice/deleteOrder?idOrderForDelete=${orderT.idOrder}&idPackage=${orderT.packageT.idPackage}" data-toggle="tooltip" data-placement="top" title="Удалить">
													<img src="${pageContext.request.contextPath}/resources/img/card.png">
												</a>
											</c:if>
											<c:set var="statusId" value="${orderT.ordersStatuses.get(i - 1).status.id}"/>
											<c:if test="${statusId == 7 || statusId == 9 || statusId == 10 || statusId == 11}">
												<a href="${pageContext.request.contextPath}/privateOffice/changeOrder?idOrderForChange=${orderT.idOrder}" data-toggle="tooltip" data-placement="top" title="Заменить">
													<img src="${pageContext.request.contextPath}/resources/img/fill.png">
												</a>
												<a href="${pageContext.request.contextPath}/privateOffice/deleteOrder?idOrderForDelete=${orderT.idOrder}&idPackage=${orderT.packageT.idPackage}" data-toggle="tooltip" data-placement="top" title="Удалить">
													<img src="${pageContext.request.contextPath}/resources/img/card.png">
												</a>
											</c:if>
										</td>
									</tr>
           					</tbody>
       					</table>
       					<c:set var="i" value="${orderT.ordersStatuses.size()}"/>
       						<div class="btn-card">
								<c:if test="${orderT.packageT.purchased == 1 && orderT.changed != 1}">
									<h3><label class="label label-success pull-right">Оплачено</label></h3>
								</c:if>
							</div>
					</div>		
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>