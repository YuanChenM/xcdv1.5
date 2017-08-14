<%--
    Title:模拟产品入库
    author:liuyan
    createDate:2016-07-14
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<navigation:header title="模拟产品入库" backTitleArray="" backUrlArray=""></navigation:header>
<form action='<c:url value="/SSC11317/search"/>' id="SSC11317Form" method="post">
  <input type="hidden" id="deliveryPreIntoCode" name="deliveryPreIntoCode" value="${deliveryPreIntoCode}">
  <input type="hidden" id="deliveryPreIntoId" name="deliveryPreIntoId" value="${deliveryPreIntoId}">
  <input type="hidden" id="ver" name="ver" value="${ver}">
<div class="page-content list-page">
  <div>
    <table style="width: 100%;">
      <tr>
        <td align="left" width="200px">入库单号:</td>
        <td align="left">
           <input type="text" name="deliveryStockId" id="deliveryStockId" value="${deliveryPreIntoCode}" readonly/>
        </td>
      </tr>

      <tr>
        <td align="left" width="200px">(必填)实际入库时间:yyyy-mm-dd hh:mm:ss</td>
        <td align="left">
            <input type="text" id="inputDate" name="inputDate" value=""/>
        </td>

      </tr>

      <tr>
        <td align="left" width="200px">收货备注:</td>
        <td align="left"><input type="text" id="stockMemo" name="stockMemo" maxlength="100"/></td>
      </tr>
    </table>
  </div>
    <div class="group-accordion" collapsible="true" active="true">
      <h3>
        产品明细
      </h3>
      <table id="SSC1131701_list_grid" width="100%">
        <thead>
        <tr>
          <th coltype="sno">序号</th>
          <th coltype="text" name="pdCode">产品编码</th>
          <th coltype="text" name="pdName">产品名称</th>
          <th coltype="number" name="productPlanBox">计划到货（箱）</th>
          <th coltype="number" name="productRecvBox" edit="true" width="200px">实际到货（箱）</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  <msk:button buttonType="button" buttonId="SSC1131701.SAVE" buttonValue="确认入库"/>
</div>
</form>
<script src="<c:url value="/static/js/ssc/SSC1131701.js"/>"></script>
