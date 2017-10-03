<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>自动跳转</title>
    <%@include file="_header.jsp" %>
    <meta http-equiv="refresh" content="5;url=<c:url value="index.jsp"/>">
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container">
    <h4>您还没有登录,<span id="seconds">5</span>秒后自动跳转到主页</h4>
    <h4>如果没有跳转，请手动点击链接 <a href="index.jsp">返回主页</a></h4>
</div>
<script>
    setInterval("refreshSeconds()", 1000);
    var times = 5;
    function refreshSeconds() {
        times = times - 1;
        $("#seconds").text((times));
    }
</script>
<%@include file="_footer.jsp" %>
</body>
</html>