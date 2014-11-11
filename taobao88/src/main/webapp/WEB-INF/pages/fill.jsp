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
            
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
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
               		<div id="img-product">
                   		<img src="resources/img/${good.photo}" class="active-image" height="100%" alt="">
               		</div>
               		<div class="slides-product">
                   		<ul id="pr-carousel" class="jcarousel">
                  			<c:forEach items="${good.recomendation.images}" var="img">
                          		<li class="" >
                               		<a href="#"><img src="/images/${img.imageName}" alt=""></a>
                           		</li>
                       		</c:forEach>
                       	</ul>
               		</div>	
			    </div>
		 	<!-- central block -->
				<div class="col">
					<form action="${pageContext.request.contextPath}/fillUpdate" method="POST">
						<fieldset style="border:none;">
							<div class="row-form">
								<input type="hidden" name="idGoods" value="${good.idGoods}">
								<label>Цвет:</label>
								<div class="overflow">
									<select class="form in" name="color">
										<c:forEach items="${good.recomendation.colors}" var="color">
											<option value="${color.colorName}">${color.colorName}</option>
										</c:forEach>
									</select> 
								</div>
							</div>
							<div class="row-form">
								<label>Размер:</label>
								<div class="overflow">
									<select class="form in" name="size">
										<c:forEach items="${good.recomendation.sizes}" var="size">
											<option value="${size.sizeName}">${size.sizeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Количество:</label>
								<div class="overflow">
									<select class="form" name="count">
										<c:forEach var="i" begin="1" end="${good.recomendation.count}">
   											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row-form">
								<label>Вес:</label>
								<a href="#">${good.weightGoods}g</a>
							</div>
							<div class="row-form">
								<label>Фотоотчет:
									<c:choose>
										<c:when test="${good.photoGoods != null}">
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