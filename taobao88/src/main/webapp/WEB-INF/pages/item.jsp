<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>taobao88. Товары из Китая. Аукцион Таобао. Посредник Taobao</title>
<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-latest.css" type="text/css">
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.cslider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.jcarousel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
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
            var spinner = $( "#spinner" ).spinner();
            $( "#tabs" ).tabs();
            $(".product li:nth-child(3n)").css("margin-right","0");
            <!--select-->
            <!--jcarousel-->
            $('#pr-carousel').jcarousel({
                scroll:4,
                start: 1
            });
            $(".jcarousel-item").click(function(){
                $(".active").removeClass("active");
                $(this).addClass("active");
				var activeImage = $('.active img').attr('src');
				$('.active-image').attr('src', activeImage);
            });
            <!--select-->
            var params = {
                changedEl: "#s-category,#s-color,#s-size",
                visRows: 12,
                scrollArrows: false,
                refreshEl: "#s-category,#s-color,#s-size"
            }
            $('.jcarousel').jcarousel(params);
            
            $('#basket').text('${basket.size()}');
            $('#translate').click(function() {
            	translate();
            });
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
               		<div id="img-product">
                   		<img src="/images/${item.photo}" class="active-image" height="100%" alt="">
               		</div>
               		<div class="slides-product">
                   		<ul id="pr-carousel" class="jcarousel">
                  			<c:forEach items="${item.images}" var="img">
                          		<li class="" >
                               		<a href="#"><img src="/images/${img.imageName}" alt=""></a>
                           		</li>
                       		</c:forEach>
                       	</ul>
               		</div>	
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
								<a href="#">${item.weight}g</a>
							</div>
							<div class="btn-form">
								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<input type="button" class="submitbutton" value="В корзину" onclick="addToBasket('${pageContext.request.contextPath}',${item.id});">
								</c:if>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="main-hot-right">
				<ul>
   					<li><a href="#">hot products</a></li>
				</ul>
				<div class="best1" style="background: url(/images/${item.photo});">
					<a href="${item.href}">
						<p>${item.price}$</p>${item.description}
					</a>
				</div>
			</div>
		</div>
	</div>
 </div>

 <div class="width">
 	<div class="tabs">
    	<ul>
        	<li>
            	<input type="radio" name="tabs0" checked="checked" id="tabs0-0" /><label for="tabs0-0">Информация</label>
            	<div>
                	<h3>${item.description}</h3>
					<p>${item.longDescription}</p>
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
	</div>
</div> 
<jsp:include page="partials/index_footer.jsp"/>
</body>
</html>