<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Методы оплаты</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Методы оплаты<small></small></h1>
			</div>
		</div>
	</div>

	<div class="container">

<table class="table table-hover">
						<thead>
							<tr>
								<th>Название</th>
								<th>Реквизиты</th>
								<th>Действия</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/paymentMethods/create" role="button" class="btn btn-success">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${paymentMethods}" var="method">
								<tr>
									<td>${method.methodName}</td>
									<td>
										${method.methodDescription}
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/paymentMethods/update?id=${method.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/paymentMethods/delete?id=${method.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/paymentMethods/create" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
</body>
</html>