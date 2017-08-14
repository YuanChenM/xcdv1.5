<%--
    Title: 核销单详细
    author: xia_xiaojie
    createDate: 2016-08-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="navigation" uri="/msk" %>

<style type="text/css">
    a:link {color: blue;}
</style>

<c:set var="editable" value="${verification.contractStatus ne 9 and verification.status ne 5}"/>

<msk:codemaster codeType="VerificationStatus" viewType="json" modelName="SSC" />
<msk:codemaster codeType="VerificationAuditStatus" viewType="json" modelName="SSC" />
<msk:codemaster codeType="SscOrderStatus" viewType="json" modelName="SSC"/>

<c:if test="${empty navigation}">
    <navigation:header title="核销单详细" backTitleArray="核销单一览" backUrlArray="../SSC11321/init"></navigation:header>
</c:if>
<c:if test="${navigation eq 'payment'}">
    <navigation:header title="核销单详细" backTitleArray="资金池一览" backUrlArray="../SSC11319/init/"></navigation:header>
</c:if>
<c:if test="${navigation eq 'paymentDetail'}">
    <navigation:header title="核销单详细" backTitleArray="资金池一览,资金池详细" backUrlArray="../SSC11319/init,../SSC11320/init/" backParamArray='/{paymentId:${verification.paymentDetailId}}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'contractDetail'}">
    <navigation:header title="核销单详细" backTitleArray="合同管理一览,合同详细详细" backUrlArray="../SSC11303/init,../SSC11304/init/" backParamArray='/{contractId:${verification.contractId}}'></navigation:header>
</c:if>

<div class="page-content list-page">
    <form id="SSC11322_form">
        <input type="hidden" name="verificationId" value="${verification.verificationId}" />
        <input type="hidden" name="verificationCode" value="${verification.verificationCode}" />
        <input type="hidden" name="contractId" value="${verification.contractId}" />
        <input type="hidden" name="contractCode" value="${verification.contractCode}" />
        <input type="hidden" name="contractName" value="${verification.contractName}" />
        <input type="hidden" name="purchaserId" value="${verification.purchaserId}" />
        <input type="hidden" name="purchaserCode" value="${verification.purchaserCode}" />
        <input type="hidden" name="purchaserName" value="${verification.purchaserName}" />
        <input type="hidden" name="supplierId" value="${verification.supplierId}" />
        <input type="hidden" name="supplierCode" value="${verification.supplierCode}" />
        <input type="hidden" name="supplierName" value="${verification.supplierName}" />
        <input type="hidden" name="verificationAmount" value="${verification.verificationAmount}" />
        <input type="hidden" name="status" value="${verification.status}" />
        <input type="hidden" name="auditStatus" value="${verification.auditStatus}" />
        <input type="hidden" name="ver" value="${verification.ver}" />

    <!-- 订单合同核销单 -->
    <div class="group-accordion" collapsible="true" active="true">
        <h3><label>订单合同核销单</label></h3>
        <div>
            <table width="100%">
                <tr>
                    <td style="width:12%; text-align:right;">合同编号：</td>
                    <td>
                        <c:if test="${empty verification.verificationId}">
                            ${verification.contractCode}
                        </c:if>
                        <c:if test="${not empty verification.verificationId}">
                            <a href="javascript:SSC11322.clickContractCodeLink(${verification.contractId});">${verification.contractCode}</a>
                        </c:if>
                    </td>
                    <td style="width:12%; text-align:right;">合同名称：</td>
                    <td>${verification.contractName}</td>
                </tr>
                <tr>
                    <td style="white-space:nowrap; text-align:right;">甲方(采购商)：</td>
                    <td style="white-space:nowrap;">${verification.purchaserName}</td>
                    <td style="white-space:nowrap; text-align:right;">乙方(生产商)：</td>
                    <td style="white-space:nowrap;">${verification.supplierName}</td>
                </tr>
                <tr>
                    <td align="right">合同生效日期：</td>
                    <td>${verification.contractActDate}</td>
                    <td align="right">合同状态：</td>
                    <td><msk:codemaster codeType="SscOrderStatus" viewType="label" modelName="SSC" codeValue="${verification.contractStatus}"/></td>
                </tr>
                <tr>
                    <td align="right">核销单编号：</td>
                    <td>${verification.verificationCode}</td>
                    <td align="right">核销单状态：</td>
                    <td><msk:codemaster codeType="VerificationStatus" viewType="label" modelName="SSC" codeValue="${verification.status}" /></td>
                </tr>
                <tr>
                    <td align="right">审核状态：</td>
                    <td><msk:codemaster codeType="VerificationAuditStatus" viewType="label" modelName="SSC" codeValue="${verification.auditStatus}" /></td>
                    <td align="right">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td style="white-space:nowrap; text-align:right;">采购商确认人：</td>
                    <td>${verification.purchaserConfirmName}</td>
                    <td style="white-space:nowrap; text-align:right;">采购商确认时间：</td>
                    <td>
                        <fmt:formatDate var="purchaserConfirmTime" value="${verification.purchaserConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        ${purchaserConfirmTime}
                    </td>
                </tr>
                <tr>
                    <td align="right">生产商确认人：</td>
                    <td>${verification.supplierConfirmName}</td>
                    <td align="right">生产商确认时间：</td>
                    <td>
                        <fmt:formatDate var="supplierConfirmTime" value="${verification.supplierConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        ${supplierConfirmTime}
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!-- 发货入库差异单核销 -->
    <div class="group-accordion" collapsible="true" active="true">
        <h3><label>发货入库差异单核销</label></h3>
        <div>
            <table class="dataTable no-footer" width="100%">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>差异单编号</th>
                    <th>入库单编号</th>
                    <th>发货订单编号</th>
                    <th>已付预付款(元)</th>
                    <th>已付货款(元)</th>
                    <th>应付货款(元)</th>
                    <th>金额差(元)(采购方)</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productValueBeans}" var="productValueBean" varStatus="status">
                        <tr>
                            <td align="center">${status.index + 1}</td>
                            <td align="left">
                                <a href="javascript:SSC11322.clickDifferCodeLink(${productValueBean.differId});">${productValueBean.differCode}</a>
                            </td>
                            <td align="left" id="intoStoreCode${status.index}" intoStoreId="${productValueBean.intoStoreId}">${productValueBean.intoStoreCode}</td>
                            <td align="left">
                                <c:if test="${empty verification.verificationId}">
                                    ${productValueBean.deliveryCode}
                                </c:if>
                                <c:if test="${not empty verification.verificationId}">
                                    <a href="javascript:SSC11322.clickDeliveryCodeLink(${productValueBean.deliveryId});">${productValueBean.deliveryCode}</a>
                                </c:if>
                            </td>
                            <td align="right">${productValueBean.firstPaidStr}</td>
                            <td align="right">${productValueBean.valuePaidStr}</td>
                            <td align="right">${productValueBean.valueNeedStr}</td>
                            <td align="right">${productValueBean.valueDiffStr}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty productValueBeans}">
                        <tr>
                            <td colspan="4" style="font-weight:bold; text-align:center;">合计</td>
                            <td align="right">${productValueBeans[0].totalFirstPaidStr}</td>
                            <td align="right">${productValueBeans[0].totalValuePaidStr}</td>
                            <td align="right">${productValueBeans[0].totalValueNeedStr}</td>
                            <td align="right">${productValueBeans[0].totalValueDiffStr}</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 运输差异单核销 -->
    <div class="group-accordion" collapsible="true" active="true">
        <h3><label>运输差异单核销</label></h3>
        <div>
            <table class="dataTable no-footer" width="100%">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>发货订单编号</th>
                    <th>运输单编号</th>
                    <th>入库总重量(吨)</th>
                    <th>发货总重量(吨)</th>
                    <th>重量差(吨)</th>
                    <th>已付运费(元)</th>
                    <th>应付运费(元)</th>
                    <th>实需付运费(元)</th>
                    <th>金额差(元)(采购方)</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transportCostBeans}" var="transportCostBean" varStatus="status">
                        <input type="hidden" name="verificationDetails[${status.index}].verificationDetailId" value="${transportCostBean.verificationDetailId}" />
                        <input type="hidden" name="verificationDetails[${status.index}].deliveryId" value="${transportCostBean.deliveryId}" />
                        <input type="hidden" name="verificationDetails[${status.index}].deliveryCode" value="${transportCostBean.deliveryCode}" />
                        <input type="hidden" name="verificationDetails[${status.index}].freightActualPaid" value="${transportCostBean.freightDeal}" />
                        <tr>
                            <td align="center">${status.index + 1}</td>
                            <td align="left">
                                <c:if test="${empty verification.verificationId}">
                                    ${transportCostBean.deliveryCode}
                                </c:if>
                                <c:if test="${not empty verification.verificationId}">
                                    <a href="javascript:SSC11322.clickDeliveryCodeLink(${transportCostBean.deliveryId});">${transportCostBean.deliveryCode}</a>
                                </c:if>
                            </td>
                            <td align="left" id="intoStoreCode${status.index}" intoStoreId="${transportCostBean.intoStoreId}">${transportCostBean.intoStoreCode}</td>
                            <td align="right"><label name="weight">${transportCostBean.receiveWeight}</label></td>
                            <td align="right"><label name="weight">${transportCostBean.deliveryWeight}</label></td>
                            <td align="right"><label name="weight">${transportCostBean.weightDiff}</label></td>
                            <td align="right" id="freightPaid${status.index}">${transportCostBean.freightPaidStr}</td>
                            <td align="right">${transportCostBean.freightNeedStr}</td>
                            <td style="padding:0; text-align:center;">
                                <input type="text" id="freightDeal${status.index}" value="${transportCostBean.freightDealStr}" <c:if test="${empty verification.verificationId or verification.status ge 3}">disabled="disabled"</c:if> />
                            </td>
                            <td align="right" id="freightDiff${status.index}">${transportCostBean.freightDiffStr}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty transportCostBeans}">
                        <tr>
                            <td colspan="3" style="font-weight:bold; text-align:center;">合计</td>
                            <td align="right"><label name="weight">${transportCostBeans[0].totalReceiveWeight}</label></td>
                            <td align="right"><label name="weight">${transportCostBeans[0].totalDeliveryWeight}</label></td>
                            <td align="right"><label name="weight">${transportCostBeans[0].totalWeightDiff}</label></td>
                            <td align="right">${transportCostBeans[0].totalFreightPaidStr}</td>
                            <td align="right">${transportCostBeans[0].totalFreightNeedStr}</td>
                            <td align="right" id="totalFreightDeal">${transportCostBeans[0].totalFreightDealStr}</td>
                            <td align="right" id="totalFreightDiff">${transportCostBeans[0].totalFreightDiffStr}</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!---->
    <div style="margin-top:15px;">
        <span style="margin-left:36px;">合同总金额(元)：${verification.contractAmountStr}</span>
        <br /><br />
        <span style="margin-left:12px;">合同实际应付款(元)：${verification.contractAmountDealStr}</span>&nbsp;&nbsp;(含应付货款(元)：${verification.totalValueNeedStr}，应付运费(元)：${verification.totalFreightDealStr})
        <br /><br />
        <span style="margin-left:12px;">合同实际已付款(元)：${verification.contractAmountPaidStr}</span>&nbsp;&nbsp;(含合同已付预付款(元)：<span id="firstAmountPaid">${verification.firstAmountPaidStr}</span>，已付货款(元)：${verification.totalValuePaidStr}，已付运费(元)：${verification.totalFreightPaidStr})
        <br /><hr align="left" width="800px" />
        <c:set var="pv_blue" value="${verification.totalValueDiff gt 0}"/>
        <c:set var="pv_red" value="${verification.totalValueDiff lt 0}"/>
        <span>发货入库差异金额(元)：</span><span id="productValueDiff" <c:if test="${pv_blue}">style="color:blue;"</c:if><c:if test="${pv_red}">style="color:red;"</c:if>>${verification.totalValueDiffStr}</span>
        <br /><br />
        <c:set var="tc_blue" value="${verification.totalFreightDiff gt 0}"/>
        <c:set var="tc_red" value="${verification.totalFreightDiff lt 0}"/>
        <span style="margin-left:24px;">运输差异金额(元)：</span><span id="transportCostDiff" <c:if test="${tc_blue}">style="color:blue;"</c:if><c:if test="${tc_red}">style="color:red;"</c:if>>${verification.totalFreightDiffStr}</span>&nbsp;&nbsp;(注：运输差异金额 = 实需付运费 - 已付运费)
        <br /><br />
        <c:set var="va_blue" value="${verification.verificationAmount gt 0}"/>
        <c:set var="va_red" value="${verification.verificationAmount lt 0}"/>
        <span style="margin-left:36px;">合计金额差(元)：</span><span id="verificationAmountStr" <c:if test="${va_blue}">style="color:blue;"</c:if><c:if test="${va_red}">style="color:red;"</c:if>>${verification.verificationAmountStr}</span>&nbsp;&nbsp;(注：合计金额差 = 发货入库差异金额 + 运输差异金额 - 合同已付预付款)
        <br /><br />
        <span style="margin-left:24px;">核销金额差异结果：</span>
        <span id="verificationResult" style="margin-left:-6px;">
            <c:if test="${verification.verificationAmount eq '0.00'}">
                无
            </c:if>
            <c:if test="${verification.verificationAmount gt 0}">
                甲方应支付乙方${verification.verificationAmountStr}元
            </c:if>
            <c:if test="${verification.verificationAmount lt 0}">
                乙方应支付甲方${fn:replace(verification.verificationAmountStr, "-", "")}元
            </c:if>
        </span>
        <br /><br />
        <c:if test="${not empty verification.verificationId}">
            <span style="margin-left:48px;">核销处理办法：</span>
            <span style="margin-left:-10px;">
                <input type="radio" name="verificationType" value="0" <c:if test="${verification.verificationType eq 0}">checked="checked"</c:if> <c:if test="${verification.status ge 3}">disabled="disabled" </c:if> />退款
                <input type="radio" name="verificationType" value="1" <c:if test="${verification.verificationType eq 1}">checked="checked"</c:if> <c:if test="${verification.status ge 3}">disabled="disabled" </c:if> />挂账
                <input type="radio" name="verificationType" value="2" <c:if test="${verification.verificationType eq 2}">checked="checked"</c:if> <c:if test="${verification.status ge 3}">disabled="disabled" </c:if> />其它，
                具体描述：<textarea name="remark" id="remark" cols="28" maxlength="100" <c:if test="${verification.status ge 3}">disabled="disabled" </c:if>>${verification.remark}</textarea>
            </span>
            <br/><br/>
        </c:if>
        <span style="margin-left:96px;">备注：</span>
        <span style="margin-left:-7px;">
            <textarea name="verificationRemark" id="verificationRemark" cols="65" maxlength="100" <c:if test="${verification.status ge 3}">disabled="disabled" </c:if>>${verification.verificationRemark}</textarea>
        </span>
        <br /><br />
        <c:if test="${verification.status ge 1}">
            <span style="margin-left:73px;">甲方确认：</span>
            <span style="margin-left:-10px;">
                <input type="radio" value="0" name="purchaserConfirmStatus" <c:if test="${verification.purchaserConfirmStatus eq 0}">checked="checked"</c:if> <c:if test="${verification.auditStatus eq 1 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />同意
                <input type="radio" value="1" name="purchaserConfirmStatus" <c:if test="${verification.purchaserConfirmStatus eq 1}">checked="checked"</c:if> <c:if test="${verification.auditStatus eq 1 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />不同意
                <input type="text" id="purchaserConfirmRemark" maxlength="100"  value="${verification.purchaserConfirmRemark}" style="height:25px; width:302px;" <c:if test="${verification.auditStatus eq 1 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />
                <c:if test="${editable and (verification.auditStatus ne 1 and verification.auditStatus ne 3)}">
                    <msk:button buttonType="button" buttonId="SSC11322.PURCHASER_CONFIRM" buttonValue="甲方(采购商)确认" />
                </c:if>
            </span>
            <br /><br />
            <span style="margin-left:73px;">乙方确认：</span>
            <span style="margin-left:-10px;">
                <input type="radio" value="0" name="supplierConfirmStatus" <c:if test="${verification.supplierConfirmStatus eq 0}">checked="checked"</c:if> <c:if test="${verification.auditStatus eq 2 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />同意
                <input type="radio" value="1" name="supplierConfirmStatus" <c:if test="${verification.supplierConfirmStatus eq 1}">checked="checked"</c:if> <c:if test="${verification.auditStatus eq 2 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />不同意
                <input type="text" id="supplierConfirmRemark" maxlength="100" value="${verification.supplierConfirmRemark}" style="height:25px; width:302px;" <c:if test="${verification.auditStatus eq 2 or verification.auditStatus eq 3}">disabled="disabled" </c:if> />
                <c:if test="${editable and (verification.auditStatus ne 2 and verification.auditStatus ne 3)}">
                    <msk:button buttonType="button" buttonId="SSC11322.SUPPLIER_CONFIRM" buttonValue="乙方(生产商)确认" />
                </c:if>
            </span>
            <br /><br />
        </c:if>
        <c:if test="${editable and (empty verification.verificationId or verification.status lt 3)}">
            <msk:button buttonType="button" buttonId="SSC11322.VERIFICATION_SAVE" buttonValue="保存" />
        </c:if>
        <c:if test="${editable and (verification.status ge 3 and verification.verificationType eq 0)}">
            <msk:button buttonType="button" buttonId="SSC11322.PAYMENT_REQUEST" buttonValue="发起付款申请" />
        </c:if>
        <c:if test="${editable and verification.status ge 3}">
            <msk:button buttonType="button" buttonId="SSC11322.INVOICE_REQUEST" buttonValue="发票信息" />
        </c:if>
        <c:if test="${editable and verification.status eq 3}">
            <msk:button buttonType="button" buttonId="SSC11322.CONTRACT_CLOSE" buttonValue="合同结案" />
        </c:if>
    </div>
    </form>
</div>

<script src="<c:url value='/static/js/ssc/SSCCommon.js' />"></script>
<script src="<c:url value='/static/js/ssc/SSC11322.js' />"></script>