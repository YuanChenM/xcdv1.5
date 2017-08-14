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
<div class="group-accordion" active="false">
    <h3>
        <label>企业库容概括</label>
    </h3>

    <form action="${ctx}/SL24110300502/save" id="SL24110300502Form"
          method="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">原料库容(立方)　</td>
                <td align="left" width="50%">
                    <input type="text" name="scapMaterial" max="9999999999999999.9999" number="true"
                           numberMessage="原料库容为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">成品库容(立方)　</td>
                <td align="left" width="50%">
                    <input type="text" name="scapProduct" max="9999999999999999.9999" number="true"
                           numberMessage="成品库容为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">仓库图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="importFile2"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL24110300502.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL24110300502.SAVE" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/sl/js/SL24110300502.js"></script>
