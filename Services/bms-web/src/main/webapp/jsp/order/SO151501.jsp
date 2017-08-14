<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
<msk:codemaster codeType="OrderSource" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="OrderType" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="OrderStatus" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="SubOrderStatus" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="OrderBuyerType" viewType="json" modelName="ORDER"/>
<input type="hidden" id="151506orderId" value="${orderId}">
<navigation:header title="订单列表" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <form action="<c:url value="/SO151501/search" />" id="SO151501Form" method="post">
        <div class="group-accordion" collapsible="true" active="true" style="width:100%;">
            <h3>
                <label>查询条件</label>
            </h3>
            <table style="width:100%;">
                <tr>
                    <td width="10%" align="right">物流区 : </td>
                    <td width="15%">
                        <select name="filterMap['districtCode']" id="districtCode">
                            <option value="">-----请选择-----</option>
                            <c:forEach items="${districtList}" var="district" varStatus="status">
                                <option value="${district.lgcsAreaCode}">${district.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="10%" align="right">买家编码 : </td>
                    <td width="15%"><input type="text" name="filterMap['buyerCode']" id="buyerCode"/></td>
                    <td width="10%" align="right">买家名称 : </td>
                    <td width="15%"><input type="text" align="right" name="filterMap['buyerName']" id="buyerName"/></td>
                    <td width="10%" align="right">冻品管家 : </td>
                    <td width="15%"><input type="text" align="right" name="filterMap['saName']" id="saName"/></td>
                </tr>
                <tr>
                    <td width="10%" align="right">订单来源 : </td>
                    <td width="15%">
                        <select name="filterMap['orderSource']" id="orderSource">
                            <option value="">-----请选择-----</option>
                            <c:forEach items="${orderSourceMap}" var="orderSource">
                                <option value="${orderSource.key}">${orderSource.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="10%%" align="right">订单类型 : </td>
                    <td width="15%">
                        <select name="filterMap['orderType']" id="orderType">
                            <option value="">-----请选择-----</option>
                            <c:forEach items="${orderTypeMap}" var="orderType">
                                <option value="${orderType.key}">${orderType.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="10%" align="right">订单状态 : </td>
                    <td width="15%">
                        <input type="hidden" name="filterMap['subOrderStatus']" id="subOrderStatus" value="">
                        <select id="checkbox-subOrderStatus">
                            <c:forEach items="${subStatusMap}" var="subStatus" varStatus="status">
                                <option value="${subStatus.key}">${subStatus.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="10%" align="right">订单编码 : </td>
                    <td width="15%"><input type="text" name="filterMap['orderCode']" id="orderCode"/></td>
                </tr>
                <tr>
                    <td width="10%" align="right">订单ID : </td>
                    <td width="15%">
                        <input type="text" name="filterMap['orderId']" id="orderId" value="${orderId}"/>
                    </td>
                    <td width="10%" align="right">订单创建时间 : </td>
                    <td  width="15%">
                        <input type="text" id="orderTimeStart" name="filterMap['orderTimeStart']" readonly="readonly"/>
                    </td>
                    <td width="10%"><label style="margin-left: 40%">~</label></td>
                    <td><input type="text" id="orderTimeEnd" name="filterMap['orderTimeEnd']"
                               readonly="readonly"/></td>
                    <td width="10%"></td>
                    <td width="15%"></td>
                </tr>
                <tr>
                    <td width="10%"></td>
                    <td width="15%"></td>
                    <td width="10%"></td>
                    <td width="15%"></td>
                    <td width="10%"></td>
                    <td width="15%"></td>
                    <td width="10%"></td>
                    <td align="right" width="15%">
                        <msk:button buttonValue="查询" buttonId="SO151501.SEARCH" buttonType="button"/>
                </tr>
            </table>
        </div>
        <table id="SO251101_list_grid">
            <thead>
            <tr>
                <th coltype="text" width="20px" name="orderId">订单ID</th>
                <th coltype="text" width="20px" name="subOrderId">分批订单ID</th>
                <th coltype="text" width="40px" name="orderCode">订单编码</th>
                <th coltype="text" width="7%" name="districtName">物流区</th>
                <th coltype="code" width="7px" name="buyerType" code2name="ORDERBUYERTYPE_JSON">买家类型</th>
                <th coltype="code" width="10px" name="orderSource" code2name="ORDERSOURCE_JSON">订单来源</th>
                <th coltype="code" width="10px" name="orderType" code2name="ORDERTYPE_JSON">订单类型</th>
                <th coltype="text" width="40px" name="buyerCode">买家编码</th>
                <th coltype="text" width="10%" name="buyerName">买家名称</th>
                <th coltype="text" width="10%" name="saName">冻品管家</th>
                <th coltype="text" width="40px" name="orderTimeStr">订单创建时间</th>
                <th coltype="money" accuracy="0" width="10%"  name="orderQty">订单数量</th>
                <th coltype="money"  width="10%" name="orderAmount">订单总金额(元)</th>
                <th coltype="code" width="30px" name="orderStatus" code2name="SUBORDERSTATUS_JSON">订单状态</th>
                <th coltype="action">
                    <msk:button buttonValue="订单详情" buttonId="SO151501.editBtn" buttonType="hidden"
                                coltype="edit"/>
                    <%--  <c:if test="${loginUser.userType ne ('99')}">--%>
                    <msk:button buttonValue="发货单详情" buttonId="SO151501.checkBtn" buttonType="hidden"
                                coltype="check"/>
                    <%--     </c:if>--%>
                    操作
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
    <msk:button buttonType="button" buttonId="SO151501.NEW" url="${pageContext.request.contextPath}/SO151508/init/" buttonValue="新增"/>
    <msk:button buttonValue="订单详细数据导出" buttonId="SO151501.EXPORT" buttonType="button" align="left"/>
    <msk:button buttonValue="订单列表数据导出" buttonId="SO151501.EXPORTORDER" buttonType="button" align="left"/>
</div>
<script src='<c:url value="/static/js/core/utils.js"/>'/>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src="<c:url value="/static/js/order/SO151501.js" />"></script>