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
					<div class="heading"><h2>登录</h2></div>
					<form name="form1" id="ff1" method="post" action="">
						<div class="form-group">
							<input type="text" class="form-control" id="login_username" name="username" placeholder="账号">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="login_password" name="password" placeholder="密码">
						</div>
						<button type="button" class="btn btn-1" onclick="loginCheck();" name="login" id="login">登录</button>
						<a href="find.html">忘记密码了 ?</a>
						
					</form>
				</div>
			</div>
		</div>
	</div>
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function loginCheck(){
		var username = $("#login_username").val();
		var password = $("#login_password").val();
		var type = $("#type").val();
		if(username==''||password==''){
			alert('用户名和密码必须填写');
			return false;
		}
		var aa="";
		var bb="";
		if(1==2){
			aa="${ctx}/adminLogin/save.html";
			bb="${ctx}/admin/index.html";
		}else{
			
			aa="${ctx}/front/save.html";
			bb="${ctx}/front/index.html";
		}
		$.ajax({
		      type: "POST",
		      async:false,  // 设置同步方式
		      cache:false,
		      url: aa,
				data:{username:username,password:password},
				success:function(result){
				result = eval("("+result+")");
				if(result.status=='true'||result.status==true){
					if(result.msg=='1'){
						alert('登录成功');
						window.location.href=bb;
					}else if(result.msg=='0'){
						alert('密码或用户名错误');
					}
				}
		      }
			});
	}
	
	
	function registerSave(){
		var username = $("#customer_username").val();
		var password = $("#customer_password").val();
		if(username==''||password==''){
			alert('用户名和密码必须填写');
			return false;
		}
		$.ajax({
		      type: "POST",
		      async:false,  // 设置同步方式
		      cache:false,
		      url: "${ctx}/front/registerSave.html",
				data:$("#registerFormcustomer").serializeArray(),
				success:function(result){
				result = eval("("+result+")");
				if(result.status=='true'||result.status==true){
						alert('注册成功');
						window.location.href="${ctx}/front/index.html";
				}
		      }
			});
	}
</script>
</html>