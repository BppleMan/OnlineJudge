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
                <c:choose>
                    <c:when test="${type == 'label'}">
                        <div class="tagsinput-primary">
                            <input name="keyWord" class="tagsinput" data-role="tagsinput" value="${keyWord}" />
                        </div>
                        <span class="help-block">每输入一个标签按下回车键或英文半角符逗号以确定</span>
                        <div class="input-group">
                            <button class="btn btn-default" type="button" onclick="searchProblem('${basePath}')">搜索</button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="input-group">
                            <input name="keyWord" type="text" class="form-control" value="${keyWord}">
                            <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="searchProblem('${basePath}')">搜索</button></span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:choose>
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
            <input type="hidden" name="" id="" value="${typeValue}">
            <div class="search-type">
                <div class="btn-group">
                    <button id="type-button" data-toggle="dropdown" type="button" class="btn btn-default dropdown-toggle">${typeButtonMap[typeValue]} <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu">
                        <c:forEach var="map" items="${typeMap}">
                            <li><a onclick="selectType('${map.key}', '${map.value}', '${basePath}')">${map.value}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <hr>
        <div class="pagination-control">
            <c:set var="path">
                ${basePath}/problem/list_problem?page=
            </c:set>
            <ul class="pagination">
                <li>
                    <a href="${path}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${path}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${path}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
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
                        (${problemRatioMap[problem.id].acTime}/${problemRatioMap[problem.id].submitTime})${problemRatioMap[problem.id].ratioValue}
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
                    <a href="${path}${pageNumber == 1 ? pageNumber : pageNumber - 1}${searchParam}">Prev</a>
                </li>
                <c:forEach var="item" varStatus="i" begin="1" end="${pageCount}" step="1">
                    <li class="page_number_li">
                        <a href="${path}${i.index}${searchParam}">${i.index}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="${path}${pageNumber == pageCount ? pageCount : pageNumber + 1}${searchParam}">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/problem/list_problem.js"></script>
</body>
</html>