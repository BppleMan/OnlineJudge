<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/1
  Time: 17:01
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
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/contest/list_contest.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<%
    int pageNumber = (Integer) session.getAttribute("pageNumber");
    int pageCount = (Integer) session.getAttribute("pageCount");
%>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${requestScope.h1}</h1>
        <div class="search-button">
            <div class="input-group">
                <input id="keyWord" type="text" class="form-control">
                <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="search('${basePath}')">Search</button></span>
            </div>
        </div>
        <hr>
        <div class="pagination-control">
            <ul class="pagination">
                <li id="page_number_head_pre">
                    <a href="${basePath}/contest/contest_list/<%=pageNumber == 1 ? pageNumber : pageNumber - 1%>">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${basePath}/contest/contest_list/${i.index}">${i.index}</a>
                    </li>
                </c:forEach>
                <li id="page_number_head_next">
                    <a href="${basePath}/contest/contest_list/<%=pageNumber == pageCount ? pageCount : pageNumber + 1%>">Next</a>
                </li>
            </ul>
            <div class="page-button">
                <div class="input-group">
                    <input name="pageInputNumber" oninput="inputChange(this, ${pageCount})" type="number" class="form-control" min="1"
                           max="${pageCount}"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default go-button" type="button"
                                onclick="jumpToPage('${basePath}')">Go!</button>
                    </span>
                </div>
            </div>
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
            <c:forEach var="contest" items="${sessionScope.contests}" varStatus="i" begin="${(pageNumber-1) * countPerPage}"
                       end="${pageNumber * countPerPage - 1}" step="1">
                <tr class="${color[i.index mod 3]}">
                    <td class="text-center">${contest.id}</td>
                    <td class="text-center">
                        <div class="popover-options">
                            <a onclick="showContest('${basePath}', '${contest.author}', '${contest.type}', ${contest.id}, '${user.username}');">
                                    ${contest.name}
                            </a>
                        </div>
                    </td>
                    <td class="text-center">${contest.startTime}</td>
                    <td class="text-center">${contest.endTime}</td>
                    <td class="text-center">${contest.duration}</td>
                    <td class="text-center">${contest.status}</td>
                    <td class="text-center">${contest.type}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${contest.author == user.username}">
                                <a href="${basePath}/contest/edit_contest_problem?contestId=${contest.id}">编辑</a>
                                <a href="">删除</a>
                            </c:when>
                            <c:otherwise>
                                ${contest.author}
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
                    <a href="${basePath}/contest/contest_list/<%=pageNumber == 1 ? pageNumber : pageNumber - 1%>">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${basePath}/contest/contest_list/${i.index}">${i.index}</a>
                    </li>
                </c:forEach>
                <li id="page_number_foot_next">
                    <a href="${basePath}/contest/contest_list/<%=pageNumber == pageCount ? pageCount : pageNumber + 1%>">Next</a>
                </li>
            </ul>
            <div class="page-button">
                <div class="input-group">
                    <input name="pageInputNumber" type="number" class="form-control" min="1" max="${pageCount}"/>
                    <span class="input-group-btn">
                <button class="btn btn-default go-button" type="button"
                        onclick="jumpToPage('${basePath}')">Go!</button>
                </span>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/contest/list_contest.js"></script>
</body>
</html>