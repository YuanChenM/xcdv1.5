<%--
    Title:中标确认书一览
    author:yuan_zhifei
    createDate:2016-06-28
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="BidStatus" viewType="json" modelName="SSC"/>
<navigation:header title="中标成交确认书一览" backTitleArray="" backUrlArray="" />

<div class="page-content list-page">
    <form action="<c:url value="/SSC11301/search"/>" method="post">

        <div class="group-accordion" style="white-space:nowrap;" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">招标项目编号:</td>
                    <td  align="left">
                        <input type="text" id="bidProjectNo" name="filterMap['bidProjectNo']" value="${param.filterMap.bidProjectNo}"/>
                    </td>

                    <td  align="right">招标项目名称:</td>
                    <td  align="left">
                        <input type="text"  name="filterMap['bidProjectName']" value="${param.filterMap.bidProjectName}"/>
                    </td>

                    <td  align="right">招标方公司名:</td>
                    <td  align="left">
                        <input type="text" name="filterMap['purchaserName']" value="${param.filterMap.purchaserName}"/>
                    </td>

                    <td  align="right">中标公司名:</td>
                    <td  align="left">
                        <input type="text" name="filterMap['supplierName']" value="${param.filterMap.supplierName}"/>
                    </td>
                </tr>

                <tr>
                    <td  align="right">开标开始时间:</td>
                    <td  align="left">
                        <input type="text" id="startDate" name="filterMap['startDate']" value="${param.filterMap.startDate}"/>
                    </td>

                    <td  align="right">开标结束时间:</td>
                    <td  align="left">
                        <input type="text" id="endDate" name="filterMap['endDate']" value="${param.filterMap.endDate}"/>
                    </td>

                    <td  align="right">中标成交确认书状态:</td>
                    <td  align="left">
                        <select name="filterMap['bidStatus']" style="width: 140px">
                            <option value="">--请选择--</option>
                            <c:forEach items="${bisStatusList}" var="item" varStatus="status">
                                <option value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" align="right">
                        <msk:button buttonValue="查询" buttonId="SSC11301.SEARCH" buttonType="button"/>
                    </td>
                </tr>

            </table>
        </div>


        <div class="group-accordion" style="white-space:nowrap;" collapsible="true" active="true">
            <h3>
                <label>中标成交确认书列表</label>
            </h3>

             <div>
                <table width="100%" id="SSC11301_list_grid">
                    <thead>
                    <tr>
                        <th coltype="sno" filter="false">序号</th>
                        <th coltype="text" width="15%" name="bidProjectNo">招标项目编号</th>
                        <th coltype="text" width="15%" name="bidProjectName">招标项目名称</th>
                        <th coltype="text" width="15%" name="purchaserName">招标方公司名</th>
                        <th coltype="text" width="15%" name="supplierName">中标公司名</th>
                        <th coltype="text" width="15%" name="startDate">开标开始时间</th>
                        <th coltype="text" width="15%" name="endDate">开标结束时间</th>
                        <th coltype="code" name="bidStatus" code2name="BIDSTATUS_JSON">中标成交确认书状态</th>

                        <th coltype="action" width="15%">操作
                            <msk:button buttonValue="中标成交确认书详细" buttonId="SSC11301.DETAIL" buttonType="hidden"
                                        coltype="detail"/>
                            <msk:button buttonValue="删除" buttonId="SSC11301.DETELE" buttonType="hidden"
                                        coltype="delete" useable="can_abolish"/>
                            <msk:button buttonValue="查看关联合同" buttonId="SSC11301.AUDIT" buttonType="hidden"
                                        coltype="audit" useable="can_abolish"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </form>
    <msk:button buttonValue="新增" buttonId="SSC11301.NEW" buttonType="button"/>
</div>


<script src="<c:url value="/static/js/ssc/SSC11301.js"/>"></script>
