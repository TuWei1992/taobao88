<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
		$(function() {
			checkFilling();
			if (!window.focus) {
				window.focus();
			}
			$('.form-group input, textarea').change(function() {
				checkFilling();
				var paramName = $(this).attr('name');
				var paramValue = $(this).val();
				if ($(this).attr('type') == 'checkbox') {
					if (this.checked) {
						post(paramName, 'on');
					} else {
						post(paramName, 'off');
					}	
				} else {
					post(paramName, paramValue);
				}
			});
			$('[data-toggle="tooltip"]').tooltip();
		});
		
		function post(paramName, paramValue) {
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/storeOrder',
				data: paramName + '=' + paramValue,
				complete: function(jsonData) {
					console.log('ok!');
				}
			});
		}
	
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
<div class="modal fade" id="orderModal" tabindex="-1" role="dialog"	aria-labelledby="orderModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h2 class="modal-title" id="orderModalLabel">Сделать заказ</h2>
			</div>
			<div class="modal-body">
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
							<label class="input" for="PRICEGOODS">Цена на taobao</label> 
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
								value="${WEIGHTGOODS}" data-toggle="tooltip" data-placement="top" title="Вес товара влияет на итоговую стоимость посылки">
						</div>
						<div class="input-group">
							<label class="input" for="COLORGOODS">Цвет</label> <input
								class="form-control order-control" type="text" id="COLORGOODS"
								name="COLORGOODS" placeholder="" required="required" value="${COLORGOODS}">
						</div>
						<div class="input-group">
							<label class="input" for="SIZEGOODS">Размер</label> <input
								class="form-control order-control" type="text" id="SIZEGOODS"
								name="SIZEGOODS" placeholder="0" required="required" value="${SIZEGOODS}" data-toggle="tooltip" data-placement="top" title="Если размер не указан, ставьте 0">
						</div>
					</div>

					<div class="form-group">
						<label class="textarea" for="COMPLEXGOODS">Примечание</label>
						<textarea class="form-control" type="text" id="COMPLEXGOODS"
							name="COMPLEXGOODS" placeholder="">${COMPLEXGOODS}</textarea>
					</div>

					<div class="form-group">
						<label for="PHOTOGOODS">Фотоотчет 
						<input type="checkbox" id="PHOTOGOODS" name="PHOTOGOODS" <c:if test="${PHOTOGOODS == 'on'}">checked</c:if>>
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
		</div>
	</div>
</div>