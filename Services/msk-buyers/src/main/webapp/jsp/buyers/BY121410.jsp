<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">

    <form id="BY121410SaveForm" action="${ctx}/BY121410/save/" method="post">
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="marketId" value="${marketId}"/>
        <table style="width: 100%;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>商户名称 : </td>
                <td><input type="text" id="merchantName" value="${marketFoodByInfo.merchantName}" name="merchantName"
                           maxlength="20" required="true" requiredMessage="商户名称不能为空"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>商户类型 : </td>
                <td width="12.5%" align="left">
                    <select id="merchantType" class="merchantType" name="merchantType">
                        <option value="">--请选择--</option>
                        <c:forEach items="${merchantType}" var="merchantType">
                            <c:choose>
                                <c:when test="${marketFoodByInfo.merchantType eq merchantType.key}">
                                    <option value="${merchantType.key}" selected>${merchantType.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${merchantType.key}">${merchantType.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">是否目标买家</td>
                <td width="12.5%" align="left">
                    <c:choose>
                        <c:when test="${marketFoodByInfo.isTargetMerchant eq '1'}">
                            <input type="radio" name="isTargetMerchant" value="${marketFoodByInfo.isTargetMerchant}"
                                   checked/>是
                            <input type="radio" name="isTargetMerchant" value="0"/>否
                        </c:when>
                        <c:when test="${marketFoodByInfo.isTargetMerchant eq '0'}">
                            <input type="radio" name="isTargetMerchant" value="1" checked/>是
                            <input type="radio" name="isTargetMerchant" value="${marketFoodByInfo.isTargetMerchant}"
                                   checked/>否
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="isTargetMerchant" value="1" checked/>是
                            <input type="radio" name="isTargetMerchant" value="0"/>否
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">商户总数 : </td>
                <td><input type="text" value="${marketFoodByInfo.totalMerchant}" id="totalMerchant"
                           name="totalMerchant" digits=":true"
                           digitsMessage="商户总数为正整数" min="0" minMessage="商户总数不小于0" min="0" minMessage="商户总数不小于0"
                           max="2147483647" maxMessage="商户总数输入超出范围限制"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">年销售额(万元) : </td>
                <td><input type="text" value="${marketFoodByInfo.annualTurnover}" id="annualTurnover"
                           name="annualTurnover" number="true" numberMessage="年销售额为数字类型"></td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="保存" buttonId="BY121410.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121410.js"></script>