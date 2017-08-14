<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/5/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="需求等级编辑" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
  <form:form action="${ctx}/SP171192/searchDetail" id="SP171192Form" method="post">
    <div>
    <table id="SP171192_Grid">
      <thead>
      <tr>
        <th coltype="text"  name="wayCode" style="text-align: center">需求等级编号</th>
        <th coltype="text"  name="wayName" style="text-align: center">需求等级</th>
        <th coltype="text" name="supOrder" style="text-align: center">大宗超级</th>
        <th coltype="text" name="order1" style="text-align: center">大宗1级</th>
        <th coltype="text" name="order2" style="text-align: center">大宗2级</th>
        <th coltype="text" name="order3" style="text-align: center">大额3级</th>
        <th coltype="text" name="order4" style="text-align: center">大额4级</th>
        <th coltype="text" name="order5" style="text-align: center">大额5级</th>
        <th coltype="text" name="order6" style="text-align: center">大额6级</th>
        <th coltype="text" name="order7" style="text-align: center">标准7级</th>
        <th coltype="text" name="order8" style="text-align: center">标准8级</th>
        <th coltype="text" name="order9" style="text-align: center">标准9级</th>
        <th coltype="action" width="60px" style="text-align: center">操作
          <msk:button buttonType="hidden" buttonId="SP171192.EDIT" buttonValue="编辑" coltype="edit"/>
          <msk:button buttonType="hidden" buttonId="SP171192.CLOSE" buttonValue="删除" coltype="close"/>
        </th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    </div>
  </form:form>
  <msk:button buttonType="button" buttonId="SP171192.CONFIRM" buttonValue="添加"/>
</div>

<script src="${ctx}/static/sp/js/SP171192.js"></script>
