<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('.return_agree_btn').click(function() {
			var data = "idPackage=${packageT.idPackage}";
			$('#returnMoneyAgreeModal').modal('hide');
			$('#loadingModal').modal('show');
				$.ajax({type:'POST',
		  		    url:'${pageContext.request.contextPath}/pay/returnMoney',
		  		    data: data,
		  		    dataType: 'json',
		  			complete: function(jsonData) {
		  				var response = JSON.parse(jsonData.responseText);
		  				if (response.success) {
		  					window.location.reload();
		  				}
		  			}
		    	});
		});
	});
</script>

<div class="modal fade" id="returnMoneyAgreeModal" tabindex="-1" role="dialog"
	aria-labelledby="returnMoneyAgreeLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h2 class="modal-title" id="returnMoneyAgreeLabel">Подтверждение операции.</h2>
			</div>
			<div class="modal-body">
				<p>
					На ваш счет будет начислено <b>$${packageT.purchasedAmount - packageT.fullPrice}</b>.
				</p>
				<p>
					Продолжить?
				</p>
			</div>
			<div class="modal-footer">
        		<button type="button" class="btn btn-primary return_agree_btn">Подтвердить операцию</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
      		</div>
		</div>
	</div>
</div>