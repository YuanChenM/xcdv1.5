<%--
  Created by IntelliJ IDEA.
  User: tao_zhifa
  Date: 2016/6/28
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<style type="text/css">
    a:link {
        color: blue;
    }
</style>

<msk:codemaster codeType="PacAuditMode" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PacSupplyMode" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="PacPaymentMode" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="RelationType" viewType="json" modelName="SSC"/>

<!-- 合同未生效前，可编辑 -->
<c:set var="editable" value="${empty contractBasic.contractStatus or (contractBasic.contractStatus ge 0 and contractBasic.contractStatus lt 4)}"/>

<c:if test="${empty navigation}">
    <navigation:header title="合同详细" backTitleArray="合同管理一览" backUrlArray="../SSC11303/init"></navigation:header>
</c:if>
<c:if test="${navigation eq 'delivery'}">
    <navigation:header title="合同详细" backTitleArray="发货订单一览,发货订单详细" backUrlArray="../SSC11305/init,../SSC11306/init" backParamArray="/{deliveryId:${deliveryId}}"></navigation:header>
</c:if>
<c:if test="${navigation eq 'differ'}">
    <navigation:header title="合同详细" backTitleArray="生产商入库差异单一览,生产商入库差异单详细" backUrlArray="../SSC11311/init/,../SSC11312/show/" backParamArray='/{differId:${differId}}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'verification'}">
    <navigation:header title="合同详细" backTitleArray="核销单一览,核销单详细" backUrlArray="../SSC11321/init,../SSC11322/init" backParamArray="/{verificationId:${verificationId}}"></navigation:header>
</c:if>
<c:if test="${navigation eq 'payment'}">
    <navigation:header title="合同详细" backTitleArray="资金池一览" backUrlArray="../SSC11319/init/"></navigation:header>
</c:if>
<c:if test="${navigation eq 'poolDetail'}">
    <navigation:header title="合同详细" backTitleArray="资金池一览,资金池详细" backUrlArray="../SSC11319/init,../SSC11320/init/" backParamArray='/{paymentId:${paymentId}}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'request'}">
    <navigation:header title="合同详细" backTitleArray="付款申请一览" backUrlArray="../SSC11307/init/" backParamArray=''></navigation:header>
</c:if>
<c:if test="${navigation eq 'control'}">
    <navigation:header title="合同详细" backTitleArray="生产商计划管理,生产商计划详细" backUrlArray="../SSC11326/init/,../SSC11326/detail/" backParamArray="/{contractCode:\"${contractCode}\"}"></navigation:header>
</c:if>
<c:if test="${navigation eq 'bidBase'}">
    <navigation:header title="合同详细" backTitleArray="中标成交确认书一览" backUrlArray="../SSC11301/init/" backParamArray=""></navigation:header>
</c:if>
<c:if test="${navigation eq 'requestDetail'}">
    <navigation:header title="合同详细"  />
</c:if>


<div class="page-content list-page">
    <div class="group-accordion" active="true">
        <h3><label>合同基础信息</label></h3>

        <form id="contract_form">
            <input type="hidden" id="navigation" value="${navigation}"/>
            <input type="hidden" id="contractId" name="contractId" value="${contractBasic.contractId}"/>
            <input type="hidden" id="contractCode" name="contractCode" value="${contractBasic.contractCode}"/>
            <input type="hidden" id="contractStatus" name="contractStatus" value="${contractBasic.contractStatus}"/>
            <input type="hidden" id="contractActDate" value="${contractBasic.contractActDate}"/>
            <input type="hidden" id="bidId" value="${contractBasic.bidId}"/>
            <input type="hidden" id="bidProjectNo" name="bidProjectNo" value="${contractBasic.bidProjectNo}"/>
            <input type="hidden" id="supplierId" name="supplierId" value="${contractBasic.supplierId}"/>
            <input type="hidden" id="supplierName" name="supplierName"value="${contractBasic.supplierName}"/>
            <input type="hidden" id="supplierCode" name="supplierCode" value="${contractBasic.supplierCode}"/>
            <input type="hidden" id="purchaserId" name="purchaserId" value="${contractBasic.purchaserId}"/>
            <input type="hidden" id="purchaserName" name="purchaserName"value="${contractBasic.purchaserName}"/>
            <input type="hidden" id="purchaserCode" name="purchaserCode" value="${contractBasic.purchaserCode}"/>
            <input type="hidden" id="ver" name="ver" value="${contractBasic.ver}"/>

            <table width="100%">
                <tr>
                    <td style="white-space:nowrap; width:14%; text-align:right;">中标成交确认书编号：</td>
                    <c:if test="${empty navigation}">
                        <td>
                            <<c:if test="${contractBasic.contractStatus ne 9}">a href="javascript:SSC11304.gotoBidDetail();"</c:if>id="bidProjectNoHref">${contractBasic.bidProjectNo}</a>
                            <c:if test="${contractBasic.contractStatus ne 9}">
                                <c:if test="${empty contractBasic.contractId || contractBasic.bidRelationType eq 2}">
                                    <msk:button buttonType="button" buttonId="SSC11304.CONTRACT_ADDBID" buttonValue="关联中标"/>
                                </c:if>
                                <c:if test="${not empty contractBasic.contractId && contractBasic.bidRelationType eq 1}">
                                    <msk:button buttonType="button" buttonId="SSC11304.CONTRACT_ADDBID" buttonValue="重新关联中标"/>
                                </c:if>
                            </c:if>
                        </td>
                    </c:if>
                    <c:if test="${not empty navigation}">
                        <td>${contractBasic.bidProjectNo}</td>
                    </c:if>
                    <td style="white-space:nowrap; width:14%; text-align:right;">合同关联中标类型：</td>
                    <td>
                        <c:if test="${not empty contractBasic}">
                            <msk:codemaster codeType="RelationType" viewType="label" modelName="SSC" codeValue="${contractBasic.bidRelationType}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right">合同编号：</td>
                    <td>${contractBasic.contractCode}</td>
                    <td align="right">合同名称：</td>
                    <td>
                        <c:if test="${editable}">
                            <input maxlength="100" type="text" id="contractName" name="contractName" value="${contractBasic.contractName}" style="width:300px;" onchange="changeVal();"/>
                        </c:if>
                        <c:if test="${not editable}">
                            ${contractBasic.contractName}
                            <input type="hidden" id="contractName" name="contractName" value="${contractBasic.contractName}"/>
                        </c:if>
                    </td>
                </tr>
                <c:if test="${empty contractBasic}">
                    <tr>
                        <td align="right">甲方(采购商)：</td>
                        <td id="purchaser_td">
                            <select id="purchaserSelect" style="border:1px solid #888888; width:307px;">
                                <option value="">--&nbsp;请选择&nbsp;--</option>
                                <c:forEach items="${sellers}" var="seller">
                                    <option value="${seller.slCode}" purchaserName="${seller.epName}" purchaserId="${seller.epId}" slMainClass="${seller.slMainClass}">${seller.epName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="right">乙方(生产商)：</td>
                        <td>
                            <select id="supplierSelect" style="border:1px solid #888888; width:307px;">
                                <option value="">--&nbsp;请选择&nbsp;--</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${not empty contractBasic}">
                    <tr>
                        <td align="right">甲方(采购商)：</td>
                        <td style="white-space:nowrap;">${contractBasic.purchaserName}</td>
                        <td align="right">乙方(生产商)：</td>
                        <td style="white-space:nowrap;">${contractBasic.supplierName}</td>
                    </tr>
                </c:if>
                <tr>
                    <td align="right">合同生效日期：</td>
                    <td>
                        <c:if test="${editable}">
                            <input type="text" name="contractActDateStr" id="contractActDateStr" value='<fmt:formatDate value="${contractBasic.contractActDate}" type="date" dateStyle="default"/>' style="width:300px;" readonly="re" onchange="changeVal();"/>
                        </c:if>
                        <c:if test="${not editable}">
                            <fmt:formatDate value="${contractBasic.contractActDate}" type="date" dateStyle="default"/>
                        </c:if>
                    </td>
                    <td align="right">合同状态：</td>
                    <td>
                        <c:if test="${not empty contractBasic}">
                            <msk:codemaster codeType="SscOrderStatus" viewType="label" modelName="SSC" codeValue="${contractBasic.contractStatus}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right">采购方审核人：</td>
                    <td>
                        <c:if test="${contractBasic.contractStatus ne 0 && contractBasic.contractStatus ne 1 && contractBasic.contractStatus ne 3}">
                            ${contractBasic.purchaserConfirmName}
                        </c:if>
                    </td>
                    <td align="right">采购方审核时间：</td>
                    <td>
                        <c:if test="${contractBasic.contractStatus ne 0 && contractBasic.contractStatus ne 1 && contractBasic.contractStatus ne 3}">
                            <c:if test="${not empty contractBasic.purchaserConfirmTime}">
                                <fmt:formatDate value="${contractBasic.purchaserConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right">生产方审核人：</td>
                    <td>
                        <c:if test="${contractBasic.contractStatus ne 0 && contractBasic.contractStatus ne 1 && contractBasic.contractStatus ne 2}">
                            ${contractBasic.supplierConfirmName}
                        </c:if>
                    </td>
                    <td align="right">生产方审核时间：</td>
                    <td>
                        <c:if test="${contractBasic.contractStatus ne 0 && contractBasic.contractStatus ne 1 && contractBasic.contractStatus ne 2}">
                            <c:if test="${not empty contractBasic.supplierConfirmTime}">
                                <fmt:formatDate value="${contractBasic.supplierConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </table>
            <c:if test="${editable}">
                <msk:button buttonType="button" buttonId="SSC11304.CONTRACT_EDIT" buttonValue="保存"/>
            </c:if>
        </form>
    </div>

    <div id="tabs" class="page-content list-page" style="padding:0;">
        <ul style="border-top:0; border-right:0; border-left:0;">
            <li><a href="#tabs-1">合同订单信息</a></li>
            <li><a href="#tabs-2">合同包材信息</a></li>
            <li><a href="#tabs-3">交货期计划</a></li>
            <li><a href="#tabs-4">商务条款</a></li>
        </ul>

        <!-- 合同订单信息 -->
        <div id="tabs-1">
            <form action="<c:url value='/SSC1130403/product/search' />" method="post">
                <input type="hidden" name="contractId" value="${contractBasic.contractId}"/>

                <div>
                    <table width="100%" id="SCC11304_contractOrder_list_grid">
                        <thead>
                        <tr>
                            <th coltype="sno" rowspan="2">序号</th>
                            <th colspan="2">产品信息</th>
                            <th colspan="2">加工信息</th>
                            <th colspan="3">中标价(元/kg)</th>
                            <th colspan="2">到岸价组成</th>
                            <th colspan="2">货值</th>
                            <th colspan="2">本次付款数</th>
                            <th colspan="3">其它</th>
                        </tr>
                        <tr>
                            <th coltype="text" name="pdName">产品</th>
                            <th coltype="action">产品质量标准<msk:button buttonType="hidden" buttonId="SSC11304.AUDIT" coltype="audit" buttonValue="产品质量标准" class="h-button"/></th>
                            <th coltype="money" accuracy="4" name="productTonnage">重量(吨)</th>
                            <th coltype="money" accuracy="0" <c:if test="${editable}">edit="true"</c:if> name="productBox">&nbsp;&nbsp;&nbsp;箱数&nbsp;&nbsp;&nbsp;</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="fobFreePackage">不含包装离岸价</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="packageCost">包材成本</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="fobIncludePackage">含包装离岸价</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="trunkFreight">干线运费</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="cif">到岸价</th>
                            <th coltype="money" <c:if test="${editable}">edit="true"</c:if> name="settkementStandardPrice">本次结算标准价(元/kg)</th>
                            <th coltype="money" name="productValue">货值(元)</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="downPayment">预付款比例(%)</th>
                            <th coltype="money" name="paymentAmount">付款额(元)</th>
                            <th coltype="text" name="deliveryPlan">交货期计划</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="remark">&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;&nbsp;</th>
                            <th coltype="action">
                                操作
                                <c:if test="${editable}">
                                    <msk:button buttonValue="保存" buttonId="SSC11304.ORDER_UPDATE" buttonType="hidden" coltype="save"/>
                                    <msk:button buttonValue="删除" buttonId="SSC11304.ORDER_DELETE" buttonType="hidden" coltype="delete"/>
                                </c:if>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tr id="order_sum_tr">
                            <td colspan="3" style="font-weight:bold; text-align:center;">合计</td>
                            <td style="text-align:right;" id="totalTonnages"></td>
                            <td style="text-align:right;" id="totalBoxes"></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td style="text-align:right;" id="totalValues"></td>
                            <td>&nbsp;</td>
                            <td style="text-align:right;" id="totalPayments"></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </form>
            <c:if test="${editable}">
                <msk:button buttonValue="添加产品" buttonType="button" buttonId="SSC11304.ORDER_NEW"/>
                <span style="color:red; font-size:12px;">*请新增或修改合同订单的产品明细后，及时更新包材信息和交货期计划！</span>
            </c:if>
        </div>

        <!-- 合同包材信息 -->
        <div id="tabs-2">
            <form action="<c:url value='/SSC11304/contractPackageSearch' />" method="post">
                <input type="hidden" name="contractId" value="${contractBasic.contractId}"/>

                <div>
                    <table id="SCC11304_contractPackage_list_grid" width="100%">
                        <thead>
                        <tr>
                            <th coltype="sno" rowspan="2">序号</th>
                            <th colspan="1">产品信息</th>
                            <th colspan="2">(神农卫士包材标准)纸箱</th>
                            <th colspan="2">(神农卫士包材标准)内袋</th>
                            <th colspan="2">(本次订单包材信息)纸箱</th>
                            <th colspan="2">(本次订单包材信息)内袋</th>
                            <th colspan="2">本次包材需求量</th>
                            <th colspan="3">方式</th>
                            <th colspan="3">其它</th>
                        </tr>
                        <tr>
                            <th coltype="text" name="product">产品名称</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="cartonQuaSta">质量标准</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="cartonSpecSta">规格</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="innerBagQuaSta">质量标准</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="innerBagSpecSta">规格</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="cartonQua">质量标准</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="cartonSpec">规格</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="innerBagQua">质量标准</th>
                            <th coltype="text" <c:if test="${editable}">edit="true"</c:if> name="innerBagSpec">&nbsp;&nbsp;规格&nbsp;&nbsp;</th>
                            <th coltype="money" accuracy="0" <c:if test="${editable}">edit="true"</c:if> name="cartonUseNum">&nbsp;&nbsp;纸箱&nbsp;&nbsp;</th>
                            <th coltype="money" accuracy="0" <c:if test="${editable}">edit="true"</c:if> name="innerBagUseNum" width="100px">&nbsp;&nbsp;内袋&nbsp;&nbsp;</th>
                            <th coltype="code" <c:if test="${editable}">cellEdit="true" edit="true"</c:if> code2name="PACSUPPLYMODE_JSON" name="supplyMode">包材提供方式</th>
                            <th coltype="code" <c:if test="${editable}">cellEdit="true" edit="true"</c:if> code2name="PACAUDITMODE_JSON" name="auditMethod">&nbsp;&nbsp;&nbsp;包材审核方式&nbsp;&nbsp;&nbsp;</th>
                            <th coltype="code" <c:if test="${editable}">cellEdit="true" edit="true"</c:if> code2name="PACPAYMENTMODE_JSON" name="settlementMethod">&nbsp;&nbsp;&nbsp;包材结算方式&nbsp;&nbsp;&nbsp;</th>
                            <th coltype="action">
                                操作
                                <c:if test="${editable}">
                                    <msk:button buttonValue="保存" buttonId="SSC11304.PKG_UPDATE" buttonType="hidden" coltype="save"/>
                                    <msk:button buttonValue="删除" buttonId="SSC11304.PKG_DELETE" buttonType="hidden" coltype="delete"/>
                                </c:if>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tr id="pkg_sum_tr">
                            <td colspan="10" style="font-weight:bold; text-align:center;">合计</td>
                            <td style="text-align:right;" id="totalCartons"></td>
                            <td style="text-align:right;" id="totalInnerBags"></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </form>
            <c:if test="${editable}">
                <msk:button buttonValue="添加包材" buttonType="button" buttonId="SSC11304.PKG_NEW"/>
            </c:if>
        </div>

        <!-- 交货期计划 -->
        <div id="tabs-3">
            <form action="<c:url value='/SSC11304/contractDeliveryPlanSearch' />" method="post">
                <input type="hidden" name="contractId" value="${contractBasic.contractId}"/>

                <div>
                    <table id="SCC11304_deliveryPlan_list_grid" width="100%">
                        <thead>
                        <th coltype="sno">序号</th>
                        <th coltype="date" <c:if test="${editable}">edit="true"</c:if> name="arriveDateStr">到货日期</th>
                        <th coltype="text" name="batchCode">到货车次</th>
                        <th coltype="text" name="pdName">到货产品</th>
                        <th coltype="money" accuracy="0" <c:if test="${editable}">edit="true"</c:if> style="width:10%;" name="arriveBoxes">到货箱数</th>
                        <th coltype="money" accuracy="4" name="arriveQut">到货数量(吨)</th>
                        <th coltype="text" <c:if test="${editable}">edit="true"</c:if> style="width:10%;" name="remark">备注</th>
                        <th coltype="action">
                            操作
                            <c:if test="${editable}">
                                <msk:button buttonValue="保存" buttonId="SSC11304.DP_UPDATE" buttonType="hidden" coltype="save"/>
                                <msk:button buttonValue="删除" buttonId="SSC11304.DP_DELETE" buttonType="hidden" coltype="delete"/>
                            </c:if>
                        </th>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </form>
            <c:if test="${editable}">
                <msk:button buttonValue="添加计划" buttonType="button" buttonId="SSC11304.DP_NEW"/>
                <span style="color:red; font-size:12px;">*紫色：部分分配，绿色：已分配完</span>
            </c:if>
        </div>

        <!-- 商务条款 -->
        <div id="tabs-4">
            <form id="SSC11304ContractBusinesss">
                <input type="hidden" name="contractId" value="${contractBasic.contractId}"/>
                <input type="hidden" name="clauseId" id="clauseId" value="${bs.clauseId}"/>
                <input type="hidden" name="tranFeeStandard" value="${bs.tranFeeStandard}"/>
                <input type="hidden" name="ver" value="${bs.ver}"/>

                <h4>1.&nbsp;&nbsp;付款方式：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;合同签订后付合同总货值&nbsp;<input type="text"  name="paymentRatio" value="${bs.paymentRatio}"/>&nbsp;%预付款，发货前，付清本次发货余款。</h4>
                <h4>2.&nbsp;&nbsp;运输责任方及运费支付方式：</h4>
                <h4>
                    &nbsp;&nbsp;&nbsp;&nbsp;由&nbsp;<input type="text" maxlength="100" name="tranRes" value="${bs.tranRes}"/>&nbsp;方负责承运，运费支付方式：
                    <input type="radio" value="0" <c:if test="${bs.tranFeeMethod==0}">checked="checked"</c:if> name="tranFeeMethod"/>价内支付，
                    <input type="radio" value="1" <c:if test="${bs.tranFeeMethod==1}">checked="checked"</c:if> name="tranFeeMethod"/>价外支付，
                    <input type="radio" value="3" <c:if test="${bs.tranFeeMethod==3}">checked="checked"</c:if> name="tranFeeMethod"/>单独支付。
                    乙方负责运输，干线运费标准为&nbsp;<input type="text" id="tranFeeStandardStr"/>&nbsp;元/吨•公里。
                </h4>
                <h4>3.&nbsp;&nbsp;验收方式：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;到货验收，验收内容如下：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;1)&nbsp;到货时车辆温度、产品中心温度；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;2)&nbsp;按合同约定的质量标准验收；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;3)&nbsp;必检产品检测指标：解冻失水率、挥发性盐基氮、菌落总数、感官质量标准；副产品类抽检重金属指标、分割品类、白条类抽检农药残留、兽药残留指标。</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;如验收出现质量问题，甲方将产品入待处理产品库，双方协商处理办法。如出现严重质量问题导致产品无法销售的，甲方将保留包括但不限于退货、换货、索赔等处理权力。</h4>
                <h4>4.&nbsp;&nbsp;交货期延迟处理：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;交货期超过&nbsp;<input type="text" name="contractVerPeriod" value="${bs.contractVerPeriod}"/>&nbsp;天期限，甲方有权要求重新定价或取消订单。</h4>
                <h4>5.&nbsp;&nbsp;本次订单甲方授权签字人：<input type="text" maxlength="100" name="purchaserAuthSig" value="${bs.purchaserAuthSig}"/>，乙方授权签字人：<input maxlength="100" type="text" name="supplierAuthSig" value="${bs.supplierAuthSig}"/>，本订单按签字人签字、盖公司合同章(或公章)后生效。扫描件(指定传真号码：<input maxlength="100" type="text" name="faxNum" value="${bs.faxNum}"/>&nbsp;传真件)与原件具有同等法律效力，合同原件采取扫描件互认互寄方式传送。</h4>
                <h4>6.&nbsp;&nbsp;甲方信息：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲方地址：<input maxlength="100" type="text" name="purchaserAddr" value="${bs.purchaserAddr}" style="width: 400px"/>，对公邮箱：<input maxlength="100" type="text" name="purchaserEmail" value="${bs.purchaserEmail}" style="width: 300px"/>；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;行政流转人：<input maxlength="100" type="text" name="adminTrans" value="${bs.adminTrans}"/>，QQ：<input maxlength="100" type="text" name="adminTransQq" value="${bs.adminTransQq}"/>；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;谈判管理人：<input maxlength="100" type="text" name="negManager" value="${bs.negManager}"/>，电话：<input maxlength="100" type="text" name="negManagerPhonenum" value="${bs.negManagerPhonenum}"/>，QQ：<input maxlength="100" type="text" name="negManagerQq" value="${bs.negManagerQq}"/>；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;品控管理人：<input maxlength="100" type="text" name="qcManager" value="${bs.qcManager}">，电话：<input maxlength="100" type="text" name="qcManagerPhonenum" value="${bs.qcManagerPhonenum}"/>，QQ：<input maxlength="100" type="text" name="qcManagerQq" value="${bs.qcManagerQq}"/> 。</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;合同主体：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲1：<input maxlength="100" type="text" name="contractSubj1" value="${bs.contractSubj1}" style="width: 300px"/></h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲2：<input maxlength="100" type="text" name="contractSubj2" value="${bs.contractSubj2}" style="width: 300px"/></h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;付款单位：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲1：<input maxlength="100" type="text" name="paymentUnit1" value="${bs.paymentUnit1}" style="width: 300px"/></h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲2：<input maxlength="100" type="text" name="paymentUnit2" value="${bs.paymentUnit2}" style="width: 300px"/></h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;乙方信息：</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;公司QQ号 ：<input maxlength="100" type="text" name="supplierQq" value="${bs.supplierQq}"/>，对外邮箱：<input maxlength="100" type="text" name="supplierEmail" value="${bs.supplierEmail}" style="width: 300px"/>，</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;营销负责人：<input maxlength="100" type="text" name="marketManager" value="${bs.marketManager}"/>，联系方式：<input maxlength="100" type="text" name="marketManagerPhonenum" value="${bs.marketManagerPhonenum}" style="width: 300px"/>，</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;合同负责人：<input maxlength="100" type="text" name="contractDirector" value="${bs.contractDirector}"/>，联系方式：<input maxlength="100" type="text" name="contractDirectorPhonenum" value="${bs.contractDirectorPhonenum}" style="width: 300px"/>，</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;生产负责人：<input maxlength="100" type="text" name="produceDirector" value="${bs.produceDirector}"/>，联系方式：<input maxlength="100" type="text" name="produceDirectorPhonenum" value="${bs.produceDirectorPhonenum}" style="width: 300px"/>，</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;品控负责人：<input maxlength="100" type="text" name="qcDirector" value="${bs.qcDirector}"/>，联系方式：<input maxlength="100" type="text" name="qcDirectorPhonenum" value="${bs.qcDirectorPhonenum}" style="width: 300px"/>，</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;运输负责人：<input maxlength="100" type="text" name="transDirector" value="${bs.transDirector}"/>，联系方式：<input maxlength="100" type="text" name="transDirectorPhonenum" value="${bs.transDirectorPhonenum}" style="width: 300px"/> 。</h4>
                <h4>7.&nbsp;&nbsp;本次订单的交付地点为：<input maxlength="100" type="text" name="deliveryLocation" value="${bs.deliveryLocation}" style="width: 400px"/>，(具体见发货订单)</h4>
                <h4>8.&nbsp;&nbsp;本次订单合同执行完成后甲乙双方一周内进行核销，乙方应按甲方实际支付的总金额为甲方开具增值税专用发票。如干线运费由乙方代付，则发票总额中应包含乙方代付的干线运费。</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;甲方核销负责人：<input maxlength="100" type="text" name="purVerDirector" value="${bs.purVerDirector}"/>，电话：<input maxlength="100" type="text" name="purVerDirectorNum" value="${bs.purVerDirectorNum}"/>；</h4>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;乙方核销负责人：<input maxlength="100" type="text" name="suppVerDirector" value="${bs.suppVerDirector}"/>，电话：<input maxlength="100" type="text" name="suppVerDirectorNum" value="${bs.suppVerDirectorNum}"/> 。</h4>
            </form>
            <c:if test="${editable}">
                <msk:button buttonValue="保存" buttonType="button" buttonId="SSC11304.BT_SAVE"/>
            </c:if>
        </div>
    </div>

    <div style="margin-top:15px;">
        <c:if test="${not empty contractBasic.contractId and contractBasic.contractStatus ne 2 and contractBasic.contractStatus lt 4}">
            <msk:button buttonValue="甲方(采购商)确认" buttonType="button" buttonId="SSC11304.PURCHASER_CONFIRM"/>
        </c:if>
        <c:if test="${not empty contractBasic.contractId and contractBasic.contractStatus ne 3 and contractBasic.contractStatus lt 4}">
            <msk:button buttonValue="乙方(生产商)确认" buttonType="button" buttonId="SSC11304.SUPPLIER_CONFIRM"/>
        </c:if>
        <c:if test="${contractBasic.contractStatus eq 4}">
            <msk:button buttonValue="再修改" buttonType="button" buttonId="SSC11304.ENABLE_TO_MODIFY"/>
            <c:if test="${navigation ne 'payment'}">
                <msk:button buttonValue="发起付款申请" buttonType="button" buttonId="SSC11304.ORDER_CREATE"/>
            </c:if>
            <msk:button buttonValue="生成发货订单" buttonType="button" buttonId="SSC11304.ORDER_ADD"/>
            <msk:button buttonValue="生成核销单" buttonType="button" buttonId="SSC11304.VERIFICATION_DETAIL"/>
        </c:if>
    </div>
</div>

<script src='<c:url value="/static/js/ssc/SSCCommon.js"/>'></script>
<script src='<c:url value="/static/js/ssc/SSC11304.js"/>'></script>