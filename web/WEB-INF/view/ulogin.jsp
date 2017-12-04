<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/22
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file = "../public/header.jspf" %>
</head>
<body>
    <div class="login-form">
        <div class="tips-wrapper">
            <div class="tips-inner">
                <div class="cont-wrapper">
                    <i class="icon-tips"></i>
                    <p>本商城不会以任何理由要求您转账汇款，谨防诈骗。</p>
                </div>
            </div>
        </div>

        <div class="login-box">
            <div class="msg-wrap">
                <div class="msg-error hide">
                    <b></b>
                    账户名与密码不匹配，请重新输入
                </div>
            </div>

            <div class="mc">
                <div class="form">
                    <form id="formlogin" method="post"  action = "${shop}/user/login">
                        <input type="hidden" name = "sa_token" value = "###">
                        <input type="hidden" name = "uuid" id = "uuid" value = "uuid" />
                        <input type="hidden" name = "eid" id = "eid" name = "eid" value = "eid">
                        <div class="item item-fore1">
                            <label class = "login-label name-label"></label>
                            <input id="loginname" type="text" class="itxt" name="loginname" autocomplete="off" placeholder="邮箱/用户名/已验证手机">
                        </div>

                        <div id="entry" class = "item item-fore2">
                            <label class="login-label pwd-label"></label>
                            <input type="password" id = "loginpwd" name = "loginpwd" class="itxt" autocomplete="off" placeholder="密码">
                        </div>
                        <div class="item item-fore4">
                            <div class="safe">
                                <span class = "forget-pw-safe"><a href="#" class = "">忘记密码</a></span>
                            </div>
                        </div>

                        <div class="item item-fore5">
                            <div class="login-btn">
                                <a href="javascript:;" class="btn-img btn-entry" id="loginsubmit" style="outline: rgb(109, 109, 109) none 0px;">登&nbsp;&nbsp;录</a>
                            </div>
                        </div>

                        <div class="coagent" id = "kbCoagent">
                            <ul>
                                <li class = "extra-r">
                                    <a href="#">忘记密码</a>
                                </li>
                            </ul>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</body>
</html>
