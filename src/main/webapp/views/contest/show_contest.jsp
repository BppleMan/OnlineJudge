<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th class="text-center">
                    题目编号
                </th>
                <th class="text-center">
                    题目名称
                </th>
                <th class="text-center">
                    创建时间
                </th>
                <th class="text-center">
                    答题状态
                </th>
            </tr>
            </thead>
            <tbody id="problems_table_body">
            <c:forEach var="problem" items="${contest.problems}" varStatus="i">
                <tr class="${color[i.index mod 3]}">
                    <td class="text-center">
                            ${problem.id}
                    </td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/contest/show_problem?problemId=${problem.id}">
                                ${problem.title}
                        </a>
                    </td>
                    <td class="text-center">
                            ${problem.createTime}
                    </td>
                    <td class="text-center">
                        <c:if test="${userSolveMap[problem.id].equals('solved')}">
                            <span class="fui-check"></span>
                        </c:if>
                        <c:if test="${userSolveMap[problem.id].equals('un_solved')}">
                            <span class="fui-cross"></span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="submit-button-row">
            <button class="btn btn-embossed btn-primary" onclick="listStatus('${basePath}', ${contest.id})">
                <em class="glyphicon glyphicon-pencil"></em>
                答题状态
            </button>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script>
    function listStatus(contextPath, contestId) {
        location.href = contextPath + "/status/list_status/1?contestId=" + contestId;
    }
</script>
</body>
</html>