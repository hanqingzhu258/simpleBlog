<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display-->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
			data-target="" >
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">博客</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="about.jsp">关于</a></li>
				<li><a href="<c:url value="/myPage"/>">我的主页</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user!=null}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.nickname}
                        <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<c:url value="/myPage"/>">我的主页</a></li>
                            <li><a href="<c:url value="/userInfo?index=0"/>">个人信息修改</a></li>
                            <li><a href="newArtical.jsp">新建博客</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/logout"/>">注销</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user==null}">
                    <li><a href="login.jsp">登录</a></li>
                    <li><a href="register.jsp">注册</a></li>
                </c:if>
            </ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>