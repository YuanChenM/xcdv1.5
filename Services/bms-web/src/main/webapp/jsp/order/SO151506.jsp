<%-- 
    Title:退货单列表
    author:wang_shuai
    updateDate:2016-8-3
    updateAuthor:rwf
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="ReturnSource" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="ReturnType" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="ReturnMode" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="ReturnOrderStatus" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="IsPaid" viewType="json" modelName="ORDER"/>
<navigation:header title="退货单列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form action="<c:url value="/SO151506/search"/>" id="SO151506Form" method="post">
    <div class="group-accordion" collapsible="true" active="true" width="100%">
      <h3>
        <label>查询条件</label>
      </h3>
      <table width="100%">
        <tr>
          <td width="15%" align="right">退货单编码 : </td>
          <td id="returnCodeTd" align="left">
            <input type="text" id="returnCode" name="filterMap['returnCodeQuery']"/>
          </td>
          <td width="15%" align="right">订单编码 : </td>
          <td id="orderCodeTd" align="left">
            <input type="text" id="orderCode" name="filterMap['orderCode']"/>
          </td>
          <td width="15%" align="right">买家编码 : </td>
          <td id="buyerCodeTd" align="left">
            <input type="text" id="buyerCode" name="filterMap['buyerCode']"/>
          </td>
          <td width="15%" align="right">买家名称 : </td>
          <td align="left">
            <input type="text" id="buyerName" name="filterMap['buyerName']"/>
          </td>
        </tr>
        <tr>
          <td width="15%" align="right">退货单来源 : </td>
          <td align="left">
            <msk:codemaster codeType="ReturnSource" modelName="ORDER" id="returnSource" name='filterMap["returnSource"]' viewType="select"/>
          </td>
          <td width="15%" align="right">退货单类型 : </td>
          <td align="left">
            <msk:codemaster codeType="ReturnType" modelName="ORDER" id="returnType" name='filterMap["returnType"]' viewType="select"/>
          </td>
          <td width="15%" align="right">退货单状态 : </td>
          <td style="display: none">
            <msk:codemaster codeType="ReturnOrderStatus" modelName="ORDER" id="returnOrderStatus" name='filterMap["returnStatus"]' viewType="select"/>
          </td>
          <td align="left">
            <input type="hidden" name="filterMap['returnStatus']" id="returnStatus" value="">
            <select id="checkbox-returnStatus">
            </select>
          </td>
        </tr>
        <tr>
          <td width="15%" align="right">退货单申请时间 : </td>
          <td align="left" colspan="6">
            <input type="text" name="filterMap['startTime']" id="startTime" readonly="true"/>
            &emsp;&emsp;&emsp;&emsp;&emsp;~&emsp;&emsp;&emsp;&emsp;&emsp;
            <input type="text" name="filterMap['endTime']" id="endTime" readonly="true"/>
          </td>

          <td align="right">
            <msk:button buttonValue="查询" buttonId="SO151506.SEARCH" buttonType="button"/>
          </td>
        </tr>
      </table>
    </div>

    <table id="SO151506_list_grid" >
      <thead>
      <tr>
        <th coltype="link" width="5%" name="returnCode">退货单编码</th>
        <th width="5%" name="returnId" style="display: none">退货单id</th>
        <th coltype="link" width="5%" name="orderCode">订单编码</th>
        <th width="5%" name="orderCode" style="display: none">订单id</th>
        <th coltype="text" width="7%" name="buyerCode">买家编码</th>
        <th coltype="text" width="10%" name="buyerName">买家名称</th>
        <th coltype="code" width="10%" name="returnSource" code2name="RETURNSOURCE_JSON">退货单来源</th>
        <th coltype="code" width="10%" name="isPaid" code2name="ISPAID_JSON">是否已付款</th>
        <th coltype="code" width="10%" name="returnMode" code2name="RETURNMODE_JSON">退货方式</th>
        <th coltype="money" accuracy = "2" width="10%" name="returnAmount" style="align: right">退货单总金额(元)</th>
        <th coltype="text" width="10%" name="applyTime">退货单申请时间</th>
        <th coltype="code" width="10%" name="returnStatus" code2name="RETURNORDERSTATUS_JSON">退货单状态</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </form>
</div>
<script src="<c:url value="/static/js/order/SO151506.js" /> "></script>
