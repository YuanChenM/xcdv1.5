<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="物流区分销需求预测系数设定" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SP171195/save" id="SP171195Form" method="post">
        <div style="padding-left: 30%;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
            <table class="dataTable no-footer" style="width: 50%;">
                <tr>
                    <td align="right" width="30%">选择物流区 </td>
                    <td align="left">
                        &nbsp; <select id="lgcsCode" style="width:135px;" name="filterMap['lgcsCode']">
                        <option value="0">--请选择--</option>
                        <c:forEach items="${lgcsList}" var="list">
                            <option value="${list.lgcsAreaCode}">${list.lgcsAreaName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">下月度库存平衡系数</td>
                    <td align="left">　
                        <input type="text" id="securityRatio" name="filterMap['securityRatio']" >
                        <input type="text" id="securityRatioHint" value="取值：(0.7~1.3)" style="border-style: solid; border-width: 0;" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td  align="right">下月度销量平衡系数</td>
                    <td align="left">　
                        <input type="text" id="sellForecastRatio" name="filterMap['sellForecastRatio']" >
                        <input type="text" id="sellForecastRatioHint" value="取值：(0.9~1.1)" style="border-style: solid; border-width: 0;" readonly="readonly">
                    </td>
                </tr>
            </table>
            <table style="width: 50%;">
                <tr>
                    <td align="center"><msk:button buttonValue="设定预测需求系数" buttonId="SP171195.SAVE" buttonType="button"/>
                </tr>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/sp/js/SP171195.js"></script>
