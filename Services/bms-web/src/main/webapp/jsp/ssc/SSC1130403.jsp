<%--
    Title:产品详细信息页面
    author:zhao_chen1
    createDate:2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div>
    <form id="SSC1130403Form">
        <input type="hidden" name="contractId" value="${contractId}" id="contractId"/>
        <input type="hidden" name="classesCode" id="classesCode"/>
        <input type="hidden" name="classesName" id="classesName"/>
        <input type="hidden" name="machiningCode" id="machiningCode"/>
        <input type="hidden" name="machiningName" id="machiningName"/>
        <input type="hidden" name="breedCode" id="breedCode"/>
        <input type="hidden" name="breedName" id="breedName"/>
        <input type="hidden" name="featureCode" id="featureCode"/>
        <input type="hidden" name="featureName" id="featureName"/>
        <input type="hidden" name="weightCode" id="weightCode"/>
        <input type="hidden" name="weightName" id="weightName"/>
        <input type="hidden" name="normsCode" id="normsCode"/>
        <input type="hidden" name="normsName" id="normsOut"/>
        <input type="hidden" name="weightVal" id="weightVal"/>
        <input type="hidden" name="gradeCode" id="gradeCode"/>
        <input type="hidden" name="gradeName" id="gradeName"/>
        <input type="hidden" name="pdDesc" id="pdDesc"/>
        <input type="hidden" name="brandEpId" id="brandEpId"/>
        <input type="hidden" name="brandId" id="brandId"/>
        <input type="hidden" name="brandName" id="brandName"/>

        <table>
            <tr>
                <td>产品：</td>
                <td>
                    <select name="pdCode" id="productAttrCode" style="border:1px solid #888888; width:307px;">
                        <option value="">--&nbsp;请选择&nbsp;--</option>
                        <c:forEach items="${productBeans}" var="productBean">
                            <option value="${productBean.pdCode}"
                                    classes_code="${productBean.pdClassesCode}" machining_code="${productBean.machiningCode}" breed_code="${productBean.pdBreedCode}" feature_code="${productBean.pdFeatureCode}" weight_code="${productBean.weightCode}" norms_code="${productBean.normsCode}" grade_code="${productBean.slTncGradeCode}"
                                    classes_name="${productBean.pdClassesName}" machining_name="${productBean.machiningName}" breed_name="${productBean.pdBreedName}" feature_name="${productBean.pdFeatureName}" weight_name="${productBean.weightName}" norms_out="${productBean.normsOut}"  grade_name="${productBean.slTncGradeName}"
                                    weight_val="${productBean.weightVal}" brandId="${productBean.brandId}" brandEpId="${productBean.brandEpId}" brandName="${productBean.brandName}">
                                    ${productBean.pdDesc}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>箱数：</td>
                <td><input type="text" id="productBox" name="productBox" style="width:300px;"/></td>
            </tr>
            <tr>
                <td style="white-space:nowrap;">不含包装离岸价(元/kg)：</td>
                <td><input type="text" id="fobFreePackage" name="fobFreePackage" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>包材成本(元/kg)：</td>
                <td><input type="text" id="packageCost" name="packageCost" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>含包装离岸价(元/kg)：</td>
                <td><input type="text" id="fobIncludePackage" name="fobIncludePackage" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>干线运费(元/kg)：</td>
                <td><input type="text" id="trunkFreight" name="trunkFreight" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>到岸价(元/kg)：</td>
                <td><input type="text" id="cif" name="cif" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>本次结算标准价(元/kg)：</td>
                <td><input type="text" id="settkementStandardPrice" name="settkementStandardPrice" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>预付款比例(%)：</td>
                <td><input type="text" id="downPayment" name="downPayment" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><textarea  maxlength="100" type="text" name="remark" id="remark" style="width:300px;"/></td>
            </tr>
        </table>
    </form>
</div>
<div style="margin-top:20px;">
    <msk:button buttonValue="保存" buttonType="button" buttonId="SSC1130403.SAVE"/>
</div>
<script src="<c:url value='/static/js/ssc/SSC1130403.js' />"></script>
