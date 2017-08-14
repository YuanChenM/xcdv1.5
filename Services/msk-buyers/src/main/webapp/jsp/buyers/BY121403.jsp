<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<msk2:codemaster codeType="MerchantType" viewType="json"/>
<msk2:codemaster codeType="NTargetPdClasses" viewType="json"/>
<navigation:header title="批发市场新增编缉" backTitleArray="批发市场定性定级列表"
                   backUrlArray="${ctx}/BY121401/init/"></navigation:header>

<div class="page-content list-page">
    <form id="BY121403SaveForm" action="${ctx}/BY121403/save/${type}" method="post">
        <input type="hidden" id="marketId" name="marketId" value="${marketId}"/>
        <input type="hidden" id="flag" name="flag" value="${marketId}"/>
        <input type="hidden" id="marketStatus" name="marketStatus" value="${marketTerminalBasic.marketStatus}"/>
        <input type="hidden" id="marketCode" name="marketCode" value="${marketTerminalBasic.marketCode}"/>
        <input type="hidden" id="marketLevel" name="marketLevel" value="${marketTerminalBasic.marketLevel}"/>
        <input type="hidden" id="marketLevelName" name="marketLevelName"
               value="${marketTerminalBasic.marketLevelName}"/>
        <input type="hidden" id="oldResearchPhase" name="oldResearchPhase"/>

        <input type="hidden" id="existenceFlg" name="existenceFlg"/>

        <table style="width: 100%">
            <tr>
                <td>
                    <div class="group-accordion" collapsible="true" active="true">
                        <h3>批发市场基本信息</h3>
                        <table style="width: 100%;">
                            <tr>
                                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>批发市场名称 : </td>
                                <td><input type="text" id="marketName" value="${marketTerminalBasic.marketName}"
                                           name="marketName" maxlength="100"  readonly="readonly"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>物流区 : </td>
                                <td width="12.5%" align="left">
                                    <input type="text" id="lgcsAreaName" name="lgcsAreaName"
                                           value="${marketTerminalBasic.lgcsAreaName}" readonly="readonly" editmodel = "false" />
                                    <input type="hidden" id="lgcsAreaCode" name="lgcsAreaCode"
                                           value="${marketTerminalBasic.lgcsAreaCode}"/>
                                </td>
                                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>地区(城市) : </td>
                                <td width="12.5%" align="left">
                                    <input type="text" id="cityName" name="cityName"
                                           value="${marketTerminalBasic.cityName}" readonly="readonly" editmodel = "false"/>
                                    <input type="hidden" id="cityCode" name="cityCode"
                                           value="${marketTerminalBasic.cityCode}"/>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>详细地址 : </td>
                                <td width="12.5%" align="left"><input type="text" id="marketAddr"
                                                                      value="${marketTerminalBasic.marketAddr}"
                                                                      name="marketAddr" readonly="readonly" editmodel = "false"></td>
                                <td width="12.5%" align="right"><span style="color:red;display: inline; vertical-align: sub">*&nbsp;</span>市场调研阶段 : </td>
                                <td width="12.5%" align="left">

                                    <select id="researchPhase" name="researchPhase">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${researchPhase}" var="researchPhase">
                                            <c:choose>
                                                <c:when test="${marketTerminalBasic.researchPhase eq researchPhase.key}">
                                                    <option value="${researchPhase.key}"
                                                            selected>${researchPhase.value}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${researchPhase.key}">${researchPhase.value}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" id="researchPhaseName" name="researchPhaseName" value=""/>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="5" align="left"
                                    <c:if test="${marketId ne 'add'}">style='display:none;'</c:if>>
                                    <msk:button buttonValue="保存" buttonId="BY121403.SAVEUAPPROVE2" buttonType="button"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="group-accordion" collapsible="true"
                            <c:choose>
                                <c:when test="${marketId eq 'add'}">
                                    active="false"
                                </c:when>
                                <c:otherwise>
                                    active="true"
                                </c:otherwise>
                            </c:choose> >
                        <h3>批发市场定性定级信息</h3>
                        <table width="100%">
                            <!-- First -->
                            <tr>
                                <td colspan="8"><h3>1.批发市场占地面积</h3></td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">1) 依托大型冷库的批发市场:一批[50]亩以上,二批[30-50]亩,三批[不足30]亩;</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">2) 不依托大型冷库批发市场:一批[200]亩以上,二批[100-200]亩,三批[不足100]亩;</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td width="12%" align="right">占地面积 : </td>
                                <td width="12%" align="left" colspan=""><input type="text"
                                                                               value="${marketTerminalBasic.areaCovered}"
                                                                               name="areaCovered" id="areaCovered"  number = "true" numberMessage = "占地面积为数字类型" min="0" minMessage ="占地面积不能小于0">(单位:亩)
                                </td>

                                <td width="12%" align="right" colspan="">占地面积定级 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.areaCoveredLevel}"
                                                                    name="areaCoveredLevel" id="areaCoveredLevel" maxlength="10"></td>
                                <td width="12%" align="right">依托大型冷库</td>
                                <td width="12%" align="left">
                                    <input type="radio" name="isLargeStorage" value="1"
                                           <c:if test="${marketTerminalBasic.isLargeStorage eq '1'}">checked="true" </c:if>/>是
                                    &nbsp;&nbsp;<input type="radio" name="isLargeStorage" value="0"
                                                       <c:if test="${marketTerminalBasic.isLargeStorage eq '0'}">checked="true" </c:if> />否

                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>

                            <!-- Second -->
                            <tr>
                                <td colspan="8"><h3>2.批发市场目标买家年销售额</h3></td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">1）一批[20]亿元以上，二批[5-20]亿元，三批不足5]亿元;</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td width="12%" align="right">本批发市场年销售额 : </td>
                                <td width="12%"><input type="text" value="${marketTerminalBasic.targetAnnualTurnover}"
                                                       name="targetAnnualTurnover" id="targetAnnualTurnover" number = "true" numberMessage = "本批发市场年销售额为数字类型" min="0" minMessage ="本批发市场年销售额不能小于0" />(单位:亿元)
                                </td>
                                <td width="12%" align="right">买家年销售额定级 : </td>
                                <td width="12%"><input type="text"
                                                       value="${marketTerminalBasic.targetAnnualTurnoverLevel}"
                                                       name="targetAnnualTurnoverLevel" id="targetAnnualTurnoverLevel" maxlength="10"/>
                                </td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>
                            <!-- Third -->
                            <tr>
                                <td colspan="8"><h3>3.目标买家数</h3></td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">1）最大分类目标买家数:一批[50]户以上，二批[20-50]户，三批[不足20]户</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td width="12%" align="right">最大分类目标买家为 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.maxClassBuyerType}"
                                                                    name="maxClassBuyerType" id="maxClassBuyerType"
                                                                    readonly="readonly" editmodel = "false"/>(产品名称)
                                </td>
                                <td width="12%" align="right">买家数 : </td>
                                <td align="left" width="12%"><input type="text"
                                                                    value="${marketTerminalBasic.maxClassBuyerNum}"
                                                                    name="maxClassBuyerNum" id="maxClassBuyerNum"
                                                                    readonly="readonly" editmodel = "false"/>(单位:户)
                                </td>
                                <td width="12%" align="right" colspan="">最大分类目标买家定级 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.maxClassBuyerLevel}"
                                                                    name="maxClassBuyerLevel" id="maxClassBuyerLevel"  maxlength="10"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">2）批发市场目标买家总数:一批[150]户以上，二批[50-150]户，三批[不足50]户</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td align="right" width="12%">目标买家总数 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.targetBuyer}"
                                                                    name="targetBuyer" id="targetBuyer"
                                                                    readonly="readonly" editmodel = "false" />(单位:户)
                                </td>
                                <td width="12%" align="right">目标买家总数定级 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.targetBuyerLevel}"
                                                                    name="targetBuyerLevel" id="targetBuyerLevel"  maxlength="10"/></td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>
                            <!-- Four -->
                            <tr>
                                <td colspan="8"><h3>4.批发市场目标买家分级</h3></td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">1）一批[二批、三批分销商]为主，二批[综合农贸市场买家]为主，三批[用户]为主</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td align="right" width="12%">目标买家 : </td>
                                <td align="left" width="12%"><input type="text"
                                                                    value="${marketTerminalBasic.faceBuyerTypeName}"
                                                                    name="faceBuyerTypeName" id="faceBuyerTypeName" maxlength="20"/>
                                </td>
                                <td align="right" width="12%">目标买家分级定级 : </td>
                                <td align="left" width="12%"><input type="text"
                                                                    value="${marketTerminalBasic.faceBuyerLevel}"
                                                                    name="faceBuyerLevel" id="faceBuyerLevel" maxlength="10"/></td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>
                            <!-- Five -->
                            <tr>
                                <td colspan="8"><h3>5.批发市场辐射范围</h3></td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td colspan="3">1）一批[全物流区]，二批[本地区(城市)]，三批[本区(县)]</td>
                            </tr>
                            <tr>
                                <td width="2%"></td>
                                <td width="12%" align="right" colspan="">辐射范围 : </td>
                                <td width="12%" align="left"><input type="text"
                                                                    value="${marketTerminalBasic.radiationRangeTypeName}"
                                                                    name="radiationRangeTypeName"
                                                                    id="radiationRangeTypeName" maxlength="20"/></td>
                                <td width="12%" align="right">辐射范围定级 : </td>
                                <td width="12%" align="left" colspan=""><input type="text"
                                                                               value="${marketTerminalBasic.radiationRangeLevel}"
                                                                               name="radiationRangeLevel"
                                                                               id="radiationRangeLevel" maxlength="100"/></td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>
                            <!-- Six -->
                            <tr>
                                <td colspan="8"><h3>6.批发市场定性</h3></td>
                            </tr>
                            <tr>
                                <td colspan="4" align="center" width="">
                                    <input type="radio" name="marketNature" value="1"
                                           <c:if test="${marketTerminalBasic.marketNature eq '1'}">checked="true" </c:if>>综合(冻品)
                                    <input type="radio" name="marketNature" value="2"
                                           <c:if test="${marketTerminalBasic.marketNature eq '2'}">checked="true" </c:if>>非综合(冻品)
                                    <input type="radio" name="marketNature" value="3"
                                           <c:if test="${marketTerminalBasic.marketNature eq '3'}">checked="true" </c:if>>综合(常温品)
                                    <input type="radio" name="marketNature" value="4"
                                           <c:if test="${marketTerminalBasic.marketNature eq '4'}">checked="true" </c:if>>非综合(常温品)
                                </td>
                                <td width="12%" align="right">偏重产品 : </td>
                                <td width="12%" align="left" colspan=""><input type="text"
                                                                               value="${marketTerminalBasic.marketNatureEmohasis}"
                                                                               name="marketNatureEmohasis"
                                                                               id="marketNatureEmohasis" maxlength="100"></td>
                            </tr>
                            <tr>
                                <td height="10px"></td>
                            </tr>
                            <tr>
                                <td align="left" <c:if test="${marketId eq 'add'}">style='display:none;'</c:if>>
                                    <msk:button buttonValue="保存" buttonId="BY121403.SAVEUAPPROVE" buttonType="button"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>

    </form>
    <!--批发市场目标买家汇总-->
    <div class="group-accordion" collapsible="true" active="true"
         <c:if test="${marketId eq 'add'}">style='display:none;'</c:if> >
        <h3>
            <label>批发市场目标买家汇总</label>
        </h3>

        <form action="${ctx}/BY121403/search" method="post">
            <input type="hidden" name="marketId" value="${marketId}"/>


            <input type="hidden" name="isTargetMerchant" value="1"/>
            <table id="BY121403_Grid">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="merchantName" width="22%" filter=true>买家类型</th>
                    <th coltype="text" name="salePd" width="22%">销售产品类型</th>
                    <th coltype="text" name="totalMerchant" width="22%" filter=true>买家户数</th>
                    <th coltype="money" name="annualTurnover" width="22%" cellEditMode="true" edit="true" >
                        年交易额(万元)
                    </th>
                </tr>
                </thead>
                <tbody></tbody>
                <tr role='row' style='height: 26px;'>
                    <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2'
                        colspan='2'></td>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>
                        当前页合计 :
                    </td>
                    <td class='text' name='currentNumber' id='currentNumber'
                        style='border: 1px solid #b1b1b1;text-align: left'></td>
                    <td class='text' name='currentAmount' id='currentAmount'
                        style='border: 1px solid #b1b1b1;text-align: right ;padding-right: 10px;padding-left: 4px;'></td>
                </tr>
                <tr role='row' style='height: 26px;'>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>
                        总合计 :
                    </td>
                    <td class='text' name='totalNumber' id='totalNumber'
                        style='border: 1px solid #b1b1b1;text-align: left'></td>
                    <td class='text' name='totalAmount' id='totalAmount'
                        style='border: 1px solid #b1b1b1;text-align: right ;padding-right: 10px;padding-left: 4px;'></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><msk:button buttonType="button" buttonId="BY121403.TARGETADD" buttonValue="保存"/></td>
                </tr>
            </table>
        </form>
    </div>

    <!--批发市场非目标买家汇总-->
    <div class="group-accordion" collapsible="true" active="true"
         <c:if test="${marketId eq 'add'}">style='display:none;'</c:if> >
        <h3>
            <label>批发市场非目标买家汇总</label>
        </h3>

        <form action="${ctx}/BY121403/search" method="post">
            <input type="hidden" name="marketId" value="${marketId}"/>


            <input type="hidden" name="isTargetMerchant" value="0"/>
            <table id="BY121403_Grid2">
                <thead>
                <tr>
                    <th coltype="sno">序号</th>
                    <th coltype="text" name="merchantName" width="30%" filter=true>非目标买家名称</th>
                    <th coltype="text" name="totalMerchant" width="30%" filter=true>非目标买家户数</th>
                    <th coltype="money" name="annualTurnover" width="30%" cellEditMode="true" edit="true" >
                        年交易额(万元)
                    </th>
                </tr>
                </thead>
                <tbody></tbody>
                <tr role='row' style='height: 26px;'>
                    <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2'></td>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>
                        当前页合计 :
                    </td>
                    <td class='text' name='currentNumber2' id='currentNumber2'
                        style='border: 1px solid #b1b1b1;text-align: left'></td>
                    <td class='text' name='currentAmount2' id='currentAmount2'
                        style='border: 1px solid #b1b1b1;text-align: right ;padding-right: 10px;padding-left: 4px;'></td>
                </tr>
                <tr role='row' style='height: 26px;'>
                    <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>
                        总合计 :
                    </td>
                    <td class='text' name='totalNumber2' id='totalNumber2'
                        style='border: 1px solid #b1b1b1;text-align: left'></td>
                    <td class='text' name='totalAmount2' id='totalAmount2'
                        style='border: 1px solid #b1b1b1;text-align: right ;padding-right: 10px;padding-left: 4px;'></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><msk:button buttonType="button" buttonId="BY121403.NOTARGETADD" buttonValue="保存"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="synchButton"
         <c:if test="${synchButton eq '0' or marketId eq 'add' or  marketId eq 'null'  }">style='display:none;' </c:if> >

        <msk:button buttonType="button" buttonId="BY121403.NOTARGETSYNCH" buttonValue="更新先期调查表数据 "/>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121403.js"></script>
<style type="text/css">
    td {
        white-space: nowrap;
    }
</style>