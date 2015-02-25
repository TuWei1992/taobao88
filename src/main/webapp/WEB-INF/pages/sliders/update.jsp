<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Обновить слайд</span></h4>
</div>
	<jsp:include page="../partials/errors.jsp"/>
<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/slider/updateSlider/doUpdate">
				
					<div class="form-group">
						<label class="input" for="rDesc">Краткое описание</label> 
						<input type="text" class="form-control" name="rDesc" id="rDesc" value="${rec.description}">
						<input	type="hidden" name="id" id="id" value="${rec.id}">
					</div>

					<div class="form-group">
						<label class="input" for="rPrice">Цена</label> <input type="text"
							class="form-control" id="rPrice" name="rPrice" value="${rec.price}">
					</div>

					<div class="form-group">
						<label class="input" for="rHref">Ссылка</label> <input type="text"
							class="form-control" name="rHref" id="rHref" value="${rec.href}">
					</div>
					
					<div class="form-group">
						<label class="input" for="rPhoto">Фото</label> 
						<input type="file" name="rPhoto" id="rPhoto">
					</div>

					<a href="${pageContext.request.contextPath}/admin/pageRedactor/slider" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Обновить</button>
				</form>
		</div>