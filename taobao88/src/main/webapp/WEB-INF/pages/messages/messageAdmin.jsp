<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../adminStyles.jsp"/>
<title>Сообщение</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-xs-6 col-md-6 col-sm-offset-2 col-xs-offset-2 col-md-offset-2">
			<h4>Написать сообщение пользователю <b>${toUser.nameUser}</b></h4>
			<form role="form" method="post" accept-charset="utf-8" action="${pageContext.request.contextPath}/messages/confirmMessage">
					<input type="hidden" name="toUser" value="${toUser.idUser}">
					<input type="hidden" name="fromUser" value="${fromUser.idUser}">
					<input type="hidden" name="idpackage" value="${packageT.idPackage}">
					<div class="form-group">
						<label class="input" for="message">Текст сообщения</label> 
						<textarea class="form-control order-control" id="message" name="message" placeholder="" onchange="checkFilling();"></textarea>
					</div>
					<div class="form-group">
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<button role="button" class="btn btn-primary btn-order"
									type="submit">Отправить</button>
							</c:when>
							<c:otherwise>
								<a role="button" class="btn btn-danger"
									href="${pageContext.request.contextPath}/login" target="_blank">Авторизоваться</a>
								<br>
						Для написания сообщения необходима авторизация
					</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>