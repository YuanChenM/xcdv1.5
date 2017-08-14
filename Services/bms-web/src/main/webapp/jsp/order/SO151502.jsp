<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<link rel="stylesheet" href="<c:url value='/static/js/treegrid/css/jquery.treegrid.css'/>"/>
<script type="text/javascript" src="<c:url value="/static/js/treegrid/js/jquery.treegrid.js"/>"></script>
<input type="hidden" id="printUrl" value="/async/print/start">
<input type="hidden" id="orderId" value="${orderId}">
<input type="hidden" id="subOrderId" value="${subOrderId}">
<input type="hidden" id="subOrderIds" value="${subOrderIds}">
<input type="hidden" id="orderCode" value="${orderCode}">
<input type="hidden" id="orderTypeHidden" value="${so151502Bean.orderType}">
<input type="hidden" id="ver" value="${so151502Bean.ver}">
<style>
    html {
        overflow-x: hidden;
    }
</style>

<c:choose>
    <c:when test="${enterMark eq 'return'}">
        <navigation:header title="订单详细信息" backTitleArray="退货单列表" backUrlArray="../SO151506/init"></navigation:header>
    </c:when>
    <c:otherwise>
        <navigation:header title="订单详细信息" backTitleArray="订单列表" backUrlArray="../SO151501/init"></navigation:header>
    </c:otherwise>
</c:choose>
<div  class="page-content list-page">
    <div id="point">
        <div id="baseorder"></div>
        <div id="orderbuyers"></div>
        <div id="basedelivery"></div>
        <div id="logisticsDetail">
        </div>
        <div id="orderDetail">
            <div class="group-accordion" active="true">
                <h3>
                    <label>订单明细</label>
                </h3>

                <div>
                    <table class="tree dataTable no-footer" id="detailTable" style="width: 100%">
                        <tr style="background:#DBDBDB">
                            <td width="8%">期望配送日</td>
                            <td width="8%">产品编码</td>
                            <td width="10%">产品名称</td>
                            <td width="8%">单价(元/箱)</td>
                            <td width="10%">金额(元)</td>
                            <td width="8%">订购数量</td>
                            <td width="8%">发货数量</td>
                            <td width="8%">收货数量</td>
                            <td width="8%">取消数量</td>
                            <td width="8%">退货数量</td>
                            <td width="8%">拒收数量</td>
                            <td width="8%">状态</td>
                        </tr>
                        <c:forEach items="${orderDetailList}" var="orderDetail" varStatus="i">
                            <tr class="treegrid-${orderDetail.orderDetailId}">
                                <td align="left">
                                    <fmt:formatDate value="${orderDetail.proDate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${orderDetail.pdCode}</td>
                                <td>${orderDetail.pdName}</td>
                                <td align="right">${orderDetail.pdPrice}</td>
                                <td align="right"><fmt:formatNumber value="${orderDetail.pdPrice*orderDetail.orderQty}" pattern="#,#00.00#"/> </td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###"  value="${orderDetail.orderQty}"/></td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###"  value="${orderDetail.sendQty}"/></td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${orderDetail.receiveQty}"/></td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${orderDetail.cancelQty}"/></td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###"  value="${orderDetail.returnQty}"/></td>
                                <td align="right"><fmt:formatNumber type="number" pattern="###,###"  value="${orderDetail.rejectionQty}"/></td>
                                <td>
                                    <msk:codemaster codeType="SubOrderDetailStatus" viewType="label" modelName="ORDER"
                                                    codeValue="${orderDetail.detailStatus}"/>
                                    <c:if test="${orderDetail.detailStatus <= 1000}">
                                        <%--   <img alt="编辑" name="orderDetailInfo" orderDetailId="${orderDetail.orderDetailId}" src="${ctx}/static/core/images/action/edit.png" title="编辑">--%>
                                    </c:if>
                                </td>
                            </tr>
                            <c:if test="${orderDetail.orderShipDetailList.size() gt 0}">
                                <tr class="treegrid-${orderDetail.orderDetailId}-${i.index} treegrid-parent-${orderDetail.orderDetailId}"
                                    style="background:#DBDBDB">
                                    <td></td>
                                    <td>供应商编号</td>
                                    <td colspan="3">供应商名称</td>
                                    <td>供货数量</td>
                                    <td>发货数量</td>
                                    <td>收货数量</td>
                                    <td>取消数量</td>
                                    <td>退货数量</td>
                                    <td>拒收数量</td>
                                    <td></td>
                                </tr>
                                <c:forEach items="${orderDetail.orderShipDetailList}" var="soOrderShipDetail">
                                    <tr class="treegrid-${orderDetail.orderDetailId}-${i.index} treegrid-parent-${orderDetail.orderDetailId}">
                                        <td></td>
                                        <td>${soOrderShipDetail.supplierCode}</td>
                                        <td colspan="3">${soOrderShipDetail.supplierName}</td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.suppQty}"/></td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.sendQty}"/></td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.receiveQty}"/></td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.cancelQty}"/></td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.returnQty}"/></td>
                                        <td align="right"><fmt:formatNumber type="number" pattern="###,###"
                                                                            value="${soOrderShipDetail.rejectionQty}"/></td>
                                        <td>
                                                <%--  <img alt="编辑" name="SO151502" orderDetailAvailabilityId="${soOrderShipDetail.shipDetailId}" src="${ctx}/static/core/images/action/edit.png" title="编辑">--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div>
            <table>
                <tr>
                    <%-- <td><msk:button buttonValue="打印需求订单" buttonId="SO251108.NEW" buttonType="hidden"/></td>
                     <td><msk:button buttonValue="打印确认订单" buttonId="SO251108.MODIFY" buttonType="hidden"/></td>
                     <td><msk:button buttonValue="打印配送订单" buttonId="SO251108.DELETE" buttonType="hidden"/></td>
                     <c:if test="${so151502Bean.orderStatus eq '12'|| so151502Bean.orderStatus eq '9'}">
                         <td><msk:button buttonValue="订单完成" buttonId="SO251108.OVER" buttonType="button"/></td>
                     </c:if>
                     <c:if test="${so151502Bean.orderType eq 4 and so151502Bean.orderStatus eq 2}">
                         <td><msk:button buttonValue="已付款" buttonId="SO251108.CHANGE" buttonType="button"/></td>
                     </c:if>--%>
                    <c:if test="${so151502Bean.orderStatus le 9}">
                        <td><msk:button buttonValue="订单取消" buttonId="SO151502.CANCEL" buttonType="button"
                                        needAuth="true"/></td>
                    </c:if>
                    <td><msk:button buttonValue="PDF导出" buttonId="SO151502.PRINT" buttonType="button"/></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/order/SO151502.js"/>"></script>
<script src='<c:url value="/static/js/core/utils.js"/>'/>
<script src='<c:url value="/static/js/loading/download.js"/>'/>


