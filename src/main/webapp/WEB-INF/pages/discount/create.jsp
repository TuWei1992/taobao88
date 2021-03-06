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
  <h4><span class="label label-primary">Создание рекомендаций (со скидками)</span></h4>
</div>	
			<jsp:include page="../partials/errors.jsp"/>
			
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/discount/createRecomendation/doCreate">
					<input type="hidden" name="page" value="${curr_page}">
					<div class="form-group">
						<label class="input" for="rDesc">Краткое описание:</label> 
						<input type="text" class="form-control" name="rDesc" id="rDesc"	placeholder="Краткое описание" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rDescLong">Подробное описание:</label> 
						<textarea class="form-control" name="rDescLong" id="rDescLong"	placeholder="Подробное описание"></textarea>
					</div>

					<div class="form-group">
						<label class="input" for="rPrice">Цена:</label> 
						<input type="text" class="form-control" id="rPrice" name="rPrice" placeholder="Цена" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="rHref">Ссылка:</label> 
						<input type="text" class="form-control" name="rHref" id="rHref" placeholder="Ссылка" required="required">
					</div>
					
					<input type="hidden" name="rType" value="${recomendation_type}">
					<%--<div class="form-group">
						<label class="input" for="rType">Тип:</label>
						<select class="form-control" name="rType" id="rType" required="required">
							<c:forEach items="${recomendationTypes}" var="type">
								<option value="${type.typeId}">${type.typeName}</option>							
							</c:forEach>
						</select>
					</div>--%>
					
					<div class="form-group">
						<label class="input" for="rColor">Доступные цвета (через запятую):</label> 
						<input type="text" class="form-control" name="rColor" id="rColor" placeholder="Пример: черный, синий, красный..." required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rSize">Доступные размеры (через запятую):</label> 
						<input type="text" class="form-control" name="rSize" id="rSize" placeholder="Пример: 36, 52, XL, XXL..." required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rCount">Количество:</label> 
						<input type="text" class="form-control" name="rCount" id="rCount" placeholder="Пример: 10" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rWeight">Вес (в граммах):</label> 
						<input type="text" class="form-control" name="rWeight" id="rWeight" placeholder="Пример: 250" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="rPhoto">Фото</label> 
						<input type="file" id="rPhoto" name="rPhoto">
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
						<a href="${pageContext.request.contextPath}/admin/pageRedactor/discount" class="btn btn-default pull-left">Назад</a>
						<button type="submit" role="button" class="btn btn-success pull-right">Создать</button>
					</div>
			</form>
		</div>