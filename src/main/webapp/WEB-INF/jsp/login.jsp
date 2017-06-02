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
</head>
<body>
<form action="<c:url value="/loginHandle"/>" method="post">
    <label>username<input type="text" name="username"> </label><br>
    <label>password<input type="password" name="password"> </label><br>

    <img src="" id="verifyImage"><br>
    <button type="button" id="showVerify">显示验证码</button>
    <br>
    <label>验证码<input type="text" name="verify"> </label><br>
    <input type="hidden" name="verifyId" value=""><br>
    <input type="submit" value="登录">
</form>

<script>
    $("#showVerify").click(function () {
        $.ajax({
            url: "/loginVerify?" + Math.random(),
            async: true,
            success: function (result) {
                $("input[name='verifyId']").val(result.loginVerifyCodeId);
                $("#verifyImage").attr("src", result.base64Code);
            }
        })
    })


</script>

<h2>${msg}</h2>
</body>
</html>
