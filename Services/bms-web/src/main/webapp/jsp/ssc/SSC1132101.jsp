<%--
    Title:选择核销单
    author:yang_yang
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<msk:codemaster codeType="VerificationType" viewType="json" modelName="SSC"/>

<div class="page-content list-page">
    <form action="<c:url value="/SSC11321/search"/>" id="SSC1132101Form" method="post" >
        <input type="hidden" name="filterMap[delFlg]" value="${ssc11321RsParam.delFlg}">
        <input type="hidden" name="status" value="${ssc11321RsParam.status}">
        <input type="hidden" name="isPaymentRequest" value="${ssc11321RsParam.isPaymentRequest}">
        <input type="hidden" id="verificationCodeId" value="${ssc11321RsParam.verificationCodeId}"/>
        <input type="hidden" name="verificationType" value="${ssc11321RsParam.verificationType}"/>

        <div>
            <table width="100%" id="SSC1132101_list_grid">
                <thead>
                <tr>
                    <th coltype="radio"></th>
                    <th coltype="text" filter="true" name="verificationCode">核销单编号</th>
                    <th coltype="link" filter="true" name="contractCode">合同编号</th>
                    <th coltype="text" filter="true" name="contractName">合同名称</th>
                    <th coltype="money" name="contractAmount">合同总金额(元)</th>
                    <th coltype="money" name="contractAmountPaid">合同已付金额(元)</th>
                    <th coltype="money" name="verificationAmount">差异总金额(元)</th>
                    <th coltype="code" name="verificationType" code2name="VERIFICATIONTYPE_JSON">核销处理办法</th>
                    <th coltype="action"></th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </form>
    <msk:button buttonValue="确定" buttonId="SSC1132101.CONFIRM" buttonType="button"/>
</div>
<script src="<c:url value="/static/js/ssc/SSC1132101.js"/>" />


