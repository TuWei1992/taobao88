<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>

<div class="modal fade" id="brandModal" tabindex="-1" role="dialog"
	aria-labelledby="brandModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="brandModalLabel">Создать</h4>
			</div>
			<form role="form" method="POST" accept-charset="utf-8"
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/admin/pageRedactor/createBrand">
				<div class="modal-body">
					<div class="form-group">
						<label class="input" for="bDesc">Название</label> <input
							type="text" class="form-control" name="bDesc" id="bDesc"
							placeholder="Название" required="required">
					</div>

					<div class="form-group">
						<label class="input" for="bPhoto">Фото</label> <input type="file"
							name="bPhoto" required="required">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success sub_dsc">Создать</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
			</form>
		</div>
	</div>
</div>