<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:市场需求审核注册修改添加
    author:pxg
    createDate:2016-02-23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<style type="text/css">
    #btn{
        position: relative;
        left:20px;
    }
</style>
<div align="center">
<form:form action="${ctx}/PD141134/save" id="PD141134Form"
           method="post">
            <table>
                <tr>
                    <td >标准:</td>
                    <td><input type="text" name="normsSuttle" value="${standard}"/> </td>
                </tr>
                <tr>
                    <td >调研:</td>
                    <td><input type="text" name="normsSuttle" value="${survey}"/> </td>
                </tr>
                </table>
    </form:form>
    <div id="btn">
        <msk:button buttonValue="保存" buttonId="PD141134.save" buttonType="button"/>
        <msk:button buttonValue="返回" buttonId="PD141134.back" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD141134.js"></script>
