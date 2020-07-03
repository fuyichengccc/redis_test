<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	    ${users}<br>
	       <form action="/user/updateUser" method="get">
	       		用户id：<input type="text" name="userid" value="${users.userid}" readonly="readonly"><br>
	       		姓名：<input type="text" name="username" value="${users.username}" ><br>
	       		密码：<input type="text" name="userage" value="${users.userage}" ><br>
	       		<input type="submit" value="修改">
	       </form>
  </body>
</html>
