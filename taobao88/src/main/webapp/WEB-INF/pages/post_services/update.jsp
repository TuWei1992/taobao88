<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Изменить сервис</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Изменить сервис <small>управление почтовыми сервисами</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-2">
			<form role="form" method="POST" action="${pageContext.request.contextPath}/admin/postServices/doUpdate" accept-charset="utf-8" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${postService.id}">
				<div class="form-group">
					<label class="input" for="serviceName">Название</label>
					<input class="form-control" type="text" name="serviceName" id="serviceName" value="${postService.serviceName}" required>
				</div>
				
				<div class="form-group">
					<label class="input" for="countryId">Регион доставки</label>
					<span>${postService.country.nameCountry}</span>
				</div>
				
				<c:forEach items="${postService.postServicesPrices}" var="postPrice">
					<div class="weight_price">
						<div class="col-md-6">
							<label class="input" for="weight">Вес</label>
							<input class="form-control" type="text" name="weight" value="${postPrice.weight}" required>
						</div>
						<div class="col-md-6">
							<label class="input" for="price">Цена</label>
							<input class="form-control" type="text" name="price" value="${postPrice.price}" required>
						</div>
					</div>
				</c:forEach>
				
				<div class="form-group">
					<label class="input" for="logo">Логотип</label> 
					<input type="file" name="logo" id="logo">
				</div>
				
				<div class="form-group">
					<input type="submit" role="button" class="btn btn-success pull-right" value="Обновить">
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>