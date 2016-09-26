<%@page import="com.lms.bean.Borrow"%>
<%@page import="com.lms.entity.user.User"%>
<%@page import="com.alibaba.fastjson.JSON"%>
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
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户列表</a></li>
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
        <th style="width:5%">编号</th>
        <th style="width:6%">用户</th>
        <th style="width:12%">邮箱</th>
        <th style="width:10%">联系方式</th>
        <th style="width:13%">身份证</th>
        <th style="width:15%">地址</th>
        <th style="width:5%">性别</th>
        <th style="width:8%">已借图书</th>
        <th style="width:7%">权限</th>
        <th style="width:5%">状态</th>
        <th style="width:14%">操作</th>
        </tr>
        </thead>
        <tbody>
        <%
        	List<Borrow<List<User>>> lists=(List<Borrow<List<User>>>)request.getAttribute("lists");
        	if(lists!=null&&lists.size()>0)
        		for(Borrow borrow:lists){
        			String key=borrow.getKey();
        			List<User> value=(List<User>)borrow.getValue();
        %>
        <tr>
        	<td colspan="11" style="font-size:15px;background:#FCFCFC"><%=key%></td>
        </tr>
        <c:forEach var="user" items="<%=value%>">
        <tr>
        <td style="width:5%">${user.id}</td>
        <td style="width:6%">${user.name}</td>
        <td style="width:12%">${user.email}</td>
        <td style="width:10%">${user.tell}</td>
        <td style="width:13%">${user.idcard}</td>
        <td style="width:15%">${user.address}</td>
        <td style="width:5%">
        	<c:if test="${user.gender==1}">
        		男
        	</c:if>
        	<c:if test="${user.gender==0}">
        		女
        	</c:if>
        </td>
        <td style="width:8%">
        	<a href="" class="tablelink" style="color:green">${user.lendNumber }</a>
        	
        </td>
        <td style="width:7%">
        	<c:if test="${user.role==1}">
        		VIP用户
        	</c:if>
        	<c:if test="${user.role==0}">
        		普通用户
        	</c:if>
        </td>
        <td style="width:5%">
        	<c:if test="${user.status==1}">
        		启用
        	</c:if>
        	<c:if test="${user.status==0}">
        		禁用
        	</c:if>
        </td>
        <td style="width:14%">
        	<c:if test="${user.status==1 }">
        		<a href="${ctx}/UserUpdate?id=${user.id}" class="tablelink">查看</a>
        	</c:if>
        	<c:if test="${user.status==0 }">
        		<a href="${ctx}/UserUpdate?id=${user.id}" class="tablelink" style="color:green">修改</a>
        	</c:if>
        
        	<c:if test="${user.role==1 }">
        		<a onclick="demotion('${user.id}')" class="tablelink" style="color:red">降级</a>
        	</c:if>
        	<c:if test="${user.role==0 }">
        		<a onclick="upgrade('${user.id}')"  class="tablelink" style="color:green">升级</a>
        	</c:if>
        
        	<c:if test="${user.status==1}">
        		<a onclick="disabled('${user.id}')" class="tablelink" style="color:red">禁用</a>
        	</c:if>
        	<c:if test="${user.status==0}">
        		<a onclick="start('${user.id}')"  class="tablelink" style="color:green">启用</a>
        	</c:if>
        	
        	<a onclick="del('${user.id}')" class="tablelink" style="color:red"> 删除</a></td>
        </tr> 
        </c:forEach>
       <%
        	}
       %>
    
        </tbody>
    </table>
    
	</div> 
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    



</body>

</html>