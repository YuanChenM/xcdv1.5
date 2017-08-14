<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        // $('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="page-content detail-page" active="false">
    <form action="${ctx}/SL2411030050101/save" id="SL2411030050101Form"
          method="post" enctype="multipart/form-data">
        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">车间名称(必填)　</td>
                <td align="left" width="50%">
                    <input type="text" name="workshopName" maxlength="30" required="true" requiredMessage="车间名称不能为空"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">车间生产产品　</td>
                <td align="left" width="50%">
                    <input type="text" name="product" maxlength="200"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">车间工艺流程特点　</td>
                <td align="left" width="50%">
                    <input type="text" name="process" maxlength="200"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">车间图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="importFile"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SL2411030050101.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL2411030050101.SAVE" buttonType="button"/>--%>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL2411030050101.BACK" buttonValue="取消"/>
                    <%--<msk:button buttonValue="取消" buttonId="SL2411030050101.BACK" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/sl/js/SL2411030050101.js"></script>
