<%--
    Title:买家资金池管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="SupplyPlatform" viewType="json" />
<msk:codemaster codeType="TransFlg" viewType="json" />
<msk:codemaster codeType="PaymentType" viewType="json" />
<msk:codemaster codeType="TransType" viewType="json" />
<msk:codemaster codeType="SettlementStatus" viewType="json" />
<msk:codemaster codeType="RoleType" viewType="json" />
<msk:codemaster codeType="PaidType" viewType="json" />
<navigation:header title="买家资金池管理" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO153101/search" id="SO153101Form" method="post" enctype="multipart/form-data">
        <table width="100%"><tr><td>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right" >交易发生日 :</td>
                    <td  align="left" colspan="10">　
                        <input type="text" id="orderTimeStart" name="filterMap['timeStart']" value="${param.filterMap.timeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="orderTimeEnd" name="filterMap['timeEnd']" value="${param.filterMap.timeEnd}"/>
                    </td>
                </tr>
            </table>
        </div>
        </td></tr>
        <tr><td>
        <div>
            <table id="SO153101_list_grid" width="100%">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="datetime" width="7%" name="tranTime">交易发生日</th>
                    <th coltype="text" width="7%" id="businessAssistantCode" name="businessAssistantCode" filter="true">买家编码</th>
                    <th coltype="text" width="7%" id="businessAssistantName" name="businessAssistantName" filter="true">买家名称</th>
                    <th coltype="text" width="7%" id="bsName" name="bsName" filter="true">买手</th>
                    <th coltype="text" width="7%" id="transCode" name="transCode" filter="true">订单编码</th>
                    <th coltype="text" width="5%" id="orderIdStr" name="orderIdStr" filter="true">订单ID</th>
                    <th coltype="code" width="12%" filter="true" name="paymentType" code2name="PAYMENTTYPE_JSON">支付类型</th>
                    <th coltype="code" width="3%" filter="true" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                   <!--   <th coltype="code" width="7%" filter="true" name="transFlg" code2name="TRANSFLG_JSON">交易标志</th> -->
                    <th coltype="money" width="7%" name="orderAmount">订单金额</th>
                    <th coltype="money" width="7%" name="actualDue">应收金额</th>
                    <th coltype="money" width="7%" name="actualPaid">已收金额</th>
                    <th coltype="money" width="7%" name="reliefAmount">减/退金额</th>
                    <th coltype="money" width="7%" name="balance">结余金额</th>

                    <th coltype="code" width="3%" filter="true" name="settlementStatus" code2name="SETTLEMENTSTATUS_JSON">结算状态</th>
                    <!--  <th coltype="text" width="7%" name="remark" filter="true">备注</th> -->
                    <th coltype="action" width="2%">操作
                        <msk2:button buttonValue="交易详情" buttonId="SO153101.DETAIL" buttonType="hidden" coltype="detail" class="h-button" />
                        <msk2:button buttonValue="费用调整" buttonId="SO153101.REDUCE" buttonType="hidden" coltype="edit" class="h-button"  useable="can_abolish" />
                        <msk2:button buttonValue="支付" buttonId="SO153101.PAY" buttonType="hidden" coltype="check" class="h-button"  useable="can_abolish" />
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        </td></tr>
        </table>
    </form>
    <!--<input type="hidden" id="printUrl" value="/excel/async/print/start">-->
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <msk2:button buttonValue="买家资金池数据导出" buttonId="SO153101.EXPORTORDER" buttonType="button" align="left"/>
</div>
<script src="${ctx}/static/so/js/SO153101.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
