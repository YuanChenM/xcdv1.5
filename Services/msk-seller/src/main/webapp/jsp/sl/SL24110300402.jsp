<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        //('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业厂区概要</label>
    </h3>
    <form:form action="${ctx}/SL24110300402/save" id="SL24110300402Form"
               method="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">厂区总资产(万元)　</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyAsset" max="999999999999999999.99" number="true"
                           numberMessage="厂区总资产为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">厂区占地面积(亩)　</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyLandArea" max="9999999999999999.9999" number="true"
                           numberMessage="厂区占地面积为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">厂房面积(平米)　</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyFloorArea" max="9999999999999999.9999" number="true"
                           numberMessage="厂房面积为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">主要设备</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyEquipment" maxlength="500"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">设计产能(万吨)</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyDesignCap" maxlength="50"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">实际产能(万吨)</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyActualCap" maxlength="50"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">外贸销售占比(%)</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyFtRate" max="999.99" number="true" numberMessage="外贸销售占比为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">直销占比(%)</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyDsRate" max="999.99" number="true" numberMessage="直销占比为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">代理占比(%)</td>
                <td align="left" width="50%">
                    <input type="text" name="ftyAsRate" max="999.99" number="true" numberMessage="代理占比为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">厂房平面图图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="importFile"/>
                </td>
            </tr>

            <tr>
                <td></td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL24110300402.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL24110300402.SAVE" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL24110300402.js"></script>
