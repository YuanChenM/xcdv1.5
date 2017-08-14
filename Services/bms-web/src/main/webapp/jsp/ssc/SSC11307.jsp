<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="SscPaymentType" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PaymentStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="AuditingStatus" viewType="json" modelName="SSC"/>

<c:if test="${empty ssc11307RsBean.navigation}">
<navigation:header title="付款申请一览" backTitleArray="" backUrlArray="" />
</c:if>
<c:if test="${ssc11307RsBean.navigation eq 'contract'}">
  <navigation:header title="付款申请一览"
                     backTitleArray="合同管理一览"
                     backUrlArray="../SSC11303/init/"
                     backParamArray='' />
</c:if>


<div class="page-content list-page">
<form action="<c:url value="/SSC11307/search"/>" id="SSC11307Form" method="post" >
  <input type="hidden" name="contractCode" value="${ssc11307RsBean.contractCode}">

  <div class="group-accordion" style="white-space:nowrap;" collapsible="true" active="true">
    <h3>
      <label>查询条件</label>
    </h3>
    <table WIDTH="100%">
      <tr>
          <td  align="right">付款申请单编号:</td>
          <td  align="left">
            <input type="text" name="filterMap['paymentRequestCode']" value="${param.filterMap.paymentRequestCode}"/>
          </td>


          <td  align="right">付款申请名称:</td>
          <td  align="left">
            <input type="text" name="filterMap['paymentRequestName']" value="${param.filterMap.paymentRequestName}"/>
          </td>


          <td  align="right">付款类型:</td>
          <td  align="left">
            <select name="filterMap['paymentType']" style="width: 140px">
              <option value="">--请选择--</option>
              <c:forEach items="${paymentTypeList}" var="item" varStatus="status">
                <option value="${item.key}">${item.value}</option>
              </c:forEach>
            </select>
          </td>

          <td  align="right">发货订单编号:</td>
          <td  align="left">
            <input type="text" name="filterMap['deliveryCode']" value="${param.filterMap.deliveryCode}"/>
          </td>
        </tr>

        <tr>
          <td  align="right">合同编号:</td>
          <td  align="left">
            <input type="text" name="filterMap['contractCode']" value="${param.filterMap.contractCode}"/>
          </td>
          <td  align="right">合同名称:</td>
          <td  align="left">
            <input type="text" name="filterMap['contractName']" value="${param.filterMap.contractName}"/>
          </td>
          <td  align="right">合同状态:</td>
          <td  align="left">
            <select name="filterMap['contractStatus']" style="width: 140px">
              <option value="">--请选择--</option>
              <c:forEach items="${contractStatusList}" var="item" varStatus="status">
                <option value="${item.key}">${item.value}</option>
              </c:forEach>
            </select>
          </td>
          <td  align="right">受款主体:</td>
          <td  align="left">
            <input type="text" name="filterMap['supplierName']" value="${param.filterMap.supplierName}"/>
          </td>
      </tr>
      <tr>
        <td  align="right">付款状态:</td>
        <td  align="left">
          <select name="filterMap['payedStatus']" style="width: 140px">
            <option value="">--请选择--</option>
            <c:forEach items="${payedStatusList}" var="item" varStatus="status">
              <option value="${item.key}">${item.value}</option>
            </c:forEach>
          </select>
        </td>

        <td  align="right">审批审核状态:</td>
        <td  align="left">
          <select name="filterMap['auditingStatus']" style="width: 140px">
            <option value="">--请选择--</option>
            <c:forEach items="${auditingStatusList}" var="item" varStatus="status">
              <option value="${item.key}">${item.value}</option>
            </c:forEach>
          </select>
        </td>

        <td  align="right">最近付款时间:</td>
        <td  align="left" colspan="3">
          <input type="text" name="filterMap['remitTimeStr']" id="remitTimeStr" value="${param.filterMap.remitTimeStr}"/>
        </td>

      </tr>

      <tr>
        <td colspan="8" align="right">
          <msk:button buttonValue="查询" buttonId="SSC11307.SEARCH" buttonType="button"/>
        </td>
      </tr>

    </table>
  </div>


  <div class="group-accordion" style="white-space:nowrap;" collapsible="true" active="true">
    <h3>
      <label>付款申请列表</label>
    </h3>
    <div>
      <table width="100%" id="SSC11307_list_grid">
        <thead>
        <tr>
          <th width="40px"coltype="sno">序号</th>
          <th coltype="text" width="5%"  name="paymentRequestCode">付款申请单编号</th>
          <th coltype="text" width="5%"  name="paymentRequestName">付款申请名称</th>
          <th coltype="code" width="10%" name="paymentType" code2name="SSCPAYMENTTYPE_JSON">付款类型</th>
          <th coltype="link" width="10%" name="deliveryCode">发货订单编号</th>
          <th coltype="link" width="10%" name="contractCode">合同编号</th>
          <th coltype="text" width="10%" name="contractName">合同名称</th>
          <th coltype="code" width="10%" name="contractStatus" code2name="SSCORDERSTATUS_JSON">&nbsp;&nbsp;&nbsp;合同状态&nbsp;&nbsp;&nbsp;</th>
          <th coltype="text" width="10%" name="supplierName">受款主体</th>
          <th coltype="code" width="10%" name="payedStatus" code2name="PAYMENTSTATUS_JSON">付款状态</th>
          <th coltype="code" width="10%" name="auditingStatus" code2name="AUDITINGSTATUS_JSON">审批审核状态</th>
          <th coltype="money" width="10%" name="amount">本次申请付款金额</th>
          <th coltype="money" width="10%" name="paidAmount">本次申请已付金额</th>
          <th coltype="money" width="10%" name="contractTotalAmount">合同总金额</th>
          <th coltype="money" width="10%" name="totalPaidAmount">合同已支付金额</th>
          <th coltype="text" width="10%"  name="remitTimeStr">最近付款时间</th>
          <th coltype="action" width="60px">操作
            <msk:button buttonValue="付款申请单详细" buttonId="SSC11307.DETAIL" buttonType="hidden" coltype="detail" class="h-button" />
            <msk:button buttonValue="删除" buttonId="SSC11307.DELETE" buttonType="hidden" coltype="delete" class="h-button" useable="can_abolish"/>
          </th>
        </tr>
        </thead>
        <tbody></tbody>
      </table>
      </div>
  </div>
  </form>
<msk:button buttonValue="新增" buttonId="SSC11307.ADD" buttonType="button"/>
</div>
<script src="<c:url value="/static/js/ssc/SSC11307.js" /> " />