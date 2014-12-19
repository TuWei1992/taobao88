<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="shop-ttl frst-ttl">
	<span>Бренды</span>
</div>
<div class="prepouse">
	<c:forEach items="${brands}" var="brandList">
		<div class="row" style="height: 100px;">
			<div class="goods-list">
				<c:forEach items="${brandList}" var="brand">
					<div id="${brand.brandId}">
						<div class="shop-item" style="width: 100px; height: 15px">
							<div>
								<div class="item-box" style="width: 100px;">
									<div class="item-thumb">
										<a href="http://translate.google.ru/translate?hl=ru&sl=zh-CN&tl=ru&u=http://s.taobao.com/search?q=${brand.brandName}"
										   class="item-lnk thumbnail" target="_blank"> 
										   <img src="/images/${brand.image.imageName}" alt="New fashion">
										</a>
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