<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 75747
  Date: 2017/5/14
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/normalize.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pure-min.css"/>"/>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<div class="container">
    <form class="pure-form pure-form-stacked" action="<c:url value="/loginHandle"/>" method="post">
        <h2 class="">登录</h2>
        <input type="text" name="username" placeholder="用户名">
        <input type="password" name="password" placeholder="密码">
        <input type="text" class="verifyInput" name="verify" placeholder="验证码">
        <img class="verifyImg" src="/loginVerify" id="verifyImage">
        <input type="hidden" name="verifyId" value="">
        <input style="display: none;" name="cToken" value="${sessionScope.get("cToken")}" title=""/>
        <input type="submit" class="pure-button pure-button-primary" value="登录">
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

<h2>${msg}</h2>
</body>
</html>
