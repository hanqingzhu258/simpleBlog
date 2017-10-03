<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>阅读文章</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1 class="article-title">${requestScope.artical.title} <a hidden class="small edit-link"
                                                                       href="<c:url value="/editArtical?id=${requestScope.artical.id}"/>">编辑文章</a>
            </h1>
            <hr>
            <div id="content">

            </div>
            <div hidden>
                <pre class="fuck-marked">${requestScope.artical.content}</pre>
            </div>

        </div>
    </div>
    <div class="row">
        <hr>
        <div class="col-md-8 col-md-offset-2">
            <form action="<c:url value="/comment"/>" method="post">
                <div class="form-group">
                    <label for="comment" class="sr-only">评论</label>
                    <input type="text" name="comment"
                           id="comment" placeholder="请输入评论内容"
                           class="form-control"/>
                </div>
                <input type="hidden" name="articalId" value="${requestScope.artical.id}"/>
                <input type="submit" class="btn btn-primary" value="发表评论"/>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:set var="comments" value="${requestScope.comments}"/>
            <c:set var="currentPage" value="${requestScope.page}"/>
            <c:set var="totalPages" value="${requestScope.totalPages}"/>
            <c:set var="beginIndex" value="${requestScope.beginIndex}"/>
            <c:set var="endIndex" value="${requestScope.endIndex}"/>
            <table class="table table-responsive">
                <thead>
                <tr>
                    <td>作者</td>
                    <td>评论内容</td>
                    <td>评论时间</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="comment" items="${comments.subList(beginIndex,endIndex)}">
                    <tr>
                        <td><a href="<c:url value="/viewUser?id=${comment.getAuthor().getId()}"/>">${comment.getAuthor().getNickname()}</a>
                        </td>
                        <td>${comment.content}</td>
                      <!--   <td>${comment.createTime}</td>-->
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="text-center">
                <nav>
                    <ul class="pagination">
                        <c:set var="artical" value="${requestScope.artical}"/>
                        <li><a href="<c:url value="/viewArtical?id=${artical.id}&page=1"/>">首页</a></li>
                        <li>
                            <a href="<c:url value="/viewArtical?id=${artical.id}&page=${currentPage-1==0?1:currentPage-1}"/>">&laquo;</a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                            <c:set var="isCurrent" value="${loop.index==currentPage?'active':''}"/>
                            <li class="${isCurrent}">
                                <a href="<c:url value="/viewArtical?id=${artical.id}&page=${loop.index}"/>">${loop.index}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a href="<c:url value="/viewArtical?id=${artical.id}&page=${currentPage+1>totalPages?totalPages:currentPage+1}"/>">&raquo;</a>
                        </li>
                        <li><a href="<c:url value="/viewArtical?id=${artical.id}&page=${totalPages}"/>">尾页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<%@include file="_footer.jsp" %>
<script>
    $(document).ready(function () {
        $("#content").html(
            marked($(".fuck-marked").text())
        );
        $(".article-title").bind("mouseenter mouseleave", function () {
            $(".edit-link").toggle();
        });
        $("table").addClass("table table-bordered table-responsive table-hover table-striped");
    });
</script>
</body>
</html>