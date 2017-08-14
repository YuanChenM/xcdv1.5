<%--
    Title:卖家库存管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/msk" %>
<%@ taglib uri="/codemaster" prefix="msk2"%>
<msk2:codemaster codeType="SupplyPlatform" viewType="json" />
<navigation:header title="卖家库存管理" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO151202/search" id="SO151202Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>

            <div>
                <table>
                    <tr>
                        <td width="15%" align="right">物流区编码</td>
                        <td><input type="text" name="filterMap['lgcsCode']" id="lgcsCode"
                                   value="${param.filterMap.lgcsCode}"/></td>
                        <td coltype="text" width="15%" align="right">仓库编码</td>
                        <td><input type="text" name="filterMap['warehouseCode']" id="warehouseCode"
                                   value="${param.filterMap.warehouseCode}"/></td>

                        <td coltype="text" width="15%" align="right">销售平台</td>
                        <td>
                            <select width="25px" name="filterMap['salePlatform']"  id="salePlatform">
                                <option value="" >--请选择--</option>
                                <c:forEach items="${salePlatformList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td coltype="text" width="15%" align="right">卖家编码</td>
                        <td><input type="text" name="filterMap['slCodeDis']" id="slCodeDis"
                                   value="${param.filterMap.slCodeDis}"/></td>
                        <td width="15%" align="right">产品编码</td>
                        <td><input type="text" align="right" name="filterMap['pdCode']" id="pdCode"
                                   value="${param.filterMap.pdCode}"/></td>
                        <td></td>

                    </tr>
                    <tr>
                        <td width="15%" align="right">物流区名称</td>
                        <td><input type="text" name="filterMap['lgcsName']" id="lgcsName"
                                   value="${param.filterMap.lgcsName}"/></td>
                        <td coltype="text" width="15%" align="right">仓库名称</td>


                        <td><input type="text" name="filterMap['warehouseName']" id="warehouseName"
                                   value="${param.filterMap.warehouseName}"/></td>
                        <td coltype="text" width="15%" align="right">卖家名称</td>
                        <td><input type="text" name="filterMap['slName']" id="slName"
                                   value="${param.filterMap.slName}"/></td>
                        <td width="15%" align="right">产品名称</td>
                        <td><input type="text" align="right" name="filterMap['pdName']" id="pdName"
                                   value="${param.filterMap.pdName}"/></td>
                        <td width="15%"></td>
                        <td align="left"><msk:button buttonValue="查询" buttonId="SO151202.SEARCH" buttonType="button"/>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
        <div>
            <table id="SO151202_list_grid">
                <thead>
                <tr>
                    <th coltype="text" width="40px" name="lgcsCode">物流区编码</th>
                    <th coltype="text" width="40px" name="lgcsName">物流区名称</th>
                    <th coltype="text" width="7%" name="warehouseCode">仓库编码</th>
                    <th coltype="text" width="10%" name="warehouseName">仓库名称</th>
                    <th coltype="text" width="10%"  name="salePlatform" >供货平台</th>
                    <th coltype="text" width="40px" name="slCodeDis">卖家编码</th>
                    <th coltype="text" width="10%" name="slName">卖家名称</th>
                    <th coltype="text" width="10%" name="pdCode">产品编码</th>
                    <th coltype="text" width="30px" name="pdName">产品名称</th>
                    <th coltype="text" width="30px" name="enabledStockQty">可用库存</th>
                    <th coltype="text" width="30px" edit="true" cellEditMode="true" name="stockQty">库存数量</th>
                    <th coltype="action">操作
                        <msk:button buttonValue="库存保存" coltype="save" buttonId="SO151202.saveBtn" buttonType="hidden"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/js/so/SO151202.js"></script>
