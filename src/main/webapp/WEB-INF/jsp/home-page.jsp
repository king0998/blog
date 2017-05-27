<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN"
      class=" wf-source-han-serif-sc-n4-active wf-source-han-serif-sc-n9-active wf-active wf-loading wf-source-han-serif-sc-n4-loading wf-source-han-serif-sc-n9-loading">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>首页 | ${user.nickname}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js?v=2.0.1"/>"></script>
</head>
<body onload="changeArticlePageTimeFormat()">
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
        </div>
    </div>

    <div id="layout" class="pure-g">

        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">
                <c:forEach items="${advoList}" var="advo">
                    <div class="post">
                        <h1 class="post-title"><a href="<c:url value="/article/${advo.id}"/>">${advo.title}</a></h1>
                        <div class="post-meta">
                                ${advo.timestamp}
                        </div>
                        <div class="post-content">
                                ${advo.content}
                        </div>
                        <p class="readmore"><a href="<c:url value="/article/${advo.id}"/>">阅读全文</a></p>
                    </div>
                </c:forEach>

                <nav class="page-navigator">
                    <span class="page-number current">1</span>
                    <a class="page-number" href="/page/2/">2</a>
                    <a class="page-number" href="/page/3/">3</a>
                    <span class="space">…</span>
                    <a class="page-number" href="/page/20/">20</a>
                    <a class="extend next" rel="next" href="/page/2/">下一页</a>
                </nav>
            </div>
        </div>


        <%-- footer and sidebar  --%>

        <div class="pure-u-1-4 pure-u-md-1-4">
            <div id="sidebar">
                <div class="widget">
                    <form action="//www.google.com/search" method="get" accept-charset="utf-8" target="_blank"
                          class="search-form">
                        <input style="width: 172px" type="text" name="q" maxlength="20" placeholder="Search"/>
                        <input type="hidden" name="sitesearch" value="https://www.haomwei.com"/>
                    </form>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-star-o"> 标签</i>
                    </div>
                    <div class="tagcloud">

                        <%--<a href="/tags/wudaokou/" style="font-size: 15px;">五道口</a>--%>

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
    </div>
    <script type="text/javascript" src="<c:url value="/js/jquery.fancybox.min.js?v=2.0.1"/>" async=""></script>
    <script type="text/javascript" src="<c:url value="/js/fancybox.js?v=2.0.1"/>" async=""></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.fancybox.min.css?v=2.0.1"/>"/>
    <script type="text/javascript" src="<c:url value="/js/codeblock-resizer.js?v=2.0.1"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/smartresize.js?v=2.0.1"/>"></script>
</div>
</body>
<script>
    function getLocalTime(nS) {
//        return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
        return new Date(parseInt(nS)).toLocaleString().substr(0, 9).replace("-", "/").replace("-", "/");
    }

    function changeArticlePageTimeFormat() {
        $(".post-meta").each(function () {
            $(this).html(getLocalTime($(this).html()));
        })
    }

</script>
</html>