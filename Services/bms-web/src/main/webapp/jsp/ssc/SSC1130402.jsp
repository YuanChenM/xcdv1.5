<%--
    Title:产品详细信息页面
    author:zhang_qiang1
    createDate:2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div>
    <form action="<c:url value='/SSC11304/deliveryPlan/save' />" id="SSC1130402Form" method="post">
        <input type="hidden" name="contractId" value="${contractId}" id="contractId" />
        <table>
            <tr>
                <td style="white-space:nowrap;">到货日期：</td>
                <td><input type="text" name="arriveDateStr" id="expectArriveDate" style="width:204px;" readonly="readonly" /></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><textarea maxlength="100" type="text" name="remark" id="remark" style="width:204px;" /></td>
            </tr>
        </table>
        <table cellspacing="8">
            <th style="white-space:nowrap;">选择</th>
            <th style="white-space:nowrap;">产品信息</th>
            <th style="white-space:nowrap;">待分配箱数</th>
            <th style="white-space:nowrap;">分配箱数</th>
            <th style="white-space:nowrap;">分配吨数</th>
            <c:forEach items="${pdBeans}" var="pdBean">
                <input type="hidden" value="${pdBean.weightVal}" name="weight" id="weight_${pdBean.pdCode}" />
                <tr>
                    <td style="text-align:center;"><input type="checkbox" id="code_${pdBean.pdCode}" name="productCode" value="${pdBean.pdCode}" /></td>
                    <td style="white-space:nowrap; text-align:left;" id="name_${pdBean.pdCode}">${pdBean.pdName}</td>
                    <td style="text-align:center;" id="leftBoxes${pdBean.pdCode}">${pdBean.plannedBoxes}</td>
                    <td style="text-align:center;"><input type="text" name="boxes" id="boxes_${pdBean.pdCode}" style="width:80px;" /></td>
                    <td style="text-align:center;" id="ton_${pdBean.pdCode}"></td>
                </tr>
            </c:forEach>
            <c:if test="${not empty pdBeans}">
                <tr>
                    <td colspan="4" style="font-weight:bold;">合计</td>
                    <td style="text-align:center;" id="totalTons"></td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
<div style="margin-top:20px;">
    <msk:button buttonValue="保存" buttonType="button" buttonId="SSC1130402.SAVE" />
</div>

<script src="<c:url value='/static/js/ssc/SSCCommon.js' />"></script>
<script src="<c:url value='/static/js/ssc/SSC1130402.js' />"></script>
