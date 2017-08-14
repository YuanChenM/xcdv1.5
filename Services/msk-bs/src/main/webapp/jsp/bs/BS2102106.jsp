    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:添加冻品管家组下的冻品管家
    author:yang_chunyan
    createDate:2016-8-2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<div class="page-content list-page">
    <form action="${ctx}/BS2102106/search" method="post" id="bs2102106FormId">
        <div class="group-accordion" collapsible="false" active="false" >
            <h3>
                <label>冻品管家条件查询</label>
            </h3>
            <table width="100%">
                <input type="hidden" id="BS2102106_groupId" name="filterMap[dpGroupId]" value="${groupId}">
                <input type="hidden" id="BS2102106_areaCode"  value="${lgcsCode}">
                <input type="hidden" id="BS2102106_districtCode"  value="${cityCode}">
                <input type="hidden" id="BS2102106_areaName"  value="${lgcsName}">
                <input type="hidden" id="BS2102106_cityName"  value="${cityName}">
                <input type="hidden" id="BS2102106_buyerType"  value="${buyerType}">
                <input type="hidden" id="BS2102106_buyerTypeName"  value="${buyerTypeName}">
                <input type="hidden" id="BS2102106_classesName"  value="${classesName}">
                <input type="hidden" id="BS2102106_machiningName"  value="${machiningName}">
                <input type="hidden" id="BS2102106_groupName"  value="${groupName}">
                <tr>
                    <td width="100px" align="right">物流区</td>
                    <td align="left" width="150px">
                        <input type="hidden" name="filterMap[lgcsAreaCode]" id="lgcsCode" value="${lgcsCode}">
                        ${lgcsName}
                    </td>
                    <td width="100px" align="right">地区</td>
                    <td align="left" width="150px">
                        <select name="filterMap[cityCode]"  style="width:120px" id="cityCode" >
                            <option value="">请选择</option>
                            <c:forEach items="${cityList}" var="city"  varStatus="status">
                                <option value="${city.cityCode}">${city.cityName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="80px" align="right">管家一级分类</td>
                    <td align="left">
                        <select name="filterMap[category]"  style="width:120px" id="houseCategory" >
                            <option value="">请选择</option>
                            <c:forEach items="${houseTypeList}" var="category"  varStatus="status">
                                <option value="${category.typeCode}">${category.typeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="80px" align="right">管家二级分类</td>
                    <td align="left">
                        <select  name="filterMap[categorySub]"  style="width:120px" id="houseReclassify" name="houseReclassify">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td>
                    <msk:button buttonValue="查询" buttonId="BS2102106.SEARCH" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table id="bs2102106_list_grid" width="100%">
                <thead>
                <tr>

                    <th coltype="checkbox" name="checkFlag"></th>
                    <th coltype="sno" filter="false">序号</th>
                    <%--<th coltype="text" name="houseGreade" filter="false">级别</th>
                    <th coltype="text" name="houseStar" filter="false">星级</th>--%>
                    <th coltype="text" name="houseShowName" filter="false">姓名</th>
                    <th coltype="text" name="sex" filter="false">性别</th>
                    <th coltype="text" name="houseTel" filter="false">手机号</th>
                    <th coltype="text" name="wechat" filter="">微信号</th>
                </thead>
                <tbody id="bs2102106_tbody">
                </tbody>
            </table>
        </div>
    </form>
    <msk:button buttonType="button" buttonId="BS2102106.SAVE" buttonValue="保存"/>
</div>
<script src="${ctx}/static/bs/js/BS2102106.js"></script>