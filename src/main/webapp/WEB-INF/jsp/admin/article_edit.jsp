<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/17
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章编辑</title>
</head>
<body>
todo:提供一个在线md编辑器,从文章管理页面进来
<form action="<c:url value="/admin/article/editHandle"/>" method="post">
    <input type="text" name="title" value="${advo.title}">
    <textarea name="content" rows="8" cols="50">${advo.content}</textarea>
    <input name="tags" value="${advo.tags}">
    <label>是否发布
        <input type="checkbox" name="draft" value="${advo.draft}"/>
    </label>
    <label>更新时间戳:
        <input type="checkbox" name="updateTimestamp"/>
    </label>
    <input name="id" type="hidden" value="${advo.id}"/>
    <input type="submit"/>
</form>
</body>
</html>
