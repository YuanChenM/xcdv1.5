<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${pageType eq '1'}">
    <navigation:header title="其他供应商申报价格一览" backTitleArray="价盘一览"
                       backUrlArray="${ctx}/SP171112/init/"></navigation:header>
</c:if>
<c:if test="${pageType eq '0'}">
    <navigation:header title="其他供应商申报价格一览" backTitleArray="供应商价格申报详细"
                       backUrlArray="${ctx}/SP171110/init/?pdCode=${pdCode}&pricePeriod=${pricePeriod}"></navigation:header>
</c:if>
<div class="page-content list-page">
    <form:form action="${ctx}/SP171111/search" id="SP171111Form" metdod="post">
        <input name="filterMap['pricePeriod']" type="hidden" id="pricePeriod" value="${pricePeriod}"/>
        <input name="filterMap['pdCode']" type="hidden" id="pdCode" value="${pdCode}"/>
        <input name="filterMap['lgcsCode']" type="hidden" id="lgcsCode" value="${lgcsCode}"/>
        <input name="filterMap['slCode']" type="hidden" id="slId" value="${slId}"/>
        <input name="filterMap['isSupply']" type="hidden" id="slCode" value="${isSupply}"/>
        <input name="filterMap['isPrice']" type="hidden" id="slCode" value="${isPrice}"/>

        <div>
            <table id="SP171111_list_grid">
                <thead>
                <tr>
                    <th coltype="text" name="pdCode">产品编码</th>
                    <th coltype="text" name="classesName">产品一级分类</th>
                    <th coltype="text" name="machiningName">产品二级分类</th>
                    <th coltype="text" name="breedName">品种</th>
                    <th coltype="text" name="featureName">特征</th>
                    <th coltype="text" name="weightName">净重(kg/箱)</th>
                    <th coltype="text" name="gradeName">产品等级</th>
                    <th coltype="text" name="slCode">供应商</th>
                    <th coltype="text" name="wayGradePrice">申报价格</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/sp/js/SP171111.js"></script>

