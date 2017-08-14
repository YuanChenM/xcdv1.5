<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:卖家供应备案注册标准目录添加
    author:pxg
    createDate:2016-03-14
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<style type="text/css">
    #btn{
        position: relative;
        left:80px;
    }
</style>
<div>
<form:form action="${ctx}/PD14112801/save" id="PD14112801Form"
           method="post">
    <input type="hidden" value="${breedCode}" name="breedCode"/>
    <input type="hidden" value="${getDivId}" name="getDivId"/>
            <table>
                <tr>
                    <td >标准目录值：</td>
                    <td><input type="text" name="featureName" value="${featureName}"/> </td>
                </tr>
                <tr>
                    <td >产品销售对象：</td>
                    <td><input type="text" name="salesTarget" value="${salesTarget}"/> </td>
                </tr>
                <tr>
                    <td >产品加工方向：</td>
                    <td><input type="text" name="machiningWay" value="${machiningWay}"/> </td>
                </tr>
                </table>
    </form:form>
    <div id="btn">
        <msk:button buttonValue="保存" buttonId="PD14112801.SAVE" buttonType="button"/>
        <msk:button buttonValue="返回" buttonId="PD14112801.BACK" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD14112801.js"></script>
