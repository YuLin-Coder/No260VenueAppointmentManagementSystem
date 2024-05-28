<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
var ctx='${ctx}';
</script>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/html5.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/respond.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctx}/resource/mine/four/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/mine/four/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript" src="${ctx }/resource/My97DatePicker/WdatePicker.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>场馆预约系统</title>
</head>
<body>
<div class="header">场馆预约系统</div>
<div class="loginWraper">


  <div id="loginform" class="loginBox">
    <form action="index.html" method="post" id="login_form">
      <div class="formRow user">
        <input type="text" id="login_username" name="username" placeholder="用户名" class="input_text input-big">
      </div>
      <div class="formRow password">
      	<input type="password" name="password" id="login_password" placeholder="密码" class="input_text input-big">
      </div>
      <div class="formRow yzm">
      	<select class="input_text input-big" name="type" id="type">
            	<option value="超级管理员">超级管理员</option>
            </select>
      </div>
      <%--
      <div class="formRow yzm">
        <input class="input_text input-big" type="text" placeholder="验证码" onBlur="if(this.value==''){this.value='验证码:'}" onClick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
        <img src="images/VerifyCode.aspx.png"> <a id="kanbuq" href="javascript:;">看不清，换一张</a> 
      </div> --%>
      <div class="formRow">
        <input id="submit" onclick="loginCheck();" type="button" class="btn radius btn-success btn-big" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
			<%--
        <input onclick="showRegistercustomer();" type="button" class="btn radius btn-success btn-big" value="&nbsp;客户注册&nbsp;">
			 --%>
      </div>
    </form>
	<form action="" method="post" id="registerFormcustomer" style="display: none;">
      <div class="formRow user">
			<input type="text" id="customer_username" name="username" class="input_text input-big" placeholder="用户名" />
		</div>
      <div class="formRow password">
			<input type="password" id="customer_password" name="password" class="input_text input-big" placeholder="密码"  />
		</div>
      <div class="formRow user">
<input type="text" class="input_text input-big" placeholder="姓名" id="customerName" name="customerName"/>
		</div>
      <div class="formRow user">
<select class="input_text input-big" id="sex" name="sex">
								<option value="男">男</option>
								<option value="女">女</option>
					</select>
		</div>
      <div class="formRow user">
<input type="text" class="input_text input-big" placeholder="地址" id="address" name="address"/>
		</div>
      <div class="formRow user">
<input type="text" class="input_text input-big" placeholder="手机" id="phone" name="phone"/>
		</div>
      <div class="formRow user">
<input class="input_text input-big" id="account" type="text"  name="account"/>
		</div>
      <div class="formRow user">
<input class="input_text input-big" id="jf" type="text"  name="jf"/>
		</div>
      <div class="formRow">
        <input id="submit" onclick="registerSavecustomer();" type="button" class="btn radius btn-success btn-big" value="点击注册">
        <input onclick="showLogin();" type="button" class="btn radius btn-success btn-big register-tis" value="我有账号，我要登录">
      </div>
	</form>
  </div>
  
  
</div>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/H-ui.js"></script>
</body>
<script type="text/javascript">
	function showRegistercustomer(){
		$("#login_form").hide();
		$("#registerFormcustomer").show();
	}
	function showLogin(){
		$("#login_form").show();
		$("#registerFormcustomer").hide();
	}	
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
		aa="${ctx}/adminLogin/save.html";
			bb="${ctx}/admin/index.html";
		$.ajax({
		      type: "POST",
		      async:false,  // 设置同步方式
		      cache:false,
		      url: aa,
				data:$("#login_form").serializeArray(),
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
	function registerSavecustomer(){
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
		      url: "${ctx}/customerLogin/registerSave.html",
				data:$("#registerFormcustomer").serializeArray(),
				success:function(result){
				result = eval("("+result+")");
				if(result.status=='true'||result.status==true){
						alert('注册成功');
						window.location.href="${ctx}/customer/index.html";
				}
		      }
			});
	}
</script>
</html>
