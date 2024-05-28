<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@include file="/taglibs.jsp"%>
<link href="${ctx }/resource/bootstrap.css" rel="stylesheet">
<html>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="bs-example" data-example-id="contextual-table">
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>场地名称</th>
								<th>图片1</th>
								<th>图片2</th>
								<th>图片3</th>
								<th>图片4</th>
								<th>价格</th>
								<th>原价</th>
								<th>内容</th>
								<th>数量</th>
								<th>推荐星级</th>
								<th>状态</th>
								<th>分类</th>
								<th>积分</th>
								<th>商家</th>
								<th>标签</th>
								<th>操作</th>
								<th>状态操作</th>
							</tr>
						</thead>
						<tbody>
								<!-- td class="text-center"> text-center 字段内容居中 text-right靠右边 text-left 靠左边 -->
								<!-- <td style="word-break:break-all;word-wrap:break-all;width: 300px;">这个是例子，内容过长，固定宽度，自动换行</td> -->
							<c:forEach items="${list }" var="row" varStatus="vs">
								<c:if test="${vs.index%2==0 }"><tr class="info"></c:if>
								<c:if test="${vs.index%2!=0 }"><tr></c:if>
								<th scope="row">${vs.index+1 }</th>
								<td>${row.productName }</td>
								<td><img style="width:100px;" src="${ctx}/${row.productPic1}"/></td>
								<td><img style="width:100px;" src="${ctx}/${row.productPic2}"/></td>
								<td><img style="width:100px;" src="${ctx}/${row.productPic3}"/></td>
								<td><img style="width:100px;" src="${ctx}/${row.productPic4}"/></td>
								<td>${row.price }</td>
								<td>${row.oldPrice }</td>
								<td>${row.content }</td>
								<td>${row.nums }</td>
								<td>${row.tjxj }</td>
								<td>${row.status }</td>
								<td>${row.typesName }</td>
								<td>${row.jf }</td>
								<td>${row.userId }</td>
								<td>${row.bqId }</td>
								<td>
						<c:choose>
							<c:when test="${param.flag==1 }"></c:when>
							<c:when test="${param.flag==2 }"></c:when>
							<c:when test="${param.flag==3 }"></c:when>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose>
									<button type="button" onclick="return modifyOne('${row.id}','show');" class="btn btn-info">查看</button>
									<button type="button" onclick="return modifyOne('${row.id}','modify');" class="btn btn-warning">修改</button>
									<button type="button" onclick="return deleteOne('${row.id}');" class="btn btn-danger">删除</button>
								</td>
								<td>
								<c:if test="${row.status=='有效' }">									<button type="button" onclick="return updateColumnstatus('无效','${row.id}');" class="btn btn-warning">无效</button>
								</c:if>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript">
	//更新字段信息
	function updateColumnstatus(status,id){
		if(!confirm("确定要更新为"+status+"吗?")){
			return false;
		}
		//var aaa = prompt("请输入操作意见或说明：","");//弹出输入框接收文字，这里给个例子
		var params={id:id,status:status};
		$.post("updateColumnstatus.html",params,function(
				result){
			result=eval("("+result+")");
			if(result.status=="true"||result.status==true){
				alert('成功');
			window.parent.form1.submit();
			}
		});
	}
	//更新字段信息
	function updateColumntypesId(typesId,id){
		if(!confirm("确定要更新为"+typesId+"吗?")){
			return false;
		}
		//var aaa = prompt("请输入操作意见或说明：","");//弹出输入框接收文字，这里给个例子
		var params={id:id,typesId:typesId};
		$.post("updateColumntypesId.html",params,function(
				result){
			result=eval("("+result+")");
			if(result.status=="true"||result.status==true){
				alert('成功');
			window.parent.form1.submit();
			}
		});
	}
	//删除
	function deleteOne(id){
		if(!confirm("确定要删除吗?")){
			return false;
		}
  //var aaa = prompt("请输入操作意见或说明：","");
		var params={id:id};
		$.post("editDelete.html",params,function(
				result){
			result=eval("("+result+")");
			if(result.status=="true"||result.status==true){
				alert('成功');
			window.parent.form1.submit();
			}
		});
	}
	//修改
	function modifyOne(id,method){
			window.location.href='edit.html?id='+id+'&flag=${param.flag}&method='+method;
	}
</script>
</html>
