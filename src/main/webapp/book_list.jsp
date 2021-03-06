<%@page import="com.lms.entity.Admin"%>
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
	
	/* 借阅图书 */
	function borrow(id){
		$.post('${ctx}/Borrow', { "id":id }, function (data) {
			if(data=='true'){
				window.location.href="${ctx}/UserBookList";
			}
			else{
				alert("借阅失败,请检查您账号状态和借阅数目");
			}
		}
		);
	}
</script>
<style>
	a{
		cursor: pointer;
		margin-left:5px;
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
    <form action="BookList" method="post">
    <li><label>综合查询</label><input id="bookName" name="bookName" type="text" class="scinput" /></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>
    </form>
    </ul>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th style="width:5%">编号</th>
        <th style="width:22%">书名</th>
        <th style="width:22%">作者</th>
        <th style="width:15%">出版社</th>
        <th style="width:5%">总量</th>
        <th style="width:5%">借出</th>
        <th style="width:5%">库存</th>
        <th style="width:6%">状态</th>
        <th style="width:5%">操作</th>
        </tr>
        </thead>
        <tbody>
        <%
        	Admin admin=(Admin)session.getAttribute("admin");
        	String data=(String)request.getAttribute("books");
        	List<Book> lists=JSON.parseArray(data, Book.class);
        %>
        <c:forEach var="book" items="<%=lists%>">
        <tr>
        <td style="width:5%">${book.id}</td>
        <td style="width:22%">${book.name}</td>
        <td style="width:22%">${book.auth}</td>
        <td style="width:15%">${book.press }</td>
        <td style="width:5%">${book.totalNumber }</td>
        <td style="width:5%">
        	<a href="${ctx}/SingleBookUserList?id=${book.id}" style="color:green">${book.lendNumber }</a>
        </td>
        <td style="width:5%">${book.inventoryNumber }</td>
        <td style="width:6%">
        	<c:if test="${book.status==1}">
        		正常
        	</c:if>
        	<c:if test="${book.status==0}">
        		禁用
        	</c:if>
        </td>
        <td style="width:15%">
        	<%
        		if(admin.getRole()==1){
        	%>
        	<c:if test="${book.lendNumber==0 }">
        		<a href="${ctx}/BookUpdate?id=${book.id}" class="tablelink">修改</a>
        	</c:if>
        	<c:if test="${book.lendNumber!=0 }">
        		<a href="${ctx}/BookUpdate?id=${book.id}" class="tablelink">查看</a>
        	</c:if>
        	
        	<c:if test="${book.status==1}">
        		<a onclick="disabled('${book.id}')" class="tablelink" style="color:red">禁用</a>
        	</c:if>
        	<c:if test="${book.status==0}">
        		<a onclick="start('${book.id}')"  class="tablelink" style="color:green">启用</a>
        	</c:if>
        	<a onclick="del('${book.id}')" class="tablelink" style="color:red"> 删除</a></td>
        	<%
        		}else{
        	%>
        		<a onclick="borrow('${book.id}')" class="tablelink">借阅</a></td>
        	<%
        		}
        	%>
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