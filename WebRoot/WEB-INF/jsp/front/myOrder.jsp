<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="product well">
					<c:forEach items="${orderList }" var="lists">
					<div class="col-md-12">
						<div class="caption">
							<div class="info">	
								<ul>
									<li>场地名称: ${lists.productName }</li>
									<li>时间: ${lists.orderDate }</li>
									<li>周次: ${lists.zc }</li>
									<li>时间段: ${lists.sjd }</li>
									<li>订单编号: ${lists.orderNum }</li>
									<li>金额: ${lists.fee }</li>
									<li>状态: ${lists.status }</li>
									<li>下单日期: ${lists.insertDate }</li>
								</ul>
							</div>
							<hr>
							<c:if test="${lists.status=='预定中' }">
							<a href="#" onclick="deleteOne('${lists.id}');" class="btn btn-default pull-right">取消预定</a></c:if>
						</div>
					</div>
					</c:forEach>
					<div class="clear"></div>
				</div>	
			</div>
		</div>
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
		$.post("deleteOneOrder.html", params, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			}
		});
	}

</script>


</html>
