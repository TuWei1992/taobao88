<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../adminStyles.jsp"/>
<title>Сообщения</title>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Сообщения <small>просмотр сообщений по посылкам</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="panel-group" id="accordion">
				<c:forEach items="${packages}" var="packageT">
					<div class="panel panel-default">
  						<div class="panel-heading">
  							<h4 class="panel-title">
  								<a data-toggle="collapse" data-parent="#accordion" href="#collapse${packageT.idPackage}">Посылка №${packageT.idPackage}</a>
  							</h4>
  						</div>
  						<div id="collapse${packageT.idPackage}" class="panel-collapse collapse">
  						
  							<div class="panel-body">
    							<c:forEach items="${packageT.messages}" var="message">
    								<div class="panel <c:if test="${message.fromUser.nameUser == 'admin'}">panel-success</c:if> panel-primary">
    									<div class="panel-heading">
    										От: <b>${message.fromUser.nameUser}</b> 
    										Кому: <b>${message.toUser.nameUser}</b>
    										<span class="pull-right">Отправлено: <b><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${message.createdAt}"/></b></span>
    									</div>
  										<div class="panel-body">
  											<p>${message.message}</p>
  										</div>
    								</div>
    							</c:forEach>
    							<form role="form" method="post" accept-charset="utf-8" action="${pageContext.request.contextPath}/messages/confirmMessage">
									<input type="hidden" name="toUser" value="${packageT.orders.toArray()[0].idUser}">
									<input type="hidden" name="fromUser" value="1">
									<input type="hidden" name="idpackage" value="${packageT.idPackage}">
									<div class="form-group">
										<label class="divider"></label>
										<textarea class="form-control order-control" id="message" name="message" placeholder="Введите текст сообщения..."></textarea>
									</div>
									<div class="form-group">
										<button role="button" class="btn btn-success btn-order"	type="submit">Отправить</button>
									</div>
								</form>
  							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>