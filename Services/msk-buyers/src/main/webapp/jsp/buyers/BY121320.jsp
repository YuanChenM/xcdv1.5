<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买家会员卡管控表" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>

 <div class="page-content list-page">
     <input type="hidden" id="buyerId"  name="buyerId" value="${buyerId}"/>
     <from>
         <div class="group-accordion" collapsible="true" active="true">

             <h3> 买家会员卡管控表</h3>
             <table width="100%">
                 <tr>
                     <td align="right">买家会员卡号 ：</td>
                     <td align="left"><input type="text" id="cardId" name=""/></td>
                     <td align="right">初始密码 ：</td>
                     <td align="left"><input type="text" id="cardPass" name=""/></td>
                 </tr>

                 <tr>
                     <td align="right">绑定手机号 ：</td>
                     <td align="left"><input type="text" id="tel" name=""/></td>
                     <td align="right">绑定微信号 ：</td>
                     <td align="left"><input type="text" id="weChat" name=""/></td>
                 </tr>

                 <tr>
                     <td align="right">绑定QQ号 ：</td>
                     <td><input type="text" id="qq" name=""/></td>
                     <td></td>
                     <td align="left"><msk:button buttonType="button" buttonId="BY121320.ADD" buttonValue="保存"/> </td>
                 </tr>

             </table>

         </div>
     </from>

 </div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121320.js"></script>