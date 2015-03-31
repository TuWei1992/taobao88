<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>taobravo.com. Мои посылки</title>
	<meta charset="utf-8">
	<meta name="keywords" content="товары из китая, taobao на русском, таобао на русском языке, товары из китая по низким ценам, доставка из Китая для Россия, Казахстан, Украина, Беларусь">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_latest.min.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/office.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/card.css" type="text/css">
	<link rel="shortcut icon" href="http://taobravo.com/favicon.ico">
<!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="/css/ie6.css"/>
    <script  language="JavaScript" src="js/png.js" type="text/JavaScript"></script>
    <![endif]-->
<!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="/css/ie.css"/>
    <![endif]-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.custom.28468.js"></script>
	<script	type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.cslider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap_latest.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao.js"></script>
	<script type="text/javascript">
        $(function() {
            $('#basket').text('${basket}');
            $('#translate').click(function() {
            	translate();
            });
            $('[data-toggle="tooltip"]').tooltip();
        });      
    </script>
</head>
<body>
	<div id="wrapper">
  		<jsp:include page="partials/index_header.jsp"/>
  	</div>
		<div class="content">
		<div class="width">
			<jsp:include page="partials/horizontal_menu_logged.jsp"/>
			<div class="position">
				<span><a href="${pageContext.request.contextPath}/">Главная</a></span>
				<span>></span> 
				<span><a href="${pageContext.request.contextPath}/privateOffice">Мой аккаунт</a></span>
				<span>></span>
				<span><a href="${pageContext.request.contextPath}/privateOffice/toPackages">Мои посылки</a></span>
			</div>
			
			<c:if test="${newPackage != null}">
				<div id="legend">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true"><strong>&times;</strong></span><span class="sr-only">Close</span></button>
						<c:if test="${newPackage.size() == 1}">
							<h6>Номер вышей посылки :</h6>
							<ul>
								<c:forEach items="${newPackage}" var="packagesT">
									<li>${packagesT.idPackage}</li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${newPackage.size() > 1}">
							<h6>Так как общий вес Вашего посылки превышает 20 кг, то
								Ваша послыка была разделена на ${orders.size()}, номера посылок
								:</h6>
							<ul>
								<c:forEach items="${newPackage}" var="packagesT">
									<li>${packagesT.idPackage}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>
			</c:if>
			<div>
				<div class="control">
					<div class="alert alert-warning">
						Для перехода к оплате посылки или детального просмотра ее состояния щелкните по значку посылки.
					</div>
				
					<div class="o_head">
						<h3>Мои посылки</h3>
						<div>
							<span>Сервис по отслеживанию посылок - </span><span class="label label-success"><a href="http://post-tracker.ru/" target="_blank">Post-Tracker</a></span>
						</div>
					</div>
					<c:if test="${packages != null}">
						<table class="orders">
                			<thead>
                    			<tr>
                        			<th colspan="2">Посылка</th>
                        			<th>Состояние</th>
                        			<th>Стоимость</th>
                        			<th>Номер отправления</th>
									<th></th>
                    			</tr>
                			</thead>
							<tbody>
								<c:forEach items="${packages}" var="packageT">
                       				<tr>
										<td>
											<a href="${pageContext.request.contextPath}/privateOffice/showPackage?idPackage=${packageT.idPackage}" class="product-img">
												<img src="${pageContext.request.contextPath}/resources/img/box.png" />
											</a>
										</td>
										<td  class="product">
											<div class="property">
												<span class="order"><a href="${pageContext.request.contextPath}/privateOffice/showPackage?idPackage=${packageT.idPackage}" data-toggle="tooltip" data-placement="top" title="Щелкните для просмотра информации о посылке.">№: ${packageT.idPackage}</a></span>  
												<div>
													<c:if test="${TIME == packageT.datePackage}">
														<span class="label label-success" disabled>NEW</span>
													</c:if> 
													<c:if test="${TIME != packageT.datePackage}">
														<span class="label label-warning">Дата заказа: ${packageT.datePackage}</span>
													</c:if>
												</div>
											</div>
					        			</td>  
										<td>
											<span>
												<c:set var="i" value="${packageT.packagesStatuses.size()}"/>
												${packageT.packagesStatuses.get(i - 1).status.statusName}<br>
												<span class="label label-warning"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${packageT.packagesStatuses.get(i - 1).createdAt}"/></span>
											</span>
										</td>
										<td>
											<div>
												<span>$ <span class="price">${packageT.fullPrice}</span></span>                            
											</div>
										</td>
										<td>
											<div>
												<c:if test="${empty packageT.tracknumber}">
													<span>н/д</span>
												</c:if>
												<span>${packageT.tracknumber}</span>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
       					</table>
       				</c:if>
       				<div class="goods-list">
       					<c:forEach items="${recomendations}" var="rec">
							<div id="${rec.id}">
								<div class="shop-item">
									<div>
										<div class="item-box">
											<div class="item-thumb">
												<a href="${pageContext.request.contextPath}/item?id=${rec.id}" target="_blank" class="item-lnk thumbnail"> 
													<img src="/images/${rec.photo}" alt="New fashion">
												</a>
											</div>
											<div class="item-meta">
												<div class="item-ttl">
													<a href="${pageContext.request.contextPath}/item?id=${rec.id}" target="_blank" title="${rec.description}">${rec.description}</a>
												</div>
												<div class="item-prc">
													<span>${rec.price}</span>
												</div>
												<div class="item-buy">
													<c:choose>
														<c:when test="${pageContext.request.userPrincipal.name != null}">
															<a href="#" onclick="addToBasket('${pageContext.request.contextPath}',${rec.id});">В корзину</a>
														</c:when>
														<c:otherwise>
															<a href="${pageContext.request.contextPath}/login" target="_blank">В корзину</a>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>	
					</div>
				</div>
			</div>
		</div>		
	</div>
	<jsp:include page="partials/index_footer.jsp" />
</body>
</html>