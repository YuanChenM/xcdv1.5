<%--
  批发市场一览
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/7/7
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MarketApproveStatus" viewType="json"/>
<navigation:header title="批发市场定性定级列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form action="${ctx}/BY121401/search" method="post" id="selectBuyerList">
      <div class="group-accordion" collapsible="true" active="true">
          <h3>
              <label>查询条件</label>
          </h3>
          <table width="100%">
              <tr>
                  <td width="10%" align="right">物流区 : </td>
                  <td width="15%">
                      <select id="lgcsAreaCode" name="filterMap['lgcsAreaCode']" >
                          <option value="">--请选择--</option>
                          <c:forEach items="${logisticsAreaList}" var="lal">
                              <option value="${lal.lgcsAreaCode}">${lal.lgcsAreaName}</option>
                          </c:forEach>
                      </select>
                      <input type="hidden" name="filterMap['lgcsAreaName']" id="lgcsAreaName" value="">
                  </td >
                  <td width="10%" align="right">地区 : </td>
                  <td width="15%" >
                      <select id="cityCode" name="filterMap['cityCode']">
                          <option value="">--请选择--</option>
                      </select>
                      <input type="hidden" name="filterMap['cityName']" id="cityName" value="">
                  </td>
                  <td width="10%" align="right"></td>
                  <td width="15%"></td>
                  <td width="10%" align="right"></td>
                  <td width="15%"></td>
              </tr>
              <tr>
                  <td  align="right">批发市场名称 : </td>
                  <td ><input id="marketName" name="filterMap['marketName']" type="text"></td>
                  <td  align="right">批发市场地址 : </td>
                  <td ><input id="marketAddr" name="filterMap['marketAddr']" type="text"></td>
                  <td align="right">批发市场编码 : </td>
                  <td><input id="marketCode" name="filterMap['marketCode']" type="text"></td>
                  <td align="right">状态 : </td>
                  <td>
                      <select id="marketStatus" name="filterMap['marketStatus']">
                          <option value="">--请选择--</option>
                          <c:forEach items="${marketApproveStatusList}" var="masl">
                              <option value="${masl.key}">${masl.value}</option>
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
                  <td align="right"><msk:button buttonType="button" coltype="select"  buttonId="BY121401.SELECT"  buttonValue="查询"/></td>

              </tr>
          </table>
      </div>
      <div>
      <table id="BY121401_Grid" width="100%">
            <thead>
                <tr>
                    <th hidden="true" name="marketId"></th>
                    <th coltype="sno" >序号</th>
                    <th coltype="text" name="lgcsAreaName" filter=false>物流区</th>
                    <th coltype="text" name="cityName" filter=false>地区</th>
                    <th coltype="text" name="marketName" filter=false>批发市场名称</th>
                    <th coltype="text" name="marketAddr" filter=false>批发市场地址</th>
                    <th coltype="text" name="marketCode" filter=false>批发市场编码</th>
                    <th coltype="text" name="marketLevelName" filter=false>批发市场等级</th>
                    <th coltype="text" name="targetBuyer" filter=false>目标买家总数(户)</th>
                    <th coltype="money" name="targetAnnualTurnover" filter=false>年交易额(万元)</th>
                    <th coltype="text" name="researchPhaseName" filter=false>调研阶段</th>
                    <th coltype="code" name="marketStatus" code2Name="MARKETAPPROVESTATUS_JSON" filter=false>状态</th>
                    <th coltype="action">网搜调研审批
                        <msk:button buttonType="hidden" coltype="search" buttonId="BY121401.SEARCH"  buttonValue="调查详情"/>
                        <msk:button buttonType="hidden" coltype="edit" buttonId="BY121401.EDIT"  buttonValue="编辑"/>
                        <msk:button buttonType="hidden" coltype="repair" buttonId="BY121401.APPROVAL"  buttonValue="审批"/>
                        <msk:button buttonType="hidden" coltype="detail" buttonId="BY121401.DETAIL"  buttonValue="文件上传"/>
                        <msk:button buttonType="hidden" coltype="delete" buttonId="BY121401.DELETE"  buttonValue="删除"/>
                    </th>
                </tr>
            </thead>
          <tbody></tbody>
      </table>
      </div>
  </form>
    <div><msk:button buttonType="button" coltype="save" buttonId="BY121401.SAVE"  buttonValue="新增"/></div>

</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121401.js"></script>