<%--
    Title:发货订单一览
    author:yuan_zhifei
    createDate:2016-07-04
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="DeliveryOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="RelationType" viewType="json" modelName="SSC"/>
<navigation:header title="发货订单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">

    <form action="<c:url value="/SSC11305/search"/>" method="post" id="SSC11305Form" >
        <input type="hidden" name="contractCode" value="${contractCode}">
        <c:if test="${null!=contractCode}">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    基本信息
                </h3>
                <table width="100%">
                    <tr>
                        <td width="10%" align="right">合同编号:</td>
                        <td>${contractCode}</td>
                    </tr>
                    <tr>
                        <td width="10%" align="right">合同名称:</td>
                        <td> ${contractName}</td>
                    </tr>
                    <tr>
                        <td width="10%" align="right">甲方(采购商):</td>
                        <td>${purchaserName}</td>
                    </tr>
                    <tr>
                        <td width="10%" align="right">乙方(生产商):</td>
                        <td>${supplierName}</td>
                    </tr>
                </table>
            </div>
            <br/>
            <br/>
        </c:if>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <div width="100%">
                <table WIDTH="100%">
                    <tr>
                        <td align="right">发货订单编号 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['deliveryCode']"/>
                        </td>
                        <td align="right">合同编号 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['contractCode']"/>
                        </td>
                        <td align="right" style="white-space:pre;">合同名称 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['contractName']"/>
                        </td>
                        <td align="right" style="white-space:pre;">关联合同类型 :</td>
                        <td align="left">　
                            <select name="filterMap['contractRelationType']" style="width: 137px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${relationTypeList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="white-space:pre;">甲方（采购商） :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['purchaserName']"/>
                        </td>
                        <td align="right" style="white-space:pre;">乙方（生产商） :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['supplierName']"/>
                        </td>
                        <td align="right">物流区 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['lgcsAreaName']"/>
                        </td>
                        <td align="right">到货车次 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['deliveryBatch']"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="white-space:pre;">计划到货时间 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['etaStr']"/>
                        </td>
                        <td align="right">发货订单状态 :</td>
                        <td align="left" colspan="3">　
                            <select name="filterMap['deliveryStatus']" style="width: 137px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${deliveryOrderStatusList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" colspan="8">
                            <msk:button buttonValue="查询" buttonId="SSC11305.SEARCH" buttonType="button"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>发货订单列表</label>
            </h3>
            <table width="100%" id="SSC11305_list_grid">
                <thead>
                <tr>
                    <c:if test="${null==contractCode}">
                        <th coltype="sno">序号</th>
                    </c:if>
                    <th coltype="text" width="15%" name="deliveryCode">发货订单编号</th>
                    <c:if test="${null==contractCode}">
                        <th coltype="text"width="15%" name="contractCode">合同编号</th>
                        <th coltype="text"width="25%" name="contractName">合同名称</th>
                        <th coltype="code" width="10%" name="contractRelationType" code2name="RELATIONTYPE_JSON">关联合同类型</th>
                        <th coltype="text" width="15%" name="purchaserName">甲方（采购商）</th>
                        <th coltype="text" width="15%" name="supplierName">乙方（生产商）</th>
                    </c:if>
                    <th coltype="text" width="15%" name="lgcsAreaName">物流区</th>
                    <th coltype="text" width="15%" name="deliveryBatch">到货车次</th>
                    <th coltype="text" width="15%" name="etaStr" id="etaStr">计划到货时间</th>
                    <th coltype="code" width="10%" name="deliveryStatus" code2name="DELIVERYORDERSTATUS_JSON">发货订单状态</th>
                    <th coltype="action" width="15%">操作
                        <msk:button buttonValue="发货订单详细" buttonId="SSC11305.DETAIL" buttonType="hidden"
                                    coltype="detail"/>
                        <msk:button buttonValue="删除" buttonId="SSC11305.DELETE"  buttonType="hidden"
                                    coltype="delete" useable="can_abolish"/>

                    </th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <c:if test="${null==contractCode}">
            <msk:button buttonValue="新增" buttonId="SSC11305.ADD" buttonType="button"/>
        </c:if>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSC11305.js"/>"></script>
