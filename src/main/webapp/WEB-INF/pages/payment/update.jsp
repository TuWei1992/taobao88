<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../adminStyles.jsp"/>
<title>Методы оплаты</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
	$(function() {
		tinymce.init({
		    selector: "textarea",
		    height : 500,
		    theme: "modern",
		    plugins: "image",
		    	image_advtab: true,
		    	image_description: false
		 });
	});
</script>
</head>
<body>
	<jsp:include page="../adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Методы оплаты</h1>
			</div>
		</div>
	</div>
	
	<div class="container">

<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Редактирование способа оплаты</span></h4>
</div>
	<jsp:include page="../partials/errors.jsp"/>
	<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/paymentMethods/update/doUpdate">
				<input type="hidden" name="id" value="${method.id}">
				
					<div class="form-group">
						<label class="input" for="methodName">Название</label> <input
							type="text" class="form-control" name="methodName" id="methodName"
							value="${method.methodName}" required>
					</div>

					<div class="form-group">
						<label class="input" for="methodDescription">Реквизиты</label> 
						<textarea name="methodDescription" id="methodDescription">${method.methodDescription}</textarea>
					</div>
				
				
					<a href="${pageContext.request.contextPath}/admin/paymentMethods" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Обновить</button>
				
			</form>
		</div>
	</div>
</body>
</html>