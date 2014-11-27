<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/dopstyle.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/table1.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.css" rel="stylesheet" media="screen"> 
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-latest-theme.css" rel="stylesheet" media="screen">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="../../assets/js/html5shiv.js"></script>
	<script src="../../assets/js/respond.min.js"></script>
	<![endif]-->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<!-- Checking for empty fields -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<div class="container">
		<div class="row">
			<form name="toOrderStatus" action="${pageContext.request.contextPath}/admin/toAdminStatus">
				<input type="hidden" name="idOrderStatus" />
			</form>
			<form name="deleteOrder" action="${pageContext.request.contextPath}/admin/deleteOrder">
				<input type="hidden" name="idOrderForDelete" />
			</form>
			<table class="table">
				<thead>
					<tr>
						<th>Названия состояний</th>
						<th>Состояние посылки</th>
					</tr>
				</thead>
					<tr>
						<td>Подтверждение наличия всех товаров в послыке</td>
						<c:if test="${statusPackage.approvePackage  == 'true'}">
							<td><span class="label label-success" disabled>ok</span></td>
						</c:if>
						<c:if test="${statusPackage.approvePackage  == 'false'}">
							<td><span class="label label-danger" disabled>waiting</span></td>
						</c:if>
					</tr>
					<tr>
						<td>Ожидание оплаты за все товары посылки</td>
						<c:if test="${statusPackage.payPackage  == 'true'}">
							<td><span class="label label-success" disabled>ok</span></td>
						</c:if>
						<c:if test="${statusPackage.payPackage  == 'false'}">
							<td><span class="label label-danger" disabled>waiting</span></td>
						</c:if>
					</tr>
					<tr>
						<td>Товары посылки находятся на стадии выкупа</td>
						<c:if test="${statusPackage.ransomPackage  == 'true'}">
							<td><span class="label label-success" disabled>ok</span></td>
						</c:if>
						<c:if test="${statusPackage.ransomPackage  == 'false'}">
							<td><span class="label label-danger" disabled>waiting</span></td>
						</c:if>
					</tr>
					<tr>
						<td>Посылка готова и находится на офисе</td>
						<c:if test="${statusPackage.readyPackage  == 'true'}">
							<td><span class="label label-success" disabled>ok</span></td>
						</c:if>
						<c:if test="${statusPackage.readyPackage  == 'false'}">
							<td><span class="label label-danger" disabled>waiting</span></td>
						</c:if>
					</tr>
					<tr>
						<td>Посылка отправлена заказчику</td>
						<c:if test="${statusPackage.importPackage == 'true'}">
							<td><span class="label label-success" disabled>ok</span></td>
						</c:if>
						<c:if test="${statusPackage.importPackage == 'false'}">
							<td><span class="label label-danger" disabled>waiting</span></td>
						</c:if>
					</tr>
			</table>
			<h1>
				Посылка от пользователя <a style="color: blue; cursor: pointer;"
					href="${pageContext.request.contextPath}/admin/userInformation">${userOfCurrentPackage.nameUser}</a>,
					почта <span style="color: blue">${userOfCurrentPackage.gmail}</span>
			</h1>
				<a href="${pageContext.request.contextPath}/privateOffice/sendMessage?toUser=${userOfCurrentPackage.idUser}&idpackage=${currentIdPackage}" type="button" class="btn btn-primary">Написать сообщение пользователю</a>
				
				<form name="toOrder" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/saveOrderStatus">
				<h3>Посылка включает следующие заказы :</h3>
				<div class="panel-group" id="accordion">
					<table class="simple-little-table" cellspacing='0' style="width: 90%">
						<thead>
							<tr>
								<th>№</th>
								<th>Отправка заказа на почту</th>
								<th>Состояние заказа</th>
							</tr>
						</thead>
						<c:forEach items="${orders}" var="order">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
									<tr>
										<td><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne${order.idOrder}" style="cursor: pointer; color: blue"> 
											Заказ №${order.idOrder}</a>
											<p></p>
											<div id="collapseOne${order.idOrder}" class="panel-collapse collapse">
												<div class="panel-body">
													<table class="simple-little-table">
														<tr>
															<td colspan="2"><a href="${order.hrefGoods}">Ссылка	на заказ</a></td>
														</tr>
														<tr>
															<td>Название продукции :</td>
															<td>${order.nameGoods}</td>
														</tr>
														<tr>
															<td>Количество единиц продукции :</td>
															<td>${order.amountGoods}</td>
														</tr>
														<tr>
															<td>Вес единицы продукции :</td>
															<td>${order.weightGoods}</td>
														</tr>
														<tr>
															<td>Стоимость единицы продукции :</td>
															<td>${order.priceGoods}</td>
														</tr>
														<tr>
															<td>Доставка по китаю :</td>
															<td><c:if test="${order.chinaGoods == null}">
                                                            Не нужна
                                                        </c:if> <c:if
																	test="${order.chinaGoods != null}">
                                                            ${order.chinaGoods} $
                                                        </c:if></td>
														</tr>
														<c:if test="${order.colorGoods != ''}">
															<tr>
																<td>Цвет продукции :</td>
																<td>${order.colorGoods}</td>
															</tr>
														</c:if>
														<c:if test="${order.sizeGoods != ''}">
															<tr>
																<td>Размер продукции :</td>
																<td>${order.sizeGoods}</td>
															</tr>
														</c:if>
														<tr>
															<td>Фотоотчет :</td>
															<td><c:if test="${order.photoGoods == 'true'}">
                                                            Нужен
                                                        </c:if> <c:if
																	test="${order.photoGoods != 'true'}">
                                                            Не нужен
                                                        </c:if></td>
														</tr>
													</table>
												</div>
											</div>
										</div>
						
								</td>
						<td><c:if test="${order.approve == 'true'}">
								<span class="label label-success" disabled>Заказ сделан</span>
							</c:if> <c:if test="${order.approve == 'false'}">
								<span class="label label-warning">Сделать заказ</span>
							</c:if></td>
						<td><a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne${order.idOrderStatus+1}"
							style="cursor: pointer; color: blue"> Просмотреть</a>
							<div id="collapseOne${order.idOrderStatus+1}"
								class="panel-collapse in collapse">
								<div class="panel-body">
									<table>
										<tr>
											<td><b>Подтверждение наличия товара</b></td>
											<td><c:if test="${order.approveStatus == 'true'}">
													<span class="label label-success" disabled>ok</span>
													<td><input type="checkbox"
														name="approve${order.idOrderStatus}" value="false" /></td>
												</c:if> <c:if test="${order.approveStatus == 'false'}">
													<span class="label label-danger" disabled>waiting</span>
													<td><input type="checkbox"
														name="approve${order.idOrderStatus}" value="true" /></td>
												</c:if></td>
										</tr>
										<tr>
											<td><b>Ожидание Вышей оплаты за товар</b></td>
											<td><c:if test="${order.payStatus == 'true'}">
													<span class="label label-success" disabled>ok</span>
													<td><input type="checkbox"
														name="pay${order.idOrderStatus}" value="false" /></td>
												</c:if> <c:if test="${order.payStatus == 'false'}">
													<span class="label label-danger" disabled>waiting</span>
													<td><input type="checkbox"
														name="pay${order.idOrderStatus}" value="true" /></td>
												</c:if></td>
										</tr>
										<tr>
											<td><b>Товар на стадии выкупа</b></td>
											<td><c:if test="${order.ransomStatus == 'true'}">
													<span class="label label-success" disabled>ok</span>
													<td><input type="checkbox"
														name="ransom${order.idOrderStatus}" value="false" /></td>
												</c:if> <c:if test="${order.ransomStatus == 'false'}">
													<span class="label label-danger" disabled>waiting</span>
													<td><input type="checkbox"
														name="ransom${order.idOrderStatus}" value="true" /></td>
												</c:if></td>
										</tr>
										<tr>
											<td><b>Товар готов и находится на офисе</b></td>
											<td><c:if test="${order.readyStatus == 'true'}">
													<span class="label label-success" disabled>ok</span>
													<td><input type="checkbox"
														name="ready${order.idOrderStatus}" value="false" /></td>
												</c:if> <c:if test="${order.readyStatus == 'false'}">
													<span class="label label-danger" disabled>waiting</span>
													<td><input type="checkbox"
														name="ready${order.idOrderStatus}" value="true" /></td>
												</c:if></td>
										</tr>
										<tr>
											<td><b>Посылка отправлена заказчику</b></td>
											<td><c:if test="${order.doneStatus == 'true'}">
													<span class="label label-success" disabled>ok</span>
													<td><input type="checkbox"
														name="done${order.idOrderStatus}" value="false" /></td>
												</c:if> <c:if test="${order.doneStatus == 'false'}">
													<span class="label label-danger" disabled>waiting</span>
													<td><input type="checkbox"
														name="done${order.idOrderStatus}" value="true" /></td>
												</c:if></td>
										</tr>
									</table>
								</div>
							</div></td>
						</tr>
						</h4>

					</div>

				</c:forEach>

				</div>
				<tr>
					<td><input type="submit" class="btn btn-success" value="Сохранить" />
						<a href="${pageContext.request.contextPath}/admin/packages/update?idPackage=${currentIdPackage}" class="btn btn-success"><span>Редактировать</span></a>
					</td>
				</tr>
				</form>
			</table>
			</div>
			</div>
		</div>
</body>
</html>