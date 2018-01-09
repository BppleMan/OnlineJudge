<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
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
    <link href="${basePath}/css/contest/list_contest.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${requestScope.h1}</h1>
        <div class="search-group">
            <div class="search-keyword">
                <%--
                检测type是否是label
                type为label时设置select
                否则设置为搜索栏
                --%>
                <div class="input-group">
                    <input name="keyWord" type="text" class="form-control" value="${keyWord}">
                    <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="searchContest()">搜索</button></span>
                </div>
            </div>
            <%--search是后台传值，表示当前是否是搜索后得到的页面--%>
            <input type="hidden" name="type" id="type" value="${type}">
            <div class="search-type">
                <div class="btn-group">
                    <%--这个选择器是用来选择搜索条件--%>
                    <select placeholder="选择条件">
                        <option></option>
                        <c:forEach var="map" items="${typeMap}">
                            <option value="${map.key}" ${type.equals(map.value)?'selected':''}>${map.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <hr>
        <div class="pagination-control">
            <c:set var="pagePath">
                ${basePath}/contest/list_contest?page=
            </c:set>
            <c:set var="searchParam">
                &tp=${type}&kw=${keyWord}
            </c:set>
            <ul class="pagination">
                <li id="page_number_head_pre">
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li id="page_number_head_next">
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
                </li>
            </ul>
        </div>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th class="text-center">竞赛编号</th>
                <th class="text-center">竞赛名称</th>
                <th class="text-center">开始时间</th>
                <th class="text-center">结束时间</th>
                <th class="text-center">持续时间</th>
                <th class="text-center">竞赛状态</th>
                <th class="text-center">竞赛类型</th>
                <th class="text-center">竞赛作者</th>
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
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${contest.username == user.username}">
                                <a href="${basePath}/contest/edit_contest_problem?contestId=${contest.id}&cpage=1">编辑</a>
                            </c:when>
                            <c:otherwise>
                                ${contest.username}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination-control">
            <ul class="pagination">
                <li id="page_number_foot_pre">
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li id="page_number_foot_next">
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/js/contest/list_contest.js"></script>
<script>
    basePath = "${basePath}";
</script>
</body>
</html>