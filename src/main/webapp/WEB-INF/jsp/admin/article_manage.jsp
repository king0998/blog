<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/17
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章管理页面</title>

</head>
<body>
<table>
    todo : draft状态可点击改变
    <c:forEach items="${advoList}" var="advo">
        <tr>
            <td>${advo.title}</td>
            <td>${advo.timestamp}</td>
            <td>${advo.draft}</td>
            <td><a href="<c:url value="/admin/article/edit/${advo.id}"/>">编辑</a>
                <a href="<c:url value="/admin/article/delete/${advo.id}"/>">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
