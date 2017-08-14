<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家报表中心" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content detail-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>报表中心</h3>
        <div>
            <div style="height:90px;">
                <div id="buyerListReport">
                    <msk:button url="" buttonValue="买家列表信息导出" buttonId="BY121414.BUYER.LIST.REPORT" buttonType="button"/>
                </div>
            </div>
           <%-- <input type="hidden" id="printUrl" value="/excel/async/print/start/">--%>
            <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121414.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>


