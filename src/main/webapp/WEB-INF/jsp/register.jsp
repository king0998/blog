<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login & Register</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
<div class="register container">
    <form class="pure-form pure-form-stacked" action="<c:url value="/registerHandle"/>" method="post">
        <h2 class="">注册</h2>
        <input type="text" name="username" placeholder="用户名">
        <input type="password" name="password" placeholder="密码">
        <input type="email" name="email" placeholder="邮箱">
        <input type="text" name="nickname" placeholder="昵称">
        <input type="text" name="intro" placeholder="介绍">
        <input type="text" class="verifyInput" name="verify" placeholder="验证码">
        <img class="verifyImg" src="/loginVerify" id="verifyImage"><br>
        <input style="display: none;" name="cToken" value="${sessionScope.get("cToken")}" title="cToken">
        <input class="pure-button pure-button-primary" value="注册" type="submit"/>
    </form>
</div>
<script>
    var getNewImg = function () {
        $.ajax({
            url: "/loginVerify?" + Math.random(),
            async: true,
            success: function (result) {
                $("input[name='verifyId']").val(result.loginVerifyCodeId);
                $("#verifyImage").attr("src", result.base64Code);
            }
        })
    }
    window.onload = getNewImg
    document.querySelector("img").addEventListener("click", getNewImg)
</script>
</body>
</html>
