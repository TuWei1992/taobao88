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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
<script type="text/javascript">
$(function() {
    $('#basket').text('${basket}');
    $('#translate').click(function() {
    	translate();
    });
});    
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
			<span>></span>
			<span><a href="${pageContext.request.contextPath}/privateOffice/changePassword">Смена пароля</a></span>
		</div>
		<div class="main">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h1>Изменение пароля для
					${pageContext.request.userPrincipal.name}</h1>
			</c:if>
			
			<form name="toOrder" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/privateOffice/savePassword" method="post">
				<c:if test="${rightOldPassword == 'false'}">
					<div id="legend">
						<div class="alert alert-danger">
							<h5>Ошибка!</h5>
							<h6>Вы ввели неверный пароль, повторите попытку !</h6>
						</div>
					</div>
				</c:if>
				<table class="table" cellpadding='50px' style="width: 60%">
					<tr>
						<td><label>Старый пароль</label></td>
						<td style="width: 30%" align="left">
							<input type="password" name="OLDPASSWORD" class="form in" required />
						</td>
					</tr>
					<tr>
						<td><label>Новый пароль</label></td>
						<td style="width: 30%" align="left">
							<input type="password" id="pass" onkeyup="validForm(this)" name="NEWPASSWORD" class="form in" required />
							<span id="msg">
								Количество символов в пароле должно быть больше 6
							</span>
						</td>
					</tr>
					<tr>
						<td><label>Повторить новый пароль</label></td>
						<td style="width: 30%" align="left">
							<input type="password" data-validation-match-match="PASSWORD" onkeyup="validFormRepeat(this)" class="form in" required name="ANOTHERPASSWORD">
							<span id="msg1">
								<p>Пароли не совпадают</p>
							</span>
						</td>
					</tr>
				</table>
				<input type="submit" class="submitbutton" value="Сохранить" />
			</form>
		</div>
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>