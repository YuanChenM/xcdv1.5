<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="销售期分销买家平台下单方式管控" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BY121321/search/${buyerId}" id="BY121321Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>查询条件</h3>
            <table width="100%">
                <tr>
                    <td align="right" width="15%" >下单开始日期 : </td>
                    <td width="8%">
                        <input type="text" id="startTime" name="startTime" readonly="readonly"/>
                    </td>

                    <td align="right" width="8%" >下单结束日期 : </td>
                    <td width="8%">
                        <input type="text" id="endTime" name="endTime" readonly="readonly"/>
                    </td>
                    <td width="15%"></td>
                    <td><msk:button buttonType="button" buttonId="BY121321.SEARCH" buttonValue="查询"/></td>
                </tr>
            </table>
        </div>

        <div>
            <table width="100%" id="BY121321_list_grid">
                <thead>
                <tr>
                    <th coltype="text" name="orderType" align="center">订单下单方式</th>
                    <th coltype="text" name="orderMicroMall" align="center">微商城下单</th>
                    <th coltype="text" name="orderPC" align="center">PC下单</th>
                    <th coltype="text" name="buyerApp" align="center">买手APP</th>
                    <th coltype="text" name="orderWeChat" align="center">微信下单</th>
                    <th coltype="text" name="orderTel" align="center">电话下单</th>
                    <th coltype="text" name="orderQq" align="center">QQ下单</th>
                    <th coltype="text" name="totalOrder" align="center">合计</th>
                </tr>
                </thead>

                <tbody>

                </tbody>

            </table>
        </div>
    </form>
</div>


<script type="text/javascript" src="${ctx}/static/buyers/js/BY121321.js"></script>