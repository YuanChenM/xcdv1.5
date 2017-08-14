<%--
  Created by IntelliJ IDEA.
  User: wang_shuai
  Date: 2016/8/3
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<input type="hidden" id="returnId" value="${returnId}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="returnCode" value="${returnCode}"/>

<navigation:header title="退货单详细" backTitleArray="退货单列表" backUrlArray="../SO151506/init"></navigation:header>
<div class="page-content list-page">
  <!--退货单基本信息  -->
  <div id="baseReturnOrder"></div>
  <!--退货人信息  -->
  <div id="returnBuyers"></div>
  <!-- 订单详情 -->
  <div class="group-accordion" active=false>
    <h3>
      <label>订单详情</label>
    </h3>
    <div>
      <table class="tree dataTable no-footer" style="width: 100% ">
        <tr style="background: #DBDBDB">
          <td width="9%">期望配送日</td>
          <td width="9%">产品编码</td>
          <td width="9%">产品名称</td>
          <td width="9%">产品规格</td>
          <td width="9%">包装规格</td>
          <td width="9%">单件体积(m³/箱)</td>
          <td width="9%">单价(元/箱)</td>
          <td width="9%">数量</td>
          <td width="9%">重量(kg)</td>
          <td width="9%">体积(m³)</td>
          <td width="9%">金额(元)</td>
        </tr>

        <!-- 循环退货订单中的所有商品数据 -->
        <c:forEach items="${detailList}" var="detailInfo">
          <!-- 显示品种类别 和详细信息 -->
          <tr>
            <%--<td><fmt:formatDate value="${detailInfo.proDate}" var="both" pattern="yyyy-MM-dd"/></td>--%>
            <td>${detailInfo.expectedDate}</td>
            <td>${detailInfo.pdCode}</td>
            <td>${detailInfo.pdName}</td>
            <td>${detailInfo.breedName}</td>
            <td>${detailInfo.featureName}</td>
            <td><fmt:formatNumber value="${detailInfo.packingVolume}" minFractionDigits="2" ></fmt:formatNumber></td>
            <td style="text-align: right"><fmt:formatNumber value="${detailInfo.pdPrice}" pattern="###,###" minFractionDigits="2" ></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${detailInfo.orderQty}" pattern="###,###"></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${detailInfo.weight}" minFractionDigits="2" ></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${detailInfo.volume}" minFractionDigits="2" ></fmt:formatNumber></td>
            <td style="text-align: right"><fmt:formatNumber value="${detailInfo.orderQty*detailInfo.pdPrice}" pattern="###,###" minFractionDigits="2" ></fmt:formatNumber></td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="11" align="right">金额合计：<fmt:formatNumber value="${orderAllMoney}" pattern="###,###" minFractionDigits="2" ></fmt:formatNumber></td>
        </tr>
      </table>
    </div>
  </div>

  <!--退货产品信息  -->
  <div class="group-accordion" active=true>
    <h3>
      <label>本次退货记录</label>
    </h3>
    <div>
      <table class="tree dataTable no-footer" style="width: 100% ">
        <tr style="background: #DBDBDB">
          <td width="10%">产品编码</td>
          <td width="10%">产品名称</td>
          <td width="10%">产品规格</td>
          <td width="10%">包装规格</td>
          <td width="10%">单件体积(m³/箱)</td>
          <td width="10%">单价(元/箱)</td>
          <td width="10%">退货数量</td>
          <td width="10%">重量(kg)</td>
          <td width="10%">体积(m³)</td>
        </tr>
        <c:forEach items="${returnGoodsList}" var="returnGoods2">
          <tr>
            <td>${returnGoods2.pdCode}</td>
            <td>${returnGoods2.pdName}</td>
            <td>${returnGoods2.breedName}</td>
            <td>${returnGoods2.featureName}</td>
            <td><fmt:formatNumber value="${returnGoods2.packingVolume}" minFractionDigits="2" ></fmt:formatNumber></td>
            <td style="text-align: right"><fmt:formatNumber value="${returnGoods2.pdPrice}" pattern="###,###" minFractionDigits="2" ></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${returnGoods2.returnQty}" pattern="###,###"></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${returnGoods2.weight}" minFractionDigits="2" ></fmt:formatNumber></td>
            <td><fmt:formatNumber value="${returnGoods2.volume}" minFractionDigits="2" ></fmt:formatNumber></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>
<script src="<c:url value="/static/js/order/SO151507.js" /> "></script>


