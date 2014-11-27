<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Посылки</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактирование посылки №<small>${packageT.idPackage}</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-2">
				<form role="form" method="POST" action="${pageContext.request.contextPath}/admin/packages/doUpdate" accept-charset="utf-8">
					<input type="hidden" name="idPackage" value="${packageT.idPackage}">
					<div class="form-group">
						<label class="input" for="serviceName">Цена</label>
						<input class="form-control" type="text" name="fullPrice" id="fullPrice" value="${packageT.fullPrice}" required>
					</div>
					<div class="form-group">
						<label class="input" for="serviceName">Трек-номер</label>
						<input class="form-control" type="text" name="tracknumber" id="tracknumbere" value="${packageT.tracknumber}" required>
					</div>
					<div class="form-group">
						<label class="input" for="serviceName">Вес</label>
						<input class="form-control" type="text" name="weight" id="weight" value="${packageT.weight}" required>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success">Обновить</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>