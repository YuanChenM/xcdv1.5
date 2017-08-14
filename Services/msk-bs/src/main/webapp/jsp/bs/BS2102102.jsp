
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<navigation:header title="冻品管家一览" backTitleArray="" backUrlArray=""></navigation:header>

<div class="page-content list-page">
<form action="${ctx}/BS2102102/search"  method="post" id="BS2102102Form" >
    <div class="group-accordion" collapsible="false" active="false" >
        <h3>
            <label>冻品管家查询条件</label>
        </h3>
        <table width="100%">
            <tr>
                <td width="100px" align="right">物流区</td>
                <td align="left">
                    <select path="logiareaCode" style="width:120px" id="vlgcsAreaCode" name="vlgcsAreaCode">
                        <option value="">请选择</option>
                        <c:forEach items="${logisticsAreas}" var="lgcs" varStatus="status">
                            <option value="${lgcs.lgcsAreaCode}" <c:if test="${lgcs.lgcsAreaCode eq logcAreaCode}">selected</c:if>>${lgcs.lgcsAreaName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td width="100px" align="right">地区</td>
                <td align="left">
                    <select path="cityCode" style="width:120px" id="vcityCode" name="vcityCode">
                        <option value="">请选择</option>
                    </select>
                </td>

                <td width="100px" align="right">管家一级分类</td>
                <td align="left">
                    <select path="cateCode" style="width:120px" id="cateCode" name="cateCode" >
                        <option value="">请选择</option>
                        <c:forEach items="${houseTypeList}" var="category"  varStatus="status">
                            <option value="${category.typeCode}">${category.typeName}</option>
                        </c:forEach>
                    </select>
                </td>

                <td width="100px" align="right">管家二级分类</td>
                <td align="left">
                    <select path="subCode" style="width:120px" id="subCode" name="subCode">
                        <option value="">请选择</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="100px" align="right">姓名</td>
                <td align="left">
                    <input type="text"  style="width:120px" id="houseShowName" name="houseShowName" />
                </td>
                <td width="100px" align="right">手机号</td>
                <td align="left">
                    <input type="text"  style="width:120px" id="houseTel" name="houseTel"/>
                </td>
                <td width="100px" align="right">微信号</td>
                <td align="left">
                    <input type="text"  style="width:120px" id="wechat" name="wechat"/>
                </td>
                <td width="100px" align="right">所属买手</td>
                <td align="left">
                    <input type="text"  style="width:120px" id="slContact" name="slContact"/>
                </td>
                <td><msk:button buttonValue="查询" buttonType="button" buttonId="BS2102102.SEARCH"/></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%;">
        <table id="BS2102102_list_grid" width="100%">
            <thead>
            <tr>
                <th coltype="sno" width="20px">序号</th>
                <th coltype="text"  name="vlgcsAreaName">物流区</th>
                <th coltype="text"  name="houseMangeCityName">地区</th>
                <th coltype="text"  width="10%"  name="houseGreade" >级别</th>
                <th coltype="text"  width="10%"   name="houseStar1" >星级</th>
                <th coltype="text"  width="10%"  name="cateName" >管家一级分类</th>
                <th coltype="text" width="10%"   name="subName" >管家二级分类</th>
                <th coltype="text" width="10%"   name="houseShowName" >姓名</th>
                <th coltype="text" width="10%"   name="sex" >性别</th>
                <th coltype="text" width="10%"   name="houseTel" >手机号</th>
                <th coltype="text" width="10%"   name="wechat" >微信号</th>
                <th coltype="text" width="10%"   name="slContact" >所属买手</th>
                <%--<th coltype="text" width="10%"   name="remark">备注</th>--%>
                <th coltype="action">操作
                    <msk:button buttonValue="管控"  buttonType="hidden" coltype="detail" title="管控" class="h-button" buttonId="BS2102102.CRTL"/>
                    <msk:button buttonValue="设置"  buttonType="hidden" coltype="check" title="设置" class="h-button" buttonId="BS2102102.CHECK"/>
                    <%--<msk:button buttonValue="编辑"  buttonType="hidden" coltype="edit" title="编辑" class="h-button" buttonId="BS2102102_deit"/>--%>
                    <msk:button buttonValue="删除"  buttonType="hidden" coltype="delete" title="删除" class="h-button" buttonId="BS2102102.DELETE"/>

                </th>
            </tr>

            </thead>
            <tbody>
            </tbody>
        </table>
        </div>
</form>
   <div>
        <table>
            <tr>
                <td><msk:button buttonValue="新增" buttonType="button" buttonId="BS2102102.SAVE"/></td>
            </tr>
        </table>
    </div>
</div>

<script src="${ctx}/static/bs/js/BS2102102.js"></script>
