<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
var ctx='${ctx}';
</script>
<!DOCTYPE HTML>
<html style="overflow-y:hidden;">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/html5.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/respond.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctx}/resource/mine/four/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/mine/four/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resource/mine/four/font/font-awesome.min.css"/>
<!--[if IE 7]>
<link href="${ctx}/resource/mine/four/font/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>场馆预约系统</title>
</head>
<body style="overflow:hidden">
<header class="Hui-header cl"> <a class="Hui-logo l" title="场馆预约系统" href="/">场馆预约系统</a>  <span class="Hui-userbox"><span class="c-white">(${customerBean.username })</span>
 <a class="btn btn-danger radius ml-10" href="${ctx }/customerLogin/out.html" title="退出"><i class="icon-off"></i> 退出</a></span> 
<a aria-hidden="false" class="Hui-nav-toggle" id="nav-toggle" href="#"></a> </header>
<div class="cl Hui-main">
  <aside class="Hui-aside" style="">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
      
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>个人中心<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/customer/mine.html" href="javascript:void(0)">个人中心</a></li>
            <li><a _href="${ctx}/customer/password.html" href="javascript:void(0)">修改密码</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>建议管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/contact/frame.html" href="javascript:void(0)">建议列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>客户管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/customer/frame.html" href="javascript:void(0)">客户列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>轮播图管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/lbt/frame.html" href="javascript:void(0)">轮播图列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>订单管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/order/frame.html" href="javascript:void(0)">订单列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>场地管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/product/frame.html" href="javascript:void(0)">场地列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>分类管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/types/frame.html" href="javascript:void(0)">分类列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl id="menu-system">
        <dt><i class="icon-cogs"></i>通知管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx}/admin/zx/frame.html" href="javascript:void(0)">通知列表</a></li>
          </ul>
        </dd>
      </dl>
      <%--<dl id="menu-system">
        <dt><i class="icon-cogs"></i> 系统管理<b></b></dt>
        <dd>
          <ul>
            <li><a _href="${ctx }/admin/book/frame.html" href="javascript:void(0)">基本设置</a></li>
            <li><a _href="${ctx }/admin/book/frame.html" href="javascript:void(0)">栏目设置</a></li>
            <li><a _href="${ctx }/admin/book/frame.html" href="javascript:void(0)">数据字典</a></li>
            <li><a _href="${ctx }/admin/book/frame.html" href="javascript:void(0)">屏蔽词</a></li>
            <li><a _href="${ctx }/admin/book/frame.html" href="javascript:void(0)">系统日志</a></li>
          </ul>
        </dd>
      </dl>
--%>    </div>
  </aside>
  <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);"></a></div>
  <section class="Hui-article">
    <div id="Hui-tabNav" class="Hui-tabNav">
      <div class="Hui-tabNav-wp">
        <ul id="min_title_list" class="acrossTab cl">
          <li class="active"><span title="首页" data-href="main.html">首页</span><em></em></li>
        </ul>
      </div>
      <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-backward"></i></a><a id="js-tabNav-next" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-forward"></i></a></div>
    </div>
    <div id="iframe_box" class="Hui-articlebox">
      <div class="show_iframe">
        <div style="display:none" class="loading"></div>
        <iframe scrolling="yes" frameborder="0" src="main.html"></iframe>
      </div>
    </div>
  </section>
</div>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="${ctx}/resource/mine/four/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/resource/mine/four/js/H-ui.admin.js"></script>
</body>
</html>
