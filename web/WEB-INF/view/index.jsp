<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/7
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@include file="../public/header.jspf" %>
</head>
<body>
<a href="backstage_index">测试进入后台</a>

<hr>

<form action="category-query" method = "post">
    <input type="text" name = "page" />
    <input type="text" name = "rows" />
    <input type="submit">
</form>
<%-- 测试文件上传 --%>
<hr>
<h4>文件上传</h4>
<form action="upload" method="post" enctype = "multipart/form-data">
    <!-- <input type = "hidden" name = "method" value ="upload"> -->
    <input type = "text" name = "fileName" >
    <input type = "file" name = "file" >
    <input type = "submit" value = "上传文件">
</form>

<div class="allcon">
    <div id = "shortcut-2017">
        <div class="w">
            <ul class = "fl">
                <li>
                    <i class="iconfont"></i>
                    <a href="http://www.jd.com">商城首页</a>
                </li>
                <li class = "dorpdown" id = "ttbar-mycity">
                    <i class="iconfont"></i>
                    北京
                </li>
            </ul>
            <ul class = "fr">

                <c:if test = "${not empty sessionScope.userOnline}">
                    <li>欢迎您</li>
                    <li class = "color-red">${userOnline.login }</li>
                </c:if>
                <c:if test = "${empty userOnline}">
                    <li>你好，请登录</li>
                    <li class = "color-red">免费注册</li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="w">
        <div id="search" class = "p1">
            <div class="search-m">
                <div class="form">
                    <input type="text" autocomplete="off" id = "key" class = "text" style = "color:#999999" />
                    <input type="button" value = "搜索"  class="button" />
                </div>
                <div id = "settleup" class = "dropdown">
                    <div class = "cw-icon">
                        <a href="${shop}/user/cartPage" class = "color-red" target="_blank">我的购物车(<span class = "num-in-cart">${sessionScope.forder.sorders.size()}</span>)</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class = "clear"></div>
    <c:forEach var="map" items="${applicationScope.shopMap}">
    <div class="con">
        <div class="w">
            <h3 class = "c-type">${map.key.type} （热卖中）</h3>
            <div class = "listoverflow">
                <ul class = "c-list">
                    <%--<h3>${map.value.size()}</h3>--%>
                    <c:if test = "${map.value != null  and map.value.size() > 0}">
                        <c:forEach var="product" items="${map.value}">
                            <li class = "list-item fore0">
                                <div class="p-img">
                                    <a href="#" target="_blank">
                                        <img width="100" height="100" alt="" src="upload/${product.pic}">
                                    </a>
                                </div>
                                <a href="" class = "p-name" target="_blank" title = "${product.remark}">${product.name}</a>
                                <div class="p-price">￥${product.price}</div>
                                <a href="#" class = "ato-cart" data-id = "${product.id}">加入购物车</a>
                            </li>
                        </c:forEach>
                    </c:if>
                    <c:if  test = "${map.value == null or map.value.size() == 0}">
                        <h3>暂无此类商品信息哦！</h3>
                    </c:if>


                </ul>
            </div>
        </div>
    </div>
    </c:forEach>


</div>

</body>
</html>
