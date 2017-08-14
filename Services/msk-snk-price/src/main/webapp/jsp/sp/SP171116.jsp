<%--
    Title:供应商待报价一览
    author:tang_feng
    createDate:2016-5-3
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<navigation:header title="产品价盘录入（简易版）" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SP171116/search" id="SP171116Form" metdod="post">
        <input type="hidden" id="priceProid" value="${pricePeriod}">
        <input type="hidden" id="pricePeriodStart" value="${pricePeriodStart}">
        <input type="hidden" id="pricePeriodEnd" value="${pricePeriodEnd}">
            <div class="group-accordion" collapsible="" active="false">
                <h3>
                    <label>查询条件</label>
                </h3>
                <table width="100%" border="0">
                    <tr>

                        <td width="80px" align="left">价盘周期</td>
                        <td align="left">
                            <input path="pricecycleDate" style="width:60px" id="priceDate" name="filterMap[priceDate]" value="${priceDate}"/>
                            <select path="pricecycle" style="width:90px" id="pricecycle" name="filterMap[pricecycle]">
                                <option value="1" <c:if test="${pricecycle eq '1'}">selected</c:if>>1-5日</option>
                                <option value="2" <c:if test="${pricecycle eq '2'}">selected</c:if>>6-10日</option>
                                <option value="3" <c:if test="${pricecycle eq '3'}">selected</c:if>>11-15日</option>
                                <option value="4" <c:if test="${pricecycle eq '4'}">selected</c:if>>16-20日</option>
                                <option value="5" <c:if test="${pricecycle eq '5'}">selected</c:if>>21-25日</option>
                                <option value="6"  <c:if test="${pricecycle eq '6'}">selected</c:if>>26日-月底</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <table id="SP171116_grid">
                    <thead>
                        <tr>
                            <th coltype="sno" style="min-width: 40px;">序号</th>
                            <th coltype="select" filter="true" style="min-width: 80px;" name="lgcsName">区域
                                <select  name="lgcsCode"  id="districtName" style="width: 100%">
                                    <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                                        <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="pdCode">产品编码</th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="classesName">产品一级分类</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="machining">产品二级分类</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="breed">品种</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="feature">特征</th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="weight">净重(kg/箱)</th>
                            <th coltype="code" style="min-width: 60px;" filter="true" name="gradeCode" code2name="GRADECODE_JSON">供应等级</th>
                            <th coltype="text" style="min-width: 40px;" filter="false" name="applyPrice">报价(元/kg)</th>
                            <th coltype="action" style="min-width: 40px;">操作
                                <msk:button buttonValue="申报价格" buttonId="SP171116.EDIT" buttonType="hidden" coltype="edit" class="h-button" useable="can_abolish"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
            </div>
        </form>
</div>
<script src="${ctx}/static/sp/js/SP171116.js"></script>
