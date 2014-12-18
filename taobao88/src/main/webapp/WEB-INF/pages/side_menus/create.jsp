<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="col-md-6 col-md-offset-2">
<div class="page-header">
  <h4><span class="label label-primary">Создание меню</span></h4>
</div>
<form role="form" action="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu/createMenu/doCreate" method="POST">
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
							placeholder="Укажите ссылку для меню" required>
					</div>
					
					<div class="form-group">
						<label class="input" for="menu_order">Порядок:</label> 
						<input class="form-control menu_order" id="menu_order" type="text" name="menuOrder" placeholder="пример: 1 - отобразится первым и т.д." required>
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
					<a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu" role="button" class="btn btn-default pull-left">Назад</a>
					<button type="submit" class="btn btn-success pull-right">Создать</button>
			</form>
			</div>