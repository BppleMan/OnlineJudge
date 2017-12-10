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
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/problem/create_problem.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Create New Problem</h1>
    </div>
    <div class="jumbotron create-problem">
        <form action="${basePath}/problem/create_problem/submit" method="post" onsubmit="return submit_new_problem();">
            <div class="title">
                <div class="input-group">
                    <span class="input-group-addon">题目名称</span>
                    <input type="text" class="form-control" name="title"/>
                </div>
            </div>
            <div class="description">
                <h1>题目描述</h1>
                <div id="des_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>题目输入</h1>
                <div id="input_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>题目输出</h1>
                <div id="output_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>样例输入</h1>
                <div id="sample_input_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>样例输出</h1>
                <div id="sample_output_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>题目提示</h1>
                <div id="hints_editor" name="description" class="form-control"></div>
            </div>
            <div class="">
                <h1>题型标签</h1>
                <div class="tagsinput-primary">
                    <input name="labels" class="tagsinput" data-role="tagsinput" value="School, Teacher, Colleague" />
                </div>
                <span class="help-block">请参照首页中的FAQ进行标签输入，错误的标签将无法添加!</span>
                <span class="help-block">每个标签以英文半角符逗号或回车隔开</span>
            </div>
            <div class="submit-button-row">
                <button type="submit" id="submit_button" class="btn btn-embossed btn-primary">
                    <em class="glyphicon glyphicon-pencil"></em>
                    提交
                </button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/problem/create_problem.js"></script>
<script src="${basePath}/js/problem/create_problem_ace_editor.js"></script>
<script src="${basePath}/lib/ace/src-min-noconflict/ace.js"></script>
<script src="${basePath}/lib/ace/src-min-noconflict/ext-language_tools.js"></script>
<script src="${basePath}/lib/ace/src-min-noconflict/ext-old_ie.js"></script>
<script src="${basePath}/lib/ace/src-min-noconflict/theme-monokai.js"></script>
</body>
</html>
