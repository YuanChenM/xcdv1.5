<%--
    Title:产品价盘维护
    author:gyh
    createDate:2016-1-15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<div class="group-accordion" collapsible="false" active="false">
    <h3>
        <label id="labelValue">产品价盘详细信息</label>
    </h3>

    <div>
        <table class="dataTable no-footer" id="PD14112001Table">
            <thead>
            <tr style="background:#DBDBDB">
                <th>分销通道及通道等级</th>
                <th>箱数</th>
                <th>通道等级标准报价平衡系数(%)</th>
                <th>卖家报价(元/kg)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pdOrderlevelList}" var="pdOrderlevel" varStatus="i">
                <tr>
                    <td align="center">${pdOrderlevel.orderlevelName}</td>
                    <td align="center">${pdOrderlevel.boxCnt}</td>
                    <td align="center">${pdOrderlevel.pricePercent}
                        <input type="hidden" name="orderlevelCodeArray" value="${pdOrderlevel.orderlevelCode}">
                        <input type="hidden" name="orderlevelNameArray" value="${pdOrderlevel.orderlevelName}">
                        <input type="hidden" name="pricePercentArray" value="${pdOrderlevel.pricePercent}">
                        <input type="hidden" name="boxCntArray" value="${pdOrderlevel.boxCnt}">
                        <input type="hidden" name="boxCntminArray" value="${pdOrderlevel.boxCntmin}">
                        <input type="hidden" name="boxCntmaxArray" value="${pdOrderlevel.boxCntmax}">
                    </td>
                    <%--<c:if test="${pdOrderlevel.orderlevelCode eq '3'}">--%>
                        <%--<td><input type="number" id="priceofkg4" min="0" name="priceofkgArray"></td>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${pdOrderlevel.orderlevelCode ne '3'}">--%>
                        <td><input type="number" name="priceofkgArray" min="0" indexNum="${i.index+1}" id="priceofkg${i.index+1}"/></td>
                    <%--</c:if>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${ctx}/static/js/pd/PD14112001.js"></script>