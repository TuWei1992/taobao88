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
  				<h1>Страны и регионы <small>управление доступными странами и регионами</small></h1>
			</div>
		</div>
	</div>

	<div class="container">
	
		<c:if test="${errors != null}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>
							<c:if test="${error == 'cannot_delete_country'}">
								Не удается удалить страну.
							</c:if>
							<c:if test="${error == 'cannot_update_country'}">
								Не удается обновить страну.
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Название</th>
						<th>Действия</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${countries}" var="country">
						<tr>
							<td>${country.idCountry}</td>
							<td>${country.nameCountry}</td>
							<td>
								<a type="button" href="${pageContext.request.contextPath}/admin/countries/update?id=${country.idCountry}" class="btn btn-default glyphicon glyphicon-pencil"></a>
								<a type="button" href="${pageContext.request.contextPath}/admin/countries/delete?id=${country.idCountry}" class="btn btn-default glyphicon glyphicon-remove"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td></td>
						<td>
							<a type="button" href="${pageContext.request.contextPath}/admin/countries/create" class="btn btn-success">Создать</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>
</html>