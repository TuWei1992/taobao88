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
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/banner/createBanner" role="button" class="btn btn-success">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							
								<tr>
									<td><img src="/images/${banner.photo}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="250" height="250" class="img-thumbnail"></td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${banner.description}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${banner.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${banner.href}</span></li>
  										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/banner/updateBanner?id=${banner.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/banner/deleteBanner?id=${banner.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/banner/createBanner" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							
						</tbody>
					</table>