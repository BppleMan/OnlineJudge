<%@ page import="com.bppleman.enumration.UserChangeType" %>
<%@ page import="com.bppleman.entity.UserSolve" %>
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
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String sidebar = "/views/user/information/sidebar.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/user/sidebar.css" rel="stylesheet">
    <link href="${basePath}/css/user/user_solve_info.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container information">
    <div class="row clearfix">
        <jsp:include page="<%=sidebar%>" flush="true"></jsp:include>
        <div class="content col-md-10 column">
            <div class="hover-top">
                <div class="pagination-control">
                    <c:set var="pagePath">
                        ${basePath}/user/information/contest_info?page=
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
                        <c:set var="editPath">
                            ${basePath}/user/information/contest_info/edit_contest
                        </c:set>
                        <td class="text-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${editPath}_date?contestId=${contest.id}'">编辑时间</button>
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${editPath}_content?contestId=${contest.id}'">编辑内容</button>
                                <button type="button" class="btn btn-default btn-primary"
                                        onclick="location.href='${editPath}_delete?contestId=${contest.id}'">删除竞赛</button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/user/sidebar.js"></script>
<script>

</script>
</body>
</html>
