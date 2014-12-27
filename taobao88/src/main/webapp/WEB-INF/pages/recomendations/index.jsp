<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<c:forEach items="${recomendations}" var="rec">
		<div class="col-sm-4 col-md-2">
    		<div class="thumbnail" style="margin-top: 20px;">
      			<img src="/images/${rec.photo}" style="max-height: 100px;">
      			<div class="caption">
        			<h5>${rec.description}</h5>
        			<p><a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation/view?id=${rec.id}" class="btn btn-primary" role="button">Просмотр</a></p>
      			</div>
    		</div>
    	</div>
	</c:forEach>
</div>
<div class="row">
	<div class="col-sm-4 col-md-2 pull-right" style="padding-top: 20px;">
		<a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation/createRecomendation" type="button" role="button" class="btn btn-success create_rec_btn">Создать</a>
	</div>
</div>