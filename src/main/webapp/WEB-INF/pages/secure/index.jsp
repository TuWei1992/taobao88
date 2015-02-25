<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Admin login</title>
	<jsp:include page="../adminStyles.jsp"/>
</head>
<body>
	<div class="container">
		<div class="row col-md-5 col-md-offset-3">
			<div class="page-header">
  				<h1>Приложение администратора</h1>
			</div>
			<form role="form" accept-charset="utf-8" action="${pageContext.request.contextPath}/secure/auth" method="POST">
				<c:if test="${incorrect_credentials == true}">
					<div class="alert alert-danger" role="alert">
						<strong>Ошибка!</strong> Неверные данные для входа!
					</div>
				</c:if>			
  				<div class="form-group">
    				<label for="login">Логин</label>
    				<input type="text" class="form-control" id="login" name="login" placeholder="Login">
  				</div>
  				<div class="form-group">
    				<label for="password">Пароль</label>
    				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
  				</div>
  				<button type="submit" class="btn btn-primary">Авторизация</button>
			</form>
		</div>
	</div>
</body>
</html>