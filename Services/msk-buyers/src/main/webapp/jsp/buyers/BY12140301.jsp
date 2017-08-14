<%--
  Created by IntelliJ IDEA.
  User: guan_zhongheng
  Date: 2016/8/11
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MarketApproveStatus" viewType="json"/>
<div class="page-content list-page">
  <form action="${ctx}/BY121301/search" method="post">
    <table id="BY12140301_Grid" width="100%">
      <thead>
      <tr>
        <th hidden="true" name="marketId"></th>
        <th coltype="checkbox" name="checkInfo"></th>
        <th coltype="sno" >序号</th>
        <th coltype="text" name="marketName" filter=true width="20%">批发市场名称</th>
        <th coltype="text" name="marketAddr" width="20%">批发市场地址</th>
        <th coltype="text" name="lgcsAreaName" width="20%">物流区</th>
        <th coltype="text" name="cityName" width="20%">地区</th>
        <th coltype="action"></th>
      </tr>
      </thead>
      <tbody></tbody>
    </table>
  </form>
  <msk:button buttonType="button" buttonId="BY12140301.CONFIRM" buttonValue="确认"/>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12140301.js"></script>