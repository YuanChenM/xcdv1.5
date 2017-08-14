<%--
  Created by IntelliJ IDEA.
  User: wu_honglei
  Date: 2016/8/5
  Time: 15:29
  付款申请新增页面
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>


<msk:codemaster codeType="PaymentStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="Subject" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PaidType" viewType="json" modelName="SoCashpoolingConstant"/>
<msk:codemaster codeType="SscPaymentType" viewType="json" modelName="SSC"/>

<c:if test="${type eq '0'}">
    <navigation:header title="付款申请"
                       backTitleArray="付款申请一览"
                       backUrlArray="../SSC11307/init"
                       backParamArray=""></navigation:header>
</c:if>
<c:if test="${type eq '1'}">
    <navigation:header title="付款申请"
                       backTitleArray="合同管理一览,合同详细"
                       backUrlArray="../SSC11303/init/,../SSC11304/init"
                       backParamArray='/{contractId:"${ssc11308RsBean.contractId}"}'></navigation:header>
</c:if>
<c:if test="${type eq '2'}">
    <navigation:header title="付款申请"
                       backTitleArray="发货订单一览,发货订单详细"
                       backUrlArray="../SSC11305/init,../SSC11306/init"
                       backParamArray='/{deliveryId:"${ssc11308RsBean.deliveryId}"}'></navigation:header>
</c:if>
<c:if test="${type eq '3'}">
    <navigation:header title="付款申请"
                       backTitleArray="核销单一览,核销单详细"
                       backUrlArray="../SSC11321/init,../SSC11322/init"
                       backParamArray='/{verificationId:"${ssc11308RsBean.verificationId}"}'></navigation:header>
</c:if>
<div class="page-content list-page">
    <form id="SSC11308Form" action="<c:url value='/SSC11308/search'/>" method="post">
        <input type="hidden" name="paymentRequestId" id="paymentRequestId" value="${ssc11308RsBean.paymentRequestId}"/>
        <input type="hidden" name="paymentRequestCode" id="paymentRequestCode"
               value="${ssc11308RsBean.paymentRequestCode}"/>
        <input type="hidden" name="type" id="type" value="${type}"/>
        <input type="hidden" name="contractId" id="contractId" value="${ssc11308RsBean.contractId}"/>
        <input type="hidden" name="contractCode" id="contractCode" value="${ssc11308RsBean.contractCode}"/>
        <input type="hidden" name="supplierId" id="supplierId" value="${ssc11308RsBean.supplierId}"/>
        <input type="hidden" name="supplierCode" id="supplierCode" value="${ssc11308RsBean.supplierCode}"/>
        <input type="hidden" name="purchaserId" id="purchaserId" value="${ssc11308RsBean.purchaserId}"/>
        <input type="hidden" name="purchaserName" id="purchaserName" value="${ssc11308RsBean.purchaserName}"/>
        <input type="hidden" name="purchaserCode" id="purchaserCode" value="${ssc11308RsBean.purchaserCode}"/>
        <input type="hidden" name="deliveryId" id="deliveryId" value="${ssc11308RsBean.deliveryId}"/>
        <input type="hidden" name="deliveryCode" id="deliveryCode" value="${ssc11308RsBean.deliveryCode}"/>
        <input type="hidden" name="auditingStatus" id="auditingStatus" value="${ssc11308RsBean.auditingStatus}"/>
        <input type="hidden" id="payedStatus" value="${ssc11308RsBean.payedStatus}"/>

        <input type="hidden" name="paidAmount" id="paidAmount" value="${ssc11308RsBean.paidAmount}"/>

        <input type="hidden" name="verificationId" id="verificationId" value="${ssc11308RsBean.verificationId}"/>
        <input type="hidden" name="verificationCode" id="verificationCode" value="${ssc11308RsBean.verificationCode}"/>

        <input type="hidden"  id="totalApplyAmount" value="${ssc11308RsBean.totalApplyAmount}" />
        <input type="hidden"  id="allowApplyAmount" value="${ssc11308RsBean.allowApplyAmount}" />
        <input type="hidden"  name="moneyFlag" id="moneyFlag" value="${ssc11308RsBean.moneyFlag}" />
        <input type="hidden"  name="ver" id="ver" value="${ssc11308RsBean.ver}" />


        <!--付款申请表-->
        <div class="page-content list-page">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>付款申请表</label>
                </h3>

                <div>
                    <c:choose>
                        <c:when test="${ssc11308RsBean.auditingStatus eq '2'  ||  ssc11308RsBean.payedStatus eq  '9'}">
                            <table width="100%">
                                <tr>
                                    <th width="15%" align="right" style="white-space:pre;">付款申请名称:</th>
                                    <td width="15%" align="left"  >${ssc11308RsBean.paymentRequestName}</td>
                                    <th width="15%" align="right"  style="white-space:pre;">付款申请单号:</th>
                                    <td width="15%" align="left">
                                            ${ssc11308RsBean.paymentRequestCode}
                                    </td>
                                    <th width="15%" align="right"  style="white-space:pre;">付款状态:</th>
                                    <td width="15%" align="left">
                                         <msk:codemaster codeType="PaymentStatus" viewType="label" modelName="SSC" codeValue="${ssc11308RsBean.payedStatus}"/>
                                    </td>

                                </tr>

                                <tr>
                                    <th width="15%" align="right" >受款主体:</th>
                                    <td width="15%" align="left" style="white-space:pre;">${ssc11308RsBean.supplierName}</td>
                                    <th width="15%" align="right">开户银行:</th>
                                    <td width="15%" align="left" style="white-space:pre;">${ssc11308RsBean.supplierBank}</td>
                                    <th width="15%" align="right" style="white-space:pre;">银行账号:</th>
                                    <td width="15%" align="left" style="white-space:pre;">${ssc11308RsBean.supplierAccount}</td>
                                </tr>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <table width="100%">
                                <tr>
                                    <th width="15%" align="right" style="white-space:pre;">付款申请名称:</th>
                                    <td width="15%" align="left">
                                        <input type="text" name="paymentRequestName" id="paymentRequestName"
                                               value="${ssc11308RsBean.paymentRequestName}" style="width:220px;" />
                                    </td>
                                    <td width="10%"></td>
                                    <th width="15%" align="right" style="white-space:pre;">付款申请单号:</th>
                                    <td width="15%" align="left">
                                            ${ssc11308RsBean.paymentRequestCode}
                                    </td>
                                    <th width="15%" align="right"  style="white-space:pre;">付款状态:</th>
                                    <td width="15%" align="left">
                                        <msk:codemaster codeType="PaymentStatus" viewType="label" modelName="SSC" codeValue="${ssc11308RsBean.payedStatus}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">受款主体:</th>
                                    <td width="15%" align="left">
                                        <input type="text" name="supplierName"  id="supplierName" style="width:220px;" value="${ssc11308RsBean.supplierName}"/>
                                    </td>
                                    <td width="10%">
                                        &nbsp;&nbsp;<img  src="../static/images/action/search.png" title="选择企业信息" id="chooseEpInfo" style="cursor:pointer;" />
                                    </td>
                                    <th width="15%" align="right">开户银行:</th>
                                    <td width="15%" align="left">
                                        <input type="text" name="supplierBank" id="supplierBank" style="width:220px;" value="${ssc11308RsBean.supplierBank}"/>
                                    </td>
                                    <th width="15%" align="right" style="white-space:pre;">银行账号:</th>
                                    <td width="15%" align="left">
                                        <input type="text" name="supplierAccount"  id="supplierAccount" value="${ssc11308RsBean.supplierAccount}"/>
                                    </td>
                                </tr>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!--付款说明-->
        <div class="page-content list-page">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>付款说明</label>
                </h3>

                <div>
                    <c:choose>
                        <c:when test="${ssc11308RsBean.auditingStatus eq '2'  ||  ssc11308RsBean.payedStatus eq  '9'}" >
                            <input type="hidden" name="contractFirstAmount" id="contractFirstAmount"  value="${ssc11308RsBean.contractFirstAmount}" />
                            <input type="hidden" id="amount" value="${ssc11308RsBean.amount}"/>
                            <input type="hidden" name="paidDownPaymentPercentage" id="paidDownPaymentPercentage"  value="${ssc11308RsBean.paidDownPaymentPercentage}" />

                            <table width="100%" id="paymentInfoTable">
                                <tr>
                                    <th width="15%" align="right">
                                        付款类型:
                                    </th>
                                    <td  width="75%" align="left" colspan="5">
                                        <msk:codemaster codeType="SscPaymentType" viewType="label" modelName="SSC" codeValue="${ssc11308RsBean.paymentType}"/>
                                        <input type="hidden" name="paymentType" id="paymentType" value="${ssc11308RsBean.paymentType}" />
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">合同编号:</th>
                                    <td width="15%" align="left">
                                        <c:choose>
                                            <c:when test="${ssc11308RsBean.payedStatus eq '9'}">
                                            ${ssc11308RsBean.contractCode}
                                            </c:when>
                                            <c:otherwise>
                                            <a href="#" id="showContractDetail" style="color: #0000ff">${ssc11308RsBean.contractCode}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <th width="15%" align="right">订单合同总金额(元):</th>
                                    <td width="15%" align="left">${ssc11308RsBean.contractTotalAmount}</td>
                                    <th width="15%" align="right">订单合同预付款金额(元):</th>
                                    <td width="15%" align="left" >${ssc11308RsBean.contractFirstAmount}</td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right" style="white-space:pre;">发货订单编号:</th>
                                    <td width="15%" align="left">
                                        <c:choose>
                                            <c:when test="${ssc11308RsBean.payedStatus eq '9'}">
                                            ${ssc11308RsBean.deliveryCode}
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" id="showDeliveryDetail" style="color: #0000ff">${ssc11308RsBean.deliveryCode}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <th width="15%" align="right" >本次发货订单总金额(元):</th>
                                    <td width="15%" align="left">${ssc11308RsBean.deliverTotalAmount}</td>
                                    <th width="15%" align="right" style="white-space:pre;">预付款按比例已支付金额(元):</th>
                                    <td width="15%" align="left" >${ssc11308RsBean.paidDownPaymentPercentage}</td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right"></th>
                                    <td width="15%" align="left"></td>
                                    <th width="15%" align="right" style="white-space:pre;"> 本次运输费用实际发生额(元):</th>
                                    <td width="15%" align="left">${ssc11308RsBean.transportAmount}</td>
                                    <th width="15%" align="right">包材使用费实际发生额(元):</th>
                                    <td width="15%" align="left">${ssc11308RsBean.packageAmount}</td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">核销单编号:</th>
                                    <td width="15%" align="left">${ssc11308RsBean.verificationCode}</td>
                                    <th width="15%" align="right">核销金额(元):</th>
                                    <td width="45%" align="left" colspan="3">${ssc11308RsBean.verificationAmount}</td>
                                </tr>

                                <tr>
                                    <td colspan="6">
                                        &nbsp;
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right" style="white-space:pre;">本次申请金额(元):</th>
                                    <td width="15%" align="left">${ssc11308RsBean.amount}</td>

                                    <th width="15%" align="right">请款发起人:</th>
                                    <td width="15%" align="left" style="white-space:pre;">${ssc11308RsBean.applicant}</td>
                                    <th width="15%" align="right">请款时间:</th>
                                    <td width="15%" align="left">
                                        <fmt:formatDate value='${ssc11308RsBean.accountingDate}' pattern='yyyy-MM-dd' />
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">付款约定:</th>
                                    <td width="30%" align="left" colspan="2">
                                            ${ssc11308RsBean.paymentMethod}
                                    </td>

                                    <th width="15%" align="right">备注:</th>
                                    <td width="30%" align="left" colspan="2">
                                            ${ssc11308RsBean.remark}
                                    </td>
                                </tr>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <table width="100%">
                                <tr>
                                    <th width="15%" align="right">
                                        付款类型:
                                    </th>
                                    <td  width="75%" align="left" colspan="5">
                                        <input type="radio" name="paymentTypeShow" class="paymentTypeRadio" value="0" disabled  <c:if
                                                test="${ssc11308RsBean.paymentType==0}">checked</c:if> >预付款</input>
                                        <input type="radio" name="paymentTypeShow" class="paymentTypeRadio" value="1" disabled  <c:if
                                                test="${ssc11308RsBean.paymentType==1}">checked</c:if> >进度款</input>
                                        <input type="radio" name="paymentTypeShow" class="paymentTypeRadio" value="2" disabled  <c:if
                                                test="${ssc11308RsBean.paymentType==2}">checked</c:if> >余款</input>
                                        <input type="hidden" name="paymentType" id="paymentType" value="${ssc11308RsBean.paymentType}" />
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">合同编号:</th>
                                    <td width="15%" align="left">
                                                <a href="#" id="showContractDetail" style="color: #0000ff">${ssc11308RsBean.contractCode}</a>
                                    </td>
                                    <th width="15%" align="right">订单合同总金额(元):</th>
                                    <td width="15%" align="left">
                                        <input  type="text" id="contractTotalAmountShow" />
                                        <input  type="hidden" id="contractTotalAmount" name="contractTotalAmount" value="${ssc11308RsBean.contractTotalAmount}" />

                                    </td>
                                    <th width="15%" align="right">订单合同预付款金额(元):</th>
                                    <td width="15%" align="left" >
                                        <input type="text" id="contractFirstAmountShow"  readonly disabled />
                                        <input type="hidden" name="contractFirstAmount" id="contractFirstAmount"  value="${ssc11308RsBean.contractFirstAmount}" />
                                    </td>
                                </tr>


                                <tr>
                                    <th width="15%" align="right">发货订单编号:</th>
                                    <td width="15%" align="left">
                                        <a href="#" id="showDeliveryDetail" style="color: #0000ff">${ssc11308RsBean.deliveryCode}</a>
                                    </td>
                                    <th width="15%" align="right">本次发货订单总金额(元):</th>
                                    <td width="15%" align="left">
                                        <input type="text" id="deliverTotalAmountShow"/>
                                        <input type="hidden" id="deliverTotalAmount"  name="deliverTotalAmount" value="${ssc11308RsBean.deliverTotalAmount}"/>
                                    </td>
                                    <th width="15%" align="right" style="white-space:pre;">预付款按比例已支付金额(元):</th>
                                    <td width="15%" align="left" >
                                        <input type="text" id="paidDownPaymentPercentageShow"  readonly disabled />
                                        <input type="hidden" name="paidDownPaymentPercentage" id="paidDownPaymentPercentage"
                                               value="${ssc11308RsBean.paidDownPaymentPercentage}"
                                                />
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right"></th>
                                    <td width="15%" align="left"></td>
                                    <th width="15%" align="right" style="white-space:pre;"> 本次运输费用实际发生额(元):</th>
                                    <td width="15%" align="left">
                                        <input type="text" id="transportAmountShow" />
                                        <input type="hidden" id="transportAmount"  name="transportAmount" value="${ssc11308RsBean.transportAmount}"/>
                                    </td>
                                    <th width="15%" align="right">包材使用费实际发生额(元):</th>
                                    <td width="15%" align="left">
                                        <input type="text" id="packageAmountShow" />
                                        <input type="hidden" id="packageAmount"  name="packageAmount" value="${ssc11308RsBean.packageAmount}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right">核销单编号:</th>
                                    <td width="15%" align="left">
                                            ${ssc11308RsBean.verificationCode}
                                    </td>
                                    <th width="15%" align="right">核销金额(元):</th>
                                    <td width="45%" align="left" colspan="3">
                                        <input type="text"  id="verificationAmountShow" />
                                        <input type="hidden" name="verificationAmount" id="verificationAmount"
                                               value="${ssc11308RsBean.verificationAmount}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="6">
                                        &nbsp;
                                    </td>
                                </tr>

                                <tr>
                                    <th width="15%" align="right" style="white-space:pre;">本次申请金额(元):</th>
                                    <td width="15%" align="left">
                                        <input type="text"  id="amountShow" value="${ssc11308RsBean.amount}" />
                                        <input type="hidden" name="amount" id="amount" value="${ssc11308RsBean.amount}" />
                                    </td>

                                    <th width="15%" align="right">请款发起人:</th>
                                    <td width="15%" align="left">
                                        <input type="text" name="applicant" value="${ssc11308RsBean.applicant}" id="applicant"/>
                                    </td>
                                    <th width="15%" align="right"> 请款时间:</th>
                                    <td width="15%" lign="left">
                                        <input type="text" name="accountingDateStr" readonly id="accountingDateStr"
                                               value="${ssc11308RsBean.accountingDateStr}" />
                                    </td>
                                </tr>


                                <tr>
                                    <th width="15%" align="right">付款约定:</th>
                                    <td width="30%" align="left" colspan="2">
                                        <textarea type="text" name="paymentMethod" id="paymentMethod"
                                                  style="width:300px;height:50px;"  >${ssc11308RsBean.paymentMethod}</textarea>
                                    </td>

                                    <th width="15%" align="right">备注:</th>
                                    <td width="30%" align="left" colspan="2">
                                        <textarea  type="text" name="remark" id="remark"
                                                   style="width:300px;height:50px;">${ssc11308RsBean.remark}</textarea>
                                    </td>
                                </tr>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <c:if test="${ssc11308RsBean.auditingStatus ne '2' && ssc11308RsBean.payedStatus ne '9'}">
                <msk:button buttonValue="保存" buttonId="SSC11308.SAVE" buttonType="button"/>
            </c:if>
        </div>

        <!--付款支付管控单-->
        <div class="page-content list-page">
            <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>支付管控单</label>
            </h3>
            <table id="SSC11308_list_grid">
                <thead>
                <tr>
                    <th width="40px" coltype="sno">编号</th>

                    <th coltype="action">支付
                        <c:if test="${ssc11308RsBean.payedStatus ne '9'}">
                             <msk:button buttonValue="支付" buttonId="SSC11308.PAY" buttonType="hidden" coltype="save"
                                    class="h-button"/>
                        </c:if>
                    </th>
                    <th coltype="text" width="20%" name="payer">付款单位</th>
                    <th coltype="code" width="10%" name="subject" code2name="SUBJECT_JSON">科目</th>
                    <th coltype="money" width="10%" name="amount">申请金额(元)</th>
                    <th coltype="code" width="10%" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                    <th coltype="code" width="10%" name="status" code2name="PAYMENTSTATUS_JSON">支付状态</th>
                    <th coltype="text" width="10%" name="remitTimeStr">支付时间</th>
                    <th coltype="text" width="10%" name="payerBank">付款银行</th>
                    <th coltype="text" width="10%" name="paterAccount">付款账号</th>
                    <th coltype="text" width="10%" name="remark">备注</th>
                    <th coltype="action">操作
                        <c:if test="${ssc11308RsBean.auditingStatus ne '2' && ssc11308RsBean.payedStatus ne '9'}">
                                     <msk:button buttonValue="删除" buttonId="SSC11308.DELETE" buttonType="hidden" coltype="delete"
                                         class="h-button"/>
                        </c:if>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
                <tr>
                    <td colspan="2"></td>
                    <td>
                        <label style="font-weight:bold; text-align:center;">可申请金额(元):</label>
                        <label id="allowApplyAmountLabel" />
                    </td>
                    <td style="font-weight:bold; text-align:center;">合计</td>
                    <td align="right"><label id="totalApplyAmountLabel" /></td>
                    <td colspan="7">&nbsp;</td>
                </tr>
            </table>
            </div>
            <c:if test="${ssc11308RsBean.auditingStatus ne '2'&& ssc11308RsBean.payedStatus ne '9'}">
                <msk:button buttonValue="添加付款记录" buttonId="SSC11308.ADD" buttonType="button"/>
            </c:if>
        </div>


        <!--审批信息-->
        <c:if test="${not empty ssc11308RsBean.paymentRequestId}">
            <div class="page-content list-page">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>审批信息</label>
                </h3>

                <div>
                    <table width="100%">
                        <tr>
                            <th align="center">
                                <c:choose>
                                    <c:when test="${ssc11308RsBean.payedStatus eq '9'}">
                                        <input type="radio" name="approvalFlag" value="0" disabled="ture" <c:if test="${ssc11308RsBean.approvalFlag==0}">checked</c:if>>同意</input>
                                        <input type="radio" name="approvalFlag" value="1"  disabled="ture" <c:if test="${ssc11308RsBean.approvalFlag==1}">checked</c:if>>不同意</input>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="text" name="approvalRemark" disabled="ture" id="approvalRemark" maxlength="100" style="width:300px;" value="${ssc11308RsBean.approvalRemark}"/> &nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="approvalFlag" value="0" <c:if test="${ssc11308RsBean.approvalFlag==0}">checked</c:if>>同意</input>
                                        <input type="radio" name="approvalFlag" value="1" <c:if test="${ssc11308RsBean.approvalFlag==1}">checked</c:if>>不同意</input>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="text" name="approvalRemark" id="approvalRemark" maxlength="100" style="width:300px;" value="${ssc11308RsBean.approvalRemark}"/> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${ssc11308RsBean.auditingStatus ne '2' && ssc11308RsBean.payedStatus ne '9'}">
                                            <msk:button buttonValue="审批" buttonId="SSC11308.APPROVAL" buttonType="button"/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </th>
                        </tr>
                        <tr>
                            <th align="center">
                                <c:choose>
                                    <c:when test="${ssc11308RsBean.payedStatus eq '9'}">
                                        <input type="radio" name="auditingFlag" value="0"  disabled="ture"<c:if test="${ssc11308RsBean.auditingFlag==0}">checked</c:if>>同意</input>
                                        <input type="radio" name="auditingFlag" value="1"  disabled="ture"<c:if test="${ssc11308RsBean.auditingFlag==1}">checked</c:if>>不同意</input>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="text" name="auditingRemark" disabled="ture" id="auditingRemark" maxlength="100"  style="width:300px;" value="${ssc11308RsBean.auditingRemark}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="auditingFlag" value="0" <c:if test="${ssc11308RsBean.auditingFlag==0}">checked</c:if>>同意</input>
                                        <input type="radio" name="auditingFlag" value="1" <c:if test="${ssc11308RsBean.auditingFlag==1}">checked</c:if>>不同意</input>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="text" name="auditingRemark" id="auditingRemark" maxlength="100"  style="width:300px;" value="${ssc11308RsBean.auditingRemark}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${ssc11308RsBean.auditingStatus ne '2' && ssc11308RsBean.payedStatus ne '9'}">
                                            <msk:button buttonValue="审核" buttonId="SSC11308.AUDITING" buttonType="button"/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        </c:if>

    </form>
</div>
</div>

<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>" />
<script src="<c:url value="/static/js/ssc/SSC11308.js" /> "/>
