<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>

<div class="modal fade" id="slModal" tabindex="-1" role="dialog"
	aria-labelledby="slModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="slModalLabel">Создать рекомендацию</h4>
			</div>
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/createSlider">
				<div class="modal-body">
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

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success sub_sl">Создать</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
			</form>
		</div>
	</div>
</div>