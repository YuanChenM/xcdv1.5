<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:正式上线修改添加
    author:pxg
    createDate:2016-02-23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content detail-page" style="height:100%">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>
        <div>
            <div>
                <table>
                    <tr>
                        <td>
                            第一级分类：<select id="oneClass" style="width:135px;">
                            <option>--请选择--</option>
                            <c:forEach items="${listOne}" var="list">
                                <option value="${list.classestreeCode}">${list.levelName}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td>
                            &emsp;&emsp;第二级分类：<select id="twoClass" style="width:135px;">
                            <option>--请选择--</option>
                        </select>
                        </td>
                        <td>
                            &emsp;&emsp;第三级分类：<select id="threeClass" style="width:135px;">
                            <option>--请选择--</option>
                        </select>
                        </td>
                        <td>
                            &emsp;&emsp;第四级分类：<select id="fourClass" style="width:135px;">
                            <option>--请选择--</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            第五级分类：<select id="fiveClass" style="width:135px;">
                            <option>--请选择--</option>
                        </select>
                        </td>
                        <td>&emsp;&emsp;&emsp;买家编码：<input type="text" name="normsSuttle" style="width:128px;"/> </td>
                        <td>&emsp;&emsp;&emsp;买家名称：<input type="text" name="normsSuttle" style="width:128px;"/> </td>
                        <td>&emsp;&emsp;&emsp;&emsp;&emsp;调研人<input type="text" name="normsSuttle" style="width:128px;"/> </td>
                    </tr>
                    <tr>
                        <td colspan="5">
                            &emsp;调查日期：<input type="text" id="orderTimeStart" name="filterMap['orderTimeStart']" style="width:128px;"/>
                            &emsp;&emsp;&emsp;~&emsp;&emsp;&emsp;&emsp;
                            <input type="text" width="15%" id="orderTimeEnd" name="filterMap['orderTimeEnd']" style="width:128px;"/>
                        </td>
                        <td><msk:button buttonValue="查询" buttonId="PD141130.SEARCH" buttonType="button"/>
                            <msk:button buttonValue="新建" buttonId="PD141130.ADD" buttonType="button" url="${ctx}/PD141131/init"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="group-accordion" collapsible="false" active="false" style="height:80%">
        <h3 style="position: relative">
            <label>神农客卫士产品总控目录在线调研列表</label>
        </h3>
        <div style="padding-left: 0px;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
            <div class="page-content list-page">
                <form action="${ctx}/PD141130/search" method="post">
                <table id="pd141130_grid" showAddBtn="true">
                    <thead>
                    <tr>
                        <th coltype="text" name="levelCode" id="levelCode" >分类编码</th>
                        <th coltype="text" name="levelName">分类名称</th>
                        <th coltype="text" name="buyerCode" filter="false">买家编码</th>
                        <th coltype="text" name="buyerName" filter="false">买家名称</th>
                        <th coltype="text" name="investigateDate" filter="false">调查日期</th>
                        <th coltype="text" name="investigateMan" filter="false">调研人</th>
                        <th coltype="action">
                            <msk:button buttonValue="详细信息" buttonType="hidden" coltype="detail" class="h-button" buttonId="PD141130.EDIT"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                    </form>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/pd/PD141130.js"></script>
