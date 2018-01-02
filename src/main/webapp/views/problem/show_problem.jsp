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
                    ${problem.id}:${problem.getTitle()}
                </h3>
                <span class="label label-primary">Description</span>
                <div class="form-control">
                    ${problem.description}
                </div>
                <span class="label label-primary">Input</span>
                <div class="form-control">
                    ${problem.input}
                </div>
                <span class="label label-primary">Output</span>
                <div class="form-control">
                    ${problem.output}
                </div>
                <span class="label label-primary">Sample Input</span>
                <pre>${problem.sampleInput}</pre>
                <span class="label label-primary">Sample Output</span>
                <pre>${problem.sampleOutput}</pre>
                <span class="label label-primary">Hints</span>
                <pre>${problem.hints}</pre>

                <span class="label label-primary">Author</span>
                <div class="form-control">
                    ${problem.author}
                </div>
                <div style="height: 20px"></div>
                <c:choose>
                    <c:when test="${not empty isContestProblem && isContestProblem == true}">
                        <c:set var="urlParam">
                            ?problemId=${problem.id}&contestId=${contestId}
                        </c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="urlParam">
                            ?problemId=${problem.id}
                        </c:set>
                    </c:otherwise>
                </c:choose>
                <div class="center-row">
                    <button class="btn btn-default" type="button"
                            onclick="document.location.href = '${basePath}${codeProblemPath}${urlParam}'">
                        <em class="glyphicon glyphicon-pencil"></em>
                        编辑代码
                    </button>
                    <button class="btn btn-default" type="button"
                            onclick="document.location.href = '${basePath}/status/list_status${urlParam}&page=1'">
                        <em class="glyphicon glyphicon-list"></em>
                        答题状态
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