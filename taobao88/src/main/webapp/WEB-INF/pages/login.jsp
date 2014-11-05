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

#msg {
	color: red; /* Цвет текста */
	display: none; /* Прячем сообщение */
}

#msg1 {
	color: red; /* Цвет текста */
	display: none; /* Прячем сообщение */
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
<script>
        function validForm(f) {
            // Если введено число, то скрываем предупреждение
            if (isDigit(f.value)){
                document.getElementById("msg").style.display = "none";
                document.getElementById("btnPlaceOrder").disabled = false;
            }
            // В противном случае отображаем предупреждение
            else {
                document.getElementById("msg").style.display = "inline";
                document.getElementById("btnPlaceOrder").disabled = true;
            }
        }

        function validFormRepeat(f) {
            // Если введено число, то скрываем предупреждение
            console.log(f.value+" = "+document.getElementById("pass").value);
            if (f.value == document.getElementById("pass").value){
                document.getElementById("msg1").style.display = "none";
                document.getElementById("btnPlaceOrder").disabled = false;
            }
            // В противном случае отображаем предупреждение
            else {
                document.getElementById("msg1").style.display = "inline";
                document.getElementById("btnPlaceOrder").disabled = true;
            }
        }
        // Функция по проверке, число введено или нет
        function isDigit(data) {
            if (data.length > 6) return 1;
            else return 0;
        }
    </script>
</head>
<body onload='document.f.j_username.focus();'>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="" id="loginModal">
					<div class="modal-body" style="max-height: 900px;">
						<div class="well">
							<ul class="nav nav-tabs">
								<li
									<c:if test="${badRegistration == 'false'}">class="active"</c:if>><a
									href="#login" data-toggle="tab">Войти</a></li>
								<li
									<c:if test="${badRegistration == 'true'}">class="active"</c:if>><a
									href="#create" data-toggle="tab">Создать аккаунт</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div
									<c:if test="${badRegistration == 'false'}">class="tab-pane active in"</c:if>
									<c:if test="${badRegistration == 'true'}">class="tab-pane fade"</c:if>
									id="login">
									<form class="form-horizontal" name='f'
										action="<c:url value='j_spring_security_check' />"
										method="post">
										<fieldset>
											<div id="legend">
												<legend class="">
													Авторизация
													<c:if test="${not empty error}">
														<div class="alert alert-danger">
															<button type="button" class="close" data-dismiss="alert">&times;</button>
															<h5>Ошибка!</h5>
															<h6>Вы ввели некорреткное имя пользователя или
																пароль.</h6>
														</div>
													</c:if>
												</legend>

											</div>

											<div class="control-group">
												<!-- Username -->
												<label class="control-label" for="username">Логин</label>
												<div class="controls">
													<input type="text" id="username" name="j_username"
														placeholder="" required class="input-xlarge">
												</div>
											</div>

											<div class="control-group">
												<!-- Password-->
												<label class="control-label" for="password">Пароль</label>
												<div class="controls">
													<input type="password" id="password" name="j_password"
														placeholder="" required class="input-xlarge">
												</div>
											</div>

											<div class="form-group">
												<!-- Button -->
												<div class="controls">
													<button class="btn btn-success">Вход</button>
													<a href="/newPassword" style="cursor: pointer">Забыли
														пароль?</a>
												</div>
											</div>
										</fieldset>
									</form>
								</div>


								<div
									<c:if test="${badRegistration == 'true'}">class="tab-pane active in"</c:if>
									<c:if test="${badRegistration == 'false'}">class="tab-pane fade"</c:if>
									id="create">

									<div id="legend1">
										<legend class="">
											<c:if
												test="${badRegistration == 'true'  && countryChoose=='false'}">
												<div class="alert alert-danger">
													<button type="button" class="close" data-dismiss="alert">&times;</button>
													<h5>Ошибка!</h5>
													<h6>Вы ввели зарегистрированное имя пользователя или
														зарегестрированную почту.</h6>
												</div>
											</c:if>
										</legend>

									</div>
									<form id="tab" name="registration" class="reg"
										action="registration" method="post">
										<input type="hidden" name="step" />
										<table rules="none" cellpadding="60px">
											<tr>
												<td align="left">
													<div class="control-group">
														<label class="control-label">Логин</label> <input
															type="text" name="NAME" value="${user.getNameUser()}"
															required
															<c:if test="${badRegistration == 'true' && countryChoose=='false'}">style="background-color: #ff0000"</c:if>
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Email</label> <input
															type="email" name="EMAIL" value="${user.getGmail()}"
															required
															<c:if test="${badRegistration == 'true' && countryChoose=='false'}">style="background-color: #ff0000"</c:if>
															class="input-xlarge">
													</div>
													<div class="control-group">
														<label class="control-label">Пароль</label>
														<div class="controls">
															<input type="password" id="pass" name="PASSWORD"
																onkeyup="validForm(this)" value="${user.getPassword()}"
																required class="input-xlarge"> <span id="msg"><p>Количество
																	символов в пароле должно быть больше 6.</p></span>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Повторить пароль</label>
														<div class="controls">
															<input type="password"
																data-validation-match-match="PASSWORD"
																onkeyup="validFormRepeat(this)"
																value="${user.getPassword()}" required
																name="ANOTHERPASSWORD" class="input-xlarge"> <span
																id="msg1"><p>Пароли не совпадает.</p></span>
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
														<label class="control-label">Почтовый индекс</label> <input
															type="number" name="INDEX"
															value="${user.getIndexUserT()}" required
															class="input-xlarge">
													</div>
												</td>
												<td>

													<div class="control-group">
														<label class="control-label">Страна</label> <select
															size="0" multiple name="idCountry"
															onchange="{document.registration.step.value='stepRegion';document.registration.submit();}"
															required>
															<option disabled>Выберите страну</option>
															<c:forEach items="${countries}" var="country">
																<option value="${country.idCountry}"
																	<c:if test="${user.countryUser == country.idCountry}">
                                                                     selected
                                                                 </c:if>>${country.nameCountry}</option>
															</c:forEach>
														</select>
													</div>
													<div class="control-group">
														<label class="control-label">Регион</label> <select
															multiple name="idRegion"
															onchange="{document.registration.step.value='stepCity';document.registration.submit();}"
															required>
															<option disabled>Выберите регион</option>
															<c:forEach items="${regions}" var="region">
																<option value="${region.idRegion}"
																	<c:if test="${user.regionUser == region.idRegion}">
                                                                     selected
                                                                 </c:if>>${region.nameRegion}</option>
															</c:forEach>
														</select>
													</div>
													<div class="control-group">
														<label class="control-label">Город</label> <select
															multiple name="idCity" required>
															<option disabled>Выберите город</option>
															<c:forEach items="${cities}" var="city">
																<option value="${city.idCity}"
																	<c:if test="${user.cityUser == city.idCity}">
                                                                     selected
                                                                 </c:if>>${city.nameCity}</option>
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
											<button id="btnPlaceOrder" class="btn btn-primary">Создать</button>
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
</body>
</html>