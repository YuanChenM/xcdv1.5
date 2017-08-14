<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="SscPaymentType" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="Subject" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PaidType" viewType="json" modelName="SoCashpoolingConstant"/>

<navigation:header title="资金池详细" backTitleArray="资金池一览" backUrlArray="../SSC11319/init" />
<style type="text/css" media="all">
    a:link{
      color: #0000ff;
      text-decoration: none;
    }
   .tableCss{
       border-style: solid;
       border-width: 0;
       border-left: 1px solid #b1b1b1;
       border-top: 1px solid #b1b1b1;
   }
</style>

<div class="page-content list-page">
    <input type="hidden" name="contractId" id="contractId"  value="${ssc11320Bean.contractId}"/>
    <input type="hidden" name="contractCode" id="contractCode"  value="${ssc11320Bean.contractCode}"/>
    <input type="hidden" name="deliveryId" id="deliveryId"  value="${ssc11320Bean.deliveryId}"/>
    <input type="hidden" name="deliveryCode" id="deliveryCode"  value="${ssc11320Bean.deliveryCode}"/>
    <input type="hidden" name="verificationId" id="verificationId"  value="${ssc11320Bean.verificationId}"/>
    <input type="hidden" name="verificationCode" id="verificationCode"  value="${ssc11320Bean.verificationCode}"/>
    <input type="hidden" name="paymentId" id="paymentId"  value="${ssc11320Bean.paymentId}"/>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>资金池交易详细</label>
        </h3>
        <div>
            <table width="100%">
                <tr>
                    <td align="right" width="14%">对应合同总金额(元):</td>
                    <td width="14%"><span id="contractTotalAmount">${ssc11320Bean.contractTotalAmount}</span></td>
                    <td width="14%" align="right" >付款类型:</td>
                    <td width="14%">
                        <c:if test="${not empty ssc11320Bean.paymentType}">
                            <msk:codemaster codeType="SscPaymentType" viewType="label" modelName="SSC" codeValue="${ssc11320Bean.paymentType}"/>
                        </c:if>
                    </td>
                    <td align="right" width="14%" style="white-space:pre;">本次付款依据:</td>
                    <td width="14%">
                        <c:if test="${ssc11320Bean.paymentType==0}">
                            <a href="javascript:SSC11320.clickContractCodeLink();" >合同信息</a>
                        </c:if>
                        <c:if test="${ssc11320Bean.paymentType==1}">
                            <a href="javascript:SSC11320.clickDeliveryCodeLink();" >发货订单信息</a>
                        </c:if>
                        <c:if test="${ssc11320Bean.paymentType==2}">
                            <a href="javascript:SSC11320.clickVerificationLink();" >核销单信息</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="14%">科目:</td>
                    <td width="14%">
                        <c:if test="${not empty ssc11320Bean.subject}">
                            <msk:codemaster codeType="Subject" viewType="label" modelName="SSC" codeValue="${ssc11320Bean.subject}"/>
                        </c:if>
                        <c:if test="${empty ssc11320Bean.subject}">
                            -
                        </c:if>
                    </td>
                    <td width="14%" align="right" >支付方式:</td>
                    <td width="14%">
                        <c:if test="${not empty ssc11320Bean.paymentType}">
                            <msk:codemaster codeType="PaidType" viewType="label" modelName="SSC" codeValue="${ssc11320Bean.paidType}"/>
                        </c:if>
                    </td>
                    <td align="right" width="14%"></td>
                    <td width="14%"> </td>
                </tr>
                <tr>
                    <td align="right" width="14%">收款单位:</td>
                    <td width="14%" style="white-space:pre;">${ssc11320Bean.receiving} </td>
                    <td width="14%" align="right">收款银行:</td>
                    <td width="14%" style="white-space:pre;">${ssc11320Bean.receivingBank}</td>
                    <td align="right" width="14%">收款银行账户:</td>
                    <td width="14%"> ${ssc11320Bean.receivingAccount}</td>
                </tr>
                <tr>
                    <td align="right" width="14%">付款单位:</td>
                    <td width="14%">${ssc11320Bean.payer}</td>
                    <td width="14%" align="right" >付款银行:</td>
                    <td width="14%">${ssc11320Bean.payerBank}</td>
                    <td align="right" width="14%">付款银行账户:</td>
                    <td width="14%">${ssc11320Bean.paterAccount}</td>
                </tr>
                <tr>
                    <td width="14%" align="right" >付款申请编号:</td>
                    <td width="14%">${ssc11320Bean.paymentRequestCode}</td>
                    <td align="right" width="14%">付款时间:</td>
                    <td width="14%">${ssc11320Bean.remitTime}</td>
                    <td align="right" width="14%"></td>
                    <td width="14%"></td>
                </tr>
                <tr>
                    <td align="right" width="14%">本次付款申请总金额(元):</td>
                    <td width="14%"><span id="totalAmount">${ssc11320Bean.totalAmount}</span></td>
                    <td width="14%" align="right" >本次支付主体付款金额(元):</td>
                    <td width="14%"><span id="amount">${ssc11320Bean.amount}</span></td>
                    <td align="right" width="14%"></td>
                    <td width="14%"></td>
                </tr>
                <tr>
                    <td align="right" width="14%" style="white-space:pre;">本次付款申请累计付款金额(元):</td>
                    <td width="14%"><span id="accumulateAmount">${ssc11320Bean.accumulateAmount}</span></td>
                    <td width="14%" align="right" style="white-space:pre;">本次付款申请尚余应付金额(元):</td>
                    <td width="14%"><span id="remainAmount">${ssc11320Bean.remainAmount}</span> </td>
                    <td align="right" width="14%"></td>
                    <td width="14%"></td>
                </tr>
                <tr style="height: 8px;">
                    <td>
                                                 
                    </td>
                </tr>
                <tr>
                    <td align="right" width="14%">审批人:</td>
                    <td width="14%">${ssc11320Bean.approvalPerson} </td>
                    <fmt:formatDate var="approvalDate" value="${ssc11320Bean.approvalDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <td width="14%" align="right" >审批时间:</td>
                    <td width="14%">${approvalDate}</td>
                    <td align="right" width="14%"></td>
                    <td width="14%"> </td>
                </tr>
                <tr>
                    <td align="right" width="14%">审核人:</td>
                    <td width="14%">${ssc11320Bean.auditingPerson} </td>
                    <fmt:formatDate var="auditingDate" value="${ssc11320Bean.auditingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <td width="14%" align="right" >审核时间:</td>
                    <td width="14%">${auditingDate}</td>
                    <td align="right" width="14%"></td>
                    <td width="14%"> </td>
                </tr>
            </table>
        </div>
    </div>
</div>
    <script src="<c:url value="/static/js/ssc/SSC11320.js" /> " />