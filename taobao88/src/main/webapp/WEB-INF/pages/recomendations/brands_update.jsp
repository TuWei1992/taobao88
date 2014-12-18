<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Update Brand</title>
	<jsp:include page="../adminStyles.jsp"/>
</head>
<body>
	<jsp:include page="../adminMenu.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактор <small>изменение бренда</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-md-8 col-xs-8 col-xs-offset-2">
				
				<jsp:include page="../partials/errors.jsp"/>
			
				<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/updateBrand/doUpdate">
					<input type="hidden" name="id" value="${brand.brandId}">
					
					<div class="form-group">
						<label class="input" for="bDesc">Название</label> 
						<input type="text" class="form-control" name="bDesc" id="bDesc" value="${brand.brandName}" required>
					</div>

					<div class="form-group">
						<label class="input" for="bPhoto">Фото</label>
						<input type="file" name="bPhoto">
					</div>
					<button type="submit" class="btn btn-success">Обновить</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>