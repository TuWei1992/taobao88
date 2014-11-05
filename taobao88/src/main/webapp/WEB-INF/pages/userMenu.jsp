<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>
	<a href=<c:url value="/privateOffice"/>>На главную</a> |
	<a href=<c:url value="/privateOffice/toPackages"/>>К посылкам</a> |
	<a href=<c:url value="/privateOffice/ordersHistory"/>>В архив</a> |
	<a href=<c:url value="/privateOffice/accountSettings"/>>Мои
		настройки</a> |
	<a href=<c:url value="/order"/> target="_blank">Сделать еще один
		заказ</a>
</body>
</html>
