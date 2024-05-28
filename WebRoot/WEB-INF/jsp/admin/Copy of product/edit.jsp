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
			    <tr class="success">
			      <td>场地名称：</td>
					<td>
						<input type="text" id="productName" value="${map.productName }" name="productName"/>
					</td>
				</tr>
			    <tr class="">
			      <td>图片1：</td>
					<td>
						<input id="f_fileImg0" name="cmfile"
						onchange="triggerUploadImg0(this);" title="选择图片" type="file">
						<input class="text" type="hidden" name="productPic1" id="bbbbbImg0" value="${map.productPic1 }">
						<c:if test="${map.productPic1!=null }">
						<img style='width:100px;' src="${ctx }/${map.productPic1}" id="aaaaaImg0"/>
						</c:if>
					</td>
				</tr>
			    <tr class="success">
			      <td>图片2：</td>
					<td>
						<input id="f_fileImg1" name="cmfile"
						onchange="triggerUploadImg1(this);" title="选择图片" type="file">
						<input class="text" type="hidden" name="productPic2" id="bbbbbImg1" value="${map.productPic2 }">
						<c:if test="${map.productPic2!=null }">
						<img style='width:100px;' src="${ctx }/${map.productPic2}" id="aaaaaImg1"/>
						</c:if>
					</td>
				</tr>
			    <tr class="">
			      <td>图片3：</td>
					<td>
						<input id="f_fileImg2" name="cmfile"
						onchange="triggerUploadImg2(this);" title="选择图片" type="file">
						<input class="text" type="hidden" name="productPic3" id="bbbbbImg2" value="${map.productPic3 }">
						<c:if test="${map.productPic3!=null }">
						<img style='width:100px;' src="${ctx }/${map.productPic3}" id="aaaaaImg2"/>
						</c:if>
					</td>
				</tr>
			    <tr class="success">
			      <td>图片4：</td>
					<td>
						<input id="f_fileImg3" name="cmfile"
						onchange="triggerUploadImg3(this);" title="选择图片" type="file">
						<input class="text" type="hidden" name="productPic4" id="bbbbbImg3" value="${map.productPic4 }">
						<c:if test="${map.productPic4!=null }">
						<img style='width:100px;' src="${ctx }/${map.productPic4}" id="aaaaaImg3"/>
						</c:if>
					</td>
				</tr>
			    <tr class="">
			      <td>价格：</td>
					<td>
						<input id="price" type="text" value="${map.price }" name="price"/>
					</td>
				</tr>
			    <tr class="success">
			      <td>原价：</td>
					<td>
						<input id="oldPrice" type="text" value="${map.oldPrice }" name="oldPrice"/>
					</td>
				</tr>
			    <tr class="">
			      <td>内容：</td>
					<td>
						<textarea rows="5"  id="content" cols="50" name="content">${map.content }</textarea>
					</td>
				</tr>
			    <tr class="success">
			      <td>数量：</td>
					<td>
						<input id="nums" type="text" value="${map.nums }" name="nums"/>
					</td>
				</tr>
			    <tr class="">
			      <td>推荐星级：</td>
					<td>
						<input type="text" id="tjxj" value="${map.tjxj }" name="tjxj"/>
					</td>
				</tr>
			    <tr class="success">
			      <td>状态：</td>
					<td>
					</td>
				</tr>
			    <tr class="">
			      <td>分类：</td>
					<td>
						<select id="typesId" name="typesId">
							<c:forEach items="${typesList }" var="lists">
								<option value="${lists.id }" ${map.typesId==lists.id?'selected':'' }>${lists.typesName
									}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
			    <tr class="success">
			      <td>积分：</td>
					<td>
						<input type="text" id="jf" value="${map.jf }" name="jf"/>
					</td>
				</tr>
			    <tr class="">
			      <td>商家：</td>
					<td>
						<input type="text" id="userId" value="${map.userId }" name="userId"/>
					</td>
				</tr>
			    <tr class="success">
			      <td>标签：</td>
					<td>
						<input type="text" id="bqId" value="${map.bqId }" name="bqId"/>
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
	function triggerUploadImg0(src) {
		$.ajaxFileUpload({
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
				$("#bbbbbImg0").after("<img  id='aaaaaImg0' style='width:100px;' src='${ctx}/"+data.data.filepath+"' />");
			},
			error : function(data, status, e) {alert('文件上传失败');}
		});
	}
	function triggerUploadImg1(src) {
		$.ajaxFileUpload({
			url : '${ctx}/file/upload.json',
			secureuri : false,
			fileElementId : 'f_fileImg1',
			dataType : 'json',
			data : {
				fileloc : 'upload/',
				dir : 'temp'
			},
			success : function(data, status) {
				$("#bbbbbImg1").val(data.data.filepath);
				$("#aaaaaImg1").remove();
				$("#bbbbbImg1").after("<img  id='aaaaaImg1' style='width:100px;' src='${ctx}/"+data.data.filepath+"' />");
			},
			error : function(data, status, e) {alert('文件上传失败');}
		});
	}
	function triggerUploadImg2(src) {
		$.ajaxFileUpload({
			url : '${ctx}/file/upload.json',
			secureuri : false,
			fileElementId : 'f_fileImg2',
			dataType : 'json',
			data : {
				fileloc : 'upload/',
				dir : 'temp'
			},
			success : function(data, status) {
				$("#bbbbbImg2").val(data.data.filepath);
				$("#aaaaaImg2").remove();
				$("#bbbbbImg2").after("<img  id='aaaaaImg2' style='width:100px;' src='${ctx}/"+data.data.filepath+"' />");
			},
			error : function(data, status, e) {alert('文件上传失败');}
		});
	}
	function triggerUploadImg3(src) {
		$.ajaxFileUpload({
			url : '${ctx}/file/upload.json',
			secureuri : false,
			fileElementId : 'f_fileImg3',
			dataType : 'json',
			data : {
				fileloc : 'upload/',
				dir : 'temp'
			},
			success : function(data, status) {
				$("#bbbbbImg3").val(data.data.filepath);
				$("#aaaaaImg3").remove();
				$("#bbbbbImg3").after("<img  id='aaaaaImg3' style='width:100px;' src='${ctx}/"+data.data.filepath+"' />");
			},
			error : function(data, status, e) {alert('文件上传失败');}
		});
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
