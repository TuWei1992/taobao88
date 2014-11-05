<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Update</title>
	<jsp:include page="adminStyles.jsp"/>
</head>
<body>
<jsp:include page="adminMenu.jsp"/>
	<div class="container">
		<div class="row">
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/doUpdateBanner">
				<div class="modal-body">
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

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success sub_sl">Обновить</button>
				</div>
			</form>
		</div>
	</div>
</body>