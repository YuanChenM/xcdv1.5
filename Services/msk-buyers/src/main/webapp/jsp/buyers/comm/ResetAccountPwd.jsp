
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form id="resetAccountFrom" method="post">
        <table id="ResetAccountPwd_Grid">
            <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}">
            <thead>
            <tr>
                <td width="60%" style="text-align: right;"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>手机号 : </td>
                <td><input name="telNo" id="telNewNo" value="${telNo}" maxlength="20" required requiredMessage="手机号不能为空" digits="true"
                           digitsMessage="手机号号为数字类型"></td>
            </tr>
            <tr>
                <td style="text-align: right;"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>账号 : </td>
                <td><input maxlength="40" name="accountName" id="accountNewName"  value="${accountName}" required requiredMessage="账号不能为空" onblur="ResetAccountPwd.changePassWord();"></td>
            </tr>
            <tr>
                <td style="text-align: right;"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>密码 : </td>
                <td><input maxlength="40" name="accountPass" id="accountNewPass"  value="${accountPass}" required requiredMessage="密码不能为空"></td>
            </tr>
            <tr>
                <td  style="text-align: right;">
                    <msk:button buttonValue="重置" buttonId="ACCOUNT.RESET" buttonType="button"/>
                </td>
                <td  style="text-align: left;padding-left: 25px;">
                    <msk:button buttonValue="取消" buttonId="ACCOUNT.CANCEL" buttonType="button"/>
                </td>
            </tr>
            </thead>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/comm/ResetAccountPwd.js"></script>
