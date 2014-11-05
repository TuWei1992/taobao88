<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<html>
<body>
	<jsp:include page="adminMenu.jsp" />
	<div class="container">
		<div class="row">
	<form name="toOrder" class="form-horizontal" role="form"
		<c:if test="${changeSettingsBool == 'false'}">action="${pageContext.request.contextPath}/privateOffice/updateSettings"</c:if>
		<c:if test="${changeSettingsBool == 'true'}">action="${pageContext.request.contextPath}/privateOffice/saveSettings"</c:if>
		method="post">

		<input type="hidden" name="stepSettings" />
		<c:if test="${rightOldPassword == 'true'}">
			<div id="legend">
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h6>Ваш пароль изменен успешно !</h6>
				</div>
			</div>
		</c:if>
		<table class="table" cellpadding='50px' style="width: 90%" rules="all">
			<tr>
				<td>
					<table>
						<tr>
							<td><b>Изменить Логин : </b></td>
							<td><input type="text" name="NAME" value="${user.nameUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>

						<tr>
							<td><b>Изменить почту : </b></td>
							<td><input type="email" name="EMAIL" value="${user.gmail}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить имя : </b></td>
							<td><input type="text" name="NAMEUSER"
								value="${user.fullNameUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить фамилию : </b></td>
							<td><input type="text" name="FEMAIL"
								value="${user.femailUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить отчество : </b></td>
							<td><input type="text" name="PATRONYMIC"
								value="${user.patronymicUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить почтовый индекс : </b></td>
							<td><input type="number" name="PATRONYMIC"
								value="${user.indexUserT}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td><b>Изменить страну : </b></td>
							<td><select size="0" multiple name="idCountry"
								onchange="{document.toOrder.stepSettings.value='stepRegion';document.toOrder.submit();}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required>
									<option disabled>Выберите страну</option>
									<c:forEach items="${countries}" var="country">
										<option value="${country.idCountry}"
											<c:if test="${user.countryUser == country.idCountry}">
                                                selected
                                            </c:if>>${country.nameCountry}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><b>Изменить регион : </b></td>
							<td><select multiple name="idRegion"
								onchange="{document.toOrder.stepSettings.value='stepCity';document.toOrder.submit();}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required>
									<option disabled>Выберите регион</option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.idRegion}"
											<c:if test="${user.regionUser == region.idRegion}">
                                                selected
                                            </c:if>>${region.nameRegion}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><b>Изменить город : </b></td>
							<td><select multiple name="idCity"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required>
									<option disabled>Выберите город</option>
									<c:forEach items="${cities}" var="city">
										<option value="${city.idCity}"
											<c:if test="${user.cityUser == city.idCity}">
                                                selected
                                            </c:if>>${city.nameCity}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td><b>Изменить улицу : </b></td>
							<td><input type="text" name="STREET"
								value="${user.streetUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить дом : </b></td>
							<td><input type="number" name="HOUSE"
								value="${user.houseUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
						<tr>
							<td><b>Изменить квартиру : </b></td>
							<td><input type="number" name="ROOM"
								value="${user.roomUser}"
								<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if>
								required /></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td><c:if test="${changeSettingsBool == 'true'}">
									<a href="${pageContext.request.contextPath}/changePassword">Изменить ПАРОЛЬ : </a>
								</c:if> <c:if test="${changeSettingsBool == 'false'}">
									<p>
										<b>Изменить ПАРОЛЬ </b>
									</p>
								</c:if></td>
						</tr>
					</table>
				</td>
		</table>
	</form>
	</div>
	</div>
</body>
</html>
