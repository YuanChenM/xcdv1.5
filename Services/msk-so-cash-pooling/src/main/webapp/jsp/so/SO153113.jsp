<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="TransType" viewType="json" />
<msk:codemaster codeType="ChargeFlg" viewType="json" />
<msk:codemaster codeType="RoleType" viewType="json" />
<c:if test="${srcPage eq 'SO153111'}">
  <navigation:header title="卖家资金池计费明细" backTitleArray="卖家资金池管理" backUrlArray="${ctx}/SO153111/init/"></navigation:header>
</c:if>
<c:if test="${srcPage eq 'SO153121'}">
  <navigation:header title="买手资金池计费明细" backTitleArray="买手资金池管理" backUrlArray="${ctx}/SO153121/init/"></navigation:header>
</c:if>
<div class="page-content list-page">
  <form action="${ctx}/SO153113/search/${sellerBillIdHide}" id="SO153113Form" method="post">
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>卖家计费明细</label>
      </h3>
      <div>
        <table width="100%">
          <tr>
            <td width="100px" style="text-align: right">收款方编码:</td>
            <td>
              ${soSellerBill.businessMainCode}
            </td>
            <td width="100px" style="text-align: right">收款方名称:</td>
            <td>
              ${soSellerBill.businessMainName}
            </td>
           <!-- <td width="100px" style="text-align: right">收款方角色:</td>
            <td>
              <msk:codemaster codeType="RoleType" viewType="label" codeValue="${soSellerBill.businessMainRole}" />
            </td> -->
            <td width="100px" style="text-align: right">对账标志:</td>
            <td>
                <msk:codemaster codeType="StatementFlg" viewType="label" codeValue="${soSellerBill.statementFlg}" />
            </td>
            <td width="100px" style="text-align: right">结算状态:</td>
            <td>
              <msk:codemaster codeType="SettlementStatus" viewType="label" codeValue="${soSellerBill.settlementStatus}" />
            </td>
          </tr>

          <tr>
            <td width="100px" style="text-align: right">结算标志:</td>
            <td>
                <msk:codemaster codeType="SettlementFlg" viewType="label" codeValue="${soSellerBill.settlementFlg}" />
            </td>
            <td width="100px" style="text-align: right">起始日期:</td>
            <td>
              ${soSellerBill.startDateStr}
            </td>
            <td width="100px" style="text-align: right">终止日期:</td>
            <td>
              ${soSellerBill.endDateStr}
            </td>
            <td width="100px" style="text-align: right">支付类型:</td>
            <td>
                <msk:codemaster codeType="PaymentType" viewType="label" codeValue="${soSellerBill.paymentType}" />
            </td>
          </tr>

          <tr>
            <td width="100px" style="text-align: right">计费类型:</td>
            <td>
                <msk:codemaster codeType="BillType" viewType="label" codeValue="${soSellerBill.billType}" />
            </td>
            <td width="100px" style="text-align: right">计费金额:</td>
            <td>
              ${soSellerBill.billAmount}
            </td>
            <td width="100px" style="text-align: right">应收金额:</td>
            <td>
              ${soSellerBill.receiveable}
            </td>
            <td width="100px" style="text-align: right">应退金额:</td>
            <td>
              ${soSellerBill.refundable}
            </td>
          </tr>
          <tr>
            <td width="100px" style="text-align: right">实收金额:</td>
            <td>
              ${soSellerBill.received}
            </td>
            <td width="100px" style="text-align: right">实退金额:</td>
            <td>
              ${soSellerBill.refundable}
            </td>
            <td width="100px" style="text-align: right">冲抵核销标志:</td>
            <td>
                <msk:codemaster codeType="MatchVerFlg" viewType="label" codeValue="${soSellerBill.matchVerFlg}" />
            </td>
            <td width="100px" style="text-align: right">手续费率:</td>
            <td>
              ${soSellerBill.chargeRate}
            </td>

          </tr>

          <tr>
            <td width="100px" style="text-align: right">交易支付状态:</td>
            <td>
                <msk:codemaster codeType="TransPaidStatus" viewType="label" codeValue="${soSellerBill.transPaidStatus}" />
            </td>
            <td width="100px" style="text-align: right">手续费:</td>
            <td>
              ${soSellerBill.handingCharge}
            </td>
            <td width="100px" style="text-align: right">手续费支付状态:</td>
            <td>
                <msk:codemaster codeType="TransPaidStatus" viewType="label" codeValue="${soSellerBill.chargeStatus}" />
            </td>
          </tr>
           <tr>
              <td width="100px" style="text-align: right">备注:</td>
              <td colspan="7">
                ${soSellerBill.remark}
              </td>
           </tr>

        </table>
      </div>
    </div>
   <div class="group-accordion" collapsible="true" active="true">
    <h3>
      <label>卖家计费项目明细</label>
    </h3>
     <table id="SO153113_list_grid">
       <thead>
       <tr>
         <th width="40px"coltype="sno">序号</th>
         <th coltype="text" width="5%"  filter="true" name="businessAssistantCode">付款方编码</th>
         <th coltype="text" width="7%" filter="true" name="businessAssistantName">付款方名称</th>
        <!-- <th coltype="code" width="10%" filter="true" name="businessAssistantRole" code2name="ROLETYPE_JSON">付款方角色</th> -->
         <th coltype="text" width="10%"  filter="true" name="transCode">订单号</th>
         <!-- <th coltype="code" width="10%" filter="true" name="transType" code2name="TRANSTYPE_JSON">交易类型</th> -->
         <th coltype="datetime" width="30px" name="deliveryTime">签收日期</th>
         <th coltype="text" width="30px" name="paidAmount">签收金额</th>
         <th coltype="code" width="10%" filter="true" name="chargeFlg" code2name="CHARGEFLG_JSON">计费标志</th>

         <th coltype="text" width="30px" filter="true" name="remark">备注</th>
         <th coltype="action" width="60px">操作
           <msk2:button buttonValue="详情" buttonId="SO153113.DETAIL" buttonType="hidden" coltype="detail" class="h-button" />
         </th>
       </tr>
       </thead>
       <tbody>
       </tbody>
     </table>
  </div>
</form>
</div>
<script src="${ctx}/static/so/js/SO153113.js" />