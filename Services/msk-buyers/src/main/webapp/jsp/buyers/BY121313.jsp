<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/7/5
  Time: 上午10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家信息总控画面" backTitleArray="买家列表" backUrlArray="${ctx}/BY121303/init/"></navigation:header>
<div class="page-content detail-page">
    <input type="hidden" id="buyerId" value="${buyerId}"/>
    <input type="hidden" id="marketingsStatus" value="${marketingsStatus}"/>
    <input type="hidden" id="buyerType" value="${buyerType}"/>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>买家基本信息管控</h3>
        <div>
            <div style="height:90px;">
                <div id="byBasicInfo">
                    <msk:button url="${ctx}/BY121304/init/${buyerId}" buttonValue="" buttonId="BY121313.BUYER.BASIC" buttonType="button"/>
                </div>
            </div>
            <div style="margin-top: 20px;height:90px;">
                <div id="byCodeControl" style="float: left; display: none">
                    <msk:button url="${ctx}/BY121311/init/${buyerId}" buttonValue="" buttonId="BY121313.BUYERCODE.DETAIL" buttonType="button"/>
                </div>
                <div id="byPoolAttribution" style="float: left;">
                    <msk:button url="${ctx}/BY121310/init/${buyerId}" buttonValue="" buttonId="BY121313.BUYER.POOL" buttonType="button"/>
                </div>
                <div id="marketingStatus" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121315/init/${buyerId}" buttonValue="" buttonId="BY121313.MARKETING.STATUS" buttonType="button"/>
                </div>
                <div id="marketingTool" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121319/init/${buyerId}" buttonValue="" buttonId="BY121313.MARKETING.TOOL" buttonType="button"/>
                </div>
                <div id="orderMethod" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121321/init/${buyerId}" buttonValue="" buttonId="BY121313.ORDER.METHOD" buttonType="button"/>
                </div>
            </div>
            <div style="margin-top: 20px;height:90px;">
                <div id="shoppingAccount" style="float: left;">
                    <msk:button url="${ctx}/BY121316/init/${buyerId}" buttonValue="" buttonId="BY121313.SHOPPING.MALL" buttonType="button"/>
                </div>
                <div id="microAccount" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121317/init/${buyerId}" buttonValue="" buttonId="BY121313.MICRO.MALL" buttonType="button"/>
                </div>
                <div id="memberCard" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121320/init/${buyerId}" buttonValue="" buttonId="BY121313.MEMBER.CARD" buttonType="button"/>
                </div>
            </div>
        </div>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>买家营销期管控</h3>
        <div>
            <div style="height:90px;">
                <div id="marketingHousekeeper" style="float: left;">
                    <msk:button url="${ctx}/BY121312/init/${buyerId}" buttonValue="" buttonId="BY121313.MARKETINGHOUSE.DETAIL" buttonType="button"/>
                </div>
            </div>
            <div style="width: 100%;height:90px;display: none">
                <div id="orderSummary" style="float: left;">
                    <msk:button url="${ctx}/BY121308/init/${buyerId}" buttonValue="" buttonId="BY121313.ORDER" buttonType="button"/>
                </div>
                <div id="purPdCatalog" style="float: left;margin-left:20px;">
                    <msk:button url="${ctx}/BY121309/init/${buyerId}" buttonValue="" buttonId="BY121313.PDPOOL.CATALOG" buttonType="button"/>
                </div>
            </div>
        </div>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>买家销售期管控</h3>
        <div>
            <div style="width: 100%;height:90px;">
                <div id="saleForecast" style="float: left;display: none">
                    <msk:button url="${ctx}/BY121308/init/${buyerId}" buttonValue="" buttonId="BY121313.FORECAST" buttonType="button"/>
                </div>
                <div id="saleHousekeeper" style="float: left;">
                    <msk:button url="${ctx}/BY121312/init/${buyerId}" buttonValue="" buttonId="BY121313.SALE.DETAIL" buttonType="button"/>
                </div>
                <div id="deliveryInfo" style="float: left;margin-left: 20px;">
                    <msk:button url="${ctx}/BY121314/init/${buyerId}" buttonValue="" buttonId="BY121313.DELIVERY.DETAIL" buttonType="button"/>
                </div>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${buyerType eq '01'}">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>买家报表中心</h3>
                <div>
                    <div style="width: 100%;height:50px;">
                        <div id="buyerInfoReport" style="float: left;">
                            <msk:button url="" buttonValue="" buttonId="BY121313.BUYER.INFO.REPORT" buttonType="button"/>
                        </div>
                        <div id="salePeriodBasicReport" style="float: left;margin-left: 20px;">
                            <msk:button url="${ctx}/BY121325/init/${buyerId}" buttonValue="" buttonId="BY121313.SALE.PERIOD.REPORT" buttonType="button"/>
                        </div>
                    </div>
                    <div style="width: 100%;height:50px;margin-top: 20px;">
                        <div id="frozenProductManagerReport" style="float: left;">
                            <msk:button url="" buttonValue="" buttonId="BY121313.FROZEN.PRODUCT.REPORT" buttonType="button"/>
                        </div>
                        <div id="reportManage" style="float: left;margin-left: 20px;">
                            <msk:button url="${ctx}/BY121318/init/${buyerId}" buttonValue="" buttonId="BY121313.REPORT.MANAGE" buttonType="button"/>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="printUrl" value="/excel/async/print/start">
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121313.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>


