<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="da-slider" class="da-slider">
	<c:forEach items="${slider}" var="rec">
		<div class="da-slide">
			<h2>${rec.description}</h2>
			<a href="${rec.href}" class="da-link">от ${rec.price}$</a>
			<div class="da-img">
				<img alt="image01" src="/images/${rec.photo}">
			</div>
		</div>
	</c:forEach>
</div>
<div class="shop-ttl frst-ttl">
	<span>Наши рекомендации</span>
</div>
<div class="prepouse">
	<c:forEach items="${recomendations}" var="recomendList">
		<div class="row">
			<div class="goods-list">
				<c:forEach items="${recomendList}" var="rec">
					<div id="${rec.id}">
						<div class="shop-item">
							<div>
								<div class="item-box">
									<div class="item-thumb">
										<a href="${pageContext.request.contextPath}/item?id=${rec.id}" target="_blank" class="item-lnk thumbnail"> <img
											src="/images/${rec.photo}" alt="New fashion">
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
	</c:forEach>
</div>