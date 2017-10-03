<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑文章</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container-fluid">
    <form role="form" id="form" action="<c:url value="/updateArtical"/>" method="post">
        <div class="form-group" hidden>
            <label for="id" class="sr-only">文章编号</label>
            <input type="text" id="id" name="id" value="${requestScope.artical.id}"/>
        </div>
        <div class="row">
            <div class="col-md-8">
                <%@include file="_alert.jsp" %>
                <div class="form-group">
                    <label class="sr-only" for="title">标题</label>
                    <input class="form-control" name="title"
                           id="title" placeholder="请输入文章标题" type="text"
                           value="${requestScope.artical.title}"/>
                </div>
            </div>
            <div class="col-md-2 col-md-offset-1">
                <input type="submit" class="btn btn-primary" value="更新"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="sr-only" for="content">内容</label>
                    <textarea placeholder="请输入内容"
                              class="form-control"
                              id="content" name="content"
                              rows="30">${requestScope.artical.content}</textarea>
                </div>
            </div>
            <div id="preview" class="col-md-5">

            </div>
        </div>
    </form>

</div>
<script>
    $(document).ready(function () {
        $("#content").on("keydown keyup change", function () {
            $("#preview").html(marked($("#content").val()));
            $("table").addClass("table table-bordered table-responsive table-hover table-striped");

        }).keydown();
    })
</script>
<%@include file="_footer.jsp" %>
</body>
</html>