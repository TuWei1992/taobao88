<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:if test="${not empty errors}">
		<div class="alert alert-danger" role="alert">
			<strong>Ошибка!</strong> Проверьте правильность заполнения формы. Заполнены не все поля.
			<a class="btn btn-danger back" role="button">Назад к заполнению</a>
			<script type="text/javascript">
				$(function() {
					$('form').remove();
					$('.back').click(function() {
						window.history.go(-1);
					});
				});
			</script>
		</div>
	</c:if>
	
	<c:if test="${not empty unknown_error}">
		<div class="alert alert-danger" role="alert">
			<strong>Неизвестная ошибка!</strong> Ее быть никогда не должно.
		</div>
	</c:if>