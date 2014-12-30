<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('.basket_entry').click(function() {
			if ('${currentIdUser}' == '') {
				$('.basket_entry').attr('href', '${pageContext.request.contextPath}/login');
			} 
		});
	});
</script>

<jsp:include page="../modal/order_modal.jsp" />
<jsp:include page="../modal/added_to_basket_modal.jsp" />

<div class="ul_menu">
				<ul>
					<li><a href="${pageContext.request.contextPath}/brands"	id="brandsBtn"><i class="icon brend"></i>Бренды</a></li>
					<li><a href="${pageContext.request.contextPath}/discount"><i class="icon rea"></i>Товары со скидками</a></li>
					<!-- <li><a href="${pageContext.request.contextPath}/comments"><i class="icon social"></i>Товары с отзывами</a></li> -->
					<!-- <li><a href="${pageContext.request.contextPath}/free"><i class="icon free"></i>Бесплатная доставка!</a></li> -->
					<li><a href="${pageContext.request.contextPath}/privateOffice"><i class="icon office"></i>Мой аккаунт</a></li>
					<li><a href="javascript:void(0);" class="orderHref" data-toggle="modal" data-target="#orderModal">Сделать заказ</a></li>
					<li>
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<a href="${pageContext.request.contextPath}/basket">Корзина: <span id="basket" style="font-size: 10pt; color: white;"></span></a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/login" target="_blank">Корзина: <span id="basket" style="font-size: 10pt; color: white;"></span></a>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="entre">
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<a href="${pageContext.request.contextPath}/logout">Выйти</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/login">Войти</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			</div>