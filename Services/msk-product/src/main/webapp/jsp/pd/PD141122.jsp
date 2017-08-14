<%--
    Title:物流区选择
    author:yuan_chen
    createDate:2016-1-27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/static/js/raphael/raphael.js"></script>
<script type="text/javascript" src="${ctx}/static/js/raphael/chinamapPath.js"></script>
<div class="page-content detail-page" style="width: 650px">
    <div class="group-accordion" collapsible="false" active="false">
        <h3>
            <label>物流区选择</label>
        </h3>
    <form:form action="${ctx}PD141122/init" id="PD141122Form" method="post">
        <div id="map"></div>
    </form:form>
</div>
<script src="${ctx}/static/js/pd/PD141122.js"></script>