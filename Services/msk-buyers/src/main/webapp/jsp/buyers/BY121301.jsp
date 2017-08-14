<%--
  Created by IntelliJ IDEA.
  User: marshall
  Date: 16/3/9
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="批发市场列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BY121301/search" method="post"  id="selectBuyerList">
        <div class="group-accordion" collapsible="true" active="true" >
            <h3>
                <label>查询条件</label>
            </h3>

            <table width="100%">
                <tr>
                    <td width="10%"  align="right">批发市场编码 : </td>
                    <td width="15%"><input type="text" id="marketCode" name="filterMap['marketCode']" value=""></td>
                    <td width="10%"  align="right">批发市场名称 : </td>
                    <td width="15%"><input type="text" id="marketName" name="filterMap['marketName']" value=""></td>
                    <td width="10%" align="right">物流区 : </td>
                    <td width="15%">
                        <select id="lgcsAreaCode" name="filterMap['lgcsAreaCode']">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="lal">
                                <option value="${lal.lgcsAreaCode}">${lal.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="filterMap['lgcsAreaName']" id="lgcsAreaName" value="">
                    </td>
                    <td width="10%" align="right">地区 : </td>
                    <td width="15%">
                        <select id="cityCode" name="filterMap['cityCode']">
                            <option value="">--请选择--</option>
                        </select>
                        <input type="hidden" name="filterMap['cityName']" id="cityName">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="right"><msk:button buttonType="button" coltype="select"  buttonId="BY121301.SELECT"  buttonValue="查询"/></td>
                </tr>

            </table>
            </div>
        <div>
        <table id="BY121301_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="marketCode" filter=false>批发市场编码</th>
                <th coltype="link" name="marketName" filter=false>批发市场名称</th>
                <th coltype="text" name="marketAddr" filter=false>批发市场地址</th>
                <th coltype="text" name="marketLevelName" filter=false>批发市场级别</th>
                <th coltype="text" name="lgcsAreaCode" filter=false>物流区编码</th>
                <th coltype="text" name="lgcsAreaName" filter=false>物流区名称</th>
                <th coltype="text" name="cityCode" filter=false>城市(地区)编码</th>
                <th coltype="text" name="cityName" filter=false>城市(地区)名称</th>
                <th coltype="action">
                    <%--    <input type="hidden"coltype="edit" title="编辑" class="h-button" />
                        <input type="hidden"coltype="delete" title="删除" class="h-button" />--%>
                    <msk:button buttonType="hidden" coltype="edit" buttonId="BY121301.EDIT"  buttonValue="编辑"/>
                    <msk:button buttonType="hidden" coltype="delete" buttonId="BY121301.DELETE"  buttonValue="删除"/>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        </div>
    </form>
    <div>
        <msk:button buttonType="button" buttonId="BY121301.NEW"  buttonValue="新增"/>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121301.js"></script>


