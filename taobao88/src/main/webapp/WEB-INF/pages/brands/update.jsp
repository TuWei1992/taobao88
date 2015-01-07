<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Редактирование бренда</span></h4>
</div>
		<jsp:include page="../partials/errors.jsp"/>
				<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/brands/updateBrand/doUpdate">
					<input type="hidden" name="page" value="${curr_page}">
					<input type="hidden" name="id" value="${brand.brandId}">
					
					<div class="form-group">
						<label class="input" for="bDesc">Название</label> 
						<input type="text" class="form-control" name="bDesc" id="bDesc" value="${brand.brandName}" required>
					</div>

					<div class="form-group">
						<label class="input" for="bPhoto">Фото</label>
						<input type="file" name="bPhoto">
					</div>
					<a href="${pageContext.request.contextPath}/admin/pageRedactor/brands" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Обновить</button>
				</form>
			</div>