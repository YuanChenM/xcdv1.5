<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
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
            <td align="right">销售开始时间 : </td>
            <td>
                <input type="text" id="fileStartTime" name="startTime" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right">销售结束时间 : </td>
            <td>
                <input type="text" id="fileEndTime" name="endTime" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <msk:button buttonValue="生成文件" buttonId="BY121325.CREATE" buttonType="button"/>
            </td>
        </tr>
    </table>

</div>
<script src="${ctx}/static/buyers/js/BY121325.js"></script>
