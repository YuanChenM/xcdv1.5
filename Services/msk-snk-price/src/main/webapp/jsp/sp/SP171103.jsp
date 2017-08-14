<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="GradeCode" viewType="json" />
<msk2:codemaster codeType="IsConfirm" viewType="json" />

<navigation:header title="供应商申报情况一览" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form:form action="${ctx}/SP171103/search" id="SP171103Form" metdod="post">
        <input name="filterMap['demandStartDate']" type="hidden" id="demandStartDate" value="<fmt:formatDate value='${demandStartDate}' pattern='yyyy-MM-dd' />"/>
        <input name="filterMap['demandEndDate']" type="hidden" id="demandEndDate" value="<fmt:formatDate value='${demandEndDate}' pattern='yyyy-MM-dd' />"/>
        <input name="loginId" type="hidden" id="loginId" value="${loginId}"/>
        <input name="nowMonth" type="hidden" id="nowMonth" value="${nowMonth}"/>
        <div class="group-accordion" collapsible="" active="false">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%" border="0">
                <tr>
                    <td align="left">*申报周期: </td>
                    <td align="left">　
                        <select width="25px" name="filterMap['demandYearMonth']"  id="demandYearMonth">
                            <c:forEach items="${demandYearMonthlist}" var="demandYearMonth" varStatus="status">
                                <option value="${demandYearMonth.demandYearMonth}"
                                        StartDate="${demandYearMonth.demandStartDate}"
                                        EndDate="${demandYearMonth.demandEndDate}"
                                        showName="${demandYearMonth.demandLimitedDateShow}">${demandYearMonth.demandYearMonthShow}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td coltype="text" align="left" width="10%">填报时间: </td>
                    <td colspan="3" align="left" width="50%">
                        <label id="demandLimitedDateShow">${demandLimitedDateShow}</label>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table id="SP171103_list_grid" width="100%">
                <thead>
                    <tr>
                        <th coltype="sno"  width="40px">序号</th>
                        <input type="hidden" name="publishId">
                        <input type="hidden" name="demandId">
                        <th coltype="select"  filter="true" name="lgcsName" style="min-width: 100px;" >区域
                            <select  name="filterMap['lgcsCode']"  id="districtName" style="width: 100%">
                                <c:forEach items="${lgcsinfo}" var="lgcs" varStatus="status" >
                                    <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                                </c:forEach>
                            </select>
                        </th>
                        <th coltype="text" name="pdCode" filter=true>产品编码</th>
                        <th coltype="text" name="classesName" filter=true>产品一级分类</th>
                        <th coltype="text" name="machiningName" filter=true>产品二级分类</th>
                        <th coltype="text" name="breedName" filter=true>品种</th>
                        <th coltype="text" name="scientificName" filter=true>学名</th>
                        <th coltype="text" name="localName" filter=true>俗名</th>
                        <th coltype="text" name="featureName" filter=true>特征</th>
                        <th coltype="text" name="weightName" filter=true>净重(kg/箱)</th>
                        <th coltype="code" name="gradeCode"filter="true" code2name="GRADECODE_JSON">产品等级</th>
                        <th coltype="text" name="publishNum">本期需求(吨)</th>
                        <th coltype="text" name="slName" filter=true>供应商</th>
                        <th coltype="text" name="applyNum">申报数量(吨)</th>
                        <th coltype="code" filter="true" name="isConfirm" code2name="ISCONFIRM_JSON" id="isConfirm">申报状态</th>
                        <th coltype="action" >操作
                            <msk:button buttonValue="申报数量详情" buttonId="SP171103.DETAIL" buttonType="hidden" coltype="check" class="h-button" useable="can_abolish"/>
                        </th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/sp/js/SP171103.js"></script>

