<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<!--Top-->
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="sidebar" class="col-md-12">
					<div class="widget wid-categories">
						<div class="heading"><h4>通知</h4></div>
						<div class="content">
							<ul>
								<c:forEach items="${list }" var="listss">
									<li><a href="zxShow.html?id=${listss.id }">${listss.title }</a></li>
                                </c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function addShopCar(){
		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		var id = '${map.id}';
		var num = $("input[name='numbs']").val();
		$.post("addShopcar.html", {id:id,num:num}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('添加成功');
				window.location.reload();
			} else {
				
			}
		});
	}


</script>
</html>
