<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SO153103/update/${buyerBillId}/${ver}/${srcPage}" id="so153103Param"
               metdod="post" enctype="multipart/form-data">
        <table id="SO153103DataGrid">
    		<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 start -->
            <tr>
                <td class="left required">费用调整类型</td>
                <td><select name="refundType" id="refundType">
                    <option value="">--请选择--</option>
                    <c:forEach items="${refundTypeList}" var="item" varStatus="status">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left">减免金额</td>
                <td>
                    <input type="text" id="reliefAmount" name="reliefAmount"/>
                </td>
            </tr>
            <tr>
                <td class="left">供应商</td>
                <td><select name="suppCode" id="suppCode">
                    <option value="">--请选择--</option>
                    <c:forEach items="${suppCodeList}" var="item" varStatus="status">
                        <option value="${item.userId}">${item.userNo}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td class="left">供应商名称</td>
                <td>
                    <input type="text" id="suppCodeName" name="suppCodeName" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td class="left">参考号</td>
                <td>
                    <input type="text" id="backNo" name="backNo"/>
                </td>
            </tr>
            <tr>
                <td class="left">发生日期</td>
                <td>
                    <input type="text" id="operateTime" name="operateTime"/>
                </td>
            </tr>
            <tr>
                <td class="left">备注</td>
                <td>
                    <input type="text" id="remark" name="remark"/>
                </td>
            </tr>
    		<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 end -->
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SO153103.SAVE" buttonValue="保存"/>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SO153103.BACK" buttonValue="取消"/>
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
<!-- Add for Bug#1652 by li_huiqian at 2016/9/8 end -->
<script src="${ctx}/static/so/js/SO153103.js"></script>