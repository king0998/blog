<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/15
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>归档页面</title>
</head>
<body>
<h1>这是归档页面</h1>
日志数量:${num}
<ul>
    <c:forEach items="${advoList}" var="advo">
        <li>
            <a href="<c:url value="/article/${advo.id}"/>">${advo.title}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
