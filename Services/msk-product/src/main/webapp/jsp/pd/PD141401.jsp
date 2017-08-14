<%--
    Title:卖家一览列表
    author:xhy
    createDate:2016-4-11
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="卖家一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/PD141401/search" method="post" id="pd141401FormId">
            <table class="accordion" id="PD141401_list_grid" showAddBtn="true" width="100%" >
                <thead>
                <tr>
                    <th coltype="text" width="16%" name="slCodeDis" filter="true">卖家编码</th>
                    <th coltype="text" width="16%" name="slShowName" filter="true">卖家名称</th>
                    <th coltype="select" width="16%" name="slMainClass">卖家主类型
                        <select>
                            <option value="0">生产型</option>
                            <option value="1">自产型</option>
                            <option value="2">代理型</option>
                            <option value="3">OEM型</option>
                        </select>
                    </th>
                    <th coltype="text" width="16%" name="slContact" filter="true">联系人姓名</th>
                    <th coltype="text" width="16%" name="slTel" filter="true">联系电话</th>
                    <th coltype="text" width="16%" name="cityName" filter="true">行政区划</th>
                    <th coltype="action" width="60px">产品列表
                        <msk:button buttonValue="产品列表" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141401.DETAIL"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD141401.js"></script>
