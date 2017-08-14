<%--
    Title:核销单一览
    author:yang_yang
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="VerificationStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="VerificationAuditStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="VerificationType" viewType="json" modelName="SSC"/>

<navigation:header title="核销单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="<c:url value='/SSC11321/search'/>" method="post" id="SSC11321Form">
        <input type="hidden" name="contractCode" value="${contractCode}">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <div width="100%">
                <table WIDTH="100%">
                    <tr>
                        <td align="right" style="white-space:pre;">核销单编号 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['verificationCode']"/>
                        </td>
                        <td align="right" style="white-space:pre;">合同编号 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['contractCode']"/>
                        </td>
                        <td align="right" style="white-space:pre;">合同名称 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['contractName']"/>
                        </td>
                        <td align="right" style="white-space:pre;">合同状态 :</td>
                        <td align="left">　
                            <select name="filterMap['contractStatus']" style="width: 137px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractStatusList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">核销单状态 :</td>
                        <td align="left">　
                            <select name="filterMap['status']" style="width: 137px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${statusList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="right">审核状态 :</td>
                        <td align="left">　
                            <select name="filterMap['auditStatus']" style="width: 137px;">
                                <option value="">--请选择--</option>
                                <c:forEach items="${auditStatusList}" var="item" varStatus="status">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="right">核销人 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['chargerName']"/>
                        </td>
                        <td align="right">核销日期 :</td>
                        <td align="left">　
                            <input type="text" name="filterMap['verificationDateStr']"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" colspan="8">
                            <msk:button buttonValue="查询" buttonId="SSC11321.SEARCH" buttonType="button"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>核销单列表</label>
            </h3>
            <table width="100%" id="SSC11321_list_grid">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="verificationCode">核销单编号</th>
                    <th coltype="link" name="contractCode">合同编号</th>
                    <th coltype="text" name="contractName">合同名称</th>
                    <th coltype="code" width="150px" name="contractStatus" code2name="SSCORDERSTATUS_JSON">合同状态</th>
                    <th coltype="money" name="contractAmount">合同总金额(元)</th>
                    <th coltype="money" name="contractAmountPaid">合同已付金额(元)</th>
                    <th coltype="money" name="verificationAmount">差异总金额(元)</th>
                    <th coltype="code" name="status" code2name="VERIFICATIONSTATUS_JSON">核销单状态</th>
                    <th coltype="code" name="auditStatus" code2name="VERIFICATIONAUDITSTATUS_JSON">审核状态</th>
                    <th coltype="code" name="verificationType" code2name="VERIFICATIONTYPE_JSON">核销处理办法</th>
                    <th coltype="text" name="chargerName">核销人</th>
                    <th coltype="text" name="verificationDateStr">核销日期</th>
                    <th coltype="action">
                        操作
                        <msk:button buttonType="hidden" buttonValue="核销单详细" buttonId="SSC11321.DETAIL" coltype="detail"/>
                        <msk:button buttonType="hidden" buttonValue="删除" buttonId="SSC11321.DELETE" coltype="delete" useable="deletable"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="<c:url value='/static/js/ssc/SSC11321.js'/>"></script>
