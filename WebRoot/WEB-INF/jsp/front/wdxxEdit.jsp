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
					<div class="heading"><h2>编辑帖子</h2></div>
					
					
					
					
					<form id="form1">
    
    <input type="hidden" value="${param.flag }" name="flag" /> <input
			type="hidden" value="${map.id }" name="id" id="id" />
    
    <table class="mobile" style="width: 95%;">
				<tr class="pageheader">
					<td colspan="2"><strong>信息处理</strong></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">标题： <label
						style="font-weight: bold; color: red"> </label></td>
					<td class="outDetail2"><input  class="form-control" type="text" id="title"
						value="${map.title }" name="title" /></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">图片： <label
						style="font-weight: bold; color: red"> </label></td>
					<td class="outDetail2"><input  class="form-control" id="f_fileImg0" name="cmfile"
						onchange="triggerUploadImg0(this);" title="选择图片" type="file">
						<input class="text"  type="hidden" name="pic" id="bbbbbImg0"
						value="${map.pic }"> <c:if test="${map.pic!=null }">
							<img style='width:100px;' src="${ctx }/${map.pic}" id="aaaaaImg0" />
						</c:if></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">内容： <label
						style="font-weight: bold; color: red"> </label></td>
					<td class="outDetail2"><textarea  class="form-control" rows="5" id="content"
							cols="50" name="content">${map.content }</textarea></td>
				</tr>
				<tr height="25">
					<td class="outDetail" style="width: 30%">是否游客可见： <label
						style="font-weight: bold; color: red"> </label></td>
					<td class="outDetail2">
						<select name="nologin" class="form-control" >
							<option ${map.nologin=='是'?'selected':'' } value="是">是</option>
							<option ${map.nologin=='否'?'selected':'' } value="否">否</option>
						</select>
					</td>
				</tr>
				<input type="hidden" value="1" name="bkId"/>
				<%--
				<tr height="25">
					<td class="outDetail" style="width: 30%">版块： <label
						style="font-weight: bold; color: red"> </label></td>
					<td class="outDetail2" class="form-control" >
						<select name="bkId">
							<c:forEach items="${rightBkList }" var="lists">
								<option ${map.bkId==lists.id?'selected':'' } value="${lists.id }">${lists.bkName }</option>
							</c:forEach>
						</select>
					</td>
				</tr> --%>
			</table>
    <input type="button" class="btn btn-default" value="保  存"
				onclick="save(this);" /> <input type="button" 
				value="返回" class="btn btn-default" onclick="window.history.go(-1)" />
    
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
	
	
	function save(src) {
		$.post("wdxxEditSave.html", $("#form1").serializeArray(), function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			} else {
				alert('保存失败，请重试');
			}
		});
	}
	function triggerUploadImg0(src) {
		$
				.ajaxFileUpload({
					url : '${ctx}/file/upload.json',
					secureuri : false,
					fileElementId : 'f_fileImg0',
					dataType : 'json',
					data : {
						fileloc : 'upload/',
						dir : 'temp'
					},
					success : function(data, status) {
						$("#bbbbbImg0").val(data.data.filepath);
						$("#aaaaaImg0").remove();
						$("#bbbbbImg0")
								.after(
										"<img  id='aaaaaImg0' style='width:100px;' src='${ctx}/"+data.data.filepath+"' />");
					},
					error : function(data, status, e) {
						alert('文件上传失败');
					}
				});
	}
</script>
</html>