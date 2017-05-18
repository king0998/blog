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
    <style>
        textarea {
            width: 200px;
            height: 200px;;
        }
    </style>
</head>
<body>

todo:提供一个在线编辑器,直接输入md文档
<form action="<c:url value="/admin/article/addHandle"/>" method="post">
    <input type="text" name="title"/>
    <label>
        <textarea name="content"></textarea>
    </label>
    <input type="text" name="tags"/>
    <input type="checkbox" name="draft">
    <input type="submit">
</form>

</body>
</html>
