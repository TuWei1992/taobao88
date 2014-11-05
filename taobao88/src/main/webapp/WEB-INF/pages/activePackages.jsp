<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin</title>
	<jsp:include page="adminStyles.jsp"/>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Главная <small>активные посылки</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
	<div class="row">
		<form name="showPackageAdmin" action=<c:url value="/admin/showPackageAdmin"/>>
			<input type="hidden" name="idPackage" />
		</form>
		<form name="toAdminStatus" action=<c:url value="/admin/toAdminStatus"/>>
			<input type="hidden" name="idOrderStatus" />
			<table class="table">
				<thead>
					<tr>
						<th>№</th>
						<th>Полная стоимость посылки</th>
					</tr>
				</thead>
				<c:forEach items="${activePackages}" var="packageT">
					<tr>
						<td><a
							onclick="{document.showPackageAdmin.idPackage.value = ${packageT.idPackage};document.showPackageAdmin.submit();}"
							style="cursor: pointer"> Посылка № ${packageT.idPackage} </a></td>
						<td>${packageT.fullPrice}</td>
						<td><c:if test="${TIME == packageT.datePackage}">
								<span class="label label-success" disabled>NEW</span>
							</c:if> 
							<c:if test="${TIME != packageT.datePackage}">
								<span class="span10" style="color: blue">Дата заказа: ${packageT.datePackage}</span>
							</c:if>
						</td>
						<td><span class="label-important">IN PROGRESS</span></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<form name="ADMIN" action=<c:url value="/admin"/>>
			<input type="hidden" name="currentPageFromPage" value="1" />
		</form>
		<c:choose>
			<c:when test="${currentPage > 1 }">
				&nbsp;<a onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';document.ADMIN.submit();}" style="cursor: pointer;">Пред.</a>&nbsp;
			</c:when>
			<c:otherwise>
            	&nbsp;Пред.&nbsp;|
        	</c:otherwise>
		</c:choose>
		<c:forEach items="${countOfPages}" var="CountOfPages">
			<a	onclick="{document.ADMIN.currentPageFromPage.value = '${CountOfPages}';document.ADMIN.submit();}" style="cursor: pointer; <c:if test="${currentPage == CountOfPages}">color: black !important</c:if>">${CountOfPages} </a>
		</c:forEach>
		<c:choose>
			<c:when test="${currentPage < countOfPages.size()}">
    			&nbsp;<a onclick="{document.ADMIN.currentPageFromPage.value = '${currentPage-1}';document.ADMIN.submit();}" style="cursor: pointer;">След.|</a>&nbsp;
        	</c:when>
			<c:otherwise>
        		&nbsp;След.&nbsp;
        	</c:otherwise>
		</c:choose>
	</div>
	</div>
</body>
</html>