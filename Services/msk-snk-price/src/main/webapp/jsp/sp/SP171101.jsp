<%--
    Title:供应商需求发布
    author:zc
    createDate:2016-04-26
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="navigation" uri="/msk-navigation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<navigation:header title="供应商需求发布" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SP171101/search" id="SP171101Form" method="post">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="left">*申报周期</td>
                    <td width="35px"></td>
                    <td >
                        <select name="filterMap['cycle']" id="cycle">
                            <c:forEach items="${dateList}" var="date" varStatus="index">
                                <%--<c:choose>
                                    <c:when test="${date.nowYearMonth eq date.demandYearMonth}">
                                        <option value="${date.demandYearMonth}" limitWriteDate="${date.limitWriteDate}" selected >${date.demandYearMonthShow}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${date.demandYearMonth}" limitWriteDate="${date.limitWriteDate}">${date.demandYearMonthShow}</option>
                                    </c:otherwise>
                                </c:choose>--%>
                                <option value="${date.demandYearMonth}" limitWriteDate="${date.limitWriteDate}">${date.demandYearMonthShow}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td coltype="text" align="left" width="10%">填报时间</td>
                    <%--<td width="8%" align="right">填报时间</td>--%>
                    <td width="15px"></td>
                    <td>
                        <input type="text" width="100%" name="filterMap['publicDate']" id="publicDate" readOnly="true"
                               style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  value="${publicDate}" />
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table width="100%" id="SP171101_list_grid">
                <thead>
                <tr>
                    <th coltype="sno"  filter="false" align="center" style="padding-right: 10px; padding-left: 10px;">序号</th>
                    <th coltype="select"  filter="true" name="lgcsName" width="8%" align="center" style="padding-right: 10px; padding-left: 10px;">区域
                         <select  name="filterMap['lgcsCode']"  id="districtName">
                            <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                                <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </th>
                    <th coltype="text" filter="true" name="pdTypeCode" align="center" style="padding-right: 10px; padding-left: 10px;">产品编码</th>
                    <th coltype="text" filter="true" name="classes" align="center" style="padding-right: 10px; padding-left: 10px;">产品一级分类</th>
                    <th coltype="text" filter="true" name="machining" align="center" style="padding-right: 10px; padding-left: 10px;">产品二级分类</th>
                    <th coltype="text" filter="true" name="breed" align="center" style="padding-right: 10px; padding-left: 10px;">品种</th>
                    <th coltype="text" filter="true" name="feature" align="center" style="padding-right: 10px; padding-left: 10px;">特征</th>
                    <th coltype="text" filter="true" name="weight" align="center" style="padding-right: 10px; padding-left: 10px;">净重(kg/箱)</th>
                    <th coltype="text" filter="false" name="stockCnt" align="center" style="padding-right: 10px; padding-left: 10px;">现有库存量(吨)</th>
                    <th coltype="text" filter="false" name="forecastNum" align="center" style="padding-right: 10px; padding-left: 10px;">预计需求量(吨)</th>
                    <th coltype="text" filter="false" name="publishTotalNum" id="publishTotalNum" align="center" style="padding-right: 10px; padding-left: 10px;">本期需求(吨)
                    </th>
                    <th coltype="action" align="center" style="padding-right: 10px; padding-left: 10px;">操作
                        <msk:button buttonValue="需求详情" buttonId="SP171101.EDIT" buttonType="hidden" coltype="edit" class="h-button" />
                    </th>
                </tr>
                </thead>
                <tbody id="SP171101Tbody">
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/sp/js/SP171101.js"></script>
