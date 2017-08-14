<%--
  Created by IntelliJ IDEA.
  User: zhu_kai1
  Date: 2016/6/7
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
  <form:form  id="sellerFrom" metdod="post">
    <div class="group-accordion">
      <h3>
        <label>入围卖家上半月销售额及百分比</label>
      </h3>
      <table id="seller_list_grid" width="100%"  class="dataTable no-footer">
        <thead>
        <tr>
          <td style="text-align: center;padding-right: 20px;" class="sorting_disabled">供应商</td>
          <td style="text-align: center;padding-right: 20px;" class="sorting_disabled">销售额</td>
          <td style="text-align: center;padding-right: 20px;" class="sorting_disabled">百分比</td>
        </tr>
        </thead>
        <tbody>
        <c:choose>
          <c:when test="${(sellerList) ==null || fn:length(sellerList)==0}">
            <tr>
              <td style="text-align: center;padding-right: 20px;" colspan="3">没有找到对应的数据</td>
            </tr>
          </c:when>
          <c:otherwise>
            <c:forEach items="${sellerList}" var="seller">
              <tr>
                <td style="text-align: center;padding-right: 20px;">${seller.epName}</td>
                <td style="text-align: center;padding-right: 20px;">${seller.orderCount}</td>
                <td style="text-align: center;padding-right: 20px;">${seller.percent*100}%</td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
        </tbody>
      </table>
    </div>
  </form:form>
</div>

