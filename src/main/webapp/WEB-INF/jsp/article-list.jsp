<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
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
<body>
<div class="body_container">
    <div id="header">
        <div class="site-name">
            <a id="logo" href="#">${user.nickname}</a>
            <p class="description">${user.intro}</p>
        </div>
        <div id="nav-menu">
            <a href="<c:url value="/home-page/${user.id}"/>"><i class="fa fa-home"> 首页</i></a>
            <a href="<c:url value="/archives/${user.id}"/>" class="current"><i class="fa fa-archive"> 归档</i></a>
            <a href="<c:url value="/about/${user.id}"/>"><i class="fa fa-user"> 关于</i></a>
            <a href="<c:url value="/admin/center"/>"><i class="fa fa-cog"> 个人中心</i></a>
        </div>
    </div>

    <%-- header --%>

    <div id="layout" class="pure-g">
        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">
                <c:if test="${not empty desc}">
                    <h1 class="label-title">正在查看有关 ${desc} 的文章</h1>
                </c:if>

                <div class="post">
                    <div class="post-archive">
                        <%-- start --%>
                        <%--  end  --%>

                        <c:forEach items="${yearMap}" var="value">
                            <h2>${value.key}</h2>
                            <ul class="listing">
                                <c:forEach items="${value.value}" var="advo">
                                    <li>
                                        <span class="date">
                                                ${elf:date(advo.timestamp)}
                                        </span>

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
            <%@include file="slice/sideBarSlice.jsp" %>
        </div>
    </div>

    <%@include file="slice/IncludeJsSlice.jsp" %>

</div>
</body>
</html>