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
								<th>订单日期</th>
								<th>周</th>
								<th>时间段</th>
								<th>状态</th>
								<th>用户</th>
								<th>下单日期</th>
								<th>价格</th>
								<th>订单编号</th>
								<th>场地名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
								<!-- td class="text-center"> text-center 字段内容居中 text-right靠右边 text-left 靠左边 -->
								<!-- <td style="word-break:break-all;word-wrap:break-all;width: 300px;">这个是例子，内容过长，固定宽度，自动换行</td> -->
							<c:forEach items="${list }" var="row" varStatus="vs">
								<c:if test="${vs.index%2==0 }"><tr class="info"></c:if>
								<c:if test="${vs.index%2!=0 }"><tr></c:if>
								<th scope="row">${vs.index+1 }</th>
								<td>${row.orderDate }</td>
								<td>${row.zc }</td>
								<td>${row.sjd }</td>
								<td>${row.status }</td>
								<td>${row.customerId }</td>
								<td><fmt:formatDate value="${row.insertDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${row.fee }</td>
								<td>${row.orderNum }</td>
								<td>${row.productName }</td>
								<td>
						<c:choose>
							<c:when test="${param.flag==1 }"></c:when>
							<c:when test="${param.flag==2 }"></c:when>
							<c:when test="${param.flag==3 }"></c:when>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose>
									<c:if test="${row.status=='预定中' }">									
									<button type="button" onclick="return updateColumnstatus('已退订','${row.id}');" class="btn btn-warning">退订</button>
									<button type="button" onclick="return updateColumnstatus('完成','${row.id}');" class="btn btn-warning">完成</button>
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
	//修改
	function modifyOne(id,method){
			window.location.href='edit.html?id='+id+'&flag=${param.flag}&method='+method;
	}
</script>
</html>
