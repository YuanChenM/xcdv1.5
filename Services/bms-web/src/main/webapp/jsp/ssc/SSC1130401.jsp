<%--
    Title:产品详细信息页面
    author:zhao_chen1
    createDate:2016-06-28
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<navigation:header title="产品详细信息" backTitleArray="" backUrlArray=""></navigation:header>
<div>
    <table>
        <tr>
            <td>标准市场销售名：</td>
            <td>${pdBean.salesName}</td>
        </tr>
        <tr>
            <td>学名：</td>
            <td>${pdBean.scientificName}</td>
        </tr>
        <tr>
            <td>俗名：</td>
            <td>${pdBean.localName}</td>
        </tr>
        <tr>
            <td>产品特征：</td>
            <td>${pdBean.featureName}</td>
        </tr>
        <tr>
            <td>净重(kg/箱)：</td>
            <td>${pdBean.weightName}</td>
        </tr>
        <tr>
            <td>包装规格：</td>
            <td>${pdBean.normsName}</td>
        </tr>
    </table>
</div>

