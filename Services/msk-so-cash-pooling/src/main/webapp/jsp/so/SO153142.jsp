<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<div class="page-content list-page">
    <form:form action="${ctx}/SO153142/update/${accountBookId}/${ver}" id="SO153142Param"
               metdod="post" enctype="multipart/form-data">
        <table id="SO153142DataGrid">
            <tr>
                <td align="right" width="50%">用户编码</td>
                <td align="left" width="50%">
                    <input  id="userNo" name="userNo" value="${userNo}" readonly = "true"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">支付方式</td>
                <td><select name="paidType" id="paidType">
                    <option value="">--请选择--</option>
                    <c:forEach items="${paidTypeList}" var="item" varStatus="status">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">期初金额</td>
                <td align="left" width="50%">
                    <input type="text" id="periodAmount" name="periodAmount"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">支付流水号</td>
                <td align="left" width="50%">
                    <input type="text" id="paidSeq" name="paidSeq"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">发生日期</td>
                <td align="left" width="50%">
                    <input type="text" id="operateTime" name="operateTime"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="50%">备注</td>
                <td align="left" width="50%">
                    <input type="text" id="remark" name="remark"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <msk2:button buttonType="button" buttonId="SO153142.SAVE" buttonValue="保存"/>
                </td>
                <td align="left">
                    <msk2:button buttonType="button" buttonId="SO153142.BACK" buttonValue="取消"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script src="${ctx}/static/so/js/SO153142.js"></script>