<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin-style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/pure-min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/side-menu.css"/>">
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js?v=2.0.1"/>"></script>
</head>
<body>

<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="<c:url value="/admin/center"/> ">个人中心</a>
            <%--    class="menu-item-divided pure-menu-selected"    --%>
            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/add"/>" class="pure-menu-link">发表文章</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/article/manage"/>"
                                              class="pure-menu-link">文章管理</a></li>
                <li class="menu-item-divided pure-menu-selected"><a href="<c:url value="/admin/following"/>"
                                                                    class="pure-menu-link">我的关注</a></li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/follower"/>" class="pure-menu-link">关注我的</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/star/list"/>" class="pure-menu-link">我的收藏</a>
                </li>
                <li class="pure-menu-item"><a href="<c:url value="/admin/msg/list"/>" class="pure-menu-link">系统消息</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>我的关注</h1>
            <h2>${applicationScope.get("interesting")}</h2>
        </div>

        <ol>

        </ol>

        <div class="admin_content">
            <table class="pure-table pure-table-horizontal">
                <thead>
                <tr>
                    <th width="50%">昵称</th>
                    <th width="30%">签名</th>
                    <th width="20%">操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${followingList}" var="user">
                    <tr id="tr-${user.id}">
                        <td><a href="/home-page/${user.id}">${user.nickname}</a></td>
                        <td>${user.intro}</td>
                        <td><a href="#" onclick="deleteStar(${user.id})">取消关注</a></td>
                    </tr>
                </c:forEach>
                <script>
                    function deleteStar(id) {
                        $.ajax({
                            url: "/admin/following/deleteHandle",
                            type: "POST",
                            data: {id: id, cToken: "${sessionScope.get("cToken")}"},
                            success: function (result) {
                                if (result) {
                                    $("#tr-" + id).css("display", "none");
                                }
                            }
                        })
                    }
                </script>

                </tbody>
            </table>
        </div>

    </div>
</div>

</body>
</html>
