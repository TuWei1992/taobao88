<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<jsp:include page="head1.jsp" />
</head>
<html>
<body>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Здравствуйте, ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/j_spring_security_logout" />"> Выйти</a>
		</h2>
	</c:if>

	

	<!--<div id="legend">
    <legend class="">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                 <h5>Ok!</h5>
                 <h6>Ваш заказ принят.</h6>
             </div>
     </legend>
 </div> -->

	<h1>Архив посылок :</h1>

	<form name="showPackageAdmin"
		action=<c:url value="/privateOffice/showPackage"/>>
		<input type="hidden" name="idPackage" />
	</form>

	<form name="toAdminStatus"
		action=<c:url value="/privateOffice/toAdminStatus"/>>
		<input type="hidden" name="idOrderStatus" />
		<table class="table">
			<thead>
				<tr>
					<th>№</th>
					<th>Полная стоимость посылки</th>
				</tr>
			</thead>
			<c:forEach items="${packageTs}" var="packageT">
				<tr>
					<td><a
						onclick="{document.showPackageAdmin.idPackage.value = ${packageT.idPackage};document.showPackageAdmin.submit();}"
						style="cursor: pointer"> Посылка № ${packageT.idPackage} </a></td>
					<td>${packageT.fullPrice}</td>
					<td><span class="label label-warning">Дата заказа :
							${packageT.datePackage}</span></td>
					<td><span class="bg-success">Посылка выполнена</span></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>