<%--
    Title:资金池一览
    author:yang_yang
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="PaymentStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="SscPaymentType" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="Subject" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PaidType" viewType="json" modelName="SoCashpoolingConstant"/>

<navigation:header title="资金池一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">

    <form action="<c:url value="/SSC11319/search"/>" method="post" id="SSC11319Form" >
        <input type="hidden" name="contractCode" value="${contractCode}">
        <div class="group-accordion" collapsible="true" active="true">
            <h3 >
                <label>查询条件</label>
            </h3>
            <div width="100%" style="border-right: none; border-top: none;">
                <table WIDTH="100%" border="0">
                    <tr>
                        <td  align="right"  style="white-space:pre;">交易时间 :</td>
                        <td  align="left" >　
                            <input type="text" style="width: 120px" id="remitTimeStart" name="filterMap['remitTimeStart']" value="${param.filterMap.remitTimeStart}" readonly/>
                        </td>
                        <td  align="center" >~</td>
                        <td  align="left" >　
                            <input type="text"style="width: 120px" id="remitTimeEnd" name="filterMap['remitTimeEnd']" value="${param.filterMap.remitTimeEnd}" readonly/>
                        </td>
                        <td  align="right" style="white-space:pre;">合同编号 :</td>
                        <td  align="left">　
                            <input type="text" name="filterMap['contractCode']" value="${param.filterMap.contractCode}"/>
                        </td>
                        <td  align="right"  style="white-space:pre;">合同名称 :</td>
                        <td  align="left">　
                            <input type="text" name="filterMap['contractName']" value="${param.filterMap.contractName}" style="width: 199px"/>
                        </td>
                    </tr>
                    <tr>
                        <td  align="right" >付款方 :</td>
                        <td  align="left">　
                            <input type="text" name="filterMap['payer']" value="${param.filterMap.payer}" style="width: 120px"/>
                        </td>
                        <td  align="right"  style="white-space:pre;">收款方 :</td>
                        <td  align="left">　
                            <input type="text" name="filterMap['receiving']" value="${param.filterMap.receiving}" style="width: 120px"/>
                        </td>
                        <td  align="right" style="white-space:pre;">付款申请单编号 :</td>
                        <td  align="left">　
                            <input type="text" name="filterMap['paymentRequestCode']" value="${param.filterMap.paymentRequestCode}"/>
                        </td>
                        <td  align="right" >付款类型 :</td>
                        <td  align="left">　
                            <select name="filterMap['paymentType']" style="width: 206px">
                                <option value="">--请选择--</option>
                                <c:forEach items="${paymentTypeList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                            <%--<msk:codemaster codeType="SscPaymentType" id="orderSource" name='sscPaymentType' viewType="select" modelName="SSC"/>--%>
                        </td>
                        <%--<msk:button buttonValue="查询" buttonId="SSC11319.SEARCH" buttonType="button"/>--%>
                    </tr>
                    <tr>
                        <td align="right" colspan="8">
                            <msk:button buttonValue="查询" buttonId="SSC11319.SEARCH" buttonType="button"/>
                        </td>
                    </tr>
                </table>
                </div>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>资金池列表</label>
            </h3>

            <table width="100%" id="SSC11319_list_grid">
                <thead>
                <tr>
                    <%--<th coltype="sno">序号</th>--%>
                    <th coltype="text" name="remitTime">交易时间</th>
                    <th coltype="text" name="paymentRequestCode">付款申请单编号</th>
                    <th coltype="text" name="payer">付款方</th>
                    <th coltype="text" name="receiving">收款方</th>
                    <th coltype="code" name="subject" code2name="SUBJECT_JSON">科目</th>
                    <th coltype="code" name="paidType" code2name="PAIDTYPE_JSON">支付方式</th>
                    <th coltype="code" name="paymentType" code2name="SSCPAYMENTTYPE_JSON">付款类型</th>
                    <th coltype="money" name="amount">本次付款金额(元)</th>
                    <%--<th coltype="money" name="contractAmount">总金额</th>--%>
                    <th coltype="code" name="paymentStatus" code2name="PAYMENTSTATUS_JSON">付款状态</th>
                    <th coltype="link" name="paymentStr">付款依据</th>
                    <th coltype="link" name="contractCode">合同编号</th>
                    <th coltype="text" name="contractName">合同名称</th>
                    <th coltype="text" name="remark">备注</th>
                    <th coltype="action">操作
                        <msk:button buttonValue="资金池详细" buttonId="SSC11319.DETAIL" buttonType="hidden"
                                    coltype="detail"/>
                        <%--<msk:button buttonValue="删除" buttonId="SSC11319.DETELE"  buttonType="hidden"
                                    coltype="delete" useable="can_abolish"/>--%>

                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%--<c:if test="${null==contractCode}">
            <msk:button buttonValue="新增" buttonId="SSC11319.ADD" buttonType="button"/>
        </c:if>--%>
    </form>
</div>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>
<script src="<c:url value="/static/js/ssc/SSC11319.js"/>"></script>
