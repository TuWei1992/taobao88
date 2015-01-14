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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slider.css" type="text/css">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.easing.1.3.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gallery/js/jquery.galleryview-3.0-dev.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
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
<div class="wrapper">
	<jsp:include page="partials/index_header.jsp"/>
	<div class="width">
		<jsp:include page="partials/horizontal_menu.jsp"/>
		<div class="left-block"> 
			<div class="content">
	 		<!-- image slide -->
				<div class="product-container">
               		<%--<div id="img-product">
                   		<img src="/images/${item.photo}" class="active-image" width="100%" alt="">
               		</div>
               		<div class="slides-product">
                   		<ul id="pr-carousel" class="jcarousel">
                  			<c:forEach items="${item.images}" var="img">
                          		<li class="" >
                               		<a href="#"><img src="/images/${img.imageName}" alt="" width="100%"></a>
                           		</li>
                       		</c:forEach>
                       	</ul>
               		</div>--%>
               			<ul id="myGallery">
							<c:forEach items="${item.images}" var="img">
                          		<li class="" >
                               		<img src="/images/${img.imageName}" alt="" width="100%">
                           		</li>
                       		</c:forEach>
						</ul>
			    </div>
		 	<!-- central block -->
				<div class="col">
					<form>
						<input type="hidden" name="id" value="${item.id}"/>
						<fieldset style="border:none;">
							<div class="row-form">
								<label>Цвет:</label>
								<div class="overflow">
									<select class="form in" name="color">
										<c:forEach items="${item.colors}" var="color">
											<option value="${color.id}">${color.colorName}</option>
										</c:forEach>
									</select> 
								</div>
							</div>
							<div class="row-form">
								<label>Размер:</label>
								<div class="overflow">
									<select class="form in" name="size">
										<c:forEach items="${item.sizes}" var="size">
											<option value="${size.id}">${size.sizeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Количество:</label>
								<div class="overflow">
									<select class="form" name="count">
										<c:forEach var="i" begin="1" end="${item.count}">
   											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Вес:</label>
								<a href="javascript:void(0);">${item.weight}g</a>
							</div>
							<div class="row-form">
								<label>Цена:</label>
								
									<a href="javascript:void(0);" style="color: #d1233e; font: bold 16px Verdana;">${item.price}</a>
								
							</div>
							<div>
								<label class="label label-success"><a href="${item.href}" title="Ссылка на тао" target="_blank">Ссылка на тао</a></label>
							</div>
							<div class="btn-card btn-form">
								
								<c:choose>
												<c:when test="${pageContext.request.userPrincipal.name != null}">
													<a href="#" onclick="addToBasket('${pageContext.request.contextPath}',${item.id});" style="margin-right: 0px;">В корзину</a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/login" target="_blank" style="margin-right: 0px;">В корзину</a>
												</c:otherwise>
											</c:choose>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<c:if test="${banner.size() != 0}">
			<div class="main-hot-right">
				<div class="best1" style="background: url(/images/${banner.get(0).photo}) no-repeat; background-size: cover;">
					<a href="${banner.get(0).href}">
						<p>${banner.get(0).price}$</p>${banner.get(0).description}
					</a>
				</div>
			</div>
			</c:if>
		</div>
	</div>
 </div>

 <div class="width">
 	${item.longDescription}
 	<%--<div class="tabs">
    	<ul>
        	<li>
            	<input type="radio" name="tabs0" checked="checked" id="tabs0-0" /><label for="tabs0-0">Информация</label>
            	<div>
                	<h3>${item.description}</h3>
					<p></p>
            	</div>
        	</li>
        	<li>
            	<input type="radio" name="tabs0" id="tabs0-1" /><label for="tabs0-1">Отзывы</label>
            	<div>
                	<c:choose>
                		<c:when test="${item.comments.size() > 0}">
                			Отзывы...
                		</c:when>
                		<c:when test="${item.comments.size() == 0}">
                			<h3>У данного товара нет отзывов</h3>
                		</c:when>
                	</c:choose>
            	</div>
        	</li>
    	</ul>
	</div>--%>
</div> 
<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>