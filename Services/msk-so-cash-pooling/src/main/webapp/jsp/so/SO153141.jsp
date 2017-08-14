<%--
    Title:账套一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk" uri="/codemaster" %>
<msk:codemaster codeType="RoleType" viewType="json" />

<navigation:header title="账套一览" backTitleArray="" backUrlArray="${ctx}"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/SO153141/search" id="SO153141Form" method="post">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table WIDTH="100%">
                <tr>
                    <td  align="right">启用日期 :</td>
                    <td  align="left">　
                        <input type="text" id="commDateStart" name="filterMap['timeStart']" value="${param.filterMap.timeStart}"/>
                        &emsp;~&emsp;
                        <input type="text" id="commDateEnd" name="filterMap['timeEnd']" value="${param.filterMap.timeEnd}"/>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table id="SO153141_list_grid" WIDTH="100%">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="accountBookName" filter="true">账套名称</th>
                    <th coltype="text" name="userNo" filter="true">用户编号</th>
                    <th coltype="code" width="100px" filter="true" name="userRole" code2name="ROLETYPE_JSON">用户角色</th>
                    <!--<th coltype="datetime" name="commDate">启用日期</th>-->
                    <th coltype="money" name="unpaid">未付款</th>
                    <th coltype="money" name="unrecieved">未收款</th>
                    <th coltype="money" name="forRefund">待退款</th>
                    <th coltype="money" name="balance">余额</th>
                    <th coltype="action" width="2%">操作
                        <msk2:button buttonValue="期初调整" buttonId="SO153141.REDUCE" buttonType="hidden" coltype="edit" class="h-button"  useable="can_abolish" />
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

    </form>
</div>
<script src="${ctx}/static/so/js/SO153141.js"></script>
