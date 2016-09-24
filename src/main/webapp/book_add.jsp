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
		var params=$("#book_add_form").serialize();
		$.post('${ctx}/BookAdd', params, function (data) { window.location.reload(true) });
	}
	function update(lendNumber){
		if(lendNumber==0){
			var params=$("#book_add_form").serialize();
			$.post('${ctx}/BookUpdate', params, function (data) { window.location.href="${ctx}/BookList"});
		}else{
			alert("该图书有外借情况,数量为"+lendNumber+"本,故无法修改图书信息");
		}
		
	}
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">图书管理</a></li>
    <li><a href="#">添加图书</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form id="book_add_form" method="post">
    <ul class="forminfo">
    	<input type="text" id="id" name="id" value="${book.id}" hidden/>
		<li><label>图书名称</label><input id="name" name="name" type="text" class="dfinput" value="${book.name}"/></li>
		<li><label>作者</label><input id="auth" name="auth" type="text" class="dfinput" value="${book.auth }"/></li>
		<li><label>出版社</label><cite><input id="press" name="press" type="text" class="dfinput" value="${book.press}"/></li>
		<li><label>总量</label><input id="totalNumber" name="totalNumber" type="number"  class="dfinput" value="${book.totalNumber}"/></li>
		<c:if test="${book.status==null}">
			<li><label>是否可借阅</label><cite><input name="status" type="radio" value="1" checked="checked"/>是&nbsp;&nbsp;&nbsp;&nbsp;<input name="status" type="radio" value="0"/>否</cite></li>
		</c:if>
		
		<li><label>备注</label><cite><input id="remark" name="remark" type="text" class="dfinput" value="${book.remark}"/></li>
		<c:if test="${book.id!=null}">
			<li><label>&nbsp;</label><input name="" onclick="update('${book.lendNumber}')" type="button" class="btn" value="确认修改"/></li>
		</c:if>
		<c:if test="${book.id==null }">
			<li><label>&nbsp;</label><input name="" onclick="add()" type="button" class="btn" value="确认添加"/></li>
		</c:if>
	</ul>
    </form>
    
    </div>


</body>

</html>