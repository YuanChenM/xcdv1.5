<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<navigation:header title="价盘详情" backTitleArray="价盘查询" backUrlArray="${ctx}/SP171112/init/"></navigation:header>
<div class="page-content detail-page">
    <form action="${ctx}/SP171113/save" id="SP171113Form" method="post">
        <input type="hidden" id="defaultWayGrade" name="defaultWayGrade" value="${defaultWayGrade}">

        <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>价盘信息</label>
        </h3>
        <table style="width: 100%;" CellSpacing=3>
            <tr>
                <td width="100px" align="right">物流区：</td>
                <td align="left">${lgcsName}</td>
                <td width="15%" align="right">价盘期：</td>
                <td width="35%" align="left">${pricePeriod}</td>
            </tr>
            <tr>
                <td width="100px" align="right">价盘所属期限：</td>
                <td align="left">${beforePeriodTime}</td>
                <td width="15%" align="right">产品名称：</td>
                <td width="35%" align="left">${pdName}</td>
            </tr>
        </table>
        </div>

        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label>价盘周期详细</label>
            </h3>
        <table id="SP171113Table" class="tree dataTable no-footer">
                <thead>
                <tr>
                    <th coltype="text">分销通道及通道等级</th>
                    <th coltype="text">通道等级</th>
                    <th coltype="text">标准报价平衡系数(%)</th>
                    <%--<th coltype="text">价盘报价下限价(元/kg)</th>
                    <th coltype="text">价盘报价上限价(元/kg)</th>--%>
                    <th colty   pe="text">半旬标准定价价盘(元/kg)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${priceDetailList}" var="priceDetailInfo" varStatus="i">
                    <tr>
                        <c:if test="${i.index eq ('0')}">
                            <td align="center" rowspan="3">大宗订单</td>
                        </c:if>
                        <c:if test="${i.index eq ('3')}">
                            <td align="center" rowspan="3">大额订单</td>
                        </c:if>
                        <c:if test="${i.index eq ('6')}">
                            <td align="center" rowspan="4">标准订单</td>
                        </c:if>
                        <td align="center">${priceDetailInfo.wayGateName}</td>
                        <td align="center">${priceDetailInfo.wayGradePercent}</td>
                        <%--<td align="center">${priceDetailInfo.downPrice}</td>
                        <td align="center">${priceDetailInfo.upPrice}</td>--%>
                        <c:choose>
                            <c:when test="${userType eq '1' && overDataFlag eq '0'}">
                                <td align="center">
                                    <input type="number" indexNum="${i.index+1}" min="0" id="wayGradePrice${i.index+1}" name="wayGradePriceArray" value="${priceDetailInfo.price}">
                                </td>
                                <input type="hidden" name="priceIdArray" value="${priceDetailInfo.priceId}">
                            </c:when>
                            <c:otherwise>
                                <td align="center">${priceDetailInfo.price}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
    <br>
    <span>
        <c:if test="${userType eq '1' && overDataFlag eq '0'}">
            <msk:button buttonValue="保存" buttonId="SP171113.SAVE"  buttonType="button" />
        </c:if>
    </span>
</div>

<script src="${ctx}/static/sp/js/SP171113.js"></script>

