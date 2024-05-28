<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<h2>您预定的场地：${map.productName }</h2>
				<h2>周期：${map.zc }</h2>
				<h2>日期：${map.orderDate }</h2>
				<h2>时间段：${map.sjd }</h2>
				<h2>费用：${map.fee }</h2>
				<h2><img src="${ctx }/resource/222.jpg"></h2>
				<a href="#" onclick="zhifuSave();" class="btn btn-2 ">支付</a>
			</div>
		</div>
	</div>	
</body>
<script type="text/javascript">
	function zhifuSave(){
		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		var id = '${map.id}';
		$.post("zhifuSave.html", {id:id}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('支付成功');
				window.location.href='${ctx}/front/myOrder.html';
			} else {
				
			}
		});
	}

function zan(id) {
		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		$.post("zanSave.html", {id:id}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('点赞成功');
				window.location.reload();
			} else {
				alert('保存失败，请重试');
			}
		});
	}
	
	
	  	function save(src) {
  		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		$.post("pinglunSave.html", $("#form1").serializeArray(), function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			} else {
				alert('保存失败，请重试');
			}
		});
	}
	
</script>
</html>
