<%--
    Title:退款一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="TransType" viewType="json" />
<msk:codemaster codeType="RefundType" viewType="json" />
<msk:codemaster codeType="ReShipFlg" viewType="json" />
<navigation:header title="退款一览" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO153161/search" id="SO153161Form" method="post">
        <table width="100%"><tr><td>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">退款日期 :</td>
                    <td  align="left" colspan="5">　
                        <input type="text" id="refundTimeStart" name="filterMap['refundTimeStart']" value="${param.filterMap.refundTimeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="refundTimeEnd" name="filterMap['refundTimeEnd']" value="${param.filterMap.refundTimeEnd}"/>
                    </td>
                </tr>
            </table>
        </div>
        </td></tr>
        <tr><td>
        <div>
            <table id="SO153161_list_grid" WIDTH="100%">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="refundCode" filter="true">退款编码</th>
                    <th coltype="text" name="transCode" filter="true">订单号</th>
                   <!-- <th coltype="code" width="100px" filter="true" name="transType" code2name="TRANSTYPE_JSON">交易类型</th> -->
                    <th coltype="money" name="refundAmount">退款金额</th>
                    <th coltype="datetime" name="refundTime">退款日期</th>
                    <th coltype="code" width="100px" filter="true" name="refundType" code2name="REFUNDTYPE_JSON">退回费用类型</th>
                    <th coltype="text" name="remark" filter="true" width="300px">备注</th>
                    <th coltype="action" width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
                <tr>
                    <td></td>
                    <td style="border-left:none"></td>
                    <td class="text" style="background-color: rgb(245, 255, 250);">当前页合计:</td>
                    <td class="money" id="currentRefund" name="currentRefund"></td>
                    <td></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                    <td style="border-left:none"></td>
                </tr>
                <tr>
                    <td style="border-top:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td class="text" style="background-color: rgb(245, 255, 250);">总合计:</td>
                    <td class="money" id="totalRefund" name="totalRefund"></td>
                    <td style="border-top:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                    <td style="border-top:none;border-left:none"></td>
                </tr>
            </table>
        </div>
        </td></tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/so/js/SO153161.js"></script>
<style type="text/css">
    table { table-layout: fixed; }
    td {overflow:hidden;text-overflow:ellipsis;}
</style>
