<%--
    Title:卖家资金池一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="PaymentType" viewType="json" />
<msk:codemaster codeType="BillType" viewType="json" />
<msk:codemaster codeType="SettlementStatus" viewType="json" />
<msk:codemaster codeType="RoleType" viewType="json" />
<msk:codemaster codeType="SettlementFlg" viewType="json" />
<msk:codemaster codeType="PaidType" viewType="json" />
<navigation:header title="卖家资金池管理" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SO153111/search" id="SO153111Form" method="post">
        <table width="100%"><tr><td>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">生成时间 :</td>
                    <td align="left" colspan="10">　
                        <input type="text" id="orderTimeStart" name="filterMap['startTime']" value="${param.filterMap.startTime}"/>
                        &emsp;~&emsp;
                        <input type="text" id="orderTimeEnd" name="filterMap['endTime']" value="${param.filterMap.endTime}"/>

                    </td>
                </tr>
            </table>
        </div>
        </td></tr>
        <tr><td>
        <div>
            <%--<span WIDTH="30%" id="cycle" style="background-color: #FFB48F;font-size:14pt;display:inline-block;height: 30px;"></span>--%>
            <table id="SO153111_list_grid">
                <thead>
                <tr>
                    <th width="40px"coltype="sno">序号</th>
                    <th coltype="link" width="40px"  name="accountDate" align="center">账期</th>
                    <th coltype="text" width="7%" filter="true" name="businessMainCode">卖家编码</th>
                    <th coltype="text" width="10%" filter="true" name="businessMainName">卖家名称</th>
                    <!--<th coltype="code" width="10%"  name="businessMainRole" code2name="ROLETYPE_JSON">收款方角色</th>-->
                    <th coltype="text"  width="40px" name="startDateStr">开始时间</th>
                    <th coltype="text" width="10%" name="endDateStr">结束时间</th>
                    <%--<th coltype="code" width="10%" filter="true" name="paymentType" code2name="PAYMENTTYPE_JSON">支付类型</th>--%>
                    <th coltype="code" width="3%" filter="true" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                    <th coltype="code" width="10%" filter="true" name="billType" code2name="BILLTYPE_JSON">计费类型</th>
                    <th coltype="money" width="30px" name="billAmount">计费金额</th>
                    <th coltype="money" width="30px" name="realReceiveable">应付金额</th>
                    <th coltype="money" width="30px" name="realSettlementAmount">实付金额</th>
                    <th coltype="money" width="7%" name="ajustAmount">减/退金额</th>
                    <th coltype="money" width="30px" name="unSettlementAmount">结余金额</th>
                    <th coltype="code" width="5%" filter="true" name="settlementStatus" code2name="SETTLEMENTSTATUS_JSON">结算状态</th>
                    <th coltype="code" width="10%" filter="true" name="settlementFlg" code2name="SETTLEMENTFLG_JSON">结算标志</th>
                    <!-- <th coltype="money" width="30px" name="handingCharge">手续费</th>  -->
                    <!-- <th coltype="text" width="30px" filter="true" name="remark" >备注</th> -->
                    <th coltype="action" width="60px">操作
                        <msk2:button buttonValue="详情" buttonId="SO153111.DETAIL" buttonType="hidden" coltype="detail" class="h-button" />
                        <msk2:button buttonValue="打款" buttonId="SO153111.PAYMONEY" buttonType="hidden" coltype="edit" class="h-button" />
                        <msk2:button buttonValue="完成" buttonId="SO153111.FINISH" buttonType="hidden" coltype="check" class="h-button" />
                        <msk2:button buttonValue="费用调整" buttonId="SO153111.REDUCE" buttonType="hidden" coltype="export" class="h-button"/>
                        <msk2:button buttonValue="支付" buttonId="SO153111.PAY" buttonType="hidden" coltype="audit" class="h-button"/>
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
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <msk2:button buttonValue="卖家资金池数据导出" buttonId="SO153111.EXPORTSELLER" buttonType="button" align="left"/>

</div>
<script src="${ctx}/static/so/js/SO153111.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
<!--
<style type="text/css">
    table { table-layout: fixed; }
    td {overflow:hidden;text-overflow:ellipsis;}
</style> -->
