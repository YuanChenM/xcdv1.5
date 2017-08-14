<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${superiorId eq 'all'}">
    <navigation:header title="买家列表" backTitleArray="" backUrlArray=""></navigation:header>
</c:if>
<c:if test="${!(superiorId eq 'all')}">
    <c:if test="${type eq '1'}">
        <navigation:header title="${market.marketName}的买家列表" backTitleArray="批发市场列表"
                           backUrlArray="${ctx}/BY121301/init/"></navigation:header>
    </c:if>
    <c:if test="${type eq '2'}">
        <navigation:header title="${market.marketName}的买家列表" backTitleArray="菜场列表"
                           backUrlArray="${ctx}/BY121302/init/"></navigation:header>
    </c:if>
</c:if>
<div class="page-content list-page">
    <form action="${ctx}/BY121303/search/${superiorId}" method="post" id="selectBuyerList">
    <input value="${superiorId}" type="hidden" id="superiorId">
        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td width="10%" align="right">买家账号 : </td>
                    <td width="15%"><input type="text" id="accountName" name="filterMap['accountName']"></td>
                    <td width="10%" align="right">注册手机 : </td>
                    <td width="15%" ><input type="text" id="telNo" name="filterMap['telNo']"></td>
                    <td width="10%" align="right">买家编码 : </td>
                    <td width="15%"><input type="text" id="buyerCode" name="filterMap['buyerCode']"></td>
                    <td width="10%" align="right">店铺号 : </td>
                    <td width="15%"><input type="text" id="storeNo" name="filterMap['storeNo']"></td>
                </tr>
                <tr>
                    <td align="right">买家名称 : </td>
                    <td><input type="text" id="buyerName" name="filterMap['buyerName']"></td>
                    <td align="right">老板名称 : </td>
                    <td><input type="text" id="bossName" name="filterMap['bossName']"></td>
                    <td align="right">买家类型 : </td>
                    <td>
                        <select id="superiorType" name="filterMap['superiorType']">
                            <option value="">--请选择--</option>
                            <c:forEach items="${buyerTypeList}" var="bl">
                                <option value="${bl.key}">${bl.value}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['superiorName']" id="superiorName" value="">
                    </td>
                    <td align="right">上线状态 : </td>
                    <td>
                        <select id="marketStatus" name="filterMap['marketStatus']">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketStatusList}" var="ml">
                                <option value="${ml.key}">${ml.value}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['marketingsStatusName']" id="marketingsStatusName" value="">
                    </td>
                </tr>
                <tr>
                    <td align="right">物流区 : </td>
                    <td>
                        <select id="lgcsAreaCode" name="filterMap['lgcsAreaCode']">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="lal">
                                <option value="${lal.lgcsAreaCode}">${lal.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['lgcsAreaName']" id="lgcsAreaName" value="">
                    </td>
                    <td align="right">地区 : </td>
                    <td>
                        <select id="cityCode" name="filterMap['cityCode']">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['cityName']" id="cityName">
                    </td>
                    <td align="right">区县 : </td>
                    <td>
                        <select id="districtCode"  name="filterMap['districtCode']">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['districtName']" id="districtName">
                    </td>
                    <td align="right">批发市场/菜场 : </td>
                    <td>
                        <select id="marketId" name="filterMap['marketId']">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['marketName']" id="marketName">

                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td  align="right"><msk:button buttonType="button" coltype="select"  buttonId="BY121303.SELECT"  buttonValue="查询"/></td>
                </tr>
            </table>
        </div>

        <div>
        <table id="BY121303_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="accountName" >买家账号</th>
                <th coltype="text" name="telNo" >注册手机</th>
                <th coltype="text" name="buyerCode" >买家编码</th>
                <th coltype="text" name="storeNo" >店铺号</th>
                <th coltype="text" name="buyerName" >买家名称</th>
                <th coltype="text" name="bossName" >老板名称</th>
                <th coltype="text" name="superiorName" >买家类型</th>
                <th coltype="text" name="marketingsStatusName" >上线状态</th>
                <th coltype="text" name="lgcsAreaName">物流区名称</th>
                <th coltype="text" name="marketName" >批发市场/菜场</th>
                <th coltype="action">所属买家池
                    <msk:button buttonType="hidden" coltype="audit" useable="can_viewPool" buttonId="BY121303.AUDITBRBYPOOL"  buttonValue="查看买家池"/>
                </th>
                <th coltype="action">冻品管家
                    <msk:button buttonType="hidden" coltype="audit" useable="can_viewHk" buttonId="BY121303.AUDITHOUSEKEEP"  buttonValue="查看冻品管家"/>
                </th>
                <th coltype="action">
                    <%--<input type="hidden" coltype="edit" title="编辑" class="h-button"/>
                    <input type="hidden"coltype="delete" title="删除" class="h-button" />--%>
                    <msk:button buttonType="hidden" coltype="detail" buttonId="BY121303.DETAIL"  buttonValue="总控"/>
                    <%--<msk:button buttonType="hidden" coltype="edit" buttonId="BY121303.edit"  buttonValue="编辑"/>--%>
                    <msk:button buttonType="hidden" coltype="delete" buttonId="BY121303.DELETE"  buttonValue="删除"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        </div>
    </form>
    <input type="hidden" value="${message}" id="message"/><span style="color:red" style="text-align:center;">*</span>
    买家手机号 : <input value="" type="text" name="telNo" id="insertTelNo" maxlength="20"/>
    <msk:button buttonValue="新增" buttonId="BY121303.ADD" buttonType="button"/>
    <br><br>
    <msk:button buttonValue="查看买家全部账号信息" buttonId="BY121303.ACCOUNT" buttonType="button"/>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121303.js"></script>