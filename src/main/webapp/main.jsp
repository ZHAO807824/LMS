<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>LMS</title>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="menu.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
		<frame src="index.jsp" name="rightFrame" id="rightFrame" title="rightFrame"/>
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
