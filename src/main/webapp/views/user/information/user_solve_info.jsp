<%@ page import="com.bppleman.enumration.UserChangeType" %>
<%@ page import="com.bppleman.entity.UserSolve" %>
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
    <link href="${basePath}/css/user/user_solve_info.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container information">
    <div class="row clearfix">
        <jsp:include page="<%=sidebar%>" flush="true"></jsp:include>
        <div class="content col-md-10 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">
                        已解决的题目列表
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                        <tr class="active">
                            <th class="text-center">题目ID</th>
                            <th class="text-center">名称</th>
                            <th class="text-center">AC次数/提交次数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="solved" value="<%=UserSolve.SOLVED%>"></c:set>
                        <c:forEach var="problem" items="${userSolveProblemsMap[solved]}">
                            <tr class="success">
                                <td class="text-center">${problem.id}</td>
                                <td class="text-center"><a href="${basePath}/problem/show_problem?problemId=${problem.id}">${problem.title}</a></td>
                                <td class="text-center">${problemACTimesMap[problem.id]}/${problemSubmittedTimesMap[problem.id]}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${userSolveProblemsMap[solved].size() + 1}" end="${countPerPage}">
                            <tr class="success">
                                <td class="text-center">&nbsp;</td>
                                <td class="text-center">&nbsp;</td>
                                <td class="text-center">&nbsp;</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination-control center-row">
                        <c:set var="path">
                            ${basePath}/user/information/user_solve_info?rt=${requestTime}&tp=${solved}&page=
                        </c:set>
                        <c:set var="pageNumber" value="${userSolveCurrentPageMap[solved]}"></c:set>
                        <ul class="pagination">
                            <li>
                                <a href="${path}${pageNumber == 1 ? pageNumber : pageNumber - 1}">Prev</a>
                            </li>
                            <c:forEach var="item" varStatus="i" begin="1" end="${userSolveTotalPageMap[solved]}" step="1">
                                <li class="page_number_li ${i.index==userSolveCurrentPageMap[solved]?'active':''}">
                                    <a href="${path}${i.index}">${i.index}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a href="${path}${pageNumber == userSolveTotalPageMap[solved] ? userSolveTotalPageMap[solved] : pageNumber + 1}">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">
                        提交未AC题目列表
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                        <tr class="active">
                            <th class="text-center">题目ID</th>
                            <th class="text-center">名称</th>
                            <th class="text-center">提交次数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="solved" value="<%=UserSolve.UN_SOLVED%>"></c:set>
                        <c:forEach var="problem" items="${userSolveProblemsMap[solved]}">
                            <tr class="danger">
                                <td class="text-center">${problem.id}</td>
                                <td class="text-center"><a href="${basePath}/problem/show_problem?problemId=${problem.id}">${problem.title}</a></td>
                                <td class="text-center">${problemSubmittedTimesMap[problem.id]}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${userSolveProblemsMap[solved].size() + 1}" end="${countPerPage}">
                            <tr class="danger">
                                <td class="text-center">&nbsp;</td>
                                <td class="text-center">&nbsp;</td>
                                <td class="text-center">&nbsp;</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination-control center-row">
                        <c:set var="path">
                            ${basePath}/user/information/user_solve_info?rt=${requestTime}&tp=${solved}&page=
                        </c:set>
                        <c:set var="pageNumber" value="${userSolveCurrentPageMap[solved]}"></c:set>
                        <ul class="pagination">
                            <li>
                                <a href="${path}${pageNumber == 1 ? pageNumber : pageNumber - 1}">Prev</a>
                            </li>
                            <c:forEach var="item" varStatus="i" begin="1" end="${userSolveTotalPageMap[solved]}" step="1">
                                <li class="page_number_li ${i.index==userSolveCurrentPageMap[solved]?'active':''}">
                                    <a href="${path}${i.index}">${i.index}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a href="${path}${pageNumber == userSolveTotalPageMap[solved] ? userSolveTotalPageMap[solved] : pageNumber + 1}">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/user/sidebar.js"></script>
<script>

</script>
</body>
</html>
