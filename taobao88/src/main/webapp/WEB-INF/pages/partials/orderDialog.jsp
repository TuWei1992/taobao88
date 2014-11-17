<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
		$(function() {
			$('.btn-order').addClass('disabled');
			if (!window.focus) {
				window.focus();
			}
			$('.form-group input, textarea').change(function() {
				checkFilling();
				var paramName = $(this).attr('name');
				var paramValue = $(this).val();
				$.ajax({
					type: 'POST',
					url: '${pageContext.request.contextPath}/storeOrder',
					data: paramName + '=' + paramValue,
					complete: function(jsonData) {
						console.log('ok!');
					}
				});
			});
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

<div class="col-md-12" style="font-size: 10px">
		<form role="form" method="post" accept-charset="utf-8"
					action="${pageContext.request.contextPath}/privateOffice/FromOrder">

					<div class="form-group">
						<label class="input" for="HREFGOODS">Ссылка</label> 
						<input class="form-control order-control" type="text" id="HREFGOODS" name="HREFGOODS" placeholder="" required="required" value="${HREFGOODS}">
					</div>

					<div class="form-group">
						<label class="input" for="NAMEGOODS">Название товара</label> 
						<input class="form-control order-control" type="text" id="NAMEGOODS" name="NAMEGOODS" placeholder="" required="required" value="${NAMEGOODS}">
					</div>

					<div class="form-group">
						<div class="input-group">
							<label class="input" for="AMOUNTGOODS">Количество единиц</label>
							<input class="form-control order-control" type="number" id="AMOUNTGOODS" max="1000" name="AMOUNTGOODS" placeholder="" required="required" value="${AMOUNTGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="PRICEGOODS">Цена ($)</label> 
							<input
								class="form-control order-control" type="text" id="PRICEGOODS"
								name="PRICEGOODS" placeholder="Пример: 10, 10.343"
								pattern="\d*\.?\d+"
								data-validation-pattern-message="Пример: 10, 10.343"
								required="required"
								value="${PRICEGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="CHINAGOODS">Доставка по Китаю</label>
							<input class="form-control order-control" type="number"
								id="CHINAGOODS" name="CHINAGOODS" max="1000" placeholder=""
								data-validation-pattern-message="Пример: 1.00, 0.55, 12.73"
								required="required"
								value="${CHINAGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="WEIGHTGOODS">Вес единицы
								продукции (в граммах)</label> <input class="form-control order-control"
								type="number" id="WEIGHTGOODS" max="1000000" name="WEIGHTGOODS"
								placeholder="Пример: 100, 150, 1100"
								required="required"
								value="${WEIGHTGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="COLORGOODS">Цвет</label> <input
								class="form-control order-control" type="text" id="COLORGOODS"
								name="COLORGOODS" placeholder="" required="required" value="${COLORGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="SIZEGOODS">Размер</label> <input
								class="form-control order-control" type="text" id="SIZEGOODS"
								name="SIZEGOODS" placeholder="Если размер не указан, ставьте 0" required="required" value="${SIZEGOODS}">
						</div>
					</div>

					<div class="form-group">
						<label class="textarea" for="COMPLEXGOODS">Примечание</label>
						<textarea class="form-control" type="text" id="COMPLEXGOODS"
							name="COMPLEXGOODS" placeholder="">${COMPLEXGOODS}</textarea>
					</div>

					<div class="form-group">
						<label for="PHOTOGOODS">Фотоотчет <input type="checkbox"
							value="${PHOTOGOODS}" id="PHOTOGOODS" name="PHOTOGOODS">
						</label>
					</div>

					<div class="form-group">
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<button role="button" class="btn btn-success btn-order"
									type="submit">Положить в корзину</button>
							</c:when>
							<c:otherwise>
								<a role="button" class="btn btn-danger"
									href="${pageContext.request.contextPath}/login">Авторизоваться</a>
								<br>
						Для совершения заказа необходима авторизация
					</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>