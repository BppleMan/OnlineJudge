<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
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
    <link href="${basePath}/css/problem/list_problem.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true"/>
<jsp:include page="<%=userModalPath%>" flush="true"/>
<%
    Map<String, String> typeButtonMap = new HashMap<>();
    typeButtonMap.put("id", "题目编号");
    typeButtonMap.put("title", "题目名称");
    typeButtonMap.put("author", "题目作者");
    typeButtonMap.put("label", "题型标签");
    request.setAttribute("typeButtonMap", typeButtonMap);
%>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Problems Set</h1>
        <div class="search-group">
            <div class="search-keyword">
                <%--
                检测type是否是label
                type为label时设置select
                否则设置为搜索栏
                --%>
                <c:choose>
                    <c:when test="${type == 'label'}">
                        <select name="labelTypeSelect" placeholder="选择条件" onchange="listenLabelSelect(this)" class="text-center form-control select select-inverse select-block mbl">
                            <option></option>
                            <c:forEach var="labelType" items="${label}">
                                <option>${labelType}</option>
                            </c:forEach>
                        </select>
                        <select name="labelValueSelect" placeholder="选择题型" onchange="listenLabelSelect(this)" class="text-center form-control select select-inverse select-block mbl">
                            <option></option>
                        </select>
                        <button type="button" class="btn btn-default btn-block" onclick="searchProblem()">搜索</button>
                        <input name="keyWord" type="hidden" class="form-control" value="${keyWord}">
                    </c:when>
                    <c:otherwise>
                        <div class="input-group">
                            <input name="keyWord" type="text" class="form-control" value="${keyWord}">
                            <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="searchProblem()">搜索</button></span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <%--search是后台传值，表示当前是否是搜索后得到的页面--%>
            <c:choose>
                <%--当search不为空且search为true时--%>
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
            <input type="hidden" name="type" id="type" value="${typeValue}">
            <div class="search-type">
                <div class="btn-group">
                    <%--这个选择器是用来选择搜索条件--%>
                    <select placeholder="选择条件" class="text-center form-control select select-inverse select-block mbl">
                        <option></option>
                        <c:forEach var="map" items="${typeMap}">
                            <option value="${map.key}" ${typeValue.equals(map.value)?'selected':''} >${map.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <hr>
        <div class="pagination-control">
            <c:set var="pagePath">
                ${basePath}/problem/list_problem?page=
            </c:set>
            <ul class="pagination">
                <li>
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
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
                        <a href="${basePath}/problem/show_problem?problemId=${problem.id}">
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
        <div class="pagination-control">
            <ul class="pagination">
                <li>
                    <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${pagePath}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${pagePath}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/problem/list_problem.js"></script>
<script>
    basePath = "${basePath}";
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