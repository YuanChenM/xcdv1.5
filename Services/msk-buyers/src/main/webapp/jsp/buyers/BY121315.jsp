<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<navigation:header title="买家上线状态管控" backTitleArray="买家列表,买家信息总控画面"
                   backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">

    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家上线状态管控</label>
        </h3>

        <form id="BY121315SaveForm" action="${ctx}/BY121315/save/" method="post">
            <input type="hidden" name="buyerId" value="${buyerId}"/>
            <input type="hidden" id="marketingsStatus" name="marketingsStatus" value="${marketStatus.marketingsStatus}"/>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right">营销期 : </td>
                    <td colspan="7" width="87.5%" align="left">
                        <c:forEach items="${marketStatus.marketPeriod}" var="marketPeriod">
                            <c:choose>
                                <c:when test="${marketPeriod.key eq marketStatus.marketingsStatus}">
                                    <input type="radio" disabled="disabled" name="marketingsStatus" value="${marketPeriod.key}" checked/>${marketPeriod.value}
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" disabled="disabled" name="marketingsStatus" value="${marketPeriod.key}"/>${marketPeriod.value}
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">销售期 : </td>
                    <td colspan="7" width="87.5%" align="left">
                        <c:forEach items="${marketStatus.salesPeriod}" var="salesPeriod">
                            <c:choose>
                                <c:when test="${salesPeriod.key eq marketStatus.marketingsStatus}">
                                    <input type="radio" disabled="disabled" name="marketingsStatus" value="${salesPeriod.key}" checked/>${salesPeriod.value}
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" disabled="disabled" name="marketingsStatus" value="${salesPeriod.key}"/>${salesPeriod.value}
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">异常(可兼容) : </td>
                    <td colspan="7" width="87.5%" align="left">
                        <c:forEach items="${marketStatus.exceptional}" var="exceptional">
                            <c:choose>
                                <c:when test="${'31' eq exceptional.key}">
                                    <c:choose>
                                        <c:when test="${exceptional.key eq marketStatus.marketingsStatus}">
                                            <input type="radio" id="exception${exceptional.key}" name="marketingsStatus" value="${exceptional.key}" checked/>${exceptional.value}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" id="exception${exceptional.key}" name="marketingsStatus" value="${exceptional.key}"/>${exceptional.value}
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${exceptional.key eq marketStatus.marketExceptionStatus}">
                                            <input type="checkbox" id="exception${exceptional.key}" name="marketExceptionStatus" value="${exceptional.key}" checked/>${exceptional.value}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" id="exception${exceptional.key}" name="marketExceptionStatus" value="${exceptional.key}"/>${exceptional.value}
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <span>,请填写具体的错误信息 : </span>
                        <input type="text" id="marketExceptionRemark" value="${marketStatus.marketExceptionRemark}" name="marketExceptionRemark" maxlength="100"/>
                    </td>
                </tr>
            </table>
            <msk:button buttonType="button" buttonId="BY121315.SAVE" buttonValue="保存"/>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121315.js"></script>