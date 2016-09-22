<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="com.lms.entity.book.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>无标题文档</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/select-ui.min.js"></script>
<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>

<script type="text/javascript">
	/* 禁用图书外借  */
	function disabled(id){
		$.post('${ctx}/BookDisabled', { "id":id }, function (data) { window.location.reload(true) });
	}
	/* 启用图书外借 */
	function start(id){
		$.post('${ctx}/BookStart', { "id":id }, function (data) { window.location.reload(true) });
	}
	/* 删除图书 */
	function del(id){
		$.post('${ctx}/BookDelete', { "id":id }, function (data) { window.location.reload(true) });
	}
</script>
<style>
	a{
		cursor: pointer;
	}
	
</style>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">图书管理</a></li>
    <li><a href="#">图书列表</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    

    

    
  	
    

    
    
    <ul class="seachform">
    
    <li><label>综合查询</label><input name="" type="text" class="scinput" /></li>
    <li><label>指派</label>  
    <div class="vocation">
    <select class="select3">
    <option>全部</option>
    <option>其他</option>
    </select>
    </div>
    </li>
    
    <li><label>重点客户</label>  
    <div class="vocation">
    <select class="select3">
    <option>全部</option>
    <option>其他</option>
    </select>
    </div>
    </li>
    
    <li><label>客户状态</label>  
    <div class="vocation">
    <select class="select3">
    <option>全部</option>
    <option>其他</option>
    </select>
    </div>
    </li>
    
    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="查询"/></li>
    
    </ul>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>总量</th>
        <th>借出</th>
        <th>库存</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
        	String data=(String)request.getAttribute("books");
        	List<Book> lists=JSON.parseArray(data, Book.class);
        %>
        <c:forEach var="book" items="<%=lists%>">
        <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.auth}</td>
        <td>${book.press }</td>
        <td>${book.totalNumber }</td>
        <td>${book.lendNumber }</td>
        <td>${book.inventoryNumber }</td>
        <td>
        	<c:if test="${book.status==1}">
        		正常
        	</c:if>
        	<c:if test="${book.status==0}">
        		禁用
        	</c:if>
        </td>
        <td>
        	<a href="#" class="tablelink">查看</a>
        	<c:if test="${book.status==1}">
        		<a onclick="disabled('${book.id}')" class="tablelink" style="color:red">禁用</a>
        	</c:if>
        	<c:if test="${book.status==0}">
        		<a onclick="start('${book.id}')"  class="tablelink" style="color:green">启用</a>
        	</c:if>
        	<a onclick="del('${book.id}')" class="tablelink" style="color:red"> 删除</a></td>
        </tr> 
        </c:forEach>
       
    
        </tbody>
    </table>
    
   
  
    

       
	</div> 
 

    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    



</body>

</html>