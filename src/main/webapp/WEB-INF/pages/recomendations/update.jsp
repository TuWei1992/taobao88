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
</head>

<div class="col-md-7 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Редактирование рекомендации</span></h4>
</div>		
			<jsp:include page="../partials/errors.jsp"/>
			
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/recomendation/updateRecomendation/doUpdate">
					<input type="hidden" name="page" value="${curr_page}">
					<div class="form-group">
						<label class="input" for="rDesc">Краткое описание:</label> 
						<input type="hidden" class="form-control" name="id" value="${rec.id}">
						<input type="text" class="form-control" name="rDesc" id="rDesc"	placeholder="Краткое описание" value="${rec.description}">
					</div>
					
					<div class="form-group">
						<label class="input" for="rDescLong">Подробное описание:</label> 
						<textarea class="form-control" name="rDescLong" id="rDescLong"	placeholder="Подробное описание">${rec.longDescription}</textarea>
					</div>

					<div class="form-group">
						<label class="input" for="rPrice">Цена:</label> 
						<input type="text" class="form-control" id="rPrice" name="rPrice" placeholder="Цена" value="${rec.price}">
					</div>

					<div class="form-group">
						<label class="input" for="rHref">Ссылка:</label> 
						<input type="text" class="form-control" name="rHref" id="rHref" placeholder="Ссылка" value="${rec.href}">
					</div>
					
					<div class="form-group">
						<label class="input" for="rColor">Доступные цвета (через запятую):</label> 
						<input type="text" class="form-control" name="rColor" id="rColor" value=<c:forEach items="${rec.colors}" var="color">${color.colorName},</c:forEach>>
					</div>
					
					<div class="form-group">
						<label class="input" for="rSize">Доступные размеры (через запятую):</label> 
						<input type="text" class="form-control" name="rSize" id="rSize" value=<c:forEach items="${rec.sizes}" var="size">${size.sizeName},</c:forEach>>
					</div>
					
					<div class="form-group">
						<label class="input" for="rCount">Количество:</label> 
						<input type="text" class="form-control" name="rCount" id="rCount" placeholder="10" value="${rec.count}">
					</div>
					
					<div class="form-group">
						<label class="input" for="rWeight">Вес (в граммах):</label> 
						<input type="text" class="form-control" name="rWeight" id="rWeight" placeholder="250" value="${rec.weight}">
					</div>

					<div class="form-group">
						<label class="input" for="rPhoto">Фото</label> <input type="file"
							name="rPhoto">
					</div>

					<div class="form-group">
						<input type="file" name="rPhoto">
					</div>

					<div class="form-group">
						<input type="file" name="rPhoto">
					</div>

					<div class="form-group">
						<input type="file" name="rPhoto">
					</div>

					<div class="form-group">
						<input type="file" name="rPhoto">
					</div>
					
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation" class="btn btn-default pull-left">Назад</a>
						<button type="submit" role="button" class="btn btn-success pull-right">Обновить</button>
					</div>
			</form>
		</div>