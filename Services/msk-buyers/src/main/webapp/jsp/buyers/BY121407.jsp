<%@ taglib prefix="msk2" uri="/codemaster" %>
<%--
  菜场一览
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/7/8
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<msk2:codemaster codeType="MarketApproveStatus" viewType="json"/>
<msk2:codemaster codeType="MarketType" viewType="json"/>
<navigation:header title="菜场定性定级列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form action="${ctx}/BY121407/search" method="post" id="selectBuyerList">


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
          </td>

          <td width="10%" align="right">地区 : </td>
          <td width="15%">
            <select id="cityCode" name="filterMap['cityCode']">
              <option value="">--请选择--</option>
            </select>
            <input type="hidden" name="filterMap['cityName']" id="cityName" value="">
          </td>
          <td width="10%" align="right">市场类型 : </td>
          <td width="15%">
            <select id="marketNature" name="filterMap['marketNature']">
              <option value="">--请选择--</option>
              <c:forEach items="${marketTypeList}" var="mtl">
                <option value="${mtl.key}">${mtl.value}</option>
              </c:forEach>
            </select>
          </td>
          <td width="10%" align="right">菜场编码 : </td>
          <td width="15%"><input id="marketCode" name="filterMap['marketCode']" type="text"></td>
        </tr>
        <tr>
          <td  align="right">菜场名称 : </td>
          <td ><input id="marketName" name="filterMap['marketName']" type="text"></td>
          <td  align="right">菜场地址 : </td>
          <td><input id="marketAddr" name="filterMap['marketAddr']" type="text"></td>
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
          <td align="right"><msk:button buttonType="button" coltype="select"  buttonId="BY121407.SELECT"  buttonValue="查询"/></td>

        </tr>
      </table>
    </div>

    <div>
    <table id="BY121407_Grid" width="100%">
      <thead>
      <tr>
        <th hidden="true" name="marketId"></th>
        <th coltype="sno" >序号</th>
        <th coltype="text" name="lgcsAreaName" filter=false>物流区</th>
        <th coltype="text" name="cityName" filter=false>地区</th>
        <th coltype="text" name="districtName" filter=false>区县</th>
        <th coltype="code" name="marketNature" filter=false code2Name="MARKETTYPE_JSON">市场类型</th>
        <th coltype="text" name="sectionTypeName" filter=false>地段</th>
        <th coltype="text" name="marketName" filter=false>菜场名称</th>
        <th coltype="text" name="marketAddr" filter=false>菜场地址</th>
        <th coltype="text" name="marketCode" filter=false>菜场编码</th>
        <th coltype="text" name="merchantTotalNo" filter=false>总商户数(户)</th>
        <th coltype="text" name="targetBuyer" filter=false>目标买家总数(户)</th>
        <th coltype="money" name="targetAnnualTurnover" filter=false>年交易额(万元)</th>
        <th coltype="text" name="researchPhaseName" filter=false>调研阶段</th>
        <th coltype="code" name="marketStatus" code2Name="MARKETAPPROVESTATUS_JSON" filter=false>状态</th>
        <th coltype="action">网搜调研审批
          <msk:button buttonType="hidden" coltype="edit" buttonId="BY121407.EDIT"  buttonValue="编辑"/>
          <msk:button buttonType="hidden" coltype="repair" buttonId="BY121407.APPROVAL"  buttonValue="审批"/>
          <msk:button buttonType="hidden" coltype="detail" buttonId="BY121407.DETAIL"  buttonValue="文件上传"/>
          <msk:button buttonType="hidden" coltype="delete" buttonId="BY121407.DELETE"  buttonValue="删除"/>
        </th>
      </tr>
      </thead>
      <tbody></tbody>
    </table>
    </div>
  </form>
  <div><msk:button buttonType="button" coltype="save" buttonId="BY121407.SAVE"  buttonValue="新增"/></div>

</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121407.js"></script>