<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/5/17
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
  <form action="${ctx}/SP171193/searchDetail" id="SP171193Form" method="post">

    <!-- <input type="hidden" id="salePlatform" name="salePlatform" value="${productStock.salePlatform}"/> -->


    <table id="SP171193Grid">
      <thead>
      <tr>
        <th coltype="radio">选择</th>

        <th coltype="text"  name="wayCode">需求等级编号</th>
        <th coltype="text"  name="wayName">需求等级</th>
        <th coltype="number" name="supOrder">大宗超级</th>
        <th coltype="number" name="order1">大宗1级</th>
        <th coltype="number" name="order2">大宗2级</th>
        <th coltype="number" name="order3">大额3级</th>
        <th coltype="number" name="order4">大额4级</th>
        <th coltype="number" name="order5">大额5级</th>
        <th coltype="number" name="order6">大额6级</th>
        <th coltype="number" name="order7">标准7级</th>
        <th coltype="number" name="order8">标准8级</th>
        <th coltype="number" name="order9">标准9级</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </form>
  <msk:button buttonType="button" buttonId="SP171193.CONFIRM" buttonValue="确认"/>
</div>

<script src="${ctx}/static/sp/js/SP171193.js"></script>
