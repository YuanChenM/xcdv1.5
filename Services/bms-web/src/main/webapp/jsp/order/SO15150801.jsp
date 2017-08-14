<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/msk" %>
<div class="page-content list-page">
    <form action="<c:url value="/SO15150801/search/"/>" id="SO15150801Form" method="post">
        <input type="hidden" id="lgcsCode" name="lgcsCode" value="${productStock.lgcsCode}"/>
        <input type="hidden" id="slCode" name="slCode" value="${productStock.slCode}"/>
        <input type="hidden" id="salePlatform" name="salePlatform" value="${productStock.salePlatform}"/>
        <input type="hidden" name="supplyPlatform" value="${productStock.salePlatform}"/>
        <input type="hidden" id="pricecycleId" name="pricecycleId" value="${productStock.pricecycleId}"/>

        <table id="SO151508Grid">
            <thead>
            <tr>
                <th coltype="checkbox"></th>
                <c:if test="${isSupp}">
                    <th coltype="text" filter="true" name="supplierCode">供应商编码</th>
                    <th coltype="text" filter="true" name="supplierName">供应商名称</th>
                </c:if>
                <th coltype="text" filter="true" name="unit">产品单位</th>
                <th coltype="text" filter="true" name="pdCode">产品编码</th>
                <th coltype="text" filter="true" name="pdName">产品名称</th>
                <th coltype="date" edit="true" name="proDate" id="proDate">期望配送日</th>
                <th coltype="money" accuracy="0" edit="true" name="activeQty" maxlenth="10">下单数量</th>
                <th coltype="money" name="price">产品单价(元/箱)</th>
                <th coltype="number" name="priceCyclePeriod">价盘周期</th>
                <th coltype="text" name="orderLevelName">订单级别</th>
                <th coltype="text" name="lgcsName">销售区域
                    <%--<select name="districtCode" id="districtCode" style="width: 50px" >
                        <option value="" selected>请选择</option>
                        <c:forEach items="${lgcsAreaBeanList}" var="lgcsAreaBeanList">
                            <option value="${lgcsAreaBeanList.lgcsAreaCode}">${lgcsAreaBeanList.lgcsAreaName}</option>
                        </c:forEach>
                    </select>--%>
                </th>
                <th coltype="action"></th>
                <%--<th style="display: none"  name="normsOut">包装规格</th>
                <th style="display: none"  name="packingVolume">单箱体积</th>
                <th style="display: none"  name="netWeightOut">净重</th>--%>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
    <msk:button buttonType="button" buttonId="SO15150801.CONFIRM" buttonValue="确认"/>
</div>

<script src='<c:url value="/static/js/order/SO15150801.js" />'></script>