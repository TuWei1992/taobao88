<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="adminStyles.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tinymce/tinymce.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function($) {
			$('#myTab a').click(function (e) {
				e.preventDefault()
				$(this).tab('show')
			})
			
			$('.create_banner_btn').on('click', function() {
				$('#bannerModal').modal('show');
			});
			
			tinymce.init({
			    selector: "#content",
			    height : 500,
			    theme: "modern",
			    plugins: "image",
			    	image_advtab: true,
			    	image_description: false
			 });	
		});
	</script>
</head>
<body>

	<jsp:include page="adminMenu.jsp" />
	<jsp:include page="modal/banner_modal.jsp" />
	
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
				<li><a href="#recomendations" role="tab" data-toggle="tab">Рекомендации</a></li>
				<li class="<c:if test="${slider_index || slider_create || slider_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/slider">Слайдер</a></li>
				<li><a href="#banner" role="tab" data-toggle="tab">Баннер</a></li>
				<li><a href="#discount" role="tab" data-toggle="tab">Товары	со скидками</a></li>
				<li><a href="#free_ship" role="tab" data-toggle="tab">Бесплатная доставка</a></li>
				<li class="<c:if test="${brands_index || brands_create || brands_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/brands">Бренды</a></li>
				<li class="<c:if test="${top_menu_index || top_menu_create || top_menu_update}">active</c:if>"><a href="${pageContext.request.contextPath}/admin/pageRedactor/topMenu">Топ Меню</a></li>
				<li><a href="#userAcc" role="tab" data-toggle="tab">Аккаунт пользователя</a></li>
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

				<div class="tab-pane" id="recomendations">
					<br>
					<jsp:include page="recomendations/index.jsp"/>
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
				
				<div class="tab-pane" id="banner">
					<br>
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
										class="btn btn-success create_banner_btn">Создать</button>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${banner}" var="item">
								<tr>
									<td><img src="/images/${item.photo}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="250" height="250" class="img-thumbnail"></td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${item.description}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${item.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${item.href}</span></li>
  										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateBanner?id=${item.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteRecomendation?id=${item.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="discount">
					<br>
					<jsp:include page="recomendations/discount.jsp"/>
				</div>
				<div class="tab-pane" id="free_ship">
					<br>
					<jsp:include page="recomendations/free_shipping.jsp"/>
				</div>
				
				<div class="tab-pane <c:if test="${brands_index || brands_create || brands_update}">active</c:if>" id="brands">
					<c:if test="${brands_index}">
						<jsp:include page="brands/index.jsp"/>
					</c:if>
					<c:if test="${brands_create}">
						<jsp:include page="brands/create.jsp"/>
					</c:if>
					<c:if test="${brands_update}">
						<jsp:include page="brands/update.jsp"/>
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
				<div class="tab-pane" id="userAcc">
					<br>
					<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-xs-12">
			<form role="form" method="POST" accept-charset="utf-8" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/pageRedactor/updatedPageContent">
					<input type="hidden" name="page" value="privateOffice"/>
										
					<div class="form-group">
						<label class="input" for="content">Описание:</label>
						<textarea class="form-control" name="content" id="content" placeholder="Описание" required="required">${privateOffice.content}</textarea>
					</div>
					
					<div class="form-group">
						<button type="submit" role="button" class="btn btn-success create">Обновить</button>
					</div>
			</form>
			</div>
		</div>
	</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>