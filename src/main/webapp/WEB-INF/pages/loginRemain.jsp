<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link href=<c:url value="/resources/css/bootstrap.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value="/resources/css/dopstyle.css"/> rel="stylesheet"
	media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<!-- or use local jquery -->
<script src=<c:url value="/resources/js/jqBootstrapValidation.js"/>></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="../../assets/js/html5shiv.js"></script>
    <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body onload='document.f.j_username.focus();'>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="" id="loginModal">
					<div class="modal-body" style="max-height: 800px;">
						<div class="well">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#login" data-toggle="tab">Войти</a></li>
								<li><a href="#create" data-toggle="tab">Создать аккаунт</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane active in" id="login">
									<form class="form-horizontal" name='f'
										action="/remainPassword" method='POST'>
										<fieldset>
											<div id="legend">
												<legend class="">
													Авторизация
													<c:if test="${goodMail == 'true'}">
														<div class="alert alert-success">
															<button type="button" class="close" data-dismiss="alert">&times;</button>
															<h5>Ваш пароль изменен успешно, все подробности в
																письме на почте, которую вы указывали при регистрации</h5>
															<a href="/login" style="cursor: pointer">Перейти
																на страницу авторизации</a>
														</div>
													</c:if>
													<c:if test="${badMail == 'true'}">
														<div class="alert alert-danger">
															<button type="button" class="close" data-dismiss="alert">&times;</button>
															<h5>Вы ввели незарегистрированную почту, потворите
																попытку.</h5>
														</div>
													</c:if>
												</legend>
											</div>

											<div class="control-group">
												<!-- Username -->
												<label class="control-label" for="username">Введите
													вашу почту, которые вы использовали при регистрации</label>
												<div class="controls">
													<input type="text" id="username" name="username"
														placeholder="" required class="input-xlarge">
												</div>
											</div>

											<div class="form-group">
												<!-- Button -->
												<div class="controls">
													<button class="btn btn-success">Восстановить</button>
												</div>
											</div>
										</fieldset>
									</form>
								</div>

								<div class="tab-pane fade" id="create">


									<form id="tab" class="reg" action="registration">
										<table rules="none" cellpadding="60px">
											<tr>
												<td align="left">
													<div class="control-group">
														<label class="control-label">Логин</label> <input
															type="text" name="NAME" value="${user.getNameUser()}"
															required
															<c:if test="${badRegistration == 'true'}">style="background-color: #ff0000"</c:if>
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Email</label> <input
															type="email" name="EMAIL" value="${user.getGmail()}"
															required
															<c:if test="${badRegistration == 'true'}">style="background-color: #ff0000"</c:if>
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Пароль</label>
														<div class="controls">
															<input type="password" name="PASSWORD" required
																class="input-xlarge">
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Повторить пароль</label>
														<div class="controls">
															<input type="password"
																data-validation-match-match="PASSWORD" required
																name="ANOTHERPASSWORD" class="input-xlarge">
														</div>
													</div>
													<div></div>
													<div class="control-group">
														<label class="control-label">Имя пользователя</label> <input
															type="text" name="NAMEUSER"
															value="${user.getFullNameUser()}" required
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Фамилия</label> <input
															type="text" name="FEMAIL" value="${user.getFemailUser()}"
															required class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Отчество</label> <input
															type="text" name="PATRONYMIC"
															value="${user.getPatronymicUser()}" required
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Индекс</label> <input
															type="number" name="INDEX" value="" required
															class="input-xlarge">
													</div>
												</td>
												<td>

													<div class="control-group">
														<label class="control-label">Страна</label> <select
															size="0" multiple name="idCountry">
															<option disabled>Выберите страну</option>
															<c:forEach items="${countries}" var="country">
																<option value="${country.idCountry}">${country.nameCountry}</option>
															</c:forEach>
														</select>
													</div>
													<div class="control-group">
														<label class="control-label">Регион</label> <select
															multiple name="idRegion">
															<option disabled>Выберите регион</option>
															<c:forEach items="${regions}" var="region">
																<option value="${region.idRegion}">${region.nameRegion}</option>
															</c:forEach>
														</select>
													</div>
													<div class="control-group">
														<label class="control-label">Город</label> <select
															multiple name="idCity">
															<option disabled>Выберите город</option>
															<c:forEach items="${cities}" var="city">
																<option value="${city.idCity}">${city.nameCity}</option>
															</c:forEach>
														</select>
													</div>
													<div class="control-group">
														<label class="control-label">Улица</label> <input
															type="text" name="STREET" value="${user.getStreetUser()}"
															required class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Дом</label> <input
															type="number" name="HOUSE" value="${user.getHouseUser()}"
															required class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Квартира</label> <input
															type="number" name="ROOM" value="${user.getRoomUser()}"
															required class="input-xlarge">
													</div>

												</td>
											</tr>
										</table>
										<div>
											<button class="btn btn-primary">Создать</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src=<c:url value="/resources/js/bootstrap.js"/>></script>
		<script src=<c:url value="/resources/js/jquery.js"/>></script>
		<!-- Checking for empty fields -->
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        </div>
</body>
</html>