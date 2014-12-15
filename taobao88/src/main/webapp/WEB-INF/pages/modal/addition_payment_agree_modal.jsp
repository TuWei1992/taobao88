<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('.addition_agree_btn').click(function() {
			var data = "idPackage=${packageT.idPackage}";
			$('#additionPaymentAgreeModal').modal('hide');
			$('#loadingModal').modal('show');
				$.ajax({type:'POST',
		  		    url:'${pageContext.request.contextPath}/pay/additionPay',
		  		    data: data,
		  		    dataType: 'json',
		  			complete: function(jsonData) {
		  				var response = JSON.parse(jsonData.responseText);
		  				if (!response.success) {
		  					if (response.message == 'not_enough_money') {
		  						$('#notEnoughMoneyModal').modal('show');
		  					}
		  				} else {
		  					window.location.reload();
		  				}
		  			}
		    	});
		});
	});
</script>

<div class="modal fade" id="additionPaymentAgreeModal" tabindex="-1" role="dialog"
	aria-labelledby="additionPaymentAgreeLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h2 class="modal-title" id="additionPaymentAgreeLabel">Подтверждение оплаты.</h2>
			</div>
			<div class="modal-body">
				<p>
					С вашего счета будет списано <b>$${packageT.fullPrice - packageT.purchasedAmount}</b> для оплаты посылки <b>#${packageT.idPackage}</b>.
				</p>
				<p>
					В посылку входят следующие товары:
				</p>
					<ul class="list-group">
						<c:forEach items="${packageT.orders}" var="order">
							<li class="list-group-item">${order.goods.nameGoods} (${order.goods.amountGoods}шт.)</li>
						</c:forEach>
					</ul>
				<p>
					Продолжить?
				</p>
			</div>
			<div class="modal-footer">
        		<button type="button" class="btn btn-primary addition_agree_btn">Подтвердить платеж</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
      		</div>
		</div>
	</div>
</div>