<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Update Menu</title>
	<jsp:include page="adminStyles.jsp"/>
</head>
<body>
	<jsp:include page="adminMenu.jsp"/>
	<div class="container">
		<div class="row">
			<div class="col-sm-5 col-md-5 col-xs-5 col-sm-offset-4">
			<form role="form" method="POST" accept-charset="utf-8" action="${pageContext.request.contextPath}/admin/pageRedactor/updateMenu/doUpdate">
				
					<div class="form-group">
						<label class="input" for="menu_name">Название меню</label>
						<input id="menu_name" class="form-control menu_name" type="text" name="menuName" value="${menu.menuName}">
						<input id="id" type="hidden" name="id" value="${menu.id}">
					</div>

					<div class="form-group">
						<label class="input" for="menu_href">Ссылка</label>
						<input class="form-control menu_href" id="menu_href" type="text" name="menuHref" value="${menu.menuHref}">
					</div>
					
					<div class="form-group">
						<label class="input" for="menu_order">Порядок</label> 
						<input class="form-control menu_order" id="menu_order" type="text" name="menuOrder" value="${menu.menuOrder}">
					</div>
					
					<div class="form-group">
						<button class="btn btn-success" type="submit">Обновить</button>
					</div>

				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>