<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/10
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 登陆模态框（Modal） -->
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="loginForm" role="form">
                <div class="login-form-row">
                    <h2 class="text-center">登录</h2>
                </div>
                <div class="login-form-row">
                    <div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input type="text" class="form-control" id="username"/>
                    </div>
                </div>
                <div class="login-form-row">
                    <div class="input-group">
                        <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                        <input type="password" class="form-control" id="password"/>
                    </div>
                </div>
                <div class="login-form-row">
                    <div class="input-group">
                        <div class="bootstrap-switch-square">
                            <input type="checkbox" data-toggle="switch"
                                   data-off-text="记住我"
                                   name="remember_me" id="remember_me" />
                        </div>
                    </div>
                </div>
                <div class="login-form-row center-row">
                    <button type="button" id="submit_button" class="btn btn-embossed btn-primary" onclick="login('${basePath}')">
                        登录
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>