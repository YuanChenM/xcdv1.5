<%--
    Title:供应商待报价一览
    author:tang_feng
    createDate:2016-5-3
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<navigation:header title="供应商待报价一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SP171109/search" id="SP171109Form" metdod="post">
            <input name="filterMap['priceStartDate']" type="hidden" id="priceStartDate" value="<fmt:formatDate value='${priceStartDate}' pattern='yyyy-MM-dd' />"/>
            <input name="filterMap['priceEndDate']" type="hidden" id="priceEndDate" value="<fmt:formatDate value='${priceEndDate}' pattern='yyyy-MM-dd' />"/>
            <input name="loginId" type="hidden" id="loginId" value="${loginId}"/>
            <input type="hidden" id="viewFlg" value="${viewFlg}"/>
            <div class="group-accordion" collapsible="" active="false" style="min-width: 910px">
                <h3>
                    <label>查询条件</label>
                </h3>
                <table WIDTH="100%" border="0">
                    <tr>
                        <td align="left">*价盘周期</td>
                        <td align="left">　
                            <select width="25px" name="filterMap['pricePeriod']"  id="pricePeriod">
                                <c:forEach items="${pricePeriodList}" var="item" varStatus="status">
                                    <option value="${item.pricePeriod}" showName="${item.demandLimitedDateShow}">${item.demandYearMonthShow}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td coltype="text" align="left" width="10%">填报时间</td>
                        <td colspan="3" align="left" width="50%">
                            <label id="demandLimitedDateShow">${demandLimitedDateShow}</label>
                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <table id="SP171109_grid">
                    <thead>
                        <tr>
                            <th coltype="sno" style="min-width: 40px;">序号</th>
                            <th coltype="select" filter="true" style="min-width: 80px;" name="lgcsName">区域
                                <select  name="lgcsCode"  id="districtName">
                                    <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status">
                                        <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="pdTypeCode">产品编码</th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="classesName">产品一级分类</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="machining">产品二级分类</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="breed">品种</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="feature">特征</th>
                            <th coltype="text" style="min-width: 60px;" filter="true" name="weight">净重(kg/箱)</th>
                            <th coltype="code" style="min-width: 60px;" filter="true" name="gradeCode" code2name="GRADECODE_JSON">供应等级</th>
                            <th coltype="text" style="min-width: 60px;" filter="false" name="stockQty">已有库存量(吨)</th>
                            <th coltype="text" style="min-width: 40px;" filter="false" name="applyPrice">报价(元/kg)</th>
                            <th coltype="action" style="min-width: 40px;">操作
                                <msk:button buttonValue="申报价格" buttonId="SP171109.EDIT" buttonType="hidden" coltype="edit" class="h-button" useable="can_abolish"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
            </div>
        </form>
</div>
<script src="${ctx}/static/sp/js/SP171109.js"></script>
