<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<div class="col-md-7 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Аккаунт пользователя</span></h4>
</div>
			<jsp:include page="../partials/errors.jsp"/>
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/userAccount/updatedPageContent">
					<input type="hidden" name="page" value="privateOffice"/>
										
					<div class="form-group">
						<label class="input" for="content">Описание:</label>
						<textarea class="form-control" name="content" id="content" placeholder="Описание">${privateOffice.content}</textarea>
					</div>
					
					<div class="form-group">
						<button type="submit" role="button" class="btn btn-success pull-right">Обновить</button>
					</div>
			</form>
			</div>
		