<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/5/20
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
  <form action="${ctx}/SP17119201/init" id="SP17119201Form" method="post">
    <input id="wayCode"  type="hidden"  name="wayCode" value="${wayCode}">
    <table class="dataTable" cellspacing="0" cellpadding="0" border="0" style="width: 100%;">
      <tr width="100%">
       <%-- <td style="background:#DBDBDB;text-align: center" width="50%">通道标准编号</td>
        <td style="background:#DBDBDB;text-align: center"><input id="wayCode" style="width: 35%" readOnly="true" type="text"  name="wayCode" value="${wayCode}"></td>--%>
        <td style="background:#DBDBDB;text-align: center" width="50%">需求等级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="wayName" style="width: 35%" type="text" name="wayName" value="${wayName}"></td>
        <td style="background:#DBDBDB;text-align: center" width="20%">通道平衡系数(%)</td>
      </tr>
    <%--  <tr   width="100%">
        <td style="background:#DBDBDB;text-align: center" width="50%">通道标准名称</td>
        <td style="background:#DBDBDB;text-align: center"><input id="wayName" style="width: 35%" type="text" name="wayName" value="${wayName}"></td>
        <td style="background:#DBDBDB;text-align: center">&nbsp;</td>
      </tr>--%>
      <tr>
        <td style="background:#DBDBDB;text-align: center">超级</td>
        <td style="background:#DBDBDB;text-align: center">≥<input id="startSup" style="width: 31.5%" type="text" maxlength="6" name="wayStartNum" value="${startSup}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance0" maxlength="6" style="width: 35%" type="text" value="${supBlance}"></td>
        </td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">1级</td>

        <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start-->
        <td style="background:#DBDBDB;text-align: center"><input id="start1" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start1}">-<input id="end1" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end1}"></td>
        <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang End-->
        <td style="background:#DBDBDB;text-align: center"><input id="balance1" style="width: 35%" maxlength="6" type="text" value="${blance1}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">2级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start2" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start2}">-<input id="end2" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end2}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance2" style="width: 35%" maxlength="6" type="text" value="${blance2}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">3级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start3" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start3}">-<input id="end3" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end3}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance3" style="width: 35%"  maxlength="6" type="text" value="${blance3}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">4级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start4" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start4}">-<input id="end4" maxlength="6"  style="width: 35%" type="text" name="wayEndNum" value="${end4}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance4" style="width: 35%"  maxlength="6" type="text" value="${blance4}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">5级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start5" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start5}">-<input id="end5" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end5}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance5" style="width: 35%" maxlength="6" type="text" value="${blance5}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">6级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start6" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start6}">-<input id="end6" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end6}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance6" style="width: 35%" maxlength="6" type="text" value="${blance6}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">7级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start7" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start7}">-<input id="end7" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end7}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance7" style="width: 35%" maxlength="6" type="text" value="${blance7}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">8级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start8" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start8}">-<input id="end8" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end8}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance8" style="width: 35%" maxlength="6" type="text" value="${blance8}"></td>
      </tr>
      <tr>
        <td style="background:#DBDBDB;text-align: center">9级</td>
        <td style="background:#DBDBDB;text-align: center"><input id="start9" style="width: 35%" maxlength="6" type="text" name="wayStartNum" value="${start9}">-<input id="end9" maxlength="6" style="width: 35%" type="text" name="wayEndNum" value="${end9}"></td>
        <td style="background:#DBDBDB;text-align: center"><input id="balance9" style="width: 35%" maxlength="6" type="text" value="${blance9}"></td>
      </tr>
      <tr>
        <td>
          <input type="hidden" name="maxCode" id="maxCode" value="${maxCode}">
          <input type="hidden" name="flag" id="flag" value="${flag}">
          <input type="hidden" name="saveOrUpFlag" id="saveOrUpFlag" value="${saveOrUpFlag}">
        </td>
      </tr>

    </table>

  </form>
  <msk:button buttonType="button" buttonId="SP17119201.CONFIRM" buttonValue="保存"/>
</div>

<script src="${ctx}/static/sp/js/SP17119201.js"></script>