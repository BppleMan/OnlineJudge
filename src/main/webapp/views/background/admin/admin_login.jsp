<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 16:56
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
    <jsp:include page="<%=headerPath%>" flush="true"></jsp:include>
    <link href="">
    <link href="${basePath}/css/background/admin/admin_login.css" rel="stylesheet">
</head>
<body>
<%--<jsp:include page="<%=navigationBarPath%>" flush="true"></jsp:include>--%>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">欢迎登录评测系统后台</h1>
        <div class="login-form">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-2 control-label">管理员账号</label>
                    <div class="col-lg-10">
                        <div class="form-group">
                            <select class="selectpicker" data-style="btn-primary">
                                <option value="0">My Profile here is</option>
                                <option value="1">My Friends</option>
                                <option value="2">My Profile</option>
                                <option value="3">My Dear Friends</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-lg-2 control-label">管理员密码</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="password" name="password" placeholder="输入管理员密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <label class="checkbox" for="checkbox3">
                            <input type="checkbox" data-toggle="checkbox" value="" id="checkbox3" name="remember" required>
                            记住我？
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button id="login_button" type="submit" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
</body>
</html>
