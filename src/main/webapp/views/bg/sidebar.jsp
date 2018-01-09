<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2018/1/4
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<div class="sidebar">
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion"
                       href="#problem">
                        题目
                    </a>
                </h4>
            </div>
            <div id="problem" class="panel-collapse collapse ${collapse.equals("problem")?"in":""}">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="${basePath}/bg/problem/list_problem">题目浏览</a></li>
                        <li class="list-group-item"><a href="${basePath}/bg/problem/create_problem">题目添加</a></li>
                        <li class="list-group-item"><a href="${basePath}/bg/problem/edit_problem_data">题目数据</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion"
                       href="#contest">
                        竞赛
                    </a>
                </h4>
            </div>
            <div id="contest" class="panel-collapse collapse ${collapse.equals("contest")?"in":""}">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="${basePath}/bg/contest/list_contest">竞赛浏览</a></li>
                        <li class="list-group-item"><a href="${basePath}/bg/contest/create_contest">创建竞赛</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion"
                       href="#exam">
                        考试
                    </a>
                </h4>
            </div>
            <div id="exam" class="panel-collapse collapse ${collapse.equals("exam")?"in":""}">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="">题目浏览</a></li>
                        <li class="list-group-item"><a href="">题目添加</a></li>
                        <li class="list-group-item"><a href="">题目浏览</a></li>
                        <li class="list-group-item"><a href="">题目浏览</a></li>
                        <li class="list-group-item"><a href="">题目浏览</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>