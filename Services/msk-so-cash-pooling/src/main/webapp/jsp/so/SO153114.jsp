<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib uri="/msk-navigation" prefix="navigation"%>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="RoleType" viewType="json" />
<msk:codemaster codeType="ChargeFlg" viewType="json" />
<msk:codemaster codeType="TransType" viewType="json" />
<%--<navigation:header title="卖家交易费用明细详情" backTitleArray="卖家交易费用明细详情" backUrlArray="${ctx}/SO151401/init/"></navigation:header>--%>
<form action="${ctx}/SO153114/search" id="SO153114Form" method="post">
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>卖家交易费用明细详情</label>
      </h3>
      <div>
        <table width="100%">
          <tr>
            <td width="100px" style="text-align: right">订单号:</td>
            <td>
              ${soSelCharging.transCode}
            </td>
            <td width="100px" style="text-align: right">交易类型:</td>
            <td>
              <msk:codemaster codeType="TransType" viewType="label" codeValue="${soSelCharging.transType}" />
            </td>
            <td width="100px" style="text-align: right">付款方编码:</td>
            <td>
              ${soSelCharging.businessAssistantCode}
            </td>
            <td width="100px" style="text-align: right">付款方名称:</td>
            <td>
              ${soSelCharging.businessAssistantName}
            </td>
          </tr>

          <tr>
           <!-- <td width="100px" style="text-align: right">付款方角色:</td>
            <td>
              <msk:codemaster codeType="RoleType" viewType="label" codeValue="${soSelCharging.businessAssistantRole}" />
            </td>  -->
            <td width="100px" style="text-align: right">配送单编码:</td>
            <td>
              ${soSelCharging.deliveryCode}
            </td>
            <td width="100px" style="text-align: right">签收日期:</td>
            <td>
              <fmt:formatDate value="${soSelCharging.deliveryTime}" type="both"/>
            </td>
            <td width="100px" style="text-align: right">配送金额:</td>
            <td>
              ${soSelCharging.shippingAmount}
            </td>
          </tr>

          <tr>
            <td width="100px" style="text-align: right">签收金额:</td>
            <td>
              ${soSelCharging.paidAmount}
            </td>
            <td width="100px" style="text-align: right">计费标志:</td>
            <td>
              <msk:codemaster codeType="ChargeFlg" viewType="label" codeValue="${soSelCharging.chargeFlg}" />
            </td>
            <td width="100px" style="text-align: right">备注:</td>
            <td colspan="3">
              ${soSelCharging.remark}
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</form>