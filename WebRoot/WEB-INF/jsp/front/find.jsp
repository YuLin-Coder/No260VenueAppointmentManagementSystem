<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>

<body>
	<!--Top-->
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading"><h2>找回密码</h2></div>
					<form name="form1" id="ff1" method="post" action="">
						<div class="form-group">
							<input type="text" class="form-control" id="login_username" placeholder="账号" class="name">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="login_phone" placeholder="手机号码" class="name">
						</div>
						<button type="button" class="btn btn-1" onclick="zh();" name="login" id="login">找回</button>
						<font color="red">模拟手机找回密码，不是真的</font>
						
					</form>
				</div>
			</div>
		</div>
	</div>
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function zh() {
		var username = $("#login_username").val();
		var phone = $("#login_phone").val();
		var type = $("#type").val();
		if (username == '' || phone == '') {
			alert('用户名和手机必须填写');
			return false;
		}
		var aa = "";
		var bb = "";
		aa = "${ctx}/front/findSave.html";
		bb = "${ctx}/front/main.html";
		$.ajax({
			type : "POST",
			async : false, // 设置同步方式
			cache : false,
			url : aa,
			data : {username:username,phone:phone},
			success : function(result) {
				result = eval("(" + result + ")");
				if (result.status == 'true' || result.status == true) {
					var sex=prompt("请输入密码",'您的新密码');
					loginConfirm(sex);
				}else{
					alert('手机或用户名错误');
				}
			}
		});
	}
	
function loginConfirm(password) {
		var username = $("#login_username").val();
		var phone = $("#login_phone").val();
		var type = $("#type").val();
		var aa = "";
		var bb = "";
		aa = "${ctx}/front/findSaveConfirm.html";
		bb = "${ctx}/front/main.html";
		$.ajax({
			type : "POST",
			async : false, // 设置同步方式
			cache : false,
			url : aa,
			data : {username:username,phone:phone,password:password},
			success : function(result) {
				result = eval("(" + result + ")");
				if (result.status == 'true' || result.status == true) {
					alert('修改成功');
					window.parent.location.href="index.html";
				}else{
					alert('修改失败');
				}
			}
		});
	}	
</script>
</html>