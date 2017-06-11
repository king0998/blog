<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN"
      class=" wf-source-han-serif-sc-n4-active wf-source-han-serif-sc-n9-active wf-active wf-loading wf-source-han-serif-sc-n4-loading wf-source-han-serif-sc-n9-loading">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>广场 | 大好时光！</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css?v=2.0.1"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js?v=2.0.1"/>"></script>
</head>
<body>
<div class="body_container">
    <div id="header">
        <div class="site-name">
            <a id="logo" href="#">广场</a>
            <p class="description">你的内在只有通过所做的事情才能表现出来</p>
        </div>
        <div id="nav-menu">
            <a href="<c:url value="/square/new"/>"><i class="fa fa-home"> 最新文章</i></a>
            <a href="<c:url value="/square/hot"/>" class="current"><i class="fa fa-archive"> 最热文章</i></a>
            <a href="<c:url value="/square/archives"/> "><i class="fa fa-archive"> 全部文章</i></a>
            <a href="<c:url value="/square/following"/>"><i class="fa fa-user"> 我关注的</i></a>
            <a href="<c:url value="/square/discuss"/>"><i class="fa fa-cog"> 讨论版</i></a>
        </div>
    </div>

    <%-- header --%>


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

            </div>
        </div>


        <div class="pure-u-1-4 pure-u-md-1-4">
            <div id="sidebar" style="min-width: 222px;">
                <div class="widget">
                    <form action="<c:url value="/archives/search"/> " method="post" accept-charset="utf-8"
                          class="search-form">
                        <%--//TODO 修改搜索为全局搜索,最好加上solr?然后是标签页,然后是将sidebar做成通用的slice--%>
                        <input style="width: 172px" type="text" name="keyword" maxlength="20" placeholder="Search"/>
                        <input type="hidden" name="userId" value="${user.id}">
                    </form>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-star-o"> 标签</i>
                    </div>
                    <div class="tagcloud">
                        <c:forEach items="${tags}" var="tag">
                            <a href="/square/archives/${tag.name}" style="font-size: 15px;">${tag.name}(${tag.size})</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<%@include file="../slice/footer.jsp" %>
</body>
</html>