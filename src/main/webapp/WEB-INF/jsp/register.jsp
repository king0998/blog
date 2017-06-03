<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login & Register</title>
</head>
<body>

<form action="<c:url value="/registerHandle"/>" method="post">
    <label>username<input type="text" name="username"> </label><br>
    <label>password<input type="password" name="password"> </label><br>
    <label>email<input type="email" name="email"> </label><br>
    <label>nickname<input type="text" name="nickname"></label><br>
    <label>intro<input type="text" name="intro"></label><br>
    <input style="display: none;" name="cToken" value="${sessionScope.get("cToken")}" title="cToken">
    <button type="submit">注册</button>
</form>
</body>
</html>
