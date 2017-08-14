<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/7/4
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<msk:codemaster codeType="DeliveryConfirmStatus" viewType="json" modelName="SSC"/>
<navigation:header title="订单发货确认单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form id="SSC11314FormSearch" action="<c:url value="/SSC11314/search"/>" method="post">

        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <div width="100%" style="border-right: none; border-top: none;">
                <table WIDTH="100%" border="0">
                <tr>
                    <td  align="right"  style="white-space:pre;">发货确认单编号 :</td>
                    <td  align="left" >　
                        <input type="text" name="filterMap['deliveryConfirmCode']" value="${param.filterMap.deliveryConfirmCode}"/>
                    </td>
                    <td  align="right"  style="white-space:pre;">发货订单编号 :</td>
                    <td  align="left">　
                        <input type="text"   name="filterMap['deliveryCode']" value="${param.filterMap.deliveryCode}" />
                    </td>
                    <td  align="right" >合同编号 :</td>
                    <td  align="left">　
                        <input type="text"  name="filterMap['contractCode']" value="${param.filterMap.contractCode}"/>
                    </td>
                    <td  align="right"  style="white-space:pre;">合同名称 :</td>
                    <td  align="left">　
                        <input type="text"   name="filterMap['contractName']" value="${param.filterMap.contractName}" />
                    </td>
                </tr>
                <tr>
                    <td  align="right" >采购方 :</td>
                    <td  align="left" >　
                        <input type="text"  name="filterMap['purchaserName']" value="${param.filterMap.purchaserName}" />
                    </td>
                    <td  align="right" >供应商 :</td>
                    <td  align="left">　
                        <input type="text"   name="filterMap['supplierName']" value="${param.filterMap.supplierName}" />
                    </td>
                    <td  align="right" >物流区 :</td>
                    <td  align="left">　
                        <input type="text"  name="filterMap['lgcsAreaName']" value="${param.filterMap.lgcsAreaName}" />
                    </td>
                    <td  align="right"  style="white-space:pre;">状态 :</td>
                    <td  align="left">　
                        <select name="filterMap['deliveryConfirmStatus']" style="width: 138px">
                            <option value="">--请选择--</option>
                            <c:forEach items="${deliveryConfirmStatusList}" var="item" varStatus="status">
                                <option value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td  align="right" >目标仓库 :</td>
                    <td  align="left" >　
                        <input type="text"   name="filterMap['arriveWarehouse']" value="${param.filterMap.arriveWarehouse}" />
                    </td>
                    <td  align="right" >目标仓库地址 :</td>
                    <td  align="left">　
                        <input type="text"   name="filterMap['arriveWarehouseAddr']" value="${param.filterMap.arriveWarehouseAddr}"/>
                    </td>
                    <td  align="right"  style="white-space:pre;">到货车次 :</td>
                    <td  align="left">　
                        <input type="text"  name="filterMap['deliveryBatch']" value="${param.filterMap.deliveryBatch}" />
                    </td>
                    <td  align="right" ></td>
                    <td  align="left">　
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="8">
                        <msk:button buttonValue="查询" buttonId="SSC11314.SEARCH" buttonType="button"/>
                    </td>
                </tr>
            </table>
            </div>
        </div>

        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>订单发货确认单列表</label>
            </h3>
            <table id="SCC11314_list_grid" width="100%">
                <thead>
                <th coltype="sno"  width="2%">序号</th>
                <th coltype="text" width="10%" name="deliveryConfirmCode">发货确认单编号</th>
                <th coltype="text" width="10%" name="deliveryCode">发货订单编号</th>
                <th coltype="text" width="10%" name="contractCode">合同编号</th>
                <th coltype="text" width="10%" name="contractName">合同名称</th>
                <th coltype="text" width="10%" name="purchaserName">采购方</th>
                <th coltype="text" width="10%" name="supplierName">供应商</th>
                <th coltype="text" width="10%" name="lgcsAreaName">物流区</th>
                <th coltype="text" width="10%" name="arriveWarehouse">目标仓库</th>
                <th coltype="text" width="10%" name="arriveWarehouseAddr">目标仓库地址</th>
                <th coltype="text" width="5%" name="deliveryBatch">到货车次</th>
                <th coltype="code" width="10%"  name="deliveryConfirmStatus" code2name="DELIVERYCONFIRMSTATUS_JSON">状态</th>
                <th coltype="action" width="2%">
                    操作
                    <msk:button buttonType="hidden" buttonValue="发货确认单详细" buttonId="SSC11314.EDIT" coltype="detail"/>
                    <msk:button buttonType="hidden" buttonValue="删除" buttonId="SSC11314.DELETE" coltype="delete" useable="can_abolish"/>
                </th>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </form>
    <msk:button buttonType="button" buttonId="SSC11314.NEW" buttonValue="新增"/>
</div>
<script src="<c:url value='/static/js/ssc/SSC11314.js'/>"></script>