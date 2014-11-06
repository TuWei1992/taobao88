<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Create Recomendation</title>
	<jsp:include page="adminStyles.jsp"/>
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
	<jsp:include page="adminMenu.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактор <small>создание рекомендации</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-md-8 col-xs-8 col-xs-offset-2">
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/createRecomendation/doCreate">
				
					<div class="form-group">
						<label class="input" for="rDesc">Краткое описание:</label> 
						<input type="text" class="form-control" name="rDesc" id="rDesc"	placeholder="Краткое описание" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rDescLong">Подробное описание:</label> 
						<textarea class="form-control" name="rDescLong" id="rDescLong"	placeholder="Подробное описание" required="required"></textarea>
					</div>

					<div class="form-group">
						<label class="input" for="rPrice">Цена:</label> 
						<input type="text" class="form-control" id="rPrice" name="rPrice" placeholder="Цена" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="rHref">Ссылка:</label> 
						<input type="text" class="form-control" name="rHref" id="rHref" placeholder="Ссылка" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rType">Тип:</label>
						<select class="form-control" name="rType" id="rType" required="required">
							<c:forEach items="${recomendationTypes}" var="type">
								<option value="${type.typeId}">${type.typeName}</option>							
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label class="input" for="rColor">Доступные цвета (через запятую):</label> 
						<input type="text" class="form-control" name="rColor" id="rColor" placeholder="черный, синий, красный..." required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rSize">Доступные размеры (через запятую):</label> 
						<input type="text" class="form-control" name="rSize" id="rSize" placeholder="36, 52, XL, XXL..." required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rCount">Количество:</label> 
						<input type="text" class="form-control" name="rCount" id="rCount" placeholder="10" required="required">
					</div>
					
					<div class="form-group">
						<label class="input" for="rWeight">Вес (в граммах):</label> 
						<input type="text" class="form-control" name="rWeight" id="rWeight" placeholder="250" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="rPhoto">Фото</label> 
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
						<input type="file" name="rPhoto">
					</div>
					
					<div class="form-group">
						<button type="submit" role="button" class="btn btn-success">Создать</button>
					</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>