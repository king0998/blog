<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/11
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章详情页</title>
</head>
<body>
todo : 根据情况显示是否已收藏
<h3>${advo.title}</h3><br/>
<label>作者</label>${advo.nickname}<br/>
<label>发表时间</label>${advo.timestamp}<br/>
<button onclick="">点赞</button>
<br/>
<label>点赞数量</label>${advo.likeNum}<br/>
<br/>
<a href="<c:url value="/admin/star/add/${advo.id}"/> ">收藏</a><br/>
<br/>
<label>收藏数量</label>${advo.starNum}<br/>
<label>文章主体:</label>
<p>${advo.content}</p><br/>
<label>标签</label>${advo.tags}<br/>
<label>上一篇</label><br/>
<button onclick="">上一篇</button>
<br/>
<label>下一篇</label><br/>
<button onclick="">下一篇</button>
<br/>
</body>
</html>
