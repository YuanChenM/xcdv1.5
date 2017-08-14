<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SO153106/update" id="so153106Param"
               metdod="post" enctype="multipart/form-data">
        <table id="SO153106DataGrid">
            <!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td class="left required">支付类型</td>
                <td><select name="amountType" id="amountType" value="${amountType}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${amountTypeList}" var="item" varStatus="status">
                        <c:choose>
                            <c:when test='${item.key == amountType}'>
                                <option value="${item.key}" selected="selected">${item.value}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.key}">${item.value}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left required">支付方式</td>
                <td><select name="paidType" id="paidType" value="${paidType}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${paidTypeList}" var="item" varStatus="status">
                        <c:if test='${item.key == paidType}'>
                            <option value="${item.key}" selected="selected">${item.value}</option>
                        </c:if>
                        <c:if test='${item.key != paidType}'>
                            <option value="${item.key}">${item.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left required">支付金额</td>
                <td>
                    <input type="text" id="paidAmount" name="paidAmount" value="${paidAmount}"/>
                </td>
            </tr>
            <tr>
                <td class="left">退款编码</td>
                <td>
                    <input type="text" id="refundCode" name="refundCode" value="${refundCode}"/>
                </td>
            </tr>
            <tr>
                <!-- Add for Bug#3307 by ren_yi at 2016/10/25start -->
                <td class="left">支付流水号</td>
                <!-- Add for Bug#3307 by ren_yi at 2016/10/25 end -->
                <td>
                    <input type="text" id="paidSeq" name="paidSeq" value="${paidSeq}"/>
                </td>
            </tr>
            <tr>
                <td class="left required">发生日期</td>
                <td>
                    <input type="text" id="operateTime" name="operateTime" value="${operateTime}"/>
                </td>
            </tr>
            <tr>
                <td class="left">经手人</td>
                <td>
                    <input type="text" id="handler" name="handler" value="${handler}"/>
                </td>
            </tr>
            <tr>
                <td class="left">备注</td>
                <td>
                    <input type="text" id="remark" name="remark" value="${remark}"/>
                </td>
            </tr>
            <!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SO153106.SAVE" buttonValue="保存"/>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SO153106.BACK" buttonValue="取消"/>
                </td>
            </tr>
        </table>
        <input type="hidden" id="buyerBillId" name="buyerBillId" value="${buyerBillId}"/>
        <input type="hidden" id="ver" name="ver" value="${ver}"/>
        <input type="hidden" id="runningId" name="runningId" value="${runningId}"/>
        <input type="hidden" id="oldAmountType" name="oldAmountType" value="${oldAmountType}"/>
        <input type="hidden" id="oldPaidAmount" name="oldPaidAmount" value="${oldPaidAmount}"/>
        <input type="hidden" id="srcPage" name="srcPage" value="${srcPage}"/>
    </form:form>
</div>
<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
<style>
    td.left {
        text-align: right;
        width: 50%;
        padding-right: 0.5em;
    }

    td.left.required:after {
        content: "*";
        color: red;
        position: absolute;
    }

</style>
<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
<script src="${ctx}/static/so/js/SO153106.js"></script>