<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>归档 | ${user.nickname}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grids-responsive-min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/my-style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/comment.css"/> "/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
<div class="body_container">
    <div id="header">
        <div class="site-name">
            <a id="logo" href="#">${user.nickname}</a>
            <p class="description">${user.intro}</p>
        </div>
        <div id="nav-menu">
            <a href="<c:url value="/square"/> "><i class="fa fa-home"> 广场</i></a>
            <a href="<c:url value="/home-page/${user.id}"/>"><i class="fa fa-home"> 首页</i></a>
            <a href="<c:url value="/archives/${user.id}"/>"><i class="fa fa-archive"> 归档</i></a>
            <a href="<c:url value="/about/${user.id}"/>" class="current"><i class="fa fa-user"> 关于</i></a>
            <a href="<c:url value="/admin/center"/>"><i class="fa fa-cog"> 个人中心</i></a>
        </div>
    </div>
    <script>
        function follow(id) {
            $.ajax({
                url: "/admin/follow/add/" + id,
                async: true,
                success: function (result) {
                    alert(result.msg);
                    if (result.code == 200) {
                        $("#follow").attr("disabled", true).html("已关注");
                    }
                }
            })
        }
    </script>

    <div id="layout" class="pure-g">
        <div class="pure-u-1 pure-u-md-3-4">
            <div class="content_container">

                <c:choose>
                    <c:when test="${hasFollow}">
                        <button type="button" id="follow" disabled="disabled">已关注</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" id="follow" onclick="follow(${user.id})">关注</button>
                    </c:otherwise>
                </c:choose>

                ${elf:md(about)}

            </div>
            <br/><br/>
            <h2>留言板</h2>
            <hr>
            <br/><br/>


            <%@include file="slice/comment_slice.jsp" %>



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