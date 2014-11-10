<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>taobao88. Мой аккаунт</title>
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/item.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">

<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="css/ie6.css"/>
<script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
<![endif]-->
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="css/ie.css"/>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
<script type="text/javascript">
$(function() {
    $('#basket').text('${basket}');
    $('#translate').click(function() {
    	translate();
    });
});    
</script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="partials/index_header.jsp"/>
	</div>
		<div class="width">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
				<span>></span>
				<span><a href="${pageContext.request.contextPath}/privateOffice/accountSettings">Мои настройки</a></span>
			</div>
			<div class="main">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h1>Добро пожаловать,
						${pageContext.request.userPrincipal.name}</h1>
				</c:if>
			</div>
			<div class="control">
			
				<form name="toOrderStatus" action="${pageContext.request.contextPath}/privateOffice/toOrderStatus">
					<input type="hidden" name="idOrder" />
				</form>
				<form name="deleteOrder" action="${pageContext.request.contextPath}/privateOffice/deleteOrder">
					<input type="hidden" name="idOrderForDelete" />
				</form>
			
				<form class="form-horizontal" role="form" 
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
					<table class="table" cellpadding='50px' rules="all">
						<tr>
							<td>
								<table>
									<tr>
										<td><label>Логин</label></td>
										<td><input type="text" name="NAME" class="form in" value="${user.nameUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Почта</label></td>
										<td><input type="email" name="EMAIL" class="form in" value="${user.gmail}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Имя</label></td>
										<td><input type="text" name="NAMEUSER" class="form in" value="${user.fullNameUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Фамилия</label></td>
										<td><input type="text" name="FEMAIL" class="form in" value="${user.femailUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Отчество</label></td>
										<td><input type="text" name="PATRONYMIC" class="form in" value="${user.patronymicUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Почтовый индекс</label></td>
										<td><input type="text" name="PATRONYMIC" class="form in" value="${user.indexUserT}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td><label>Страна</label></td>
										<td>
											<select size="0" multiple name="idCountry" class="form in" onchange="{document.toOrder.stepSettings.value='stepRegion';document.toOrder.submit();}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required>
												<option disabled>Выберите страну</option>
													<c:forEach items="${countries}" var="country">
														<option value="${country.idCountry}"
															<c:if test="${user.countryUser == country.idCountry}">selected</c:if>>${country.nameCountry}
														</option>
													</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td><label>Регион</label></td>
										<td>
											<select multiple name="idRegion" class="form in" onchange="{document.toOrder.stepSettings.value='stepCity';document.toOrder.submit();}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required>
											<option disabled>Выберите регион</option>
												<c:forEach items="${regions}" var="region">
													<option value="${region.idRegion}"
														<c:if test="${user.regionUser == region.idRegion}">selected</c:if>>${region.nameRegion}
													</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td><label>Город</label></td>
										<td>
											<select multiple name="idCity" class="form in" 
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required>
											<option disabled>Выберите город</option>
												<c:forEach items="${cities}" var="city">
													<option value="${city.idCity}"
														<c:if test="${user.cityUser == city.idCity}">selected</c:if>>${city.nameCity}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td><label>Улица</label></td>
										<td><input type="text" name="STREET" class="form in" value="${user.streetUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Дом</label></td>
										<td><input type="number" name="HOUSE" class="form in" value="${user.houseUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
									<tr>
										<td><label>Квартира</label></td>
										<td><input type="number" name="ROOM" class="form in" value="${user.roomUser}"
											<c:if test="${changeSettingsBool == 'false'}">disabled="true"</c:if> required />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td>
											<c:if test="${changeSettingsBool == 'true'}">
												<div class="position">
													<span><a href="${pageContext.request.contextPath}/privateOffice/changePassword">Изменить пароль</a></span>
												</div>
										   </c:if>
										   <c:if test="${changeSettingsBool == 'false'}">
										   <p>
												<b>Изменить пароль</b>
										   </p>
										   </c:if>
									   </td>
								   </tr>
							   </table>
							</td>
					</table>
					<input type="submit" class="submitbutton"
						<c:if test="${changeSettingsBool == 'false' && adminSettingsBool == 'false'}">value="Обновить"</c:if>
						<c:if test="${changeSettingsBool == 'true' && adminSettingsBool == 'false'}">value="Сохранить"</c:if> />
					</form>
				</div>
			</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>