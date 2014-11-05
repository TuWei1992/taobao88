<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>

<jsp:include page="head1.jsp" />
</head>
<body>

	<h1>
		ADMIN PAGE <a href="<c:url value="/j_spring_security_logout" />">
			Выйти</a>
	</h1>

	<jsp:include page="adminMenu.jsp" />

	<p>
	<h4>Информация о заказе :</h4>
	</p>

	<input type="hidden" name="idOrderStatus" />
	<table class="table">
		<thead>
			<tr>
				<th>№ Заказа</th>
				<th>Пользователь</th>
				<th>Почта пользователя</th>
				<th>Ссылка на товар</th>
				<th>Кол-во единиц продукции</th>
				<th>Вес продукции</th>
				<th>Полная стоимость продукции</th>
			</tr>
		</thead>
		<tr>
			<td>${order.idOrder}</td>
			<td>${user.nameUser}</td>
			<td>${user.gmail}</td>
			<td>${good.hrefGoods}</td>
			<td>${good.amountGoods}</td>
			<td>${good.weightGoods}</td>
			<td>${order.fullPrice}</td>
		</tr>
	</table>
</body>
</html>