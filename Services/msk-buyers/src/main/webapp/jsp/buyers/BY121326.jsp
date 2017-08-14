<%--
    Title:分销买家营销期冻品管家营销信息管控表
    author:jiang_zhenping
    createDate:2016-08-23
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form action="${ctx}/BY121326/search" id="BY121326Form" method="post">
        <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}"/>
        <table width="100%" cellspacing=8>
            <tr>
                <td align="right">物流区 : </td>
                <td>
                    <select name="lgcsAreaCode" id="lgcsAreaCode">
                        <option value="">--请选择--</option>
                        <c:forEach items="${logisticsAreaList}" var="lgcs" varStatus="status">
                            <option value="${lgcs.lgcsAreaCode}">${lgcs.lgcsAreaName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">地区(城市) : </td>
                <td>
                    <select name="cityCode" id="cityCode">
                        <option value="">--请选择--</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.cityCode}">${city.cityName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">营销开始时间 : </td>
                <td>
                    <input type="text" id="fileStartTime" name="filterMap['fileStartTime']"/>
                </td>
            </tr>
            <tr>
                <td align="right">营销结束时间 : </td>
                <td>
                    <input type="text" id="fileEndTime" name="filterMap['fileEndTime']"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <msk:button buttonValue="生成文件" buttonId="BY121326.CREATE" buttonType="button"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="${ctx}/static/buyers/js/BY121326.js"></script>