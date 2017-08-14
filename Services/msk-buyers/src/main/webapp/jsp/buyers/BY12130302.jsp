<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form action="${ctx}/BY12130302/search" method="post">
        <input type="hidden" value="${buyerId}" name="buyerId"/>
        <table id="BY12130302_Grid">
            <thead>
            <c:choose>
                <c:when test="${applyStatus eq '1'}">
                    <th coltype="sno" align="center">编号</th>
                    <th coltype="text" name="houseShowName" align="center" >冻品管家</th>
                    <th coltype="datetime" name="startTime" align="center" >营销所属期(营销开始日期)</th>
                    <th coltype="datetime" name="endTime" align="center" >营销所属期(营销结束日期)</th>
                </c:when>
                <c:otherwise>
                    <th coltype="sno" align="center">编号</th>
                    <th coltype="text" name="houseShowName" align="center">冻品管家</th>
                    <th coltype="text" name="developmentWay" align="center" >专属会员发展方式</th>
                    <th coltype="datetime" name="startTime" align="center" >专属会员归属期(销售开始日期)</th>
                    <th coltype="datetime" name="endTime" align="center"  >专属会员归属期(销售结束日期)</th>
                    <th coltype="text" name="changeReason" align="center" >冻品管家变更原因</th>
                </c:otherwise>
            </c:choose>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY12130302.js"></script>