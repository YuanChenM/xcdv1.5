<%--
    Title:买家订单汇总列表
    author:yuan_zhifei
    createDate:2016-07-05
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家订单汇总" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <input type="hidden" value="${buyerId}">
    <form action="${ctx}/BY121308/search/${buyerId}" method="post">
            <table width="100%" id="BY121308_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" rowspan="2">序号</th>
                    <th colspan="5" width="50%">产品信息</th>
                    <th colspan="4">订单信息</th>
                    <th></th>
                </tr>
                <tr>
                    <th coltype="text" width="25%" name="pdName">产品</th>
                    <th coltype="text" name="weightName">单箱净重(kg)</th>
                    <th coltype="text" name="normsName">包装规格</th>
                    <th coltype="text" name="gradeName">产品等级</th>
                    <th coltype="text" name="pdCode">产品编码</th>
                    <th coltype="text" name="orderTime">首次下单日期</th>
                    <th coltype="text" name="orderCount">订单(笔)</th>
                    <th coltype="text" name="receiveQty">订单箱数</th>
                    <th coltype="text" name="weight">订单吨数</th>
                    <th coltype="sno">排名</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121308.js"></script>


