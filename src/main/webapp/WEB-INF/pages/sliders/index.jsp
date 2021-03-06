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
									<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/slider/createSlider" class="btn btn-success ">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${slider}" var="sli">
								<tr>
									<td><img src="/images/${sli.photo}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="250" height="250" class="img-thumbnail"></td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${sli.description}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${sli.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${sli.href}</span></li>
  										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/slider/updateSlider?id=${sli.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/slider/deleteSlider?id=${sli.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/slider/createSlider" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>