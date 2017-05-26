<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/15
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人首页</title>
</head>
<body>
<h1>${user.nickname}的博客</h1>
<p>${user.intro}</p>

<a href="<c:url value="/admin/follow/add/${user.id}"/>">follow</a>
<a href="<c:url value="/archives/${user.id}"/> ">归档</a>
<a href="<c:url value="/tags/${user.id}"/> ">标签</a>
<a href="<c:url value="/about/${user.id}"/> ">关于我</a>
<ul>
    <c:forEach items="${advoList}" var="advo">
        <li>
            <h4>${advo.title}</h4>
            <label>id : [ ${advo.id} ]</label><br/>
            <label>作者</label>${advo.nickname}<br/>
            <label>发表时间</label>${advo.timestamp}<br/>
            <label>点赞数量</label>${advo.likeNum} 收藏<br/>

            <label>收藏数量</label>${advo.starNum}<br/>
            <label>文章主体</label>${advo.content}<br/>
            <label>标签</label>${advo.tags}<br/>
            <a href="<c:url value="/article/${advo.id}"/>">查看全文</a>
            <br/>
            -----------------------------------------------------------------
            <br/>
        </li>
    </c:forEach>

</ul>

</body>
</html>
