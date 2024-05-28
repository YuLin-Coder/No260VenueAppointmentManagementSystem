<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
var ctx='${ctx}';
var ctx_fck='${ctx}';
</script>
<script src="${ctx}/resource/admin/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/resource/admin/js/common.js" type="text/javascript"></script>
<script src="${ctx}/resource/check.js" type="text/javascript"></script>
<link href="${ctx}/resource/add1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function saveAnd(){
if(window.opener.parent.parent != window.opener.parent){
		try{
			if(window.opener.location.href.indexOf('d-16544-p') == -1) {
				window.opener.parent.document.forms[0].submit();
					
			} else {
				window.opener.location.reload();
			}
    	} catch(e){
    		window.opener.document.forms[0].submit();
    	}
	} else {
		try {
		
			window.opener.document.forms[0].submit();
		} catch(e){
			window.opener.location.reload();
		}			
	}
	window.close();
}
</script>
