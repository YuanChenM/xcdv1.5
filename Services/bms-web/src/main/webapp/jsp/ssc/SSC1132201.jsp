<%--
    Title: 核销单详情之差异单详情
    author: xia_xiaojie
    createDate: 2016-08-10
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!-- 差异单详情 -->
<div>
    <table class="dataTable no-footer" width="100%">
        <thead>
        <tr>
            <th rowspan="2">序号</th>
            <th rowspan="2">产品信息</th>
            <th colspan="4">入库单</th>
            <th colspan="4">发货订单</th>
            <th rowspan="2">单价差(元/kg)</th>
            <th rowspan="2">重量差(kg)</th>
            <th rowspan="2">金额差(元)</th>
        </tr>
        <tr>
            <th>箱数</th>
            <th>重量(kg)</th>
            <th>单价(元/kg)</th>
            <th>金额(元)</th>
            <th>箱数</th>
            <th>重量(kg)</th>
            <th>单价(元/kg)</th>
            <th>金额(元)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${differDetails}" var="differDetail" varStatus="status">
            <tr>
                <td align="center">${status.index + 1}</td>
                <td align="left">${differDetail.productName}</td>
                <td align="right">${differDetail.receiveBoxStr}</td>
                <td align="right">${differDetail.receiveWeightStr}</td>
                <td align="right">${differDetail.receivePriceStr}</td>
                <td align="right">${differDetail.receiveAmountStr}</td>
                <td align="right">${differDetail.sendBoxStr}</td>
                <td align="right">${differDetail.sendWeightStr}</td>
                <td align="right">${differDetail.sendPriceStr}</td>
                <td align="right">${differDetail.sendAmountStr}</td>
                <td align="right">${differDetail.priceDiffStr}</td>
                <td align="right">${differDetail.weightDiffStr}</td>
                <td align="right">${differDetail.amountDiffStr}</td>
            </tr>
        </c:forEach>
        <c:if test="${not empty differDetails}">
            <tr>
                <td colspan="2" style="font-weight:bold; text-align:center;">合计</td>
                <td align="right">${differDetails[0].totalReceiveBoxesStr}</td>
                <td align="right">${differDetails[0].totalReceiveWeightStr}</td>
                <td></td>
                <td align="right">${differDetails[0].totalReceiveAmountStr}</td>
                <td align="right">${differDetails[0].totalSendBoxesStr}</td>
                <td align="right">${differDetails[0].totalSendWeightStr}</td>
                <td></td>
                <td align="right">${differDetails[0].totalSendAmountStr}</td>
                <td></td>
                <td align="right">${differDetails[0].totalWeightDiffStr}</td>
                <td align="right">${differDetails[0].totalAmountDiffStr}</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
