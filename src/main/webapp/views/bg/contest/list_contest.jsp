<%@ page import="com.bppleman.entity.Contest" %><%--
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
    String[] color = {"success", "warning", "info"};
    request.setAttribute("color", color);
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true"></jsp:include>
    <link href="${basePath}/css/bg/problem/list_problem.css" rel="stylesheet">
    <link href="${basePath}/css/bg/main_page.css" rel="stylesheet">
    <link href="${basePath}/css/bg/sidebar.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">闽南师范大学OJ后台系统</h1>
    </div>
    <div class="background">
        <jsp:include page="<%=sidebarPath%>" flush="true"></jsp:include>
        <div class="content">
            <div class="hover-top">
                <div class="pagination-control">
                    <c:set var="pagePath">
                        ${basePath}/bg/contest/list_contest?page=
                    </c:set>
                    <ul class="pagination">
                        <li>
                            <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                        </li>
                        <c:if test="${not empty begin && begin != 1}">
                            <li class="page_number_li">
                                <a href="${pagePath}${begin-1}${searchParam}">...</a>
                            </li>
                        </c:if>
                        <c:forEach var="item" varStatus="i" begin="${begin}" end="${end}" step="1">
                            <li class="page_number_li ${i.index==pageNumber?"active":""}">
                                <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${not empty end && end < pageCount}">
                            <li class="page_number_li">
                                <a href="${pagePath}${end+1}${searchParam}">...</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
                        </li>
                    </ul>
                </div>
                <div class="pagination-right">
                    <div class="search-group">
                        <div class="search-item">
                            <select id="condition" placeholder="选择条件" class="text-center form-control select select-primary select-block mbl">
                                <option></option>
                                <c:forEach var="map" items="${typeMap}">
                                    <option value="${map.key}" ${type.equals(map.value)?'selected':''}>${map.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="keyWordItem" class="search-item">
                            <input id="KeyWord" type="text" class="form-control" value="${keyWord}">
                        </div>
                        <div class="search-item">
                            <button class="btn btn-default" type="button" onclick="searchContest()">搜索</button>
                        </div>
                    </div>
                    <%--search是后台传值，表示当前是否是搜索后得到的页面--%>
                    <input type="hidden" name="type" id="type" value="${type}">
                </div>
            </div>
            <table class="table table-hover table-striped">
                <thead style="background-color: #eee">
                <tr>
                    <th class="text-center">竞赛编号</th>
                    <th class="text-center">竞赛名称</th>
                    <th class="text-center">开始时间</th>
                    <th class="text-center">结束时间</th>
                    <th class="text-center">持续时间</th>
                    <th class="text-center">竞赛状态</th>
                    <th class="text-center">竞赛类型</th>
                    <th class="text-center">竞赛作者</th>
                    <th class="text-center">编辑竞赛</th>
                </tr>
                </thead>
                <tbody id="problems_table_body">
                <c:forEach var="contest" items="${contests}" varStatus="i">
                    <tr class="${color[i.index mod 3]}">
                        <td class="text-center">${contest.id}</td>
                        <td class="text-center">
                            <div class="popover-options">
                                <a onclick="showContest('${contest.id}');">
                                        ${contest.name}
                                </a>
                            </div>
                        </td>
                        <td class="text-center">${contest.startTime}</td>
                        <td class="text-center">${contest.endTime}</td>
                        <td class="text-center">${contest.day}天${contest.hour}时${contest.minute}分${contest.second}秒</td>
                        <td class="text-center">${contest.status}</td>
                        <td class="text-center">${contest.type}</td>
                        <td class="text-center">${contest.username}</td>
                        <td class="text-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${basePath}/bg/contest/edit_contest/date?contestId=${contest.id}'">编辑时间</button>
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${basePath}/bg/contest/edit_contest/content?contestId=${contest.id}'">编辑内容</button>
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${basePath}/bg/contest/delete_contest?contestId=${contest.id}'">删除竞赛</button>
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${basePath}/bg/contest/clone_contest?contestId=${contest.id}'">克隆竞赛</button>
                            </div>
                        </td>
                        <%--<td class="text-center"><a href="${basePath}/bg/contest/edit_contest?contestId=${contest.id}">编辑</a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/js/bg/contest/list_contest.js"></script>
<script src="${basePath}/js/bg/sidebar.js"></script>
<script>
    var basePath = "${basePath}"
    <%
    String types[] = {Contest.Type.PASSWORD, Contest.Type.PUBLIC, Contest.Type.CLASS};
    String statuses[] = {Contest.Status.RUNNING, Contest.Status.READY, Contest.Status.END};
    request.setAttribute("typeList", types);
    request.setAttribute("statusList", statuses);
    %>
    var types = new Array(
        <c:forEach var="type" items="${typeList}" varStatus="i">
        <c:if test="${i.index != 0}">,</c:if>"${type}"
        </c:forEach>
    )

    var statuses = new Array(
        <c:forEach var="status" items="${statusList}" varStatus="i">
        <c:if test="${i.index != 0}">,</c:if>"${status}"
        </c:forEach>
    )
</script>
</body>
</html>
