<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN"
      class=" wf-source-han-serif-sc-n4-active wf-source-han-serif-sc-n9-active wf-active wf-loading wf-source-han-serif-sc-n4-loading wf-source-han-serif-sc-n9-loading">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>归档 | ${user.nickname}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js?v=2.0.1"/>"></script>
</head>
<body onload="changeTimeFormat()">
<div class="body_container">
    <div id="header">
        <div class="site-name">
            <%--<h1 class="hidden">春去春又来</h1>--%>
            <a id="logo" href="#">${user.nickname}</a>
            <%--<p class="description">屠夫9441的博客</p>--%>
            <p class="description">${user.intro}</p>
        </div>
        <div id="nav-menu">
            <a href="<c:url value="/home-page/${user.id}"/>"><i class="fa fa-home"> 首页</i></a>
            <a href="<c:url value="/archives/${user.id}"/>" class="current"><i class="fa fa-archive"> 归档</i></a>
            <a href="<c:url value="/about/${user.id}"/>"><i class="fa fa-user"> 关于</i></a>
        </div>
    </div>

    <%-- header --%>

    <div id="layout" class="pure-g">
        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">
                <div class="post">
                    <div class="post-archive">
                        <c:forEach items="${yearMap}" var="value">
                            <h2>${value.key}</h2>
                            <ul class="listing">
                                <c:forEach items="${value.value}" var="advo">
                                    <li>
                                        <span class="date">${advo.timestamp}</span>

                                        <a href="<c:url value="/article/${advo.id}"/>"
                                           title="${advo.title}">${advo.title}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:forEach>
                        <%-- map end --%>
                    </div>
                </div>
            </div>
        </div>


        <%-- footer and sidebar  --%>

        <div class="pure-u-1-4 pure-u-md-1-4">
            <div id="sidebar">
                <div class="widget">
                    <form action="//www.google.com/search" method="get" accept-charset="utf-8" target="_blank"
                          class="search-form">
                        <input style="width: 172px" type="text" name="q" maxlength="20" value="${keyword}"
                               placeholder="Search"/>
                    </form>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-star-o"> 标签</i>
                    </div>
                    <div class="tagcloud">

                        <%@ include file="tagsHtmlSlice.jsp" %>

                    </div>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-file-o"> 最近文章</i>
                    </div>
                    <ul class="post-list">
                        <script>
                            $.ajax({
                                url: "/latestArticle?userId=${user.id}",
                                async: true,
                                success: function (result) {
                                    $(result).each(function () {
                                        <%--$(".tagcloud").append('<a href="/archives?name=' + this.name + '&userId=${user.id}" style="font-size: 15px;">' + this.name + '</a>')--%>
                                        $(".post-list").append('<li class="post-list-item"><a class="post-list-link" href="/article/' + this.id + '">' + this.title + '</a></li>')
                                    })
                                }
                            });
                        </script>
                    </ul>
                </div>
            </div>
        </div>
        <div class="pure-u-1 pure-u-md-3-4"></div>
    </div>
    <script type="text/javascript" src="<c:url value="/js/totop.js?v=2.0.1"/>" async=""></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.fancybox.min.js?v=2.0.1"/>" async=""></script>
    <script type="text/javascript" src="<c:url value="/js/fancybox.js?v=2.0.1"/>" async=""></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.fancybox.min.css?v=2.0.1"/>"/>
    <script type="text/javascript" src="<c:url value="/js/codeblock-resizer.js?v=2.0.1"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/smartresize.js?v=2.0.1"/>"></script>
</div>
</body>
<script>

    function getLocalTime(nS) {
        return new Date(parseInt(nS)).toLocaleString().substr(0, 9).replace("-", "/").replace("-", "/");
    }

    function changeTimeFormat() {
        $(".date").each(function () {
            $(this).html(getLocalTime($(this).html()));
        })
    }


</script>
</html>