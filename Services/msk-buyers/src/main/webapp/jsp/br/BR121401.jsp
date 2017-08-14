<%--
    Title:买家标准产品池列表
    author:zhao_chen1
    createDate:2016-06-06
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="FileStatus" viewType="json"/>
<navigation:header title="买家标准产品池列表" backTitleArray="" backUrlArray=""></navigation:header>
<style type="text/css">
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<div class="page-content list-page">
    <form action="${ctx}/BR121401/search" id="BR121401Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right" width="12.5%">报表开始时间</td>
                    <td width="6%">
                        <input type="text" id="fileStartTime" name="filterMap['fileStartTime']" readonly="readonly"/>
                    </td>
                    <td align="right" width="12.5%">报表结束时间</td>
                    <td width="6%">
                        <input type="text" id="fileEndTime" name="filterMap['fileEndTime']" readonly="readonly"/>
                    </td>
                    <td width="12.5%"></td>
                    <td width="6%"></td>
                    <td></td>
                    <td></td>


                </tr>
                <tr>
                    <td align="right">物流区</td>
                    <td width="">
                        <select name="filterMap['lgcsAreaCode']" id="lgcsAreaCode">
                            <option value="">---请选择---</option>
                            <c:forEach items="${logisticsAreaList}" var="lgcs" varStatus="status">
                                <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">地区(城市)</td>
                    <td>
                        <select name="filterMap['cityCode']" id="cityCode" style="width: 100%">
                            <option value="">---请选择---</option>
                            <c:forEach items="${cityList}" var="city">
                                <option value="${city.cityCode}">${city.cityName}</option>
                            </c:forEach>

                        </select>
                    </td>
                    <td align="right">区县</td>
                    <td>
                        <select name="filterMap['districtCode']" id="districtCode" style="width: 100%">
                            <option value="">---请选择---</option>
                            <c:forEach items="${districtList}" var="district">
                                <option value="${district.districtCode}">${district.districtName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>

                    <td></td>
                </tr>
                <tr>
                    <td align="right">买家类型</td>
                    <td>
                        <select name="filterMap['buyersType']" id="buyersType" style="width: 100%">
                            <option value="">---请选择---</option>
                            <c:forEach items="${buyerTypeList}" var="buyerType">
                                <option value="${buyerType.key}">${buyerType.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td></td>

                    <td></td>
                    <td></td>
                    <td></td>

                    <td></td>
                </tr>
                <tr>
                    <td align="right">产品一级分类</td>
                    <td>
                        <select name="filterMap['classesCode']" id="classesCode" style="width: 100%">
                            <option value="">---请选择---</option>
                            <c:forEach items="${classesList}" var="classes">
                                <option value="${classes.classesCode}">${classes.classesName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">产品二级分类</td>
                    <td>

                        <select name="filterMap['machiningCode']" id="machiningCode" style="width: 100%">
                            <option value="">---请选择---</option>
                        </select>
                    </td>

                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="right" width="5%"><msktag:button buttonValue="查询" buttonId="BR121401.SEARCH"
                                                                buttonType="button"/></td>
                </tr>
            </table>
        </div>
        <div>
            <table width="100%" id="BR121401_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center">编号</th>
                    <th coltype="text" name="fileName" align="center">买家标准产品池在线管控表</th>
                    <th coltype="code" name="fileStatus" code2name="FILESTATUS_JSON">文件状态</th>
                    <th coltype="action" width="12%" align="center">操作

                    </th>
                </tr>
                </thead>
                <tbody id="BR121401Tbody">
                </tbody>
            </table>
        </div>
    </form>
</div>

<script src="${ctx}/static/util/js/download.js"></script>
<script src="${ctx}/static/br/js/BR121401.js"></script>
