<%--
    Title:产品详细信息页面
    author:zhao_chen1
    createDate:2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<navigation:header title="产品详细信息" backTitleArray="" backUrlArray=""></navigation:header>
<div style="white-space:nowrap;">
    <table>
        <tr>
            <td align="right">标准市场销售名：</td>
            <td>${ssc11302RsBeen.salesName}</td>
        </tr>
        <tr align="right">
            <td align="right">学名：</td>
            <td>${ssc11302RsBeen.scientificName}</td>
        </tr>
        <tr >
            <td align="right">俗名：</td>
            <td>${ssc11302RsBeen.localName}</td>
        </tr>
        <tr>
            <td align="right">产品特征：</td>
            <td>${ssc11302RsBeen.featureName}</td>
        </tr>
        <tr>
            <td align="right">净重(kg/箱)：</td>
            <td>${ssc11302RsBeen.weightName}</td>
        </tr>
        <tr>
            <td align="right">包装规格：</td>
            <td>${ssc11302RsBeen.normsName}</td>
        </tr>
    </table>
</div>

