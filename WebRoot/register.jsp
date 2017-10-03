<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container">

    <div class="col-md-6 col-md-offset-3">
        <h1>用户注册</h1>
        <hr/>
        <%@include file="_alert.jsp" %>
        <form role="form" method="post" action="register">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label for="password2">确认密码</label>
                <input type="password" class="form-control" name="password2" id="password2" placeholder="请再次输入密码">
            </div>
            <div class="form-group">
                <label for="nickname">昵称</label>
                <input type="text" class="form-control" name="nickname" id="nickname" placeholder="请输入昵称">
            </div>
            <div class="form-group">
                <label for="email">电子邮箱</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="请输入电子邮箱">
            </div>
            <div class="form-group">
                <label for="birthday">生日</label>
                <input type="date" class="form-control" name="birthday" id="birthday" placeholder="请输入生日">
            </div>
            <button type="submit" class="btn btn-primary">注册用户</button>
        </form>
    </div>
</div>
<%@include file="_footer.jsp" %>
</body>
</html>