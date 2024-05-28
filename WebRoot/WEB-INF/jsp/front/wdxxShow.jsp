<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<body>
	<!--Top-->
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="product">
						<div class="clear"></div>
					</div>
					<div class="product-desc">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#description">${map.title }</a></li>
							<li><a href="#review">评论</a></li>
						</ul>
						<div class="tab-content">
							<div id="description" class="tab-pane fade in active">
							<div class="post-meta clearfix">
							<span class="date">${map.insertDate }</span> <span class="category">
							</span> <span class="comments">
							</span> <span class="like-count" onclick="zan('${map.id}');" style="cursor: pointer;">赞(${map.zan })</span>
						</div>
						<c:if test="${map.pic!=null&&map.pic!=''}">
					<img height="100" src="${ctx }/${map.pic}"
						class="attachment-std-thumbnail wp-post-image" alt="Living room">
					</c:if>
								<p>${map.content }</p>
							</div>
							<div id="review" class="tab-pane fade">
							  <div class="review-text">
								
								<c:forEach items="${list }" var="lists">
									<p>${lists.customerName }:${lists.content }(${lists.insertDate })</p>
								</c:forEach>
							  </div>
							  <div class="review-form">
							  <c:if test="${customerBean!=null&&customerBean.ispl!='否' }">
								<form name="form1" id="form1" method="post">
								<input type="hidden" name="wdxxId" value="${map.id }"/>
									<label>
									<span>评论内容:</span>
									<textarea name="content" id="content"></textarea>
									</label>
									<div class="text-right">
										<input class="btn btn-default" type="reset" name="reset" value="清空">
										<input class="btn btn-default" onclick="save(this);" type="button" name="Submit" value="发表评论">
									</div>
								</form>
								</c:if>
							  </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function addShopCar(){
		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		var id = '${map.id}';
		var num = $("input[name='numbs']").val();
		$.post("addShopcar.html", {id:id,num:num}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('添加成功');
				window.location.reload();
			} else {
				
			}
		});
	}

function zan(id) {
		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		$.post("zanSave.html", {id:id}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('点赞成功');
				window.location.reload();
			} else {
				alert('保存失败，请重试');
			}
		});
	}
	
	
	  	function save(src) {
  		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		$.post("pinglunSave.html", $("#form1").serializeArray(), function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				alert('成功');
				window.location.reload();
			} else {
				alert('保存失败，请重试');
			}
		});
	}
	
</script>
</html>
