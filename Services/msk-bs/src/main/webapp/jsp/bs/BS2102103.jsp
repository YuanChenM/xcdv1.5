<%--
    Title:冻品管家管理设置
    User: wang_haichun
    Date: 2016/8/2
    Time: 11:40
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<c:if test="${empty flagNum}">
    <navigation:header title="冻品管家管理设置" backTitleArray="冻品管家一览" backUrlArray="${ctx}/BS2102102/init"></navigation:header>
</c:if>
<c:if test="${flagNum == '4'}">
    <navigation:header title="冻品管家管理设置" backTitleArray="冻品管家组一览,冻品管家组成员一览"
                       backUrlArray="${ctx}/BS2102104/init,${ctx}/BS2102105/init/?hkGroupId=${groupId}&isHk=1"></navigation:header>
</c:if>
<c:if test="${flagNum == '3'}">
    <navigation:header title="冻品管家管理设置" backTitleArray="冻品管家一览,冻品管家管控"
                       backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init/?slCode=${bs2102103Bean.slCode}&houseCode=${bs2102103Bean.houseCode}"></navigation:header>
</c:if>


<div class="page-content list-page">
        <div class="group-accordion" style="min-width: 904px" collapsible="false" active="false" >
                <label>冻品管家信息</label>
            </h3>
            <table style="text-align: center;border-collapse:separate;border-spacing:10px;width: 100%;">
                <tr>
                    <td width="15%" style="text-align: right;">姓名：</td>
                    <td width="15%" id="houseShowName" style="text-align: left;">${bs2102103Bean.houseShowName}</td>
                    <td width="10%" style="text-align: right;">性别：</td>
                    <c:choose>
                        <c:when test="${bs2102103Bean.flag1 == '1'}">
                            <td width="10%" style="text-align: left;">男</td>
                        </c:when>
                        <c:when test="${bs2102103Bean.flag1 == '2'}">
                            <td width="10%" style="text-align: left;">女</td>
                        </c:when>
                        <c:otherwise>
                            <td width="10%" style="text-align: left;"></td>
                        </c:otherwise>
                    </c:choose>


                    <td width="10%" style="text-align: right;">手机号：</td>
                    <td width="10%" id="houseTel" style="text-align: left;">${bs2102103Bean.houseTel}</td>
                    <td width="10%" style="text-align: right;">微信号：</td>
                    <td width="10%" id="wechat" style="text-align: left;">${bs2102103Bean.wechat}</td>
                </tr>
                <tr>
                    <%--<td width="50px" style="text-align: right;">级别：</td>
                    <td width="50px" id="houseGreade" style="text-align: left;">${bs2102103Bean.houseGreade}</td>
                    <td width="50px" style="text-align: right;">星级：</td>
                    <td width="50px" id="houseStar" style="text-align: left;">${bs2102103Bean.houseStar}</td>--%>
                    <td style="text-align: right;">虚拟经营地址：</td>
                    <td colspan="7" id="vhouseAddress" style="text-align: left;">${provinceName}${cityName}${districtName}${bs2102103Bean.vhouseAddress}</td>
                </tr>
            </table>
            <input type="hidden" id="flag1" value="${bs2102103Bean.flag1}" />
            <input type="hidden" id="input_groupId" value="${groupId}" />
            <input type="hidden" id="input_flagNum" value="${flagNum}" />
        </div>

    <form action="${ctx}/BS2102103/search" method="post" id="bs2102103FormId">
        <input type="hidden" id="input_slCode" name="slCode" value="${bs2102103Bean.slCode}">
        <input type="hidden" id="input_houseCode" name="houseCode" value="${bs2102103Bean.houseCode}">
        <input type="hidden" id="input_lgcsAreaCode" name="lgcsAreaCode" value="${bs2102103Bean.lgcsAreaCode}">
        <input type="hidden" id="input_lgcsAreaName" name="lgcsAreaName" value="${bs2102103Bean.lgcsAreaName}">
        <table id="BS2102103_list_grid" width="100%">
            <thead>
            <tr>
                <th coltype="sno">序号</th>
                <th coltype="text" name="lgcsAreaName">物流区</th>
                <th coltype="text" name="cityName">地区</th>
                <th coltype="text" name="grade">等级</th>
                <th coltype="text" name="star">星级</th>
                <th coltype="text" name="houseCategoryName">一级分类</th>
                <th coltype="text" name="houseReclassifyName">二级分类</th>
                <!--Add 数量、重量、金额等以千分位形式显示横展 2016/10/12 Start-->
                <th coltype="money" accuracy="0" edit="true" name="publicBuyers">管理公众买家数</th>
                <th coltype="money" accuracy="0" edit="true" name="vipBuyers">管理会员买家数</th>
                <!--Modif for Bug #3670 at 2016/11/23 by whc Start -->
                <th coltype="money" accuracy="0" edit="true" name="marketingDays">营销所属期时长(天)</th>
                <!--Modif for Bug #3670 at 2016/11/23 by whc Start -->
                <!--Add 数量、重量、金额等以千分位形式显示横展 2016/10/12 End-->
                <th coltype="code" edit="true" name="isChangeBuyersText" blank="false" code2name="['否','是']">可否自由串换买家
                </th>
                <th coltype="text" edit="true" name="remark">备注</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </form>
    <div>
        <table>
            <tr>
                <td><msk:button buttonValue="新增" buttonId="BS2102103.NEW" buttonType="button"/></td>
                <td> <msk:button buttonValue="保存" buttonId="BS2102103.SAVE" buttonType="button"/></td>
            </tr>
        </table>
    </div>

</div>
<script src="${ctx}/static/bs/js/BS2102103.js"></script>
