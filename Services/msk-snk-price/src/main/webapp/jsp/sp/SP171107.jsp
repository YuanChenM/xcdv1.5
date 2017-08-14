<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="其他供应商申报数量一览" backTitleArray="供应商数量申报详细" backUrlArray="${ctx}/SP171106/init?pdCode=${pdCode}&demandId=${demandId}&demandYearMonth=${demandYearmonth}"></navigation:header>
<div class="page-content list-page">
    <form:form action="${ctx}/SP171107/search" id="SP171107Form" metdod="post">
        <input name="filterMap['demandYearMonth']" type="hidden" id="demandYearmonth" value="${demandYearmonth}"/>
        <input name="filterMap['pdCode']" type="hidden" id="pdCode" value="${pdCode}"/>
        <input name="filterMap['lgcsCode']" type="hidden" id="lgcsCode" value="${lgcsCode}"/>
        <input name="filterMap['slCode']" type="hidden" id="slCode" value="${slCode}"/>
        <input name="filterMap['demandId']" type="hidden" id="demandId" value="${demandId}"/>

        <input name="filterMap['isSupply']" type="hidden" id="isSupply" value="${isSupply}"/>
        <input name="filterMap['isNum']" type="hidden" id="isNum" value="${isNum}"/>
        <div>
            <table id="SP171107_list_grid">
                <thead>
                <tr>
                    <th coltype="text" name="pdCode">产品编码</th>
                    <th coltype="text" name="classesName">产品一级分类</th>
                    <th coltype="text" name="machiningName">产品二级分类</th>
                    <th coltype="text" name="breedName">品种</th>
                    <th coltype="text" name="scientificName">学名</th>
                    <th coltype="text" name="localName">俗名</th>
                    <th coltype="text" name="featureName">特征</th>
                    <th coltype="text" name="weightName">净重(kg/箱)</th>
                    <th coltype="text" name="gradeName">产品等级</th>
                    <th coltype="text" name="publishNum">本期需求</th>
                    <th coltype="text" name="slCode">供应商</th>
                    <th coltype="text" name="applyNum">申报数量</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/sp/js/SP171107.js"></script>

