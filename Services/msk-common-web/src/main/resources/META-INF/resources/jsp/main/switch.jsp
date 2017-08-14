<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统${systemEnv}</title>
    <link href="${ctx}/static/login/css/pub.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/login/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/static/login/css/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/static/login/css/jquery-ui.min.js"></script>
    <style>
        #module-list {
            list-style-type: none;
            margin: auto;
        }

        #module-list button {
            margin: 5px;
            float: left;
            width: 220px;
            height: 40px;
            font-size: 1.5em;
            text-align: center;
        }

        #module-list button[disabled] {
            color: #bbb;
        }

        #login-bg a {
            font-family: "Microsoft Yahei",Tahoma,Simsun,sans-serif;
            font-size: 16px;
            color: blue;
            text-decoration: underline;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $("button[src]").button().click(function (event) {
                event.preventDefault();
                var url = $(this).attr("src");
                $("form#module-list").attr("action", url).submit();
            });
            $("#logoutLink").click(function (event) {
                event.preventDefault();
                $("form#logoutFrom").submit();
            });
        });
    </script>
</head>
<body>

<div>

    <div id="popup_filter"></div>
    <div class="wrapper">
        <div id="login_hd">
            <div class="login_hd">
                <div id="login-logo"
                     style="height:98px; background: url('${ctx}/static/sso/images/logo.png') no-repeat;background-size: 25%;vertical-align: central;">
                </div>
            </div>
        </div>
        <div class="login_middle"
             style="background: url('${ctx}/static/sso/images/wallpaper-1.png') no-repeat center;">
            <div id="background" class="login_box_bg" style="width:500px;">
                <form id="logoutFrom" action="${ctx}/logout" method="post">
                    <div id="login-bg" class="form login-backgroud-color" style="top: 30px; height:70px; width:100%;">
                        <span style="margin-left:30px;font-size:24px;line-height:20px;">系统选择</span>
                        <span style="margin-right:30px;line-height:20px;float: right;"><a id="logoutLink" href="#">退出</a></span>
                    </div>
                </form>
                <form id="module-list" action="${ctx}/main" method="post">
                    <div class="form login-backgroud-color" style="height:210px; width:100%; padding: 10px 0 0 20px;">
                    <c:forEach items="${modules}" var="module" varStatus="status">
                        <c:choose>
                            <c:when test='${module.sysMainUrl != null && module.sysMainUrl != ""}'>
                                <button src="${ctx}/${module.sysMainUrl}"
                                        module="${module.sysModule}">${module.sysShowName}</button>
                            </c:when>
                            <c:otherwise>
                                <button disabled>${module.sysShowName}</button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </div>
                </form>
                <div style="height:20px; width:100%; padding: 0 0 0 30px;">
                    <c:if test='${message!=null && message!=""}'>
                    <span style="font-size:12px;color:red;line-height:20px;">${message}</span>
                    </c:if>
                </div>
            </div>
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
    </div>

</div>
</body>
</html>