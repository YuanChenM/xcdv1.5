<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
    <msk2:codemaster codeType="HalfCodeType" viewType="json" />
<msk2:codemaster codeType="DeliveryStockStatus" viewType="json" />
<msk2:codemaster codeType="SourceFlg" viewType="json" />
<navigation:header title="发货入库通知单一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SC182101/search" method="post" >
        <table id="SC182101_Grid">
            <thead>
            <tr>
                <th coltype="text" name="deliveryStockId" filter="true" width="20px">发货入库通知单号</th>
                <th coltype="text" name="distMonth" id = "distMonth" filter="true">周期</th>
                <th coltype="text" name="lgcsName" filter="true">物流区</th>
                <th coltype="text" name="suppName" filter="true">卖家</th>
                <%--<th coltype="code" width="100px" filter="true" name="halfCode" code2name="HALFCODETYPE_JSON">半旬码</th>--%>
                <th coltype="text" name="manuName">生产商</th>
                <th coltype="code" name="sourceFlg" filter="true"  width="120px" code2name="SOURCEFLG_JSON">入库平台</th>
                <%--<th coltype="text" name="sourceFlg" filter="true">入库平台</th>--%>
                <th coltype="code" width="80px" filter="true" name="deliveryStockStatus" code2name="DELIVERYSTOCKSTATUS_JSON">状态</th>

                <%-- #1763 modify by likai on 2016/8/15 --%>
                <th coltype="datetime" name="deliveryReceiveStockTime" filter="true">实际到货时间</th>
                <%--#1763  modify by likai on 2016/8/15 --%>
                <th coltype="money" accuracy="0" name="sendPlanNum">计划发货箱数</th>
                <th coltype="money" accuracy="0"  name="sendActualNum">实际发货箱数</th>
                <th coltype="money" accuracy="0"  name="receiveNum">实际收货箱数</th>
                <th coltype="money" name="sendPlanQty">计划发货重量(KG)</th>
                <th coltype="money" name="sendActualQty">实际发货重量(KG)</th>
                <th coltype="money" name="recriveQty">实际收货重量(KG)</th>
                <th coltype="money" accuracy="0" name="differNum">差异箱数</th>
                <th coltype="money" name="differQty">差异重量(KG)</th>
                <%--<th coltype="text" name="deliveryWarehouseAddr" filter="true">发货仓库地点</th>--%>
                <%--<th coltype="text" name="deliveryResponName" filter="true">发货责任人</th>--%>
                <%--<th coltype="text" name="transportUnitName" filter="true">运输单位名称</th>--%>
                <%--<th coltype="text" name="transportUnitResponName" filter="true">运输单位负责人</th>--%>
                <%--<th coltype="text" name="stockAddr" filter="true">运抵仓库地址</th>--%>
                <%--<th coltype="text" name="stockResponName" filter="true">仓管负责人</th>--%>
                <th coltype="action" width="10px">
                    <msk:button  buttonValue="编辑" buttonId="SC182101.edit" buttonType="hidden" coltype="edit" class="h-button"/>
                    <%--<msk:button buttonValue="确认中标" buttonId="SP171133.edit" buttonType="hidden" coltype="edit" class="h-button" useable="can_restore"/>--%>
                </th>
            </tr>
            </thead>

            <tbody>
            </tbody>
        </table>

    </form>
    <div id="userType" style="display:none"><c:out value="${loginUser.userType}"></c:out></div>
    <div id="crtId" style="display:none"><c:out value="${loginUser.emplId}"></c:out></div>

    <input type="hidden" id="printUrl" value="/excel/async/print/start">
    <msk:button buttonValue="发货入库通知单一览数据导出" buttonId="SC182101.EXPORTORDER" buttonType="button" align="left"/>
    <c:if test="${userType == 2}">
        <msk:button buttonValue="新增" buttonId="SC182101.ADD" buttonType="button" url="${ctx}/SC182102/init/false" />
    </c:if>

</div>
<script type="text/javascript" src="${ctx}/static/ds/js/SC182101.js"></script>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>
