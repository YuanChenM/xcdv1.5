<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ver" value="1"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理系统-SSO登录</title>
    <link href="${ctx}/static/css/pub.css?v=${ver}" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/sso/css/iconfont.css?v=${ver}" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/sso/css/iconfont-main.css?v=${ver}" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/jquery-ui.min.css?v=${ver}" rel="stylesheet" type="text/css">
    <script src="${ctx}/static/js/core/jquery-1.11.1.min.js?v=${ver}" type="text/javascript"></script>
    <script src="${ctx}/static/js/core/jquery-ui.min.js?v=${ver}" type="text/javascript"></script>
    <script src="${ctx}/static/sso/js/login.js?v=${ver}" type="text/javascript"></script>
    <style>
        .login-backgroud-color {
            background-color: white;
        }

        .custom-combobox {
            position: relative;
            display: inline-block;
        }

        .custom-combobox-toggle {
            position: absolute;
            top: 0;
            bottom: 0;
            margin-left: -1px;
            padding: 0;
            background: none;
            border: none;
        }

        .custom-combobox-input {
            margin: 0 0 0 15px;
            padding: 5px 10px 5px 0;
            width: 180px;
            background: none;
            border: none;
            font-family: "Microsoft Yahei";
            font-size: 14px;
            color: #333;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#userType").combobox();
            $("#submitNew").click(function () {
                if ($("#username").val() == "") {
                    alert("请输入用户名!");
                    $("#loginId").focus().select();
                    return false;
                }
                if ($("#password").val() == "") {
                    alert("请输入密码!");
                    $("#loginPwd").focus().select();
                    return false;
                }
                $("#fm1").submit();
            });

            $(window).keydown(function (e) {
                if (e.which == 13) {
                    $("#submitNew").click();
                }
            });
        });
    </script>
</head>