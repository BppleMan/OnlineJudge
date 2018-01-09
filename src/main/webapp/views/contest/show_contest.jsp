<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%
    String headerPath = "/views/public/header.jsp";
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String footerPath = "/views/public/footer.jsp";
    String[] color = {"success", "warning", "info"};
    request.setAttribute("color", color);
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"/>
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true"/>
<jsp:include page="<%=userModalPath%>" flush="true"/>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Problems Set</h1>
        <hr>
        <div class="pagination-control">
            <c:set var="pagePath">
                ${basePath}/contest/show_contest?page=
            </c:set>
            <c:set var="urlParam">
                &contestId=${contestId}
            </c:set>
            <ul class="pagination">
                <li>
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${urlParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li${pageNumber.equals(i.index)?' active':''}">
                        <a href="${pagePath}${i.index}${urlParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${urlParam}">Next</a>
                </li>
            </ul>
        </div>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th class="text-center">编号</th>
                <th class="text-center">名称</th>
                <th class="text-center">创建日期</th>
                <th class="text-center">作者</th>
                <th class="text-center">(ac/total)通过率</th>
                <th class="text-center">状态</th>
            </tr>
            </thead>
            <tbody id="problems_table_body">
            <c:forEach var="problem" items="${problems}" varStatus="i">
                <tr class="${color[i.index mod 3]}">
                    <td class="text-center">
                            ${problem.id}
                    </td>
                    <td class="text-center">
                        <a href="${basePath}/contest/show_problem?contestId=${contestId}&problemId=${problem.id}">
                                ${problem.title}
                        </a>
                    </td>
                    <td class="text-center">${problem.createTime}</td>
                    <td class="text-center">${problem.author}</td>
                    <td class="text-center">
                        (${contestProblemRatiosMap[problem.id].acTime}/${contestProblemRatiosMap[problem.id].submitTime})${contestProblemRatiosMap[problem.id].ratioValue}
                    </td>
                    <td class="text-center">
                        <c:if test="${contestProblemUserSolveMap[problem.id].equals('solved')}">
                            <span class="fui-check"></span>
                        </c:if>
                        <c:if test="${contestProblemUserSolveMap[problem.id].equals('un_solved')}">
                            <span class="fui-cross"></span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination-control">
            <ul class="pagination">
                <li>
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${urlParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li${pageNumber.equals(i.index)?' active':''}">
                        <a href="${pagePath}${i.index}${urlParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${urlParam}">Next</a>
                </li>
            </ul>
        </div>
        <div class="center-row">
            <button class="btn btn-embossed btn-primary" onclick="listStatus()">
                <em class="glyphicon glyphicon-pencil"></em>
                答题状态
            </button>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script>
    function listStatus() {
        location.href = "${basePath}/status/list_contest_status?page=1&contestId=${contestId}&contestNote=${note}";
    }
</script>
</body>
</html>