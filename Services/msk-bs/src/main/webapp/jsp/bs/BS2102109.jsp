<%-- 
    Title:价盘详情报表

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<navigation:header title="冻品管家总控表下载画面" backTitleArray="报表中心" backUrlArray="${ctx}/BS2102115/init"></navigation:header>

<div class="page-content list-page">
<form  method="post" id="BS2102109Form" >
    <input type="hidden" id="printUrl" value="/excel/async/print/start/faster">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>冻品管家总控表</label>
        </h3>
            <table width="100%">
                <tr>
                    <td width="80px" align="right">物流区<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <select path="logiareaCode" style="width:80px" id="logiareaCode" name="lgcsAreaCode">
                            <option value="0">请选择</option>
                            <c:forEach items="${logisticsAreas}" var="lgcs" varStatus="status">
                                <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="80px" align="right">地区</td>
                    <td align="left">
                        <select path="cityCode" style="width:80px" id="cityCode" name="cityCode">
                            <option value="0">请选择</option>
                        </select>
                    </td>
                    <td width="80px" align="right">管家一级分类</td>
                    <td align="left">
                        <select path="houseCategory" style="width:120px" id="houseCategory" >
                            <option value="">请选择</option>
                            <c:forEach items="${houseTypeList}" var="category"  varStatus="status">
                                <option value="${category.typeCode}">${category.typeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="80px" align="right">管家二级分类</td>
                    <td align="left">
                        <select path="houseTypeList" style="width:120px" id="houseReclassify" name="houseReclassify">
                            <option value="">请选择</option>
                        </select>
                    </td>

                    <td width="80px" align="right">所属期<span style="color: #ff0000">(*必选)</span></td>
                    <td align="left">
                        <input path="creationStartTime" style="width:80px" id="creationStartTime" name="creationStartTime"/>
                         —
                        <input path="creationEndTime" style="width:80px" id="creationEndTime" name="creationEndTime"/>
                    </td>
                    <td><msk:button buttonValue="导出" buttonType="button" buttonId="BS2102109.EXPORT"/></td>
                </tr>
            </table>
    </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/js/core/utils.js"></script>
<script src='<c:url value="/static/js/loading/download.js"/>'/>
<script src="${ctx}/static/bs/js/BS2102109.js"></script>
