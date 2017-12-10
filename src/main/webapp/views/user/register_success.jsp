<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/15
  Time: 14:17
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
</head>
<body>
<jsp:include page="<%=navigationBarPath%>" flush="true" />
<jsp:include page="<%=userModalPath%>" flush="true" />
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${registerState}</h1>
        <div class="progress progress-striped active">
            <div id="progressbar" class="progress-bar progress-bar-success" role="progressbar"
                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                 style=" width: 0;">
                <%--<span class="sr-only">40% 完成</span>--%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="<%=footerPath%>" flush="true" />
<script src="${basePath}/js/user/register_success.js"></script>
<script>
    startAnimation('${basePath}');
</script>
</body>
</html>