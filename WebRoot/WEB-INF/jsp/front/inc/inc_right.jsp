<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script>
	var ctx = '${ctx}';
</script>
<%--
<div id="sidebar" class="col-md-4">
					<div class="widget wid-categories">
						<div class="heading"><h4>分类</h4></div>
						<div class="content">
							<ul>
								<c:forEach items="${typesList }" var="typesLists">
									<li><a href="${ctx }/front/all.html?typesId=${typesLists.id}">${typesLists.typesName }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div> --%>