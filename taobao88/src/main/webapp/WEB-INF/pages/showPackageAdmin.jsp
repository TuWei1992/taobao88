<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/dopstyle.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/table1.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.css" rel="stylesheet" media="screen"> 
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-latest-theme.css" rel="stylesheet" media="screen">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="../../assets/js/html5shiv.js"></script>
	<script src="../../assets/js/respond.min.js"></script>
	<![endif]-->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		$('.remove_order_status').click(function() {
			send($(this).attr('id'), 'removeOrdersStatuses');
	  	});
		$('.remove_package_status').click(function() {
			send($(this).attr('id'), 'removePackagesStatuses');
	  	});
		$('[data-toggle="tooltip"]').tooltip();
	});
	
	function send(id, action) {
		var data = 'id=' + id + '&action=' + action;
		$.ajax({type:'POST',
  		    url:'${pageContext.request.contextPath}/admin/removeStatus',
  		    data: data,
  		    dataType: 'json',
  			complete: function(jsonData) {
  				var response = JSON.parse(jsonData.responseText);
  				if (response.success) {
  					window.location.href = '${pageContext.request.contextPath}/admin/showPackageAdmin?idPackage=${packageT.idPackage}';
  				} 
  			}
    	});
	}
</script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Информация о посылке</h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<form name="toOrderStatus" action="${pageContext.request.contextPath}/admin/toAdminStatus">
				<input type="hidden" name="idOrderStatus" />
			</form>
			<form name="deleteOrder" action="${pageContext.request.contextPath}/admin/deleteOrder">
				<input type="hidden" name="idOrderForDelete" />
			</form>
			
			<div class="jumbotron">
  				<p>Посылка #${packageT.idPackage} от пользователя <a style="color: blue; cursor: pointer;" href="${pageContext.request.contextPath}/admin/userInformation">${userOfCurrentPackage.nameUser}</a>, почта <span style="color: blue">${userOfCurrentPackage.gmail}</span></p>
  				<p>
  					<ul class="list-group">
						<c:forEach begin="0" end="${packageT.packagesStatuses.size() - 1}" var="loop">
							<c:forEach items="${allStatuses}" var="status">
								<c:set var="pStatus" value="${packageT.packagesStatuses.get(loop)}"/>
								<c:if test="${status.id == pStatus.status.id}">
									<li class="list-group-item">
										<c:if test="${loop == packageT.packagesStatuses.size() - 1}"><i class="glyphicon glyphicon-plus"></i></c:if>
										<c:if test="${loop != packageT.packagesStatuses.size() - 1}"><i class="glyphicon glyphicon-ok"></i></c:if>
										${pStatus.status.statusName} <label class="label label-success"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${pStatus.createdAt}"/></label>
										<button type="button" role="button" id="${pStatus.id}" class="glyphicon glyphicon-remove remove_package_status pull-right"></button>
									</li>
								</c:if>
							</c:forEach>
						</c:forEach>									
					</ul>
  				</p>
  				<p>
  					Полная стоимость посылки: <span class="label label-primary">${packageT.fullPrice} $</span>
  				</p>
  				<p>
  					Вес посылки: <span class="label label-primary">${packageT.weight} кг</span>
  				</p>
  				<p>
  					Метод доставки: <span class="label label-primary">${packageT.postService.serviceName}</span>
  				</p>
  			</div>
				<a href="${pageContext.request.contextPath}/messages/sendMessage?toUser=${userOfCurrentPackage.idUser}&idpackage=${packageT.idPackage}" type="button" class="btn btn-primary">Написать сообщение пользователю</a>
				
				<form name="toOrder" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/saveOrderStatus">
				<input type="hidden" name="idPackage" value="${packageT.idPackage}">
				<h3>Посылка включает следующие заказы :</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>№</th>
							<th>Статус заказа</th>
							<th>Смена статуса</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td><input type="submit" class="btn btn-primary" value="Сохранить"> <a href="${pageContext.request.contextPath}/admin/packages/update?idPackage=${packageT.idPackage}" class="btn btn-success"><span>Редактировать</span></a></td>
							<td></td>
							<td></td>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach items="${packageT.orders}" var="orderT">
						<tr>
							<td>
								<input type="hidden" name="idOrder" value="${orderT.idOrder}">
								<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  									<div class="panel panel-primary">
    									<div class="panel-heading" role="tab" id="heading${orderT.idOrder}">
      										<h4 class="panel-title">
        										<a data-toggle="collapse" data-parent="#accordion" href="#collapse${orderT.idOrder}" aria-expanded="true" aria-controls="collapse${orderT.idOrder}">
          											Заказ #${orderT.idOrder}
        										</a>
      										</h4>
    									</div>
    									<div>
      										<div class="panel-body">
        										<table class="table table-hover">
        											<tr>
        												<td>Ссылка на товар</td>
        												<td><a href="${orderT.goods.hrefGoods}" target="_blank">#${orderT.idOrder}</a></td>
        											</tr>
        											<tr>
        												<td>Название продукции</td>
        												<td>${orderT.goods.nameGoods}</td>
        											</tr>
        											<tr>
        												<td>Количество</td>
        												<td>${orderT.goods.amountGoods}</td>
        											</tr>
        											<tr>
        												<td>Вес единицы продукции</td>
        												<td>${orderT.goods.weightGoods} (граммы)</td>
        											</tr>
        											<tr>
        												<td>Стоимость единицы продукции</td>
        												<td>${orderT.goods.priceGoods} (юани)</td>
        											</tr>
        											<tr>
        												<td>Доставка по Китаю</td>
        												<td>${orderT.goods.chinaGoods}</td>
        											</tr>
        											<tr>
        												<td>Цвет</td>
        												<td>${orderT.goods.colorGoods}</td>
        											</tr>
        											<tr>
        												<td>Размер</td>
        												<td>${orderT.goods.sizeGoods}</td>
        											</tr>
        											<tr>
        												<td>Фотоотчет</td>
        												<td>
        													<c:if test="${orderT.goods.photoGoods == 'true'}">Нужен</c:if>
        													<c:if test="${orderT.goods.photoGoods == 'false'}">Не нужен</c:if>
        												</td>
        											</tr>
        											<tr>
        												<td data-toggle="tooltip" data-placement="left" title="${orderT.goods.complexGoods}">Комментарий пользователя</td>
        												<td></td>
        											</tr>
        										</table>
      										</div>
    									</div>
  									</div>
  								</div>
							</td>
							<td>
								<c:set var="i" value="${orderT.ordersStatuses.size() - 1}"/>
								<div>
									<h4><span class="label label-primary">${orderT.ordersStatuses.get(i).status.statusName}</span></h4>
								</div>
							</td>
							<td>
								<ul class="list-group">
									<c:forEach begin="0" end="${orderT.ordersStatuses.size() - 1}" var="loop">
										<c:forEach items="${allStatuses}" var="status">
											<c:set var="oStatus" value="${orderT.ordersStatuses.get(loop)}"/>
											<c:if test="${status.id == oStatus.status.id}">
												<li class="list-group-item">
													<c:if test="${loop == orderT.ordersStatuses.size() - 1}"><i class="glyphicon glyphicon-plus"></i></c:if>
													<c:if test="${loop != orderT.ordersStatuses.size() - 1}"><i class="glyphicon glyphicon-ok"></i></c:if>
													${oStatus.status.statusName} <label class="label label-success"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${oStatus.createdAt}"/></label>
													<button type="button" role="button" id="${oStatus.id}" class="glyphicon glyphicon-remove remove_order_status pull-right"></button>
												</li>
											</c:if>
										</c:forEach>
									</c:forEach>									
								</ul>
								<div class="col-md-10 col-md-offset-1">
									<div class="form-group">
										<select class="form-control" name="statusId">
										<c:set var="i" value="${orderT.ordersStatuses.size() - 1}"/>
											<option value="${orderT.ordersStatuses.get(i).status.id}">${orderT.ordersStatuses.get(i).status.statusName}</option>
											<c:forEach items="${allStatuses}" var="status">
												<option value="${status.id}">${status.statusName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>