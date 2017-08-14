<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>企业实验室、检测设备以及产品质量控制系统描述</label>
    </h3>

    <form action="${ctx}/SL241103006/save" id="SL241103006Form"
          method="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">实验室面积(平米)　</td>
                <td align="left" width="50%">
                    <input type="text" name="labArea" max="9999999999999999.9999" number="true"
                           numberMessage="实验室面积为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">实验室功能　</td>
                <td align="left" width="50%">
                    <input type="text" name="labFunction" maxlength="100"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">投资(万元)　</td>
                <td align="left" width="50%">
                    <input type="text" name="labInvestment" max="999999999999999999.99" number="true"
                           numberMessage="投资为数字类型"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">人员数量　</td>
                <td align="left" width="50%">
                    <input type="text" name="labMember" max="2147483647" digits="true" digitsMessage="人员数量为整数"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">主要设备及用途　</td>
                <td align="left" width="50%">
                    <input type="text" name="ddEquipment" maxlength="200"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">实验室图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="labFile" id="importFile"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">检测设备图片上传</td>
                <td align="left" width="50%">
                    <input type="file" name="ddEquFile" id="importFile2"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">品控组织架构　</td>
                <td align="left" width="50%">
                    <input type="file" name="controllFile" id="importFile3"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">质量控制系统图　</td>
                <td align="left" width="50%">
                    <input type="file" name="qualityFile" id="importFile4"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SL241103006.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL241103006.SAVE" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/sl/js/SL241103006.js"></script>
