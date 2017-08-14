<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SO153116/update/${sellerBillId}/${ver}/${srcPage}" id="SO153116Param"
               metdod="post" enctype="multipart/form-data">
        <table id="SO153116DataGrid">
    		<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td class="left required">支付类型</td>
                <td><select name="amountType" id="amountType">
                    <option value="">--请选择--</option>
                    <c:forEach items="${amountTypeList}" var="item" varStatus="status">
                        <c:if test='${item.key == "0"}'>
                            <option value="${item.key}" selected="selected">${item.value}</option>
                        </c:if>
                        <c:if test='${item.key != "0"}'>
                            <option value="${item.key}">${item.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left required">支付方式</td>
                <td><select name="paidType" id="paidType">
                    <option value="">--请选择--</option>
                    <c:forEach items="${paidTypeList}" var="item" varStatus="status">
                        <c:if test='${item.key == "5"}'>
                            <option value="${item.key}" selected="selected">${item.value}</option>
                        </c:if>
                        <c:if test='${item.key != "5"}'>
                            <option value="${item.key}">${item.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left required">支付金额</td>
                <td>
                    <input type="text" id="paidAmount" name="paidAmount" value="${billAmount}"/>
                </td>
            </tr>
            <tr>
                <td class="left">退款编码</td>
                <td>
                    <input type="text" id="backNo" name="backNo"/>
                </td>
            </tr>
            <tr>
                <td class="left required">支付流水号</td>
                <td>
                    <input type="text" id="paidSeq" name="paidSeq"/>
                </td>
            </tr>
            <tr>
                <td class="left required">发生日期</td>
                <td>
                    <input type="text" id="operateTime" name="operateTime"/>
                </td>
            </tr>
            <tr>
                <td class="left">经手人</td>
                <td>
                    <input type="text" id="handler" name="handler"/>
                </td>
            </tr>
            <tr>
                <td class="left">备注</td>
                <td>
                    <input type="text" id="remark" name="remark"/>
                </td>
            </tr>
    		<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SO153116.SAVE" buttonValue="保存"/>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SO153116.BACK" buttonValue="取消"/>
                </td>
            </tr>
        </table>
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
<script src="${ctx}/static/so/js/SO153116.js"></script>