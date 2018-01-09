<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2018/1/1
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<div class="sidebar col-md-2 column">
    <ul id="sidebar" class="nav nav-pills nav-stacked">
        <li class="brand text-center">
            <a href="${basePath}/user/information/main_info">个人信息</a>
        <li>
            <a href="${basePath}/user/information/main_info">账户信息</a>
        </li>
        <li>
            <a href="${basePath}/user/information/user_solve_info?rt=1">答题信息</a>
        </li>
        <li>
            <a href="${basePath}/user/information/contest_info/list_contest?page=1">竞赛信息</a>
        </li>
        <li>
            <a href="${basePath}/user/information/exam_info">考试信息</a>
        </li>
    </ul>
</div>