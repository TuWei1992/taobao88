<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>==>>>Оформить заказ здесь<<<==</title>
<meta charset="utf-8">
<meta name="keywords"
	content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
<script type="text/javascript">
		$(function() {
			$('.btn-order').addClass('disabled');
			if (!window.focus) {
				window.focus();
			}
		});
	
		function checkFilling(){
			var empty = false; 
			$('.order-control').each(function(){
				if ($(this).attr('value') == ''){
					empty = true;
				} 
				if (empty) {return;}
			});
			if (empty) {
				$('.btn-order').addClass('disabled');
			} else {
				$('.btn-order').removeClass('disabled');
			}
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-6">
				<div class="page-header">
					<h1>Оформление заказа</h1>
				</div>
				<form role="form" method="post" accept-charset="utf-8"
					action="${pageContext.request.contextPath}/privateOffice/FromOrder">

					<div class="form-group">
						<label class="input" for="HREFGOODS">Ссылка</label> <input
							class="form-control order-control" type="text" id="HREFGOODS"
							name="HREFGOODS" placeholder="" onchange="checkFilling();">
					</div>

					<div class="form-group">
						<label class="input" for="NAMEGOODS">Название товара</label> <input
							class="form-control order-control" type="text" id="NAMEGOODS"
							name="NAMEGOODS" placeholder="" onchange="checkFilling();">
					</div>

					<div class="form-group">
						<div class="input-group">
							<label class="input" for="AMOUNTGOODS">Количество единиц</label>
							<input class="form-control order-control" type="number"
								id="AMOUNTGOODS" max="1000" name="AMOUNTGOODS" placeholder=""
								onchange="checkFilling();">
						</div>
						<div class="input-group">
							<label class="input" for="PRICEGOODS">Цена ($)</label> <input
								class="form-control order-control" type="text" id="PRICEGOODS"
								name="PRICEGOODS" placeholder="Пример: 10, 10.343"
								pattern="\d*\.?\d+"
								data-validation-pattern-message="Пример: 10, 10.343"
								onchange="checkFilling();">
						</div>
						<div class="input-group">
							<label class="input" for="CHINAGOODS">Доставка по Китаю</label>
							<input class="form-control order-control" type="number"
								id="WEIGHTGOODS" name="CHINAGOODS" max="1000" placeholder=""
								data-validation-pattern-message="Пример: 1.00, 0.55, 12.73"
								onchange="checkFilling();">
						</div>
						<div class="input-group">
							<label class="input" for="WEIGHTGOODS">Вес единицы
								продукции (в граммах)</label> <input class="form-control order-control"
								type="number" id="WEIGHTGOODS" max="1000000" name="WEIGHTGOODS"
								placeholder="Пример: 100, 150, 1100"
								onchange="checkFilling();">
						</div>
						<div class="input-group">
							<label class="input" for="COLORGOODS">Цвет</label> <input
								class="form-control order-control" type="text" id="COLORGOODS"
								name="COLORGOODS" placeholder="" onchange="checkFilling();">
						</div>
						<div class="input-group">
							<label class="input" for="SIZEGOODS">Размер</label> <input
								class="form-control order-control" type="text" id="SIZEGOODS"
								name="SIZEGOODS" placeholder="" onchange="checkFilling();">
						</div>
					</div>

					<div class="form-group">
						<label class="textarea" for="COMPLEXGOODS">Примечание</label>
						<textarea class="form-control" type="text" id="COMPLEXGOODS"
							name="COMPLEXGOODS" placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="PHOTOGOODS">Фотоотчет <input type="checkbox"
							value="true" id="PHOTOGOODS" name="PHOTOGOODS">
						</label>
					</div>

					<div class="form-group">
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<button role="button" class="btn btn-success btn-order"
									type="submit">Сделать заказ</button>
							</c:when>
							<c:otherwise>
								<a role="button" class="btn btn-danger"
									href="${pageContext.request.contextPath}/login" target="_blank">Авторизоваться</a>
								<br>
						Для совершения заказа необходима авторизация
					</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>