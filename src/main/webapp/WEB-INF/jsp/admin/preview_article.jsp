<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>预览文章 | ${user.nickname}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/prism.css"/> "/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js?v=2.0.1"/>"></script>
</head>
<body>
<div class="body_container">
    <div id="header">
        <div class="site-name">
            <a id="logo" href="#">${user.nickname}</a>
            <p class="description">${user.intro}</p>
        </div>
        <div id="nav-menu">
            <a href="<c:url value="/home-page/${user.id}"/>" class="current"><i class="fa fa-home"> 首页</i></a>
            <a href="<c:url value="/archives/${user.id}"/>"><i class="fa fa-archive"> 归档</i></a>
            <a href="<c:url value="/about/${user.id}"/>"><i class="fa fa-user"> 关于</i></a>
            <a href="<c:url value="/admin/center"/>"><i class="fa fa-cog">个人中心</i></a>
        </div>
    </div>
    <div id="layout" class="pure-g">
        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">
                <div class="post">
                    <h1 class="post-title">${advo.title}</h1>
                    <div id="date" class="post-meta">
                        ${elf:date(advo.timestamp)}
                    </div>

                    <div class="post-content" id="code-html">
                        ${elf:md(advo.content)}
                    </div>

                    <script src="<c:url value="/js/prism.js"/>"></script>
                </div>
            </div>
        </div>

        <div class="pure-u-1-4 pure-u-md-1-4">
            <%@include file="../slice/sideBarSlice.jsp" %>
        </div>

        <%@include file="../slice/IncludeJsSlice.jsp" %>

    </div>
</div>
</body>
</html>