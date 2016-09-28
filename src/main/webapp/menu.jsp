<%@page import="com.lms.entity.Admin"%>
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
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</head>
<body style="background:#f0f9fd;">
<div class="lefttop">
	<span></span>通讯录
</div>
<dl class="leftmenu">
	<dd>
	<div class="title">
		<span><img src="images/leftico01.png"/></span>管理信息
	</div>
	<ul class="menuson">
		<li class="active"><cite></cite><a href="index.jsp" target="rightFrame">首页模版</a><i></i></li>
	</ul>
	</dd>
	<dd>
	<div class="title">
		<span><img src="images/leftico02.png"/></span>图书管理
	</div>
	<ul class="menuson">
		<li><cite></cite><a href="${ctx}/BookList" target="rightFrame">图书列表</a><i></i></li>
		<%
			Admin admin=(Admin)session.getAttribute("admin");
			if(admin.getRole()==1){
	%>
		<li><cite></cite><a href="${ctx}/BookAdd" target="rightFrame">添加图书</a><i></i></li>
		<%
			}
		%>
	</ul>
	</dd>
	<%
			if(admin.getRole()==1){
	%>
	<dd>
	<div class="title">
		<span><img src="images/leftico03.png"/></span>用户管理
	</div>
	<ul class="menuson">
		<li><cite></cite><a href="${ctx}/UserList"  target="rightFrame">用户列表</a><i></i></li>
		<li><cite></cite><a href="${ctx }/UserAdd"  target="rightFrame">添加用户</a><i></i></li>
	</ul>
	</dd>
	<%
			}
	%>
	<dd>
	<div class="title">
		<span><img src="images/leftico04.png"/></span>借书管理
	</div>
	<ul class="menuson">
		<%
			if(admin.getRole()==1){
		%>
		<li><cite></cite><a href="${ctx}/BookUserList" target="rightFrame">图书列表</a><i></i></li>
		<%
			}
		%>
		<li><cite></cite><a href="${ctx}/UserBookList" target="rightFrame">用户列表</a><i></i></li>
	</ul>
	</dd>
	
</dl>
</body>
</html>
