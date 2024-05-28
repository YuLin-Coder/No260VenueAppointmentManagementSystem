<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<c:if test="${list!=null&&list.size()>0 }">
		<div class="container">
			<div class="row">
				
				<div class="product well">
					<c:forEach items="${list }" var="lists">
					<div class="col-md-3">
						<div class="image" >
							<img src="${ctx}/${lists.productPic1}" />
						</div>
					</div>
					
						<div class="col-md-12">
							<div class="caption">
								<div class="name"><h3><a href="product.html">${lists.productName }</a></h3></div>
								<div class="price">${lists.price }元</div>
								<label>数量: </label> <input class="form-inline quantity" type="text" value="${lists.num }" name="num"><a href="#" onclick="updateShowCar('${lists.ids}',this);" class="btn btn-2 ">更新</a>
								<hr>
								<a href="#" onclick="deleteOne('${lists.ids}');" class="btn btn-default pull-right">删除</a>
							</div>
						</div>
						
					</c:forEach>
					
					<div class="clear"></div>
					
				</div>	
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-8 ">
					<center><a href="${ctx }/front/all.html" class="btn btn-1">继续购物</a></center>
				</div>
			</div>
			<div class="row">
				<div class="pricedetails">
					<div class="col-md-4 col-md-offset-8">
						<table>
							<h6>价格详情</h6>
							<tr>
								<td>总计</td>
								<td>${total }</td>
							</tr>
							<%--
							<tr>
								<td>Discount</td>
								<td>-----</td>
							</tr> --%>
							<tr>
								<td>运费</td>
								<td>20</td>
							</tr>
							<tr style="border-top: 1px solid #333">
								<td><h5>总计</h5></td>
								<td>${total+20 }</td>
							</tr>
						</table>
						<center><a href="#" onclick="qrzf('${total+20 }');" class="btn btn-1">确认支付</a></center>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${list==null||list.size()==0 }">
		<div class="container">
			<div class="row">
				暂无物品
			</div>
		</div>
		</c:if>
	</div>	
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function deleteOne(id) {
		if (!confirm("确定要删除吗?")) {
			return false;
		}
		var params = {
			id : id
		};
		$.post("deleteOneShopCar.html", params, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			}
		});
	}
	
	function updateShowCar(id,src) {
		var num = $(src).prev().val();
		var params = {id : id,num:num};
		$.post("updateShopCar.html", params, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			}
		});
	}
	
	function qrzf(total) {
		var params = {total:total};
		$.post("pay.html", params, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('支付成功');
				window.location.href="${ctx}/front/myOrder.html";
			}
		});
	}

</script>
</html>
