<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Почтовые сервисы</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Почтовые сервисы <small>управление почтовыми сервисами</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<c:forEach items="${postServices}" var="service">
  				<div class="panel panel-default">
    				<div class="panel-heading" role="tab" id="headingOne">
      					<h4 class="panel-title">
        					<a data-toggle="collapse" data-parent="#accordion" href="#${service.id}" aria-expanded="true" aria-controls="${service.id}">
          						${service.serviceName} (${service.country.nameCountry})
        					</a>
      					</h4>
    				</div>
    				<div id="${service.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      					<div class="panel-body">
      						<table class="table table-hover">
      							<thead>
									<tr>
										<th>Логотип</th>
										<th>Вес/стоимость</th>
										<th>Действия</th>
									</tr>
								</thead>
      							<tbody>
      								<tr>
      									<td>
											<img src="/images/${service.image.imageName}" alt="image dropped" width="100" height="100"	class="img-thumbnail">
										</td>
										<td>
											<c:forEach items="${service.postServicesPrices}" var="postPrice">
												<span><b>${postPrice.weight}</b> | ${postPrice.price}</span><br>
											</c:forEach>
										</td>
										<td>
											<div class="btn-group">
												<a type="button" href="${pageContext.request.contextPath}/admin/postServices/update?id=${service.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
												<a type="button" href="${pageContext.request.contextPath}/admin/postServices/delete?id=${service.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											</div>
										</td>
      								</tr>
      							</tbody>
      						</table>
      					</div>
     				</div>
     			</div>
     			</c:forEach>
     			<br>
     			<div>
     				<a href="${pageContext.request.contextPath}/admin/postServices/create" role="button" class="btn btn-success">Создать сервис</a>
     			</div>
     		</div>
			<%--<table class="table table-hover">
				<thead>
					<tr>
						<th>Логотип</th>
						<th>Название</th>
						<th>Регион доставки</th>
						<th>Вес/стоимость</th>
						<th>Действия</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postServices}" var="service">
						<tr>
							<td>
								<img src="/images/${service.image.imageName}" alt="image dropped" width="100" height="100"	class="img-thumbnail">
							</td>
							<td>
								<span>${service.serviceName}</span>
							</td>
							<td>
								<span>${service.country.nameCountry}</span>
							</td>
							<td>
								
							</td>
							<td>
								<div class="btn-group">
									<a type="button" href="${pageContext.request.contextPath}/admin/postServices/update?id=${service.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
									<a type="button" href="${pageContext.request.contextPath}/admin/postServices/delete?id=${service.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<a href="${pageContext.request.contextPath}/admin/postServices/create" role="button" class="btn btn-success">Создать меню</a>
						</td>
					</tr>
				</tfoot>
			</table>--%>
		</div>
	</div>
</body>
</html>