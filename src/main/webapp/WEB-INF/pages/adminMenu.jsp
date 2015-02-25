<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">

		<nav class="navbar navbar-default" role="navigation">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/admin">На
							главную</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/orderHistory">В
							архив</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/pageRedactor">Редактор
							страниц</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/showMessages">Сообщения</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/showBalances">Счета</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/postServices">Почтовые сервисы</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/countries">Страны/Регионы</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/paymentMethods">Способы оплаты</a></li>
					<li><a href="${pageContext.request.contextPath}/secure/logout">Выйти</a></li>
				</ul>
			</div>
		</nav>

	</div>
</div>

