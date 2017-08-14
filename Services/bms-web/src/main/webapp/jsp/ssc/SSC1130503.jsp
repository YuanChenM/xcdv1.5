<%--
    Title:选择发货订单列表
    createDate:2016-10-09
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<div class="page-content list-page">
    <form action="<c:url value="/SSC11305/search"/>" id="SSC1130503Form" method="post" >
        <input type="hidden" name="filterMap[delFlg]" value="${ssc11305RsParam.delFlg}">
        <input type="hidden" name="deliveryStatus" value="${ssc11305RsParam.deliveryStatus}">
        <input type="hidden" name="contractCode" value="${ssc11305RsParam.contractCode}">
        <input type="hidden" name="isDeliveryConfirm" value="${ssc11305RsParam.isDeliveryConfirm}">
        <input type="hidden" name="isPaymentRequest" value="${ssc11305RsParam.isPaymentRequest}">
       <%-- <input type="hidden" name="deliveryIdId" value="${ssc11305RsParam.deliveryIdId}">--%>
        <input type="hidden" id="deliveryCodeId" value="${ssc11305RsParam.deliveryCodeId}"/>

        <div>
            <table width="100%" id="SSC1130503_list_grid">
                <thead>
                <tr>
                    <th coltype="radio"></th>
                    <th coltype="text" filter="true" width="15%" name="deliveryCode">发货订单编号</th>
                    <th coltype="text" filter="true" width="15%" name="contractCode">合同编号</th>
                    <th coltype="text" filter="true" width="25%" name="contractName">合同名称</th>
                    <th coltype="text" filter="true" width="15%" name="purchaserName">甲方（采购商）</th>
                    <th coltype="text" filter="true" width="15%" name="supplierName">乙方（生产商）</th>
                    <th coltype="text" filter="true" width="15%" name="lgcsAreaName">物流区</th>
                    <th coltype="text" filter="true" width="15%" name="deliveryBatch">到货车次</th>
                    <th coltype="text" filter="true" width="15%" name="etaStr" id="etaStr">计划到货时间</th>
                    <th coltype="action" width="10px"></th>
                </tr>
              </thead>
               <tbody></tbody>
            </table>
        </div>
    </form>
    <msk:button buttonValue="确定" buttonId="SSC1130503.CONFIRM" buttonType="button"/>
</div>
<script src="<c:url value="/static/js/ssc/SSC1130503.js"/>" />