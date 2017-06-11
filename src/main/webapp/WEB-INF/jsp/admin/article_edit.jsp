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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/input-style.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/pure-min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/side-menu.css"/>">
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>


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
                <li class="pure-menu-item"><a href="<c:url value="/admin/following"/>" class="pure-menu-link">我的关注</a>
                </li>
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
            <h1>修改 文章</h1>
            <h2>todo:添加一个在线md文档编辑器</h2>
        </div>

        <div class="admin_content">
            <form action="<c:url value="/admin/article/editHandle"/>" class="pure-form-stacked pure-form editArticle"
                  method="post">
                <fieldset>
                    <div id="input-title-container">
                        <label></label>
                        <input placeholder="标题" type="text" id="input_title" name="title" value="${advo.title}">
                    </div>

                    <div id="input-content-container">
                        <label>
                            <textarea placeholder="正文" id="article_content" name="content">${advo.content}</textarea>
                        </label>
                    </div>

                    <div id="input-tags-container">
                        <label>
                            <%--//TODO  js校验tags格式--%>
                            <input placeholder="标签" type="text" name="tags" value="${advo.tags}">
                        </label>
                    </div>
                    <div>
                        是否发布
                        <input type="checkbox" name="draft" value="${advo.draft}"/>
                        更新时间戳:
                        <input type="checkbox" name="updateTimestamp"/>
                    </div>
                    <input name="id" type="hidden" value="${advo.id}"/>
                    <input name="cToken" type="hidden" value="${sessionScope.get("cToken")}"/>
                    <div class="button-group">
                        <button type="submit" class="pure-button pure-button-primary">发布</button>
                        <button type="button" class="pure-button pure-button-primary" id="preview">预览</button>
                    </div>
                </fieldset>
            </form>

            <script>
                $(function () {
                    $("#preview").click(function () {
                        var form = $("<form></form>");
                        form.attr('action', "/admin/article/preview");
                        form.attr('method', 'post');
                        form.attr('target', '_blank');

                        var content = $("<input type='text' name='content' value='" + JSON.stringify($("#article_content")[0].value) + "'>");
                        var title = $("<input type='text' name='title' value='" + $("#input_title").val() + "'>");
                        form.append(content);
                        form.append(title);
                        form.appendTo("body");
                        form.css('display', 'none');
                        form.submit();
                    });

                })

            </script>


        </div>

    </div>
</div>
</body>
</html>
