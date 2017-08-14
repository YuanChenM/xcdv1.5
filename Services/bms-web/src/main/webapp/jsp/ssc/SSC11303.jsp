<%--
  title="合同管理一览画面"
  author"tao_zhifa"
  Created by IntelliJ IDEA.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC" />
<msk:codemaster codeType="RelationType" viewType="json" modelName="SSC" />

<navigation:header title="合同管理一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form id="SSC11303_form" action="<c:url value='/SSC11303/search' />" method="post">
        <div class="group-accordion" active="true">
            <h3><label>查询条件</label></h3>
            <div>
                <table width="100%">
                    <tr>
                        <td style="text-align:right; white-space:nowrap;">合同编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['contractCode']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">合同名称&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['contractName']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">中标成交确认书编号&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['bidProjectNo']" style="width:135px;" />
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
                        <td style="text-align:right; white-space:nowrap;">合同生效日期&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <input type="text" name="filterMap['contractActDateStr']" style="width:135px;" />
                        </td>
                        <td style="text-align:right; white-space:nowrap;">合同状态&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <select name="filterMap['contractStatus']" style="border:1px solid #888888; width:142px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractStatuses}" var="contractStatus">
                                    <option value="${contractStatus.key}">${contractStatus.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align:right; white-space:nowrap;">合同关联中标类型&nbsp;:&nbsp;</td>
                        <td style="text-align:left;">
                            <select name="filterMap['bidRelationType']" style="border:1px solid #888888; width:142px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${relationTypes}" var="relationType">
                                    <option value="${relationType.key}">${relationType.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7"></td>
                        <td style="text-align:right;">
                            <msk:button buttonType="button" buttonValue="查询" buttonId="SSC11303.SEARCH" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" active="true">
            <h3><label>合同列表</label></h3>
            <div>
                <table id="SCC11303_contract_list" width="100%">
                    <thead>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="contractCode">合同编号</th>
                    <th coltype="text" name="contractName">合同名称</th>
                    <th coltype="text" name="bidProjectNo">中标成交确认书编号</th>
                    <th coltype="text" name="purchaserName">甲方(采购商)</th>
                    <th coltype="text" name="supplierName">乙方(生产商)</th>
                    <th coltype="text" name="contractActDateStr">合同生效日期</th>
                    <th coltype="code" name="contractStatus" code2name="SSCORDERSTATUS_JSON" style="width:10%;">合同状态</th>
                    <th coltype="code" name="bidRelationType" code2name="RELATIONTYPE_JSON" style="width:10%;">合同关联中标类型</th>
                    <th coltype="action">
                        操作
                        <msk:button buttonType="hidden" buttonValue="合同详细" buttonId="SSC11303.DELIVERYORDER" coltype="detail" />
                        <msk:button buttonType="hidden" buttonValue="删除" buttonId="SSC11303.DELETE" coltype="delete" useable="can_delete" />
                        <msk:button buttonType="hidden" buttonValue="查看付款申请" buttonId="SSC11303.PAYMONEYDETAIL" coltype="audit" useable="can_abolish" />
                    </th>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div>
            <msk:button buttonType="button" buttonValue="新增" buttonId="SSC11303.ADD" />
        </div>
    </form>
</div>
<script src="<c:url value='/static/js/ssc/SSC11303.js' />" />