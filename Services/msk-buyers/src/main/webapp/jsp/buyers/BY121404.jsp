
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">

    <form id="BY121404SaveForm" action="${ctx}/BY121404/save/" method="post">
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="marketId" value="${marketId}"/>
        <table style="width: 100%;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>商户名称:</td>
                <td><input type="text" id="merchantName" value="${marketTerminalBasic.merchantName}" name="merchantName" maxlength="100" required="true" requiredMessage="商户名称不能为空"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">商户类型:</td>
                <td width="12.5%" align="left">
                    <select id="merchantType" name="merchantType">
                        <c:forEach items="${merchantType}" var="merchantType">
                            <c:choose>
                                <c:when test="${marketTerminalBasic.merchantType eq merchantType.key}">
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
                        <c:when test="${marketTerminalBasic.isTargetMerchant eq '1'}">
                            <input type="radio" name="isTargetMerchant" value="${marketTerminalBasic.isTargetMerchant}"
                                   checked/>是
                            <input type="radio" name="isTargetMerchant" value="0"/>否
                        </c:when>
                        <c:when test="${marketTerminalBasic.isTargetMerchant eq '0'}">
                            <input type="radio" name="isTargetMerchant" value="1" checked/>是
                            <input type="radio" name="isTargetMerchant" value="${marketTerminalBasic.isTargetMerchant}"
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
                <td width="12.5%" align="right">商户总数:</td>
                <td><input type="number" value="${marketTerminalBasic.totalMerchant}" name="totalMerchant" digits="true"  digitsMessage="商户总数为正整数" min="0" max="2147483647" maxMessage="商户总数超出范围限制"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">年销售额(万元):</td>
                <td><input type="number" value="${marketTerminalBasic.annualTurnover}" name="annualTurnover" number="true" numberMessage="年销售额为数字类型"></td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="保存" buttonId="BY121404.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121404.js"></script>