<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-hover">
						<thead>
							<tr>
								<th>Название</th>
								<th>Описание</th>
								<th>Порядок</th>
								<th>Действия</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu/createTopMenu" type="button" role="button" class="btn btn-success">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${topMenuList}" var="menu">
								<tr>
									<td>${menu.menuName}</td>
									<td>${menu.menuDescription}</td>
									<td>${menu.menuOrder}</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu/updateTopMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu/viewTopMenu?id=${menu.id}" target="_blank" class="btn btn-default glyphicon glyphicon-eye-open"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu/deleteTopMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>