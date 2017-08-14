<%--
  Created by IntelliJ IDEA.
  User: gyh
  Date: 2016.1.7
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<%@ taglib prefix="msktag" uri="/codemaster" %>
<msktag:codemaster codeType="YESNO" viewType="json"/>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<style>
    td.details-control {
        background: url('${ctx}/static/images/action/details_open.png') no-repeat center center;
        cursor: pointer;
    }
    tr.details td.details-control {
        background: url('${ctx}/static/images/action/details_close.png') no-repeat center center;
    }
</style>
<script>
    var fileSerUrl='${fileSerUrl}';
    var isDebug="${isDebug}"
</script>
<div class="page-content list-page">
    <form id="searchForm" action="${ctx}/SL241105/search/${isFeature}" method="post">
        <input type="hidden" name="filterMap[slCode]" value="${slCode}">
        <input type="hidden" name="filterMap[classesCode]" value="${classesCode}">
        <input type="hidden" name="filterMap[machiningCode]" value="${machiningCode}">
        <input type="hidden" name="filterMap[breedCode]" value="${breedCode}">
        <input type="hidden" name="filterMap[prodEpId]" value="${prodEpId}">
        <input type="hidden" name="filterMap[brandEpId]" value="${brandEpId}">
        <input type="hidden" name="filterMap[brandId]" value="${brandId}">
        <table id="SL24110501_Grid" >
            <thead>
                <tr>
                    <th coltype="text"  align="center" width="10px"></th>
                    <th coltype="text" name="featureName" align="center">特征</th>
                    <th coltype="code" name="distFlg" align="center" code2Name="YESNO_JSON">是否参与分销</th>
                    <th coltype="text" name="slQltGradeName" align="center">加工技术等级</th>
                    <%--<th coltype="text" name="distFlg" align="center">图片</th>--%>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </form>
    <msk2:button buttonType="button" buttonId="SL24110501.BACK" buttonValue="取消"/>
    <%--<msk:button buttonValue="取消" buttonType="button" buttonId="SL24110501.BACK"/>--%>
    <div id="reasonDiv"></div>
</div>
<script src="${ctx}/static/sl/js/SL24110501.js"></script>
