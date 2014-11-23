<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
											<img src="/images/${img.imageName}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"	class="img-thumbnail">
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