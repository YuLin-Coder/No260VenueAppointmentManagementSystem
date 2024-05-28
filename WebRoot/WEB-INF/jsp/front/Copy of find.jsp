<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="inc/inc_css.jsp" %>
<body>
<!-- Wrapper -->
<div id="wrapper">
	<!-- Top bar -->
<%@include file="inc/inc_menu.jsp" %>
	<div class="clear"></div>
	<!-- End of Topbar -->
	<!-- Introtext -->
	<%@include file="inc/inc_right.jsp" %>
	<div id="introtext-contact">
		<h1>欢迎您使用找回密码</h1>
		<div class="clear"></div>
	</div>
	<!-- End of introtext -->
	<!-- Form -->
	<form class="form-signin" id="login_form">
	<div id="contactform">
		<div id="main">
			<h1>账号: </h1><p><input type="text" name="username" id="login_username" placeholder="账号" class="name" /></p>
			<h1>手机: </h1><p><input type="text" name="password" id="login_phone" placeholder="手机号码" class="name" /></p>
			<p><input type="button" name="submit" class="button" onclick="login();" value="找回" /></p>
			<p><label>
            <font color="red">模拟手机找回密码，不是真的</font>
          </label></p>
		</div>
	</div>
	</form>
	<!-- End of form-->
	<div class="clear"></div>
	<!-- Footer-->
	<%@include file="inc/inc_foot.jsp" %>
	<!-- End of footer-->
</div>
<!-- End of wrapper -->
</body>
<script type="text/javascript">
function login() {
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
