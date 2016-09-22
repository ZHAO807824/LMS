<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>无标题文档</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<body style="background:url(${ctx}/images/topbg.gif) repeat-x;">
<div class="topleft">
	<a href="main.html" target="_parent"><img src="${ctx}/images/logo.png" title="系统首页"/></a>
</div>

<div class="topright">
	<ul>
		<li><span><img src="${ctx}/images/help.png" title="帮助" class="helpimg"/></span><a href="#">帮助</a></li>
		<li><a href="#">关于</a></li>
		<li><a href="${ctx}/Logout" target="_parent">退出</a></li>
	</ul>
	<div class="user">
		<span>${sessionScope.user}</span>
		<i>消息</i>
		<b>5</b>
	</div>
</div>
</body>
</html>
