<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Страны/регионы</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактировать страну <small>управление доступными странами и регионами</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		
		<c:if test="${errors != null}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>
							<c:if test="${error == 'cannot_update_country'}">
								Неизвестная ошибка при обновлении страны.
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
		<div class="row col-md-6 col-md-offset-2">
			<form class="form" role="form" action="${pageContext.request.contextPath}/admin/countries/doUpdate" method="POST">
				<input type="hidden" name="id" value="${country.idCountry}">
				<div class="form-group">
					<label for="nameCountry">Название</label>
					<input type="text" class="form-control" id="nameCountry" name="nameCountry" value="${country.nameCountry}" required>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success">Обновить</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>