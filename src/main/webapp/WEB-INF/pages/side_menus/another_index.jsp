<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12" style="margin-top: 20px;">
		${anotherMenu.content}
	</div>
</div>
<div class="row">
	<div class="col-md-12 pull-right" style="margin-top: 20px;">
		<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/other/update" class="btn btn-success" role="button">Редактировать</a>
	</div>
</div>