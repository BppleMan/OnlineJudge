<%@ page import="com.bppleman.entity.Contest" %><%--
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
    String sidebarPath = "/views/bg/sidebar.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"></jsp:include>
    <link href="${basePath}/css/bg/main_page.css" rel="stylesheet">
    <link href="${basePath}/css/bg/sidebar.css" rel="stylesheet">
    <link href="${basePath}/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/bg/contest/edit_contest/date.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">闽南师范大学OJ后台系统</h1>
    </div>
    <div class="background">
        <jsp:include page="<%=sidebarPath%>" flush="true"></jsp:include>
        <div class="content">
            <div class="container">
                <div class="jumbotron">
                    <h1 class="text-center">修改时间</h1>
                </div>
                <div class="form-horizontal">
                    <form id="contestForm" action="${basePath}/bg/contest/edit_contest/date/submit" method="post" onsubmit="return formValidate(this)">
                        <div class="form-group">
                            <label for="name" class="col-lg-2 control-label">竞赛名称</label>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name" name="name" value="${contest.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startTime" class="col-lg-2 control-label">开始时间</label>
                            <div class="col-lg-8">
                                <div class="input-group input-append date form_datetime" id="startTime" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-mm-dd HH:ii:ss" data-link-field="dtp_input1">
                                    <input class="form-control" size="20" type="text" name="startTime" value="${contest.startTime}" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">持续时间</label>
                            <div class="col-lg-2">
                                <div class="input-group">
                                    <input class="form-control" name="day" type="number" min="0" value="${not empty contest?contest.day:"0"}">
                                    <span class="input-group-addon">天</span>
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <div class="input-group">
                                    <input class="form-control" name="hour" type="number" min="0" max="24" value="${not empty contest?contest.hour:"0"}">
                                    <span class="input-group-addon">小时</span>
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <div class="input-group">
                                    <input class="form-control" name="minute" type="number" min="0" max="60" value="${not empty contest?contest.minute:"0"}">
                                    <span class="input-group-addon">分</span>
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <div class="input-group">
                                    <input class="form-control" name="second" type="number" min="0" max="60" value="${not empty contest?contest.second:"0"}">
                                    <span class="input-group-addon">秒</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">竞赛类型</label>5er
                            <div class="col-lg-4">
                                <select placeholder="选择类型" id="type" data-toggle="select" class="form-control select select-primary mrs mbm">
                                    <option></option>
                                    <c:forEach var="type" items="${typesMap}">
                                        <option value="${type.key}" ${type.key.equals(contest.type)?"selected":""}>${type.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%
                                Contest contest = (Contest) request.getAttribute("contest");
                                if (contest == null)
                                    contest = (Contest) session.getAttribute("contest");
                            %>
                            <c:if test="<%=contest!=null && contest.getType().equals(Contest.Type.PASSWORD)%>">
                                <div class="col-lg-4">
                                    <input type="password" name="password" class="form-control" value="******">
                                </div>
                            </c:if>
                        </div>
                        <div class="center-row">
                            <button class="btn btn-default btn-primary col-lg-4">修改完毕提交</button>
                        </div>
                        <input type="hidden" name="contestId" value="${contest.id}">
                    </form>
                    <div class="center-row">
                        <button class="btn btn-default btn-primary col-lg-4" onclick="submitAndContinue()">提交时间修改并继续修改其它内容</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${basePath}/lib/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${basePath}/js/bg/contest/edit_contest/date.js"></script>
</body>
</html>
