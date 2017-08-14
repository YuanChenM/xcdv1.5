<%--
  Created by IntelliJ IDEA.
  User: wang_jianzhou
  Date: 2016/8/4
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="navigation" uri="/msk" %>
<link rel="stylesheet" href='<c:url value="/static/js/treegrid/css/jquery.treegrid.css"/>'>
<script type="text/javascript" src='<c:url value="/static/js/treegrid/js/jquery.treegrid.js" />'></script>
<input type="hidden" id="orderId" name="orderId" value="${orderId}">
<input type="hidden" id="subOrderId" name="subOrderId" value="${subOrderId}">
<style>
    html { overflow-x:hidden; }
</style>
<div class="page-content list-page">
    <div id="baseorder"></div>
    <div id="orderbuyers"></div>
    <div id="basedelivery"></div>
    <div id="actualdelivery"></div>
    <div id="orderDetail">
        <div class="group-accordion" active="true">
            <h3>
                <label>发货单详细</label>
            </h3>
            <div>
                <table class="tree dataTable no-footer" id = "detailTable" style="width: 100%">
                    <tr style="background:#DBDBDB">
                        <td width="6.5%">发货单ID</td>
                        <%--<td width="6.5%">预计发货时间</td>
                        <td width="6.5%">预计到货时间</td>--%>
                        <td width="6.5%">收货人名称</td>
                        <td width="6.5%">收货人电话</td>
                        <td width="6.5%">收货地址省</td>
                        <td width="6.5%">收货地址市</td>
                        <td width="6.5%">收货地址区</td>
                        <td width="8.5%">收货地址详细地址</td>
                        <td width="8.5%">辅助地址</td>
                        <td width="10%">收货人详细地址管理Key</td>
                        <td width="6.5%">付款类型</td>
                        <%--<td width="8%">配送方式</td>--%>
                        <td width="6.5%">发货单状态</td>
                        <td width="6.5%"></td>
                        <td width="6.5%"></td>
                    </tr>
                    <c:forEach items="${so151510Bean.beanList}" var="shipInfo" varStatus="i">
                        <tr class="treegrid-${shipInfo.shipId}">
                            <td>${shipInfo.shipId}</td>
                            <%--<td>${shipInfo.forecastSendTimeStr}</td>
                            <td>${shipInfo.forecastReceiveTimeStr}</td>--%>
                            <td>${shipInfo.receiverName}</td>
                            <td>${shipInfo.receiverTel}</td>
                            <td>${shipInfo.receiverProvince}</td>
                            <td>${shipInfo.receiverCity}</td>
                            <td>${shipInfo.receiverDistrict}</td>
                            <td>${shipInfo.receiverAddr}</td>
                            <td>${shipInfo.receiverAddr2}</td>
                            <td>${shipInfo.receiverAddrKey}</td>
                            <td>
                                <c:if test="${shipInfo.paymentType != null}">
                                    <msk:codemaster codeType="PaymentType" viewType="label" modelName="ORDER" codeValue="${shipInfo.paymentType}"/>
                                </c:if>
                            </td>
                            <%--<td>
                                <c:if test="${shipInfo.deliveryType != null}">
                                    <msk:codemaster codeType="DeliveryType" viewType="label" modelName="ORDER" codeValue="${shipInfo.deliveryType}"/>
                                </c:if>
                            </td>--%>
                            <td>
                                <msk:codemaster codeType="ShipStatus" viewType="label" modelName="ORDER" codeValue="${shipInfo.shipStatus}"/>
                                <msk:roleArea pageCode="SO151510" areaCode="CANCEL" needAuth="true">
                                <c:if test="${shipInfo.shipStatus <= 3 && so151510Bean.btnFlag eq (true)}">
                                    <img alt="取消" name="cancel" shipId="${shipInfo.shipId}" src='<c:url value="/static/images/action/edit.png" />'title="取消">
                                </c:if>
                                </msk:roleArea>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:if test="${shipInfo.soOrderShipDetails.size() gt 0}">
                            <tr class="treegrid-${soOrderShipDetails.shipId}-${i.index} treegrid-parent-${shipInfo.shipId}" style="background:#DBDBDB">
                                <td></td>
                                <td>供应商编号</td>
                                <td colspan="2">供应商名称</td>
                                <td>产品编码</td>
                                <td colspan="2">产品名称</td>
                                <%--<td>产品等级</td>--%>
                                <td>供货数量</td>
                                <td>发货数量</td>
                                <td>收货数量</td>
                                <td>取消数量</td>
                                <td>退货数量</td>
                                <td>拒收数量</td>
                            </tr>
                            <%-- shipDetail--%>
                            <c:forEach items="${shipInfo.soOrderShipDetails}" var="soOrderShipDetailList">
                                <tr class="treegrid-${soOrderShipDetailList.shipId}-${i.index} treegrid-parent-${shipInfo.shipId}">
                                    <td></td>
                                    <td>${soOrderShipDetailList.supplierCode}</td>
                                    <td colspan="2">${soOrderShipDetailList.supplierName}</td>
                                    <td>${soOrderShipDetailList.pdCode}</td>
                                    <td colspan="2">${soOrderShipDetailList.pdName}</td>
                                    <%--<td>${soOrderShipDetailList.pdGradeName}</td>--%>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.suppQty}"/></td>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.sendQty}"/></td>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.receiveQty}"/></td>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.cancelQty}"/></td>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.returnQty}"/></td>
                                    <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${soOrderShipDetailList.rejectionQty}"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script src='<c:url value="/static/js/order/SO151510.js" />'></script>
