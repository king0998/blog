<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin-style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/pure-min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/side-menu.css"/>">
</head>
<body>

<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="<c:url value="/admin/center"/> ">个人中心</a>
            <%--    class="menu-item-divided pure-menu-selected"    --%>
            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/add"/>" class="pure-menu-link">发表文章</a>
                </li>
                <li class="menu-item-divided pure-menu-selected"><a href="<c:url value="/admin/article/manage"/>"
                                                                    class="pure-menu-link">文章管理</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/following"/>" class="pure-menu-link">我的关注</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/follower"/>" class="pure-menu-link">关注我的</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/star/list"/>" class="pure-menu-link">我的收藏</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>文章管理</h1>
            <h2>无fuck说</h2>
        </div>

        <table>

        </table>

        <div class="admin_content">
            <table class="pure-table pure-table-horizontal">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>时间</th>
                    <th>草稿</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${advoList}" var="advo">
                <tr>
                    <td>${advo.title}</td>

                        <%--<td>${advo.timestamp}</td>--%>
                    <td>${elf:date(advo.timestamp)}</td>

                    <td>${advo.draft}</td>
                    <td><a href="<c:url value="/admin/article/edit/${advo.id}"/>">编辑</a>
                        <a href="<c:url value="/admin/article/delete/${advo.id}"/>">删除</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
