<%--
    Title:分销分类买家池XX分销买家
    author:yuan_zhifei
    createDate:2016-07-26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<%@ taglib prefix="msk1" uri="/codemaster" %>
<msk2:codemaster codeType="FileStatus" viewType="json"/>
<msk2:codemaster codeType="MarketingsStatus" viewType="json"/>
<navigation:header title="分销买家池各上线状态买家报表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="fileServerIp" value="${fileServerIp}">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>生成报表</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right" width="12.5%">报表开始时间 : </td>
                    <td width="5%">
                        <input type="text" id="fileStartTime" name="fileStartTime" readonly="readonly"/>
                    </td>
                    <td align="right" width="12.5%">报表结束时间 : </td>
                    <td width="5%">
                        <input type="text" id="fileEndTime" name="fileEndTime" readonly="readonly"/>
                    </td>
                    <td width="12.5%"></td>
                    <td width="8%"></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">物流区 : </td>
                    <td width="">
                        <select name="lgcsAreaCode" id="lgcsAreaCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                                <c:choose>
                                    <c:when test="${buyerDetail.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                        <option value="${logisticsArea.lgcsAreaCode}" selected>${logisticsArea.lgcsAreaName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">地区(城市) : </td>
                    <td width="8%">
                        <select name="cityCode" id="cityCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${cityList}" var="city">
                                <c:choose>
                                    <c:when test="${buyerDetail.cityCode eq city.cityCode}">
                                        <option value="${city.cityCode}" selected>${city.cityName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.cityCode}">${city.cityName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>

<%--                    <td align="right">区县</td>
                    <td>
                        <select name="filterMap['districtCode']" id="districtCode" style="width: 100%">
                            <option value="">---请选择---</option>
                            <c:forEach items="${districtList}" var="district">
                                <option value="${district.districtCode}">${district.districtName}</option>
                            </c:forEach>
                        </select>
                    </td>--%>
                </tr>
                <tr>
                    <td align="right" width="12.5%">产品一级分类 : </td>
                    <td>
                        <select name="classesCode" id="classesCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${classList}" var="classes">
                                <option value="${classes.classesCode}">${classes.classesName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">产品二级分类 : </td>
                    <td>

                        <select name="machiningCodeU" id="machiningCodeU" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${machiningList}" var="machining">
                                <option value="${machining.machiningCodeU}">${machining.machiningNameU}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="10%">买家类型 : </td>
                    <td width="8%">
                                <select disabled>
                                    <c:forEach items="${buyerType}" var="buyerType">
                                        <option value="${buyerType.key}">${buyerType.value}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" value="01" id="superiorType" name="superiorType"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right" width="12.5%">买家上线状态一级分类 : </td>
                    <td>
                        <select name="filterMap['marketingsStatusCla']" id="marketingsStatusCla">
                            <option value="">--请选择--</option>
                            <option value="1">营销期</option>
                            <option value="2">销售期</option>
                            <option value="98">营销期异常</option>
                            <option value="99">销售期异常</option>
                        </select>
                    </td>
                    <td align="right" width="12.5%">买家上线状态二级分类 : </td>
                    <td>
                        <select name="filterMap['marketingsStatus']" id="marketingsStatus">
                            <c:forEach items="${marketStatusMap}" var="marketStatus">
                                <option value="${marketStatus.key}">${marketStatus.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right"><msktag:button buttonValue="生成" buttonId="BR121407.GENERATE"
                                                     buttonType="button"/></td>
                    <td></td>
                </tr>
            </table>
        </div>
    <form action="${ctx}/BR121407/search" id="BR121407Form" method="post">
        <div>
            <table width="100%" id="BR121407_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center" width="15%">编号</th>
                    <th coltype="text" filter="true" name="fileName" align="center" width="40%">分销买家池各上线状态买家管控表</th>
                    <th coltype="text" filter="true" name="marketingsPeriodName" width="10%">买家上线状态期名称</th>
                    <th coltype="code" name="marketingsStatus" filter="false" code2name="MARKETINGSSTATUS_JSON" width="15%">买家上线状态</th>
                    <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON" width="15%">文件状态</th>
                    <th coltype="text" name="fileCreateTimeStr">文件生成时间</th>
                    <th coltype="action" width="10%" align="center">操作
                        <msk:button buttonValue="下载" buttonId="BR121407.SAVEBTN" buttonType="hidden" coltype="download"
                                    class="h-button" align="center" useable="can_download"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script src="${ctx}/static/util/js/download.js"></script>
<script src="${ctx}/static/br/js/BR121407.js"></script>
