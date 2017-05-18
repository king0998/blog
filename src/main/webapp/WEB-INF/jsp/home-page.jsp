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


<ul>
    <c:forEach items="${advoList}" var="advo">
        <h3>${advo.title}</h3>
        <h4>id : [ ${advo.id} ]</h4>
        <h3>作者</h3>${advo.nickname}
        <h3>发表时间</h3>${advo.timestamp}
        <h3>点赞数量</h3>${advo.likeNum} 收藏

        <h3>收藏数量</h3>${advo.starNum}
        <h3>文章主体</h3>${advo.content}
        <h3>标签</h3>${advo.tags}
        <a href="<c:url value="/article/${advo.id}"/>">查看全文</a>
        <br/>
        -------------------------------------------------------
        <br/>
    </c:forEach>

</ul>

</body>
</html>
