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
    <link href="${basePath}/lib/amazeui/dist/css/amazeui.css" rel="stylesheet">
    <jsp:include page="<%=headerPath%>" flush="true"/>
    <link href="${basePath}/lib/sidebar/css/style.css" rel="stylesheet">
    <link href="${basePath}/css/contest/edit_contest_problem.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true"/>
<jsp:include page="<%=userModalPath%>" flush="true"/>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">选择你的题目</h1>
        <input type="checkbox" checked data-toggle="switch" data-on-text="已选" data-off-text="已选" id="selected-switcher" onchange="changeSidebar(this)"/>
        <hr>
        <div class="container-with-sidebar">
            <div id="wrapper" class="page-sidebar">
                <!-- Sidebar -->
                <nav class="navbar navbar-inverse" id="sidebar-wrapper" role="navigation">
                    <ul class="nav sidebar-nav">
                        <li class="sidebar-brand"><a href="#">已选题目列表</a></li>
                        <c:forEach var="problem" items="${contestProblems}" varStatus="i">
                            <li value="${problem.id}">
                                    ${i.index + 1}:${problem.title}
                                <div class="sidebar-li-operator">
                                    <a onclick="removeOneSelectProblem('${problem.id}')"><span class="fui-trash">删除</span></a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
            <div id="wrapper-right"  class="sidebar-right">
                <div class="tabbable" id="tabs-244441">
                    <ul class="nav nav-tabs">
                        <c:forEach var="label" items="${labels}" varStatus="i">
                            <c:choose>
                                <c:when test="${not empty labelType}">
                                    <c:set var="typeActive" value="${labelType.equals(label.type)?'active':''}"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="typeActive" value="${i.index == 0 ? 'active' : ''}"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <li class="${typeActive}">
                                <a href="#panel_${i.index}" data-toggle="tab">${label.type}</a>
                            </li>
                            <c:if test="${i.index == (labels.size() - 1)}">
                                <li><a href="#panel_${i.index + 1}" data-toggle="tab">智能出题</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <div class="tab-content">
                        <c:forEach var="label" items="${labels}" varStatus="i">
                            <c:choose>
                                <c:when test="${not empty labelType}">
                                    <c:set var="typeActive" value="${labelType.equals(label.type)?'active':''}"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="typeActive" value="${i.index == 0 ? 'active' : ''}"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <div class="tab-pane ${typeActive}" id="panel_${i.index}">
                                <div class="panel-group" id="accordion-${i.index}">
                                    <c:forEach var="value" items="${label.values}" varStatus="j">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-${i.index}"
                                                       href="#collapse_${i.index}_${j.index}">${value}</a>
                                                </h4>
                                            </div>
                                            <div id="collapse_${i.index}_${j.index}" class="panel-collapse collapse ${lv.equals(value)?'in':''}">
                                                <div class="panel-body">
                                                    <table id="problems-table-${i.index}-${j.index}"
                                                           class="table table-striped table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th class="text-center">题目编号</th>
                                                            <th class="text-center">题目名称</th>
                                                            <th class="text-center">状态</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="problem" items="${labelProblemsMap[value]}">
                                                            <tr>
                                                                <td class="text-center">${problem.id}</td>
                                                                <td class="text-center">${problem.title}</td>
                                                                <td class="text-center">
                                                                    <c:choose>
                                                                        <c:when test="${not empty contestProblems}">
                                                                            <input ${contestProblems.contains(problem)?"checked":""}
                                                                                    type="checkbox" name="selected" onclick="clickCheckbox(this, ${problem.id}, '${problem.title}')" value="${problem.id}">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <input type="checkbox" name="selected" onclick="clickCheckbox(this, ${problem.id}, '${problem.title}')" value="${problem.id}">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                    <c:set var="pagePath">${basePath}/contest/edit_contest_problem?contestId=${contest.id}&rt=${requestTime}&lt=${label.type}&lv=${value}&cpage=</c:set>
                                                    <c:set var="begin" value="${labelPageCountBeginEndMap.get(value).get('begin')}"></c:set>
                                                    <c:set var="end" value="${labelPageCountBeginEndMap.get(value).get('end')}"></c:set>
                                                    <c:set var="pageNumber" value="${labelCurrentPage[value]}"></c:set>
                                                    <div class="center-row">
                                                        <ul class="pagination">
                                                            <li>
                                                                <a href="${pagePath}${pageNumber == 1 ? pageNumber : pageNumber - 1}">上一页</a>
                                                            </li>
                                                            <c:if test="${not empty begin && begin != 1}">
                                                                <li class="page_number_li">
                                                                    <a href="${pagePath}${begin-1}">...</a>
                                                                </li>
                                                            </c:if>
                                                            <c:forEach var="item" varStatus="k" begin="${begin}" end="${end}" step="1">
                                                                <li class="${labelCurrentPage[value].equals(k.index)?'active':''}">
                                                                    <a href="${pagePath}${k.index}">${k.index}</a>
                                                                </li>
                                                            </c:forEach>
                                                            <c:if test="${not empty end && end < labelPageCountMap[value]}">
                                                                <li class="page_number_li">
                                                                    <a href="${pagePath}${end+1}">...</a>
                                                                </li>
                                                            </c:if>
                                                            <li>
                                                                <a href="${pagePath}${pageNumber == labelPageCountMap[value] ? pageNumber : pageNumber + 1}">Next</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <c:if test="${i.index == (labels.size() - 1)}">
                                <div class="tab-pane ${i.index == 0 ? 'active' : ''}" id="panel_${i.index + 1}">
                                    <div class="panel-group" id="accordion-${i.index}">
                                        <table class="table table-hover">
                                            <tbody>
                                            <tr id="tr_1">
                                                <td style="vertical-align: middle"><a style="font-size: 18px">1</a></td>
                                                <td>
                                                    <select id="1" name="type" placeholder="选择分类" onchange="listenSelect(this)">
                                                        <option></option>
                                                        <c:forEach var="label" items="${labels}">
                                                            <option value="${label.type}">${label.type}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="2" name="label" placeholder="选择题型" onchange="listenSelect(this)">
                                                        <option></option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="3" name="level" placeholder="选择难度" onchange="listenSelect(this)">
                                                        <option></option>
                                                        <option value="25">通过率(0~25%]</option>
                                                        <option value="50">通过率(25~50%]</option>
                                                        <option value="75">通过率(50~75%]</option>
                                                        <option value="100">通过率(75~100%]</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input name="problemCount" type="number" min="1" class="am-form-field am-radius"
                                                           placeholder="题目数量" style="width: 100px" onchange="listenNumberInput(this)">
                                                </td>
                                                <td>
                                                    <div class="am-btn-group">
                                                        <button type="button" class="am-btn am-btn-primary am-radius" onclick="addRow(this)">
                                                            添加<i class="glyphicon glyphicon-plus-sign"></i>
                                                        </button>
                                                        <button type="button" class="am-btn am-btn-primary am-radius" onclick="removeRow(this)" id="first-row-remove-btn">
                                                            删除<i class="glyphicon glyphicon-minus-sign"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                            <tfoot>
                                            <tf>
                                                <button type="button" class="am-btn am-btn-primary am-radius" onclick="generateProblem(this)">
                                                    生成题目
                                                </button>
                                            </tf>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <input type="hidden" id="contestId" name="contestId" value="${contest.id}">
                <input type="hidden" id="token" name="token" value="${contest_problem_token}">
                <div class="center-row">
                    <button type="button" class="btn btn-embossed btn-primary" onclick="createFormAndSubmit()">
                        <em class="glyphicon glyphicon-pencil"></em>
                        提交
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/contest/edit_contest_problem.js"></script>
<script>
    //一些相关的变量初始化
    basePath = "${basePath}";
    setSelectedProblems();
    changeSidebar($("#selected-switcher"));
    labels = {
    <c:forEach var="label" items="${labels}" varStatus="i">
    ${label.type}:new Array(
        <c:forEach var="value" items="${label.values}" varStatus="j">"${value}"<c:if test="${j.index != label.values.size() - 1}">,</c:if></c:forEach>
    )<c:if test="${i.index != labels.size() - 1}">,</c:if>
    </c:forEach>
    }
    templateTR = $("#tr_1").clone();
    $("#first-row-remove-btn").attr("disabled", true);

    initSmartProblems();
</script>
</body>
</html>
