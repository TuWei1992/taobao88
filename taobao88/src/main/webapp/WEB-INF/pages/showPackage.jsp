<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            $('[data-toggle="tooltip"]').tooltip();

			$('.pay_btn').click(function() {
				var balance = ${balance};
				var fullPrice = ${packageT.fullPrice};
				if (balance < fullPrice) {
					$('#notEnoughMoneyModal').modal('show');
				} else {
					$('#paymentAgreeModal').modal('show');
				}
			});
			
			$('.addition_pay_btn').click(function() {
				var balance = ${balance};
				var fullPrice = ${packageT.fullPrice};
				if (balance < fullPrice) {
					$('#notEnoughMoneyModal').modal('show');
				} else {
					$('#additionPaymentAgreeModal').modal('show');
				}	
			});
			
			$('.return_money_btn').click(function() {
				$('#returnMoneyAgreeModal').modal('show');
			});
        });      
    </script>
</head>
<body>

	<jsp:include page="modal/not_enough_money_modal.jsp"/>
	<jsp:include page="modal/payment_agree_modal.jsp"/>
	<jsp:include page="modal/loading_modal.jsp"/>
	<jsp:include page="modal/addition_payment_agree_modal.jsp"/>
	<jsp:include page="modal/return_money_agree_modal.jsp"/>
	
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
							<c:forEach begin="0" end="${packageT.packagesStatuses.size() - 1}" var="loop">
								<c:forEach items="${allStatuses}" var="status">
									<c:set var="pStatus" value="${packageT.packagesStatuses.get(loop)}"/>
									<c:if test="${status.id == pStatus.status.id}">
										<li>
											${pStatus.status.statusName} <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${pStatus.createdAt}"/>
											<c:if test="${loop == packageT.packagesStatuses.size() - 1}"><i class ="error"></i></c:if>
											<c:if test="${loop != packageT.packagesStatuses.size() - 1}"><i class ="chek"></i></c:if>
										</li>
									</c:if>
								</c:forEach>
							</c:forEach>
							<c:if test="${not empty packageT.tracknumber}">
								<li>Номер отправления: ${packageT.tracknumber}</li>
							</c:if>						
    					</ul>
					</div>
				</div>
				<div class="main">
					<div class="control">
						<div>
							<span>Сервис по отслеживанию посылок: </span><span class="label label-success"><a href="http://post-tracker.ru/" target="_blank">Post-Tracker</a></span><br>
							<span>Ваш баланс: </span><span class="label label-success">$${balance}</span>
						</div>
						<div class="alert alert-warning">
							Для детального просмотра информации об отдельном товаре в посылке щелкните по значку товара.
						</div>
						<table class="orders">
                			<thead>
                    			<tr>
                        			<th colspan="2">Товары в посылке</th>
                        			<th>Состояние</th>
                        			<th>Стоимость</th>
                        			<th>Действия</th>
                    			</tr>
                			</thead>
							<tbody>
								<c:forEach items="${packageT.orders}" var="order">
                       				<tr>
										<td><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${order.idOrder}" class="product-img">
												<img src="${pageContext.request.contextPath}/resources/img/buy.png"/>
											</a>
										</td>
								    	<td class="product">
											<h2><a href="${pageContext.request.contextPath}/privateOffice/toOrderStatus?idOrder=${order.idOrder}">${order.goods.nameGoods}</a></h2>
						     				<div class="property">
												<span class="order">№: ${order.idOrder}</span>  
												<div> Цвет: <span class="color">${order.goods.colorGoods}</span></div>
												<div> Количество: <span class="size">${order.goods.amountGoods}</span></div>
												<div> Размер: <span class="size">${order.goods.sizeGoods}</span></div>
												<c:if test="${order.changed == 1}">
													<div><span class="label label-danger">Замена</span></div>
												</c:if>
												<div> 
													<c:if test="${not empty order.goods.complexGoods}">
														<span class="label label-success" data-toggle="tooltip" data-placement="top" title="${order.goods.complexGoods}">Примечания</span>
													</c:if>
												</div>
											</div>
					        			</td>  
										<td>
											<span>
												<c:set var="i" value="${order.ordersStatuses.size()}"/>
												${order.ordersStatuses.get(i - 1).status.statusName}<br>
												<span class="label label-warning"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${order.ordersStatuses.get(i - 1).createdAt}"/></span>							
											</span>
										</td>
										<td>
											<div>
												<span>$ <span class="price">${order.fullPrice}</span></span>                            
											</div>
										</td>
										<td>
											<c:set var="i" value="${order.ordersStatuses.size()}"/>
											<c:if test="${order.ordersStatuses.get(i - 1).status.id == 7 || order.ordersStatuses.get(i - 1).status.id == 9}">
												<a href="${pageContext.request.contextPath}/privateOffice/changeOrder?idOrderForChange=${order.idOrder}" data-toggle="tooltip" data-placement="top" title="Заменить">
													<img src="${pageContext.request.contextPath}/resources/img/fill.png">
												</a>
											</c:if>
											<c:if test="${packageT.purchased == 0}">
												<a href="${pageContext.request.contextPath}/privateOffice/deleteOrder?idOrderForDelete=${order.idOrder}&idPackage=${packageT.idPackage}" data-toggle="tooltip" data-placement="top" title="Удалить"><img src="${pageContext.request.contextPath}/resources/img/card.png"></a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
           					</tbody>
       					</table>
	    				<div class="btn-card">
	    					<c:if test="${packageT.purchased == 0}">
								<a href="javascript:void(0);" class="pay_btn">Оплатить</a>
							</c:if>
							<p>Общий вес: <span>${packageT.weight}кг</span> 
							   Метод доставки: <span>${packageT.postService.serviceName}</span> 
							   
							   <c:if test="${packageT.fullPrice != packageT.purchasedAmount}">
							   		Оплачено ранее: <span>$ ${packageT.purchasedAmount}</span>		
							   </c:if>
							   
							   Стоимость посылки: <span>$ ${packageT.fullPrice}</span>
							   
							   <c:if test="${packageT.purchased == 1 && (packageT.fullPrice == packageT.purchasedAmount)}">
							      <label class="label label-success pull-right">Оплачено</label>
							   </c:if>
							   <c:if test="${packageT.purchased == 1 && (packageT.fullPrice != packageT.purchasedAmount) && (packageT.fullPrice - packageT.purchasedAmount > 0)}">
							      <a href="javascript:void(0);" class="addition_pay_btn">Доплатить</a>
							   </c:if>
							   <c:if test="${packageT.purchased == 1 && (packageT.fullPrice != packageT.purchasedAmount) && (packageT.fullPrice - packageT.purchasedAmount < 0)}">
							      Разница: <span>$ ${packageT.purchasedAmount - packageT.fullPrice}</span>
							      <a href="javascript:void(0);" class="return_money_btn">Вернуть разницу на счет</a>
							   </c:if>
							</p>
						</div>
					</div>			
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>