<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>模板</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-2">

            <ul id="myTab" class="nav nav-pills nav-stacked" role="tablist">
                <li><a href="#personal-info" role="tab" data-toggle="tab">个人信息</a>
                </li>
                <li><a href="#modify-password" role="tab"
                       data-toggle="tab">修改密码</a></li>
            </ul>
            <script>
                $(document).ready(function () {
                    $('#myTab li:eq(${requestScope.index}) a').tab('show');
                })

            </script>
        </div>
        <div class="col-md-6">
            <div class="tab-content">
                <%@include file="_alert.jsp" %>
                <div role="tabpanel" class="tab-pane active" id="personal-info">
                    <form role="form" method="post" action="${pageContext.request.contextPath}/updateUser">
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" name="username" id="username"
                                   value="${user.username}" disabled>
                        </div>
                        <div class="form-group">
                            <label for="nickname">昵称</label>
                            <input type="text" class="form-control" name="nickname" id="nickname"
                                   placeholder="请输入昵称" value="${user.nickname}">
                        </div>
                        <div class="form-group">
                            <label for="email">电子邮箱</label>
                            <input type="email" class="form-control" name="email"
                                   id="email" placeholder="请输入电子邮箱"
                                   value="${user.email}">
                        </div>
                        <div class="form-group">
                            <label for="birthday">生日</label>
                            <input type="date" class="form-control" name="birthday"
                                   id="birthday" value="${user.birthday}">
                        </div>
                        <div class="form-group">
                            <label for="registerTime">注册时间</label>
                            <input type="datetime" class="form-control" name="registerTime"
                                   id="registerTime" value="${user.registerTime}" disabled>
                        </div>
                        <button type="submit" class="btn btn-primary">更新信息</button>
                    </form>
                </div>
                <div role="tabpanel" class="tab-pane" id="modify-password">
                    <form role="form" method="post" action="updateUserPassword">
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="text" class="form-control" name="password" id="password" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label for="password2">确认密码</label>
                            <input type="password" class="form-control" name="password2" id="password2"
                                   placeholder="请输入再次密码">
                        </div>

                        <button type="submit" class="btn btn-primary">修改密码</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="_footer.jsp" %>
</body>
</html>