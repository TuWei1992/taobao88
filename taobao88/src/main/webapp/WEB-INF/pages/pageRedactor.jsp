<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="adminStyles.jsp" />
<meta charset="utf-8">
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function($) {
			$('#myTab a').click(function (e) {
				e.preventDefault()
				$(this).tab('show')
			})			
		});
	</script>
</head>
<body>

	<jsp:include page="adminMenu.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="page-header">
  				<h1>Редактор <small>управление контентом</small></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">

			<ul class="nav nav-tabs" role="tablist">
				<li class="<c:if test="${side_menu_index || side_menu_create || side_menu_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/sideMenu">Боковое меню</a></li>
				<li class="<c:if test="${recomendation_index || recomendation_create || recomendation_update || recomendation_view}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation">Рекомендации</a></li>
				<li class="<c:if test="${slider_index || slider_create || slider_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/slider">Слайдер</a></li>
				<li class="<c:if test="${banner_index || banner_create || banner_update || banner_view}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/banner">Баннер</a></li>
				<li class="<c:if test="${discount_index || discount_create || discount_update || discount_view}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/discount">Товары	со скидками</a></li>
				<%--<li class="<c:if test="${free_ship_index || free_ship_create || free_ship_update || free_ship_view}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/freeShip">Бесплатная доставка</a></li> --%>
				<li class="<c:if test="${brands_index || brands_create || brands_update || brands_view}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/brands">Бренды</a></li>
				<li class="<c:if test="${top_menu_index || top_menu_create || top_menu_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu">Топ Меню</a></li>
				<li class="<c:if test="${user_account_index}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/userAccount">Аккаунт пользователя</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
			
				<div class="tab-pane <c:if test="${side_menu_index || side_menu_create || side_menu_update}">active</c:if>" id="sideMenu">
					<c:if test="${side_menu_index}">
						<jsp:include page="side_menus/index.jsp"/>
					</c:if>
					<c:if test="${side_menu_create}">
						<jsp:include page="side_menus/create.jsp"/>
					</c:if>
					<c:if test="${side_menu_update}">
						<jsp:include page="side_menus/update.jsp"/>
					</c:if>
				</div>

				<div class="tab-pane <c:if test="${recomendation_index || recomendation_create || recomendation_update || recomendation_view}">active</c:if>" id="recomendations">
					<c:if test="${recomendation_index}">
						<jsp:include page="recomendations/index.jsp"/>
					</c:if>
					<c:if test="${recomendation_create}">
						<jsp:include page="recomendations/create.jsp"/>
					</c:if>
					<c:if test="${recomendation_update}">
						<jsp:include page="recomendations/update.jsp"/>
					</c:if>
					<c:if test="${recomendation_view}">
						<jsp:include page="recomendations/view.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${slider_index || slider_create || slider_update}">active</c:if>" id="slider">
					<c:if test="${slider_index}">
						<jsp:include page="sliders/index.jsp"/>
					</c:if>
					<c:if test="${slider_create}">
						<jsp:include page="sliders/create.jsp"/>
					</c:if>
					<c:if test="${slider_update}">
						<jsp:include page="sliders/update.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${banner_index || banner_create || banner_update || banner_view}">active</c:if>" id="banner">
					<c:if test="${banner_index}">
						<jsp:include page="banners/index.jsp"/>
					</c:if>
					<c:if test="${banner_create}">
						<jsp:include page="banners/create.jsp"/>
					</c:if>
					<c:if test="${banner_update}">
						<jsp:include page="banners/update.jsp"/>
					</c:if>
					<c:if test="${banner_view}">
						<jsp:include page="banners/view.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${discount_index || discount_create || discount_update || discount_view}">active</c:if>" id="discount">
					<c:if test="${discount_index}">
						<jsp:include page="discount/index.jsp"/>
					</c:if>
					<c:if test="${discount_create}">
						<jsp:include page="discount/create.jsp"/>
					</c:if>
					<c:if test="${discount_update}">
						<jsp:include page="discount/update.jsp"/>
					</c:if>
					<c:if test="${discount_view}">
						<jsp:include page="discount/view.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${free_ship_index || free_ship_create || free_ship_update || free_ship_view}">active</c:if>" id="free_ship">
					<c:if test="${free_ship_index}">
						<jsp:include page="free_ship/index.jsp"/>
					</c:if>
					<c:if test="${free_ship_create}">
						<jsp:include page="free_ship/create.jsp"/>
					</c:if>
					<c:if test="${free_ship_update}">
						<jsp:include page="free_ship/update.jsp"/>
					</c:if>
					<c:if test="${free_ship_view}">
						<jsp:include page="free_ship/view.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${brands_index || brands_create || brands_update || brands_view}">active</c:if>" id="brands">
					<c:if test="${brands_index}">
						<jsp:include page="brands/index.jsp"/>
					</c:if>
					<c:if test="${brands_create}">
						<jsp:include page="brands/create.jsp"/>
					</c:if>
					<c:if test="${brands_update}">
						<jsp:include page="brands/update.jsp"/>
					</c:if>
					<c:if test="${brands_view}">
						<jsp:include page="brands/view.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${top_menu_index || top_menu_create || top_menu_update}">active</c:if>" id="topMenu">
					<c:if test="${top_menu_index}">
						<jsp:include page="top_menus/index.jsp"/>
					</c:if>
					<c:if test="${top_menu_create}">
						<jsp:include page="top_menus/create.jsp"/>
					</c:if>
					<c:if test="${top_menu_update}">
						<jsp:include page="top_menus/update.jsp"/>
					</c:if>
				</div>
				
				<div class="tab-pane <c:if test="${user_account_index}">active</c:if>" id="userAcc">
					<c:if test="${user_account_index}">
						<jsp:include page="user_account/index.jsp"/>
					</c:if>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>