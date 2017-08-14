<%--
    Title:买手资金池
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="TransType" viewType="json" />
<msk:codemaster codeType="SettlementStatus" viewType="json" />
<msk:codemaster codeType="RoleType" viewType="json" />
<msk:codemaster codeType="TransFlg" viewType="json" />
<msk:codemaster codeType="PaidType" viewType="json" />
<navigation:header title="买手资金池管理" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO153121/search" id="SO153121Form" method="post">
        <table width="100%"><tr><td>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">订单金额范围：</td>
                    <td align="left" colspan="8">　
                        <input type="text" id="orderAmountStart" name="filterMap['orderAmountStart']" value="${param.filterMap.orderAmountStart}" maxlength="18"/>
                        &emsp;~&emsp;
                        <input type="text" id="orderAmountEnd" name="filterMap['orderAmountEnd']" value="${param.filterMap.orderAmountEnd}" maxlength="18"/>
                    </td>
                </tr>

                <tr>
                    <td  align="right">生成时间:</td>
                    <td align="left" colspan="8">　
                        <input type="text" id="createTimeStart" name="filterMap['createTimeStart']" value="${param.filterMap.createTimeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="createTimeEnd" name="filterMap['createTimeEnd']" value="${param.filterMap.createTimeEnd}"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right">交易发生日:</td>
                    <td align="left" colspan="8">　
                        <input type="text" id="tranTimeStart" name="filterMap['tranTimeStart']" value="${param.filterMap.tranTimeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="tranTimeEnd" name="filterMap['tranTimeEnd']" value="${param.filterMap.tranTimeEnd}"/>
                    </td>
                </tr>
            </table>
        </div>
        </td></tr>
        <tr><td>
        <div>
            <%--<span WIDTH="30%" id="cycle" style="background-color: #FFB48F;font-size:14pt;display:inline-block;height: 30px;"></span>--%>
            <table id="SO153121_list_grid">
                <thead>
                <tr>
                    <th width="40px"coltype="sno">序号</th>
                   <!-- <th coltype="code" width="7%" filter="true" name="transFlg" code2name="TRANSFLG_JSON">交易标志</th> -->
                    <th coltype="text" width="7%" filter="true" name="businessMainCode">卖家编码</th>
                    <th coltype="text" width="10%" filter="true" name="businessMainName">卖家名称</th>
                    <!--<th coltype="code" width="10%"  name="businessMainRole" code2name="ROLETYPE_JSON">收款方角色</th>-->
                    <th coltype="text" width="7%" filter="true" name="businessAssistantCode">买手编码</th>
                    <th coltype="text" width="10%" filter="true" name="businessAssistantName">买手名称</th>
                    <!--<th coltype="code" width="10%"  name="businessAssistantRole" code2name="ROLETYPE_JSON">付款方角色</th>-->
                    <th coltype="link" width="40px"  name="accountDate" align="center">账期</th>
                    <th coltype="text" width="10%" filter="true" name="transCode">囤货/分销订单编码</th>
                    <th coltype="code" width="3%" filter="true" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                    <!-- <th coltype="code" width="10%" filter="true" name="transType" code2name="TRANSTYPE_JSON">交易类型</th>-->
                    <th coltype="money" width="7%" name="orderAmount">囤货/分销金额</th>
                    <th coltype="money" width="7%" name="actualDue">应收金额</th>
                    <th coltype="money" width="7%" name="actualPaid">已收金额</th>
                    <th coltype="money" width="7%" name="actualReceiveable">应付金额</th>
                    <th coltype="money" width="7%" name="actualReceived">已付金额</th>
                    <th coltype="money" width="7%" name="reliefAmount">减/退金额</th>
                    <th coltype="money" width="7%" name="balance">结余金额</th>
                    <th coltype="code" width="10%" filter="true" name="settlementStatus" code2name="SETTLEMENTSTATUS_JSON">结算状态</th>
                    <!--<th coltype="text" width="16%" name="remark">备注</th>  -->
                    <th coltype="action" width="60px">操作
                        <msk2:button buttonValue="详情" buttonId="SO153121.DETAIL" buttonType="hidden" coltype="detail" class="h-button" />
                        <msk2:button buttonValue="费用调整" buttonId="SO153101.REDUCE" buttonType="hidden" coltype="edit" class="h-button"   useable="can_abolish" />
                        <msk2:button buttonValue="支付" buttonId="SO153101.PAY" buttonType="hidden" coltype="check" class="h-button"   useable="can_abolish" />
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
    <msk2:button buttonValue="买手列表数据导出" buttonId="SO153121.EXPORT" buttonType="button" align="left"/>
</div>
<script src="${ctx}/static/so/js/SO153121.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>