<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="notEnoughMoneyModal" tabindex="-1" role="dialog"
	aria-labelledby="notEnoughMoneyModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h2 class="modal-title" id="notEnoughMoneyModalLabel">Недостаточно средств на счёте.</h2>
			</div>
			<div class="modal-body">
				<p>
					Недостаточно средств на счете для совершения оплаты.
				</p>
				<p>
					Пройдите в меню <b>"Пополнить счет"</b> для пополнения счета.
				</p>
			</div>
		</div>
	</div>
</div>