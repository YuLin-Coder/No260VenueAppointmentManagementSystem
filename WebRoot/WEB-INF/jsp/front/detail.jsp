<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="inc/inc_css.jsp" %>
<script type="text/javascript" src="${ctx }/resource/My97DatePicker/WdatePicker.js"></script>
<body>
	<!--Top-->
	<%@include file="inc/inc_head.jsp" %>
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="main-content" class="col-md-12">
					<div class="product">
						<div class="col-md-6">
							<div class="caption">
								<div class="name"><h3>${map.productName }</h3></div>
								
								
								
								<div class="bs-example" data-example-id="simple-table">
    <table class="table">
      <caption>日期选择：<input  id="d421" name="orderDate"  value="" type="text" onClick="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})"/><a href="#" onclick="cx();" class="btn btn-2 ">查询</a></caption>
      <thead>
        <tr>
          <th>时间段</th>
          <th>预定价格</th>
          <th>预定</th>
        </tr>
      </thead>
      <tbody id="aaaaa">
      </tbody>
    </table>
  </div>
							</div>
						</div>
						<div class="clear"></div>
					</div>	
					<div class="product-desc">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#description">描述</a></li>
							<%----%><li><a href="#review">评论</a></li> 
						</ul>
						<div class="tab-content">
							<div id="description" class="tab-pane fade in active">
								<p>${map.content }</p>
							</div>
							<div id="review" class="tab-pane fade">
							  <div class="review-text">
								
								<c:forEach items="${list }" var="lists">
									<p>${lists.customerName }:${lists.content }(${lists.insertDate })</p>
								</c:forEach>
							  </div>
							  <div class="review-form">
								<form name="form1" id="form1" method="post">
								<input type="hidden" name="productId" value="${map.id }"/>
									<label>
									<span>评论内容:</span>
									<textarea name="content" id="content"></textarea>
									</label>
									<div class="text-right">
										<input class="btn btn-default" type="reset" name="reset" value="清空">
										<input class="btn btn-default" onclick="save(this);" type="button" name="Submit" value="发表评论">
									</div>
								</form>
							  </div>
							</div>
						</div>
					</div>
				</div>
				<%@include file="inc/inc_right.jsp" %>
			</div>
		</div>
	</div>	
<%@include file="inc/inc_foot.jsp" %>
</body>
<script type="text/javascript">
	function cx(){
		var orderDate = $("input[name='orderDate']").val();
		if(orderDate==''){
			alert('请选择要查询的日期');
			return false;
		}
		$.post("cx.html", {orderDate:orderDate,productId:'${map.id}'}, function(result) {
			result = eval("(" + result + ")");
			if (result.status == "true" || result.status == true) {
				var aaa="";
				
				for(var i=0;i<result.data.length;i++){
					aaa+="<tr>";
			          aaa+="<td>"+result.data[i].sjd+"</td>";
			          aaa+="<td>"+result.data[i].fee+"</td>";
			          if(result.data[i].status=='待预定'){
			          	aaa+="<td><a href=\"#\" onclick=\"addShopCar("+result.data[i].id+");\" class=\"btn btn-2\">预定</a></td>";
			          }else{
			          	aaa+="<td>已经被预定</td>";
			          }
			        aaa+="</tr>";
				}
				$("#aaaaa").html(aaa);
			} else {
			}
		});
	}

function addShopCar(id) {
  		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		window.location.href='zhifu.html?id='+id;
	}
	
	
	function save(src) {
  		if(!checkIsLogin()){
			alert('请先登录');
			window.location.href='${ctx}/front/register.html';
			return false;
		}
		$.post("productPinglunSave.html", $("#form1").serializeArray(), function(result) {
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
