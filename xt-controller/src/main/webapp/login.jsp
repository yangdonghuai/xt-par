<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/15
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
</head>
<body>
<form action="checkLogin" method="post">
    用户名：<input type="text" name="loginId">
    密码：<input type="text" name="loginPwd">
    <input type="submit">
</form>
</body>
</html>
