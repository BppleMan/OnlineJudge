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
        <div class="content" style="overflow: hidden">
            <div class="create-problem">
                <form action="${basePath}/bg/problem/edit_problem_data/submit" method="post" onsubmit="return submitProblemData()">
                    <div class="title">
                        <div class="input-group">
                            <span class="input-group-addon">题目ID</span>
                            <input type="text" readonly class="form-control" name="problemId">
                            <span class="input-group-addon">题目名称</span>
                            <input type="text" readonly class="form-control" name="title">
                            <span class="input-group-addon">题目作者</span>
                            <input type="text" readonly class="form-control" name="author">
                        </div>
                    </div>
                    <div class="">
                        <h4>输入数据</h4>
                        <div id="inputData" class="form-control"></div>
                    </div>
                    <div class="">
                        <h4>输出数据</h4>
                        <div id="outputData" class="form-control"></div>
                    </div>
                    <div style="height: 25px"></div>
                    <div class="center-row">
                        <button type="submit" class="btn btn-embossed btn-primary">
                            <em class="glyphicon glyphicon-pencil"></em>
                            提交
                        </button>
                    </div>
                    <input type="hidden" name="token" value="${editProblemDataToken}">
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/js/bg/sidebar.js"></script>
<script src="${basePath}/js/bg/problem/edit_problem_data.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ace.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-language_tools.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-old_ie.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/theme-monokai.js"></script>
<script>
    basePath = "${basePath}"
    setTextValue(${problem.id});
</script>
</body>
</html>
