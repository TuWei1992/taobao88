<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="adminStyles.jsp"/>
<title>Счета</title>
<script type="text/javascript">

	$(function() {
		$('.alert-success').hide();
		
		$('.ok_btn').click(function () {
			window.location.reload();
		});
	});

	function adjustBalance(userId, type) {
		$('.alert-success').hide();
		var amount = $('#amount_for_'+userId).val();
		if (type == 'dec') {
			amount = -$('#amount_for_'+userId).val();
		}
		$('#loadingModal').modal('show');
		$.post('${pageContext.request.contextPath}/admin/adjustBalance', {'user_id':userId, 'amount':amount, 'type':type}, complete);
	}
	
	function complete(response) {
		$('.amount').val(0);
		$('#loadingModal').modal('hide');
		if (response.success) {
			if (response.message == 'decrement_success') {
				$('#balanceDecrementSuccessModal').modal('show');
			} else if (response.message == 'message_sended') {
				$('.' + response.message).show();
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<jsp:include page="modal/loading_modal.jsp"/>
	<jsp:include page="modal/loading_modal.jsp"/>
	<jsp:include page="modal/balance_decrement_success_modal.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Счета <small>управление счетами пользователей</small></h1>
			</div>
		</div>
		<div class="row">
			<div class="alert alert-success message_sended" role="alert">Отправлено сообщение на почту для подтверждения внесения средств. Баланс будет пополнен <strong>только</strong> после подтверждения по email.</div>			
			<c:if test="${confirmation_error == true}">
				<div class="alert alert-danger confirmation_error" role="alert">Ошибка подтверждения внесения средств! Возможно Вы уже подтверждали эту операцию.</div>
			</c:if>
			<c:if test="${not empty confirmation_complete == true}">
				<div class="alert alert-warning" role="alert">Пополнение счета в размере <strong>$${confirmation_amount}</strong> для пользователя <strong>${confirmation_user.fullNameUser} ${confirmation_user.femailUser}</strong> прошло успешно!</div>
			</c:if>
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
								<input class="form-control amount" id="amount_for_${user.idUser}" name="amount" value="0">
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