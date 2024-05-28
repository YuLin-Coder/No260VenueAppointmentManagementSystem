<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<script type="text/javascript" src="${ctx }/resource/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resource/admin/js/ajaxfileupload.js"></script>
<link href="${ctx }/resource/bootstrap.css" rel="stylesheet">
<html>
	<head>
		<title>信息内容功能</title>
	</head>
	<body>
		<form id="form1" name="form1" action="" method="post">
			<input type="hidden" value="${param.flag }" name="flag"/>
			<input type="hidden" value="${map.id }" name="id" id="id" />
			<div style="padding: 2px 10px;">
			<table class="table">
			  <tbody>
			    <tr class="">
			      <td>订单详细：</td>
					<td>
						<textarea rows="5"  id="productDetail" cols="50" name="productDetail">${map.productDetail }</textarea>
					</td>
				</tr>
			    <tr class="success">
			      <td>订单总价格：</td>
					<td>
						<input type="text" id="allPrice" value="${map.allPrice }" name="allPrice"/>
					</td>
				</tr>
			    <tr class="">
			      <td>状态：</td>
					<td>
					</td>
				</tr>
			    <tr class="success">
			      <td>订单编号：</td>
					<td>
						<input type="text" id="orderNum" value="${map.orderNum }" name="orderNum"/>
					</td>
				</tr>
			    <tr class="">
			      <td>物流信息：</td>
					<td>
						<textarea rows="5"  id="pl" cols="50" name="pl">${map.pl }</textarea>
					</td>
				</tr>
			    <tr class="">
			      <td>：</td>
					<td>
						<input type="text" id="userId" value="${map.userId }" name="userId"/>
					</td>
				</tr>
			    <tr class="success">
			      <td>预定日期：</td>
					<td>
						<input  readonly="readonly" name="orderDate" id="orderDate" value="<fmt:formatDate value="${map.orderDate }" type="date" pattern="yyyy-MM-dd"/>" type="text" onClick="WdatePicker()"/>
					</td>
				</tr>
		<c:if test="${1==1 }"></c:if>
<%--<input name="newDate" id="newDate" value="${map.newDate }" type="text" onClick="WdatePicker()"/> --%>
				<%--
				<tr height="25">
					<td class="outDetail" style="width: 30%">图片： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2"><input id="f_file" name="cmfile"
						onchange="triggerUpload(this);" title="选择图片" type="file">
						<input class="text" type="hidden" name="img" value="${map.img }">
						<img id="imgUrlShow" style="width: 100px;" src="${ctx}/${map.img }" />
					</td>
				</tr>
				--%>
				<%--
				<tr height="25">
					<td class="outDetail" style="width: 30%">是否特价： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2">
					<select name="isSale">
							<option value="是" ${map.isSale=='是'?'selected':'' }>是</option>
							<option value="否" ${map.isSale=='否'?'selected':'' }>否</option>
					</select>
				</tr>
				 --%>
				<%--
				<tr height="25">					<td class="outDetail" style="width: 30%">图书分类的外键： <label
						style="font-weight: bold; color: red"> * </label></td>
					<td class="outDetail2">					
<select name="typeId">
							<c:forEach items="${typeList }" var="lists">
								<option value="${lists.id }" ${map.typeId==lists.id?'selected':'' }>${lists.typeName
									}</option>
							</c:forEach>
					</select>
				</tr>
				 --%>
			  </tbody>
			</table>
			</div>
			<p align="center">
				<c:if test="${param.method!='show' }">
					<input type="button" class="btn" value="保  存" onclick="save(this);" />
				</c:if>
				<c:if test="${param.method=='modify'||param.method=='show' }">
				<input type="button" class="btn" value="返回"
					onclick="window.location.href='list.html?flag=${param.flag}'" />
				</c:if>
				<c:if test="${param.method=='edit' }">
				<input type="button" class="btn" value="返回"
					onclick="window.history.go(-1)" />
				</c:if>
			</p>
		</form>
	</body>
	<script type="text/javascript">
	//编辑保存
	function save(src) {
		$.post("editSave.html",$("#form1").serializeArray(),
		       function(result){
		        	result = eval("("+result+")");
		    		if(result.status == "true" || result.status == true){
		    			alert('成功');
						if('${param.method}'=='edit'){
							window.location.href='frame.html?flag=1';
						}else if('${param.method}'=='modify'){
							window.location.href='list.html?flag=${param.flag}'
						}else if('${param.method}'=='show'){
							window.location.href='list.html?flag=${param.flag}'
						}
		    		}else{
		    			alert('保存失败，请重试');
		    		}
		        }
			);
	}
	function triggerUpload(src) {
		$.ajaxFileUpload({
			url : '${ctx}/file/upload.json',
			secureuri : false,
			fileElementId : 'f_file',
			dataType : 'json',
			data : {
				fileloc : 'upload/',
				dir : 'temp'
			},
			success : function(data, status) {
				$("input[name='img']").val(data.data.filepath);
				$("#imgUrlShow").attr("src",
						"${ctx}/" + data.data.filepath + "");
			},
			error : function(data, status, e) {alert('文件上传失败');}
		});
	}
</script>
</html>
