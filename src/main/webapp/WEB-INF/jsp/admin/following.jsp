<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/18
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的关注</title>
</head>
<body>
    <ol>
        <c:forEach items="${followingList}" var="user">
            <li>
                <a href="/home-page/${user.id}">${user.nickname}</a>
                <p>${user.intro}</p>
                <a href="<c:url value="/admin/following/delete/${user.id}"/>">取消收藏</a>
            </li>
        </c:forEach>
    </ol>
</body>
</html>
