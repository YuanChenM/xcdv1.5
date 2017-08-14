<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="供应商显示项目设置" backTitleArray="" backUrlArray=""></navigation:header>
<style>
    #checkbox-lgcsCode-button {
        width: 200px;
        max-width: 200px;
        min-width: 80px;;
    }
</style>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>供应商显示项目设置</label>
        </h3>

        <form action="${ctx}/SP171196/search" id="SP171196Form" method="post" onsubmit="return SP171196.formSearch();">
            <input type="hidden" id="slCode" name="slCode"/>

            <div>
                <table id="SP171196_Grid">
                    <thead>
                    <tr>
                        <th coltype="sno" width="10%">序号</th>
                        <th coltype="select" filter="true" name="lgcsName">区域
                            <select name="lgcsCode" id="districtName">
                                <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                                    <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                                </c:forEach>
                            </select>
                        </th>
                        <th coltype="text" filter="true" name="slName">供应商</th>
                        <th coltype="text" filter="false" name="isSupply">
                            是否显示其他供应商名称
                        </th>
                        <th coltype="text" filter="false" name="isNum">是否显示其他供应商申报数量
                        </th>
                        <th coltype="text" filter="false" name="isPrice">是否显示其他供应商申报价格
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    </tbody>
                </table>
            </div>
            <div>
                <table class="dataTable no-footer">
                    <tr>
                        <td align="center"><input type="checkbox" id="isSupply" value="SUPPLY" name="viewKeys"/>供应商名称
                            <input type="checkbox" id="isNum" value="NUM" name="viewKeys"/>申报数量
                            <input type="checkbox" id="isPrice" value="PRICE" name="viewKeys"/>申报价格
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><input type="radio" value="1" checked name="viewFlg"/>显示<input
                                type="radio" value="0" name="viewFlg"/>不显示
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <msk:button buttonType="button" buttonId="SP171196.SAVE" buttonValue="保存"/>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>供应商价格申报条件设置</label>
        </h3>
        <table class="dataTable no-footer">
            <tr>
                <th style="width: 10%">申报条件：</th>
                <td><select id="priceAble">
                    <option value="1">无条件</option>
                    <option value="0" <c:if test="${viewFlg!=1}">selected </c:if>>有库存</option>
                </select>
                </td>
                <td  style="width: 10%">
                    <msk:button buttonType="button" buttonId="SP171196.ISDECLARE" buttonValue="变更"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>供应商数量申报日期设置</label>
        </h3>
        <table class="dataTable no-footer">
            <tr>
                <th style="width: 10%">申报日期：</th>
                <td>
                    <select id="startDeclare">
                        <c:forEach items="${timeList}" var="day" varStatus="status">
                            <option value="${day}" <c:if test="${declareBean.startTime == day}">selected </c:if>>${day}日</option>
                        </c:forEach>
                    </select>~
                    <select id="endDeclare">
                        <c:forEach items="${timeList}" var="day" varStatus="status">
                            <option value="${day}" <c:if test="${declareBean.endTime == day}">selected </c:if>>${day}日</option>
                        </c:forEach>
                    </select>
                </td>
                <td style="width: 10%">
                    <msk:button buttonType="button" buttonId="SP171196.DECLARESAVE" buttonValue="变更"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>有效价盘周期设置</label>
        </h3>
        <table class="dataTable no-footer">
            <tr>
                <th style="width: 10%">有效价盘周期：</th>
                <td>
                    <select id="priceUpdate">
                        <c:forEach items="${priceList}" var="price" varStatus="status">
                            <option value="${price.pricePeriod}" startDate="${price.startDateStr}"
                                    <c:if test="${price.pricePeriod == pricePeriod}">selected="selected" </c:if> endDate="${price.endDateStr}">${price.pricePeriod}</option>
                        </c:forEach>
                    </select>
                </td>
                <th>生效时间：</th>
                <td>
                    <input id="startDate" value="${startDate}" style="width:200px"/>~
                    <input id="endDate" value="${endDate}" style="width:200px"/>
                </td>
                <td style="width: 10%">
                    <msk:button buttonType="button" buttonId="SP171196.PRICESAVE" buttonValue="设置"/></td>
            </tr>
        </table>
    </div>
</div>

<script src="${ctx}/static/sp/js/SP171196.js"></script>