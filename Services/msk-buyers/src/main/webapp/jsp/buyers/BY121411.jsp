<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MerchantType" viewType="json"/>
<msk2:codemaster codeType="TargetMarketBuyer" viewType="json"/>
<navigation:header title="菜场定性定级审批" backTitleArray="菜场定性定级列表" backUrlArray="${ctx}/BY121407/init/"></navigation:header>
<style>
    .gallery > div {
        position: relative;
        float: left;
        padding: 5px;
    }

    .gallery > div > img {
        width: 200px;
        height: 200px;
        transition: .1s transform;
        transform: translateZ(0);
    }

    .gallery > div:hover {
        z-index: 1;
    }

    .gallery > div:hover > img {
        transform: scale(2, 2);
        transition: .3s transform;
    }

    .cf:before, .cf:after {
        display: table;
        content: "";
        line-height: 0;
    }

    .cf:after {
        clear: both;
    }
</style>
<div class="page-content list-page">

    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场基本信息</label>
        </h3>
        <table style="width: 100%;" CellSpacing=8>
            <tr>
                <td width="12.5%" align="right">菜场名称 : </td>
                <td><input type="text" value="${marketFoodBasic.marketName}" disabled editmodel = "false"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">物流区 : </td>
                <td width="12.5%" align="left"><input type="text" id="lgcsAreaName"  value="${marketFoodBasic.lgcsAreaName}"  lgcsAreaCode="${marketFoodBasic.lgcsAreaCode}"  disabled maxlength="20" editmodel = "false"></td>
                <td width="12.5%" align="right">地区(城市) : </td>
                <td width="12.5%" align="left"><input type="text" id="cityName" value="${marketFoodBasic.cityName}" cityCode="${marketFoodBasic.cityCode}" disabled editmodel = "false" maxlength="20" ></td>
                <td width="12.5%" align="right">区县 : </td>
                <td width="12.5%" align="left"><input type="text" id="districtName" value="${marketFoodBasic.districtName}" districtCode="${marketFoodBasic.districtCode}" disabled editmodel = "false" maxlength="20"> </td>
                <td width="12.5%" align="right">详细地址 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.marketAddr}" disabled editmodel = "false" maxlength="100"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">辐射范围 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.radiationRangeTypeName}" disabled editmodel = "false" maxlength="20"></td>
                <td width="12.5%" align="right">所属地段 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.sectionTypeName}" disabled editmodel = "false" maxlength="100">  </td>
                <td width="12.5%" align="right">买家平均定价 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.byAveragePriceTypeName}" disabled editmodel = "false"  maxlength="100"></td>
            </tr>
            <tr>
                <td width="12.5%" align="right">目标买家总数(户) : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.targetBuyer}" disabled editmodel = "false" > </td>
                <td width="12.5%" align="right">目标买家年交易额(万元) : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.targetAnnualTurnover}" disabled editmodel = "false" ></td>
                <td width="12.5%" align="right">非目标买家总数(户) : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.ntargetBuyer}" disabled editmodel = "false" ></td>
                <td width="12.5%" align="right">非目标买家年交易额(万元) : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.ntargetAnnualTurnover}" disabled editmodel = "false"></td>
            </tr>
            </tr>
            <tr>
                <td width="12.5%" align="right">菜场评估性质 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.marketAssessNatureName}" disabled editmodel = "false" maxlength="20"></td>
                <td width="12.5%" align="right">市场调研阶段 : </td>
                <td width="12.5%" align="left"><input type="text" value="${marketFoodBasic.researchPhaseName}" disabled editmodel = "false" maxlength="100"></td>
            </tr>
            <tr></tr>
            <tr></tr>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场买家信息</label>
        </h3>

        <form action="${ctx}/BY121411/search/" method="post">
            <input type="hidden" name="marketId" value="${marketId}"/>
            <table id="BY121411_Grid">
                <thead>
                <th coltype="sno">序号</th>
                <th coltype="text" name="merchantName" filter=true>商户名称</th>
                <th coltype="code" name="merchantType" code2name="MERCHANTTYPE_JSON" filter=true>商户类型</th>
                <th coltype="code" name="isTargetMerchant" code2name="TARGETMARKETBUYER_JSON">目标商户</th>
                <th coltype="text" name="totalMerchant" filter=true>商户数(户)</th>
                <th coltype="money" name="annualTurnover" filter=false>年交易额(万元)</th>
                </thead>
                <tbody></tbody>
            </table>
        </form>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场照片信息</label>
        </h3>
        <table style="width: 100%;" CellSpacing=8>
            <c:forEach items="${marketFileList}" var="marketFile">
                <tr>
                    <td width="12.5%" align="center">${marketFile.fileName}</td>
                    <td align="left">
                        <div class="gallery cf">
                            <div>
                                <img src="${marketFile.marketFilePath}"/>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场定性定级判定结果</label>
        </h3>
        <form id="BY121411SaveForm" action="${ctx}/BY121411/save/" method="post">
            <input type="hidden" id="marketId" name="marketId" value="${marketId}"/>
            <input type="hidden" name="researchPhase" value="${marketFoodBasic.researchPhase}">
            <input type="hidden" name="marketStatus" value="${marketFoodBasic.marketStatus}">
            <input type="hidden" name="syncFlag" id="syncFlag"/>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场性质(定性) : </td>
                    <td width="12.5%" align="left">
                        <select id="marketNature" name="marketNature">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketNature}" var="marketNature">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.marketNature eq marketNature.key}">
                                        <option value="${marketNature.key}" selected>${marketNature.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${marketNature.key}">${marketNature.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="marketNatureName" name="marketNatureName" value=""/>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场编码 : </td>
                    <td width="12.5%" align="left">
                        <input type="text" id="marketCode" value="${marketFoodBasic.marketCode}" name="marketCode" maxlength="10" required
                               requiredMessage="菜场编码不能为空" maxlength="10" minlength="10" minlengthMessage="菜场编码为10位的数字"
                               digits="true" digitsMessage="菜场编码为数字类型"/>
                    </td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td width="12.5%" align="right">
                        <msk:button buttonType="button" buttonId="BY121411.SEARCH" buttonValue="查看判定标准"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <table>
        <tr>
            <td><msk:button buttonType="button" buttonId="BY121411.SAVENOTSYNC" buttonValue="保存"/></td>
            <td><msk:button buttonType="button" buttonId="BY121411.SAVEANDSYNC" buttonValue="同步"/></td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121411.js"></script>