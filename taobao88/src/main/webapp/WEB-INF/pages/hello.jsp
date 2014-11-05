<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
</head>
<body>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Здравствуйте, ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/j_spring_security_logout" />"> Выйти</a>
		</h2>
	</c:if>



	<jsp:include page="userMenu.jsp" />

	<c:if test="${newOrders.size() != 0}">
		<div id="legend">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<c:if test="${newOrders.size() == 1}">
					<h6>Номер вышего нового заказа :</h6>
					<h5>
						<ul>
							<c:forEach items="${newOrders}" var="order">

								<li>${order.idOrder}</li>

							</c:forEach>
						</ul>
					</h5>
				</c:if>
				<c:if test="${newOrders.size() > 1}">
					<h6>Так как общий вес Вашего заказа превышает 20 кг, то Ваш
						заказа был разделен на ${orders.size()}, их номера :</h6>
					<h5>
						<ul>
							<c:forEach items="${newOrders}" var="order">

								<li>${order.idOrder}</li>

							</c:forEach>
						</ul>
					</h5>
				</c:if>
			</div>
		</div>
	</c:if>

	<div id="legend">
		<div class="alert alert-success">
			<p>
			<h6 style="color: black">Для того чтобы отправить заказы
				продавцу, выделите галочкой те, которые хотите заказать и нажмите
				кнопку отправить</h6>
			</p>

			<p>
			<h6 style="color: black">Для просмотра информации о товаре
				нажмите на его номер в таблице</h6>
			</p>
		</div>
	</div>

	<!--<div id="legend">
     <legend class="">
             <div class="alert alert-success">
                 <button type="button" class="close" data-dismiss="alert">&times;</button>
                 <h5>Ok!</h5>
                 <h6>Ваш заказ принят.</h6>
             </div>
     </legend>
 </div> -->

	<h1>Заказы :</h1>

	<form name="toOrderStatus"
		action=<c:url value="/privateOffice/toOrderStatus"/>>
		<input type="hidden" name="idOrder" />
	</form>
	<form name="deleteOrder"
		action=<c:url value="/privateOffice/deleteOrder"/>>
		<input type="hidden" name="idOrderForDelete" />
	</form>
	<form name="changeOrder"
		action=<c:url value="/privateOffice/changeOrder"/>>
		<input type="hidden" name="idOrderForChange" />
	</form>

	<form name="toOrder" class="form-horizontal" role="form"
		action=<c:url value="/privateOffice/toOrder"/>>

		<div class="panel-group" id="accordion">
			<table class="simple-little-table" cellspacing='0' width="90%">
				<thead>
					<tr>
						<th scope="col">№</th>
						<th scope="col">Отправка заказа на почту</th>
						<th scope="col">Стоимсоть заказа без доставки</th>
						<th scope="col">Стоимсоть заказа с доставкой</th>
						<th scope="col">Состояние заказа</th>
						<th scope="col">Удалить заказ</th>
					</tr>
				</thead>
				<c:forEach items="${orders}" var="order">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">

								<tfoot>
									<tr>
										<td><a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne${order.idOrder}"
											style="cursor: pointer; color: blue"> Заказ №
												${order.idOrder} </a>
											<div id="collapseOne${order.idOrder}"
												class="panel-collapse collapse">
												<div class="panel-body">
													<table class="simple-little-table">
														<tr>
															<td colspan="2"><a href="${order.hrefGoods}">Ссылка
																	на заказ</a></td>
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
															<td><c:if test="${order.chinaGoods == ''}">
                                                         Не нужна
                                                     </c:if> <c:if
																	test="${order.chinaGoods != ''}">
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
											</div></td>
										<td><c:if test="${order.approve == 'true'}">
												<span class="label label-success" disabled>Заказ
													сделан</span>
											</c:if> <c:if test="${order.approve == 'false'}">
												<span class="label label-warning">Сделать заказ</span>

												<input name="idOrder" type="checkbox"
													value="${order.idOrder}" />
											</c:if></td>
										<td>${order.priceGoods}$</td>
										<td>${order.fullPrice}$</td>
										<td>
											<!--a onclick="{document.toOrderStatus.idOrder.value=${order.idOrder};document.toOrderStatus.submit();}"-->
											<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne${order.idOrderStatus+1}"
											style="cursor: pointer; color: blue"> Просмотреть</a>
											<div id="collapseOne${order.idOrderStatus+1}"
												class="panel-collapse collapse">
												<div class="panel-body">
													<ul>
														<li><b>Подтверждение наличия товара</b> <c:if
																test="${order.approveStatus == 'true'}">
																<span class="label label-success" disabled>ok</span>
															</c:if> <c:if test="${order.approveStatus == 'false'}">
																<span class="label label-danger" disabled>waiting</span>
															</c:if></li>
														<li><b>Ожидание Вышей оплаты за товар</b> <c:if
																test="${order.payStatus == 'true'}">
																<span class="label label-success" disabled>ok</span>
															</c:if> <c:if test="${order.payStatus == 'false'}">
																<span class="label label-danger" disabled>waiting</span>
															</c:if></li>
														<li><b>Товар на стадии выкупа</b> <c:if
																test="${order.ransomStatus == 'true'}">
																<span class="label label-success" disabled>ok</span>
															</c:if> <c:if test="${order.ransomStatus == 'false'}">
																<span class="label label-danger" disabled>waiting</span>
															</c:if></li>
														<li><b>Товар готов и находится на офисе</b> <c:if
																test="${order.readyStatus == 'true'}">
																<span class="label label-success" disabled>ok</span>
															</c:if> <c:if test="${order.readyStatus == 'false'}">
																<span class="label label-danger" disabled>waiting</span>
															</c:if></li>
														<li><b>Посылка отправлена заказчику</b> <c:if
																test="${order.doneStatus == 'true'}">
																<span class="label label-success" disabled>ok</span>
															</c:if> <c:if test="${order.doneStatus == 'false'}">
																<span class="label label-danger" disabled>waiting</span>
															</c:if></li>
													</ul>
												</div>
											</div>
										</td>
										<td><a
											onclick="{document.changeOrder.idOrderForChange.value=${order.idOrder};document.changeOrder.submit();}"
											style="cursor: pointer; color: blue"> Изменить параметры
												заказа </a></td>
										<td><img width="50px" height="50px" name="deleteNews"
											src=<c:url value="/resources/images/delete.png"/>
											onclick="{document.deleteOrder.idOrderForDelete.value=${order.idOrder};document.deleteOrder.submit();}">
										</td>
									</tr>
								</tfoot>

							</h4>

						</div>

					</div>
				</c:forEach>
			</table>
		</div>
		<input type="submit" value="Отправить" />
	</form>


	<form name="ADMIN" action=<c:url value="/hello"/>>
		<input type="hidden" name="currentPageFromPage" value="1" />
	</form>
	<c:choose>
		<c:when test="${currentPage > 1 }">
         &nbsp;<a
				onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';
         document.ADMIN.submit();}"
				style="cursor: pointer;">Пред.</a>&nbsp;
     </c:when>
		<c:otherwise>
         &nbsp;Пред.&nbsp;|
     </c:otherwise>
	</c:choose>
	<c:forEach items="${countOfPages}" var="CountOfPages">

		<a
			onclick="{document.ADMIN.currentPageFromPage.value = '${CountOfPages}';
             document.ADMIN.submit();}"
			style="cursor: pointer; <c:if test="${currentPage == CountOfPages}">color: black !important</c:if>">
			${CountOfPages} </a>
	</c:forEach>
	<c:choose>
		<c:when test="${currentPage < countOfPages.size()}">
         &nbsp;<a
				onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';
         document.ADMIN.submit();}"
				style="cursor: pointer;">След.|</a>&nbsp;
     </c:when>
		<c:otherwise>
         &nbsp;След.&nbsp;
     </c:otherwise>
	</c:choose>


</body>
</html>