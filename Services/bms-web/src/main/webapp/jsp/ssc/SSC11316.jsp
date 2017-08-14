<%--
    Title: 预入库通知单
    author: xia_xiaojie
    createDate: 2016-07-07
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<msk:codemaster codeType="ProductRecvStatus" viewType="json" modelName="SSC" />

<navigation:header title="预入库通知单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form id="SSC11316Form" action="<c:url value='/SSC11316/search' />" method="post">
        <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
            <h3>
                <label>查询条件</label>
            </h3>
            <div width="100%" >
            <table WIDTH="100%" border="0">
                <tr>
                    <td  align="right" >预入库通知单编号:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="intoStoreCode" name="filterMap['intoStoreCode']" value="${param.filterMap.intoStoreCode}"/>
                    </td>

                    <td  align="right" >发货订单编号:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="deliveryCode" name="filterMap['deliveryCode']" value="${param.filterMap.deliveryCode}"/>
                    </td>

                    <td  align="right" >发货确认单编号:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="deliveryConfirmCode" name="filterMap['deliveryConfirmCode']" value="${param.filterMap.deliveryConfirmCode}"/>
                    </td>

                    <td  align="right" >合同编号:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="contractCode" name="filterMap['contractCode']" value="${param.filterMap.contractCode}"/>
                    </td>
                </tr>

                <tr>
                    <td  align="right" >合同名称:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="contractName" name="filterMap['contractName']" value="${param.filterMap.contractName}"/>
                    </td>

                    <td  align="right" >甲方(采购商):</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="purchaserName" name="filterMap['purchaserName']" value="${param.filterMap.purchaserName}"/>
                    </td>

                    <td  align="right" >乙方(生产商):</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="supplierName" name="filterMap['supplierName']" value="${param.filterMap.supplierName}"/>
                    </td>

                    <td  align="right" >物流区:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="lgcsAreaName" name="filterMap['lgcsAreaName']" value="${param.filterMap.lgcsAreaName}"/>
                    </td>
                </tr>

                <tr>
                    <td  align="right" >到货仓库:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="arriveWarehouse" name="filterMap['arriveWarehouse']" value="${param.filterMap.arriveWarehouse}"/>
                    </td>

                    <td  align="right" >到货车次:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="deliveryBatch" name="filterMap['deliveryBatch']" value="${param.filterMap.deliveryBatch}"/>
                    </td>

                    <td  align="right" >第N车:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="vehicleNumber" name="filterMap['vehicleNumber']" value="${param.filterMap.vehicleNumber}"/>
                    </td>

                    <td  align="right" >运输车辆车牌号:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="licPlateNumber" name="filterMap['licPlateNumber']" value="${param.filterMap.licPlateNumber}"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right" >驾驶员联系方式:</td>
                    <td  align="left" width="350px">　
                        <input type="text" id="driverTel" name="filterMap['driverTel']" value="${param.filterMap.driverTel}"/>
                    </td>
                    <td  align="right" >状态:</td>
                    <td  align="left" width="350px">　
                        <select name="filterMap['productRecvStatus']" style="width: 120px">
                            <option value="">--请选择--</option>
                            <c:forEach items="${ProductRecvStatus}" var="item" varStatus="status">
                                <option value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">
                    预计发货日期 :
                    </td>
                    <td  align="left" width="350px">　
                        <input type="text" id="expectDeliveryDate" name="filterMap['expectDeliveryDate']" value="${param.filterMap.expectDeliveryDate}" readonly/>
                        </td>
                    <td align="right">
                        预计到货日期:
                        </td>
                    <td align="left" width="350px">　
                        <input type="text" id="expectArriveDate" name="filterMap['expectArriveDate']" value="${param.filterMap.expectArriveDate}" readonly/>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td align="right">
                        <msk:button buttonValue="查询" buttonId="SSC11316.SEARCH" buttonType="button"/>
                    </td>
                </tr>
                </table>
                </div>
            </div>
        <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
            <h3>
                <label>
                    预入库通知单列表
                </label>
            </h3>
            <table id="SSC11316_list_grid" width="100%">
                <thead>
                    <tr>
                        <th coltype="sno">序号</th>
                        <th coltype="text" filter="false" name="intoStoreCode">预入库通知单编号</th>
                        <th coltype="text" filter="false" name="deliveryCode">发货订单编号</th>
                        <th coltype="text" filter="false" name="deliveryConfirmCode">发货确认单编号</th>
                        <th coltype="text" filter="false" name="contractCode">合同编号</th>
                        <th coltype="text" filter="false" name="contractName">合同名称</th>
                        <th coltype="text" filter="false" name="purchaserName">甲方(采购商)</th>
                        <th coltype="text" filter="false" name="supplierName">乙方(生产商)</th>
                        <th coltype="text" filter="false" name="expectDeliveryDate">预计发货日期</th>
                        <th coltype="text" filter="false" name="expectArriveDate">预计到货日期</th>
                        <th coltype="text" filter="false" name="lgcsAreaName">物流区</th>
                        <th coltype="text" filter="false" name="arriveWarehouse">到货仓库</th>
                        <th coltype="text" filter="false" name="deliveryBatch">到货车次</th>
                        <th coltype="text" filter="false" name="vehicleNumber">第N车</th>
                        <th coltype="text" filter="false" name="licPlateNumber">运输车辆车牌号</th>
                        <th coltype="text" filter="false" name="driverTel">驾驶员联系方式</th>
                        <th coltype="code" filter="false" name="productRecvStatus" code2name="PRODUCTRECVSTATUS_JSON" style="width:100px;">&nbsp;&nbsp;状态&nbsp;&nbsp;</th>
                        <th coltype="action" width="2%">
                            操作
                            <msk:button buttonType="hidden" buttonValue="预入库通知单详细" buttonId="SSC11317.INIT" coltype="detail"></msk:button>
                            <msk:button buttonType="hidden" buttonValue="删除" buttonId="SSC11317.DETELE" coltype="delete" useable="can_delete"></msk:button>
                        </th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            </div>
    </form>
</div>

<script src="<c:url value="/static/js/ssc/SSC11316.js" />"></script>
