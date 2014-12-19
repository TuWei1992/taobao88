<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-7 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Создание баннера</span></h4>
</div>		
			<jsp:include page="../partials/errors.jsp"/>

<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/banner/createBanner/doCreate">
				
					<div class="form-group">
						<label class="input" for="bDesc">Краткое описание</label> 
						<input type="text" class="form-control" name="rDesc" id="bDesc" placeholder="Краткое описание" required="required">
						<input	type="hidden" name="recType" id="recType" value="5">
					</div>

					<div class="form-group">
						<label class="input" for="bPrice">Цена</label> <input type="text"
							class="form-control" id="sPrice" name="rPrice" placeholder="Цена" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="bHref">Ссылка</label> <input type="text"
							class="form-control" name="rHref" id="bHref" placeholder="Ссылка" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="bPhoto">Фото</label> 
						<input type="file" name="rPhoto" id="bPhoto" required="required">
					</div>

				
				
					<a href="${pageContext.request.contextPath}/admin/pageRedactor/banner" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Создать</button>
				
			</form>
		</div>