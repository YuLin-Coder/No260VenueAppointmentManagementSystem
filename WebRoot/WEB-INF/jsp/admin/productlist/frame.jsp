<%@ page language="java" pageEncoding= "UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link href="${ctx }/resource/bootstrap.css" rel="stylesheet">
<html>
	<body class= "mobile">
		<form id= "form1" name="form1" method="post" action= "list.html" target= "query">
			<input type="hidden" value="${param.flag }" name="flag"/>
			<table class= "table">
				<tr class= "biao_ti" height="6%">
					<td colspan= "2">场地预定管理</td>
				</tr>
				<tr class= "seach_head" height="5%">
					<td style= "text-align: left;">
						
						&nbsp;&nbsp;
						<label>订单日期模糊查询</label>：
						<input type= "text" name= "orderDate" style= "width:100px;">
						&nbsp;&nbsp;
						<label>周模糊查询</label>：
						<input type= "text" name= "zc" style= "width:100px;">
						&nbsp;&nbsp;
						<label>时间段模糊查询</label>：
						<input type= "text" name= "sjd" style= "width:100px;">
						<br>
						&nbsp;&nbsp;
						<label>状态模糊查询</label>：
						<input type= "text" name= "status" style= "width:100px;">
						&nbsp;&nbsp;
						<label>订单编号模糊查询</label>：
						<input type= "text" name= "orderNum" style= "width:100px;">
						&nbsp;&nbsp;
						<label>场地名称模糊查询</label>：
						<input type= "text" name= "productName" style= "width:100px;">
						<button type="button" onClick="sch();"  class="btn">查询</button>
						<c:choose>
							<c:when test="${param.flag==1 }"></c:when>
							<c:when test="${param.flag==2 }"></c:when>
							<c:when test="${param.flag==3 }"></c:when>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose>
						<c:if test="${1==1 }"></c:if>
					</td>
				</tr>
			</table>
			<iframe id= "query" name= "query" frameborder= "0" height= "88%" width= "100%" scrolling= "yes"></iframe>
		</form>
		<script>
		sch();
		//查询方法
		function sch() {
			form1.action= "list.html";
			form1.submit();
		}
		//添加一个信息
		function add(){
			window.location.href='edit.html?flag=${param.flag}&method=edit';
		}
   </script>
	</body>
</html>
