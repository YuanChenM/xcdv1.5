<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<input type="hidden" id="srcPage" value="${srcPage}">
<input type="hidden" name="sellerBillId1" id="sellerBillId1" value="${sellerBillId}"/>
<input type="hidden" name="sellerBillId" id="sellerBillId" value="${sellerBillId}"/>
<input type="hidden" name="ver" id="ver" value="${ver}"/>

<c:if test="${srcPage eq 'SO153111'}">
    <navigation:header title="卖家资金池详情" backTitleArray="卖家资金池管理"
                       backUrlArray="${ctx}/SO153111/init/"></navigation:header>
</c:if>
<c:if test="${srcPage eq 'SO153121'}">
    <navigation:header title="买手资金池详情" backTitleArray="买手资金池管理"
                       backUrlArray="${ctx}/SO153121/init/"></navigation:header>
</c:if>
<!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start -->
<form:form action="${ctx}/SO153112" id="SO153112DeleteForm" method="post" enctype="multipart/form-data">
    <input type="hidden" id="sellerBillId" name="sellerBillId" value="${sellerBillId}">
    <input type="hidden" id="srcPage" name="srcPage" value="${srcPage}">
    <input type="hidden" id="ver" name="ver" value="${ver}">
    <input type="hidden" id="transCode" name="transCode" value="">
    <input type="hidden" id="runningId" name="runningId" value="">
    <input type="hidden" id="id" name="id" value="">
    <input type="hidden" id="tb" name="tb" value="">
</form:form>
<!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end -->
<div class="page-content list-page">
    <form action="${ctx}/SO153112/search" id="SO153112Form" method="post">

        <!--
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        <label>订单交易信息</label>
      </h3>
      <div>
        <table width="100%">
          <tr>
            <td width="100px" style="text-align: right">收货地址:</td>
            <td width="12.5%">
                  ${order.orderReceiveAddr}
            </td>
            <td width="100px" style="text-align: right">订单状态:</td>
            <td width="12.5%">
              <msk:codemaster codeType="OrderStatus" viewType="label" codeValue="${order.orderStatus}" />
            </td>
            <td width="100px" style="text-align: right">成交时间:</td>
            <td>
                 <fmt:formatDate value="${order.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td width="100px" style="text-align: right">发货时间:</td>
            <td>
                 <fmt:formatDate value="${order.orderSendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr>
            <td style="text-align: right">卖家留言:</td>
            <td>
                 ${order.remark}
            </td>
            <td style="text-align: right">订单编号:</td>
            <td>
                  ${order.orderCode}
            </td>
            <td style="text-align: right">付款时间:</td>
            <td>
              <fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td style="text-align: right">完结时间:</td>
            <td>
              <fmt:formatDate value="${order.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
        </table>
      </div>
    </div>
    -->
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>卖家计费明细</label>
            </h3>

            <div>
                <table width="100%">
                    <tr>
                        <td width="100px" style="text-align: right">收款方编码:</td>
                        <td>
                            ${soSellerBill.businessMainCode}

                        </td>
                        <td width="100px" style="text-align: right">收款方名称:</td>
                        <td>
                            ${soSellerBill.businessMainName}
                        </td>
                        <!-- <td width="100px" style="text-align: right">收款方角色:</td>
            <td>
                <msk:codemaster codeType="RoleType" viewType="label" codeValue="${soSellerBill.businessMainRole}" />
            </td> -->
                        <td width="100px" style="text-align: right">对账标志:</td>
                        <td>
                            <msk:codemaster codeType="StatementFlg" viewType="label"
                                            codeValue="${soSellerBill.statementFlg}"/>
                        </td>
                        <td width="100px" style="text-align: right">结算状态:</td>
                        <td>
                            <msk:codemaster codeType="SettlementStatus" viewType="label"
                                            codeValue="${soSellerBill.settlementStatus}"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="100px" style="text-align: right">结算标志:</td>
                        <td>
                            <msk:codemaster codeType="SettlementFlg" viewType="label"
                                            codeValue="${soSellerBill.settlementFlg}"/>
                        </td>
                        <td width="100px" style="text-align: right">起始日期:</td>
                        <td>
                            ${soSellerBill.startDateStr}
                        </td>
                        <td width="100px" style="text-align: right">终止日期:</td>
                        <td>
                            ${soSellerBill.endDateStr}
                        </td>
                        <td width="100px" style="text-align: right">支付类型:</td>
                        <td>
                            <msk:codemaster codeType="PaymentType" viewType="label"
                                            codeValue="${soSellerBill.paymentType}"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="100px" style="text-align: right">计费类型:</td>
                        <td>
                            <msk:codemaster codeType="BillType" viewType="label" codeValue="${soSellerBill.billType}"/>
                        </td>
                        <td width="100px" style="text-align: right">计费金额:</td>
                        <td>
                            ${soSellerBill.billAmount}
                        </td>
                        <!-- Modify for Bug#3761 by li_huiqian on 20161124 start -->
                        <td width="100px" style="text-align: right">应付金额:</td>
                        <!-- Modify for Bug#3761 by li_huiqian on 20161124 end -->
                        <td>
                            ${soSellerBill.receiveable}
                        </td>
                        <td width="100px" style="text-align: right">应退金额:</td>
                        <td>
                            ${soSellerBill.refundable}
                        </td>
                    </tr>
                    <tr>
                        <!-- Modify for Bug#3761 by li_huiqian on 20161124 start -->
                        <td width="100px" style="text-align: right">实付金额:</td>
                        <!-- Modify for Bug#3761 by li_huiqian on 20161124 end -->
                        <td>
                            ${soSellerBill.received}
                        </td>
                        <td width="100px" style="text-align: right">实退金额:</td>
                        <td>
                            ${soSellerBill.realRefund}
                        </td>
                        <td width="100px" style="text-align: right">冲抵核销标志:</td>
                        <td>
                            <msk:codemaster codeType="MatchVerFlg" viewType="label"
                                            codeValue="${soSellerBill.matchVerFlg}"/>
                        </td>
                        <td width="100px" style="text-align: right">手续费率:</td>
                        <td>
                            ${soSellerBill.chargeRate}
                        </td>

                    </tr>

                    <tr>
                        <td width="100px" style="text-align: right">交易支付状态:</td>
                        <td>
                            <msk:codemaster codeType="TransPaidStatus" viewType="label"
                                            codeValue="${soSellerBill.transPaidStatus}"/>
                        </td>
                        <td width="100px" style="text-align: right">手续费:</td>
                        <td>
                            ${soSellerBill.handingCharge}
                        </td>
                        <td width="100px" style="text-align: right">手续费支付状态:</td>
                        <td>
                            <msk:codemaster codeType="TransPaidStatus" viewType="label"
                                            codeValue="${soSellerBill.chargeStatus}"/>
                        </td>

                    </tr>

                    <tr>
                        <td width="100px" style="text-align: right">备注:</td>
                        <td colspan="7">
                            ${soSellerBill.remark}
                        </td>

                    </tr>

                </table>
            </div>
        </div>


        <div class="group-accordion" active=true>
            <h3>
                <label>支付明细</label>
            </h3>

            <div>
                <table id="SO153112_running" class="tree dataTable no-footer" style="width: 100% ">
                    <!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start -->
                    <thead>
                    <tr style="background: #DBDBDB">
                        <th>序号</th>
                        <!-- Bug #3307 Modified by li_huiqian on 2016/10/13 start -->
                        <th>卖家计费单号</th>
                        <!-- Bug #3307 Modified by li_huiqian on 2016/10/13 end -->
                        <!-- <th>交易类型</th> -->
                        <th>退款编码</th>
                        <th>支付流水号</th>
                        <th>支付金额</th>
                        <th>支付方式</th>
                        <th>支付日期</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty soRunningList}">
                        <c:forEach items="${soRunningList}" var="running" varStatus="status">
                            <tr data-runningid="${running.runningId}">
                                <td>${status.count}</td>
                                <td>${running.transCode}</td>
                                <!--   <td><msk:codemaster codeType="TransType" viewType="label" codeValue="${running.transType}" /></td> -->
                                <td>${running.refundCode}</td>
                                <td>${running.paidSeq}</td>
                                <td>${running.paidAmount}</td>
                                <td><msk:codemaster codeType="PaidType" viewType="label"
                                                    codeValue="${running.paidType}"/></td>
                                <td><fmt:formatDate value="${running.paidTime}" type="both"/></td>
                                <td>${running.remark}</td>
                                <td>
                                    <a title="更新" class="cellButton edit"
                                       href="javascript:SO153112.editRunning(${status.index});" col="8">
                                        <img src="../static/images/action/edit.png">
                                    </a>
                                    <a title="删除" class="cellButton delete"
                                       href="javascript:SO153112.deleteRunning(${status.index});" col="8">
                                        <img src="../static/images/action/delete.png">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end -->
                    <%--添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start--%>
                    <tr>
                        <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1"
                            colspan="3"></td>
                        <td class="text" style="border: 1px solid #b1b1b1; background-color: #F5FFFA">合计:</td>
                        <td class="text" id="totalPaidAmount"
                            style="border: 1px solid #b1b1b1;text-align: left">${totalPaidAmountBean.totalPaidAmount}</td>
                        <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1"
                            colspan="4"></td>
                    <tr>
                    </tbody>
                        <%--添加买家资金池详细页面合计 modify by renyi on 2016/8/12 end --%>
                </table>
            </div>
        </div>

        <div class="group-accordion" active=true>
            <h3>
                <label>应退款明细</label>
            </h3>

            <div>
                <table id="SO153112_refund" class="tree dataTable no-footer" style="width: 100% ">
                    <!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start -->
                    <thead>
                    <tr style="background: #DBDBDB">
                        <th>序号</th>
                        <th>退款编码</th>
                        <th>订单号</th>
                        <%--<th width="12.5%">订单日期</th>--%>
                        <th>退款金额</th>
                        <th>退款日期</th>
                        <th>退回费用类型</th>
                        <%--<th width="10%">是否重新发货</th>--%>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty soRefundList}">
                        <c:forEach items="${soRefundList}" var="refund" varStatus="status">
                            <tr data-id="${refund.id}" data-tb="${refund.tb}" data-transcode="${refund.transCode}">
                                <td>${status.count}</td>
                                <td>${refund.refundCode}</td>
                                <td>${refund.transCode}</td>
                                    <%--<td width="10%"></td>--%>
                                <td>${refund.refundAmount}</td>
                                <td><fmt:formatDate value="${refund.refundTime}" type="both"/></td>
                                <td><msk:codemaster codeType="RefundType" viewType="label"
                                                    codeValue="${refund.refundType}"/></td>
                                    <%--<td width="10%"><msk:codemaster codeType="ReShipFlg" viewType="label" codeValue="${refund.reShipFlg}" /></td>--%>
                                <td>${refund.remark}</td>
                                <td>
                                    <a title="更新" class="cellButton edit"
                                       href="javascript:SO153112.editRefund(${status.index});" col="7">
                                        <img src="../static/images/action/edit.png">
                                    </a>
                                    <a title="删除" class="cellButton delete"
                                       href="javascript:SO153112.deleteRefund(${status.index});" col="7">
                                        <img src="../static/images/action/delete.png">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <!-- 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end -->
                    <%--添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start--%>
                    <tr>
                        <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1"
                            colspan="2"></td>
                        <td class="text" style="border: 1px solid #b1b1b1; background-color: #F5FFFA">合计:</td>
                        <td class="text" id="totalRefundAmount"
                            style="border: 1px solid #b1b1b1;text-align: left">${totalRefundAmountBean.totalRefundAmount}</td>
                        <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1"
                            colspan="4"></td>
                    <tr>
                    </tbody>
                        <%--添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end --%>
                </table>
            </div>

        </div>
    </form>
    <msk2:button buttonValue="页面打印" buttonId="SO153112.PRINT" buttonType="button" align="left"/>
</div>
<script src="${ctx}/static/so/js/SO153112.js"></script>

