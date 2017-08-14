<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:公共买家池列表
    author:cx
    createDate:2016-3-3
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:choose>
    <c:when test="${flagNum == '10'}">
        <navigation:header title="买家列表" backTitleArray="买手列表,冻品管家列表"
                           backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101102/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}" ></navigation:header>
    </c:when>
    <c:when test="${flagNum == '11'}">
        <navigation:header title="买家列表" backTitleArray="买手列表,买手管控,冻品管家列表"
                           backUrlArray="${ctx}/BS2101101/init,${ctx}/BS2101199/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&flagNum=${flagNum},${ctx}/BS2101102/init/?slCode=${slCode}&slCodeDis=${slCodeDis}&slContact=${slContact}&flagNum=${flagNum}" ></navigation:header>
    </c:when>
    <c:otherwise>
        <navigation:header title="买家列表" backTitleArray="" backUrlArray=""></navigation:header>
    </c:otherwise>
</c:choose>
<div class="page-content list-page">
    <c:choose>
        <c:when test="${!empty flagNum}">
            <form action="${ctx}/BS2101104/search/" method="post" id="BS2101104FormId">
        </c:when>
        <c:otherwise>
            <form action="${ctx}/BS2101104/searchBuyerList/" method="post" id="BS2101104FormId">
        </c:otherwise>
    </c:choose>
        <input type="hidden" id="hidPageType" name="hidPageType" value="${pageType}">
        <input type="hidden" id="hidHouseCode" name="hidHouseCode" value="${houseCode}">
        <input type="hidden" id="hidHouseAccount" name="hidHouseAccount" value="${houseAccount}">
        <input type="hidden" id="hidSlCode" name="hidSlCode" value="${slCode}">
            <div class="group-accordion" active="true">
                <h3>
                    <label>买家列表查询条件</label>
                </h3>
                <table width="100%">
                <tr>
                    <input type="hidden" value="${slCode}" name="filterMap[slCode]">
                    <td align="right" width="150px">物流区</td>
                    <td width="150px">
                        <select style="width:120px" name="filterMap[lgcsAreaCode]" id="province_select">
                            <option value="" label="请选择">请选择</option>
                            <c:forEach items="${lgcsArea}" var="lgcsArea" varStatus="i">
                                <option value="${lgcsArea.lgcsAreaCode}">${lgcsArea.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right" width="150px">地区</td>
                    <td width="150px">
                        <select style="width:120px" name="filterMap[cityCode]" id="city_select">
                            <option value="" label="请选择">请选择</option>
                        </select>
                    </td>
                    <td align="right" width="150px">区</td>
                    <td width="150px">
                        <select name="filterMap[districtCode]" id="district_select">
                            <option value="" label="请选择">请选择</option>
                        </select>
                    </td>
                    <%--<td>--%>
                        <%--<msk:button buttonValue="查询" buttonId="BS2101104.search" buttonType="button"/>--%>
                    <%--</td>--%>
                </tr>
            </table>
        </div>
        <table id="bs2101104_list_grid" showAddBtn="true" width="100%">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="accountName" filter="true">买家账号</th>
                <c:choose>
                    <c:when test="${!empty flagNum}">
                        <th coltype="text" name="buyerTel" filter="true">注册手机</th>
                    </c:when>
                    <c:otherwise>
                        <th coltype="text" name="telNo" filter="true">注册手机</th>
                    </c:otherwise>
                </c:choose>
                <th coltype="text" name="buyerCode" filter="true">买家编码</th>
                <th coltype="text" name="buyerName" filter="true">买家名</th>
                <th coltype="text" name="superiorName" filter="true">买家类型</th>
                <th coltype="select" width="10%" name="marketingsStatusName" filter="true">上线状态
                    <select>
                        <c:forEach items="${marketingStatus}" var="market" >
                            <option value="${market.key}">${market.value}</option>
                        </c:forEach>
                    </select>
                </th>


                <th coltype="text" name="busiTel" filter="true">联系电话</th>
                <th coltype="text" name="lgcsAreaName">物流区名称</th>
                <th coltype="text" name="marketName">批发市场/菜场</th>
                <th coltype="action" width="60px">所属买家池
                    <msk:button buttonValue="所属买家池" buttonType="hidden" coltype="audit" title="所属买家池" class="h-button" buttonId="BS2101104.AUDIT"/>
                </th>
                <c:choose>
                    <c:when test="${pageType eq 1}">
                        <th coltype="action" width="60px">成为锁定期买家
                            <msk:button buttonValue="成为锁定期买家" buttonType="hidden" coltype="add" title="成为锁定期买家" class="h-button" buttonId="BS2101104.ADD"/>
                        </th>
                        <th coltype="action" width="60px">成为专属买家
                            <msk:button buttonValue="成为专属买家" buttonType="hidden" coltype="add" title="成为专属买家" class="h-button" buttonId="BS2101104.ADD"/>
                        </th>
                    </c:when>
                    <%--pageType等于2时，是直接点击左侧菜单进入的--%>
                    <c:when test="${pageType eq 2}">
                        <th coltype="text" name="houseShowName">冻品管家</th>
                        <%--锁定期和专属买家跟pageType =1中的操作是不一样的--%>
                     <%--   <th coltype="action" width="60px">成为锁定期买家
                            <msk:button buttonValue="成为锁定期买家" buttonType="hidden" coltype="add" title="成为锁定期买家" class="h-button" buttonId="BS2101104.ADD"/>
                        </th>
                        <th coltype="action" width="60px">成为专属买家
                            <msk:button buttonValue="成为专属买家" buttonType="hidden" coltype="add" title="成为专属买家" class="h-button" buttonId="BS2101104.ADD"/>
                        </th>--%>
                        <th coltype="action" width="60px">解除冻品管家
                            <msk:button buttonValue="解除冻品管家" buttonType="hidden" coltype="check" title="解除冻品管家" class="h-button" buttonId="BS2101104.CHECK" useable="can_abolish"/>
                        </th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
</div>
<script src="${ctx}/static/bs/js/BS2101104.js"></script>