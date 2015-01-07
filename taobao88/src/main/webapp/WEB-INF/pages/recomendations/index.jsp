<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav>
  <ul class="pagination">
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${curr_page - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
    	<li <c:if test="${loop.index == curr_page}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${loop.index}">${loop.index}</a></li>
    </c:forEach>
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${curr_page + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<div class="row">
	<c:forEach items="${recomendations}" var="rec">
		<div class="col-sm-4 col-md-2">
    		<div class="thumbnail" style="margin-top: 20px; height: 200px;">
      			<a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation/view?id=${rec.id}&page=${curr_page}"><img src="/images/${rec.photo}" style="max-height: 100px;"></a>
      			<div class="caption">
        			<h5>${rec.description}</h5>
        			<p></p>
      			</div>
    		</div>
    	</div>
	</c:forEach>
</div>
<div class="row">
	<div class="col-sm-4 col-md-2 pull-right" style="padding-top: 20px;">
		<a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation/createRecomendation?page=${curr_page}" type="button" role="button" class="btn btn-success create_rec_btn">Создать</a>
	</div>
</div>

<nav>
  <ul class="pagination">
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${curr_page - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
    	<li <c:if test="${loop.index == curr_page}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${loop.index}">${loop.index}</a></li>
    </c:forEach>
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/recomendation?page=${curr_page + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>