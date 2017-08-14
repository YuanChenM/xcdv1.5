<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun2(message, slAccount) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $.pdialog.close();
        //$('#main-content').postUrl(Main.contextPath + "/SL24110100/init/"+slAccount);
    }
</script>
<div class="page-content detail-page" id="SL2411030091_id">
    <form:form action="${ctx}/SL2411030091/insert" id="SL2411030091Form"
               metdod="post" enctype="multipart/form-data">
    <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
    <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
    <table id="SL2411030091DataGrid">
        <tr>
            <td align="right" width="50%">职务</td>
            <td align="left" width="50%">
                <select name="eleaderFlg" id="eleaderFlg">
                    <option value="">--请选择--</option>
                    <option value="1">团队负责人</option>
                    <option value="0">团队成员</option>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right" width="50%">姓名</td>
            <td align="left" width="50%">
                <input type="text" id="ememberName" name="ememberName" maxlength="20"/>
            </td>
        </tr>

        <tr>
            <td align="right" width="50%">年龄</td>
            <td align="left" width="50%">
                <input type="text" id="ememberAge" name="ememberAge" name="ememberAge" maxlength="3" digits="true"
                       digitsMessage="年龄为整数"/>
            </td>
        </tr>
        <tr>
            <td align="right" width="50%">联系方式</td>
            <td align="left" width="50%">
                <input type="text" id="ememberTel" name="ememberTel" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <td align="right" width="50%">教育程度</td>
            <td align="left" width="50%">
                <input type="text" id="ememberEduc" name="ememberEduc" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <td align="right" width="50%">头像上传</td>
            <td align="left" width="50%">
                <input type="file" name="file" id="ec_teamImage"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <msk2:button buttonType="button" buttonId="SL2411030091.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL2411030091.SAVE" buttonType="button"/>--%>
            </td>
            <td align="left">
                <msk2:button buttonType="button" buttonId="SL2411030091.BACK" buttonValue="取消"/>
                    <%--<msk:button buttonValue="取消" buttonId="SL2411030091.BACK" buttonType="button"/>--%>
            </td>

        </tr>
        </form:form>
</div>
<script src="${ctx}/static/sl/js/SL2411030091.js"></script>
