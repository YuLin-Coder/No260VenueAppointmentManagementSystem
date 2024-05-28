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
								<th>场地</th>
								<th>使用次数</th>
							</tr>
						</thead>
						<tbody>
								<!-- td class="text-center"> text-center 字段内容居中 text-right靠右边 text-left 靠左边 -->
								<!-- <td style="word-break:break-all;word-wrap:break-all;width: 300px;">这个是例子，内容过长，固定宽度，自动换行</td> -->
							<c:forEach items="${list }" var="row" varStatus="vs">
								<c:if test="${vs.index%2==0 }"><tr class="info"></c:if>
								<c:if test="${vs.index%2!=0 }"><tr></c:if>
								<th scope="row">${vs.index+1 }</th>
								<td>${row.productName}</td>
								<td>
									${row.counts }
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript">
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
