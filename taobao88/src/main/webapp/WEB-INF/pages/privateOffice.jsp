<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>taobao88. Мой аккаунт</title>
<meta name="keywords"
	content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
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
		<div class="width">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
			</div>
			<div class="side">
				<div class="side-best">
					<ul>
						
							<c:forEach items="${banner}" var="item">
								<div class="best1" style="background: url(/images/${item.photo});">
									<a href="${item.href}">
										<p>${item.price}$</p>${item.description}
									</a>
								</div>
							</c:forEach>
					</ul>
				</div>
			</div>
			<div class="main">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h1>Добро пожаловать,
						${pageContext.request.userPrincipal.name}</h1>
				</c:if>
			
			
			<c:if test="${newOrders.size() != 0}">
			<div class="alert alert-warning">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<c:if test="${newOrders.size() == 1}">
					Номер вышего нового заказа:
						<ul>
							<c:forEach items="${newOrders}" var="order">
								<li><h5>${order.idOrder}</h5></li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${newOrders.size() > 1}">
						Так как общий вес Вашего заказа превышает 20 кг, то Ваш	заказ был разделен на ${orders.size()}, их номера:
						<ul>
							<c:forEach items="${newOrders}" var="order">
								<li><h5>${order.idOrder}</h5></li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</c:if>
			<div class="alert alert-warning">
				Для того, чтобы отправить заказы продавцу, выделите галочкой те, которые хотите заказать и нажмите кнопку отправить.
			</div>
			
			<h1>Ваши заказы :</h1>
			<form name="toOrderStatus" action="${pageContext.request.contextPath}/privateOffice/toOrderStatus">
				<input type="hidden" name="idOrder" />
			</form>
			<form name="deleteOrder" action="${pageContext.request.contextPath}/privateOffice/deleteOrder">
				<input type="hidden" name="idOrderForDelete" />
			</form>
			<form name="changeOrder" action="${pageContext.request.contextPath}/privateOffice/changeOrder">
				<input type="hidden" name="idOrderForChange" />
			</form>
			<form name="toOrder" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/privateOffice/toOrder">
				<div class="panel-group" id="accordion">
					<table class="orders">
						<thead>
							<tr>
								<th scope="col">№</th>
								<th scope="col">Отправка заказа на почту</th>
								<th scope="col">Стоимоcть заказа без доставки</th>
								<th scope="col">Стоимоcть заказа с доставкой</th>
								<th scope="col">Изменить параметры заказа</th>
								<th scope="col">Удалить заказ</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.idOrder}
										<div id="collapseOne${order.idOrder}" class="panel-collapse collapse">
														<div class="panel-body">
															<table class="simple-little-table">
																<tr>
																	<td colspan="2"><a href="${order.hrefGoods}">Ссылка	на заказ</a></td>
																</tr>
																<tr>
																	<td>Название продукции :</td>
																	<td>${order.nameGoods}</td>
																</tr>
																<tr>
																	<td>Количество единиц продукции :</td>
																	<td>${order.amountGoods}</td>
																</tr>
																<tr>
																	<td>Вес единицы продукции :</td>
																	<td>${order.weightGoods}</td>
																</tr>
																<tr>
																	<td>Стоимость единицы продукции :</td>
																	<td>${order.priceGoods}</td>
																</tr>
																<tr>
																	<td>Доставка по китаю :</td>
																	<td><c:if test="${order.chinaGoods == ''}">
                                                         					Не нужна
                                                     					</c:if>
                                                     					<c:if test="${order.chinaGoods != ''}">
                                                         					${order.chinaGoods} $
                                                     					</c:if>
                                                     				</td>
																</tr>
																<c:if test="${order.colorGoods != ''}">
																	<tr>
																		<td>Цвет продукции :</td>
																		<td>${order.colorGoods}</td>
																	</tr>
																</c:if>
																<c:if test="${order.sizeGoods != ''}">
																	<tr>
																		<td>Размер продукции :</td>
																		<td>${order.sizeGoods}</td>
																	</tr>
																</c:if>
																<tr>
																	<td>Фотоотчет :</td>
																	<td><c:if test="${order.photoGoods == 'true'}">
                                                         					Нужен
                                                     					</c:if>
                                                     					<c:if test="${order.photoGoods != 'true'}">
                                                         					Не нужен
                                                     					</c:if>
                                                     				</td>
																</tr>
															</table>
														</div>
													</div>
												</td>
												<td>
													<c:if test="${order.approve == 'true'}">
														<span class="label label-success" disabled>Заказ сделан</span>
													</c:if>
													<c:if test="${order.approve == 'false'}">
														<span class="label label-warning">Сделать заказ</span>
														<input name="idOrder" type="checkbox" value="${order.idOrder}" />
													</c:if>
												</td>
												<td>${order.priceGoods}$</td>
												<td>${order.fullPrice}$</td>
												<td>
													<a onclick="{document.changeOrder.idOrderForChange.value=${order.idOrder};document.changeOrder.submit();}" style="cursor: pointer; color: blue">
														<img src="${pageContext.request.contextPath}/resources/img/fill.png">
													</a>
												</td>
												<td>
													<img name="deleteNews" src="${pageContext.request.contextPath}/resources/img/card.png" onclick="{document.deleteOrder.idOrderForDelete.value=${order.idOrder};document.deleteOrder.submit();}">
												</td>
											</tr>

						</c:forEach>
						</tbody>
					</table>
				</div>
				<input type="submit" class="submitbutton" value="Отправить" />
			</form>
			<form name="ADMIN" action="${pageContext.request.contextPath}/hello">
				<input type="hidden" name="currentPageFromPage" value="1" />
			</form>
			<c:choose>
				<c:when test="${currentPage > 1 }">&nbsp;
					<a onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';document.ADMIN.submit();}" style="cursor: pointer;">Пред.</a>&nbsp;
     			</c:when>
				<c:otherwise>
         			&nbsp;Пред.&nbsp;|
     			</c:otherwise>
			</c:choose>
			<c:forEach items="${countOfPages}" var="CountOfPages">
				<a onclick="{document.ADMIN.currentPageFromPage.value = '${CountOfPages}';document.ADMIN.submit();}" style="cursor: pointer; <c:if test="${currentPage == CountOfPages}">color: black !important</c:if>">
					${CountOfPages}
				</a>
			</c:forEach>
			<c:choose>
				<c:when test="${currentPage < countOfPages.size()}">&nbsp;
					<a onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';document.ADMIN.submit();}" style="cursor: pointer;">След.|</a>
					&nbsp;
     			</c:when>
				<c:otherwise>
         			&nbsp;След.&nbsp;
     			</c:otherwise>
			</c:choose>
		</div>
	</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>