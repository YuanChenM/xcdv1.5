<%--
    Title:产品价盘维护
    author:gyh
    createDate:2016-1-15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<navigation:header title="产品价盘维护" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content detail-page">
    <form:form action="${ctx}/PD141120/save" id="PD141120Form"
               method="post" commandName="pdPriceprdLogiarea" modelAttribute="pdPriceprdLogiarea">
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>产品价盘基础信息</label>
            </h3>

            <div>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">类别</td>
                        <td align="left">
                            <form:select path="classesCode" style="width:120px" id="pdClasses">
                                <form:option value="" label="请选择"/>
                                <form:options items="${pdClasses}" itemValue="classesCode" itemLabel="classesName"/>
                            </form:select>
                            <form:hidden path="classesName" id="classesName"/>
                        </td>
                        <td width="100px" align="right">二级分类</td>
                        <td align="left">
                            <form:select path="machiningCode" style="width:120px" id="pdMachining">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <form:hidden path="machiningName" id="machiningName"/>
                        </td>
                        <td width="100px" align="right">品种</td>
                        <td align="left">
                            <form:select path="breedCode" style="width:120px" id="pdBreed">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <form:hidden path="breedName" id="breedName"/>
                        </td>
                        <td width="100px" align="right">特征</td>
                        <td align="left">
                            <form:select path="featureCode" style="width:120px" id="pdFeature">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <form:hidden path="featureName" id="featureName"/>
                        </td>

                    </tr>
                    <tr>
                        <td width="100px" align="right">净重</td>
                        <td align="left">
                            <form:select path="weightCode" style="width:120px" id="pdWeight">
                                <form:option value="" label="请选择"/>
                            </form:select>
                                <%--<form:hidden path="weightName" id="weightName"/>--%>
                        </td>
                        <td width="100px" align="right">包装</td>
                        <td align="left">
                            <form:select path="pkgCode" style="width:120px" id="pdNorms">
                                <form:option value="" label="请选择"/>
                            </form:select>
                            <form:hidden path="netweight" id="netweight"/>
                        </td>
                        <td width="100px" align="right">产品等级</td>
                        <td align="left">
                            <form:select path="gradeCode" style="width:120px" id="gradeCode">
                                <form:option value="" label="请选择"/>
                                <form:options items="${pdGrades}" itemLabel="gradeName" itemValue="gradeCode"/>
                            </form:select>
                        </td>
                        <td width="100px" align="right">物流区</td>
                        <td align="left">
                            <form:select path="logiareaCode" style="width:120px" id="logiareaCode">
                                <form:option value="" label="请选择"/>
                                <form:options items="${commLogisticsAreas}" itemLabel="lgcsAreaName"
                                              itemValue="lgcsAreaCode"/>
                            </form:select>
                            <form:hidden path="logiareaName" id="logiareaName"/>
                        </td>
                        <form:hidden path="pdtName" id="pdtName"/>
                    </tr>
                    <tr>
                        <td width="100px" align="right">价盘周期</td>
                        <td align="left">
                            <form:input path="pricecycleDate" style="width:40px" id="priceDate"/>
                            <form:select path="pricecycle" style="width:90px">
                                <form:option value="" label="请选择"/>
                                <form:option value="1" label="1-5日"/>
                                <form:option value="2" label="6-10日"/>
                                <form:option value="3" label="11-15日"/>
                                <form:option value="4" label="16-20日"/>
                                <form:option value="5" label="21-25日"/>
                                <form:option value="6" label="26日-月底"/>
                            </form:select>
                        </td>
                        <td width="100px" align="right">通道类型</td>
                        <td align="left">
                            <form:select path="wayCode" style="width:120px" id="wayCode" value="01">
                                <form:options items="${pdWayInfo}" itemLabel="wayName" itemValue="wayCode"/>
                            </form:select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="priceDetail">
        </div>
    </form:form>
    <div>
        <table>
            <tr>
                <td><msk:button buttonValue="保存" buttonType="button" buttonId="PD141120.SAVE"/></td>
                <td><msk:button buttonValue="查询" buttonType="button" buttonId="PD141120.SEARCH"/></td>
                <td width="200" align="right"><msk:button buttonValue="开始新价盘" buttonType="button" buttonId="PD141120.NEW"/></td>
            </tr>
        </table>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD141120.js"></script>