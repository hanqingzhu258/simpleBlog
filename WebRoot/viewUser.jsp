<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户信息</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-2">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">用户信息</h3>
                </div>
                <div class="panel-body">
                    <p>用户昵称：${requestScope.user.nickname}</p>
                    <p>用户邮箱：${requestScope.user.email}</p>
                    <p>用户注册时间：${requestScope.user.registerTime}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">文章信息</h3>
                </div>
                <div class="panel-body">
                    <p>文章总数：${requestScope.articalCounts}</p>
                    <p>发出的评论数：${requestScope.commentCounts}</p>
                    <p>收到的评论数：${requestScope.commentedCounts}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">文章列表</h3>
                </div>
                <c:set var="currentPage" value="${requestScope.page}"/>
                <c:set var="articalCounts" value="${requestScope.articalCounts}"/>
                <c:set var="articalsPerPage" value="${requestScope.articalsPerPage}"/>
                <fmt:parseNumber var="totalPage" integerOnly="true"
                                 value="${articalCounts%articalsPerPage==0?articalCounts/articalsPerPage:articleCounts/articlesPerPage+1}"/>
                <c:set var="articals" value="${requestScope.articals}"/>
                <c:set var="beginIndex" value="${(currentPage-1)*articalsPerPage}"/>
                <c:set var="endIndex"
                       value="${beginIndex+articalsPerPage>articals.size()?articals.size():beginIndex+articalsPerPage}"/>
                <c:set var="user" value="${requestScope.user}"/>

                <div class="panel-body">
                    <table class="table table-bordered table-responsive table-hover table-striped">
                        <thead>
                        <tr>
                            <td>标题</td>
                            <td>修改时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="artical" items="${articals.subList(beginIndex,endIndex)}">
                            <tr>
                                <td>
                                    <a href="<c:url value="/viewArtical?id=${artical.id}"/>">${artical.title}</a>
                                </td>
                                <td>${artical.modifyTime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <hr>
                    <div class="text-center">
                        <nav>
                            <ul class="pagination">
                                <li><a href="<c:url value="/viewUser?id=${user.id}&page=1"/>">首页</a></li>
                                <li>
                                    <a href="<c:url value="/viewUser?id=${user.id}&page=${currentPage-1==0?1:currentPage-1}"/>">&laquo;</a>
                                </li>
                                <c:forEach begin="1" end="${totalPage}" varStatus="loop">
                                    <c:set var="isCurrent" value="${loop.index==currentPage?'active':''}"/>
                                    <li class="${isCurrent}">
                                        <a href="<c:url value="/viewUser?id=${user.id}&page=${loop.index}"/>">${loop.index}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a href="<c:url value="/viewUser?id=${user.id}&page=${currentPage+1>totalPage?totalPage:currentPage+1}"/>">&raquo;</a>
                                </li>
                                <li><a href="<c:url value="/viewUser?id=${user.id}&page=${totalPage}"/>">尾页</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="_footer.jsp" %>
</body>
</html>