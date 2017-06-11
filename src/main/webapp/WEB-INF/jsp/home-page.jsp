<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>首页 | ${user.nickname}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
<div class="body_container" style="position: relative;right:4px">
    <div id="header">
        <div class="site-name">
            <a id="logo" href="#">${user.nickname}</a>
            <p class="description">${user.intro}</p>
        </div>

        <div id="nav-menu">
            <%--<a href="<c:url value="/square" />"><i class="fa fa-th-list"> 广场</i></a>--%>
            <a href="<c:url value="/home-page/${user.id}"/>" class="current"><i class="fa fa-home"> 首页</i></a>
            <a href="<c:url value="/archives/${user.id}"/>"><i class="fa fa-archive"> 归档</i></a>
            <a href="<c:url value="/about/${user.id}"/>"><i class="fa fa-user"> 关于</i></a>
            <a href="<c:url value="/admin/center"/>"><i class="fa fa-cog"> 个人中心</i></a>
        </div>
    </div>

    <div id="layout" class="pure-g">
        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">
                <c:forEach items="${advoList}" var="advo">
                    <div class="post">
                        <h1 class="post-title"><a href="<c:url value="/article/${advo.id}"/>">${advo.title}</a></h1>
                        <div class="post-meta">
                                <%--${advo.timestamp}--%>
                                ${elf:date(advo.timestamp)}
                        </div>
                        <div class="post-content">
                                <%--${advo.content}--%>
                                ${elf:md(advo.content)}
                        </div>
                            <%--<p class="readmore"><a href="<c:url value="/article/${advo.id}"/>">阅读全文</a></p>--%>
                    </div>
                </c:forEach>
                <%--<nav class="page-navigator">--%>
                <%--<span class="page-number current">1</span>--%>
                <%--<a class="page-number" href="/page/2/">2</a>--%>
                <%--<a class="page-number" href="/page/3/">3</a>--%>
                <%--<span class="space">…</span>--%>
                <%--<a class="page-number" href="/page/20/">20</a>--%>
                <%--<a class="extend next" rel="next" href="/page/2/">下一页</a>--%>
                <%--</nav>--%>
            </div>
        </div>


        <%-- footer and sidebar  --%>

        <div class="pure-u-1-4 pure-u-md-1-4">
            <%@include file="slice/sideBarSlice.jsp" %>
        </div>

    </div>
    <%@include file="slice/IncludeJsSlice.jsp" %>

</div>

<%@include file="slice/footer.jsp" %>

</body>
</html>