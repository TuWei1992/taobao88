<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>

<div class="modal fade" id="badPriceModal" tabindex="-1" role="dialog"
	aria-labelledby="badPriceModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h2 class="modal-title" id="badPriceModalLabel">Ошибка!</h2>
			</div>
			<div class="modal-body">
				<p>
					Невозможно отправить заказ, т.к. итоговая сумма равна 0.
				</p>
			</div>
		</div>
	</div>
</div>