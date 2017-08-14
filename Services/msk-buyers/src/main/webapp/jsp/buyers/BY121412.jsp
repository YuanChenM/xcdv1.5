<%--
  批发市场一览
  Created by IntelliJ IDEA.
  User: yuan_zhifei
  Date: 2016/7/13
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MarketResearchPhase" viewType="json"/>
<navigation:header title="菜场定性各阶段列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form action="${ctx}/BY121412/search" method="post">
      <table id="BY121412_list_grid" width="100%">
            <thead>
                <tr>
                    <th coltype="sno" rowspan="2">序号</th>
                    <th coltype="text" name="marketName" rowspan="2">菜场名称</th>
                    <th coltype="text" name="lgcsAreaName" rowspan="2">物流区域</th>
                    <th coltype="text" name="marketAddr" rowspan="2">菜场地址</th>
                    <%--Modify for Bug #2528 at 2016/09/07 by zhou_yajun Start--%>
                    <%--<th coltype="code" name="researchPhase" code2name="MARKETRESEARCHPHASE_JSON" rowspan="2">当前调研阶段</th>--%>
                    <th coltype="code" name="researchPhaseName" rowspan="2">当前调研阶段</th>
                    <%--Modify for Bug #2528 at 2016/09/07 by zhou_yajun End--%>
                    <th colspan="2">网搜阶段</th>
                    <th colspan="2">先期调研阶段</th>
                    <th colspan="2">现场稽核阶段</th>
                </tr>
                <tr>
                    <th coltype="text" name="netMarketNatureName">定性</th>
                    <th coltype="text" name="netRadiationRangeTypeName">辐射范围</th>
                    <th coltype="text" name="advanceMarketNatureName">定性</th>
                    <th coltype="text" name="advanceRadiationRangeTypeName">辐射范围</th>
                    <th coltype="text" name="auditMarketNatureName">定性</th>
                    <th coltype="text" name="auditRadiationRangeTypeName">辐射范围</th>
                </tr>
            </thead>
          <tbody></tbody>
      </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121412.js"></script>