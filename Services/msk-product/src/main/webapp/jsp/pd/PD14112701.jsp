<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:正式上线修改添加
    author:pxg
    createDate:2016-02-23
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
<form:form action="${ctx}/PD14112701/save" id="PD14112701Form"
           method="post">
    <input type="hidden" value="${breedCode}" name="breedCode"/>
    <input type="hidden" value="${getDivId}" name="getDivId"/>
            <table>
                <tr>
                    <td >OEM正式上线值：</td>
                    <td><input type="text" name="featureName" value="${featureName}"/> </td>
                </tr>
                </table>
    </form:form>
    <div id="btn">
        <msk:button buttonValue="保存" buttonId="PD14112701.save" buttonType="button"/>
        <msk:button buttonValue="返回" buttonId="PD14112701.back" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD14112701.js"></script>
