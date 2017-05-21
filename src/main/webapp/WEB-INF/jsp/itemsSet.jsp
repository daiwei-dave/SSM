<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="org.seckill.entity.Items" %>
<%@ page import="org.seckill.service.ItemsService" %>
<%@ page import="org.seckill.service.impl.ItemsServiceImpl" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        hr {
            border-color: FF7F00;
        }

        div {
            float: left;
            margin: 10px;
        }

        div dd {
            margin: 0px;
            font-size: 10pt;
        }

        div dd.dd_name {
            color: blue;
        }

        div dd.dd_city {
            color: #000;
        }
    </style>
</head>

<body>
<h1>你可能感兴趣的商品</h1>
<hr>

<table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
    <%
        Set<Items> list = (Set<Items>) request.getSession().getAttribute("itemsSet");
        if(list!=null&&list.size()>0)
        {
            for(Items item:list)
            {

    %>
    <tr>
        <td>商品id：<%=item.getId()%></td>
        <td class="dd_name">商品名：<%=item.getName() %></td>
        <td class="dd_city">产地:<%=item.getCity() %></td>
        <td>价格:￥<%=item.getPrice() %></td>
        </br>
    </tr>
<%
        }
    }
%>
</table>

</body>
</html>
