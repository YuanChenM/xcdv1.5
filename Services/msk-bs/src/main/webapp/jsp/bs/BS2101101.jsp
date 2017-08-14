<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:买手店列表
    author:cx
    createDate:2016-3-7
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="买手列表" backTitleArray="" backUrlArray=""></navigation:header>
<div class="page-content list-page">
    <form action="${ctx}/BS2101101/search" method="post" id="bs2101101FormId">
        <input type="hidden" name="filterMap[applyStatus]" value="${applyStatus}">
        <input type="hidden" name="filterMap[slCode]" value="${slCode}">
        <div class="group-accordion" active="true">
            <h3>
                <label>买手列表查询条件</label>
            </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">省</td>
                        <td align="left" width="110px">
                            <select style="width:100px" name="provinceCode" id="province">
                                <option value="0">请选择</option>
                                <c:forEach items="${mdProvinces}" var="mdProvince" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdProvince.provinceCode eq provinceCode}">
                                            <option value="${mdProvince.provinceCode}"
                                                    selected="selected">${mdProvince.provinceName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${mdProvince.provinceCode}">${mdProvince.provinceName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="100px" align="right">地区</td>
                        <td align="left" width="110px">
                            <select style="width:100px" name="cityCode" id="city">
                                <option value="0">请选择</option>
                                <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${cityList.cityCode eq cityCode}">
                                            <option value="${cityList.cityCode}"
                                                    selected="selected">${cityList.cityName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cityList.cityCode}">${cityList.cityName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="100px" align="right">区</td>
                        <td align="left" width="110px">
                            <select style="width:100px" name="districtCode" id="district">
                                <option value="0">请选择</option>
                                <c:forEach items="${mdDistrictList}" var="mdDistrictList" varStatus="i">
                                    <c:choose>
                                        <c:when test="${mdDistrictList.districtCode eq districtCode}">
                                            <option value="${mdDistrictList.districtCode}"
                                                    selected="selected">${mdDistrictList.districtName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${mdDistrictList.districtCode}">${mdDistrictList.districtName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="100px" align="right">买手编码</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="slCodeDis" name="filterMap[slCodeDis]"/>
                        </td>
                        <td width="100px" align="right">买手名称</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="slContact" name="filterMap[slContact]"/>
                        </td>

                    </tr>
                    <tr>
                        <td width="100px" align="right">店铺名称</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="shopName" name="filterMap[shopName]"/>
                        </td>
                        <td width="100px" align="right">身份证号</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="slIdcard" name="filterMap[slIdcard]"/>
                        </td>

                        <td width="100px" align="right">买手地址</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="slAddress" name="filterMap[slAddress]"/>
                        </td>
                        <td width="100px" align="right">联系电话</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="slTel" name="filterMap[slTel]"/>
                        </td>
                        <td width="100px" align="right">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">买手类型</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="agentType" name="filterMap[agentType]"/>
                        </td>
                        <td width="100px" align="right">配送站</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="distribution" name="filterMap[distribution]"/>
                        </td>

                        <td width="100px" align="right">领地</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="demesne" name="filterMap[demesne]"/>
                        </td>
                        <td width="100px" align="right">注册来源</td>
                        <td align="left" width="110px">
                            <input type="text"  style="width:92px" id="registerSource" name="filterMap[registerSource]"/>
                        </td>
                        <td width="100px" align="right">&nbsp;</td>
                        <td width="100px" align="left">
                            <msk:button buttonValue="查询" buttonType="button" buttonId="BS2101101.SEARCH"/>
                        </td>
                    </tr>
                </table>
        </div>
        <div>
            <table id="bs2101101_list_grid"  width="100%">
                <thead>
                <tr>
                    <th coltype="text" name="agentType" filter="false">买手类型</th>
                    <th coltype="text" name="slCodeDis" filter="false">买手编码</th>
                    <th coltype="text" name="lgcsAreaName" filter="false">物流区</th>
                    <th coltype="text" name="distribution" filter="false">配送站</th>
                    <th coltype="text" name="demesne" filter="false">领地</th>
                    <th coltype="text" name="slContact" filter="false">买手名称</th>
                    <th coltype="text" name="shopName" filter="false">店铺名称</th>
                    <th coltype="text" name="registerSource" filter="false">注册来源</th>
                    <th coltype="text" name="slIdcard" filter="false">身份证号码</th>
                    <th coltype="text" name="singleCityName" filter="false">地区</th>
                    <th coltype="text" name="cityName" filter="">行政区划</th>
                    <th coltype="text" name="slAddress" filter="false">买手地址</th>
                    <th coltype="text" name="slTel" filter="false">联系电话</th>
                    <c:choose>
                    <c:when test="${applyStatus eq 1}">
                        <%--<th coltype="action">联盟--%>
                        <%--<input type="hidden" value="联盟" coltype="return" title="联盟" class="h-button"/>--%>
                        <%--</th>--%>
                    </c:when>
                    <c:otherwise>
                    <th coltype="text" name="stewardNum">当前管家数</th>
                    <th coltype="text" name="buyerNum">当前专属买家数</th>
                    <th coltype="action">冻品管家
                        <msk:button buttonValue="冻品管家" buttonType="hidden" coltype="export" class="h-button" title="冻品管家" buttonId="BS2101101.EXPORT"/>
                        <%--<input type="hidden" value="冻品管家" coltype="export" title="冻品管家" class="h-button"/>--%>
                    </th>
                        <%-- <th coltype="action">新增
                             <input type="hidden" value="新增" coltype="edit" title="新增" class="h-button"/>
                         </th>--%>
                    <th coltype="action">
                        <msk:button buttonValue="管控"  buttonType="hidden" coltype="detail" title="管控" class="h-button" buttonId="BS2102101.CRTL"/>
                        <msk:button buttonValue="删除" buttonType="hidden" coltype="delete" class="h-button" title="删除" buttonId="BS2101101.DELETE"/>
                        <%--<input type="hidden" value="修改" coltype="check" title="修改" class="h-button"/>--%>
                    </th>
                        <%--<th coltype="action">申请联盟--%>
                        <%--<input type="hidden" value="申请联盟" coltype="audit" title="申请联盟" class="h-button"/>--%>
                        <%--</th>--%>
                    </c:otherwise>
                    </c:choose>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%--  <table><tr align="left"><msk:button buttonValue="新建" buttonId="SL241101.NEW" buttonType="button"/></tr></table>--%>
    </form>
    <div style="float: left;">
        <msk:button buttonValue="新增" buttonId="BS2101101.NEW" buttonType="button"/>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2101101.js"></script>