<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="com.lms.entity.book.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>无标题文档</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script>
	function add(){
		var params=$("#user_add_form").serialize();
		$.post('${ctx}/UserAdd', params, function (data) { window.location.reload(true) });
	}
	function update(status){
		if(status==0){
			var params=$("#user_add_form").serialize();
			$.post('${ctx}/UserUpdate', params, function (data) { window.location.href="${ctx}/UserList"});
		}else{
			alert("该用户处于激活状态,无法修改其信息,请把其状态修改成禁用后重试此操作。");
		}
		
	}
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">用户管理</a></li>
    <li><a href="#">添加用户</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form id="user_add_form" method="post">
    <ul class="forminfo">
    	<input type="text" id="id" name="id" value="${user.id}" hidden/>
		<li><label>用户</label><input id="name" name="name" type="text" class="dfinput" value="${user.name}"/></li>
		<li><label>联系方式</label><input id="tell" name="tell" type="text" class="dfinput" value="${user.tell }"/></li>
		<li><label>身份证号</label><cite><input id="idcard" name="idcard" type="text" class="dfinput" value="${user.idcard}"/></li>
		<li><label>地址</label><input id="address" name="address" type="text"  class="dfinput" value="${user.address}"/></li>
		<li><label>性别</label><cite><input name="gender" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input name="gender" type="radio" value="0"/>女</cite></li>
		
		<li><label>邮箱</label><cite><input id="email" name="email" type="text" class="dfinput" value="${user.email}"/></li>
		<c:if test="${user.id==null}">
			<li><label>密码</label><cite><input id="remark" name="remark" type="text" class="dfinput" value="123456" disabled/><i>系统默认密码</i></li>
			<li><label>状态</label><cite><input name="status" type="radio" value="1" checked="checked"/>启用&nbsp;&nbsp;&nbsp;&nbsp;<input name="status" type="radio" value="0"/>禁用</cite></li>
			<li><label>权限</label><cite><input name="role" type="radio" value="0" checked="checked"/>普通用户&nbsp;&nbsp;&nbsp;&nbsp;<input name="role" type="radio" value="1"/>VIP用户</cite></li>
			<li><label>&nbsp;</label><input name="" onclick="add()" type="button" class="btn" value="确认添加"/></li>
		</c:if>
		<c:if test="${user.id!=null}">
			<li><label>&nbsp;</label><input name="" onclick="update('${user.status}')" type="button" class="btn" value="确认修改"/></li>
		</c:if>
	</ul>
    </form>
    
    </div>


</body>

</html>