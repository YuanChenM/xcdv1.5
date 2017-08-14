<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<div class="group-accordion" active="false">
    <h3>
        <label>企业厂区描述</label>
    </h3>

    <div>
        <form:form action="${ctx}/SL2411010402/update" id="SL2411010402Form"
                   method="post" enctype="multipart/form-data">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="right" width="50%">厂区总资产(万元)　</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyAsset" value="${slEpCap.ftyAsset}" max="999999999999999999.99"
                               number="true" numberMessage="厂区总资产为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">厂区占地面积(亩)　</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyLandArea" value="${slEpCap.ftyLandArea}" max="9999999999999999.9999"
                               number="true" numberMessage="厂区占地面积为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">厂房面积(平米)　</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyFloorArea" value="${slEpCap.ftyFloorArea}"
                               max="9999999999999999.9999" number="true" numberMessage="厂房面积为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">主要设备</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyEquipment" value="${slEpCap.ftyEquipment}" maxlength="500"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">设计产能(万吨)</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyDesignCap" value="${slEpCap.ftyDesignCap}" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">实际产能(万吨)</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyActualCap" value="${slEpCap.ftyActualCap}" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">外贸销售占比(%)</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyFtRate" value="${slEpCap.ftyFtRate}" max="999.99" number="true"
                               numberMessage="外贸销售占比为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">直销占比(%)</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyDsRate" value="${slEpCap.ftyDsRate}" max="999.99" number="true"
                               numberMessage="直销占比为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">代理占比(%)</td>
                    <td align="left" width="50%">
                        <input type="text" name="ftyAsRate" value="${slEpCap.ftyAsRate}" max="999.99" number="true"
                               numberMessage="代理占比为数字类型"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%">${slEpCert.certName}图片上传</td>
                    <td align="left" width="50%">
                        <input type="file" name="file" id="importFile"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <msk2:button buttonType="button" buttonId="SL2411010402.UPDATE" buttonValue="保存"/>
                            <%--<msk:button buttonValue="保存"  buttonId="SL2411010402.UPDATE" buttonType="button"/>--%>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL2411010402.js"></script>
