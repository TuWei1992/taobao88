<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Редактирование меню</span></h4>
</div>
				<form role="form" method="POST" accept-charset="utf-8" action="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/updateMenu/doUpdate">
				<input type="hidden" name="page" value="${curr_page}">
					<div class="form-group">
						<label class="input" for="menu_name">Название меню</label>
						<input id="menu_name" class="form-control menu_name" type="text" name="menuName" value="${menu.menuName}">
						<input id="id" type="hidden" name="id" value="${menu.id}">
					</div>
					<div class="form-group">
						<label class="input" for="menu_href">Ссылка</label>
						<input class="form-control menu_href" id="menu_href" type="text" name="menuHref" value="${menu.menuHref}">
					</div>
					<div class="form-group">
						<label class="input" for="menu_order">Порядок</label> 
						<input class="form-control menu_order" id="menu_order" type="text" name="menuOrder" value="${menu.menuOrder}">
					</div>
					<div class="form-group">
								<label class="input" for="menu_order">Подкатегория в:</label> 
								<select class="form-control" name="parentId" id="parentId">
									<option value="0">Корневая</option>
									<c:forEach items="${side_menu}" var="m">
										<option value="${m.id}" <c:if test="${m.id == menu.parentId}">selected</c:if>>${m.menuName}</option>							
									</c:forEach>
								</select>
							</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu" role="button" class="btn btn-default pull-left">Назад</a>
						<button class="btn btn-success pull-right" type="submit">Обновить</button>
					</div>
				</form>
			</div>
		