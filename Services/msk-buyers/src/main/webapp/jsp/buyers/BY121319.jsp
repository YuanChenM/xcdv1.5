<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title=" 买家营销工具管控" backTitleArray="买家列表,买家信息总控画面" backUrlArray="${ctx}/BY121303/init/,${ctx}/BY121313/init/${buyerId}"></navigation:header>

 <div class="page-content list-page">
     <input type="hidden" id="buyerId"  name="buyerId" value="${buyerId}"/>
     <form action="${ctx}/BY121319/search/${buyerId}" id="BY121319Form" method="post">
         <div class="group-accordion" collapsible="true" active="true">
             <h3>
                 <label> 买家营销工具管控表</label>
             </h3>
             <table width="100%">
                 <tr>
                     <td width="12.5%" height="50px">买家有效营销工具(可多选) ： </td>
                     <td ></td>
                 </tr>
                 <tr>
             <c:forEach items="${toolBeanList}" var="tool">
                     <c:choose>
                         <c:when test="${tool.isChecked eq '1'}">
                            <td> <input type="checkbox" id="marketingTools"  name="marketingTools" value="${tool.marketingTools}" checked="checked">${tool.marketingToolsName}</td>
                         </c:when>

                         <c:otherwise>
                            <td> <input type="checkbox" id="marketingTools"  name="marketingTools" value="${tool.marketingTools}">${tool.marketingToolsName}</td>
                         </c:otherwise>
                     </c:choose>
               </c:forEach>
                 <td></td>
                 </tr>
                 <tr>
                     <td height="50px">电话营销标准时间段 : </td>
                     <td width="12.5%">
                     <select  name="telMarketingStartTime" id="telMarketingStartTime" style="width: 80%">
                         <option value="">--请选择--</option>
                         <c:forEach items="${timeList}" var="time">
                             <c:choose>
                                 <c:when test="${time eq telMarketingStartTime}">
                                     <option value="${telMarketingStartTime}" selected>${telMarketingStartTime}</option>
                                 </c:when>

                                 <c:otherwise>
                                     <option value="${time}">${time}</option>
                                 </c:otherwise>
                             </c:choose>
                         </c:forEach>
                     </select>
                     </td>

                     <td width="12.5%">
                         <select  name="telMarketingEndTime" id="telMarketingEndTime" style="width: 80%">
                             <option value="">--请选择--</option>
                             <c:forEach items="${timeList}" var="time">
                                 <c:choose>
                                     <c:when test="${time eq telMarketingEndTime}">
                                         <option value="${telMarketingEndTime}" selected>${telMarketingEndTime}</option>
                                     </c:when>

                                     <c:otherwise>
                                         <option value="${time}">${time}</option>
                                     </c:otherwise>
                                 </c:choose>
                             </c:forEach>
                         </select>
                     </td>

                 <tr></tr>

                 <tr>
                     <td>
                         <msk:button buttonType="button" buttonId="BY121319.ADD" buttonValue="保存"/>
                     </td>
                 </tr>
             </table>

         </div>
     </form>
 </div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121319.js"></script>