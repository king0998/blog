<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 侧栏公共部分  --%>

<div id="sidebar" style="min-width: 222px;">
    <div class="widget">
        <form action="<c:url value="/archives/search"/> " method="post" accept-charset="utf-8" class="search-form">
            <input style="width: 172px" type="text" name="keyword" maxlength="20" placeholder="Search"/>
            <input type="hidden" name="userId" value="${user.id}">
        </form>
    </div>
    <div class="widget">
        <div class="widget-title">
            <i class="fa fa-star-o"> 标签</i>
        </div>
        <div class="tagcloud">

            <script>
                $.ajax({
                    url: "/listTags?userId=${user.id}",
                    async: true,
                    success: function (result) {
                        $(result).each(function () {
                            //'<a href="/archives?name=&userId=${user.id}" style="font-size: 15px;">' + this.name + '(' + this.size + ')' + '</a>'
                            $(".tagcloud").append('<a href="/archives?name=' + this.name + '&userId=${user.id}" style="font-size: 15px;">' + this.name + '(' + this.size + ')' + '</a>')
                        })
                    }
                });
            </script>

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