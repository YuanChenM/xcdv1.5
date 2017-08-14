<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/8/3
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<msk:codemaster codeType="ReturnSource" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="ReturnMode" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="IsPaid" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="ReturnOrderStatus" viewType="json" modelName="ORDER"/>
<div class="group-accordion" active="true">
  <h3>
    <label>退货单基本信息</label>
  </h3>
  <form:form>
    <table  style="width: 100%" CellSpacing=4>
      <tr>
        <td width="25%" align="right">退货单编码: </td>
        <td width="25%" align="left">${returnOrder.returnCode}</td>
        <td width="25%" align="right">订单编码: </td>
        <td width="25%" align="left" >${returnOrder.orderCode}</td>
      </tr>
      <tr>
        <td width="25%" align="right">退货单来源: </td>
        <td width="25%" align="left"><msk:codemaster codeType="ReturnSource"  id="returnSource" name='filterMap["returnSource"]' viewType="label" modelName="ORDER" codeValue="${returnOrder.returnSource}"/></td>
        <td width="25%" align="right">退货方式: </td>
        <td width="25%" align="left"><msk:codemaster codeType="ReturnMode"  id="returnMode" name='filterMap["returnMode"]' viewType="label" modelName="ORDER" codeValue="${returnOrder.refundMode}"/></td>
      </tr>
      <tr>
        <td width="25%" align="right">是否已付款: </td>
        <td width="25%" align="left" ><msk:codemaster codeType="IsPaid"  id="isPaid" name='filterMap["isPaid"]' viewType="label" modelName="ORDER" codeValue="${returnOrder.isPaid}"/></td>
        <td width="25%" align="right">退货总金额(元): </td>
        <td width="25%" align="left" ><fmt:formatNumber value="${returnOrder.returnAmount}" pattern="###,###" minFractionDigits="2" ></fmt:formatNumber></td>
      </tr>
      <tr>
        <td width="25%" align="right">退货处理人: </td>
        <td width="25%" align="left">${returnOrder.returnActor}</td>
        <td width="25%" align="right">退货单状态: </td>
        <td width="25%" align="left" ><msk:codemaster codeType="ReturnOrderStatus"  id="returnStatus" name='filterMap["returnStatus"]' viewType="label" modelName="ORDER" codeValue="${returnOrder.returnStatus}"/></td>
      </tr>
      <tr>
        <td width="25%" align="right">退货单申请时间: </td>
        <td width="25%" align="left">${returnOrder.returnTime}</td>
        <td width="25%" align="right">退货原因: </td>
        <td width="25%" align="left">${returnOrder.returnReasonDesc}</td>
      </tr>
      <tr>
        <td width="25%" align="right">退货申请备注: </td>
        <td align="left" colspan="3">${returnOrder.applyRemark}</td>
      </tr>
    </table>
  </form:form>
</div>
