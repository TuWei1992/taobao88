<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
		$(function() {
			$('.preview').click(function() {
				var sourceHref = '${pageContext.request.contextPath}/admin/pageRedactor/topMenu/previewTopMenu';
				var form = $('form');
				$(form).attr('action', sourceHref);
				$(form).attr('target', '_blank');
			});
			$('.create').click(function() {
				var sourceHref = '${pageContext.request.contextPath}/admin/pageRedactor/topMenu/createTopMenu/doCreate';
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

	<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Создание топ-меню</span></h4>
</div>
			<jsp:include page="../partials/errors.jsp"/>
			
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data">
				
					<div class="form-group">
						<label class="input" for="menuName">Название:</label> 
						<input type="text" class="form-control" name="menuName" id="menuName"	placeholder="Название меню" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="menuDescription">Описание:</label>
						<textarea class="form-control" name="menuDescription" id="menuDescription" placeholder="Описание"></textarea>
					</div>

					<div class="form-group">
						<label class="input" for="menuOrder">Номер позиции меню:</label> 
						<input type="text" class="form-control" id="menuOrder" name="menuOrder" placeholder="пример: 1 - отобразится первым и т.д." required="required">
					</div>
					
					<div class="form-group">
						<button type="submit" role="button" class="btn btn-success create">Создать</button>
						<button type="submit" role="button" class="btn btn-primary preview">Просмотр</button>
					</div>
			</form>
		</div>
			