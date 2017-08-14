<%--
    Title:发货订单明细-产品选择
    author:yang_yang
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="page-content list-page"><%--<c:url value="/SSC11306/selectProduct"/>--%>
    <form action="<c:url value="/SSC11306/saveOrderOd"/>"  id="SSC1130601Form"  method="post" >
        <input type="hidden" name="detailId" id="detailId">
        <input type="hidden" name="deliveryId" value="${deliveryId}" id="deliveryId">
        <input type="hidden" name="deliveryCode" value="${deliveryCode}" id="deliveryCode">
        <input type="hidden" name="classesCode" id="classesCode">
        <input type="hidden" name="classesName" id="classesName">
        <input type="hidden" name="machiningCode" id="machiningCode">
        <input type="hidden" name="machiningName" id="machiningName">
        <input type="hidden" name="breedCode" id="breedCode">
        <input type="hidden" name="breedName" id="breedName">
        <input type="hidden" name="featureCode" id="featureCode">
        <input type="hidden" name="featureName" id="featureName">
        <input type="hidden" name="weightCode" id="weightCode">
        <input type="hidden" name="weightName" id="weightName">
        <input type="hidden" name="gradeCode" id="gradeCode">
        <input type="hidden" name="gradeName" id="gradeName">
        <input type="hidden" name="normsCode" id="normsCode">
        <input type="hidden" name="normsName" id="normsName">
        <input type="hidden" name="weightVal" id="weightVal">
        <input type="hidden" name="pdDesc" id="pdDesc">
        <input type="hidden" name="delFlg" id="delFlg">
        <input type="hidden" name="brandEpId" id="brandEpId"/>
        <input type="hidden" name="brandId" id="brandId"/>
        <input type="hidden" name="brandName" id="brandName"/>
        <input type="hidden" name="ver" id="pdVer"/>
        <table>
            <tr>
                <td>产品:</td>
                <td>
                    <select name="pdCode" id="productCode" style="width:307px;">
                        <option value="" label="请选择"/>
                        <c:forEach items="${productList}" var="product">
                            <option value="${product.pdCode}"
                                    brandId="${product.brandId}" brandEpId="${product.brandEpId}" brandName="${product.brandName}"
                                    classes_code="${product.pdClassesCode}" machining_code="${product.machiningCode}" breed_code="${product.pdBreedCode}" feature_code="${product.pdFeatureCode}" weight_code="${product.weightCode}"
                                    classes_name="${product.pdClassesName}" machining_name="${product.machiningName}" breed_name="${product.pdBreedName}" feature_name="${product.pdFeatureName}" weight_name="${product.weightName}"
                                    grade_code="${product.slTncGradeCode}" grade_name="${product.slTncGradeName}" pd_name="${product.pdDesc}"
                                    norms_code="${product.normsCode}" norms_name="${product.normsOut}" weight_val="${product.weightVal}">
                                    ${product.pdDesc}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <%--<tr>
                <td>等级:</td>
                <td id="productGrade"></td>
            </tr>--%>
            <tr>
                <td >发货箱数:</td>
                <td>
                    <input type="hidden" id="productBox" name="productBox"/>
                    <input type="text" id="productBoxShow"  maxlength="10" style="width:300px;" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>
                </td>
            </tr>
            <tr>
                <td >发货数量(kg):</td>
                <td>
                    <input type="text" id="productQuaShow" style="width:300px;" disabled/>
                    <input type="hidden" id="productQua" name="productQua" />
                </td>
            </tr>
            <%--<tr>
                <td>不含包装离岸价:</td>
                <td><input type="text" id="fobFreePackage" name="fobFreePackage" /></td>
            </tr>
            <tr>
                <td>包材成本:</td>
                <td><input type="text" id="packageCost" name="packageCost" /></td>
            </tr>
            <tr>
                <td>含离岸包装价:</td>
                <td><input type="text" id="fobIncludePackage" name="fobIncludePackage" /></td>
            </tr>--%>
            <tr>
                <td>干线运费(元/kg):</td>
                <td>
                    <input type="hidden" id="trunkFreight" name="trunkFreight"/>
                    <input type="text" id="trunkFreightShow" style="width:300px;" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                </td>
            </tr>
            <tr>
                <td>到岸价(元/kg):</td>
                <td>
                    <input type="hidden" id="cif" name="cif"/>
                    <input type="text" id="cifShow" style="width:300px;" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                </td>
            </tr>
            <tr>
                <td>结算标准价(元/kg):</td>
                <td>
                    <input type="hidden" id="settkementStandardPrice" name="settkementStandardPrice"/>
                    <input type="text" id="settkementStandardPriceShow" maxlength="21" style="width:300px;" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                </td>
            </tr>
            <%--<tr>
                <td>不含运费结算标准价(元/kg):</td>
                <td>
                    <input type="text" id="standardPriceShow" style="width:300px;" disabled/>
                    <input type="hidden" id="standardPrice" name="standardPrice" />
                </td>
            </tr>--%>
            <tr>
                <td >货值(元):</td>
                <td>
                    <input type="text" id="productValueShow" style="width:300px;" disabled/>
                    <input type="hidden" id="productValue" name="productValue" />
                </td>
            </tr>
            <tr>
                <td >备注</td>
                <td><textarea type="text" id="remark" name="remark" cols="55" rows="3" style="width:300px;"/> </td>
            </tr>
        </table>
    </form>
    <br>
    <msk:button buttonType="button" buttonId="SSC1130601.SAVE" buttonValue="保存"/>
</div>

<script src="<c:url value="/static/js/ssc/SSC1130601.js"/>"></script>
