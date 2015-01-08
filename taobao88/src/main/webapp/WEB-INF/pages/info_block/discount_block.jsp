<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="shop-ttl frst-ttl">
	<span>Товары со скидками</span>
</div>
<div class="prepouse">
	<c:forEach items="${discount}" var="discountList">
		<div class="row">
			<div class="goods-list">
				<c:forEach items="${discountList}" var="disc">
					<div id="${disc.id}">
						<div class="shop-item">
							<div>
								<div class="item-box">
									<%-- <div class="item-thumb">
										<a href="${pageContext.request.contextPath}/item?id=${disc.id}" target="_blank" class="item-lnk thumbnail"> 
											<img src="/images/${disc.photo}" alt="New fashion">
										</a>
									</div> --%>
									<div class="grow pic">
										<a href="${pageContext.request.contextPath}/item?id=${disc.id}" target="_blank"><img src="/images/${disc.photo}" alt="New fashion"></a>
									</div>
									<div class="item-meta">
										<div class="item-ttl">
											<a href="${pageContext.request.contextPath}/item?id=${disc.id}" target="_blank" title="${disc.description}">${disc.description}</a>
										</div>
										<div class="item-prc">
											<span>${disc.price}</span>
										</div>
										<div class="item-buy">
											<c:choose>
												<c:when test="${pageContext.request.userPrincipal.name != null}">
													<a href="#" onclick="addToBasket('${pageContext.request.contextPath}',${disc.id});">В корзину</a>
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