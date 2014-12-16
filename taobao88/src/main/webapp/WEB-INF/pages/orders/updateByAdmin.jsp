<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Редактировать заказ</title>
</head>
<body>
	
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Изменить товар <small>заказ #${orderT.idOrder}, посылка #${packageT.idPackage}</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row col-md-6 col-md-offset-2">
			<form role="form" class="form" action="${pageContext.request.contextPath}/admin/doUpdateOrder" method="POST" accept-charset="utf-8">
				<input type="hidden" name="orderId" value="${orderT.idOrder}">
				<input type="hidden" name="packageId" value="${packageT.idPackage}">
				<div class="form-group">
					<label class="input" for="href">Ссылка на товар</label>
					<input class="form-control" type="text" name="href" id="href" value="${orderT.goods.hrefGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="nameGoods">Название продукции</label>
					<input class="form-control" type="text" name="nameGoods" id="nameGoods" value="${orderT.goods.nameGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="amountGoods">Количество</label>
					<input class="form-control" type="text" name="amountGoods" id="amountGoods" value="${orderT.goods.amountGoods}">
				</div>
				<div class="form-group">
					<label class="input" for="weightGoods">Вес единицы продукции</label>
					<input class="form-control" type="text" name="weightGoods" id="weightGoods" value="${orderT.goods.weightGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="priceGoods">Стоимость единицы продукции</label>
					<input class="form-control" type="text" name="priceGoods" id="priceGoods" value="${orderT.goods.priceGoods}">
				</div>
				<div class="form-group">
					<label class="input" for="chinaGoods">Доставка по Китаю</label>
					<input class="form-control" type="text" name="chinaGoods" id="chinaGoods" value="${orderT.goods.chinaGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="colorGoods">Цвет</label>
					<input class="form-control" type="text" name="colorGoods" id="colorGoods" value="${orderT.goods.colorGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="sizeGoods">Размер</label>
					<input class="form-control" type="text" name="sizeGoods" id="sizeGoods" value="${orderT.goods.sizeGoods}" disabled>
				</div>
				<div class="form-group">
					<label class="input" for="photoGoods">Фотоотчет</label>
					<input class="form-control" type="text" name="photoGoods" id="photoGoods" value="${orderT.goods.photoGoods}" disabled>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success">Обновить</button>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>