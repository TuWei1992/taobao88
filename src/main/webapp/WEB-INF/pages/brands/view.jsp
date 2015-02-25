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
								<td>
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/brands" role="button" class="btn btn-default">Назад</a>
								</td>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/brands/createBrand?page=${curr_page}" role="button" class="btn btn-success">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							
								<tr>
									<td><img src="/images/${brand.image.imageName}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"
										class="img-thumbnail"></td>
									<td>
										<ul class="list-group">
											<li class="list-group-item"><b>Имя бренда:</b> <span>${brand.brandName}</span></li>
										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/brands/updateBrand?id=${brand.brandId}&page=${curr_page}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/brands/deleteBrand?id=${brand.brandId}&page=${curr_page}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/brands/createBrand?page=${curr_page}" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							
						</tbody>
					</table>