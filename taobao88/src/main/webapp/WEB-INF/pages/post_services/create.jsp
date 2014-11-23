<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Добавить сервис</title>
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
					<label class="input" for="region">Регион доставки</label>
					<select class="form-control" name="region" id="region" required>
						<c:forEach items="${postRegions}" var="region">
							<option value="${region.id}">${region.regionName}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label class="input" for="price">Стоимость доставки</label>
					<input class="form-control" type="text" name="price" id="price" required>
				</div>
				
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