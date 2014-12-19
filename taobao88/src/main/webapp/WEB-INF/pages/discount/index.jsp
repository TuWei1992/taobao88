<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('.image_changed').hide();
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
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/discount/createRecomendation" type="button" role="button" class="btn btn-success">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${discount}" var="dsc">
								<tr>
									<td>
										<c:forEach items="${dsc.images}" var="img">
											<img src="/images/${img.imageName}" id="img_${img.imageId}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"	class="img-thumbnail">
											<a type="button" for="img_${img.imageId}" class="btn btn-default glyphicon glyphicon-remove delete_image" data-toggle="tooltip" data-placement="top" title="Удалить"></a>
											<a type="button" for="img_${img.imageId}&rec_${dsc.id}" class="btn btn-default glyphicon glyphicon-picture main_image" data-toggle="tooltip" data-placement="top" title="Сделать основной картинкой"></a>
										</c:forEach>
									</td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${dsc.description}</span></li>
  											<li class="list-group-item"><b>Подробное описание:</b> <span>${dsc.longDescription}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${dsc.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${dsc.href}</span></li>
  											<li class="list-group-item"><b>Цвета:</b>  
  												<c:forEach items="${dsc.colors}" var="color">
  													<span>${color.colorName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Размеры:</b>  
  												<c:forEach items="${dsc.sizes}" var="size">
  													<span>${size.sizeName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Количество:</b> <span>${dsc.count}</span>, <b>Вес:</b> <span>${dsc.weight}</span></li>
										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/discount/updateRecomendation?id=${dsc.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/discount/deleteRecomendation?id=${dsc.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/discount/createRecomendation" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>