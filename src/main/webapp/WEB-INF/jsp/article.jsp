<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="elf" uri="/WEB-INF/tlds/elfunc.tld" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>${advo.title} | ${user.nickname}</title>
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
                    <%--<h1 class="post-title">春去春又来</h1>--%>
                    <h1 class="post-title">${advo.title}</h1>
                    <div id="date" class="post-meta">
                        ${elf:date(advo.timestamp)}
                        <span>阅读量:${pageView}</span>
                        <c:choose>
                            <c:when test="${isStar}">
                                <a href="#">已收藏</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" onclick="addStar(${advo.id})">收藏</a>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <script>
                        function addStar(id) {
                            $.ajax({
                                url: "/admin/star/addHandle",
                                type: "POST",
                                data: {id: id, cToken: "${sessionScope.get("cToken")}"},
                                success: function (result) {
                                    alert(result.msg);
                                    if (result.code == 200) {
                                        $("#date").find("> a").html("已收藏").attr('onclick', '');
                                    }
                                }
                            })
                        }
                    </script>

                    <div class="post-content" id="code-html">
                        ${elf:md(advo.content)}
                    </div>

                    <script src="<c:url value="/js/prism.js"/>"></script>

                    <div class="tags">
                        <c:forEach items="${advo.tags}" var="tag">
                            <a href="<c:url value="/archives?name=${tag}&userId=${user.id}"/> ">${tag}</a>
                        </c:forEach>
                    </div>
                    <%--<div class="post-nav">--%>
                    <%--<a href="/emotion/last-twilight.html" class="next">最后的黄昏</a>--%>
                    <%--</div>--%>
                </div>
            </div>
            <p>发表评论</p>
            <div class="comment-container">
                <form action="">
                    <textarea name="" id="" rows="2"></textarea>
                    <div class="input-body">
                        <input class="" type="text" placeholder="昵称">
                        <input class="" type="text" placeholder="邮箱">
                        <button>提交评论</button>
                    </div>
                </form>
            </div>
            <div class="message-container">
                <div class="user-container">
                    <img class="user-img" src="https://chopstack.com/visitor.png" alt="">
                    <span class="user-name">系统通知</span>
                    <span class="user-time">2017/06/11</span>
                </div>
                <div>
                    <p>用户[ 挥戈入巷 ]收藏了你的文章:[ 再次测试marked ]</p>

                </div>
            </div>
            <div class="message-container">
                <div class="user-container">
                    <img class="user-img" src="https://chopstack.com/visitor.png" alt="">
                    <span class="user-name">系统通知</span>
                    <span class="user-time">2017/06/11</span>
                </div>
                <div>
                    <p>用户[ 挥戈入巷 ]收藏了你的文章:[ 再次测试marked ]</p>

                </div>
            </div>
        </div>


        <div class="pure-u-1-4 pure-u-md-1-4">
            <%@include file="slice/sideBarSlice.jsp" %>
        </div>

        <%@include file="slice/IncludeJsSlice.jsp" %>

    </div>

</div>
<%@include file="slice/footer.jsp" %>

</body>
<style>
    .comment-container {
        width: 100%;
        margin-top: 15px;
        padding: 24px 0 0;
        background-color: #fff;
        border: 1px solid rgba(150, 150, 150, 0.2);
    }

    .comment-container textarea {
        width: 100%;
        line-height: 1.8;
        padding: 0 20px 0 20px;
        background-color: transparent;
        resize: none;
        height: 55px;
        border: none;
        max-width: none !important;
        font-size: 1em;
        outline: none;
    }

    .input-body {
        padding-top: 15px;
        padding-bottom: 15px;
        height: 48px;
        margin-top: 12px;
        position: relative;
        background-color: #fbfbfb;
    }

    .input-body input {
        padding: 0;
        line-height: 1.4;
        border: none;
        background-color: transparent;
        max-width: none !important;
        font-size: 1em;
        outline: none;
        padding-left: 20px;
        padding-right: 20px;
    }

    .comment-container form {
        margin-bottom: 0px;
    }

    .input-body button {
        padding-left: 25px;
        padding-right: 25px;
        width: auto;
        position: absolute;
        right: 0;
        top: 0;
        background-color: #000;
        color: #fff;
        height: 32px;
        line-height: 32px;
        margin: 8px 10px 8px 0;
        border-radius: 20px;
        text-decoration: none;
        border: none;
        max-width: none !important;
        font-size: 1em;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        outline: none;
    }

    .admin_content {
        color: #444;
        padding: 24px 20px;
    }

    .user-name {
        position: relative;
        top: -22px;
        left: 10px;
        line-height: 1.3;
        background-color: #fff;
        color: #000;
        padding: 0;
        font-weight: normal;
    }

    .user-time {
        position: relative;
        left: -50px;
        font-size: 80%;
        top: -4px;
    }

    .user-img {
        width: 36px;
        height: 36px;
        border-radius: 100%;
    }

    .message-container {
        padding: 24px 20px;
        background-color: #fff;
        border: 1px solid rgba(150, 150, 150, 0.18);
        margin: 30px 0 0;
        list-style: none;
        border-radius: 5px;
        font-family: 'TIBch', 'Classic Grotesque W01', 'Helvetica Neue', Arial, 'Hiragino Sans GB', 'STHeiti', 'Microsoft YaHei', 'WenQuanYi Micro Hei', SimSun, sans-serif;
    }

    user-container {
        margin-left: 48px;
    }
</style>
</html>