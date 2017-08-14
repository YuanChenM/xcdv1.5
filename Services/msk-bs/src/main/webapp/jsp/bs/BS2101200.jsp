<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <form action="${ctx}/BS2101200/findBuyerPoolListByBuyerId" method="post" id="bs2101200FormId">
        <div>
            <div class="group-accordion" active="true">
                <h3>
                    <label>买家池</label>
                </h3>
            </div>
        </div>
        <input type="hidden" id="buyerId" name="buyerId" value="${buyerId}">
        <div>
            <table id="bs2101200_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="text" name="buyerPoolName" >买家池名称</th>
                    <th coltype="text" name="buyerTypeName">买家类型名称</th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script src="${ctx}/static/bs/js/BS2101200.js"></script>
