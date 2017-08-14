<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家冻品管家信息" backTitleArray="买家信息总控画面" backUrlArray="${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <div class="group-accordion" collapsible="" active="false">
        <h3>买家冻品管家信息</h3>
        <form:form action="#" id="BY121312Form"
                   method="post" enctype="multipart/form-data">
            <%--分销买家--%>
            <table style="width: 100%;" CellSpacing=8 class="dataTable no-footer">
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="20%" colspan="6" align="left">买家冻品管家信息</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%"  rowspan="3" align="center">序号</td>
                    <td width="12.5%" rowspan="3" align="center">冻品管家</td>
                    <td width="12.5%" colspan="2" align="center">营销期</td>
                    <td width="12.5%" colspan="2" align="center">销售期</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%" colspan="2" align="center">公众买家营销锁定期</td>
                    <td width="12.5%" colspan="2" align="center">专属会员归属期</td>
                </tr>
                <tr style="background-color: rgba(68, 68, 68, 0.2)">
                    <td width="12.5%" align="center">开始日期</td>
                    <td width="12.5%" align="center">结束日期</td>
                    <td width="12.5%" align="center">开始日期</td>
                    <td width="12.5%" align="center">结束日期</td>
                </tr>
                <c:forEach items="${by121312Beans}" var="bean" varStatus="vs">
                    <tr>
                        <td width="12.5%" align="center">${vs.count}</td>
                        <td width="12.5%" align="center">${bean.houseName}</td>
                        <td width="12.5%" align="center">${bean.lockBeginTime}</td>
                        <td width="12.5%" align="center">${bean.lockEndTime}</td>
                        <td width="12.5%" align="center">${bean.excBeginTime}</td>
                        <td width="12.5%" align="center">${bean.excEndTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>
    </div>
</div>