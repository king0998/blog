<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN"
      class=" wf-source-han-serif-sc-n4-active wf-source-han-serif-sc-n9-active wf-active wf-loading wf-source-han-serif-sc-n4-loading wf-source-han-serif-sc-n9-loading">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>${advo.title} | ${user.nickname}</title>
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
                <div class="post">
                    <%--<h1 class="post-title">春去春又来</h1>--%>
                    <h1 class="post-title">${advo.title}</h1>
                    <div id="date" class="post-meta">
                        ${advo.timestamp}
                    </div>
                    <div class="post-content">
                        ${advo.content}
                    </div>
                    <div class="tags">
                        <c:forEach items="${advo.tags}" var="tag">
                            <a href="<c:url value="/archives?name=${tag}&userId=${user.id}"/> ">${tag}</a>
                        </c:forEach>
                    </div>
                    <div class="post-nav">
                        <a href="/emotion/last-twilight.html" class="next">最后的黄昏</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="pure-u-1-4 pure-u-md-1-4">
            <%@include file="slice/sideBarSlice.jsp" %>
        </div>

        <%@include file="slice/IncludeJsSlice.jsp" %>

    </div>
</div>
</body>
<script>

    function getLocalTime(nS) {
//        return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
        return new Date(parseInt(nS)).toLocaleString().substr(0, 9).replace("-", "/").replace("-", "/");
    }

    function changeArticlePageTimeFormat() {
        var element = document.getElementById("date");
        element.innerHTML = getLocalTime(element.innerHTML.toString());
    }

</script>
</html>