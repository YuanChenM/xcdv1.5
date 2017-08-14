<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        //$('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>卖家品牌</label>
    </h3>

    <div>

        <div>
            <table>
                <tr>
                    <td>
                        <input type="radio" name="brandType" value="1" onchange="myBrand()" checked="checked"/>自有品牌
                    </td>
                    <td>
                        <input type="radio" name="brandType" value="2" onchange="proxyBrand()"/>代理品牌
                    </td>
                </tr>
            </table>
        </div>
        <div id="myBrand" style="display:block">
            <form:form action="${ctx}/SL241103007/insert" id="SL241103007Form"
                       metdod="post" enctype="multipart/form-data">

                <input type="hidden" name="crtId" value="${loginUser.emplId}"/>
                <input type="hidden" name="updId" value="${loginUser.emplId}"/>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">

                    <tr>
                        <td align="right" width="50%">产品分类</td>
                        <td align="left" width="50%">
                            <input type="radio" name="brandClass" value="0"/>卖家独立品牌
                            <input type="radio" name="brandClass" value="1"/>神农先生联合
                            <input type="radio" name="brandClass" value="2"/>神农客联合
                            <input type="radio" name="brandClass" value="3"/>神农人家联合
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">品牌名称(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="brandName" name="brandName" maxlength="20" required="true"
                                   requiredMessage="品牌名称不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">商标注册证编码(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="brandNo" name="brandNo" maxlength="20" required="true"
                                   requiredMessage="商标注册证编码不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">有效期开始(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="termBegin" readonly="readonly"/>
                            <input type="hidden" name="termBegin" id="termBeginTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">有效期截止(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="termEnd" readonly="readonly"/>
                            <input type="hidden" name="termEnd" id="termEndTemp"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">荣誉证书编号</td>
                        <td align="left" width="50%">
                            <input type="text" id="honorNo" name="honorNo" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">荣誉描述</td>
                        <td align="left" width="50%">
                            <input type="text" id="honorDes" name="honorDes" maxlength="200"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">发证单位</td>
                        <td align="left" width="50%">
                            <input type="text" id="certIssuer" name="certIssuer" maxlength="50"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">发证日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="certDate2" readonly="readonly"/>
                            <input type="hidden" name="certDate" id="certDate2Temp"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">品牌证书图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="brandCertFile" id="brandCert"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">包装图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="brandFile" id="brand"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">品牌荣誉证书上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="fileBrand" id="honorCert"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">
                            <msk2:button buttonType="button" buttonId="SL241103007.SAVE" buttonValue="保存"/>
                                <%--<msk:button buttonValue="保存" buttonId="SL241103007.SAVE" buttonType="button"/>--%>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>

        <div id="proxyBrand" style="display:none">
            <form:form action="${ctx}/SL241103007/insert2" id="SL2411030073Form"
                       metdod="post" commandName="proxy" modelAttribute="proxy">
                <input type="hidden" name="crtId" value="${loginUser.emplId}"/>
                <input type="hidden" name="updId" value="${loginUser.emplId}"/>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="right" width="50%">产品分类</td>
                        <td align="left" width="50%">
                            <input type="radio" name="brandClass" value="0"/>卖家独立品牌
                            <input type="radio" name="brandClass" value="1"/>神农先生联合
                            <input type="radio" name="brandClass" value="2"/>神农客联合
                            <input type="radio" name="brandClass" value="3"/>神农人家联合
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">生产商列表</td>
                        <td align="left" width="50%">
                            <form:select path="producerEpId" style="width:120px" id="prolist">
                                <form:option value="" label="请选择"/>
                                <form:options items="${proxyBean}" itemValue="producerEpId" itemLabel="epName"/>
                            </form:select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">品牌列表</td>
                        <td align="left" width="50%">
                            <form:select path="brandName" style="width:120px" id="brandlist">
                                <form:option value="" label="请选择"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">代理及分销授权合同号(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="contractNo" name="contractNo" maxlength="20" required="true"
                                   requiredMessage="代理及分销授权合同号不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">授权单位</td>
                        <td align="left" width="50%">
                            <input type="text" id="authEpName" name="authEpName" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">授权期限开始日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="authTermBegin" readonly="readonly"/>
                            <input type="hidden" name="authTermBegin2" id="authTermBeginTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">授权期限截止日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="authTermEnd" readonly="readonly"/>
                            <input type="hidden" name="authTermEnd" id="authTermEndTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">授权期限是否长期</td>
                        <td align="left" width="50%">
                            <input type="radio" name="authTermUnliimited" value="0" checked="checked"/>否
                            <input type="radio" name="authTermUnliimited" value="1"/>是
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <msk2:button buttonType="button" buttonId="SL2411030073.SAVE" buttonValue="保存"/>
                                <%--<msk:button buttonValue="保存" buttonId="SL2411030073.SAVE" buttonType="button"/>--%>
                        </td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function myBrand() {
        $('#myBrand').css("display", "block");
        $('#proxyBrand').css("display", "none");
    }
    function proxyBrand() {
        $('#myBrand').css("display", "none");
        $('#proxyBrand').css("display", "block");
    }
</script>
<script src="${ctx}/static/sl/js/SL241103007.js"></script>
