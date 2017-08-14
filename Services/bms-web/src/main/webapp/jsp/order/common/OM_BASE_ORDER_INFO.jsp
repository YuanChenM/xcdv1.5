<%--
  买家基本信息
  User: zhangqiang1

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="group-accordion"  active="true"  >
    <h3>
        <label>订单基本信息</label>
    </h3>
    <table style="width: 100%" CellSpacing=4>
        <tr>
            <td width="25%" align="right">分批订单编码 : </td>
            <td width="25%" align="left">${subOrder.subOrderCode}</td>
            <td width="25%" align="right">时间编码 : </td>
            <td width="25%" align="left">${baseOrder.orderViceCode}</td>
        </tr>
        <tr>
            <td width="25%" align="right">物流区 : </td>
            <td colspan="3" align="left">${baseOrder.districtName}</td>
        </tr>
        <tr>
            <td width="25%" align="right">订单来源 : </td>
            <td width="25%" align="left">
                <c:if test="${ not empty baseOrder.orderSource}">
                    <msk:codemaster codeType="OrderSource" viewType="label" modelName="ORDER"
                                    codeValue="${baseOrder.orderSource}"/>
                </c:if>
            </td>
            <td width="25%" align="right">分批订单类型 : </td>
            <td width="25%" align="left">
                <c:if test="${ not empty baseOrder.orderType}">
                    <msk:codemaster codeType="OrderType" viewType="label" modelName="ORDER"
                                    codeValue="${subOrder.orderType}"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right">付款类型 : </td>
            <td width="25%" align="left">
                <c:if test="${ not empty baseOrder.paymentType}">
                    <msk:codemaster codeType="PaymentType" viewType="label" modelName="ORDER"
                                    codeValue="${baseOrder.paymentType}"/>
                </c:if>
            </td>
            <td width="25%" align="right">分批订单总金额(元) : </td>
            <td width="25%" align="left"><fmt:formatNumber value="${subOrderAmount}" pattern="#,#00.00#"/></td>
        </tr>
        <tr>
            <td width="25%" align="right">冻品管家 : </td>
            <td width="25%" align="left">${baseOrder.saName}</td>
            <td width="25%" align="right">订单员 : </td>
            <td width="25%" align="left">${baseOrder.orderTaker}</td>
        </tr>
        <tr>
            <td width="25%" align="right">分批订单创建时间 : </td>

            <td width="25%" align="left"> <fmt:formatDate value="${baseOrder.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td width="25%" align="right">分批订单状态 : </td>
            <td width="25%" align="left">
                <c:if test="${ not empty subOrder.subOrderStatus}">
                    <msk:codemaster codeType="SubOrderStatus" viewType="label" modelName="ORDER"
                                    codeValue="${subOrder.subOrderStatus}"/>
                </c:if>
            </td>
        </tr>
    </table>
</div>

