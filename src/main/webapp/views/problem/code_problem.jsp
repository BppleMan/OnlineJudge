<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String headerPath = "/views/public/header.jsp";
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String footerPath = "/views/public/footer.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"/>
    <link href="${basePath}/css/problem/code_problem.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true"/>
<jsp:include page="<%=userModalPath%>" flush="true"/>
<div class="container">
    <div class="jumbotron">
        <h2 class="text-center">在下方编辑你的代码</h2>
        <div class="btn-group">
            <button class="btn btn-default active" type="button" onclick="buttonGroup(this);" value="c" name="c_cpp">C
            </button>
            <button class="btn btn-default" type="button" onclick="buttonGroup(this);" value="cpp" name="c_cpp">C++
            </button>
            <button class="btn btn-default" type="button" onclick="buttonGroup(this);" value="java" name="java">Java
            </button>
        </div>
        <div class="" id="compile-editor-div">
            <div id="editor" name="" class="form-control"></div>
        </div>
        <div class="btn-group btn-group-md col-md-offset-5">
            <button type="button"
                    id="submit_button"
                    class="btn btn-default"
                    onclick="submit_code(${empty user ? false : true},'${basePath}${submitPath}',
                            '${code_problem_token}', ${problemId}, ${contestId});">
                <em class="glyphicon glyphicon-pencil"></em>
                Submit
            </button>
            <button class="btn btn-default" type="button" onclick="window.history.back();">
                <em class="glyphicon glyphicon-repeat"></em>
                Back
            </button>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/problem/code_problem.js"></script>
<script src="${basePath}/js/problem/ace_editor.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ace.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-language_tools.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/ext-old_ie.js"></script>
<script src="${basePath}/lib/ace-builds/src-min-noconflict/theme-monokai.js"></script>
</body>
</html>