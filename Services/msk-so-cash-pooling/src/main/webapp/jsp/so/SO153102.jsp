<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<!-- Delete for Bug#1652 by li_huiqian at 2016/9/8 start --><!--<style type="text/css">
    td {
        width: 10%;
    }
</style>-->
<!-- Delete for Bug#1652 by li_huiqian at 2016/9/8 end --><c:if test="${srcPage eq 'SO153101'}">
    <navigation:header title="买家资金池详情" backTitleArray="买家资金池管理"
                       backUrlArray="${ctx}/SO153101/init/"></navigation:header>
</c:if>
<c:if test="${srcPage eq 'SO153121'}">
    <navigation:header title="买手资金池详情" backTitleArray="买手资金池管理"
                       backUrlArray="${ctx}/SO153121/init/"></navigation:header>
</c:if>
<!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start -->
<form:form action="${ctx}/SO153102" id="SO153102Form" method="post" enctype="multipart/form-data">
    <input type="hidden" id="buyerBillId" name="buyerBillId" value="${buyerBillId}">
    <input type="hidden" id="srcPage" name="srcPage" value="${srcPage}">
    <input type="hidden" id="ver" name="ver" value="${ver}">
    <input type="hidden" id="transCode" name="transCode" value="${transCode}">
    <input type="hidden" id="runningId" name="runningId" value="">
    <input type="hidden" id="refundId" name="refundId" value="">
</form:form>
<!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end -->
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>订单交易信息</label>
        </h3>

        <div>
            <table width="100%">
				<!-- Bug#3247 modified by li_huiqian on 2016/10/12  -->
                <tr>
                    <td width="12.5%" colspan="1" style="text-align: right">订单编码:</td>
                    <td width="12.5%" colspan="1">
                        ${order.orderCode}
                    </td>
                    <td width="12.5%" colspan="1" style="text-align: right">订单ID:</td>
                    <td width="12.5%" colspan="1">
                        ${order.orderId}
                    </td>
                    <td width="12.5%" colspan="1" style="text-align: right">成交时间:</td>
                    <td width="12.5%" colspan="1">
                        <fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td width="12.5%" colspan="1" style="text-align: right">完结时间:</td>
                    <td width="12.5%" colspan="1">
                        <fmt:formatDate value="${order.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td width="12.5%" colspan="1" style="text-align: right">订单状态:</td>
                    <td width="12.5%" colspan="1">
                        <msk:codemaster codeType="OrderStatus" viewType="label" codeValue="${order.orderStatus}"/>
                    </td>
                    <td width="12.5%" colspan="1" style="text-align: right">收货地址:</td>
                    <td width="62.5%" colspan="5">
                    	${order.receiveInfo.receiverProvince}
                    	${order.receiveInfo.receiverCity}
                    	${order.receiveInfo.receiverDistrict}
                    	${order.receiveInfo.receiverAddr}
                    </td>
                 </tr>
                 <tr>
                    <td width="12.5%" colspan="1" style="text-align: right">买家留言:</td>
                    <td width="87.5%" colspan="7">
                    	${order.receiveInfo.remark}
                    	${order.receiveInfo.remark2}
                    	${order.receiveInfo.remark3}
                    	${order.receiveInfo.remark4}
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家计费明细</label>
        </h3>

        <div>
            <table width="100%">
                <tr>
					<!-- Modify for Bug#1652 by li_huiqian at 2016/9/8 start -->
                    <td width="12.5%" style="text-align: right">收款方编码:</td>
                    <td width="12.5%">
                        ${soBuyerBill.businessMainCode}
                    </td>
                    <td width="12.5%" style="text-align: right">收款方名称:</td>
                    <td width="12.5%">
                        ${soBuyerBill.businessMainName}
                    </td>
                    <!-- <td style="text-align: right">收款方角色:</td>
                    <td>
                        <msk:codemaster codeType="RoleType" viewType="label" codeValue="${soBuyerBill.businessMainRole}" />
                    </td> -->
                    <td width="12.5%" style="text-align: right">对账标志:</td>
                    <td width="12.5%">
                        <msk:codemaster codeType="StatementFlg" viewType="label"
                                        codeValue="${soBuyerBill.statementFlg}"/>
                    </td>
                    <td width="12.5%" style="text-align: right">结算状态:</td>
                    <td width="12.5%">
                        <msk:codemaster codeType="SettlementStatus" viewType="label"
                                        codeValue="${soBuyerBill.settlementStatus}"/>
                    </td>
					<!-- Modify for Bug#1652 by li_huiqian at 2016/9/8 end -->
                </tr>

                <tr>
                    <!-- Modify for Bug#3761 by li_huiqian on 20161124 start -->
                    <td style="text-align: right">应收金额:</td>
                    <!-- Modify for Bug#3761 by li_huiqian on 20161124 end -->
                    <td>
                        ${soBuyerBill.due}
                    </td>
                    <!-- Modify for Bug#3761 by li_huiqian on 20161124 start -->
                    <td style="text-align: right">实收金额:</td>
                    <!-- Modify for Bug#3761 by li_huiqian on 20161124 end -->
                    <td>
                        ${soBuyerBill.paid}
                    </td>
                    <td style="text-align: right">应退金额:</td>
                    <td>
                        ${soBuyerBill.refundable}
                    </td>
                    <td style="text-align: right">实退金额:</td>
                    <td>
                        ${soBuyerBill.realRefund}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">冲抵核销标志:</td>
                    <td>
                        <msk:codemaster codeType="MatchVerFlg" viewType="label" codeValue="${soBuyerBill.matchVerFlg}"/>
                    </td>
                    <td style="text-align: right">支付类型:</td>
                    <td>
                        <msk:codemaster codeType="PaymentType" viewType="label" codeValue="${soBuyerBill.paymentType}"/>
                    </td>
                    <td colspan="4">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">备注:</td>
                    <td colspan="7">
                        ${soBuyerBill.remark}
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
            <table id="#SO153102_list_grid" class="tree dataTable no-footer" style="width: 100% ">
                <!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start -->
                <thead>
                <tr style="background: #DBDBDB">
                    <th>序号</th>
                    <!--  <th>交易类型</th> -->
                    <th>退款编码</th>
                    <th>支付流水号</th>
                    <th>支付金额</th>
                    <th>支付方式</th>
                    <th>支付时间</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${!empty soRunningList}">
                    <c:forEach items="${soRunningList}" var="running" varStatus="status">
                        <tr data-runningid="${running.runningId}">
                            <td>${status.count}</td>
                            <!-- <td><msk:codemaster codeType="TransType" viewType="label"
                                                 codeValue="${running.transType}"/></td> -->
                            <td>${running.refundCode}</td>
                            <td>${running.paidSeq}</td>
                            <td>${running.paidAmount}</td>
                            <td><msk:codemaster codeType="PaidType" viewType="label"
                                                codeValue="${running.paidType}"/></td>
                            <td><fmt:formatDate value="${running.paidTime}" type="both"/></td>
                            <td>${running.remark}</td>
                            <td>
                                <a title="更新" class="cellButton edit"
                                   href="javascript:SO153102.editRunning(${status.index});" col="8">
                                    <img src="../static/images/action/edit.png">
                                </a>
                                <a title="删除" class="cellButton delete"
                                   href="javascript:SO153102.deleteRunning(${status.index});" col="8">
                                    <img src="../static/images/action/delete.png">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

                <!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end -->
                <%--添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start--%>
                <tr>
                    <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1" colspan="2"></td>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>合计:</td>
                    <td class='text' id="totalPaidAmount"
                        style='border: 1px solid #b1b1b1;text-align: left'>${totalPaidAmountBean.totalPaidAmount}</td>
                    <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1"
                        colspan="4 "></td>
                <tr>
                </tbody>
                    <%--添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end --%>
            </table>
        </div>
    </div>

    <div class="group-accordion" active=true>
        <h3>
            <label>应退款明细</label>
        </h3>

        <div>
            <table id="#SO153102_grid" class="tree dataTable no-footer" style="width: 100% ">
                <!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start -->
                <thead>
                <tr style="background: #DBDBDB">
                    <th>序号</th>
                    <th>退款编码</th>
                    <th>退款金额</th>
                    <th>退款日期</th>
                    <th>退回费用类型</th>
                    <%--<td>是否重新发货</td>--%>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end -->
                <c:if test="${!empty soRefundList}">
                    <c:forEach items="${soRefundList}" var="refund" varStatus="status">
                        <tr data-refundid="${refund.refundId}">
                            <td>${status.count}</td>
                            <td>${refund.refundCode}</td>
                            <td>${refund.refundAmount}</td>
                            <td><fmt:formatDate value="${refund.refundTime}" type="both"/></td>
                            <td><msk:codemaster codeType="RefundType" viewType="label"
                                                codeValue="${refund.refundType}"/></td>
                            <td>${refund.remark}</td>
                            <td>
                                <a title="更新" class="cellButton edit"
                                   href="javascript:SO153102.editRefund(${status.index});" col="7">
                                    <img src="../static/images/action/edit.png">
                                </a>
                                <a title="删除" class="cellButton delete"
                                   href="javascript:SO153102.deleteRefund(${status.index});" col="7">
                                    <img src="../static/images/action/delete.png">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <!-- 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end -->
                <%--添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start--%>
                <tr>
                    <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1" colspan="1"></td>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>合计:</td>
                    <td class="text" id="totalRefundAmount"
                        style="border: 1px solid #b1b1b1;text-align: left">${totalRefundAmountBean.totalRefundAmount}</td>
                    <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1" colspan="4"></td>
                <tr>
                </tbody>
                    <%--添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end --%>
            </table>
        </div>
    </div>
    <msk2:button buttonValue="页面打印" buttonId="SO153102.PRINT" buttonType="button" align="left"/>
</div>
<script src="${ctx}/static/so/js/SO153102.js"></script>
