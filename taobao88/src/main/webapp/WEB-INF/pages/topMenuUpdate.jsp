<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Update Top Menu</title>
	<jsp:include page="adminStyles.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.preview').click(function() {
				var sourceHref = '${pageContext.request.contextPath}/admin/pageRedactor/previewTopMenu';
				var form = $('form');
				$(form).attr('action', sourceHref);
				$(form).attr('target', '_blank');
			});
			$('.create').click(function() {
				var sourceHref = '${pageContext.request.contextPath}/admin/pageRedactor/updateTopMenu/doUpdate';
				var form = $('form');
				$(form).attr('action', sourceHref);
				$(form).submit();
			});
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
	<jsp:include page="adminMenu.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактор <small>изменение топ-меню</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-xs-12">
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data">
				
					<div class="form-group">
						<label class="input" for="menuName">Название:</label> 
						<input type="text" class="form-control" name="menuName" id="menuName" value="${topMenu.menuName}">
						<input type="hidden" class="form-control" name="id" id="id" value="${topMenu.id}">
					</div>
					
					<div class="form-group">
						<label class="input" for="menuDescription">Описание:</label> 
						<textarea class="form-control" name="menuDescription" id="menuDescription">${topMenu.menuDescription}</textarea>
					</div>

					<div class="form-group">
						<label class="input" for="menuOrder">Номер позиции меню:</label> 
						<input type="text" class="form-control" id="menuOrder" name="menuOrder" value="${topMenu.menuOrder}">
					</div>
					
					<div class="form-group">
						<button type="submit" role="button" class="btn btn-success create">Обновить</button>
						<button type="submit" role="button" class="btn btn-primary preview">Просмотр</button>
					</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>