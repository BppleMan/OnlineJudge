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
    String navigationBarPath = "/views/public/navigation_bar.jsp";
    String userModalPath = "/views/public/user_modal.jsp";
    String footerPath = "/views/public/footer.jsp";
%>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<head>
    <jsp:include page="<%=headerPath%>" flush="true" />
    <link href="${basePath}/css/contest/create_contest.css" rel="stylesheet">
    <link href="${basePath}/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Create A New Contest</h1>
        <form class="contest-form" action="${basePath}/contest/submit_contest" method="post" onsubmit="return submit_contest();">
            <div class="form-group">
                <label class="col-md-3">竞赛名称:</label>
                <input name="name" type="text" class="form-control input-group">
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">开始时间:</label>
                <div class="input-group input-append date form_datetime" id="datetimepicker" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-mm-dd HH:ii:ss" data-link-field="dtp_input1">
                    <input class="form-control" size="20" type="text" name="startTime" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">持续时间:</label>
                <input class="form-control" name="day" type="number" min="0" value="0">天
                <input class="form-control" name="hour" type="number" min="0" max="24" value="0">小时
                <input class="form-control" name="minute" type="number" min="0" max="60" value="0">分钟
                <input class="form-control" name="second" type="number" min="0" max="60" value="0">秒
            </div>
            <div class="form-group">
                <label class="col-md-3">竞赛类型:</label>
                <label>
                    <input type="radio" value="public" checked="checked" name="">公开竞赛
                </label>
                <label>
                    <input type="radio" value="password" name="">输入密码
                </label>
                <label>
                    <input type="radio" value="class" name="">本班同学
                </label>
            </div>
            <input type="hidden" name="token" value="${create_contest_token}">
            <div class="center-row">
                <button type="submit" id="submit" class="btn btn-embossed btn-primary">
                    <em class="glyphicon glyphicon-pencil"></em>
                    提交
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">

</script>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${basePath}/lib/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${basePath}/js/contest/create_contest.js"></script>
</body>
</html>
