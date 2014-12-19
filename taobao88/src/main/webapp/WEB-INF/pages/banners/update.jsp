<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-7 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Редактирование баннера</span></h4>
</div>		
			<jsp:include page="../partials/errors.jsp"/>

<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/banner/updateBanner/doUpdate">
				
					<div class="form-group">
						<label class="input" for="bDesc">Краткое описание</label> 
						<input type="text" class="form-control" name="rDesc" id="bDesc" value="${rec.description}">
						<input	type="hidden" name="id" id="id" value="${rec.id}">
					</div>

					<div class="form-group">
						<label class="input" for="bPrice">Цена</label> <input type="text"
							class="form-control" id="sPrice" name="rPrice" value="${rec.price}">
					</div>

					<div class="form-group">
						<label class="input" for="bHref">Ссылка</label> <input type="text"
							class="form-control" name="rHref" id="bHref" value="${rec.href}">
					</div>
					
					<div class="form-group">
						<label class="input" for="bPhoto">Фото</label> 
						<input type="file" name="rPhoto" id="bPhoto">
					</div>

				
				
					<a href="${pageContext.request.contextPath}/admin/pageRedactor/banner" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Обновить</button>
				
			</form>
		</div>