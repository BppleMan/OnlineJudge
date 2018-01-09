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
    String sidebarPath = "/views/bg/sidebar.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"></jsp:include>
    <link href="${basePath}/css/bg/main_page.css" rel="stylesheet">
    <link href="${basePath}/css/bg/sidebar.css" rel="stylesheet">
    <link href="${basePath}/css/bg/problem/edit_problem.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">闽南师范大学OJ后台系统</h1>
    </div>
    <div class="background">
        <jsp:include page="<%=sidebarPath%>" flush="true"></jsp:include>
        <div class="content">
            <div class="create-problem">
                <c:choose>
                    <c:when test="${createProblem && createProblem == true}">
                        <c:set var="token" value="${createProblemToken}"></c:set>
                        <c:set var="action">${basePath}/bg/problem/create_problem/submit</c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="token" value="${editProblemToken}"></c:set>
                        <c:set var="action">${basePath}/bg/problem/edit_problem/submit</c:set>
                    </c:otherwise>
                </c:choose>
                <form action="${action}" method="post" onsubmit="return submitProblemData();">
                    <input name="id" type="hidden" value="${problem.id}">
                    <input type="hidden" name="token" value="${token}">
                    <div class="title">
                        <div class="input-group">
                            <span class="input-group-addon">题目名称</span>
                            <input type="text" class="form-control" name="title">
                            <span class="input-group-addon">题目作者</span>
                            <input type="text" class="form-control" name="author">
                        </div>
                    </div>
                    <div class="">
                        <h4>题目描述</h4>
                        <div id="des_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>题目输入</h4>
                        <div id="input_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>题目输出</h4>
                        <div id="output_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>样例输入</h4>
                        <div id="sample_input_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>样例输出</h4>
                        <div id="sample_output_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>题目提示</h4>
                        <div id="hints_editor" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>题型标签</h4>
                        <select data-toggle="select" placeholder="选择题型" id="type"
                                class="form-control select select-primary mrs mbm">
                            <option></option>
                            <c:forEach var="type" items="${labels}">
                                <option value="${type.type}">${type.type}</option>
                            </c:forEach>
                        </select>
                        <select data-toggle="select" placeholder="选择标签" id="label"
                                class="form-control select select-primary mrs mbm">
                            <option></option>
                        </select>
                        <button type="button" class="btn btn-default btn-primary" onclick="addLabel()">添加标签</button>
                        <div class="tagsinput-primary">
                            <input name="labels" class="tagsinput" data-role="tagsinput" value="">
                        </div>
                    </div>
                    <div class="center-row">
                        <button type="submit" class="btn btn-embossed btn-primary">
                            <em class="glyphicon glyphicon-pencil"></em>
                            提交
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/js/bg/sidebar.js"></script>
<script src="${basePath}/js/bg/problem/edit_problem.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ace.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-language_tools.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-old_ie.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/theme-monokai.js"></script>
<script>
    basePath = "${basePath}"
    labels = {
    <c:forEach var="label" items="${labels}" varStatus="i">
    ${label.type}:new Array(
        <c:forEach var="value" items="${label.values}" varStatus="j">"${value}"<c:if test="${j.index != label.values.size() - 1}">,</c:if></c:forEach>
    )<c:if test="${i.index != labels.size() - 1}">,</c:if>
    </c:forEach>
    }
    <c:if test="${not empty problem}">
        setTextValue(${problem.id})
    </c:if>
</script>
</body>
</html>
