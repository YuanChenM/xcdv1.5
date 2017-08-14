<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>卖家账号信息编辑</label>
    </h3>

    <div>
        <div id="ordinary" style="display:block">
            <form:form action="${ctx}/SL241103001/insert" id="SL241103001Form"
                       method="post" enctype="multipart/form-data">

                <input type="hidden" id="crtId" name="crtId" value="${loginUser.emplId}"/>
                <input type="hidden" id="updId" name="updId" value="${loginUser.emplId}"/>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="right" width="50%">卖家账号(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="slAccount" name="slAccount" maxlength="20" required="true"
                                   requiredMessage="卖家账号不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">密码(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="accountPsd" name="accountPsd" maxlength="256" required="true"
                                   requiredMessage="密码不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">公司全名(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="epName" name="epName" maxlength="50" required="true"
                                   requiredMessage="公司全名不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">联系人姓名</td>
                        <td align="left" width="50%">
                            <input type="text" id="slContact" name="slContact" maxlength="20"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">手机号</td>
                        <td align="left" width="50%">
                            <input type="text" id="slTel" name="slTel"
                                   onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)"
                                   onblur="this.v();" maxlength="20" required="true"
                                   requiredMessage="手机号不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">卖家主经营类型</td>
                        <td align="left" width="50%">
                            <input type="radio" name="slMainClass" value="0"/>生产商
                            <input type="radio" name="slMainClass" value="1" checked="checked"/>自产型
                            <input type="radio" name="slMainClass" value="2"/>代理型
                            <input type="radio" name="slMainClass" value="3"/>OEM型
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">二级经营类型</td>
                        <td align="left" width="50%">
                            <input name="slSecondaryClass" type="checkbox" value="1" checked="checked"/>自产型
                            <input name="slSecondaryClass" type="checkbox" value="2"/>代理型
                            <input name="slSecondaryClass" type="checkbox" value="3"/>OEM型
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">是否是三证合一卖家</td>
                        <td align="left" width="50%">
                            <input type="radio" name="licType" value="0" checked="checked"/>否
                            <input type="radio" name="licType" value="1"/>是
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">营业执照名称(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" name="licName" maxlength="50" required="true"
                                   requiredMessage="营业执照名称不能为空"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">营业执照注册号</td>
                        <td align="left" width="50%">
                            <input type="text" name="licNo" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">省(含省、直辖市)</td>
                        <td align="left" width="50%">
                            <select style="width:120px" name="provinceCode" id="province_select" required="true"
                                    requiredMessage="省不能为空">
                                <option value="" label="请选择"/>
                                <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                    <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">地区(含地级市)</td>
                        <td align="left" width="50%">
                            <select style="width:120px" name="cityCode" id="city_select" required="true"
                                    requiredMessage="地区不能为空">
                                <option value="" label="请选择"/>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">区(含县级市、县、区)</td>
                        <td align="left" width="50%">
                            <select style="width:120px" name="districtCode" id="district_select" required="true"
                                    requiredMessage="区不能为空">
                                <option value="" label="请选择"/>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">公司场地</td>
                        <td align="left" width="50%">
                            <input type="text" name="licAddr" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">经营类型</td>
                        <td align="left" width="50%">
                            <input type="text" name="licBusiType" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">经营范围</td>
                        <td align="left" width="50%">
                            <input type="text" name="licBusiScope" maxlength="500"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">生产国籍</td>
                        <td align="left" width="50%">
                            <input type="radio" name="slConFlg" value="1" checked="checked"/>国产
                            <input type="radio" name="slConFlg" value="2"/>进口
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">法人代表</td>
                        <td align="left" width="50%">
                            <input type="text" name="licLegalPerson" maxlength="20"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">注册资本(万元)</td>
                        <td align="left" width="50%">
                            <input type="text" name="licRegCapital"
                                   onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-.]+/,'');}).call(this)"
                                   onblur="this.v();" max="9999999999999999.9999"
                                   number="true" numberMessage="注册资本为数字类型"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">实收资本(万元)</td>
                        <td align="left" width="50%">
                            <input type="text" name="licPaidinCapital"
                                   onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-.]+/,'');}).call(this)"
                                   onblur="this.v();" max="9999999999999999.9999"
                                   number="true" numberMessage="实收资本为数字类型"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">成立日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="licCrtDate" readonly="readonly"/>
                            <input type="hidden" name="licCrtDate" id="licCrtDateTemp"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">营业开始日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="licTermBegin" readonly="readonly"/>
                            <input type="hidden" name="licTermBegin" id="licTermBeginTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">营业截止日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="licTermEnd" readonly="readonly"/>
                            <input type="hidden" name="licTermEnd" id="licTermEndTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">营业期限是否是长期</td>
                        <td align="left" width="50%">
                            <input type="radio" name="licTermUnliimited" value="0" checked="checked"/>否
                            <input type="radio" name="licTermUnliimited" value="1"/>是
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">税务登记证号(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" name="taxNo" maxlength="30" required="true"
                                   requiredMessage="税务登记证号不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">一般增值税纳税人资格认定编号</td>
                        <td align="left" width="50%">
                            <input type="text" name="taxVatNo" maxlength="30"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">组织代码证编号(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" name="orgNo" maxlength="20" required="true"
                                   requiredMessage="组织代码证编号不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">组织代码证开始日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="orgTermBegin" readonly="readonly"/>
                            <input type="hidden" name="orgTermBegin" id="orgTermBeginTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">组织代码证截止日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="orgTermEnd" readonly="readonly"/>
                            <input type="hidden" name="orgTermEnd" id="orgTermEndTemp"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">银行开户人</td>
                        <td align="left" width="50%">
                            <input type="text" name="balLegalPerson" maxlength="20"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">开户银行</td>
                        <td align="left" width="50%">
                            <input type="text" name="balBank" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">银行账户(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" name="balAccount" maxlength="50"
                                   onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
                                   onblur="this.v();"
                                   maxlength="30" required="true" requiredMessage="银行账户不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">食品流通许可证_许可证编号</td>
                        <td align="left" width="50%">
                            <input type="text" name="fdlNo" maxlength="30"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">食品流通许可证_开始日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="fdlTermBegin" readonly="readonly"/>
                            <input type="hidden" name="fdlTermBegin" id="fdlTermBeginTemp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">食品流通许可证_截止日期(必填)</td>
                        <td align="left" width="50%">
                            <input type="text" id="fdlTermEnd" readonly="readonly"/>
                            <input type="hidden" name="fdlTermEnd" id="fdlTermEndTemp"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right" width="50%">卖家头像图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="accountfile"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">营业执照图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="licfile" id="licfileId"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">税务登记证图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="taxfile" id="taxfileId"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">组织机构代码证图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="orgfile" id="orgfileId"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">银行开户许可证图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="balfile"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50%">三证合一证照图片上传</td>
                        <td align="left" width="50%">
                            <input type="file" name="epthrfile" id="epthrfileId" disabled="disabled"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                            <msk2:button buttonType="button" buttonId="SL241103001.SAVE" buttonValue="保存"/>
                                <%--<msk:button buttonValue="保存" buttonId="SL241103001.SAVE" buttonType="button"/>--%>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${ctx}/static/sl/js/SL241103001.js"></script>
