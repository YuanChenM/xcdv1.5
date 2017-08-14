<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品总控目录在线管理表
    author:pxg
    createDate:2016-02-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${yesOrNo != 'yes' }">
    <navigation:header title="总控目录在线管理" backTitleArray="" backUrlArray="" ></navigation:header>
</c:if>
<div class="page-content list-page" style="height:100%">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>
       <div>
               <form action="${ctx}/PD14112601/queryData" id="PD141126SearchData" method="post">
               <table>
                   <tr>
                       <td>
                           第一级分类：<select id="oneClass" style="width:135px;" name="classCode">
                           <option value="0">--请选择--</option>
                           <c:forEach items="${listOne}" var="list">
                               <option value="${list.classestreeCode}">${list.levelName}</option>
                           </c:forEach>
                       </select>
                       </td>
                       <td>
                           第二级分类：<select id="twoClass" style="width:135px;" name="classCode">
                           <option value="0">--请选择--</option>
                       </select>
                       </td>
                       <td>
                           第三级分类：<select id="threeClass" style="width:135px;" name="classCode">
                           <option value="0">--请选择--</option>
                       </select>
                       </td>
                       <td>
                           <msk:button buttonValue="查询" buttonId="PD141126.SEARCH" buttonType="button"/>
                       </td>
                   </tr>
               </table>
               </form>
           </div>
        </div>
    <div id="PD14112601Id" style="height: auto">
    </div>
    </div>
<script src="${ctx}/static/js/pd/PD141126.js"></script>
