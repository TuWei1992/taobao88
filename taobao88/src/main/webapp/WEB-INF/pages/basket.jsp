<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник Taobao</title>
<meta charset="utf-8">
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		$('#basket').text('${basket.size()}');
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
 	<div class="width">
		<div class="content">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/basket">Корзина</a></span>
			</div>
			<div>
			<h1>Добро пожаловать, ${pageContext.request.userPrincipal.name}</h1>
			<div class="control">
				<c:choose>
					<c:when test="${basket.size() != 0}">
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
						<c:forEach items="${basket}" var="good">
							<tr>
								<td>
									<a href="${pageContext.request.contextPath}/item?id=${good.recomendation.id}" class="product-img">
										<img src="/images/${good.recomendation.photo}" />
									</a>
								</td>
								<td  class="product">
									<h2>
										<a href="${pageContext.request.contextPath}/item?id=${good.recomendation.id}">${good.recomendation.description}</a>
									</h2>
						     		<div class="property">
										<span class="order">№: ${good.idGoods}</span>
										<div class="row-form">
											<div class="overflow">Цвет: 
												<c:choose>
													<c:when test="${good.colorGoods == null }">
														<font color="red">Не выбран</font>
													</c:when>
													<c:otherwise>
														<span>${good.colorGoods}</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row-form">
											<div class="overflow">Размер: 
												<c:choose>
													<c:when test="${good.sizeGoods == null }">
														<font color="red">Не выбран</font>
													</c:when>
													<c:otherwise>
														<span>${good.sizeGoods}</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row-form">
											<div class="overflow">Количество: 
												<c:choose>
													<c:when test="${good.amountGoods == null }">
														<font color="red">Не выбран</font>
													</c:when>
													<c:otherwise>
														<span>${good.amountGoods}</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row-form">
											<div class="overflow">Фотоотчет: 
												<c:choose>
													<c:when test="${good.photoGoods == null }">
														<font color="red">Не выбран</font>
													</c:when>
													<c:otherwise>
														<span>да</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
					        	</td>  
								<td>
									<div>
										<span>$ <span class="price">${good.recomendation.price}</span></span>                            
									</div>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/fill?id=${good.idGoods}"><img src="${pageContext.request.contextPath}/resources/img/fill.png"></a>
									<a href="" onclick="removeFromBasket('${pageContext.request.contextPath}',${good.idGoods});"><img src="${pageContext.request.contextPath}/resources/img/card.png"></a>
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