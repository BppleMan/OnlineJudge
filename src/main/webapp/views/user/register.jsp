<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String headerPath = "/views/public/header.jsp";
    String footerPath = "/views/public/footer.jsp";
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/register.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <form role="form" action="${basePath}/user/register_submit" method="post">
            <div class="form-group">
                <label for="username">用户名</label><input type="text" class="form-control" id="username" name="username"/>
            </div>
            <div class="form-group">
                <label for="email">邮箱地址</label><input type="email" class="form-control" id="email" name="email"/>
            </div>
            <div class="form-group">
                <label for="password">密码</label><input type="password" class="form-control" id="password" name="password"/>
            </div>
            <div class="form-group">
                <label><input type="checkbox" name="needLogin"/>直接登录?</label>
            </div>
            <input type="hidden" name="token" value="${register_token}">
            <button type="button" class="btn btn-default" onclick="register('${basePath}')">注册</button>
        </form>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/user/register.js"></script>
</body>
</html>