<%-- 
    Title:产品包装
    author:pxg
    createDate:2015-12-09
    updateDate:2015-12-09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="品种产品目录报表" backTitleArray="" backUrlArray="" ></navigation:header>
<div class="page-content list-page" style="height:100%">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>
        <div>
            <table>
                <tr>
                    <td>
                        一级分类:
                        <c:forEach items="${classesList}" var="logisticsArea">
                            <a href="javascript:void(0)" style="padding-right: 10px;" name="classesCode" selectLogistics="${logisticsArea.levelCode}">${logisticsArea.levelName}</a>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="PD14210101Id"/>
</div>
<script src="${ctx}/static/js/pd/PD142101.js"></script>
