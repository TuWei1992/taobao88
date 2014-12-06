<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>taobao88. Изменить заказ</title>
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
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
				<span>Измнение заказа</span>
			</div>
			<div class="control">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/privateOffice/updateOrder" method="POST">
					<input type="hidden" id="goodsId" name="goodsId" value="${orderT.goods.idGoods}">
					<table class="table" cellpadding='50px' >
						<tr>
							<td>
								<table>
									<tr>
										<td>
											<label class="control-label" for="goodsId">Номер заказа: <span>${orderT.idOrder}</span></label>
										</td>
										<td></td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="HREFGOODS">Ссылка<a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="HREFGOODS" name="HREFGOODS" placeholder="" value="${orderT.goods.hrefGoods}" required class="form in">
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="NAMEGOODS">Название товара <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="NAMEGOODS" name="NAMEGOODS" placeholder="" value="${orderT.goods.nameGoods}" required class="form in">
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="AMOUNTGOODS">Количество единиц продукции <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="number" id="AMOUNTGOODS" max="1000" name="AMOUNTGOODS" placeholder="" value="${orderT.goods.amountGoods}" required class="form in">
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="PRICEGOODS">Стоимость единицы продукции <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" pattern="\d*\.?\d+" data-validation-pattern-message="Пример: 10, 10.343" id="PRICEGOODS" name="PRICEGOODS" placeholder="" value="${orderT.goods.priceGoods}" required class="form in">
											
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td>
											<label class="control-label" for="WEIGHTGOODS">Вес единицы продукции(в граммах) <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" pattern="\d+\.?\d+?" data-validation-pattern-message="Пример: 100, 50, 150" id="WEIGHTGOODS" name="WEIGHTGOODS" placeholder="" value="${orderT.goods.weightGoods}" required class="form in">
											
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="CHINAGOODS">Доставка по Китаю <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="CHINAGOODS" name="CHINAGOODS" max="1000" placeholder="" value="${orderT.goods.chinaGoods}" class="form in" required>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="COLORGOODS">Цвет <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="COLORGOODS" name="COLORGOODS" placeholder="" value="${orderT.goods.colorGoods}" class="form in" required>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="SIZEGOODS">Размер <a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="SIZEGOODS" name="SIZEGOODS" placeholder="" value="${orderT.goods.sizeGoods}" class="form in" required>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="COMPLEXGOODS">Комплектация<a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<input type="text" id="COMPLEXGOODS" name="COMPLEXGOODS" placeholder="" value="${orderT.goods.complexGoods}" class="form in">
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label" for="PHOTOGOODS">Фотоотчет<a href="#" title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										</td>
										<td>
											<c:choose>
												<c:when test="${orderT.goods.photoGoods == 'true'}">
													<input type="checkbox" id="PHOTOGOODS" name="PHOTOGOODS" placeholder="" checked>
												</c:when>
												<c:otherwise>
													<input type="checkbox" id="PHOTOGOODS" name="PHOTOGOODS" placeholder="">
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div class="form-group" style="margin-top: -20px;">
									<input type="submit" class="submitbutton" value="Сохранить изменения">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>