<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table class="table table-hover">
						<thead>
							<tr>
								<th>Название меню</th>
								<th>Ссылка</th>
								<th>Порядок</th>
								<th>Родительская категория</th>
								<th>Действия</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/createMenu" role="button" class="btn btn-success">Создать меню</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${side_menu}" var="menu">
								<tr>
									<td>${menu.menuName}</td>
									<td>${menu.menuHref}</td>
									<td>${menu.menuOrder}</td>
									<td>${menu.parent.menuName}</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/updateMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/deleteMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/createMenu" role="button" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
								<c:forEach items="${menu.children}" var="m">
									<tr>
										<td>${m.menuName}</td>
										<td>${m.menuHref}</td>
										<td>${m.menuOrder}</td>
										<td>${menu.menuName}</td>
										<td>
											<div class="btn-group">
												<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/updateMenu?id=${m.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
												<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/deleteMenu?id=${m.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
												<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/createMenu" role="button" class="btn btn-default glyphicon glyphicon-plus"></a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>