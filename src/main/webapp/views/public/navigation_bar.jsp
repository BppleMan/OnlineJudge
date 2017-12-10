<%@ page import="com.bppleman.entity.Contest" %><%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/10
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${basePath}/home">Online Judge</a>
        </div>
        <ul class="nav navbar-nav">
            <!--home-->
            <li><a href="${basePath}/home">Home</a></li>
            <!--problems-->
            <li><a href="${basePath}/problem/list_problem?page=1">Problem</a></li>
            <!--status-->
            <li><a href="${basePath}/status/list_status/1">Status</a></li>
            <!--contests-->
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="teacher_contest_button">TeacherContest<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a class="" href="${basePath}/contest/list_contest?note=<%=Contest.Note.TEACHER%>">ContestsList</a></li>
                    <li>
                        <c:choose>
                            <c:when test="${empty user}">
                                <a class="" onclick="bootbox.alert('请先登录')">CreateContest</a>
                            </c:when>
                            <c:otherwise>
                                <a class="" onclick="location.href = '${basePath}/contest/create_contest?note=<%=Contest.Note.DIY%>';">CreateContest</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="diy_contest_button">DIYContest<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a class="" href="${basePath}/contest/list_contest?note=<%=Contest.Note.DIY%>">ContestsList</a></li>
                    <li>
                        <c:choose>
                            <c:when test="${empty user}">
                                <a class="" onclick="bootbox.alert('请先登录')">CreateContest</a>
                            </c:when>
                            <c:otherwise>
                                <a class="" onclick="location.href = '${basePath}/contest/create_contest?note=<%=Contest.Note.DIY%>';">CreateContest</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </li>
            <!--exams-->
            <li><a href="${basePath}/exams">Exam</a></li>
            <c:if test="${user.type == 'Admin'}">
                <li><a href="${basePath}/problem/create_problem">CreateProblem</a></li>
            </c:if>
        </ul>
        <!--users-->

        <ul class="nav navbar-nav navbar-right">
            <c:if test="${empty sessionScope.user}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" data-toggle="modal" data-target="#loginModal">Log In</a></li>
                        <li><a href="${basePath}/user/register">Register</a></li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${basePath}/user/user_info">User Info</a></li>
                        <li><a onclick="logout('${basePath}')">Log Out</a></li>
                    </ul>
                </li>
            </c:if>
        </ul>
    </div>
</nav>