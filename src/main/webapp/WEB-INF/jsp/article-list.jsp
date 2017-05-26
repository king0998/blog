<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN"
      class=" wf-source-han-serif-sc-n4-active wf-source-han-serif-sc-n9-active wf-active wf-loading wf-source-han-serif-sc-n4-loading wf-source-han-serif-sc-n9-loading">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <%--<title>春去春又来 | 屠城</title>--%>
    <title>归档 | ${user.nickname}</title>
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
            <%--<h1 class="hidden">春去春又来</h1>--%>
            <%--<h1 class="hidden">${advo.title}</h1>--%>
            <%--<a id="logo" href="/.">屠城</a>--%>
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
                        <h2>2017</h2>
                        <ul class="listing">
                            <c:forEach items="${advoList}" var="advo">
                                <li>
                                    <span class="date">${advo.timestamp}</span>
                                    <a href="<c:url value="/article/${advo.id}"/>"
                                       title="${advo.title}">${advo.title}</a>
                                </li>
                            </c:forEach>
                        </ul>
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
                        <input type="text" name="q" maxlength="20" placeholder="Search"/>
                        <input type="hidden" name="sitesearch" value="https://www.haomwei.com"/>
                    </form>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-star-o"> 标签</i>
                    </div>
                    <div class="tagcloud">
                        <a href="/tags/wudaokou/" style="font-size: 15px;">五道口</a>
                        <a href="/tags/emotion/" style="font-size: 15px;">心情</a>
                        <a href="/tags/music/" style="font-size: 15px;">音乐</a>
                        <a href="/tags/sports/" style="font-size: 15px;">体育</a>
                        <a href="/tags/NBA/" style="font-size: 15px;">NBA</a>
                        <a href="/tags/technology/" style="font-size: 15px;">技术</a>
                        <a href="/tags/Internet/" style="font-size: 15px;">Internet</a>
                        <a href="/tags/Typecho/" style="font-size: 15px;">Typecho</a>
                        <a href="/tags/movie/" style="font-size: 15px;">电影</a>
                        <a href="/tags/manuscript/" style="font-size: 15px;">随笔</a>
                        <a href="/tags/eat/" style="font-size: 15px;">吃</a>
                        <a href="/tags/jeffery/" style="font-size: 15px;">李杨</a>
                        <a href="/tags/Wordpress/" style="font-size: 15px;">Wordpress</a>
                        <a href="/tags/blog/" style="font-size: 15px;">博客</a>
                        <a href="/tags/GitHub/" style="font-size: 15px;">GitHub</a>
                        <a href="/tags/Hexo/" style="font-size: 15px;">Hexo</a>
                        <a href="/tags/duoshuo/" style="font-size: 15px;">多说</a>
                        <a href="/tags/VPS/" style="font-size: 15px;">VPS</a>
                        <a href="/tags/host/" style="font-size: 15px;">主机</a>
                    </div>
                </div>
                <div class="widget">
                    <div class="widget-title">
                        <i class="fa fa-file-o"> 最近文章</i>
                    </div>
                    <ul class="post-list">
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/last-spring.html">春去春又来</a>
                        </li>
                        <li class="post-list-item"><a class="post-list-link"
                                                      href="/emotion/last-twilight.html">最后的黄昏</a></li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/about-love.html">关于爱情</a>
                        </li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/December.html">十二月</a></li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/saw-you-again.html">再会</a>
                        </li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/summer.html">又一个夏天</a></li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/Tanshari.html">断，舍，离</a>
                        </li>
                        <li class="post-list-item"><a class="post-list-link"
                                                      href="/emotion/help-each-other.html">相依为命</a></li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/dash.html">破折号使用范例</a></li>
                        <li class="post-list-item"><a class="post-list-link" href="/emotion/Ode-to-Joy.html">并没有那么欢乐</a>
                        </li>
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
<div></div>
</body>
</html>