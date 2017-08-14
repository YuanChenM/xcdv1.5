<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<script type="text/javascript">
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>--%>
<div class="group-accordion" active="false">
    <h3>
        <label>电商团队</label>
    </h3>
    <form:form action="${ctx}/SL241103009/insert" id="SL241103009Form"
               metdod="post" enctype="multipart/form-data">

        <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">

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
                    <input type="text" id="ememberAge" name="ememberAge" maxlength="3" digits="true"
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
                    <msk2:button buttonType="button" buttonId="SL241103009.SAVE" buttonValue="保存"/>
                        <%--<msk:button buttonValue="保存" buttonId="SL241103009.SAVE" buttonType="button"/>--%>
                </td>
                <td></td>
            </tr>
        </table>
    </form:form>
</div>
<script src="${ctx}/static/sl/js/SL241103009.js"></script>
