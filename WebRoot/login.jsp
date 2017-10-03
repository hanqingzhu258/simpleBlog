<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
    <%@include file="_header.jsp" %>
</head>
<body>
	<%@include file="_navbar.jsp" %>
	<div class="container">
   	 <div class="col-md-6 col-md-offset-3">
     	   <h1>用户登录</h1>
      	  <hr/>
     	   <%@include file="_alert.jsp" %>
     	   <!-- <form role="form" method="post" action="${pageContext.request.contextPath}/login"> -->
     	    <form role="form" method="post" action="login">
          	  <div class="form-group">
            	    <label for="username">用户名</label>
            	    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
          	  </div>
          	  <div class="form-group">
         	       <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label> 
          	     <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
         	   </div>

         	   <button type="submit" class="btn btn-primary">用户登录</button>
      	  </form>
   	 </div>
	</div>
	<%@include file="_footer.jsp" %>
</body>
</html>