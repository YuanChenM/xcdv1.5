<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="msk" uri="/msk" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" collapsible="false" active="false">
    <h3>
        <label>买家销售收货信息</label>
    </h3>
    <form:form id="recEditForm" metdod="post" enctype="multipart/form-data">
        <input type="hidden" name="buyerId" id="buyerId" value="${buyerId}">
        <table id="recShowTable" style="width: 100%;"  CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">买家收货地址:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${recAddrList}" var="recAddr" varStatus="i">
                        ${recAddr.receiveAddr}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家收货时间:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${recTimeList}" var="recTime">
                        ${recTime.timeDescribe}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家销售对象:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${salestargetList}" var="salestarget">
                        ${salestarget.salesTargetName}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">
                    <msk:button buttonValue="编辑" buttonId="BUYERREC.EDIT" buttonType="button"/>
                </td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
        </table>
        <table id="recEditTable" style="width: 100%;display:none;"  CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">买家收货地址:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${recAddrList}" var="recAddr" varStatus="i">
                        <input type="hidden" name="recAddr['${i.index}'].id" id="recAddr['${i.index}'].id" value="${recAddr.id}"/>
                        <input type="text" name="recAddr['${i.index}'].receiveAddr" id="recAddr['${i.index}'].receiveAddr" value="${recAddr.receiveAddr}"/>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家收货时间:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${recTimeShowList}" var="recTimeShow">
                        <c:choose>
                            <c:when test="${recTimeShow.isChecked eq '1'}">
                                <input type="checkbox" name="selectRecTime" value="${recTimeShow.recPerType}" checked/>${recTimeShow.timeDescribe}
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="selectRecTime" value="${recTimeShow.recPerType}"/>${recTimeShow.timeDescribe}
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="12.5%" align="right">买家销售对象:</td>
                <td colspan="7" width="87.5%" align="left">
                    <c:forEach items="${salestargetShowList}" var="salestargetShow">
                        <c:choose>
                            <c:when test="${salestargetShow.isChecked eq '1'}">
                                <input type="checkbox" name="salesTarget" value="${salestargetShow.salesTargetType}" checked/>${salestargetShow.salesTargetName}
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="salesTarget" value="${salestargetShow.salesTargetType}"/>${salestargetShow.salesTargetName}
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
            <tr>
            <td width="12.5%" align="right">
                <msk:button buttonValue="完成" buttonId="BUYERREC.SAVE" buttonType="button"/>
            </td>
        </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/comm/BASE_BUYER_REC_INFO.js"></script>