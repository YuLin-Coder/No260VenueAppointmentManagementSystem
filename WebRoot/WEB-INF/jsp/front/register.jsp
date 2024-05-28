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
					<div class="heading"><h2>新用户？注册账号吧.</h2></div>
					<form name="form2" id="registerFormcustomer" method="post" action="">
						<div class="form-group">
							<input type="text" class="form-control" id="customer_username" name="username" placeholder="账号">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="customer_password" name="password" placeholder="密码">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="customer_name" name="customerName" placeholder="姓名">
						</div>
						<div class="form-group">
							<select name="sex" id="customer_sex" class="form-control">
				            	<option value="男">男</option>
				            	<option value="女">女</option>
				            </select>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="customer_address" name="address" placeholder="地址">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="customer_mobile" name="phone" placeholder="手机">
						</div>
						<%--
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" > I agree to your website.
						</div> --%>
						<button type="button" onclick="registerSave();" class="btn btn-1">注册</button>
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