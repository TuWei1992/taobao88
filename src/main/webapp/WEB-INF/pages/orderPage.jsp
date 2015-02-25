<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href=<c:url value="/resources/css/bootstrap.css"/>
	rel="stylesheet" media="screen">
<link href=<c:url value="/resources/css/dopstyle.css"/> rel="stylesheet"
	media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<!-- or use local jquery -->
<script src=<c:url value="/resources//js/jqBootstrapValidation.js"/>></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="container">
		<div class="row otstup">
			<div class="col-md-9">
				<h2>Страница заказа</h2>
				<fieldset>
					<form class="form-horizontal" role="form"
						action=<c:url value="/privateOffice/FromOrder"/> method="post">

						<table rules="none" cellpadding="20px">
							<tr>
								<td>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="HREFGOODS">Сслыка <a
											href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										<div class="controls">
											<input type="text" id="HREFGOODS" name="HREFGOODS"
												placeholder="" required class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="NAMEGOODS">Название
											товара <a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="text" id="NAMEGOODS" name="NAMEGOODS"
												placeholder="" required class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="AMOUNTGOODS">Количество
											единиц продукции <a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="number" id="AMOUNTGOODS" max="1000"
												name="AMOUNTGOODS" placeholder="" required
												class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="PRICEGOODS">Стоимость
											единицы продукции($) <a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="text" pattern="\d*\.?\d+"
												data-validation-pattern-message="Пример: 10, 10.343"
												id="PRICEGOODS" name="PRICEGOODS" placeholder="" required
												class="input-medium">
											<p class="help-block" style="color: #EEE5DE;">Пример: 10,
												10.343</p>
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="WEIGHTGOODS">Вес
											единицы продукции(кг) <a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вопроса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="text" pattern="\d+\.?\d\d"
												data-validation-pattern-message="Пример: 1.00, 0.55, 12.73"
												id="WEIGHTGOODS" name="WEIGHTGOODS" placeholder="" required
												class="input-medium">
											<p class="help-block" style="color: #EEE5DE;">Пример:
												1.00, 0.55, 12.73</p>
										</div>

									</div>
								</td>
								<td>
									<div class="control-group" style="margin-top: -40px;">
										<!-- Username -->
										<label class="control-label" for="CHINAGOODS">Доставка
											по Китаю <a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="number" id="CHINAGOODS" name="CHINAGOODS"
												max="1000" placeholder="" class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="COLORGOODS">Цвет <a
											href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										<div class="controls">
											<input type="text" id="COLORGOODS" name="COLORGOODS"
												placeholder="" class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="SIZEGOODS">Размер <a
											href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a></label>
										<div class="controls">
											<input type="text" id="SIZEGOODS" name="SIZEGOODS"
												placeholder="" class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="COMPLEXGOODS">Комплектация
											<a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="text" id="COMPLEXGOODS" name="COMPLEXGOODS"
												placeholder="" class="input-medium">
										</div>
									</div>
									<div class="control-group">
										<!-- Username -->
										<label class="control-label" for="PHOTOGOODS">Фотоотчет
											<a href="#"
											title="Что это такое и как правильно вводить? Нажмите на знак вороса и узнаете.">?</a>
										</label>
										<div class="controls">
											<input type="checkbox" id="PHOTOGOODS" name="PHOTOGOODS"
												placeholder="" value="true" class="input-medium">
										</div>
									</div>
									<div class="form-group" style="margin-top: -20px;">
										<!-- Button -->
										<div class="controls">
											<button class="btn btn-success">Сделать заказ</button>
										</div>
									</div>
								</td>
							</tr>
						</table>

					</form>
				</fieldset>
			</div>
		</div>
	</div>


	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src=<c:url value="/resources/js/bootstrap.js"/>></script>

	<script src=<c:url value="/resources/js/jquery.js"/>></script>
	<!-- Checking for empty fields -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</body>
</html>