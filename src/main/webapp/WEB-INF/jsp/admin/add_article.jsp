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
            <a class="pure-menu-heading" href="<c:url value="/admin/center"/> ">个人中心</a>
            <%--    class="menu-item-divided pure-menu-selected"    --%>
            <ul class="pure-menu-list">
                <li class="menu-item-divided pure-menu-selected"><a href="<c:url value="/admin/article/add"/>"
                                                                    class="pure-menu-link">发表文章</a></li>
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
            <h1>文章管理</h1>
            <h2>你不能把希望放在别人会主动为你做些什么上，这是很没有道理的事</h2>
        </div>

        <form class="pure-form addArticle" action="<c:url value="/admin/article/addHandle"/>" method="post">
            <fieldset class="pure-group">
                <input type="text" name="title" placeholder="标题"/>
            </fieldset>
            <fieldset class="pure-group">
                <textarea name="content" placeholder="文章内容"></textarea>
            </fieldset>
            <fieldset class="pure-group">
                <input type="text" placeholder="标签" name="tags"/>
            </fieldset>
            <input id="draft" type="checkbox" name="draft">草稿
            <input name="cToken" style="display: none;" value="${sessionScope.get("cToken")}"/>
            <fieldset class="pure-group">
                <button type="submit" class="pure-button pure-button-primary ">提交</button>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
