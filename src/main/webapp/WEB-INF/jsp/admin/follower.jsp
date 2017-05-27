<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css?v=2.0.1"/>"/>
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
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/manage"/>"
                                              class="pure-menu-link">文章管理</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/following"/>" class="pure-menu-link">我的关注</a>
                </li>
                <li class="menu-item-divided pure-menu-selected"><a href="<c:url value="/admin/follower"/>"
                                                                    class="pure-menu-link">关注我的</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/star/list"/>" class="pure-menu-link">我的收藏</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>关注我的</h1>
            <h2>这是关注我的人呀</h2>
        </div>

        <ol>
            <c:forEach items="${followerList}" var="item">
                <li>
                    <a href="/home-page/${item.id}">${item.nickname}</a>
                    <p>${item.intro}</p>
                </li>
            </c:forEach>
        </ol>

        <%----%>

        <div class="content_container">

            <div class="post">
                <h1 class="post-title"><a href="/article/1">第一篇文章，写点什么呢</a></h1>
                <div class="post-meta">2017/5/27</div>
                <div class="post-content">
                    可以这么理解，ServletCo
                </div>
            </div>


        </div>

        <%----%>

    </div>
</div>

</body>
</html>
