<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/15
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>标签云</title>
</head>
<body>
<h1>这里是用户${user.nickname}的标签云</h1>

<h2>下面是该用户的标签列表</h2>
<ul>
    <c:forEach items="${tagList}" var="tag">
        <li>
                ${tag}
        </li>
    </c:forEach>
</ul>

<a href="<c:url value="/home-page/${user.id}"/>"> 返回该用户的主页 </a>

</body>
</html>
