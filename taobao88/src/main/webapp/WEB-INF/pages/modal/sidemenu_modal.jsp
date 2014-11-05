<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="sideMenuModal" tabindex="-1" role="dialog"
	aria-labelledby="sideMenuModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="sideMenuModalLabel">Создать меню</h4>
			</div>
			<form role="form" action="${pageContext.request.contextPath}/admin/pageRedactor/createMenu" method="POST">
				<div class="modal-body">
					<div class="form-group">
						<label class="input" for="menu_name">Название меню</label> <input
							id="menu_name" class="form-control menu_name" type="text"
							name="menuName" placeholder="Укажите название меню" required="required"/>
					</div>

					<div class="form-group">
						<label class="input" for="menu_href">Ссылка</label> <input
							class="form-control menu_href" id="menu_href" type="text"
							name="menuHref" value="http://"
							placeholder="Укажите ссылку для меню" required="required"/>
					</div>
					
					<div class="form-group">
						<label class="input" for="menu_order">Порядок:</label> 
						<input class="form-control menu_order" id="menu_order" type="text" name="menuOrder" placeholder="пример: 1 - отобразится первым и т.д." required="required"/>
					</div>
					
					<c:choose>
						<c:when test="${side_menu != null}">
							<div class="form-group">
								<label class="input" for="menu_order">Подкатегория в:</label> 
								<select class="form-control" name="parentId" id="parentId">
									<option value="0">Корневая</option>
									<c:forEach items="${side_menu}" var="menu">
										<option value="${menu.id}">${menu.menuName}</option>							
									</c:forEach>
								</select>
							</div>
						</c:when>
						<c:when test="${sideMenu == null}">
							<input type="hidden" name="parentId" value="0">
						</c:when>
					</c:choose>

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Создать</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
			</form>
		</div>
	</div>
</div>