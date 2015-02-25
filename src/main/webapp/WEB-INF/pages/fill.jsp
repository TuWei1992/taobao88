<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник Taobao</title>
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
<link rel="shortcut icon" href="http://taobao88.org/favicon.ico">
<!--item CSS --> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/item.css" type="text/css" media="screen"/>
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
<script  language="JavaScript" src="/js/png.js" type="text/JavaScript"></script>
<![endif]-->
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="/css/ie.css"/>
<![endif]-->
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.easing.1.3.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.galleryview-3.0-dev.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/gallery/css/jquery.galleryview-3.0-dev.css" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="./css/ie.css" />
    <script type="text/javascript" src="./js/curvycorners.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix(".cuselFrameRight");
        jQuery(document).ready(function(){
            jQuery(".cusel").each(
                    function(){
                        var w = parseInt(jQuery(this).width()),
                                scrollPanel = jQuery(this).find(".cusel-scroll-pane");
                        if(w>=scrollPanel.width())
                        {
                            jQuery(this).find(".jScrollPaneContainer").width(w);
                            scrollPanel.width(w);
                        }
                    });
        });
    </script>
    <![endif]-->
<script>
        $(document).ready(function(){
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
            $('#myGallery').galleryView({
            	panel_width: 350,
            	panel_height: 350,
            	frame_width: 70,
            	frame_height: 50
            });
            $('.gv_gallery').width(340);
            $('.gv_galleryWrap').height(405);
        });
</script>
</head>
<body>
	<section class="wrapper">
	<!-- HEADER -->
	<jsp:include page="partials/index_header.jsp"/>
	<div class="width">
		<!-- HORIZONTAL_MENU -->
		<jsp:include page="partials/horizontal_menu_logged.jsp"/>
		<div class="left-block"> 
			<div class="content">
	 		<!-- image slide -->
				<div class="product-container">
					<ul id="myGallery">
							<c:forEach items="${orderT.goods.recomendation.images}" var="img">
                          		<li class="" >
                               		<img src="/images/${img.imageName}" alt="" width="100%">
                           		</li>
                       		</c:forEach>
						</ul>
			    </div>
		 	<!-- central block -->
				<div class="col">
					<form action="${pageContext.request.contextPath}/fillUpdate" method="POST">
						<fieldset style="border:none;">
							<input type="hidden" name="idGoods" value="${orderT.goods.idGoods}">
							<div class="row-form">
								<label>Номер заказа: <span>${orderT.idOrder}</span></label>
							</div>
							<div class="row-form">
								<label>Цвет:</label>
								<div class="overflow">
									<select class="form in" name="color">
										<c:forEach items="${orderT.goods.recomendation.colors}" var="color">
											<option value="${color.colorName}">${color.colorName}</option>
										</c:forEach>
									</select> 
								</div>
							</div>
							<div class="row-form">
								<label>Размер:</label>
								<div class="overflow">
									<select class="form in" name="size">
										<c:forEach items="${orderT.goods.recomendation.sizes}" var="size">
											<option value="${size.sizeName}">${size.sizeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Количество:</label>
								<div class="overflow">
									<select class="form" name="amount">
										<c:forEach var="i" begin="1" end="${orderT.goods.recomendation.count}">
   											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Вес:</label>
								<a href="#">${orderT.goods.weightGoods}g</a>
							</div>
							<div class="row-form">
								<label>Фотоотчет:
									<c:choose>
										<c:when test="${orderT.goods.photoGoods == 'true'}">
											<input type="checkbox" name="photoGoods" checked="checked">
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="photoGoods">
										</c:otherwise>
									</c:choose>
								</label>
								
							</div>
							<div class="btn-form">
								<input type="submit" value="Сохранить" class="submitbutton">
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
 	</section>
 	<!-- FOOTER -->
	<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>