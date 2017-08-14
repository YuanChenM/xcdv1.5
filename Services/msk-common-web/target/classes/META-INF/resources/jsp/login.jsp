<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${systemName} - 登录页面</title>
    <link href="${ctx}/static/login/css/pub.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/login/css/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/login/css/iconfont-main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/login/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/static/login/css/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/static/login/css/jquery-ui.min.js"></script>
    <style>
        .login-backgroud-color {
            background-color: white;
        }
    </style>
</head>
<body>

<div>

    <form method="post" action="${ctx}/login" id="loginForm">
        <input type="hidden" name="systemCode" value="${systemCode}"/>
        <input type="hidden" id="userType" name="userType" value="1">
        <div id="popup_filter"></div>
        <div class="wrapper">
            <div id="login_hd">
                <div class="login_hd">
                    <div id="login-logo" style="/* top: 26px; */ height:98px;  background: url('${ctx}/static/login/images/logo.png') no-repeat;background-size: 25%;vertical-align: center;/* padding-left: 50px; */">
                    </div>
                </div>
            </div>
            <div class="login_middle" style="background: url('${ctx}/static/login/images/wallpaper-1.png') no-repeat center;">
                <div id="background" class="login_box_bg">
                    <div id="login-bg" class="form login-backgroud-color" style="top: 30px; height:70px;">
                        <img src="${ctx}/static/login/images/sys/${sysModel}-text.png" style="height:36px; margin-left: 8px;"/>
                    </div>
                    <div id="newVersionLogin" class="form login-backgroud-color">
                        <table class="all_input">
                            <tbody>
                            <tr>
                                <td></td>
                                <td style="height:50px;padding: 5px;">
                                    <label id="lblNewUserName" for="userName"></label>
                                    <i class="iconfont icon-login">&#xe630;</i>
                                    <input id="userName" class="module_app_input___gray_neoskin"
                                           style="margin-left:15px;" placeholder="用户名" type="text" value="" tabindex="1"
                                           name="userName" autocapitalize="off">
                                </td>
                                <td></td>
                            </tr>
                            <tr class="line">
                                <td></td>
                                <td class="tdline_2" width="30"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td style="height:45px;padding: 5px;">
                                    <label id="lblNewPassword" for="password"></label>
                                    <i class="iconfont icon-login">&#xe61f;</i>
                                    <input id="password" class="module_app_input___gray_neoskin"
                                           style="margin-left:15px;" placeholder="密码" type="password" value="" tabindex="1"
                                           name="password" autocapitalize="off">
                                    <a class="forget" href="#"></a>
                                </td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                        <div style="margin-left:9%;height:20px;">
                            <span style="font-size:12px;color:red;line-height:20px;">${message}</span>
                        </div>
                        <div class="btn_wrap login-backgroud-color">
                            <input type="button" name="submitNew" value="登录" id="submitNew" class="btn_login_submit">
                        </div>
                    </div>
                </div>
            </div>
            <div class="foot" id="footer">
                <ul class="links ft">
                    <%--<li class="links_item"><a href="${ctx}/by/login">买家管理系统</a></li>
                    <li class="links_item"><a href="${ctx}/sl/login">卖家管理系统</a></li>
                    &lt;%&ndash;<li class="links_item"><a href="${ctx}/ms/login">会员管理系统</a></li>&ndash;%&gt;
                    <li class="links_item"><a href="${ctx}/pd/login">产品管理系统</a></li>
                    <li class="links_item"><a href="${ctx}/so/login">销售运营管理系统</a></li>
                    <li class="links_item"><a href="${ctx}/ds/login">卖家供应链管理系统</a></li>
                    <li class="links_item"><a href="${ctx}/bs/login">买手店管理系统</a></li>--%>
                    <li class="links_item"><a href="${ctx}/sp/login">云冻品分销供应链</a></li>
                </ul>
            </div>
            <div>
                <p class="copyright" style="text-align: center; margin-bottom: 60px;">Copyright © 2016 Xian Chi Da
                    Group. All Rights Reserved.<br/>网站备案/许可证号：沪ICP备14048034号-1</p>
            </div>

            <!--大黑罩子-->
            <div id="J_mask" class="sec-bg-full hidden">
            </div>
            <!--浮层-->
            <div class="box_popup sec-pop hidden" style="background-color: #fff; margin: 0" id="J_risk">
                <a href="javascript:;" class="btn_close">× </a>

                <div class="box_risk">
                    <h3>
                        您的账号存在风险，暂时无法登录
                    </h3>

                    <div class="box_button">
                        <input type="button" class="btn" value="知道了">
                    </div>
                </div>
            </div>
            <script>
                $(function () {
                    $("#submitNew").click(function () {
                        if ($("#userName").val() == "") {
                            alert("请输入用户名!");
                            $("#userName").focus().select();
                            return false;
                        }
                        if ($("#password").val() == "") {
                            alert("请输入密码!");
                            $("#password").focus().select();
                            return false;
                        }
                        $("#loginForm").submit();
                    });

                    $(window).keydown(function (e) {
                        if (e.which == 13) {
                            $("#submitNew").click();
                        }
                    });

                    $("#vcodeImg").click(function () {
                        $(this).attr("src", VcodeUrl + "?r=" + new Date().getTime());
                    });
                });
            </script>
        </div>
    </form>

</div>
</body>
</html>