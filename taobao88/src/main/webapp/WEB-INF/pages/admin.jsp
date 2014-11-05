<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<jsp:include page="adminStyles.jsp" />
</head>
<body>

	<jsp:include page="adminMenu.jsp" />

	<div class="container">
		<form name="adminInformation"
			action="${pageContext.request.contextPath}/admin/orderInformation">
			<input type="hidden" name="idOrder" />
		</form>

		<form name="toAdminStatus"
			action="${pageContext.request.contextPath}/admin/toAdminStatus">
			<input type="hidden" name="idOrderStatus" />
			<table class="table">

				<thead>
					<tr>
						<th>№ Заказа</th>
						<th>Состояние заказа</th>
					</tr>
				</thead>

				<c:forEach items="${orders}" var="order">
					<tr>
						<td><a
							onclick="{document.toAdminStatus.idOrderStatus.value=${order.idOrderStatus};document.toAdminStatus.submit();}">Заказ
								№ ${order.idOrder}</a></td>
						<td><span class="label label-important">Заказ на почте</span>
						</td>
						<td><a
							onclick="{document.adminInformation.idOrder.value='${order.idOrder}';document.adminInformation.submit();}">Информация
								о заказе</a></td>
					</tr>
				</c:forEach>

			</table>
		</form>
	</div>
</body>
</html>