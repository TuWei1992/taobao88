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
						<label class="input" for="fullPrice">Цена</label>
						<input class="form-control" type="text" name="fullPrice" id="fullPrice" value="${packageT.fullPrice}" required>
					</div>
					<div class="form-group">
						<label class="input" for="tracknumber">Трек-номер</label>
						<input class="form-control" type="text" name="tracknumber" id="tracknumber" value="${packageT.tracknumber}">
					</div>
					<div class="form-group">
						<label class="input" for="weight">Вес</label>
						<input class="form-control" type="text" name="weight" id="weight" value="${packageT.weight}" required>
					</div>
					<div class="form-group">
						<label class="input" for="statusId">Статус</label>
						<select class="form-control" name="statusId" id="statusId">
							<c:set var="i" value="${packageT.packagesStatuses.size() - 1}"/>
							<option value="${packageT.packagesStatuses.get(i).status.id}">${packageT.packagesStatuses.get(i).status.statusName}</option>
							<c:forEach items="${allStatuses}" var="status">
								<option value="${status.id}">${status.statusName}</option>
							</c:forEach>
						</select>
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