<%@ page language="java" pageEncoding= "UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link href="${ctx }/resource/bootstrap.css" rel="stylesheet">
<html>
	<body class= "mobile">
		<form id= "form1" name="form1" method="post" action= "list.html" target= "query">
			<input type="hidden" value="${param.flag }" name="flag"/>
			<table class= "table">
				<tr class= "biao_ti" height="6%">
					<td colspan= "2">建议管理</td>
				</tr>
				<tr class= "seach_head" height="5%">
					<td style= "text-align: left;">
						
						<button type="button" onClick="sch();"  class="btn">刷新</button>
						&nbsp;&nbsp;
						<c:choose>
							<c:when test="${param.flag==1 }"></c:when>
							<c:when test="${param.flag==2 }"></c:when>
							<c:when test="${param.flag==3 }"></c:when>
							<c:otherwise>
							<button type="button" class="btn" onClick= "add();" >新增</button>
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
