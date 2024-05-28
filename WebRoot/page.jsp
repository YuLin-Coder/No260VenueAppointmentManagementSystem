<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<c:choose>
	<c:when test="${fn:contains(page.href, '?')}">
		<c:set var="pageHref" value="${page.href }&" />
	</c:when>
	<c:otherwise>
		<c:set var="pageHref" value="${page.href }?" />
	</c:otherwise>
</c:choose>
    <div class="text-center">
     <ul class="pagination">
     	<li class="page-item"><a class="page-link" href="${pageHref }offset=1">第一页</a></li>
	    <li class="page-item"><a class="page-link" href="${pageHref }offset=${page.prePage}">上一页</a></li>
	    <c:forEach var="x" begin="${page.startPage}" end="${page.endPage}" step="1">
			<c:if test="${x==page.currentPage}">
				 <li class="page-item active"><a class="page-link" title="${x}" href="javascript:void(0);">${x}</a></li>
			</c:if>
			<c:if test="${x!=page.currentPage}">
				<c:if test="${x>(page.currentPage-3)&& x<(page.currentPage+3) }">
					<li class="page-item"><a class="page-link" href="${pageHref }offset=${x}">${x}</a></li>
				</c:if>
			</c:if>
		</c:forEach>
	    <li class="page-item"><a class="page-link" href="${pageHref }offset=${page.nextPage}">下一页</a></li>
	    <li class="page-item"><a class="page-link" href="${pageHref }offset=${page.totalPage}">最后一页</a></li>
	  </ul>
    </div>