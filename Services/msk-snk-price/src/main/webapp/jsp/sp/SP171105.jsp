<%--
    Title:供应商待申报产品一览
    author:tang_feng
    createDate:2016-5-3
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<msk2:codemaster codeType="IsConfirm" viewType="json" />
<navigation:header title="供应商待申报产品一览" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/SP171105/search" id="SP171105Form" metdod="post">
            <input name="filterMap['demandStartDate']" type="hidden" id="demandStartDate" value="<fmt:formatDate value='${demandStartDate}' pattern='yyyy-MM-dd' />"/>
            <input name="filterMap['demandEndDate']" type="hidden" id="demandEndDate" value="<fmt:formatDate value='${demandEndDate}' pattern='yyyy-MM-dd' />"/>
            <%--<input name="loginId" type="hidden" id="loginId" value="${loginId}"/>--%>
            <div class="group-accordion" collapsible="" active="false" style="min-width: 1425px">
                <h3>
                    <label>查询条件</label>
                </h3>
                <table WIDTH="100%" border="0">
                    <tr>
                        <td align="left">*申报周期</td>
                        <td align="left">　
                            <select width="25px" name="filterMap['demandYearMonth']"  id="demandYearMonth">
                                <c:forEach items="${demandYearMonthList}" var="item" varStatus="status">
                                    <option value="${item.demandYearMonth}" showName="${item.demandLimitedDateShow}">${item.demandYearMonthShow}</option>
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
                <font color="red" size="3px" style="float:right">*卖家在该物流区所有分销品种的总供应量为30吨的整数倍</font>
                <table id="SP171105_grid">
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
                            <th coltype="text" style="min-width: 40px;" filter="true" name="machiningName">产品二级分类</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="breedName">品种</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="featureName">特征</th>
                            <th coltype="text" style="min-width: 40px;" filter="true" name="weight">净重(kg/箱)</th>
                            <th coltype="code" style="min-width: 60px;" filter="true" name="gradeCode" code2name="GRADECODE_JSON">供应等级</th>
                            <th coltype="text" style="min-width: 60px;" filter="false" name="stockQty">已有库存量(吨)</th>
                            <th coltype="text" style="min-width: 60px;" filter="false" name="forecastNum">预计需求量(吨)</th>
                            <th coltype="text" style="min-width: 60px;" filter="false" name="publishTotalNum">本期需求(吨)</th>
                            <th coltype="text" style="min-width: 80px;" filter="false" name="publishNum">对应等级需求量(吨)</th>
                            <th coltype="text" style="min-width: 60px;" filter="false" name="applyQty">申报数量(吨)</th>
                            <th coltype="code" style="min-width: 60px;" filter="true" name="isConfirm" code2name="ISCONFIRM_JSON">申报状态</th>
                            <th coltype="action" style="min-width: 40px;"> 操作
                                <msk:button buttonValue="申报数量" buttonId="SP171105.EDIT" buttonType="hidden" coltype="edit" class="h-button" useable="can_abolish"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
            </div>
        </form>
</div>
<%--<input type="button" value="sd" onclick="report()">--%>
<script src="${ctx}/static/sp/js/SP171105.js"></script>
