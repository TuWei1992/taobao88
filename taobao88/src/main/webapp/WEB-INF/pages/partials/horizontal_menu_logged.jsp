<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('.basket_entry').click(function() {
			if ('${currentIdUser}' == '') {
				$('.basket_entry').attr('href', '${pageContext.request.contextPath}/login');
			} 
		});
		
		if ('${messages_count}' != 0) {
			$('#messages_count').text('+' + '${messages_count}');
			setInterval(function() {
				$('#messages_count').fadeTo('slow', 0.1);
				$('#messages_count').fadeTo('slow', 1.0);
			}, 1000);
		}	
	});
</script>

<jsp:include page="../modal/order_modal.jsp" />
<jsp:include page="../modal/added_to_basket_modal.jsp" />

<div class="un_menu">
				<ul class="ul_menu">
					<li>
						<a href="${pageContext.request.contextPath}/privateOffice/accountSettings">
							<i class="icon inst"></i>Мой настройки
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/privateOffice/toPackages">
							<i class="icon bag"></i>Мои посылки
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/privateOffice/showMessages">
							<i class="icon mail"></i>Мои сообщения <span id="messages_count" style="font-size: 10pt; color: white;"></span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/privateOffice/showBalance">
							<i class="icon count"></i>Пополнить счёт
						</a>
					</li>
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
					<!-- <li><a href="#"><i class="icon wish"></i>Мой лист желаний</a></li> -->
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