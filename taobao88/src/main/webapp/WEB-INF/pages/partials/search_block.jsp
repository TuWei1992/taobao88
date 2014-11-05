<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="search_block">
					<div class="left_s">Поиск</div>
					<div class="center_s">
						<div class="input">
							<div class="in">
								<input class="input_search form-control" name="q" type="text" value="Женская одежда" id="q" onfocus="if(this.value==&#39;Женская одежда&#39;) this.value=&#39;&#39;;" onblur="if(this.value==&#39;&#39;) this.value=&#39;Женская одежда&#39;;">
							</div>
						</div>
						<div class="check">
							<label><input name="google" type="checkbox" value="google" checked> Автоматический перевод Google</label>
							<label><input name="king" type="checkbox" value="king">Искать в магазинах с наивысшим рейтингом</label>
						</div>
					</div>
					<div class="right_s">
						<input id="translate" type="image" src="${pageContext.request.contextPath}/resources/img/button_search.gif">
					</div>
				</div>