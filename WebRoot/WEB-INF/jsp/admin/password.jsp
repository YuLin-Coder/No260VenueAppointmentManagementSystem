<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.min.js"></script>
<link href="${ctx}/css/validate.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" href="${ctx }/resource/mine/four/mine.css" type="text/css" />
<html>
<head>
<title>信息内容功能</title>
</head>
<body>
	<form id="form1" name="form1" action="" method="post">
		<center>
			<table class="mobile" style="width: 95%;">
				<tr class="pageheader">
					<td colspan="2"><strong>信息处理</strong></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">旧密码： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="oldPassword"
						 name="oldPassword" /></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">新密码： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="newPassword"
						 name="newPassword" /></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">新密码确认： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input type="password" id="newPasswordConfirm"
						 name="newPasswordConfirm" /></td>
				</tr>
				
			</table>
		</center>
		<p align="center">
			<br> <input type="button" class="btn" value="保  存"
				onclick="save(this);" /> 
		</p>
	</form>
</body>
<script type="text/javascript">
	function save(src) {
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var newPasswordConfirm = $("#newPasswordConfirm").val();
		if(oldPassword==''){
			alert('旧密码不能为空');
			return false;
		}
		if(newPassword==''){
			alert('新密码不能为空');
			return false;
		}
		if(newPasswordConfirm==''){
			alert('确认密码不能为空');
			return false;
		}
		if(newPasswordConfirm!=newPassword){
			alert('两次密码输入不一致');
			return false;
		}
		$.post("changePassword.html", $("#form1").serializeArray(), function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('修改成功');
				window.location.reload();
			} else {
				alert('旧密码不对，请重新输入');
			}
		});
	}
</script>
</html>
