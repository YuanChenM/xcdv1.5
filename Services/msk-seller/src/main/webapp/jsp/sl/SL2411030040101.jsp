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
<div class="page-content detail-page">
    <form action="${ctx}/SL2411030040101/save" id="SL2411030040101Form"
          method="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table>
            <tr>
                <td align="right" width="50%">荣誉证书发证日期(必填)　</td>
                <td align="left" width="50%">
                    <input type="text" id="addcertDate" readonly="readonly" />
                    <input type="hidden" name="certDate" id="addcertDateTemp"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">荣誉证书发证单位　</td>
                <td align="left" width="50%">
                    <input type="text" name="certIssuer" maxlength="50"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">证书荣誉描述(必填)　</td>
                <td align="left" width="50%">
                    <input type="text" name="honorDesc" maxlength="50" required="true" requiredMessage="证书荣誉描述不能为空"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">企业荣誉图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="importFile"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SL2411030040101.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL2411030040101.SAVE" buttonType="button"/>--%>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL2411030040101.BACK" buttonValue="取消"/>
                    <%--<msk:button buttonValue="取消" buttonId="SL2411030040101.BACK" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/sl/js/SL2411030040101.js"></script>
