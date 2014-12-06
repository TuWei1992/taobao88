<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="adminStyles.jsp"/>
<title>Счета</title>
<script type="text/javascript">
	function adjustBalance(userId, type) {
		var amount = $('#amount_for_'+userId).val();
		if (type == 'dec') {
			amount = -$('#amount_for_'+userId).val();
		}
		$.post('${pageContext.request.contextPath}/admin/adjustBalance', {'user_id':userId, 'amount':amount, 'type':type});
		setTimeout(reload, 1000);
	}
	
	function reload() {
		window.location.reload();
	}
</script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Счета <small>управление счетами пользователей</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Пользователь</th>
						<th>Баланс</th>
						<th>Сумма</th>
						<th>Действия</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.idUser}</td>
							<td>
								${user.fullNameUser} ${user.patronymicUser} ${user.femailUser} 
								<input type="hidden" id="${user.idUser}" value="${user.idUser}">
							</td>
							<td>
								${balances[user.idUser]}
							</td>
							<td>
								<input class="form-control amount" id="amount_for_${user.idUser}" name="amount" value="0.0">
							</td>
							<td>
								<input type="button" class="btn btn-success inc" value="Добавить средства" onclick="adjustBalance(${user.idUser}, 'inc')">
								<input type="button" class="btn btn-danger dec" value="Списать средства" onclick="adjustBalance(${user.idUser}, 'dec')">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>