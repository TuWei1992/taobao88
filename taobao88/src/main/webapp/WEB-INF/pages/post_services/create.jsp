<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Добавить сервис</title>
<script type="text/javascript">
	$(function() {
		var groupToAppend = '<div class="col-md-6">' + 
        						'<label class="input" for="weight">Вес</label>' + 
        						'<input class="form-control" type="text" name="weight" required>' + 
      						'</div>' +
      						'<div class="col-md-6">' + 
        						'<label class="input" for="price">Цена</label>' +
				        		'<input class="form-control" type="text" name="price" required>' + 
      						'</div>';
		$('#add_input').click(function() {
			var weightPriceGroup = $('.weight_price');
			$(weightPriceGroup).append(groupToAppend);
		});	
	});
</script>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Добавить сервис <small>управление почтовыми сервисами</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-2">
			<form role="form" method="POST" action="${pageContext.request.contextPath}/admin/postServices/doCreate" accept-charset="utf-8" enctype="multipart/form-data">
				<div class="form-group">
					<label class="input" for="serviceName">Название</label>
					<input class="form-control" type="text" name="serviceName" id="serviceName" required>
				</div>
				
				<div class="form-group">
					<label class="input" for="countryId">Регион доставки</label>
					<select class="form-control" name="countryId" id="countryId" required>
						<c:forEach items="${countries}" var="country">
							<option value="${country.idCountry}">${country.nameCountry}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<div class="weight_price">
						<div class="col-md-5">
							<label class="input" for="weight">Вес</label>
							<input class="form-control" type="text" name="weight" required>
						</div>
						<div class="col-md-5">
							<label class="input" for="price">Цена</label>
							<input class="form-control" type="text" name="price" required>
						</div>
					</div>
						<div>
							<label class="input" for="add_input">Добавить</label>
							<button role="button" type="button" id="add_input" class="btn glyphicon glyphicon-plus-sign"></button>
						</div>
					
				</div><br>
				
				<div class="form-group">
					<label class="input" for="logo">Логотип</label> 
					<input type="file" name="logo" id="logo" required>
				</div>
				
				<div class="form-group">
					<input type="submit" role="button" class="btn btn-success pull-right" value="Создать">
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>