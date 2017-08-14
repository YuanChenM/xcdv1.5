<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="group-accordion" active="false">
    <h3>
        <label>卖家账号信息</label>
    </h3>
    <form:form action="${ctx}/SL24110101/update" id="SL24110101Form"
               metdod="post" enctype="multipart/form-data">
        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">卖家账号(必填)</td>
                <td align="left" width="50%">
                    <input type="text" id="slAccount" name="slAccount" value="${slAccount.slAccount}"
                           readonly="readonly"/>
                    <span id="account_sp"></span>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">密码(必填)</td>
                <td align="left" width="50%">
                    <input type="text" id="accountPsd" name="accountPsd" value="${slAccount.accountPsd}"
                           maxlength="256"  required="true"
                           requiredMessage="密码不能为空"/>
                    <span id="pwd_sp"></span>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">用户名</td>
                <td align="left" width="50%">
                    <input type="text" id="slContact" name="slContact" value="${slAccount.slContact}" maxlength="20"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">手机号(必填)</td>
                <td align="left" width="50%">
                    <input type="text" id="slTel" name="slTel" value="${slAccount.slTel}"
                           onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)"
                           onblur="this.v();" maxlength="20"
                           required="true" requiredMessage="手机号不能为空" />
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">卖家头像图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL24110101.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL24110101.SAVE" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL24110101.js"></script>
