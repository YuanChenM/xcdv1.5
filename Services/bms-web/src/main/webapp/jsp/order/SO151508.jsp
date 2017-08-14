<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ taglib prefix="msk" uri="/msk" %>
<msk:codemaster codeType="OrderSource" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="OrderType" viewType="json"  modelName="ORDER"/>
<msk:codemaster codeType="OrderStatus" viewType="json"  modelName="ORDER"/>
<msk:codemaster codeType="OrderBuyerType" viewType="json" modelName="ORDER"/>
<msk:codemaster codeType="SalePlatform" viewType="json"  modelName="ORDER" />
<msk:codemaster codeType="PaymentType" viewType="json"  modelName="ORDER" />
<msk:codemaster codeType="DeliveryType" viewType="json"  modelName="ORDER" />
<msk:codemaster codeType="ReceivePeriodType" viewType="json"  modelName="BUYER" />
<navigation:header title="订单新增" backTitleArray="订单列表" backUrlArray="../SO151501/init/"></navigation:header>
<div class="page-content list-page">
    <form commandName="SO151508Bean" id="SO151508Form" method="post" modelAttribute="SO151508Bean" action="<c:url value="/SO151508/save/"/>">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>订单基本信息</label>
            </h3>
            <div>
                <table width="100%">
                    <tr>
                        <td width="100px" style="text-align: right"><label style="color: red">*</label>卖家编号</td>
                        <td width="12.5%">
                            <input type="text" value="0000000" id="sellerCode"  name="order.sellerCode" maxlength="20"
                                 nativeinput="true" textname="卖家编号"/>
                        </td>
                        <td width="100px" style="text-align: right"><label style="color: red">*</label>卖家名称</td>
                        <td width="12.5%">
                            <input type="text" value="神农客实业有限公司" id="sellerName" name="order.sellerName" maxlength="50" nativeinput="true" textname="卖家名称"/>
                        </td>
                        <td width="100px" style="text-align: right" aria-checked="true"><label style="color: red">*</label>订单区域</td>
                        <td colspan="3">
                                <select name="order.districtCode" id="districtCode" nativeinput="true" textname="订单区域">
                                    <option value="" selected>--请选择--</option>
                                    <c:forEach items="${logisticsAreaList}" var="logisticsAreaList">
                                                <option value="${logisticsAreaList.lgcsAreaCode}">${logisticsAreaList.lgcsAreaName}</option>
                                    </c:forEach>
                                </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><label style="color: red">*</label>买家编号</td>
                        <td>
                            <input id="buyerCode" name="order.buyerCode" maxlength="200"
                                   nativeinput="true" textname="买家编号"/>
                            <%--onkeyup="value=value.replace(/[\W]/g,'')"
                                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"--%>
                        </td>
                        <td style="text-align: right"><label style="color: red">*</label>买家名称</td>
                        <td>
                            <input id="buyerName" name="order.buyerName" maxlength="50" nativeinput="true" textname="买家名称"/>
                        </td>
                        <td style="text-align: right"><label style="color: red">*</label>买家类型</td>
                        <td>
                          <msk:codemaster codeType="OrderBuyerType" modelName="ORDER" id="buyerType" name="order.buyerType" viewType="select" nativeinput="true" textname="买家类型"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><label style="color: red">*</label>订单来源</td>
                        <td>
                                <%--<msk:codemaster codeType="OrderSource" modelName="ORDER" id="orderSource" name="order.orderSource" viewType="select"/>--%>
                            <select id="orderSource" name="order.orderSource" nativeinput="true" textname="订单来源">
                                <option value="">--请选择--</option>
                                <option value="6">电话</option>
                                <option value="7">QQ</option>
                                <option value="8">微信</option>
                                <option value="99">其他</option>
                            </select>
                        </td>
                        <td style="text-align: right"><label style="color: red">*</label>订单类型</td>
                        <td>
                                <%--<msk:codemaster codeType="OrderType" modelName="ORDER" id="orderType" name="order.orderType" viewType="select"/>--%>
                            <select id="orderType" name="order.orderType" nativeinput="true" textname="订单类型">
                                <option value="">--请选择--</option>
                                <option value="1">标准分销订单</option>
                                <option value="2">第三方订单</option>
                                <option value="4">买手囤货订单</option>
                                <option value="7">第三方买手囤货订单</option>
                            </select>
                        </td>

                        <td style="text-align: right">销售平台</td>
                        <td>
                            <msk:codemaster codeType="SalePlatform" modelName="ORDER" id="salePlatForm" name="order.salePlatform" viewType="select" nativeinput="true" textname="订单区域"/>
                        </td>
                    </tr>
                    <%--<tr>
                        <td ></td>
                        <td>
                            <msk:codemaster codeType="SalePlatform" modelName="ORDER" id="salePlatForm" name="order.salePlatform" viewType="select" nativeinput="true" textname="订单区域"/>
                        </td>
                    </tr>--%>
                    <tr>
                        <td style="text-align: right"><label style="color: red">*</label>付款方式</td>
                        <td>
                            <msk:codemaster codeType="PaymentType" modelName="ORDER" id="paymentType" name="order.paymentType" viewType="select" nativeinput="true" textname="付款方式"/>
                        </td>
                        <td style="text-align: right">订单总金额</td>
                        <td>
                            <input id="orderAmount" readonly="true" name="order.orderAmount" nativeinput="true" textname="订单总金额" style="text-align:right "></input>
                        </td>
                        <td style="text-align: right"></td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>配送信息</label>
            </h3>
            <div>
                <table width="100%">
                    <tr>
                        <td style="text-align: right" width="150px"><label style="color: red">*</label>收货人名称</td>
                        <td><input id="receiverName" name="orderReceiveDemand.receiverName" maxlength="20"
                                    nativeinput="true" textname="收货人名称"/> </td>
                        <td style="text-align: right" width="100px"><label style="color: red">*</label>收货人电话</td>
                        <td><input id="receiverTel" name="orderReceiveDemand.receiverTel" maxlength="20" onKeyUp="value=value.replace(/\D+/g,'')" nativeinput="true" textname="收货人电话"/></td>
                        <td style="text-align: right" width="100px">收货人QQ</td>
                        <td ><input id="receiverQq" name="orderReceiveDemand.receiverQq" maxlength="20" onKeyUp="value=value.replace(/\D+/g,'')"/></td>
                        <td style="text-align: right" width="100px">收货人微信</td>
                        <td><input id="receiverWeixin" name="orderReceiveDemand.receiverWeixin" maxlength="50"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><label style="color: red">*</label>收货人地址</td>
                        <td colspan="7">
                            <input id="receiverProvince" name="orderReceiveDemand.receiverProvince" maxlength="10"
                                    nativeinput="true" textname="省"/>省
                            <input id="receiverCity" name="orderReceiveDemand.receiverCity" maxlength="10"
                                    nativeinput="true" textname="市"/>市
                            <input id="receiverDistrict" name="orderReceiveDemand.receiverDistrict" maxlength="20"
                                   nativeinput="true" textname="区"/>区
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><label style="color: red">*</label>收货人详细地址</td>
                        <td colspan="7">
                            <input id="receiverAddr" name="orderReceiveDemand.receiverAddr" size="100" maxlength="100" nativeinput="true" textname="收货人详细地址"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">习惯正常收货时间段</td>
                        <td hidden>
                            <%--<input id="receiveTime" name="orderReceiveDemand.receiveTime" size="100" maxlength="20"/>--%>
                        <msk:codemaster codeType="ReceivePeriodType" modelName="BUYER" id="receiveTime" name="orderReceiveDemand.receiveTime" viewType="select"/>
                        </td>
                        <td colspan="6">
                            <input type="hidden" name="newReceiveTime" id="newReceiveTime" value="">
                            <select id="checkbox-newReceiveTime" style="width: 620px">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">习惯收货最早时间要求</td>
                        <td colspan="7">
                            <input  id="receiveEarliest" name="receiveEarliest" type="hidden" value=""/>
                            <select id="checkbox-receiveEarliest" style="width: 620px">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">习惯收货最晚时间要求</td>
                        <td colspan="7">
                            <input id="receiveLast" name="receiveLast" type="hidden" value=""/>
                            <select id="checkbox-receiveLast" style="width: 620px">
                            </select>
                        </td>
                    </tr>
                    <%--<tr>
                        <td style="text-align: right">预计发货时间</td>
                        <td><input  id="forecastSendTime" name="forecastSendTime" readonly="readonly"/></td>
                        <td style="text-align: right">预计到货时间</td>
                        <td><input  id="forecastReceiveTime" name="forecastReceiveTime" readonly="readonly"/></td>
                        <td style="text-align: right">收货等待时间</td>
                        <td><input id="receiveWaitTime" name="orderReceiveDemand.receiveWaitTime" maxlength="10" readonly="readonly"/></td>
                        <td style="text-align: right">提前通知时间</td>
                        <td><input id="advanceNoticeTime" name="orderReceiveDemand.advanceNoticeTime" maxlength="2"/></td>
                    </tr>--%>
                    <tr>
                        <td style="text-align: right">装卸要求</td>
                        <td colspan="7">
                            <input id="unloadReq" name="orderReceiveDemand.unloadReq" size="100" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">包装要求</td>
                        <td colspan="7">
                            <input id="packingReq" name="orderReceiveDemand.packingReq" size="100" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">距离门店最近停车距离(米)</td>
                        <td colspan="7">
                            <input id="parkingDistance" name="orderReceiveDemand.parkingDistance" size="100" maxlength="11" onKeyUp="value=value.replace(/\D+/g,'')"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">其它配送服务要求</td>
                        <td colspan="7">
                            <input id="otherDeliveryReq" name="orderReceiveDemand.otherDeliveryReq" size="100" maxlength="200"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">本次配送服务要求</td>
                        <td colspan="7">
                            <input id="thisDeliveryReq" name="orderReceiveDemand.thisDeliveryReq" size="100" maxlength="200"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="group-accordion" collapsible="true" active="true" id="accordionProductData" >
            <h3>
                <label>下单产品</label>
            </h3>
            <%--<div style="min-height: 150px">--%>
                <table width="100%" class="dataTable no-footer">
                    <thead>
                    <tr>
                        <th>产品编码</th>
                        <th>产品名称</th>
                        <th>产品单价(元/箱)</th>
                        <th coltype="money" accuracy="0">下单数量</th>
                        <th>期望配送日</th>
                    </tr>
                    </thead>
                    <tbody id="productData"></tbody>
                </table>
            <%--</div>--%>
        </div>
    </form>
    <div>
        <msk:button buttonType="button" buttonId="SO151508.SEARCH" buttonValue="选择"/>
        <msk:button buttonType="button" buttonId="SO151508.SAVE" buttonValue="保存"/>
    </div>
</div>
<script src='<c:url value="/static/js/order/SO151508.js" />'></script>