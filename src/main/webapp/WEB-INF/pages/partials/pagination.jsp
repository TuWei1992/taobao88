<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav>
  <ul class="pagination">
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/brands?page=${curr_page - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
    	<li <c:if test="${loop.index == curr_page}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/pageRedactor/brands?page=${loop.index}">${loop.index}</a></li>
    </c:forEach>
    <li>
      <a href="${pageContext.request.contextPath}/admin/pageRedactor/brands?page=${curr_page + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>