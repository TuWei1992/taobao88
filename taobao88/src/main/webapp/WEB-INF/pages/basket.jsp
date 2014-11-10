<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник Taobao</title>
<meta charset="utf-8">
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#basket').text('${basket}');
		$('#translate').click(function() {
        	translate();
        });
	});
</script>
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
<script  language="JavaScript" src="/js/png.js" type="text/JavaScript"></script>
<![endif]-->
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="/css/ie.css"/>
<![endif]-->
</head>
<body>
	<div id="wrapper">
 	<!-- HEADER -->
 	<jsp:include page="partials/index_header.jsp"/>
 	
 	<form name="changeOrder" action="${pageContext.request.contextPath}/privateOffice/changeOrder">
		<input type="hidden" name="idOrderForChange" />
	</form>
	<form name="deleteOrder" action="${pageContext.request.contextPath}/privateOffice/deleteOrder">
		<input type="hidden" name="idOrderForDelete" />
	</form>
	 	
 	<div class="width">
		<div class="content">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/basket">Корзина</a></span>
			</div>
			<div>
			<h1>Добро пожаловать, ${pageContext.request.userPrincipal.name}</h1>
			<div class="control">
				<c:if test="${newOrders.size() != 0}">
					<c:if test="${newOrders.size() == 1}">
						<div class="alert alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
							Номер вышего нового заказа:
							<ul>
								<c:forEach items="${newOrders}" var="order">
									<li><h5>${order.idOrder}</h5></li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<c:if test="${newOrders.size() > 1}">
						<div class="alert alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
							Так как общий вес Вашего заказа превышает 20 кг, то Ваш	заказ был разделен на ${orders.size()}, их номера:
							<ul>
								<c:forEach items="${newOrders}" var="order">
									<li><h5>${order.idOrder}</h5></li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</c:if>
				<div class="alert alert-warning">
					Для того, чтобы отправить заказы продавцу, выделите галочкой те, которые хотите заказать и нажмите кнопку отправить.
				</div>
					
				<c:choose>
					<c:when test="${orders.size() !=0 }">
						<div class="o_head">
                          	<h3>Товары</h3>
                      	</div>
						<table class="orders">
							<thead>
								<tr>
									<th colspan="2">Товар</th>
									<th>Стоимость</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orders}" var="order">
									<tr>
										<td>
											<c:choose>
												<c:when test="${order.goods.recomendation != null}">
													<a href="${pageContext.request.contextPath}/item?id=${order.goods.recomendation.id}" class="product-img">
														<img src="/images/${order.goods.recomendation.photo}" />
													</a>
												</c:when>
												<c:otherwise>
													<a href="${order.goods.hrefGoods}" class="product-img">
														<img src="/images/image_missing.png" />
													</a>
												</c:otherwise>
											</c:choose>
										</td>
										<td class="product">
											<h2>
												<c:choose>
													<c:when test="${order.goods.recomendation != null}">
														<a href="${pageContext.request.contextPath}/item?id=${order.goods.recomendation.id}">${order.goods.recomendation.description}</a>
													</c:when>
													<c:otherwise>
														<a href="${order.goods.hrefGoods}">${order.goods.nameGoods}</a>
													</c:otherwise>
												</c:choose>
											</h2>
											<div class="property">
												<span class="order">№: ${order.goods.idGoods}</span>
												<div class="row-form">
													<div class="overflow">Цвет:
													<c:choose>
														<c:when test="${order.goods.colorGoods == null}">
															<font color="red">Не выбран</font>
														</c:when>
														<c:otherwise>
															<span>${order.goods.colorGoods}</span>
														</c:otherwise>
													</c:choose>
													</div>
												</div>
												<div class="row-form">
													<div class="overflow">Размер:
														<%--<c:choose>
                                                    		<c:when test="${empty order.goods.sizeGoods}">
                                                        		<font color="red">Не выбран</font>
															</c:when>
															<c:otherwise>
                                                        		<span>${order.goods.sizeGoods}</span>
                                                    		</c:otherwise>
                                                		</c:choose>--%>
													</div>
												</div>
												<div class="row-form">
													<div class="overflow">Количество:
														<c:choose>
															<c:when test="${empty order.goods.amountGoods}">
																<font color="red">Не выбран</font>
															</c:when>
															<c:otherwise>
																<span>${order.goods.amountGoods}</span>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
												<div class="row-form">
													<div class="overflow">Фотоотчет:
														<%--<c:choose>
															<c:when test="${order.goods.photoGoods == null}">
																<font color="red">Не выбран</font>
																</c:when>
															<c:otherwise>
                                                        		<span>${order.goods.photoGoods}</span>
                                                    		</c:otherwise>
                                                		</c:choose>--%>
													</div>
												</div>
											</div>
										</td>
										<td>
											<div>
												<span>$ <span class="price">${order.fullPrice}</span></span>
											</div>
										</td>
										<td>
											<c:choose>
												<c:when test="${order.goods.recomendation != null}">
													<a href="${pageContext.request.contextPath}/fill?id=${order.goods.idGoods}">
														<img src="${pageContext.request.contextPath}/resources/img/fill.png">
													</a>
												</c:when>
												<c:otherwise>
													<a onclick="{document.changeOrder.idOrderForChange.value=${order.idOrder};document.changeOrder.submit();}" style="cursor: pointer; color: blue">
														<img src="${pageContext.request.contextPath}/resources/img/fill.png">
													</a>
												</c:otherwise>
											</c:choose>
                                    		<a onclick="removeFromBasket('${pageContext.request.contextPath}',${good.idGoods});">
												<img src="${pageContext.request.contextPath}/resources/img/card.png">
											</a>
										</td>
									</tr>	
								</c:forEach>
							</tbody>
						</table>
						<div class="btn-card">
                    		<a href="${pageContext.request.contextPath}/privateOffice/fromBasket">Оформить заказ</a>
                    		<p>Итого ожидаемая сумма для оплаты: <span>$${totalPrice}</span></p>
                		</div>
					</c:when>
					<c:otherwise>
                    	<div class="o_head">
                        	<h3>В вашей корзине пока нет товаров.</h3>
                    	</div>
                	</c:otherwise>
				</c:choose>
				
			</div>
				<div class="goods-list">
       					<c:forEach items="${recomendations}" var="rec">
							<div id="${rec.id}">
								<div class="shop-item">
									<div>
										<div class="item-box">
											<div class="item-thumb">
												<a href="${pageContext.request.contextPath}/item?id=${rec.id}" target="_blank" class="item-lnk thumbnail"> 
													<img src="/images/${rec.photo}" alt="New fashion">
												</a>
											</div>
											<div class="item-meta">
												<div class="item-ttl">
													<a href="${pageContext.request.contextPath}/item?id=${rec.id}" target="_blank" title="${rec.description}">${rec.description}</a>
												</div>
												<div class="item-prc">
													<span>${rec.price}</span>
												</div>
												<div class="item-buy">
													<c:choose>
														<c:when test="${pageContext.request.userPrincipal.name != null}">
															<a href="#" onclick="addToBasket('${pageContext.request.contextPath}',${rec.id});">В корзину</a>
														</c:when>
														<c:otherwise>
															<a href="${pageContext.request.contextPath}/login" target="_blank">В корзину</a>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>	
					</div>
			</div>		
		</div>
	</div>
</div>
<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>