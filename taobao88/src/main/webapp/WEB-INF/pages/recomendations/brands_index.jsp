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
									<button type="button" role="button"
										class="btn btn-success create_brand_btn">Создать</button>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${brands}" var="brand">
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
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateBrand?id=${brand.brandId}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteBrand?id=${brand.brandId}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>