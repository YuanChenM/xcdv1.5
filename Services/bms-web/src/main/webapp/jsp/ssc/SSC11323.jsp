<%--
  Created by IntelliJ IDEA.
  Date: 2016/8/3
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="InvoiceType" viewType="json" modelName="Order"/>
<msk:codemaster codeType="InvoiceStatus" viewType="json" modelName="SSC"/>
<c:if test="${disableBtn ne 1}">
    <%--disableBtn == 1时为弹窗模式--%>
<navigation:header title="发票申请一览" backTitleArray="" backUrlArray=""></navigation:header>
</c:if>
<div class="page-content list-page">
  <form action="<c:url value="/SSC11323/search"/>" method="post" id="SSC11323Form">
    <c:if test="${disableBtn eq 1}">
      <input type="hidden" name="contractCode" value="${contractInfo.contractCode}"/>
      <input type="hidden" name="contractId" value="${contractInfo.contractId}"/>
      <input type="hidden" name="disableBtn" value="${disableBtn}"/>
      </c:if>
<c:if test="${disableBtn ne 1}">
      <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
        <h3>
          <label>查询条件</label>
        </h3>
          <div width="100%" >
        <table WIDTH="100%" border="0">
         <tr>
          <td  align="right" >发票申请编号:</td>
          <td  align="left" width="350px">　
            <input type="text" id="invoiceRequestCode" name="filterMap['invoiceRequestCode']" />
          </td>
           <td  align="right" >合同编号:</td>
           <td  align="left" width="350px">　
             <input type="text" id="contractCode" name="filterMap['contractCode']" value="${param.filterMap.contractCode}"/>
           </td>
           <td  align="right" >合同名称:</td>
           <td  align="left" width="350px">　
             <input type="text" id="contractName" name="filterMap['contractName']" value="${param.filterMap.contractName}"/>
           </td>
           <td  align="right" >发票种类:</td>
           <td  align="left" width="350px">　
               <select name="filterMap['invoiceType']" style="width: 120px">
                   <option value="">--请选择--</option>
                   <c:forEach items="${invoiceType}" var="item" varStatus="status">
                       <option value="${item.key}">${item.value}</option>
                   </c:forEach>
               </select>
           </td>
          <td  align="right" ></td>
          <td  align="left">　
          </td>
          </tr>
          <tr>
            <td  align="right" >付款单位:</td>
            <td  align="left" width="350px">　
              <input type="text" id="payer" name="filterMap['payer']" value="${param.filterMap.payer}"/>
            </td>
            <td  align="right" >收款单位:</td>
            <td  align="left" width="350px">　
              <input type="text" id="receiving" name="filterMap['receiving']" value="${param.filterMap.receiving}"/>
            </td>
            <td  align="right" >项目名称:</td>
            <td  align="left" width="350px">　
              <input type="text" id="invoiceRequestDesc" name="filterMap['invoiceRequestDesc']" value="${param.filterMap.invoiceRequestDesc}"/>
            </td>
            <td  align="right" >发票申请状态:</td>
            <td  align="left">　
                <select name="filterMap['status']"  style="width: 120px">
                    <option value="">--请选择--</option>
                    <c:forEach items="${invoiceStatus}" var="item" varStatus="status">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                </td>
          </tr>
          <tr>
            <td  align="right" >申请时间:</td>
            <td  align="left" width="350px">　
              <input type="text" id="requestTimeStr" name="filterMap['requestTimeStr']" value="${param.filterMap.requestTimeStr}" readonly/>
            </td>
            <td  align="right"></td>
            <td  align="left">　
              <td>

              </td>
              <td>

              </td>
              <td>

              </td>
            <td align="right">
              <msk:button buttonValue="查询" buttonId="SSC11323.SEARCH" buttonType="button"/>
            </td>
          </tr>
          </table>
        </div>
          </div>
    </c:if>


    <div class="group-accordion" collapsible="true" active="true" style="white-space:nowrap;">
        <h3>
            <label>
                发票申请列表
            </label>
        </h3>
      <table width="100%" id="SSC11323_list_grid">
        <thead>
        <tr>
          <th coltype="sno"   <c:if test="${disableBtn ne 1}">filter="false" </c:if>>序号</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="10%" name="invoiceRequestCode">发票申请编号</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if>width="12%" name="contractCode">合同编号</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="12%" name="contractName">合同名称</th>
          <th coltype="code"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="5%" name="contractStatus" code2name="SSCORDERSTATUS_JSON">合同状态</th>
          <th coltype="code"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="5%" name="invoiceType" code2name="INVOICETYPE_JSON" >发票种类</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="5%" name="requestTimeStr">申请日期</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> name="payer" width="8%">付款单位</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="8%" name="receiving">收款单位</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="8%" name="invoiceRequestDesc">项目名称</th>
          <th coltype="money" <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="12%" name="invoiceAmount">发票金额</th>
          <th coltype="code"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="5%" name="status" code2name="INVOICESTATUS_JSON">发票申请状态</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="5%" name="requester">申请人</th>
          <th coltype="text"  <c:if test="${disableBtn ne 1}">filter="false"</c:if> width="10%" name="remark">备注</th>
          <th coltype="action" width="10%">操作
            <msk:button buttonValue="发票详细" buttonId="SSC11323.DETAIL" buttonType="hidden"
                        coltype="detail"/>
            <c:if test="${disableBtn ne 1}">
            <msk:button buttonValue="删除" buttonId="SSC11323.DELETE" buttonType="hidden"
                        coltype="delete" useable="can_delete"/>
            </c:if>
          </th>
        </tr>
        </thead>
        <tbody></tbody>
      </table>
    </div>
  </form>
  <c:if test="${disableBtn ne 1}">
  <msk:button buttonValue="新增" buttonId="SSC11323.NEW" buttonType="button"/>
  </c:if>
  </div>
<script src="<c:url value="/static/js/ssc/SSC11323.js"/>"></script>