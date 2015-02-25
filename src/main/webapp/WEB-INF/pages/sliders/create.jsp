<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Создать слайд</span></h4>
</div>
	<jsp:include page="../partials/errors.jsp"/>
		<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/slider/createSlider/doCreate">
				
					<div class="form-group">
						<label class="input" for="sDesc">Краткое описание</label> 
						<input type="text" class="form-control" name="rDesc" id="sDesc" placeholder="Краткое описание">
						<input	type="hidden" name="recType" id="recType" value="1" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="sPrice">Цена</label> <input type="text"
							class="form-control" id="sPrice" name="rPrice" placeholder="Цена" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="sHref">Ссылка</label> <input type="text"
							class="form-control" name="rHref" id="sHref" placeholder="Ссылка" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="sPhoto">Фото</label> 
						<input type="file" name="rPhoto" id="sPhoto" required="required">
					</div>

				
					<a href="${pageContext.request.contextPath}/admin/pageRedactor/slider" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Создать</button>
					
				
			</form>
		</div>