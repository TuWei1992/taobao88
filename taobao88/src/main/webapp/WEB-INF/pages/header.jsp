<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header">
	<div class="header_cont width">
		<div class="logo_line"></div>
		<div class="logo">
			<a href="/"><img src="resources/img/logo.gif" alt="" width="374"
				height="60"></a>
		</div>
		<div class="top_menu">
			<ul>
				<li><a href=<c:url value="/"/>>Главная</a></li>
				<li><a href="about.html">О нас</a></li>
				<li><a href="usloviya_raboty.html">Условия работы</a></li>
				<li><a href="oformlenie_zakaza.html">Оформление заказа</a></li>
				<li><a href="partnerskaya_programma.html">Партнерская
						программа</a></li>
				<li><a href="http://forum.taobao88.org/">Форум поддержки</a></li>
			</ul>
		</div>
		<i class="text_top">Сделать и получить свой заказ просто, как <span
			class="orange">РАЗ, ДВА, ТРИ, ЧЕТЫРЕ!</span></i>
		<div class="count_top">
			<ul>
				<li><i>1</i><a href="http://taobao88.org/vybor_tovara"
					target="_blank">Выбор товара</a></li>
				<li><i>2</i> <a href="http://taobao88.org/zapolnenie_blanka"
					target="_blank">Заполнение бланка заказа</a></li>
				<li><i>3</i> <a href="http://taobao88.org/oplata"
					target="_blank">Оплата заказа</a></li>
				<li><i>4</i><a href="http://taobao88.org/poluchenie"
					target="_blank">Получение заказа</a></li>
			</ul>
		</div>
		<div class="search_block">
			<form method="post" target="_blank" action="frame" id="search">
				<div class="left_s">Поиск</div>
				<div class="center_s">
					<div class="input">
						<div class="in">
							<input class="input_search" name="q" type="text"
								value="Женская одежда" id="q"
								onfocus="if(this.value==&#39;Женская одежда&#39;) this.value=&#39;&#39;;"
								onblur="if(this.value==&#39;&#39;) this.value=&#39;Женская одежда&#39;;">
						</div>
					</div>
					<div class="check">
						<label> <input name="google" type="checkbox" value="1"
							checked="true">Автоматический перевод Google
						</label> <label> <input name="king" type="checkbox" value="1">Искать
							в магазинах с наивысшим рейтингом
						</label>
					</div>
				</div>
				<div class="right_s">
					<input name="" type="image" src="resources/img/button_search.gif"
						onclick="">
				</div>
			</form>
		</div>
	</div>
</div>
<div class="width">
	<div class="un_menu">
		<ul class="ul_menu">
			<li><a href="privateOffice"><i class="icon office"></i>Мой
					аккаунт</a></li>
			<li><a href="privateOffice/accountSettings"><i
					class="icon inst"></i>Мой настройки</a></li>
			<li><a href="privateOffice/toPackages"><i class="icon bag"></i>Мои
					посылки</a></li>
			<li><a href="#"><i class="icon mail"></i>Мои сообщения</a></li>
			<li><a href="#"><i class="icon count"></i>Мой счёт</a></li>
			<li><a href="#"><i class="icon wish"></i>Мой лист желаний</a></li>
		</ul>
	</div>
</div>

