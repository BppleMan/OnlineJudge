<%@ page import="com.bppleman.enumration.UserChangeType" %>
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
    String sidebar = "/views/user/information/sidebar.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/user/sidebar.css" rel="stylesheet">
    <link href="${basePath}/css/user/main_info.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container information">
    <div class="row clearfix">
        <jsp:include page="<%=sidebar%>" flush="true"></jsp:include>
        <div class="content col-md-10 column">
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="username2" class="col-lg-2 control-label">用户名</label>
                    <div class="col-lg-10">
                        <input type="email" class="form-control" id="username2" value="${user.username}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label for="nickname" class="col-lg-2 control-label">昵称</label>
                    <div class="col-lg-8">
                        <input type="email" class="form-control" id="nickname" placeholder="输入昵称" value="${user.nickname}">
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="form-control" onclick="changeNickname()">修改昵称</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-lg-2 control-label">电子邮箱</label>
                    <div class="col-lg-8">
                        <input type="email" class="form-control" id="email" placeholder="输入邮箱" value="${user.email}">
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="form-control" onclick="changeEmail()">换绑邮箱</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-lg-2 control-label">手机号码</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="telephone" placeholder="输入手机" value="${user.telephone}">
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="form-control" onclick="changeTelephone()">换绑手机</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="oldPassword" class="col-lg-2 control-label">原密码</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="oldPassword" placeholder="输入原密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="newPassword" class="col-lg-2 control-label">新密码</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="newPassword" placeholder="输入新密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="newPasswordRepeat" class="col-lg-2 control-label">确认新密码</label>
                    <div class="col-lg-8">
                        <input type="password" class="form-control" id="newPasswordRepeat" placeholder="确认新密码" oninput="inputValidateNewPassword(this)">
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="form-control" onclick="changePassword()">修改密码</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-2"></div>
                    <div id="alert" class="col-lg-8 alert alert-danger alert-dismissable">
                        <button type="button" class="close" aria-hidden="true" onclick="closeAlert()">
                            &times;
                        </button>
                        <span>成功！很好地完成了提交。</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/user/sidebar.js"></script>
<script src="${basePath}/js/user/main_info.js"></script>
<script>
    basePath = "${basePath}"
    USERNAME = "<%=UserChangeType.USERNAME%>"
    PASSWORD = "<%=UserChangeType.PASSWORD%>"
    EMAIL = "<%=UserChangeType.EMAIL%>"
    TELEPHONE = "<%=UserChangeType.TELEPHONE%>"
    NICKNAME = "<%=UserChangeType.NICKNAME%>"
</script>
</body>
</html>
