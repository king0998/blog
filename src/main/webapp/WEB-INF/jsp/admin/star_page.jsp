<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/17
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的收藏</title>
</head>
<body>
todo : :给一个list,和归档一个样式,提供删除按钮
<ul>
    <c:forEach items="${advoList}" var="advo">
        <li>
            <a href="/article/${advo.id}">${advo.title}</a>
            <a href="/home-page/${advo.userId}">${advo.nickname}</a>

        </li>
    </c:forEach>
</ul>

</body>
</html>