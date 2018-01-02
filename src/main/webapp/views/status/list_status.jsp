<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 17:00
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
    String statusPath = "/views/public/status_table.jsp";
    String []color = {"success","warning","info"};
    request.setAttribute("color",color);
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/status.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <c:if test="${idParam.userId != -1}">
            <c:set var="userH" value="${idParam.userId}同学"></c:set>
        </c:if>
        <c:if test="${idParam.problemId != -1}">
            <c:set var="problemH" value="第${idParam.problemId}题的"></c:set>
        </c:if>
        <c:if test="${idParam.contestId != -1}">
            <c:set var="contestH" value="在${idParam.contestId}竞赛中"></c:set>
        </c:if>
        <c:if test="${idParam.examId != -1}">
            <c:set var="examH" value="在${idParam.examId}考试中"></c:set>
        </c:if>
        <h1 class="text-center">
            ${userH}${contestH}${examH}${problemH}答题状态
        </h1>
        <hr>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th class="text-center">
                    ID
                </th>
                <th class="text-center">
                    <input type="checkbox" onchange="changeUserColumn(this, '${basePath}')" data-toggle="switch" name="default-switch" id="switch-user-id" />
                    <p id="th-user-id">用户 ID</p>
                </th>
                <th class="text-center">
                    <input type="checkbox" onchange="changeProblemColumn(this, '${basePath}')" data-toggle="switch" name="default-switch" id="switch-problem-id" />
                    <p id="th-problem-id">题目 ID</p>
                </th>
                <th class="text-center">
                    状态
                </th>
                <th class="text-center">
                    语言
                </th>
                <th class="text-center">
                    代码长度
                </th>
                <th class="text-center">
                    耗时
                </th>
                <th class="text-center">
                    内存
                </th>
                <th class="text-center">
                    提交时间
                </th>
            </tr>
            </thead>
            <tbody id="tbody-status">
            <c:forEach var="status" items="${statuses}"
                       varStatus="i" begin="${(pageNumber-1) * statusCountPerPage}"
                       end="${(pageNumber-1) * statusCountPerPage + (statusCountPerPage - 1)}" step="1">
                <tr class="${color[i.index mod 3]}">
                    <td class="text-center">${status.id}</td>
                    <td class="text-center">
                        <a href="${basePath}/user/user_info?userId=${status.idParam.userId}">${status.idParam.userId}</a>
                    </td>
                    <td class="text-center">
                        <a href="${basePath}/problem/show_problem?problemId=${status.idParam.problemId}">${status.idParam.problemId}</a>
                    </td>
                    <td class="text-center">${status.statusValue}</td>
                    <td class="text-center">${status.code.language}</td>
                    <td class="text-center">${status.code.length}B</td>
                    <td class="text-center">${status.time}ms</td>
                    <td class="text-center">${status.memory}KB</td>
                    <td class="text-center">${status.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="center-row">
            <ul class="pagination">
                <li id="page_number_foot_pre">
                    <a href="${basePath}/status/list_status?page=${pageNumber == 1 ? pageNumber : pageNumber - 1}${idParam.toPath()}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${basePath}/status/list_status?page=${i.index}${idParam.toPath()}">${i.index}</a>
                    </li>
                </c:forEach>
                <li id="page_number_foot_next">
                    <a href="${basePath}/status/list_status?page=${pageNumber == pageCount ? pageCount : pageNumber + 1}${idParam.toPath()}">Next</a>
                </li>
            </ul>
            <c:if test="${not empty isContestStatus && isContestStatus == true}">
                <button class="btn btn-embossed btn-primary" onclick="location.href = '${basePath}/contest/show_contest?page=1&contestId=${idParam.contestId}&note=${contestNote}';">
                    <em class="glyphicon glyphicon-arrow-left"></em>
                    返回竞赛题目
                </button>
            </c:if>
            <c:if test="${idParam.examId != -1}">
                <button class="btn btn-embossed btn-primary" onclick="">
                    <em class="glyphicon glyphicon-arrow-left"></em>
                    返回竞赛题目
                </button>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/status/status.js"></script>
</body>
</html>