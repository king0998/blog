<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加文章</title>
</head>
<body>
<form action="<c:url value="article/addHandle"/>" method="post">
    <label>title<input type="text" name="title"> </label>
    <label>tags<input type="text" name="tags"> </label>
    <label>timestamp<input type="text" name="timestamp"> </label>
    <label>content<input type="text" name="content"> </label>
</form>
</body>
</html>
