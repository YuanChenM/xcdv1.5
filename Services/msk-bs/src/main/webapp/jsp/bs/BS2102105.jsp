<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:设置冻品管家
    author:yang_chunyan
    createDate:2016-8-2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msktag" uri="/codemaster" %>
<navigation:header title="冻品管家组成员一览" backTitleArray="冻品管家组一览" backUrlArray="${ctx}/BS2102104/init"></navigation:header>

<div class="page-content list-page">
    <form action="${ctx}/BS2102105/search" method="post" id="bs2102105FormId">
        <div class="group-accordion" collapsible="false" style="min-width: 850px" active="false" >
            <h3>
                <label>冻品管家组信息</label>
            </h3>
            <table width="100%">
                <input type="hidden" id="groupId" name="filterMap[dpGroupId]" value="${groupId}">
                <tr>
                    <td width="12%" align="right">物流区：</td>
                    <td width="10%" align="left">
                        <input type="hidden" name="filterMap[lgcsAreaCode]" id="lgcsCode" value="${lgcsCode}">
                        <input type="hidden" id="lgcsName" value="${lgcsName}">
                        ${lgcsName}
                    </td>
                    <td width="10%" align="right">地区：</td>
                    <td width="10%" align="left">
                        <input type="hidden" id="districtCode" value="${cityCode}">
                        <input type="hidden" id="cityName" value="${cityName}">
                        ${cityName}
                    </td>
                    <td width="10%" align="right">买家类型：</td>
                    <td width="10%" align="left">
                        <input type="hidden" id="buyerTypeName" value="${buyerTypeName}">
                        ${buyerTypeName}
                    </td>
                    <td width="10%" align="right">产品一级分类：</td>
                    <td width="10%" align="left">
                        <input type="hidden" id="classesName" value="${classesName}">
                        ${classesName}
                    </td>
                    <td width="10%" align="right">产品二级分类：</td>
                    <td width="10%" align="left">
                        <input type="hidden" id="machiningName" value="${machiningName}">
                        ${machiningName}
                    </td>
                </tr>
                <tr >
                    <td align="right">冻品管家组名称：</td>
                    <td colspan="9" align="left">
                        <input type="hidden" id="groupName" value="${groupName}">
                        ${groupName}
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table id="bs2102105_list_grid" showAddBtn="true" style="min-width: 850px" width="100%">
                <thead>
                <tr>
                    <th coltype="sno" filter="false">序号</th>
                    <%--<th coltype="text" name="houseGreade" filter="false">级别</th>
                    <th coltype="text" name="houseStar" filter="false">星级</th>--%>
                    <th coltype="text" name="houseShowName" filter="false">姓名</th>
                    <th coltype="text" name="sex" filter="false">性别</th>
                    <th coltype="text" name="houseTel" filter="false">手机号</th>
                    <th coltype="text" name="wechat" filter="">微信号</th>
                    <th coltype="text" name="flag6" edit="true" filter="false">备注</th>
                    <th coltype="action">操作
                        <msk:button buttonValue="设置"  buttonType="hidden" coltype="check" title="设置" class="h-button" buttonId="BS2102105.CONFIG"/>
                        <msk:button buttonValue="删除"  buttonType="hidden" coltype="delete" title="删除" class="h-button" buttonId="BS2102105.DELETE"/>
                    </th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </form>
<div>
    <table>
        </tr>
            <td><msk:button buttonType="button" buttonId="BS2102105.NEW" buttonValue="新增"/></td>
            <td><msk:button buttonType="button" buttonId="BS2102105.SAVE" buttonValue="保存"/></td>
        </tr>
    </table>
</div>
</div>



<script src="${ctx}/static/bs/js/BS2102105.js"></script>