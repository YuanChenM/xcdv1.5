<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SO153105/update" id="so153105Param"
               metdod="post" enctype="multipart/form-data">
        <table id="SO153105DataGrid">
            <!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td class="left required">费用调整类型</td>
                <td><select name="refundType" id="refundType" value="${refundType}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${refundTypeList}" var="item" varStatus="status">
                        <c:if test="${item.key == refundType}">
                            <option value="${item.key}" selected="selected">${item.value}</option>
                        </c:if>
                        <c:if test="${item.key != refundType}">
                            <option value="${item.key}">${item.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left">减免金额</td>
                <td>
                    <input type="text" id="reliefAmount" name="reliefAmount" value="${reliefAmount}"/>
                </td>
            </tr>
            <tr>
                <td class="left">供应商</td>
                <td><select name="suppCode" id="suppCode" value="${suppCode}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${suppCodeList}" var="item" varStatus="status">
                        <c:if test="${item.userId == suppCode}">
                            <option value="${item.userId}" selected="selected">${item.userNo}</option>
                        </c:if>
                        <c:if test="${item.userId != suppCode}">
                            <option value="${item.userId}">${item.userNo}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left">供应商名称</td>
                <td>
                    <input type="text" id="suppCodeName" name="suppCodeName" value="${suppCodeName}" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td class="left">参考号</td>
                <td>
                    <input type="text" id="referenceCode" name="referenceCode" value="${referenceCode}"/>
                </td>
            </tr>
            <tr>
                <td class="left">发生日期</td>
                <td>
                    <input type="text" id="operateTime" name="operateTime" value="${operateTime}"/>
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
                    <msk2:button buttonType="button" buttonId="SO153105.SAVE" buttonValue="保存"/>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SO153105.BACK" buttonValue="取消"/>
                </td>
            </tr>
        </table>
        <input type="hidden" id="buyerBillId" name="buyerBillId" value="${buyerBillId}"/>
        <input type="hidden" id="ver" name="ver" value="${ver}"/>
        <input type="hidden" id="refundId" name="refundId" value="${refundId}"/>
        <input type="hidden" id="oldReliefAmount" name="oldReliefAmount" value="${oldReliefAmount}"/>
        <input type="hidden" id="oldRefundType" name="oldRefundType" value="${oldRefundType}"/>
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
<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 end -->
<script src="${ctx}/static/so/js/SO153105.js"></script>