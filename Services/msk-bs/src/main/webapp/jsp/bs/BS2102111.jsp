<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="HkGradeCode" viewType="json"/>
<navigation:header title="定级管理" backTitleArray="冻品管家一览,冻品管家管控" backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init?slCode=${slCode}&houseCode=${houseCode}"></navigation:header>
<div class="page-content list-page">
  <div class="group-accordion" active="true">
    <h3>
      <label>定级管理</label>
    </h3>

      <form:form action="${ctx}/BS2102111/search" id="BS2102111Form" method="post" enctype="multipart/form-data">
        <input name="slCode" id="slCode" value="${slCode}" type="hidden" />
        <input name="houseCode" id="houseCode" value="${houseCode}" type="hidden" />
        <div>
          <table id="bs2102111_list_grid" width="100%">
            <thead>
            <tr>
              <th coltype="checkbox" name="checkFlag"></th>
              <th coltype="sno" width="20px">编号</th>
              <th coltype="text" filter="false" name="houseShowName">冻品管家名称</th>
              <th coltype="text" filter="false" name="houseCode">冻品管家ID</th>
              <th coltype="text" filter="false" name="lgcsAreaName">物流区</th>
              <th coltype="text" filter="false" name="houseCodeDis">冻品管家编码</th>
              <th coltype="text" filter="false"  name="houseReclassifyName">冻品管家分类</th>
              <th coltype="code" width="10%" cellEdit="false" name="gradeCode" edit="true" code2name="HKGRADECODE_JSON" >分类对应等级
              </th>
             <%-- <th coltype="text" filter="false" edit="true"  name="gradeCode">冻品管家分类</th>--%>
            </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </form:form>
  </div>
  <div style="float: left;">
    <msk:button buttonType="button" buttonValue="保存" buttonId="BS2102111.SAVE"></msk:button>
  </div>
</div>
<script src="${ctx}/static/bs/js/BS2102111.js"></script>
