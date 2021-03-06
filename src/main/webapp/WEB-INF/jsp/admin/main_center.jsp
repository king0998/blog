<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="pure-menu-heading" href="#">个人中心</a>
            <%--    class="menu-item-divided pure-menu-selected"    --%>
            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/add"/>" class="pure-menu-link">发表文章</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/manage"/>"
                                              class="pure-menu-link">文章管理</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/following"/>" class="pure-menu-link">我的关注</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/follower"/>" class="pure-menu-link">关注我的</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/star/list"/>" class="pure-menu-link">我的收藏</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/msg/list"/>" class="pure-menu-link">系统消息</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>个人中心</h1>
            <h2>${applicationScope.get("interesting")}</h2>
        </div>
        <div class="dashboard">
            <div class="dashboard-item-1">
                <h2> ${articleNum} </h2>
                <p>文章</p>
            </div>
            <div class="dashboard-item-2">
                <h2>${messageNum}</h2>
                <p>留言</p>
            </div>
            <div class="dashboard-item-3">
                <h2>${commentNum}</h2>
                <p>评论</p>
            </div>
            <div class="dashboard-item-4">
                <h2>${visitNum}</h2>
                <p>访问</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
