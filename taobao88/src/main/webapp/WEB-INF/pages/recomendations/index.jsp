<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		
		$('.image_changed').hide();
		
		$('.delete_image').click(function() {
			var delete_btn = $(this);
			var imageId = $(delete_btn).attr('for');
			imageId = imageId.replace('img_', '');
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/admin/pageRedactor/deleteImage',
				data: 'imageId=' + imageId,
				complete: function(jsonData) {
					var response = JSON.parse(jsonData.responseText);
					if (response.success) {
						$(delete_btn).remove();
						$('#img_' + imageId).remove();
					}
				}
			});
		});
		
		$('.main_image').click(function() {
			var main_btn = $(this);
			var imgRec = $(main_btn).attr('for').split('&');
			var imageId = imgRec[0].replace('img_', '');
			var recId = imgRec[1].replace('rec_', '');
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/admin/pageRedactor/makeImageAsMain',
				data: 'imageId=' + imageId + '&recId=' + recId,
				complete: function(jsonData) {
					var response = JSON.parse(jsonData.responseText);
					if (response.success) {
						$('.image_changed').show();
					}
				}
			});
		});
	});
</script>

<div class="alert alert-success image_changed" role="alert">Картинка сделана <strong>главной</strong> для этого товара.</div>

<table class="table table-hover">
						<thead>
							<tr>
								<th>Фото</th>
								<th>Аттрибуты</th>
								<th>Действия</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/createRecomendation" type="button" role="button" class="btn btn-success create_rec_btn">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${recomendations}" var="rec">
								<tr>
									<td>
										<c:forEach items="${rec.images}" var="img">
											<img src="/images/${img.imageName}" id="img_${img.imageId}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"	class="img-thumbnail">
											<a type="button" for="img_${img.imageId}" class="btn btn-default glyphicon glyphicon-remove delete_image" data-toggle="tooltip" data-placement="top" title="Удалить"></a>
											<a type="button" for="img_${img.imageId}&rec_${rec.id}" class="btn btn-default glyphicon glyphicon-picture main_image" data-toggle="tooltip" data-placement="top" title="Сделать основной картинкой"></a>
											<br>
										</c:forEach>
									</td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${rec.description}</span></li>
  											<li class="list-group-item"><b>Подробное описание:</b> <span>${rec.longDescription}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${rec.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${rec.href}</span></li>
  											<li class="list-group-item"><b>Цвета:</b>  
  												<c:forEach items="${rec.colors}" var="color">
  													<span>${color.colorName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Размеры:</b>  
  												<c:forEach items="${rec.sizes}" var="size">
  													<span>${size.sizeName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Количество:</b> <span>${rec.count}</span>, <b>Вес:</b> <span>${rec.weight}</span></li>
										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateRecomendation?id=${rec.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteRecomendation?id=${rec.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>