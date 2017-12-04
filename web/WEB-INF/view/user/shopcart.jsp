<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/21
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype>
<html>

<head>
    <style type="text/css">[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide{display:none !important;}ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;-webkit-transition:0s all!important;}.ng-hide-add-active,.ng-hide-remove{display:block!important;}</style><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide{display:none !important;}ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;-webkit-transition:0s all!important;}.ng-hide-add-active,.ng-hide-remove{display:block!important;}</style>
    <%@include file ="../../public/header.jspf" %>
</head>



<body ng-app="" class="ng-scope">

<div style="margin-left:auto; margin-right:auto; width:600px; padding-top:30px" class="ng-binding">
    <input type="text" name="" value="" ng-model="abc" placeholder="输入关键字快速查找" class="ng-pristine ng-valid">
</div>

<div ng-controller="cartController" class="container ng-scope">
    <%--${"555"}
    ${sessionScope.forder.sorders}--%>
    <table class="table" ng-show="cart.length">
        <thead>
        <tr>
            <th>商品编号</th>
            <th>商品名字</th>
            <th>购买数量</th>
            <th>商品单价</th>
            <th>商品总价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in cart| filter:abc" class="ng-scope">
            <td ng-bind="item.id" class="ng-binding">100</td>
            <td ng-bind="item.name" class="ng-binding">iphone5s</td>
            <td>
                <button type="button" ng-click="reduce(item.id)" class="btn btn-primary btn-sm">-</button>
                <input type="text" ng-model="item.quantity" style="text-align: center;" class="ng-pristine ng-valid">
                <button type="button" ng-click="add(item.id)" class="btn btn-primary btn-sm">+</button>
            </td>
            <td ng-bind="item.price" class="ng-binding">4300</td>
            <td ng-bind="item.price*item.quantity" class="ng-binding">17200</td>
            <td><button type="button" ng-click="remove(item.id)" class="btn btn-danger btn-sm">移除</button></td>
        </tr>
        <!-- ngRepeat: item in cart| filter:abc --><!-- ngRepeat: item in cart| filter:abc -->
        <tr>
            <td>总购物价</td>
            <td ng-bind="totalPrice()" class="ng-binding"></td>
            <td>总购买数量</td>
            <td ng-bind="totalQuantity()" class="ng-binding"></td>
            <td colspan="2">
                <button type="button" ng-click="" class="btn btn-danger btn-sm">继续购物</button>
                <button type="button" ng-click="cart = {}" class="btn btn-danger btn-sm">清空购物车</button>
                <button type="button" ng-click="settleAccount()" class="btn btn-danger btn-sm">结账</button>
            </td>
        </tr>
        </tbody>
    </table>
    <p ng-show="!cart.length" class="ng-hide">您的购物车已空</p>
</div>

<div id="__nightingale_view_cover" style="width: 100%; height: 100%; transition: -webkit-transform 10s ease-in-out; position: fixed !important; left: 0px !important; bottom: 0px !important; overflow: hidden !important; background: rgb(0, 0, 0) !important; pointer-events: none !important; z-index: 2147483647; opacity: 0.35;"></div></body></html>