<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>广场</title>
</head>
<body>
<h1> Hi 我是广场</h1>
<h3>所有文章列表</h3>

todo : 提供follow的人的最新博文及最热博文
<ul>
    <c:forEach items="${advoList}" var="advo">
        <li>
            <a href="<c:url value="/article/${advo.id}"/>">${advo.title}</a>
            <a href="<c:url value="/home-page/${advo.userId}"/> ">${advo.username}</a>
        </li>
    </c:forEach>
</ul>
<a href="<c:url value="/admin/center"/> ">个人中心</a>
<a href="<c:url value="/login"/>">login</a>
<a href="<c:url value="/register"/>">register</a>
</body>
</html>
