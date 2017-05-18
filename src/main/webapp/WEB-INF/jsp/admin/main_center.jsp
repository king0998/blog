<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/17
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
<label><a href="<c:url value="/admin/article/add"/> ">发表文章</a> </label>
<label><a href="<c:url value="/admin/article/manage"/> "> 文章管理 </a></label>
<label>查看我的follow:给一个list,包含头像,昵称,intro,有一个按钮可以取消关注</label>
<label>查看follow我的:和楼上一样</label>
<label>查看我的收藏</label>
</body>
</html>
