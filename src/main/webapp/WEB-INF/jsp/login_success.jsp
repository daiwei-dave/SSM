<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="org.seckill.entity.User" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<!-- Page title -->
		<title>推荐系统</title>
		<!-- End of Page title -->
		<!-- Libraries -->
		<link type="text/css" href="css/login.css" rel="stylesheet" />	
		<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.html" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/easyTooltip.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
		<!-- End of Libraries -->	
	</head>
	<body>
	<div id="container">

		<div id="box">
		  <% 
		     String userName = "";
		     long id=0;
		     if(session.getAttribute("user")!=null)
		     {
				 User user = (User) session.getAttribute("user");
				 userName=user.getName();
				 id=user.getId();
				 session.setAttribute("uid",id);
			 }
		  %>
		     欢迎您<font color="red"><%=userName%></font>,登录成功！
		</div>
		<a href="/items/list">显示所有商品</a><br>
		<a href="/cart/findMyCart?uID=<%=session.getAttribute("uid")%>">查看我的购物车</a><br>
		<a href="/items/getItemsByCart?uID=<%=session.getAttribute("uid")%>">你可能感兴趣的商品</a>
	</div>
	</body>
</html>