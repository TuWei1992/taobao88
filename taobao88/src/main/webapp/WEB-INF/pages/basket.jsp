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
		$('[data-toggle="tooltip"]').tooltip();
		
		$('#orderBtn').click(function() {
		  if (validateCheckedGoods() && validateChoosedPostService() && validateDeliveryPrice()) {
			  $('#loadingModal').modal('show');
			  var form = $('form[name="toOrder"]');
			  $.ajax({
					type: 'POST',
					url: $(form).attr('action'),
					data: $(form).serialize(),
					async: false,
					complete: function() {
						window.location.href = '${pageContext.request.contextPath}/privateOffice/toPackages';
					}
				});
		  }
		});
		
		var data = '';
		var totalWeight = 0;
		
		$('.orderIdCheckbox').change(function() {
			data = '';
		    var checkbox = $(this);
		    var orderId = $(checkbox).val();
		    $('.price').each(function(i, item) {
		    if ($(item).attr('for') == orderId) {
		        	var currSum = parseInt($('.price_without_delivery').text());
		      
		        	$('.orderIdCheckbox:checked').each(function(i, item) {
		        		data += $(item).attr('name') + '=' + $(item).val() + '&';
		        	});
		        		        
		      		if ($(checkbox).is(':checked')) {
		          		currSum += parseInt($(item).text());
		        	} else {
		           		currSum -= parseInt($(item).text());
	            	};
		        
		        	$('.price_without_delivery').text(currSum);
		        	data += 'price=' + currSum;
		    	}
		  	});
		    
		    $('.order_weight').each(function(i, item) {
		    	if ($(item).attr('for') == orderId) {
		    		totalWeight = parseFloat($('.package_weight').text());
		    		
		    		if ($(checkbox).is(':checked')) {
		    			totalWeight += parseFloat($(item).text());
		        	} else {
		        		totalWeight -= parseFloat($(item).text());
	            	};
		    	}	
		    });
		    $('.package_weight').text(totalWeight.toFixed(2));
		});
		
		$('#countryId').change(function() {
			var dataDelivery = data + '&postServiceId=' + $('.service').val() + '&countryId=' + $('#countryId').val();
			send(dataDelivery);
		});
		
		$('.service').click(function() {
			if (validateCheckedGoods()) {
				var dataDelivery = data + '&postServiceId=' + $(this).val() + '&countryId=' + $('#countryId').val();
          		send(dataDelivery);
			} else {
				$(this).prop('checked', false);
			}
		});
		
		$('.shipping_address_checker').change(function() {
			var checker = $(this);
			if ($(this).prop('checked')) {
				$('.shipping_address').hide();
			} else {
				$('.shipping_address').show();
			}
		});
	});
		
	function validateCheckedGoods() {
		var checkedStatus = false;
		$('.orderIdCheckbox').each(function(i, item) {
			if (item.checked) {
			  checkedStatus = true;
		    } 
		});
		if (checkedStatus) {
			return checkedStatus;
		} else {
			$('#fromBasketModal').modal('show');
			return false;
		}
	}
	
	function validateChoosedPostService() {
		if (!$('input[name="serviceId"]:checked').size() > 0) {
			$('#postServiceNotChosenModal').modal('show');
			return false;
		}
		return $('input[name="serviceId"]:checked').size() > 0;
	}
	
	function validateDeliveryPrice() {
		if ($('input[name="price"]').val() == 0) {
			$('#badPriceModal').modal('show');
			return false;
		} else {
			return true;
		}
	}
	
	function send(dataDelivery) {
		$.ajax({type:'POST',
  		    url:'${pageContext.request.contextPath}/price/adjustPrice',
  		    data: dataDelivery,
  		    dataType: 'json',
  			complete: function(jsonData) {
  				if (jsonData.responseText == 0.0) {
  					$('#weightLimitModal').modal('show');
  					$('.price_with_delivery').text(0);
  					$('input[name="price"]').val(0);
  					$('.service').prop('checked', false);
  				} else {
  					$('.price_with_delivery').text(parseInt(jsonData.responseText));
  					$('input[name="price"]').val(parseInt(jsonData.responseText));
  				}
  			}
    	});
	}
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
	
	<jsp:include page="./modal/basket_alert_modal.jsp"/>
	<jsp:include page="./modal/weight_limit_modal.jsp"/>
	<jsp:include page="./modal/basket_bad_price_modal.jsp"/>
	<jsp:include page="./modal/post_service_not_chosen_modal.jsp"/>
	<jsp:include page="./modal/loading_modal.jsp"/>
	
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
					Для того, чтобы оформить заказ, выделите галочкой те товары, которые хотите заказать, и нажмите кнопку "Оформить заказ".
				</div>
					
			<form name="toOrder" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/privateOffice/toOrder" accept-charset="utf-8">	
				<c:choose>
					<c:when test="${orders.size() != 0 }">
						
						<table class="orders">
							<thead>
								<tr>
									<th>№</th>
									<th colspan="2">Товар</th>
									<th>В посылку</th>
									<th>Стоимость</th>
									<th>Действия</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="0" end="${orders.size()}" varStatus="loop" items="${orders}" var="order">
									<tr>
										<td>
											<%-- <span>${loop.index + 1}</span> --%>
											<span>${order.idOrder}</span>
										</td>
										<td>
											<c:choose>
												<c:when test="${order.goods.recomendation != null}">
													<a href="${pageContext.request.contextPath}/item?id=${order.goods.recomendation.id}" class="product-img" target="_blank">
														<img src="/images/${order.goods.recomendation.photo}" />
													</a>
												</c:when>
												<c:otherwise>
													<a href="${order.goods.hrefGoods}" class="product-img" target="_blank">
														<img src="${pageContext.request.contextPath}/resources/img/buy.png"/>
													</a>
												</c:otherwise>
											</c:choose>
										</td>
										<td class="product">
											<h2>
												<c:choose>
													<c:when test="${order.goods.recomendation != null}">
														<a href="${pageContext.request.contextPath}/item?id=${order.goods.recomendation.id}" target="_blank">${order.goods.recomendation.description}</a>
													</c:when>
													<c:otherwise>
														<a href="${order.goods.hrefGoods}" target="_blank">${order.goods.nameGoods}</a>
													</c:otherwise>
												</c:choose>
											</h2>
											<div class="property">
												<span class="order">№: ${order.goods.idGoods}</span>
												<div class="row-form">
													<div class="overflow">Цвет:
													<c:choose>
														<c:when test="${empty order.goods.colorGoods}">
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
														<c:choose>
															<c:when test="${empty order.goods.sizeGoods}">
																<font color="red">Не выбран</font>
															</c:when>
															<c:otherwise>
																<span>${order.goods.sizeGoods}</span>
															</c:otherwise>
														</c:choose>
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
														<c:choose>
															<c:when test="${order.goods.photoGoods == 'false'}">
																<span>Нет</span>
															</c:when>
															<c:otherwise>
																<span>Да</span>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
												<div class="row-form">
													<div class="overflow">Вес товара (кг):
														<span class="order_weight" for="${order.idOrder}">${(order.goods.weightGoods * order.goods.amountGoods) / 1000}</span>
													</div>
												</div>
											</div>
										</td>
										<td>
											<input type="checkbox" class="orderIdCheckbox" name="idOrder" value="${order.idOrder}">
										</td>
										<td>
											<div>
												<span>$ <span class="price" for="${order.idOrder}">${order.fullPrice}</span></span>
											</div>
										</td>
										<td>
											
											<c:choose>
												<c:when test="${order.goods.recomendation != null}">
													<a href="${pageContext.request.contextPath}/fill?id=${order.goods.idGoods}" data-toggle="tooltip" data-placement="top" title="Изменить">
														<img src="${pageContext.request.contextPath}/resources/img/fill.png">
													</a>
												</c:when>
												<c:otherwise>
													<a href="javascript:void(0);" onclick="{document.changeOrder.idOrderForChange.value=${order.idOrder};document.changeOrder.submit();}" data-toggle="tooltip" data-placement="top" title="Изменить">
														<img src="${pageContext.request.contextPath}/resources/img/fill.png">
													</a>
												</c:otherwise>
											</c:choose>
											<a href="javascript:void(0);" onclick="{document.deleteOrder.idOrderForDelete.value=${order.idOrder};document.deleteOrder.submit();}" data-toggle="tooltip" data-placement="top" title="Удалить">
												<img name="deleteNews" src="${pageContext.request.contextPath}/resources/img/card.png">
											</a>
										</td>
										
									</tr>	
								</c:forEach>
							</tbody>
						</table>
						<div class="btn-card">
							<input type="hidden" name="price" value="0"> 
                    		<p>Итоговая сумма без доставки: <span>$</span><span class="price_without_delivery">0</span></p>
                		</div>
					
					</c:when>
					<c:otherwise>
                    	<div class="o_head">
                        	<h3>В вашей корзине пока нет товаров.</h3>
                    	</div>
                	</c:otherwise>
				</c:choose>
				
			<span>Способ доставки</span>
			<hr>
			<div>
				<div class="goods-list">
						<fieldset style="border:none;">
						<div class="row-form col-md-2">
							<label>Страна доставки:</label>
							<div class="overflow">
								<select class="form in form-control" name="countryId" id="countryId" data-toggle="tooltip" data-placement="top" title="Если Вашей страны нет в списке, напишите нам вопрос о возможности доставки в свою страну!">
									<c:forEach items="${countries}" var="c">
										<option value="${c.idCountry}">${c.nameCountry}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row-form col-md-2">
							<label>Использовать адрес, указанный при регистрации</label>
							<div class="overflow">
								<input type="checkbox" class="form in form-control shipping_address_checker" name="registration_address">
							</div>
						</div>
						<div class="shipping_address">
							<div class="row-form col-md-2">
								<label>Регион(область)</label>
								<div class="overflow">
									<input type="text" class="form in form-control" name="region" required>
								</div>
							</div>
							<div class="row-form col-md-2">
								<label>Город</label>
								<div class="overflow">
									<input type="text" class="form in form-control" name="city" required>
								</div>
							</div>
							<div class="row-form col-md-2">
								<label>Почтовый индекс</label>
								<div class="overflow">
									<input type="text" class="form in form-control" name="post_index" required>
								</div>
							</div>
							<div class="row-form col-md-2">
								<label>Адрес</label>
								<div class="overflow">
								<input type="text" class="form in form-control" name="address" required>
								</div>
							</div>
						</div>
						</fieldset>
				</div><br>
				
				<div class="goods-list">
       				<c:forEach items="${postServices}" var="service">
						<div id="${service.value.id}">
							<div class="shop-item">
								<div>
									<div class="item-box">
										<div class="item-thumb">
											<img src="/images/${service.value.image.imageName}" alt="post service" class="thumbnail" width="200">
										</div>
										<div class="item-meta">
											<div class="item-ttl">
												<span>${service.key} </span><input type="radio" name="serviceId" class="service" value="${service.value.id}">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>	
				</div>
			</div>
		</form>
			<div class="btn-card">
                    		<a href="javascript:void(0);" id="orderBtn">Оформить заказ</a>
                    		<p>
								Общий вес посылки (предварительный): <span class="package_weight">0</span><span>кг</span> | 
								Итоговая сумма с учетом доставки: <span>$</span><span class="price_with_delivery">0</span>
							</p>
                		</div>
		</div>	
	</div>
	</div>
	</div>
</div>
<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>