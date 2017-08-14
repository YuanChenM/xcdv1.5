<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:产品加工类型维护
    author:xu_hongyang
    createDate:2016-2-15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品加工类型维护" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">

    <form:form action="${ctx}/PD141123/search" id="PD141123Form" method="post"  commandName="pdMachining" modelAttribute="pdMachining">
        <div class="group-accordion" collapsible="false" active="true">
            <h3>
                <label>查询条件</label>
            </h3>

            <div>
                <table width="100%">
                    <tr>
                           <td width="100px" align="right">类别</td>
                           <td align="left">
                               <form:select path="classesCode" style="width:120px" name="filterMap['classesCode']" id="pdClasses">
                                   <form:option value="" label="---请　选　择---"/>
                                   <form:options items="${classesList}" itemValue="classesCode" itemLabel="classesName"/>
                               </form:select>
                           </td>
                    </tr>
                </table>
            </div>
        </div>
            <div class="group-accordion" collapsible="false" active="true">
                <h3>
                    <label>加工类型</label>
                </h3>
            <table id="PD141123_list_grid">
                <thead>
                <tr width="20%">
                    <th coltype="text"  name="machiningCode" height="10%" >加工程度编码</th>
                    <th coltype="text"  name="machiningName" height="10%">加工程度名称</th>
                    <th coltype="action" width="10%">
                        <msk:button buttonValue="添加" buttonType="hidden" coltype="add" class="h-button" useable="can_add" buttonId="PD141123.ADD"/>
                        <msk:button buttonValue="修改" buttonType="hidden" coltype="edit" class="h-button" useable="can_edit" buttonId="PD141123.EDIT"/>
                        <msk:button buttonValue="删除" buttonType="hidden" coltype="delete" class="h-button" useable="can_delete" buttonId="PD141123.DELETE"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form:form>
</div>
<script src="${ctx}/static/js/pd/PD141123.js"/>