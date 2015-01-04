<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav>
  <ul class="pagination">
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${curr_page - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
    	<li <c:if test="${loop.index == curr_page}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${loop.index}">${loop.index}</a></li>
    </c:forEach>
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${curr_page + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
  <ul class="pagination">
  	<li><a href="javascript:void(0);">Уровень меню</a></li>
  </ul>
</nav>

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
											<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/createMenu?parentId=${menu.id}" role="button" class="btn btn-default glyphicon glyphicon-plus"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
<nav>
  <ul class="pagination">
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${curr_page - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
    	<li <c:if test="${loop.index == curr_page}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${loop.index}">${loop.index}</a></li>
    </c:forEach>
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu?page=${curr_page + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
  <ul class="pagination">
  	<li><a href="javascript:void(0);">Уровень меню</a></li>
  </ul>
</nav>