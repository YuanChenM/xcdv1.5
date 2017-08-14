<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<input type="hidden" id="buyerId" value="${buyerId}">
<input type="hidden" id="telNo" value="${buyerAccount.telNo}">
<input type="hidden" id="accountName" value="${buyerAccount.accountName}">
<input type="hidden" id="accountPass" value="${buyerAccount.accountPass}">
<navigation:header title="买家详细信息" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
    <%--<div id="baseBuyerBasicInfo"></div>--%>
    <jsp:include page="/by/baseBuyerBasicInfo/init/${buyerId}"/>
    <%--<div id="baseBuyerRecInfo"></div>--%>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>买家雇员信息</label>
        </h3>
        <form action="${ctx}/BY121304/search/${buyerId}" method="post">
            <table id="BY121304_Grid">
                <thead>
                <tr>
                    <th coltype="sno" width="20px">编号</th>
                    <th coltype="text" name="employeeTypeName" filter=false>员工类型</th>
                    <th coltype="text" name="employeeName" filter=true>员工姓名</th>
                    <th coltype="text" name="employeeTel" filter=true>手机号</th>
                    <th coltype="text" name="employeeQq" filter=true>QQ号</th>
                    <th coltype="text" name="employeeWechat" filter=true>微信号</th>
                    <th coltype="text" name="busCardFlgName" filter=false>有无名片照</th>
                    <th coltype="text" name="contactPersonName" filter=false>是否联络人</th>
                    <th coltype="text" name="purchaseName" filter=false>是否采购人</th>
                    <th coltype="text" name="receivePersonName" filter=false>是否收货人</th>
                    <th coltype="action">
                        <%--<input type="hidden"coltype="edit" title="编辑" class="h-button" />
                        <input type="hidden"coltype="delete" title="删除" class="h-button" />--%>
                        <msk:button buttonType="hidden" coltype="edit" buttonId="BY121304.EDIT"  buttonValue="编辑"/>
                        <msk:button buttonType="hidden" coltype="delete" buttonId="BY121304.DELETE"  buttonValue="删除"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <msk:button buttonValue="新增" buttonId="BY121304.ADD" buttonType="button"/>
        </form>
    </div>
    <jsp:include page="/by/baseBuyerLicInfo/init/${buyerId}"/>
    <%--<div id="baseBuyerLicInfo"></div>--%>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121304.js"></script>