<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/19
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关注我的</title>
</head>
<body>
<ol>
    <c:forEach items="${followerList}" var="item">
        <li>
            <a href="/home-page/${item.id}">${item.nickname}</a>
            <p>${item.intro}</p>
        </li>
    </c:forEach>
</ol>
</body>
</html>
