<%--
    Title:支付管控单
    author:wu_honglei
    createDate:2016-08-09
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<form id="SSC1130801Form"  method="post" >

<div class="page-content list-page">
    <div style="white-space:nowrap;">
        <input type="hidden" name="paymentRequestId" id="paymentRequestId" value="${ssc1130801RsBean.paymentRequestId}" />
        <input type="hidden" name="paymentRequestCode" id="paymentRequestCode" value="${ssc1130801RsBean.paymentRequestCode}" />
        <input type="hidden" name="type" id="type" value="${ssc1130801RsBean.type}" />
        <input type="hidden"  id="paymentType" value="${ssc1130801RsBean.paymentType}" />
        <input type="hidden" id="deliveryId0801" value="${ssc1130801RsBean.deliveryId}" />
        <table style="width: 100%;" style="white-space:pre;">
            <tr>
                <th align="right">支付金额(元):</th>
                <td align="left" colspan="6">
                    <input type="text" id="amountOP" name="amount" style="width: 200px" />
                </td>
            </tr>

            <tr>
                <th width="15%" align="right">受款单位全称:</th>
                <td width="15%" align="left">
                    <input type="text" id="receiving" name="receiving" value="${ssc1130801RsBean.receiving}"  style="width: 200px" />

                </td>
                <td width="10%" align="left">
                    &nbsp; &nbsp;<img  src="../static/images/action/search.png" title="选择企业信息" id="chooseReEpInfo" style="cursor:pointer;" />
                </td>
                <th width="15%" align="right">受款银行:</th>
                <td width="15%" align="left">
                    <input type="text" id="receivingBank" name="receivingBank" value="${ssc1130801RsBean.receivingBank}" style="width: 200px" />
                </td>
                <th width="15%" align="right">受款账号:</th>
                <td width="15%" align="left">
                    <input type="text" id="receivingAccount" name="receivingAccount" value="${ssc1130801RsBean.receivingAccount}" style="width: 200px" />
                </td>
            </tr>


            <tr>
                <th  align="right">付款单位全称:</th>
                <td align="left">
                    <input type="text" id="payer" name="payer" style="width: 200px"/>&nbsp;&nbsp;&nbsp;&nbsp;

                </td>
                <td align="left"  >
                    &nbsp; &nbsp;<img  src="../static/images/action/search.png" title="选择企业信息" id="choosePEpInfo" style="cursor:pointer;"/>
                </td>
                <th  align="right">支付银行:</th>
                <td align="left">
                    <input type="text" id="payerBank" name="payerBank" style="width: 200px" />
                </td>
                <th  align="right">支付账号:</th>
                <td align="left">
                    <input type="text" id="paterAccount" name="paterAccount" style="width: 200px" />
                </td>
            </tr>
            <tr>
                <th  align="right">支付方式:</th>
                <td align="left" >
                    <select name="paidType" id="paidType">
                        <option value ="2">转账</option>
                        <option value ="6">微信支付</option>
                        <option value="7">支付宝支付</option>
                    </select>
                </td>
                <td></td>
                <th  align="right" id="subjectId">科目:</th>
                <td align="left" id="subjectValueId">
                    <input type="radio"  name="subject"   checked="checked" value="0" />货款
                    <input type="radio"  name="subject" value="1" id="freight"/>运费
                </td>
            </tr>
            <tr>
                <th  align="right">备注:</th>
                <td align="left" colspan="6">

                    <textarea type="text" name="remark" id="remarkOP"
                              style="width:300px;height:50px;"   />

                </td>
            </tr>

            <tr>
                <td colspan="6" align="right">
                    <msk:button buttonValue="保存" buttonId="SSC1130801.SAVE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</form>
<script src="<c:url value="/static/js/ssc/SSC1130801.js"/>"></script>
