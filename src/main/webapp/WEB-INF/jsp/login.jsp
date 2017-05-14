<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="<c:url value="/loginHandle"/>" method="post">
        <label>username<input type="text" name="username"> </label>
        <label>password<input type="password" name="password"> </label>
        <input type="submit" value="登录">
    </form>

<h2>${msg}</h2>
</body>
</html>
