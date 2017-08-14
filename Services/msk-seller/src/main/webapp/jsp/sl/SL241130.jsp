<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:申请新品种/特征/净重/包装申请信息
    author:gyh
    createDate:2016-3-21
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<navigation:header title="新品种/特征/净重/包装申请" backTitleArray="卖家信息列表,卖家产品信息维护"
                   backUrlArray="${ctx}/SL241101/initShow,${ctx}/SL241116/init/${slCode}"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SL241130/save" method="post" id="SL241130Form" commandName="pdTcProvider"
               modelAttribute="pdTcProvider">
        <input type="hidden" name="providerCode" value="${slCode}">
        <input type="hidden" name="providerName" value="${slShowName}">
        <input type="hidden"   name="crtId" value="${loginUser.emplId}"/>
        <input type="hidden"   name="updId" value="${loginUser.emplId}"/>
    <div class="group-accordion" active="true" collapsible="false">
        <h3>
            <label>卖家信息</label>
        </h3>

        <div>
            <table width="100%">
                <tr>
                    <td width="200px" align="right">卖家编码</td>
                    <td>${slCode}</td>
                    <td width="100px" align="right">卖家名称</td>
                    <td>${slShowName}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">新品种/特征/净重/包装申请</td>
                    <td colspan="3">
                        <form:radiobutton path="chooseInfo" value="1" checked="checked"></form:radiobutton>品种　　
                        <form:radiobutton path="chooseInfo" value="2"></form:radiobutton>特征　　
                        <form:radiobutton path="chooseInfo" value="3"></form:radiobutton>净重　　
                        <form:radiobutton path="chooseInfo" value="4"></form:radiobutton>包装　　
                    </td>
                </tr>
            </table>
        </div>
    </div>
        <div class="group-accordion" active="true" collapsible="false">
            <h3>
                <label>申请信息</label>
            </h3>
            <div>
                <table width="100%" id="SL241130_FORM">
                    <tr>
                        <td width="100px" align="right">类别<input type="hidden" name="classesName" id="classesName"/></td>
                        <td align="left">
                            <form:select path="classesCode" id="classesCode" style="width:120px">
                                <form:option value="" label="请选择"/>
                                <form:options items="${pdClasses}" itemValue="classesCode" itemLabel="classesName"/>
                            </form:select>
                        </td>
                        <td width="100px" align="right">二级分类<input type="hidden" name="machiningName" id="machiningName"/>
                        </td>
                        <td align="left">
                            <form:select path="machiningCode" style="width:120px" id="machiningCode">
                                <form:option value="" label="请选择"/>
                            </form:select>
                        </td>
                        <td align="right">品种名称</td>
                        <td align="left"><form:input path="breedName" id="breedName"/></td>
                    </tr>
                    <tr style="display: none">
                        <td width="100px" align="right">品种</td>
                        <td align="left">
                            <form:select path="breedCode" style="width:120px" id="breedCode">
                                <form:option value="" label="请选择"/>
                            </form:select>
                        </td>
                        <td align="right">产品特征名称</td>
                        <td colspan="3" align="left"><form:input path="featureName" id="featureName"/></td>
                    </tr>
                    <tr style="display: none">
                        <td align="right">特征</td>
                        <td align="left">
                            <form:select path="featureCode" style="width:120px" id="featureCode">
                                <form:option value="" label="请选择"/>
                            </form:select>
                        </td>
                        <td align="right">净重名称</td>
                        <td align="left"><form:input path="weightName" id="weightName"/></td>
                        <td align="right">净重数值</td>
                        <td align="left"><input type="number" name="weightVal" id="weightVal"/></td>
                    </tr>
                    <tr style="display: none">
                        <td width="100px" align="right">净重</td>
                        <td align="left" colspan="5">
                            <form:select path="weightCode" style="width:120px" id="weightCode">
                                <form:option value="" label="请选择"/>
                            </form:select>
                        </td>
                    </tr>
                        <tr style="display: none">
                            <td align="right">单个产品规格净重:</td>
                            <td><input type="text" name="normsSuttle"/></td>
                            <td align="right">单个产品规格净重误差范围:</td>
                            <td><input type="text" name="normsError"/></td>
                            <td align="right">内包装净重/个数:</td>
                            <td><input type="text" name="normsNumber"/></td>
                        </tr>
                        <tr style="display: none">
                            <td align="right">内包装尺寸:</td>
                            <td><input type="text" name="normsSize"/></td>
                            <td align="right">内包装材质及技术标准:</td>
                            <td><input type="text" name="normsTexture"/></td>
                            <td align="right">外包装规格:</td>
                            <td><input type="text" name="normsOut"/></td>
                        </tr>
                        <tr style="display: none">
                            <td align="right">外包装净重/毛重:</td>
                            <td><input type="text" name="normsKg"/></td>
                            <td align="right">外包装尺寸:</td>
                            <td><input type="text" name="normsOutSize"/></td>
                            <td align="right">外包装材质及技术标准:</td>
                            <td><input type="text" name="normsOutTexture"/></td>
                        </tr>
                        <tr style="display: none">
                            <td align="right">内包装净重数值:</td>
                            <td><input type="text" name="netweightInner"/>(kg)</td>
                            <td align="right">外包装净重数值:</td>
                            <td><input type="text" name="netweightOut"/>(kg)</td>
                            <td align="right">外包装毛重数值:</td>
                            <td><input type="text" name="grossweightOut"/>(kg)</td>
                        </tr>
                        <tr style="display: none">
                            <td align="right">外包装长度:</td>
                            <td><input type="text" name="normsLength"/></td>
                            <td align="right">外包装宽度:</td>
                            <td><input type="text" name="normsWidth"/></td>
                            <td align="right">外包装高度:</td>
                            <td align="left"><input type="text" name="normsHeight"/></td>
                        </tr>
                        <tr style="display: none">
                            <td align="right">外包装体积:</td>
                            <td><input type="text" name="normsVolume"/></td>
                            <td align="right">其他包装信息:</td>
                            <td colspan="5" align="left"><input type="text" name="normsTen"/></td>
                        </tr>
                    <tr>
                        <td colspan="6" align="left">
                            <msk2:button buttonType="button" buttonId="SL241130.SAVE" buttonValue="申请"/>
                            <%--<msk:button buttonType="button" buttonValue="申请" buttonId="SL241130.SAVE"/>--%>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form:form>
</div>
</div>
<script src="${ctx}/static/sl/js/SL241130.js"></script>
