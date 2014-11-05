<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>

<div class="modal fade" id="bannerModal" tabindex="-1" role="dialog"
	aria-labelledby="bannerModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="bannerModalLabel">Создать рекомендацию</h4>
			</div>
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/createBanner">
				<div class="modal-body">
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

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success sub_sl">Создать</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
			</form>
		</div>
	</div>
</div>