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
    <
    <title>文章详情页</title>
</head>
<body>
<h3>${advo.title}</h3>
<h3>作者</h3>${advo.username}
<h3>发表时间</h3>${advo.timestamp}
<h3>点赞按钮</h3><button onclick="">点赞</button>
<h3>点赞数量</h3>${advo.likeNum} 收藏

<h3>收藏按钮</h3><button onclick="">收藏</button>
<h3>收藏数量</h3>${advo.starNum}
<h3>文章主体</h3>${advo.content}
<h3>上一篇</h3><button onclick="">上一篇</button>
<h3>下一篇</h3><button onclick="">下一篇</button>
<h3>标签</h3>${advo.tags}
</body>
</html>
