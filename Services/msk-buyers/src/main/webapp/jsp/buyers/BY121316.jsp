<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家商城账号信息" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>
<div class="page-content list-page">
        <div>
        <form action="${ctx}/BY121316/search/${buyerId}" id="BY121316Form" method="post">
           <table width="100%" id="BY121316_list_grid">
               <thead>
               <tr>
                   <th coltype="sno" align="center">编号</th>
                   <th coltype="text" name="accountName"  filter="true" align="center">买家登录账号</th>
                   <th coltype="text" name="accountPass"  filter="true" align="center">登录密码</th>
                   <th coltype="text" name="buyerName" filter="true" align="center">账号使用人</th>
                   <th coltype="text" name="telNo"  filter="true"  align="center">绑定手机号</th>
                   <th coltype="text" name="buyerSingleWechat"  filter="true" align="center">绑定微信号</th>
                   <th coltype="text" name="buyerQq"  filter="true" align="center">绑定QQ号</th>
                   <th coltype="action" width="12%"  align="center">
                     <%--  <msk:button buttonValue="保存" buttonId="BY121316.save" buttonType="hidden" coltype="save" class="h-button"/>
                       <msk:button buttonValue="删除" buttonId="BY121316.delete" buttonType="hidden" coltype="delete" class="h-button"/>--%>
                   </th>
               </tr>
               </thead>
               <tbody id="BY121316Tbody">
               </tbody>
           </table>
        </form>
       </div>
</div>

<script type="text/javascript" src="${ctx}/static/buyers/js/BY121316.js"></script>