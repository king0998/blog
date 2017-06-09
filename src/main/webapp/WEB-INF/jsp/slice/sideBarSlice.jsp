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

            <c:forEach items="${tags}" var="tag">
                <a href="<c:url value="/archives?name=${tag.name}&userId=${user.id}"/>"
                   style="font-size: 15px;">${tag.name}(${tag.size})</a>
            </c:forEach>

        </div>
    </div>
    <div class="widget">
        <div class="widget-title">
            <i class="fa fa-file-o"> 最近文章</i>
        </div>
        <ul class="post-list">

            <c:forEach items="${latestAdvo}" var="advo">
                <li class="post-list-item"><a class="post-list-link" href="/article/${advo.id}">${advo.title}</a></li>

            </c:forEach>

        </ul>
    </div>
</div>