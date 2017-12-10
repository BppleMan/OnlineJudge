<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bppleman.entity.Contest" %>
<%@ page import="com.bppleman.entity.Problem" %><%--
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
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String footerPath = "/views/public/footer.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"/>
    <link href="${basePath}/css/contest/edit_contest_problem.css" rel="stylesheet">
    <script src="${basePath}/js/contest/edit_contest_problem.js"></script>
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true"/>
<jsp:include page="<%=userModalPath%>" flush="true"/>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">选择你的题目</h1>
        <input type="checkbox" data-toggle="switch" data-on-text="已选" data-off-text="已选" id="custom-switch-01" onchange="changeSidebar(this)"/>
        <hr>
        <div class="container-with-sidebar">
            <div id="wrapper" class="page-sidebar">
                <!-- Sidebar -->
                <nav class="navbar navbar-inverse" id="sidebar-wrapper" role="navigation">
                    <ul class="nav sidebar-nav">
                        <li class="sidebar-brand">
                            <a href="#">已选题目列表</a>
                        </li>
                    </ul>
                </nav>
            </div><!--
         --><div id="wrapper-right"  class="sidebar-right">
            <form action="${basePath}/contest/submit_contest_problem" method="post" onsubmit="return canSubmit();">
                <div class="tabbable" id="tabs-244441">
                    <ul class="nav nav-tabs">
                        <c:forEach var="" items="${types}" varStatus="i">
                            <li class="${i.index == 0 ? 'active' : ''}">
                                <a href="#panel-${i.index}" data-toggle="tab">${}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="tab-content">
                        <c:forEach var="" items="${types}" varStatus="i">
                            <div class="tab-pane ${i.index == 0 ? 'active' : ''}" id="panel-${i.index}">
                                <div class="panel-group" id="accordion-${i.index}">
                                    <c:forEach var="label"
                                               items="${labelsMap[types[i.index]]}"
                                               varStatus="j">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-${i.index}"
                                                       href="#collapse-${i.index}-${j.index}"
                                                       onclick="loadProblems(${i.index}, ${j.index},
                                                               '${}','${label.name}',
                                                               '${basePath}')">
                                                            ${label.name}
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse-${i.index}-${j.index}"
                                                 class="panel-collapse collapse">
                                                    <%--这里在今后插入problem的名称--%>
                                                <div class="panel-body">
                                                    <table id="problems-table-${i.index}-${j.index}"
                                                           class="table table-striped table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th class="text-center">
                                                                Problem ID
                                                            </th>
                                                            <th class="text-center">
                                                                Problem Name
                                                            </th>
                                                            <th class="text-center">
                                                                Status
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <input type="hidden" name="userId" value="${userId}">
                <input type="hidden" name="contestId" value="${contest.id}">
                <input type="hidden" name="token" value="${contest_problem_token}">
                <div class="submit-button-row">
                    <button type="submit" id="submit_button" class="btn btn-embossed btn-primary">
                        <em class="glyphicon glyphicon-pencil"></em>
                        提交
                    </button>
                </div>
            </form>
        </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<%
    List<Integer> problemIds = new ArrayList<>();
    Contest contest = (Contest) request.getAttribute("contest");
    for (Problem problem : contest.getProblems()) {
        problemIds.add(problem.getId());
    }
    request.setAttribute("problemIds", problemIds);
%>
<script>
    loadSeletedProblem('${basePath}', ${contest.id});
</script>
</body>
</html>
