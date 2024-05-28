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
					<div class="heading"><h2>个人中心</h2></div>
					<form name="form2" id="registerFormcustomer" method="post" action="">
					<input type="hidden" value="${customer.id }" name="id"/>
						<div class="form-group">
						密码：
							<input type="password" class="form-control" value="${customer.password }" id="customer_password" name="password" placeholder="密码">
						</div>
						姓名：
						<div class="form-group">
							<input type="text" class="form-control" value="${customer.customerName }" id="customer_name" name="customerName" placeholder="姓名">
						</div>
						性别：
						<div class="form-group">
							<select name="sex" id="customer_sex" class="form-control">
				            	<option value="男" ${customer.sex=='男'?'selected':'' }>男</option>
            					<option value="女" ${customer.sex=='女'?'selected':'' }>女</option>
				            </select>
						</div>
						地址：
						<div class="form-group">
							<input type="text" value="${customer.address }" class="form-control" id="customer_address" name="address" placeholder="地址">
						</div>
						手机：
						<div class="form-group">
							<input type="text" value="${customer.phone }" class="form-control" id="customer_mobile" name="phone" placeholder="手机">
						</div>
						<%--
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" > I agree to your website.
						</div> --%>
						<button type="button" onclick="mineSave();" class="btn btn-1">保存</button>
					</form>
					
				</div>
			</div>
		</div>
	</div>
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
function mineSave(){
		$.ajax({
		      type: "POST",
		      async:false,  // 设置同步方式
		      cache:false,
		      url: "${ctx}/front/mineSave.html",
				data:$("#registerFormcustomer").serializeArray(),
				success:function(result){
				result = eval("("+result+")");
				if(result.status=='true'||result.status==true){
						alert('修改成功');
						window.location.reload();
				}
		      }
			});
	}
	
	
</script>
</html>