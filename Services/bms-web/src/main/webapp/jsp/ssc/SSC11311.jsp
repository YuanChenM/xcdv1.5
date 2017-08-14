<%--
    Title: 乙方(生产商)入库差异单
    author: xia_xiaojie
    createDate: 2016-07-04
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<msk:codemaster codeType="DifferStatus" viewType="json" modelName="SSC" />

<navigation:header title="生产商入库差异单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form id="SSC11311_form" action="<c:url value='/SSC11311/search' />" method="post">
        <div class="group-accordion" active="true">
            <h3><label>查询条件</label></h3>
            <div>
                <table width="100%">
                    <tr>
                        <td style="text-align:right; white-space:nowrap;">差异单编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['differCode']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">合同编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['contractCode']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">合同名称&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['contractName']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">甲方(采购商)&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['purchaserName']" style="width:135px;" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right; white-space:nowrap;">乙方(生产商)&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['supplierName']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">发货订单编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['deliveryCode']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">入库单编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['deliveryPreIntoCode']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">计划发货日期&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['etd']" style="width:135px;" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right; white-space:nowrap;">实际发货日期&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['deliveryDate']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">计划到货日期&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['eta']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">实际到货日期&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['arriveDate']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">差异单状态&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <select name="filterMap['confirmStatus']" style="border:1px solid #888888; width:142px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${differStatuses}" var="differStatus">
                                    <option value="${differStatus.key}">${differStatus.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7"></td>
                        <td style="text-align:right;">
                            <msk:button buttonType="button" buttonValue="查询" buttonId="SSC11311.SEARCH" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" active="true">
            <h3><label>生产商入库差异单列表</label></h3>
            <div>
                <table id="SSC11311_differ_list" width="100%">
                    <thead>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="differCode">差异单编号</th>
                    <th coltype="text" name="contractCode">合同编号</th>
                    <th coltype="text" name="contractName">合同名称</th>
                    <th coltype="text" name="purchaserName">甲方(采购商)</th>
                    <th coltype="text" name="supplierName">乙方(生产商)</th>
                    <th coltype="text" name="deliveryCode">发货订单编号</th>
                    <th coltype="text" name="deliveryPreIntoCode">入库单编号</th>
                    <th coltype="text" name="etd">计划发货日期</th>
                    <th coltype="text" name="deliveryDate">实际发货日期</th>
                    <th coltype="text" name="eta">计划到货日期</th>
                    <th coltype="text" name="arriveDate">实际到货日期</th>
                    <th coltype="code" name="confirmStatus" code2name="DIFFERSTATUS_JSON">差异单状态</th>
                    <th coltype="action">
                        操作
                        <msk:button buttonType="hidden" buttonValue="入库差异单详细" buttonId="SSC11312.INIT" coltype="detail" />
                    </th>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </form>
</div>
<script src="<c:url value='/static/js/ssc/SSC11311.js' />"></script>
