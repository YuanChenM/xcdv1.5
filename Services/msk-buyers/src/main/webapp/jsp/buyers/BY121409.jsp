<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MerchantType" viewType="json"/>
<msk2:codemaster codeType="TargetMarketBuyer" viewType="json"/>
<navigation:header title="菜场新增编缉" backTitleArray="菜场定性定级列表" backUrlArray="${ctx}/BY121407/init/"></navigation:header>
<div class="page-content list-page">

    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场基本信息</label>
        </h3>

        <form id="BY121409SaveForm" action="${ctx}/BY121409/save/" method="post">
            <input type="hidden" id="marketId" name="marketId" value="${marketId}"/>
            <input type="hidden" id="marketStatus" name="marketStatus" value=""/>
            <input type="hidden" id="oldResearchPhase" name="oldResearchPhase"
                   value="${marketFoodBasic.researchPhase}"/>
            <table style="width: 100%;" CellSpacing=8>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>菜场名称 : </td>
                    <td><input type="text" id="marketName" value="${marketFoodBasic.marketName}" name="marketName"
                               maxlength="100" required="true" requiredMessage="菜场名称不能为空"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>物流区 : </td>
                    <td width="12.5%" align="left">
                        <select id="lgcsAreaCode" name="lgcsAreaCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${logisticsAreaList}" var="logisticsArea">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.lgcsAreaCode eq logisticsArea.lgcsAreaCode}">
                                        <option value="${logisticsArea.lgcsAreaCode}"
                                                selected>${logisticsArea.lgcsAreaName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${logisticsArea.lgcsAreaCode}">${logisticsArea.lgcsAreaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="lgcsAreaName" name="lgcsAreaName" value=""/>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>地区(城市) : </td>
                    <td width="12.5%" align="left">
                        <select id="cityCode" name="cityCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${cityList}" var="city">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.cityCode eq city.cityCode}">
                                        <option value="${city.cityCode}" selected>${city.cityName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.cityCode}">${city.cityName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="cityName" name="cityName" value=""/>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>区县 : </td>
                    <td width="12.5%" align="left">
                        <select id="districtCode" name="districtCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${districtList}" var="district">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.districtCode eq district.districtCode}">
                                        <option value="${district.districtCode}"
                                                selected>${district.districtName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${district.districtCode}">${district.districtName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="districtName" name="districtName" value=""/>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>详细地址 : </td>
                    <td width="12.5%" align="left"><input type="text" id="marketAddr"
                                                          value="${marketFoodBasic.marketAddr}"
                                                          name="marketAddr" maxlength="100" required="true"
                                                          requiredMessage="详细地址不能为空"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>辐射范围 : </td>
                    <td width="12.5%" align="left">
                        <select id="radiationRange" name="radiationRange">
                            <option value="">--请选择--</option>
                            <c:forEach items="${radistionRange}" var="radistionRange">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.radiationRange eq radistionRange.key}">
                                        <option value="${radistionRange.key}" selected>${radistionRange.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${radistionRange.key}">${radistionRange.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right">所属地段 : </td>
                    <td width="12.5%" align="left">
                        <select id="sectionType" name="sectionType">
                            <option value="">--请选择--</option>
                            <c:forEach items="${sectionType}" var="sectionType">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.sectionType eq sectionType.key}">
                                        <option value="${sectionType.key}" selected>${sectionType.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${sectionType.key}">${sectionType.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right">买家平均定价 : </td>
                    <td width="12.5%" align="left">
                        <select id="byAveragePriceType" name="byAveragePriceType">
                            <option value="">--请选择--</option>
                            <c:forEach items="${byAveragePriceType}" var="byAveragePriceType">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.byAveragePriceType eq byAveragePriceType.key}">
                                        <option value="${byAveragePriceType.key}"
                                                selected>${byAveragePriceType.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${byAveragePriceType.key}">${byAveragePriceType.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">菜场商户总数(户) : </td>
                    <td><input id="merchantTotalNo" type="text" value="${marketFoodBasic.merchantTotalNo}"
                               name="merchantTotalNo" digits="true" digitsMessage="菜场商户总数为正整数" min="0"
                               minMessage="菜场商户总数不能小于0" max="2147483647" maxMessage="菜场商户总数输入超出范围限制"></td>
                    <td width="12.5%" align="right">目标买家总数(户) : </td>
                    <td><input id="targetBuyer" type="text" value="${marketFoodBasic.targetBuyer}" name="targetBuyer"
                               digits="true" digitsMessage="目标买家总数为正整数" min="0" minMessage="目标买家总数不能小于0"
                               max="2147483647" maxMessage="目标买家总数输入超出范围限制"></td>
                    <td width="12.5%" align="right">目标买家年交易额(万元) : </td>
                    <td><input id="targetAnnualTurnover" type="text" value="${marketFoodBasic.targetAnnualTurnover}"
                               name="targetAnnualTurnover" number="true" numberMessage="目标买家年交易额为数字类型"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">非目标买家总数(户) : </td>
                    <td><input id="ntargetBuyer" type="text" value="${marketFoodBasic.ntargetBuyer}" name="ntargetBuyer"
                               digits="true" digits="true" digitsMessage="非目标买家总数为正整数" min="0" minMessage="非目标买家总数不能小于0"
                               max="2147483647" maxMessage="非目标买家总数输入超出范围限制"></td>
                    <td width="12.5%" align="right">非目标买家年交易额(万元) : </td>
                    <td><input id="ntargetAnnualTurnover" type="text" value="${marketFoodBasic.ntargetAnnualTurnover}"
                               name="ntargetAnnualTurnover" number="true" numberMessage="非目标买家年交易额为数字类型"></td>
                </tr>
                <tr>
                    <td width="12.5%" align="right">菜场评估性质 : </td>
                    <td width="12.5%" align="left">
                        <select id="marketAssessNature" name="marketAssessNature">
                            <option value="">--请选择--</option>
                            <c:forEach items="${marketAssessNature}" var="marketAssessNature">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.marketAssessNature eq marketAssessNature.key}">
                                        <option value="${marketAssessNature.key}"
                                                selected>${marketAssessNature.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${marketAssessNature.key}">${marketAssessNature.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>市场调研阶段 : </td>
                    <td width="12.5%" align="left">
                        <select id="researchPhase" name="researchPhase">
                            <option value="">--请选择--</option>
                            <c:forEach items="${researchPhase}" var="researchPhase">
                                <c:choose>
                                    <c:when test="${marketFoodBasic.researchPhase eq researchPhase.key}">
                                        <option value="${researchPhase.key}" selected>${researchPhase.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${researchPhase.key}">${researchPhase.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td width="12.5%" align="right">
                        <msk:button buttonValue="保存为未审批" buttonId="BY121409.SAVEUAPPROVE" buttonType="button"/>
                    </td>
                    <c:choose>
                        <c:when test="${marketFoodBasic.marketStatus eq '1'}">
                            <td width="12.5%" align="right">
                                <msk:button buttonValue="保存为已审批" buttonId="BY121409.SAVEAPPROVE" buttonType="button"/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td width="12.5%" align="right">
                                <msk:button buttonValue="保存为已审批" buttonId="BY121409.SAVEAPPROVE" buttonType="hidden"/>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>
        </form>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>菜场买家信息</label>
        </h3>

        <form action="${ctx}/BY121409/search/" method="post">
            <input type="hidden" id="byMarkerId" name="marketId" value="${marketId}"/>
            <table id="BY121409_Grid">
                <thead>
                <tr>
                    <th coltype="sno" width="5%">序号</th>
                    <th coltype="text" width="15%" name="merchantName" filter=true>商户名称</th>
                    <th coltype="code" width="30%" name="merchantType" code2name="MERCHANTTYPE_JSON" filter=true>商户类型</th>
                    <th coltype="text" width="20%" name="totalMerchant" filter=true>商户数(户)</th>
                    <th coltype="money" width="10%" name="annualTurnover" filter=false>年交易额(万元)</th>
                    <th coltype="code" width="15%" name="isTargetMerchant" code2name="TARGETMARKETBUYER_JSON">目标商户</th>
                    <th coltype="action" width="5%">
                        <msk:button buttonType="hidden" coltype="edit" buttonId="BY121409.EDIT" buttonValue="编辑"/>
                        <msk:button buttonType="hidden" coltype="delete" buttonId="BY121409.DELETE" buttonValue="删除"/>
                    </th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <msk:button buttonType="button" buttonId="BY121409.ADD" buttonValue="新增"/>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121409.js"></script>