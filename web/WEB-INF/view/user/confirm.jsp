<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/22
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单确认</title>
</head>

<%
    response.setHeader("cache-control", "no-store");
%>
<h1>${ empty sessionScope.forder.sorders }</h1>
<c:if test = "${ empty sessionScope.forder.sorders }">
    <%-- 购物车中的项为空，跳转到首页 --%>
    <h1>666</h1>
    <script>
        window.location = "${pageContext.request.contextPath}/index";
    </script>
</c:if>
<body>
    <h3>${sessionScope.userOnline   }</h3><hr>
    <h3>订单数量</h3><hr>
    <h3>${sessionScope.forder.sorders.size() }</h3><hr>
    <h3>确认订单信息页面</h3>
    <h3>订购人信息</h3><hr>
    <form action="forder-save" method = "post">
        配送姓名： <input type="text" name = "name">
        联系方式： <input type="text" name = "phone">
        区域邮编： <input type="text" name = "post">
        配送地址： <input type="text" name = "address">
        <input type="submit" value = "确认无误，购买">
    </form>
</body>
</html>
