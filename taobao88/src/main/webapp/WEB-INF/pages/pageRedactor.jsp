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
			
			$('.create_menu').on('click', function() {
				$('#sideMenuModal').modal('show');
			});
			
			$('.create_sl_btn').on('click', function() {
				$('#slModal').modal('show');
			});
			
			$('.create_banner_btn').on('click', function() {
				$('#bannerModal').modal('show');
			});
			
			$('.create_brand_btn').on('click', function() {
				$('#brandModal').modal('show');
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
	<jsp:include page="modal/sidemenu_modal.jsp" />
	<jsp:include page="modal/slider_modal.jsp" />
	<jsp:include page="modal/banner_modal.jsp" />
	<jsp:include page="modal/brands_modal.jsp" />
	
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
				<li class="active"><a href="#sideMenu" role="tab" data-toggle="tab">Боковое меню</a></li>
				<li><a href="#recomendations" role="tab" data-toggle="tab">Рекомендации</a></li>
				<li><a href="#slider" role="tab" data-toggle="tab">Слайдер</a></li>
				<li><a href="#banner" role="tab" data-toggle="tab">Баннер</a></li>
				<li><a href="#discount" role="tab" data-toggle="tab">Товары	со скидками</a></li>
				<li><a href="#free_ship" role="tab" data-toggle="tab">Бесплатная доставка</a></li>
				<li><a href="#brands" role="tab" data-toggle="tab">Бренды</a></li>
				<li><a href="#topMenu" role="tab" data-toggle="tab">Топ Меню</a></li>
				<li><a href="#userAcc" role="tab" data-toggle="tab">Аккаунт пользователя</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane active" id="sideMenu">

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
									<button type="button" role="button"
										class="btn btn-success create_menu">Создать меню</button>
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
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteMenu?id=${menu.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
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
												<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateMenu?id=${m.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
												<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteMenu?id=${m.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="tab-pane" id="recomendations">
					<br>
					<jsp:include page="recomendations/index.jsp"/>
				</div>
				<div class="tab-pane" id="slider">
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
										class="btn btn-success create_sl_btn">Создать</button>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${slider}" var="sli">
								<tr>
									<td><img src="/images/${sli.photo}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="250" height="250" class="img-thumbnail"></td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${sli.description}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${sli.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${sli.href}</span></li>
  										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateBanner?id=${sli.id}" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteRecomendation?id=${sli.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/createRecomendation" type="button" role="button" class="btn btn-success create_rec_btn">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${discount}" var="dsc">
								<tr>
									<td>
										<c:forEach items="${dsc.images}" var="img">
											<img src="/images/${img.imageName}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"	class="img-thumbnail">
										</c:forEach>
									</td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${dsc.description}</span></li>
  											<li class="list-group-item"><b>Подробное описание:</b> <span>${dsc.longDescription}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${dsc.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${dsc.href}</span></li>
  											<li class="list-group-item"><b>Цвета:</b>  
  												<c:forEach items="${dsc.colors}" var="color">
  													<span>${color.colorName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Размеры:</b>  
  												<c:forEach items="${dsc.sizes}" var="size">
  													<span>${size.sizeName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Количество:</b> <span>${dsc.count}</span>, <b>Вес:</b> <span>${dsc.weight}</span></li>
										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateRecomendation?id=${dsc.id}" target="_blank" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteRecomendation?id=${dsc.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="free_ship">
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
									<a href="${pageContext.request.contextPath}/admin/pageRedactor/createRecomendation" type="button" role="button" class="btn btn-success create_rec_btn">Создать</a>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${free_ship}" var="fs">
								<tr>
									<td>
										<c:forEach items="${fs.images}" var="img">
											<img src="/images/${img.imageName}" alt="${pageContext.request.contextPath}/resources/img/empty_good.png" width="100" height="100"	class="img-thumbnail">
										</c:forEach>
									</td>
									<td>
										<ul class="list-group">
  											<li class="list-group-item"><b>Краткое описание:</b> <span>${fs.description}</span></li>
  											<li class="list-group-item"><b>Подробное описание:</b> <span>${fs.longDescription}</span></li>
  											<li class="list-group-item"><b>Цена:</b> <span>${fs.price}</span></li>
  											<li class="list-group-item"><b>Ссылка:</b> <span>${fs.href}</span></li>
  											<li class="list-group-item"><b>Цвета:</b>  
  												<c:forEach items="${fs.colors}" var="color">
  													<span>${color.colorName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Размеры:</b>  
  												<c:forEach items="${fs.sizes}" var="size">
  													<span>${size.sizeName}</span>
  												</c:forEach>
  											</li>
  											<li class="list-group-item"><b>Количество:</b> <span>${fs.count}</span>, <b>Вес:</b> <span>${fs.weight}</span></li>
										</ul>
									</td>
									<td>
										<div class="btn-group">
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/updateRecomendation?id=${fs.id}" target="_blank" class="btn btn-default glyphicon glyphicon-pencil"></a>
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteRecomendation?id=${fs.id}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="brands">
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
											<a type="button" href="${pageContext.request.contextPath}/admin/pageRedactor/deleteBrand?id=${brand.brandId}" class="btn btn-default glyphicon glyphicon-remove"></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="topMenu">
					<br>
					<jsp:include page="top_menus/index.jsp"/>
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