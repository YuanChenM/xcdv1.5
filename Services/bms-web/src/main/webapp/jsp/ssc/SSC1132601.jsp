<%--
    Title:生产商计划管理
    author:liu_yan2
    createDate:2016-08-08
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<style type="text/css">
    a:link {color: blue;}
    a:visited {color: purple;}
    .num_input {width:90px;text-align: right;}
</style>
<navigation:header title="生产商计划详细" backTitleArray="生产商计划管理" backUrlArray="../SSC11326/init/"></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            生产商计划管理
        </h3>
        <table style="width: 100%;">
            <tr>
                <td width="25%">合同编号：<a href="javascript:SSC1132601.goContractDetailPage()">${contractInfo.contractCode}</a>
                    <input type="hidden" value="${contractInfo.contractCode}" id="contractCode"/>
                    <input type="hidden" value="${contractInfo.contractId}" id="contractId"/>
                </td>
                <td>合同名称：${contractInfo.contractName}</td>
            </tr>
        </table>
    </div>

    <!--生产期产品管控-->
    <c:if test="${not empty produceMap}">
        <br/>
        <div class="group-accordion" collapsible="true" active="true">
            <h3 style="position: relative">
                生产期产品管控
            </h3>
            <c:if test="${not empty produceDates}">
            <div width="100%" style="padding: 0px;">
                <table width="100%" class="dataTable no-footer" id="produceTable">
                    <thead>
                    <tr>
                        <th align="center" coltype="text" width="15%">产品信息</th>
                        <th align="center" coltype="text">&nbsp;</th>
                        <c:forEach items="${produceDates}" var="item">
                            <th align="center" coltype="text" edit="true">${item}</th>
                        </c:forEach>
                        <th align="center" coltype="text">合计</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td rowspan="2" align="center">日生产量合计</td>
                        <td align="center">计划（KG）</td>
                        <c:forEach items="${produceTotal.totalByDateList}" var="item">
                            <td align="center"><fmt:formatNumber value="${item.totalPlanNum}" pattern="#,##0.00"/></td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td align="center">实际（KG）</td>
                        <c:forEach items="${produceTotal.totalByDateList}" var="item">
                            <td align="center"><fmt:formatNumber value="${item.totalRealNum}" pattern="#,##0.00"/></td>
                        </c:forEach>
                    </tr>
                    <c:forEach var="map" items="${produceMap}" begin="0" end="${fn:length(produceMap)}" varStatus="status">
                        <c:set var="detailId" value="${map.key.split('_')[0]}"/>
                        <c:set var="pdName" value="${map.key.split('_')[1]}"/>
                        <c:set var="totalBean" value="${produceTotal.totalByNameList[pdName]}"/>
                    <c:if test="${status.index%2 == 0}"><tr class="even"></c:if>
                    <c:if test="${status.index%2 != 0}"><tr class="odd"></c:if>
                        <td rowspan="2" align="center">${pdName}<td align="center">计划（KG）</td>
                        <c:forEach items="${map.value}" var="ssc11326RsBean">
                            <td align="center">
                                <c:if test="${ssc11326RsBean.readonly}">
                                    <fmt:formatNumber value="${ssc11326RsBean.planNum == 0?'': ssc11326RsBean.planNum}" pattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.planNum eq 0}">
                                    <input type="text" value="" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="planNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.planNum ne 0}">
                                    <input type="text" value="<fmt:formatNumber value='${ssc11326RsBean.planNum}' pattern='#,##0.00'/>" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="planNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                            </td>
                        </c:forEach>
                        <td align="center"><fmt:formatNumber value="${totalBean.totalPlanNum}" pattern="#,##0.00"/></td>
                    </tr>
                    <c:if test="${status.index%2 == 0}"><tr class="even"></c:if>
                    <c:if test="${status.index%2 != 0}"><tr class="odd"></c:if>
                        <td align="center">实际（KG）</td>
                        <c:forEach items="${map.value}" var="ssc11326RsBean">
                            <td align="center">
                                <c:if test="${ssc11326RsBean.readonly}">
                                    <fmt:formatNumber value="${ssc11326RsBean.realNum eq 0?'': ssc11326RsBean.realNum}" pattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.realNum eq 0}">
                                    <input type="text" value="" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="realNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.realNum ne 0}">
                                    <input type="text" value="<fmt:formatNumber value='${ssc11326RsBean.realNum}' pattern='#,##0.00'/>" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="realNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                            </td>
                        </c:forEach>
                        <td align="center"><fmt:formatNumber value="${totalBean.totalRealNum}" pattern="#,##0.00"/></td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="${fn:length(produceDates)+3}"><msk:button buttonType="button" buttonId="SSC1132601.SAVEPRODUCE" buttonValue="保存"/></td>
                    </tr>
                    <tbody>
                </table>
                </div>
            </c:if>
        </div>
    </c:if>

    <!--待运期产品管控-->
    <c:if test="${not empty readyGoMap}">
        <br/>
        <div class="group-accordion" collapsible="true" active="true">
            <h3 style="position: relative">
                待运期产品管控
            </h3>
            <c:if test="${not empty produceDates}">
            <div width="100%" style="padding: 0px;">
                <table width="100%" class="dataTable no-footer" id="readyGoTable">
                    <thead>
                    <tr>
                        <th align="center" coltype="text" width="15%">产品信息</th>
                        <th align="center" coltype="text">&nbsp;</th>
                        <c:forEach items="${produceDates}" var="item">
                            <th align="center" coltype="text" edit="true">${item}</th>
                        </c:forEach>
                        <th align="center" coltype="text">合计</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td rowspan="2" align="center">日生产量合计</td>
                        <td align="center">计划（KG）</td>
                        <c:forEach items="${readyGoTotal.totalByDateList}" var="item">
                            <td align="center"><fmt:formatNumber value="${item.totalPlanNum}" pattern="#,##0.00"/></td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td align="center">实际（KG）</td>
                        <c:forEach items="${readyGoTotal.totalByDateList}" var="item">
                            <td align="center"><fmt:formatNumber value="${item.totalRealNum}" pattern="#,##0.00"/></td>
                        </c:forEach>
                    </tr>
                    <c:forEach var="map" items="${readyGoMap}" begin="0" end="${fn:length(readyGoMap)}" varStatus="status">
                        <c:set var="detailId" value="${map.key.split('_')[0]}"/>
                        <c:set var="pdName" value="${map.key.split('_')[1]}"/>
                        <c:set var="totalBean" value="${readyGoTotal.totalByNameList[pdName]}"/>
                    <c:if test="${status.index%2 == 0}"><tr class="even"></c:if>
                    <c:if test="${status.index%2 != 0}"><tr class="odd"></c:if>
                        <td rowspan="2" align="center">${pdName}<td align="center">计划（KG）</td>
                        <c:forEach items="${map.value}" var="ssc11326RsBean">
                            <td align="center">
                                <c:if test="${ssc11326RsBean.readonly}">
                                    <fmt:formatNumber value="${ssc11326RsBean.planNum == 0?'': ssc11326RsBean.planNum}" pattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.planNum eq 0}">
                                    <input type="text" value="" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="planNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.planNum ne 0}">
                                    <input type="text" value="<fmt:formatNumber value='${ssc11326RsBean.planNum}' pattern='#,##0.00'/>" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="planNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                            </td>
                        </c:forEach>
                        <td align="center"><fmt:formatNumber value="${totalBean.totalPlanNum}" pattern="#,##0.00"/></td>
                    </tr>
                    <c:if test="${status.index%2 == 0}"><tr class="even"></c:if>
                    <c:if test="${status.index%2 != 0}"><tr class="odd"></c:if>
                        <td align="center">实际（KG）</td>
                        <c:forEach items="${map.value}" var="ssc11326RsBean">
                            <td align="center">
                                <c:if test="${ssc11326RsBean.readonly}">
                                    <fmt:formatNumber value="${ssc11326RsBean.realNum eq 0?'': ssc11326RsBean.realNum}" pattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.realNum eq 0}">
                                    <input type="text" value="" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="realNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                                <c:if test="${!ssc11326RsBean.readonly && ssc11326RsBean.realNum ne 0}">
                                    <input type="text" value="<fmt:formatNumber value='${ssc11326RsBean.realNum}' pattern='#,##0.00'/>" produce="${ssc11326RsBean.produceDateStr}/${detailId}/${ssc11326RsBean.id}/${ssc11326RsBean.ver}" name="realNum" maxlength="14" onblur="SSC1132601.bindInputBlur(this)"/>
                                </c:if>
                            </td>
                        </c:forEach>
                        <td align="center"><fmt:formatNumber value="${totalBean.totalRealNum}" pattern="#,##0.00"/></td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="${fn:length(produceDates)+3}"><msk:button buttonType="button" buttonId="SSC1132601.SAVEREADYGO" buttonValue="保存"/></td>
                    </tr>
                    <tbody>
                </table>
                </div>
            </c:if>
        </div>
    </c:if>
    <!--运输期产品管控-->
    <c:if test="${not empty deliveryControl}">
    <br/>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            运输期产品管控
        </h3>
        <table style="width: 100%;" class="dataTable no-footer" id="deliveryTable">
            <thead>
            <tr>
                <th align="center" coltype="text">交货期计划车次</th>
                <th align="center" coltype="text">计划到货日期</th>
                <th align="center" coltype="text">实际到货日期</th>
                <th align="center" coltype="text">计划确定车辆时间</th>
                <th align="center" coltype="text">实际确定车辆时间</th>
                <th align="center" coltype="text">计划装车结束时间</th>
                <th align="center" coltype="text">实际装车结束时间</th>
                <th align="center" coltype="text">计划发货时间</th>
                <th align="center" coltype="text">实际发货时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${deliveryControl}" var="item">
                <tr>
                    <td align="center">第 ${item.deliveryBatch} 车
                        <input type="hidden" value="${item.deliveryBatch}" name="deliveryBatch">
                        <input type="hidden" value="${item.id}" name="id">
                        <input type="hidden" value="${item.ver}" name="ver">
                    </td>
                    <td align="center"><input type="text" value="${item.planArriveDateStr}" readonly name="planArriveDateStr"/></td>
                    <td align="center"><input type="text" value="${item.realArriveDateStr}" readonly name="realArriveDateStr"/></td>
                    <td align="center"><input type="text" value="${item.planChooseVehicleDateStr}" readonly name="planChooseVehicleDateStr"/></td>
                    <td align="center"><input type="text" value="${item.realChooseVehicleDateStr}" readonly name="realChooseVehicleDateStr"/></td>
                    <td align="center"><input type="text" value="${item.planIntoVehicleDateStr}" readonly name="planIntoVehicleDateStr"/></td>
                    <td align="center"><input type="text" value="${item.realIntoVehicleDateStr}" readonly name="realIntoVehicleDateStr"/></td>
                    <td align="center"><input type="text" value="${item.planOffDateStr}" readonly name="planOffDateStr"/></td>
                    <td align="center"><input type="text" value="${item.realOffDateStr}" readonly name="realOffDateStr"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9"><msk:button buttonType="button" buttonId="SSC1132601.SAVEDELIVERY" buttonValue="保存"/></td>
            </tr>
            </tbody>

        </table>
    </div>
    </c:if>
    <!--入库产品管控-->
    <c:if test="${not empty stockProducts}">
        <br/>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                入库产品管控
            </h3>
            <table style="width: 100%;" class="dataTable no-footer">
                <thead>
                <tr>
                    <th align="center" coltype="text">交货期计划车次</th>
                    <th align="center" coltype="text">产品信息</th>
                    <th align="center" coltype="text">发货订单箱数</th>
                    <th align="center" coltype="text">入库箱数</th>
                    <th align="center" coltype="text">差异箱数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="map" items="${stockProducts}" begin="0" end="${fn:length(stockProducts)}" varStatus="parentStatus">
                <c:forEach items="${map.value}" var="stockProduct" varStatus="status">
                <c:if test="${parentStatus.index % 2 == 0}"><tr class="odd"></c:if>
                <c:if test="${parentStatus.index % 2 != 0}"><tr class="even"></c:if>
                    <c:if test="${status.index == 0}">
                        <td rowspan="${fn:length(map.value)}" align="center">第 ${map.key} 车</td>
                    </c:if>
                    <td align="center">${stockProduct.pdName}</td>
                    <td align="center">${stockProduct.productPlanBox}</td>
                    <td align="center">${stockProduct.productRecvBox}</td>
                    <td align="center">${stockProduct.differBoxes}</td>
                </tr>
                </c:forEach>
                </c:forEach>
                <tbody>
            </table>
        </div>
    </c:if>
</div>
<script src="<c:url value="/static/js/ssc/SSC1132601.js"/>"></script>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>
