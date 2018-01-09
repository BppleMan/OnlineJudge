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
                        ${basePath}/bg/problem/list_problem?page=
                    </c:set>
                    <ul class="pagination">
                        <li>
                            <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                        </li>
                        <c:if test="${begin != 1}">
                            <li class="page_number_li">
                                <a href="${pagePath}${begin-1}${searchParam}">...</a>
                            </li>
                        </c:if>
                        <c:forEach var="item" varStatus="i" begin="${begin}" end="${end}" step="1">
                            <li class="page_number_li ${i.index==pageNumber?"active":""}">
                                <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${end < pageCount}">
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
                            <%--search是后台传值，表示当前是否是搜索后得到的页面--%>
                            <c:choose>
                                <%--当search不为空且search为true时--%>
                                <%--对typeValue进行初始化--%>
                                <c:when test="${not empty search and search}">
                                    <c:set var="typeValue" value="${type}"></c:set>
                                    <c:set var="searchParam">
                                        &tp=${type}&kw=${keyWord}
                                    </c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="typeValue" value="title"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <%--这个选择器是用来选择搜索条件--%>
                            <select placeholder="选择条件" class="text-center form-control select select-primary select-block mbl">
                                <option></option>
                                <c:forEach var="map" items="${typeMap}">
                                    <option value="${map.key}" ${typeValue.equals(map.value)?'selected':''} >${map.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <%--
                        检测type是否是label
                        type为label时设置select
                        否则设置为搜索栏
                        --%>
                        <c:choose>
                            <c:when test="${type == 'label'}">
                                <div id="should-change">
                                    <div class="search-item">
                                        <select name="labelTypeSelect" placeholder="选择条件" onchange="listenLabelSelect(this)" class="text-center form-control select select-primary select-block mbl">
                                            <option></option>
                                            <c:forEach var="labelType" items="${label}">
                                                <option>${labelType}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="search-item">
                                        <select name="labelValueSelect" placeholder="选择题型" onchange="listenLabelSelect(this)" class="text-center form-control select select-primary select-block mbl">
                                            <option></option>
                                        </select>
                                    </div>
                                    <input name="keyWord" type="hidden" class="form-control" value="${keyWord}">
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div id="should-change">
                                    <div class="search-item">
                                        <input name="keyWord" type="text" class="form-control" value="${keyWord}">
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="search-item">
                            <button class="btn btn-primary btn-wide" type="button" onclick="searchProblem()">搜索</button>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="type" id="type" value="${typeValue}">
            </div>
            <table class="table table-hover table-striped">
                <thead style="background-color: #eee">
                <tr>
                    <th class="text-center">编号</th>
                    <th class="text-center">名称</th>
                    <th class="text-center">创建日期</th>
                    <th class="text-center">作者</th>
                    <th class="text-center">(ac/total)通过率</th>
                    <th class="text-center">题目数据</th>
                </tr>
                </thead>
                <tbody id="problems_table_body">
                <c:forEach var="problem" items="${problems}" varStatus="i">
                    <tr class="${color[i.index mod 3]}">
                        <td class="text-center">
                                ${problem.id}
                        </td>
                        <td class="text-center">
                            <a href="${basePath}/bg/problem/edit_problem?problemId=${problem.id}">
                                    ${problem.title}
                            </a>
                        </td>
                        <td class="text-center">
                                ${problem.createTime}
                        </td>
                        <td class="text-center">
                                ${problem.author}
                        </td>
                        <td class="text-center">
                            (<span class="text-success">${problem.problemRatio.acTime}</span>/
                            <span class="text-danger">${problem.problemRatio.submitTime}</span>)
                            <span class="text-primary">${problem.problemRatio.ratioValue}%</span>
                        </td>
                        <td class="text-center">
                            <a href="${basePath}/bg/problem/edit_problem_data?problemId=${problem.id}">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true"></jsp:include>
<script src="${basePath}/js/bg/problem/list_problem.js"></script>
<script src="${basePath}/js/bg/sidebar.js"></script>
<script>
    basePath = "${basePath}"
    labels = {
    <c:forEach var="label" items="${labels}" varStatus="i">
    ${label.type}:new Array(
        <c:forEach var="value" items="${label.values}" varStatus="j">"${value}"<c:if test="${j.index != label.values.size() - 1}">,</c:if></c:forEach>
    )<c:if test="${i.index != labels.size() - 1}">,</c:if>
    </c:forEach>
    }
</script>
</body>
</html>
