<%--
    Title：产品详细信息页面
    author：zhao_chen1
    createDate：2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div>
    <form action="<c:url value='/SSC11304/saveContractPackageM'/>" id="SSC1130404Form" method="post">
        <input type="hidden" name="contractId" value="${contractId}" id="contractId">
        <c:forEach items="${pdBeans}" var="pd">
            <input type="hidden" id="pd_${pd.detailId}" value="${pd.classesCode},${pd.machiningCode},${pd.breedCode},${pd.featureCode},${pd.weightCode}">
        </c:forEach>
        <table>
            <tr>
                <td>产品：</td>
                <td>
                    <select name="detailId" id="product" style="border:1px solid #888888; width:307px;">
                        <option value="">--&nbsp;请选择&nbsp;--</option>
                        <c:forEach items="${pdBeans}" var="pd">
                            <option value="${pd.detailId}">${pd.pdName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td style="white-space:nowrap;">(神农卫士包材标准)纸箱质量标准：</td>
                <td><input maxlength="100" type="text" id="cartonQuaSta" name="cartonQuaSta" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(神农卫士包材标准)纸箱规格：</td>
                <td><input maxlength="100" type="text" id="cartonSpecSta" name="cartonSpecSta" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(神农卫士包材标准)内袋质量标准：</td>
                <td><input maxlength="100" type="text" id="innerBagQuaSta" name="innerBagQuaSta" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(神农卫士包材标准)内袋规格：</td>
                <td><input maxlength="100" type="text" id="innerBagSpecSta" name="innerBagSpecSta" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(本地订单包材信息)纸箱质量标准：</td>
                <td><input maxlength="100" type="text" id="cartonQua" name="cartonQua" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(本地订单包材信息)纸箱规格：</td>
                <td><input maxlength="100" type="text" id="cartonSpec" name="cartonSpec" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(本地订单包材信息)内袋质量标准：</td>
                <td><input maxlength="100" type="text" id="innerBagQua" name="innerBagQua"  style="width:300px;"/></td>
            </tr>
            <tr>
                <td>(本地订单包材信息)内袋规格：</td>
                <td><input maxlength="100" type="text" id="innerBagSpec" name="innerBagSpec" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>本次纸箱需求量：</td>
                <td><input type="text" id="cartonUseNum" name="cartonUseNum" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>本次内袋需求量：</td>
                <td><input type="text" id="innerBagUseNum" name="innerBagUseNum" style="width:300px;"/></td>
            </tr>
            <tr>
                <td>包材提供方式：</td>
                <td>
                    <select id="supplyMode" name="supplyMode" style="border:1px solid #888888; width:307px;">
                        <option value="0">甲供</option>
                        <option value="1">乙供</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>包材审核方式：</td>
                <td>
                    <select id="auditMethod" name="auditMethod" style="border:1px solid #888888; width:307px;">
                        <option value="0">寄送样品审核</option>
                        <option value="1">到货审核</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>包材结算方式：</td>
                <td>
                    <select id="settlementMethod" name="settlementMethod" style="border:1px solid #888888; width:307px;">
                        <option value="0">产品价内</option>
                        <option value="1">产品价外</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div style="margin-top:15px;">
    <msk:button buttonValue="保存" buttonType="button" buttonId="SSC1130404.SAVE" />
</div>

<script src="<c:url value='/static/js/ssc/SSCCommon.js' />"></script>
<script src='<c:url value="/static/js/ssc/SSC1130404.js"/>'></script>
