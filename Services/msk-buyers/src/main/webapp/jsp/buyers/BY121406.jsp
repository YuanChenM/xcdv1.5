<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MarketResearchPhase" viewType="json"/>
<msk2:codemaster codeType="TerminalMarketNature" viewType="json"/>
<navigation:header title="批发市场定性定级各阶段列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form  action="${ctx}/BY121406/search" method="post" id="selectBuyerList">
      <div class="group-accordion" collapsible="true" active="true">
          <h3>
              <label>查询条件</label>
          </h3>
          <table width="100%">
              <tr>
                  <td width="10%" align="right">批发市场名称 : </td>
                  <td width="15%"><input id="marketName" name="filterMap['marketName']" type="text"></td>
                  <td width="10%" align="right">批发市场地址 : </td>
                  <td width="15%"><input id="marketAddr" name="filterMap['marketAddr']" type="text"></td>
                  <td width="10%" align="right">物流区域 : </td>
                  <td width="15%">
                      <select id="lgcsAreaCode" name="filterMap['lgcsAreaCode']">
                          <option value="">--请选择--</option>
                          <c:forEach items="${logisticsAreaList}" var="lal">
                              <option value="${lal.lgcsAreaCode}">${lal.lgcsAreaName}</option>
                          </c:forEach>
                      </select>
                      <input type="hidden" name="filterMap['lgcsAreaName']" id="lgcsAreaName" value="">
                  </td>
                  <td width="10%" align="right">当前调研阶段 : </td>
                  <td width="15%">
                      <select id="researchPhase" name="filterMap['researchPhase']">
                        <option value="">--请选择--</option>
                        <c:forEach items="${researchPhaseList}" var="rp">
                            <option value="${rp.key}">${rp.value}</option>
                        </c:forEach>
                  </select>
                  </td>
              </tr>
              <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td align="right"><msk:button buttonType="button" coltype="select"  buttonId="BY121406.SELECT"  buttonValue="查询"/></td>
              </tr>
            </table>
          </div>

      <div>
      <table id="BY121406_list_grid" width="100%">
            <thead>
                <tr>
                    <th coltype="sno" rowspan="2">序号</th>
                    <th coltype="text" name="marketName" rowspan="2" filter=false>批发市场名称</th>
                    <th coltype="text" name="lgcsAreaName" rowspan="2" filter=false>物流区域</th>
                    <th coltype="text" name="marketAddr" rowspan="2" filter=false>批发市场地址</th>
                    <th coltype="code" name="researchPhase" code2name="MARKETRESEARCHPHASE_JSON" rowspan="2" filter=false>当前调研阶段</th>
                    <th colspan="3">网搜阶段</th>
                    <th colspan="3">先期调研阶段</th>
                    <th colspan="3">现场稽核阶段</th>
                </tr>
                <tr>
                    <th coltype="code" width="120px" name="netMarketNature" code2name="TERMINALMARKETNATURE_JSON" cellEditMode="true" edit="true">定性
                        <msk2:codemaster codeType="TerminalMarketNature"  id="netMarketNature" name="netMarketNature" viewType="select" style="width:135px;display:none;"/>
                    </th>
                    <th coltype="text" width="120px" name="netMarketLevelName"  cellEditMode="true" edit="true" maxlength="100" >定级</th>
                    <th coltype="text" width="120px" name="netRadiationRangeTypeName"  cellEditMode="true" edit="true"  maxlength="20" >辐射范围</th>
                    <th coltype="code" name="advanceMarketNature" code2name="TERMINALMARKETNATURE_JSON">定性</th>
                    <th coltype="text" name="advanceMarketLevelName">定级</th>
                    <th coltype="text" name="advanceRadiationRangeTypeName">辐射范围</th>
                    <th coltype="code" name="auditMarketNature" code2name="TERMINALMARKETNATURE_JSON">定性</th>
                    <th coltype="text" name="auditMarketLevelName">定级</th>
                    <th coltype="text" name="auditRadiationRangeTypeName">辐射范围</th>
                </tr>
            </thead>
          <tbody></tbody>
      </table>
      </div>
      <msk:button buttonValue="保存" buttonId="BY121406.SAVENETMARKET" buttonType="button"/>
  </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121406.js"></script>