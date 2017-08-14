<%--
    Title:产品详细信息页面
    author:zhao_chen1
    createDate:2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<navigation:header title="新增产品详细信息" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <div style="white-space:nowrap;">
        <input type="hidden" name="type" id="bidId" value="${ssc11302Param.bidId}">
        <input type="hidden" name="type" id="bidProjectNo" value="${ssc11302Param.bidProjectNo}">
        <input type="hidden" name="type" id="type" value="${ssc11302Param.type}">
        <input type="hidden" name="ver" id="pdVer"/>
        <table style="width: 100%;">
            <tr>
                <td align="right">产品名称:</td>
                <td align="left">
                    <select name="pdDesc"  id="pdDesc" style="width:300px">
                        <option value="">请选择产品</option>
                        <c:forEach items="${pdList}" var="list" varStatus="status">
                            <option value="${list.pdDesc}"
                                    weightVal="${list.weightVal}"
                                    pdCode="${list.pdCode}"
                                    pdClassesCode="${list.pdClassesCode}"
                                    pdClassesName="${list.pdClassesName}"
                                    machiningName="${list.machiningName}"
                                    machiningCode="${list.machiningCode}"
                                    pdBreedCode="${list.pdBreedCode}"
                                    pdBreedName="${list.pdBreedName}"
                                    pdFeatureCode="${list.pdFeatureCode}"
                                    pdFeatureName="${list.pdFeatureName}"
                                    weightCode="${list.weightCode}"
                                    weightName="${list.weightName}"
                                    slTncGradeCode="${list.slTncGradeCode}"
                                    slTncGradeName="${list.slTncGradeName}"
                                    normsCode="${list.normsCode}"
                                    normsOut="${list.normsOut}"
                                    standardId="${list.standardId}"
                                    brandEpId="${list.brandEpId}"
                                    brandId="${list.brandId}"
                                    brandName="${list.brandName}"
                                    >${list.pdDesc}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>


            <input type="hidden" id="weightVal" />
            <input type="hidden" id="pdCode" />

            <tr>
                <td  align="right">箱数:</td>
                <td align="left">
                    <input type="text" id="productBox" name="productBox"  style="width:295px"/>
                </td>

            </tr>

            <tr>
                <td  align="right">不含包装离岸价(元/kg):</td>
                <td align="left"><input type="text" id="fobFreePackage" name="fobFreePackage"  style="width:295px" /></td>

            </tr>

            <tr>
                <td  align="right">包材成本(元/kg):</td>
                <td  align="left"><input type="text" id="packageCost" name="packageCost"  style="width:295px" /></td>
            </tr>

            <tr>
                <td  align="right">含包装离岸价(元/kg):</td>
                <td align="left"><input type="text" id="fobIncludePackage" name="fobIncludePackage"   style="width:295px" /></td>
            </tr>

            <tr>
                <td  align="right">干线运费(元/kg):</td>
                <td align="left"><input type="text" id="trunkFreight" name="trunkFreight"   style="width:295px" /></td>
            </tr>

            <tr>
                <td  align="right">到岸价(元/kg):</td>
                <td align="left"><input type="text" id="cif" name="cif"  style="width:295px" /></td>
            </tr>

            <tr>
                <td  align="right">本次结算标准价(元/kg):</td>
                <td align="left"><input type="text" id="settkementStandardPrice" name="settkementStandardPrice"   style="width:295px" /></td>
            </tr>


            <tr>
                <td  align="right">重量(吨):</td>
                <td align="left"><input type="text" id="productQua" readonly disabled  style="width:295px" /></td>
            </tr>
            <tr>
                <td  align="right">货值(元):</td>
                <td align="left"><input type="text" id="productValue" readonly disabled  style="width:295px" /></td>
            </tr>

            <tr>
                <td  align="right">备注:</td>
                <td align="left"><input type="text" id="remark" name="remark" style="width:295px" maxlength="100" /></td>
            </tr>


            <tr>
                <td></td>
                <td>
                    <msk:button buttonValue="保存" buttonId="SSC1130203.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </div>

</div>

<script src="<c:url value="/static/js/ssc/SSC1130203.js"/>" />
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>" />