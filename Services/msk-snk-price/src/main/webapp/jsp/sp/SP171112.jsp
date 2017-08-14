<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<navigation:header title="价盘查询" backTitleArray="" backUrlArray=""></navigation:header>
<style type="text/css">
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<div class="page-content list-page">
    <form action="${ctx}/SP171112/search" method="post">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>价盘周期查询</label>
        </h3>
    <table style="width: 100%;" CellSpacing=3>
        <tr>
            <td width="100px" align="right">价盘周期</td>
            <td align="left">
                <input name="priceDate" id="priceDate" style="width:40px" value="${priceDate}"/>
                <select id="pricecycle" name="pricecycle" style="width:80px">
                    <%--<option value="" <c:if test="${pricecycle eq ''}">selected</c:if>>请选择</option>--%>
                    <option value="1" <c:if test="${pricecycle eq '1'}">selected</c:if>>1-5日</option>
                    <option value="2" <c:if test="${pricecycle eq '2'}">selected</c:if>>6-10日</option>
                    <option value="3" <c:if test="${pricecycle eq '3'}">selected</c:if>>11-15日</option>
                    <option value="4" <c:if test="${pricecycle eq '4'}">selected</c:if>>16-20日</option>
                    <option value="5" <c:if test="${pricecycle eq '5'}">selected</c:if>>21-25日</option>
                    <option value="6" <c:if test="${pricecycle eq '6'}">selected</c:if>>26日-月底</option>
                </select>
            </td>
            <td width="15%" align="right">填报时间</td>
            <td width="35%" align="left" id="lastPricePeriodTime">${beforePeriodTime}</td>
        </tr>
    </table>
    </div>
    <div>
        <input type="hidden" name="userType" id="userType" value="${userType}">
        <input type="hidden" name="pricePeriod" id="pricePeriod" value="${pricePeriod}">
        <input type="hidden" name="lastPricePeriod" id="lastPricePeriod" value="${lastPricePeriod}">
        <input type="hidden" name="pricePeriodStart" id="pricePeriodStart" value="${pricePeriodStart}">
        <input type="hidden" name="pricePeriodEnd" id="pricePeriodEnd" value="${pricePeriodEnd}">
        <table id="SP171112_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="select" filter="true" style="min-width: 80px;" name="lgcsName" width="7%">区域
                    <select  name="lgcsCode"  id="districtName">
                        <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                            <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                        </c:forEach>
                    </select>
                </th>
                <th coltype="text" name="pdCode" filter=true>产品编码</th>
                <th coltype="text" name="classesName" filter=true>产品一级分类</th>
                <th coltype="text" name="machiningName" filter=true>产品二级分类</th>
                <th coltype="text" name="breedName" filter=true>品种</th>
                <th coltype="text" name="featureName" filter=true>特征</th>
                <th coltype="text" name="weightName" filter=true>净重(kg/箱)</th>
                <th coltype="code" width="10%" filter="true" name="gradeCode" code2name="GRADECODE_JSON">产品等级</th>
                <th coltype="text" name="wayGradePrice" filter=false>半旬平均报价(元/kg)</th>
                <th coltype="text" name="wayGradePriceBox" filter=false>箱价</th>
                <th coltype="action">
                    <msk:button buttonValue="价盘详情" buttonId="SP171112.DETAIL" buttonType="hidden" coltype="detail" class="h-button"/>
                    <msk:button buttonValue="其他供应商申报价格一览" buttonId="SP171112.SEARCH" buttonType="hidden" coltype="search" class="h-button"/>
                    <msk:button buttonValue="删除" buttonId="SP171112.DELETE" buttonType="hidden" coltype="delete" class="h-button"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/sp/js/SP171112.js"></script>


