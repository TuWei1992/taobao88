<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head1.jsp" />
</head>

<body>

	<h1>
		ADMIN PAGE <a href="<c:url value="/j_spring_security_logout" />">
			Logout</a>
	</h1>

	<jsp:include page="adminMenu.jsp" />

	<form action=<c:url value="/admin/saveOrderStatus"/>>
		<table class="table">
			<thead>
				<tr>
					<th>Названия состояний</th>
					<th>Состояние заказа</th>
					<th>Подтвердить/отклонить</th>
				</tr>
			</thead>

			<tr>
				<td>Подтверждение наличия товара</td>
				<c:if test="${status.approve == 'true'}">
					<td><span class="label label-success" disabled>ok</span></td>
					<td><input type="checkbox" name="approve" value="false" /></td>
				</c:if>
				<c:if test="${status.approve == 'false'}">
					<td><span class="label label-danger" disabled>waiting</span></td>
					<td><input type="checkbox" name="approve" value="true" /></td>
				</c:if>
			</tr>
			<tr>
				<td>Ожидания оплаты от заказчика</td>
				<c:if test="${status.pay == 'true'}">
					<td><span class="label label-success" disabled>ok</span></td>
					<td><input type="checkbox" name="pay" value="false" /></td>
				</c:if>
				<c:if test="${status.pay == 'false'}">
					<td><span class="label label-danger" disabled>waiting</span></td>
					<td><input type="checkbox" name="pay" value="true" /></td>
				</c:if>
			</tr>
			<tr>
				<td>Выкуп товара</td>
				<c:if test="${status.ransom == 'true'}">
					<td><span class="label label-success" disabled>ok</span></td>
					<td><input type="checkbox" name="ransom" value="false" /></td>
				</c:if>
				<c:if test="${status.ransom == 'false'}">
					<td><span class="label label-danger" disabled>waiting</span></td>
					<td><input type="checkbox" name="ransom" value="true" /></td>
				</c:if>
			</tr>
			<tr>
				<td>Товар готов и находится в офисе</td>
				<c:if test="${status.ready == 'true'}">
					<td><span class="label label-success" disabled>ok</span></td>
					<td><input type="checkbox" name="ready" value="false" /></td>
				</c:if>
				<c:if test="${status.ready == 'false'}">
					<td><span class="label label-danger" disabled>waiting</span></td>
					<td><input type="checkbox" name="ready" value="true" /></td>
				</c:if>
			</tr>
			<tr>
				<td>Посылка отправлена заказчику</td>
				<c:if test="${status.done == 'true'}">
					<td><span class="label label-success" disabled>ok</span></td>
					<td><input type="checkbox" name="done" value="false" /></td>
				</c:if>
				<c:if test="${status.done == 'false'}">
					<td><span class="label label-danger" disabled>waiting</span></td>
					<td><input type="checkbox" name="done" value="true" /></td>
				</c:if>
			</tr>
		</table>
		<input type="submit" value="Сохранить" />
	</form>
</body>
</html>
