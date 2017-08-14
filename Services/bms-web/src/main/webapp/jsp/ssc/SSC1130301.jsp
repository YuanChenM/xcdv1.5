<%--
  title="合同管理一览画面"
  author"tao_zhifa"
  Created by IntelliJ IDEA.
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="RelationType" viewType="json" modelName="SSC"/>
<div class="page-content list-page">
    <form id="SSC1130301FormSearch" action="<c:url value='/SSC1130301/search' />" method="post">
        <input type="hidden" id="contractNameId" value="${ssc1130301RsBean.contractNameId}"/>
        <input type="hidden" id="contractCodeId" value="${ssc1130301RsBean.contractCodeId}"/>
        <input type="hidden" id="contractIdId" value="${ssc1130301RsBean.contractIdId}"/>
        <input type="hidden" id="purchaserNameId" value="${ssc1130301RsBean.purchaserNameId}"/>
        <input type="hidden" id="supplierNameId" value="${ssc1130301RsBean.supplierNameId}"/>
        <input type="hidden" id="bidProjectNoId" value="${ssc1130301RsBean.bidProjectNoId}"/>
        <input type="hidden" id="contractStatusId" value="${ssc1130301RsBean.contractStatusId}"/>
        <input type="hidden" name="contractStatusStr" value="${ssc1130301RsBean.contractStatusStr}"/>
        <input type="hidden" name="isPaymentRequest" value="${ssc1130301RsBean.isPaymentRequest}"/>
        <input type="hidden" id="isRelationContract" value="${ssc1130301RsBean.isRelationContract}"/>

        <div>
            <table id="SSC1130301_list_grid" width="100%">
                <thead>
                <th coltype="radio"></th>
                <th coltype="text" filter="true" name="contractCode">合同编号</th>
                <th coltype="text" filter="true" name="contractName">合同名称</th>
                <th coltype="text" filter="true" name="bidProjectNo">中标成交确认书编号</th>
                <th coltype="text" filter="true" name="purchaserName">甲方(采购商)</th>
                <th coltype="text" filter="true" name="supplierName">乙方(生产商)</th>
                <th coltype="text" filter="true" name="contractActDateStr" id="contractActDateStr">合同生效日期</th>
                <th coltype="code" filter="false" name="contractStatus" code2name="SSCORDERSTATUS_JSON" style="width:10%;">合同状态</th>
                <th coltype="code" filter="false" name="bidRelationType" code2name="RELATIONTYPE_JSON" style="width:10%;">合同关联中标类型</th>
                <th coltype="action" width="10px"></th>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div>
        </div>
    </form>
    <msk:button buttonValue="确定" buttonId="SSC1130301.CONFIRM" buttonType="button"/>
</div>

<script src="<c:url value='/static/js/ssc/SSC1130301.js' />"/>