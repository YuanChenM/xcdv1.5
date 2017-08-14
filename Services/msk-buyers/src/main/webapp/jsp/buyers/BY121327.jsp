<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/msk" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="BuyerRegisterWay" viewType="json"/>
<navigation:header title="买家账号信息列表" backTitleArray="买家列表" backUrlArray="${ctx}/BY121303/init/"></navigation:header>
<div class="page-content detail-page">
    <div>
        <form action="${ctx}/BY121327/search" id="BY121327Form" method="post">
            <table width="100%" id="BY121327_list_grid">
                <thead>
                <tr>
                    <th coltype="sno"  >编号</th>
                    <th coltype="text"  name="accountName" filter=true>帐号名称</th>
                    <th coltype="code" width="12.5%" filter="true"  name="registerSource" code2name="BUYERREGISTERWAY_JSON" >注册来源</th>
                    <th coltype="text"  name="telNo" filter=true>手机号码</th>
                    <th coltype="text" name="buyerCode" filter=true>买家编码</th>
                    <th coltype="text" name="buyerName" filter=true>买家名称</th>

                    <th coltype="text" name="lgcsAreaName" >物流区名称</th>
                    <th coltype="text" name="cityName">地区（城市）名称</th>
                    <th coltype="text" name="districtName">区（县）名称</th>
                    <th coltype="text" name="superiorName">买家分类名称</th>
                    <th coltype="text"  name="superiorSubName">买家二级分类名称</th>
<%--                    <th coltype="action" align="center">操作
                    </th>--%>
                </tr>
                </thead>
                <tbody id="BR121327Tbody">
                </tbody>
            </table>
        </form>
</div>
    </div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121327.js"></script>