<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header">
			<div class="header_cont width">
				<div class="logo_line"></div>
				<div class="logo">
					<a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/img/logo.gif" alt="" width="374" height="60"></a>
				</div>
				<div class="top_menu">
					<ul>
						<c:forEach items="${topMenuList}" var="menuItem">
							<li><a href="${pageContext.request.contextPath}/m?i=${menuItem.id}">${menuItem.menuName}</a></li>	
						</c:forEach>
					</ul>
				</div>
				<i class="text_top">Сделать и получить свой заказ просто, как <span	class="orange">РАЗ, ДВА, ТРИ, ЧЕТЫРЕ!</span></i>
				<div class="count_top">
					<ul>
						<li><i>1</i><a href="http://taobao88.org/vybor_tovara" target="_blank">Выбор товара</a></li>
						<li><i>2</i><a href="http://taobao88.org/zapolnenie_blanka" target="_blank">Заполнение бланка заказа</a></li>
						<li><i>3</i><a href="http://taobao88.org/oplata" target="_blank">Оплата заказа</a></li>
						<li><i>4</i><a href="http://taobao88.org/poluchenie" target="_blank">Получение заказа</a></li>
					</ul>
				</div>
				
				<!-- SEARCH BLOCK -->
				<jsp:include page="search_block.jsp"/>
			</div>
		</div>