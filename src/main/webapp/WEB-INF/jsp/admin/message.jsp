<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
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
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/manage"/>"
                                              class="pure-menu-link">文章管理</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/following"/>" class="pure-menu-link">我的关注</a>
                </li>
                <li class="menu-item-divided"><a href="<c:url value="/admin/follower"/>" class="pure-menu-link">关注我的</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/star/list"/>" class="pure-menu-link">我的收藏</a>
                </li>
                <li class="pure-menu-item pure-menu-selected"><a href="<c:url value="/admin/msg/list"/>"
                                                                 class="pure-menu-link">系统消息</a></li>


            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>消息列表</h1>
            <h2>世上一切痛苦,本质都是对自己无能的愤怒</h2>
        </div>


        <div class="admin_content">
            <ul>

                <c:forEach items="${conversations}" var="con">
                        <div>
                            <div class="message-container">
                                <div class="user-container">
                                    <img class="user-img" src="https://chopstack.com/visitor.png" alt="">
                                    <a href="#" class="user-name"> ${con.get("user").nickname}</a>
                                    <span class="user-time">${ elf:date((con.get("message")).createdDate)}</span>
                                </div>
                                <div>
                                    <p>${con.get("message").content}</p>
                                    <a href="<c:url value="/admin/msg/detail?conversationId=${(con.get(\"message\")).conversationId}"/>">查看详情(共 ${con.get("message").id}
                                        条消息)</a>
                                </div>
                            </div>
                            <div></div>
                        </div>
                </c:forEach>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
