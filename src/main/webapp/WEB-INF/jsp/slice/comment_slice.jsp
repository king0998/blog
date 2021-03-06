<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="comment-container">
    <form action="/comment/addHandle" method="post">
        <textarea name="content" id="" rows="2"></textarea>
        <div class="input-body">
            <input class="" type="text" name="nickname" placeholder="昵称">
            <input class="" type="email" name="email" placeholder="邮箱">
            <input class="hidden" type="text" name="entityType" value="${entityType}">
            <input class="hidden" type="text" name="entityId" value="${entityId}">
            <input class="hidden" type="text" name="cToken" value="${sessionScope.get("cToken")}">
            <button>提交评论</button>
        </div>
    </form>
</div>


<c:forEach items="${commentList}" var="comment">
    <div class="message-container">
        <div class="user-container">
            <img class="user-img" src="/img/visitor.png" alt="">
            <div>
                <p class="user-name">${fn:escapeXml(comment.nickname)}</p>
                <p class="user-time">
                    <small>${elf:datehhmm(comment.createdDate)}</small>
                </p>
            </div>
        </div>
        <div>
            <p>${fn:escapeXml(comment.content)}</p>

        </div>
    </div>
</c:forEach>