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
						<div class="heading"><h4>我的帖子列表</h4>
							<c:if test="${param.dpc!=1&&customerBean!=null&&customerBean.isft!='否' }">
    <a class="btn btn-primary" href="wdxxEdit.html"  role="button">发布一条帖子</a></c:if>
						
						</div>
						<div class="content">
							<ul>
								<c:forEach items="${list }" var="listss">
									<li><a href="wdxxShow.html?id=${listss.id }">${listss.customerName }:${listss.title },日期：${listss.insertDate },[赞：${listss.zan }]</a>
									<c:if test="${param.dpc!=1 }">
							<a class="btn btn-danger" href="#" onclick="deleteOne('${listss.id}');" role="button">删除</a>
							<a class="btn btn-default" href="wdxxEdit.html?id=${listss.id }" role="button">修改</a>
							</c:if>
									
									</li>
                                </c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
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

	function deleteOne(id){
		if(!confirm("确定要删除吗?")){
			return false;
		}
		var params={id:id};
		$.post("wdxxDelete.html",params,function(
				result){
			result=eval("("+result+")");
			if(result.status=="true"||result.status==true){
				alert('成功');
			window.location.reload();
			}
		});
	}
</script>
</html>
