<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: BppleMan
  Date: 2017/11/10
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="<%=request.getContextPath()%>"></c:set>
<script src="${basePath}/lib/jquery/dist/jquery.js"></script>
<%--引用bootstrap.min.js会和flat-ui.min.js冲突，导致dropdown无法下拉--%>
<%--<script src="${basePath}/lib/bootstrap/js/bootstrap.min.js"></script>--%>
<script src="${basePath}/lib/flat-ui/dist/js/flat-ui.js"></script>
<script src="${basePath}/lib/flat-ui/docs/assets/js/application.js"></script>
<script src="${basePath}/lib/bootbox.js/bootbox.js"></script>
<script src="${basePath}/js/navigation_bar.js"></script>
<script src="${basePath}/js/user_modals.js"></script>
