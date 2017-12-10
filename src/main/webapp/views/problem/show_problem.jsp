<%@ page import="com.bppleman.entity.Problem" %>
<%@ page import="java.util.List" %><%--
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
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String footerPath = "/views/public/footer.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/problem/show_problem.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <h3 class="text-center">
                    ${requestScope.problem.id}:${requestScope.problem.getTitle()}
                </h3>
                <span class="label label-primary">Description</span>
                <div class="form-control">
                    ${requestScope.problem.description}
                </div>
                <span class="label label-primary">Input</span>
                <div class="form-control">
                    ${requestScope.problem.input}
                </div>
                <span class="label label-primary">Output</span>
                <div class="form-control">
                    ${requestScope.problem.output}
                </div>
                <span class="label label-primary">Sample Input</span>
                <pre>${requestScope.problem.sampleInput}</pre>
                <span class="label label-primary">Sample Output</span>
                <pre>${requestScope.problem.sampleOutput}</pre>
                <span class="label label-primary">Hints</span>
                <pre>${requestScope.problem.hints}</pre>

                <span class="label label-primary">Author</span>
                <div class="form-control">
                    ${requestScope.problem.author}
                </div>
                <div class="btn-group btn-group-md col-md-offset-5">
                    <button class="btn btn-default" type="button"
                            onclick="document.location.href = '${basePath}${codePath}?problemId=${problem.id}'">
                        <em class="glyphicon glyphicon-pencil"></em>
                        Submit
                    </button>
                    <c:if test="${not empty contestId}">
                        <c:set var="contestPath">&contestId=${contestId}</c:set>
                    </c:if>
                    <button class="btn btn-default" type="button"
                            onclick="document.location.href = '${basePath}/status${statusPath}?1=1${contestPath}&problemId=${problem.id}'">
                        <em class="glyphicon glyphicon-list"></em>
                        Status
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/problem/show_problem.js"></script>
</body>
</html>