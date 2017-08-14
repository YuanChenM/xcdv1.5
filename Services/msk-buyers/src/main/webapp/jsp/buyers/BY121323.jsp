<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家销售管控画面" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BY121323/search" id="BY121323Form" method="post">
        <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}" />
        <input type="hidden" id="searchType" name="searchType" value="1" />
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td  width="10%" align="right">物流区 : </td>
                    <td width="8%">
                        <select name="lgcsAreaCode" id="lgcsAreaCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="lgcs" varStatus="status">
                                <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td  width="10%" align="right">地区(城市) : </td>
                    <td width="8%">
                        <select name="cityCode" id="cityCode" style="width: 100%">
                            <option value="">--请选择--</option>
                            <c:forEach items="${cityList}" var="city">
                                <option value="${city.cityCode}">${city.cityName}</option>
                            </c:forEach>

                        </select>
                    </td>
                    <%--<td  width="10%" align="right">一级产品分类</td>--%>
                    <%--<td width="8%">--%>
                        <%--<select name="filterMap['classesCode']" id="classesCode" style="width: 100%">--%>
                            <%--<option value="">----请选择----</option>--%>
                            <%--<c:forEach items="${classesList}" var="classes">--%>
                                <%--<option value="${classes.classesCode}">${classes.classesName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                    <%--<td  width="10%" align="right">二级产品分类</td>--%>
                    <%--<td width="8%">--%>
                        <%--<select name="filterMap['machiningCode']" id="machiningCode" style="width: 100%">--%>
                            <%--<option value="">-----请选择-----</option>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                    <td></td>
                </tr>
                <tr>
                    <td align="right" width="10%">销售开始日期 : </td>
                    <td width="8%">
                        <input type="text" id="startTime" name="startTime" style="width: auto" readonly="readonly"/>
                    </td>
                    <td align="right" width="10%">销售结束日期 : </td>
                    <td width="8%">
                        <input type="text" id="endTime" name="endTime" readonly="readonly"/>
                    </td>

                    <td />
                    <td />
                    <td />
                    <td />
                    <td align="right">
                        <msk:button buttonValue="查询" buttonId="BY121323.SEARCH" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>分销买家销售期冻品管家销售信息管控表</label>
            </h3>
            <table width="100%" id="BY121323_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center" >编号</th>
                    <th coltype="text" name="buyerPoolName" align="center" >分类买家池名</th>
                    <th coltype="text" name="houseShowName" align="center">冻品管家</th>
                    <th coltype="text" name="developmentWay" align="center" >专属会员发展方式</th>
                    <th coltype="datetime" name="startTime" align="center" width="15%">专属会员归属期(销售开始日期)</th>
                    <th coltype="datetime" name="endTime" align="center"  width="15%">专属会员归属期(销售结束日期)</th>
                    <th coltype="text" name="changeReason" align="center" >冻品管家变更原因</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/buyers/js/BY121323.js"></script>
