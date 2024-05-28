<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script>
	var ctx = '${ctx}';
</script>
<div class="row">
				<div class="col-lg-12">
					<div class="heading"><h2>大家都在看</h2></div>
					<div class="products">
						<c:forEach items="${list2 }" var="lists">
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
							<div class="product">
								<div class="image"><a href="${ctx }/front/detail.html?id=${lists.id}"><img style="height: 200px;"  src="${ctx}/${lists.productPic1}" /></a></div>
								<%--<div class="buttons">
									<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
									<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
									<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
								</div> --%>
								<div class="caption">
									<div class="name"><h3><a href="${ctx }/front/detail.html?id=${lists.id}">${lists.productName}</a></h3></div>
									<div class="name"><h3>卖家：${lists.name }</h3></div>
									<div class="price">${lists.price }元<span>${lists.oldPrice }元</span></div>
												<div class="rating">
												<c:if test="${lists.tjxj=='一星' }">
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												</c:if>
												<c:if test="${lists.tjxj=='二星' }">
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												</c:if>
												<c:if test="${lists.tjxj=='三星' }">
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												</c:if>
												<c:if test="${lists.tjxj=='四星' }">
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star-empty"></span>
												</c:if>
												<c:if test="${lists.tjxj=='五星' }">
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												<span class="glyphicon glyphicon-star"></span>
												</c:if>
												
												</div>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>