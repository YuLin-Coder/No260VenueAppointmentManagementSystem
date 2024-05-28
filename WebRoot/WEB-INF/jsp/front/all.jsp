<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="main-content" class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="products">
							
								<c:forEach items="${list }" var="lists">
									<div class="col-lg-4 col-md-4 col-xs-12">
										<div class="product">
											<div class="image"><a href="${ctx }/front/detail.html?id=${lists.id}"><img style="height: 200px;"  src="${ctx}/${lists.productPic1}" /></a></div>
											<%--<div class="buttons">
												<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
												<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
												<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
											</div> --%>
											<div class="caption">
												<div class="name"><h3><a href="${ctx }/front/detail.html?id=${lists.id}">${lists.productName }</a></h3></div>
												<div class="price">${lists.price }元</div>
											</div>
										</div>
									</div>
								</c:forEach>
							
								
							</div>
						</div>
					</div>
					<%--
					<div class="row text-center">
						<ul class="pagination">
						  <li class="active"><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						</ul>
					</div> --%>
				</div>
				<%@include file="inc/inc_right.jsp" %>
			</div>
		</div>
	</div>	
<%@include file="inc/inc_foot.jsp" %>
</body>
</html>
