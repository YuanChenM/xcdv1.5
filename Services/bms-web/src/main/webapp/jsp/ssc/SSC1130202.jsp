<%--
    Title:产品质量加工标准页面
    author:liu_yan2
    createDate:2016-08-02
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ include file="/common/taglib.jsp" %>

<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/treegrid/js/jquery.treegrid.js"></script>--%>
<div class="page-content list-page">
    <div class="group-accordion" active="true" collapsible="true">
        <h3>
            <label>产品加工技术标准指标</label>
        </h3>
        <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background:#DBDBDB">
                <td align="center"></td>
                <td>合格值</td>
            </tr>
            <c:forEach items="${mctStdList}" var="mctBean" varStatus="i">
                <tr>
                    <td>${mctBean.mctStdItemName}</td>
                    <td style="white-space:pre;">${mctBean.mctStdVal1}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="group-accordion"  active="true" collapsible="true">
        <h3>
            <label>产品加工质量标准指标</label>
        </h3>
        <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background:#DBDBDB">
                <td align="center"></td>
                <td>A2</td>
            </tr>
            <c:forEach items="${tncStdList}" var="tncBean" varStatus="i">
                <tr>
                    <td>${tncBean.tncStdItemName}</td>
                    <td style="white-space:pre;">${tncBean.tncStdVal2}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="group-accordion" active="true" collapsible="true">
        <h3>
            <label>通用质量标准</label>
        </h3>
        <table class="tree dataTable no-footer" style="width: 100%">
            <tr style="background:#DBDBDB">
                <td align="center"></td>
                <td>合格</td>
            </tr>
            <c:forEach items="${gnqStdList}" var="listParent" varStatus="i">
                <tr style="background-color:#F8F8FF; font-weight: bold;">
                    <td colspan="2">${listParent.gnqStdClaName}</td>
                </tr>
                <c:forEach items="${listParent.gnqStdSublist}" var="gnqBean" varStatus="j">
                    <tr>
                        <td>${gnqBean.gnqStdItemName}</td>
                        <td style="white-space:pre;">${gnqBean.okVal}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </div>

    <%--<div class="group-accordion" active="false" collapsible="true">--%>
        <%--<h3>--%>
            <%--<label>通用质量标准</label>--%>
        <%--</h3>--%>
        <%--<table class="tree dataTable no-footer" style="width: 100%">--%>
            <%--<tr style="background:#DBDBDB">--%>
                <%--<td align="center"></td>--%>
                <%--<td>合格</td>--%>
            <%--</tr>--%>
            <%--<c:forEach items="${gnqStdList}" var="listParent" varStatus="i">--%>
                <%--<tr class="treegrid-${listParent.gnqStdClaId}" style="background-color:#F8F8FF">--%>
                    <%--<td>${listParent.gnqStdClaName}</td>--%>
                    <%--<td align="center"></td>--%>
                <%--</tr>--%>
                <%--<c:forEach items="${listParent.gnqStdSublist}" var="gnqBean" varStatus="j">--%>
                    <%--<tr class="treegrid-${gnqBean.gnqStdItemId} treegrid-parent-${listParent.gnqStdClaId}">--%>
                        <%--<td>${gnqBean.gnqStdItemName}</td>--%>
                        <%--<td style="white-space:pre;">${gnqBean.okVal}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
            <%--</c:forEach>--%>
        <%--</table>--%>
    <%--</div>--%>
</div>