<%--
    Title:发货订单明细
    author:yang_yang
--%>

<%@ taglib prefix="navigation" uri="/msk" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<msk:codemaster codeType="DeliveryOrderStatus" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="FreightSettleMethod" viewType="json" modelName="SSC"/>
<msk:codemaster codeType="RelationType" viewType="json" modelName="SSC"/>
<style type="text/css">
    a:link {color: blue;}
    a:visited {color: purple;}
</style>
<c:if test="${empty navigation}">
    <navigation:header title="发货订单详细" backTitleArray="发货订单一览" backUrlArray="${pageContext.request.contextPath}/SSC11305/init"></navigation:header>
</c:if>
<c:if test="${navigation eq 'confirm'}">
    <navigation:header title="发货订单详细"
                       backTitleArray="订单发货确认单一览,订单发货确认单详细"
                       backUrlArray="../SSC11314/init/,../SSC11315/init/1"
                       backParamArray='/{"deliveryConfirmCode":"${deliveryConfirmCode}"}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'differ'}">
    <navigation:header title="发货订单详细"
                       backTitleArray="生产商入库差异单一览,生产商入库差异单详细"
                       backUrlArray="../SSC11311/init/,../SSC11312/show/"
                       backParamArray='/{"differId":"${differId}"}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'payment'}">
    <navigation:header title="发货订单详细" backTitleArray="资金池一览" backUrlArray="../SSC11319/init/"></navigation:header>
</c:if>
<c:if test="${navigation eq 'request'}">
    <navigation:header title="发货订单详细" backTitleArray="付款申请一览" backUrlArray="../SSC11307/init/" backParamArray=''></navigation:header>
</c:if>
<c:if test="${navigation eq 'poolDetail'}">
    <navigation:header title="发货订单详细"
                       backTitleArray="资金池一览,资金池详细"
                       backUrlArray="../SSC11319/init,../SSC11320/init/"
                       backParamArray='/{paymentId:${paymentId}}'></navigation:header>
</c:if>
<c:if test="${navigation eq 'verification'}">
    <navigation:header title="发货订单详细" backTitleArray="核销单一览,核销单详细" backUrlArray="../SSC11321/init,../SSC11322/init" backParamArray="/{verificationId:${verificationId}}"></navigation:header>
</c:if>

<c:if test="${navigation eq 'requestDetail'}">
    <navigation:header title="发货订单详细" />
</c:if>


<div class="page-content list-page">
    <form action='' method="post" id="SSC11306Form">
        <input type="hidden" id="deliveryId" name="deliveryId" value="${orderBasic.deliveryId}">
        <input type="hidden" id="freightSettleMethod" value="${orderBasic.freightSettleMethod}">
        <input type="hidden" id="jsonStr" name="jsonStr">
        <input type="hidden" id="navigation"  value="${navigation}" >

        <input type="hidden" name="deliveryCode" id="deliveryCode" value="${orderBasic.deliveryCode}">
        <input type="hidden" id="supplierId" name="supplierId" value="${orderBasic.supplierId}">
        <input type="hidden" name="supplierName" id="supplierName" value="${orderBasic.supplierName}">
        <input type="hidden" id="purchaserId" name="purchaserId" value="${orderBasic.purchaserId}">
        <input type="hidden" name="purchaserName" id="purchaserName" value="${orderBasic.purchaserName}">

        <input type="hidden" name="contractId" id="contractId" value="${orderBasic.contractId}">
        <input type="hidden" id="contractRelationType" value="${orderBasic.contractRelationType}">
        <c:if test="${!isAdd}">
            <input type="hidden" id="purchaserCode" name="purchaserCode" value="${orderBasic.purchaserCode}">
            <input type="hidden" name="supplierCode" value="${orderBasic.supplierCode}">
        </c:if>
        <input type="hidden" id="deliveryStatus" value="${orderBasic.deliveryStatus}">
        <input type="hidden" name="remark" value="${orderBasic.remark}">
        <input type="hidden" id="ver" name="ver" value="${orderBasic.ver}">
        <input type="hidden" name="lgcsAreaName" id="lgcsAreaName" value="${orderBasic.lgcsAreaName}">

        <div class="group-accordion" collapsible="true" active="true">
            <h3>
                <label>发货信息</label>
            </h3>
            <div>
                <table width="100%">
                    <tr>
                        <th width="100px" rowspan="6" valign="top" style="text-align: right;white-space:pre;">发货基本信息:</th>
                        <td width="150px" style="text-align: right;">发货订单编号:</td>
                        <td>
                            ${orderBasic.deliveryCode}
                        </td>
                        <td width="150px" style="text-align: right;">发货订单状态:</td>
                        <td>
                            <c:if test="${not empty orderBasic.deliveryStatus}">
                                <msk:codemaster codeType="DeliveryOrderStatus" viewType="label" modelName="SSC" codeValue="${orderBasic.deliveryStatus}"/>
                            </c:if>
                        </td>
                        <td width="150px" style="text-align: right;">总金额(元):</td>
                        <td>
                            <input type="hidden" id="amount" name="amount" value="${orderBasic.amount}" style="width:200px;border:0" readonly>
                            <label id="amountStr" name="money">${orderBasic.amount}</label>
                        </td>
                    </tr>
                    <tr>
                        <td width="150px" style="text-align: right">合同编号:</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty navigation && orderBasic.deliveryStatus != '9'}">
                                    <a href="javascript:SSC11306.contractCode()" class="contractCode" id="contractCodeHref">${orderBasic.contractCode}</a>
                                </c:when>
                                <c:otherwise>
                                    ${orderBasic.contractCode}
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" id="contractCode" name="contractCode" value="${orderBasic.contractCode}">
                            <c:if test="${empty orderBasic.contractCode && orderBasic.deliveryStatus != '9'}">
                                <msk:button buttonValue="关联合同" buttonId="SSC11306.CONTRACT" buttonType="button"/>
                            </c:if>
                            <c:if test="${not empty orderBasic.contractCode && !isAdd && orderBasic.contractRelationType eq 1 && orderBasic.deliveryStatus != '9'}">
                                <msk:button buttonValue="重新关联合同" buttonId="SSC11306.RCONTRACT" buttonType="button"/>
                            </c:if>
                        </td>
                        <td width="150px" style="text-align: right">合同名称:</td>
                        <td>
                            ${orderBasic.contractName}
                            <input type="hidden" name="contractName" value="${orderBasic.contractName}">
                        </td>
                        <td width="150px" style="text-align: right">关联合同类型(元):</td>
                        <td>
                            <c:if test="${not empty orderBasic.contractRelationType}">
                                <msk:codemaster codeType="RelationType" viewType="label" modelName="SSC" codeValue="${orderBasic.contractRelationType}"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td width="150px" style="text-align: right">采购方:</td>
                        <td>
                            <c:choose>
                                <c:when test="${isAdd}">
                                    <select name="purchaserCode" id="purchaserSelect" style="width:207px;">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${purchaserList}" var="purchaser">
                                            <option value="${purchaser.slCode}" purchaserName="${purchaser.epName}" purchaserId="${purchaser.epId}" slMainClass="${purchaser.slMainClass}">${purchaser.epName}</option>
                                        </c:forEach>
                                    </select>
                                    <%--<input type="text" id="purchaserSelect" name="purchaserCode" style="width:200px;" readonly/>
                                    <img src="../static/images/action/search.png" title="选择企业信息" id="chooseEpInfo" style="cursor:pointer;" />--%>
                                </c:when>
                                <c:otherwise>
                                    ${orderBasic.purchaserName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="150px" style="text-align: right">发货方:</td>
                        <td colspan="3">
                            <c:choose>
                                <c:when test="${isAdd}">
                                    <select name="supplierCode" id="supplierSelect" style="width:207px;">
                                        <option value="">--请选择--</option>
                                    </select>
                                    <%--<input type="text" id="supplierSelect" name="supplierCode" style="width:200px;" readonly/>
                                    <img src="../static/images/action/search.png" title="选择企业信息" id="chooseRpInfo" style="cursor:pointer;" />--%>
                                </c:when>
                                <c:otherwise>
                                    ${orderBasic.supplierName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td width="150px" style="text-align: right">物流区:</td>
                        <td>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${orderBasic.lgcsAreaName}
                                </c:when>
                                <c:otherwise>
                                    <select name="lgcsAreaCode" id="lgcsAreaCode" style="width:207px;">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${lgcsAreaList}" var="lgcsArea">
                                            <c:choose>
                                                <c:when test="${orderBasic.lgcsAreaCode eq lgcsArea.lgcsAreaCode}">
                                                    <option value="${lgcsArea.lgcsAreaCode}" selected>${lgcsArea.lgcsAreaName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${lgcsArea.lgcsAreaCode}">${lgcsArea.lgcsAreaName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="150px" style="text-align: right">到货车次:</td>
                        <td colspan="3">
                            <c:choose>
                                <c:when test="${!empty orderBasic.contractId}">
                                    <lable name="box">${orderBasic.deliveryBatch}</lable>
                                    <input type="hidden" name="deliveryBatch" value="${orderBasic.deliveryBatch}" />
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                            <lable name="box">${orderBasic.deliveryBatch}</lable>
                                        </c:when>
                                        <c:otherwise>
                                            <input style="width:200px;" maxlength="11" type="text" id="deliveryBatchShow"
                                                   value="${orderBasic.deliveryBatch}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onchange="changeVal();"/>
                                            <input type="hidden" id="deliveryBatch" name="deliveryBatch" value="${orderBasic.deliveryBatch}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">发货地址:</td>
                        <td>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${orderBasic.deliveryWarehouseAddr}
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" maxlength="100" type="text" id="deliveryWarehouseAddr" name="deliveryWarehouseAddr"
                                           value="${orderBasic.deliveryWarehouseAddr}" onkeyup="value=value.replace(/\s/g,'')" onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right">到货仓库:</td>
                        <td>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${orderBasic.arriveWarehouse}
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" maxlength="100" type="text" id="arriveWarehouse" name="arriveWarehouse"
                                           value="${orderBasic.arriveWarehouse}" onkeyup="value=value.replace(/\s/g,'')" onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right">到货仓库地址:</td>
                        <td>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${orderBasic.arriveWarehouseAddr}
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" maxlength="100" type="text" id="arriveWarehouseAddr" name="arriveWarehouseAddr"
                                           value="${orderBasic.arriveWarehouseAddr}" onkeyup="value=value.replace(/\s/g,'')" onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">预计发货日期:</td>
                        <td width="150px">
                            <fmt:formatDate var="etd" value="${orderBasic.etd}" pattern="yyyy-MM-dd"/>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${etd}
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" type="text" name="etd" id="etd" value="${etd}" readonly onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right">预计到货日期:</td>
                        <td width="25%">
                            <fmt:formatDate var="eta" value="${orderBasic.eta}" pattern="yyyy-MM-dd"/>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    ${eta}
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" type="text" name="eta" id="eta" value="${eta}" readonly onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right;white-space:pre;" width="150px">预计公里数(公里):</td>
                        <td>
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    <lable name="money">${orderBasic.mileage}</lable>
                                    <input type="hidden" id="mileage" value="${orderBasic.mileage}"/>
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" maxlength="21" type="text" id="mileageShow" value="${orderBasic.mileage}"
                                           <%--onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"--%> onchange="changeVal();"/>
                                    <input type="hidden" id="mileage" name="mileage" value="${orderBasic.mileage}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr><td colspan="7">&nbsp;</td></tr>
                    <tr>
                        <th width="100px" rowspan="2" valign="top" style="text-align: right;">运费结算方式:</th>
                        <td width="150px" style="text-align: right">运费结算方式:</td>
                        <td colspan="5">
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    <msk:codemaster codeType="FreightSettleMethod" viewType="label" modelName="SSC" codeValue="${orderBasic.freightSettleMethod}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" id="freightSettleMethod1" name="freightSettleMethod" value="1" checked="checked"
                                           <c:if test="${orderBasic.freightSettleMethod=='1'}">checked="checked"</c:if>/>到岸价
                                    <input type="radio" id="freightSettleMethod2" name="freightSettleMethod" value="2"
                                           <c:if test="${orderBasic.freightSettleMethod=='2'}">checked="checked"</c:if>/>离岸价价内结算
                                    <input type="radio" id="freightSettleMethod3" name="freightSettleMethod" value="3"
                                           <c:if test="${orderBasic.freightSettleMethod=='3'}">checked="checked"</c:if>/>离岸价价外代付结算
                                    <input type="radio" id="freightSettleMethod4" name="freightSettleMethod" value="4"
                                           <c:if test="${orderBasic.freightSettleMethod=='4'}">checked="checked"</c:if>/>独立第三方支付
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;white-space:pre;" width="200px">运费单价(元/吨·公里):</td>
                        <td width="20%">
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    <lable name="money">${orderBasic.freightUnit}</lable>
                                    <input type="hidden" id="freightUnit" value="${orderBasic.freightUnit}"/>
                                </c:when>
                                <c:otherwise>
                                    <input style="width:200px;" maxlength="21" type="text" id="freightUnitShow"
                                           value="${orderBasic.freightUnit}" <%--onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" --%>onchange="changeVal();"/>
                                    <input type="hidden" id="freightUnit" name="freightUnit" value="${orderBasic.freightUnit}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right" width="150px">每吨运费(元):</td>
                        <td width="25%">
                            <c:choose>
                                <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                                    <lable name="money">${orderBasic.transportUnit}</lable>
                                    <input type="hidden" id="transportUnit" name="transportUnit" value="${orderBasic.transportUnit}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" maxlength="21" id="transportUnitShow"
                                           value="${orderBasic.transportUnit}" style="width:200px;"/>
                                    <input type="hidden" id="transportUnit" name="transportUnit" value="${orderBasic.transportUnit}" onchange="changeVal();"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: right" width="150px">本次运费(元):</td>
                        <td>
                            <input type="hidden" id="transportCost" name="transportCost" value="${orderBasic.transportCost}" style="width:200px;border:0" readonly/>
                            <label id="transportCostStr" name="money">${orderBasic.transportCost}</label>
                        </td>
                    </tr>
                    <tr><td colspan="7">&nbsp;</td></tr>
                    <tr>
                        <th width="100px" rowspan="2" valign="top" style="text-align: right;">审核信息:</th>
                        <td style="text-align: right">采购商审核人:</td>
                        <td>
                            <c:if test="${orderBasic.deliveryStatus != 1 && orderBasic.deliveryStatus != 3}">
                                ${orderBasic.purchaserAuditName}
                            </c:if>
                        </td>
                        <td style="text-align: right;white-space:pre;">采购商审核时间:</td>
                        <td colspan="3">
                            <c:if test="${orderBasic.deliveryStatus != 1 && orderBasic.deliveryStatus != 3}">
                                <fmt:formatDate var="purchaserAuditTime" value="${orderBasic.purchaserAuditTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                                ${purchaserAuditTime}
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">生产商确认人:</td>
                        <td>
                            <c:if test="${orderBasic.deliveryStatus != 1 && orderBasic.deliveryStatus != 2}">
                                ${orderBasic.supplierConfirmName}
                            </c:if>
                        </td>
                        <td style="text-align: right">生产商确认时间:</td>
                        <td colspan="3">
                            <c:if test="${orderBasic.deliveryStatus != 1 && orderBasic.deliveryStatus != 2}">
                                <fmt:formatDate var="supplierConfirmTime" value="${orderBasic.supplierConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                                ${supplierConfirmTime}
                            </c:if>
                        </td>
                    </tr>
                </table>
                <c:if test="${orderBasic.deliveryStatus != '9' && orderBasic.deliveryStatus != '4'}">
                    <msk:button buttonValue="保存" buttonId="SSC11306.SAVE" buttonType="button"/>
                </c:if>
            </div>
        </div>
    </form>
    <form action='<c:url value="/SSC11306/search"/>' id="SSC11306PdForm" method="post">
        <input type="hidden" name="deliveryId" value="${deliveryId}">
        <div class="group-accordion" active="true" id="orderProductData">
            <h3>
                <label>发货订单内容</label>
            </h3>
            <table id="SSC11306_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" width="40px">序号</th>
                    <th coltype="text" width="27%" name="productName">产品</th>
                    <%--<th coltype="text" width="5%" name="gradeName">等级</th>--%>
                    <c:choose>
                        <c:when test="${orderBasic.deliveryStatus == '9' || orderBasic.deliveryStatus == '4'}">
                            <th coltype="money" accuracy="0" width="10%" name="productBox">发货箱数</th>
                            <th coltype="money" accuracy="4" width="10%" name="productQua">发货数量(kg)</th>
                            <th coltype="money" width="10%" name="trunkFreight">干线运费(元/kg)</th>
                            <th coltype="money" width="10%" name="cif">到岸价(元/kg)</th>
                            <th coltype="money" width="10%" name="settkementStandardPrice">结算标准价(元/kg)</th>
                            <%--<th coltype="text" width="10%" name="standardPriceStr" align="center">不含运费结算标准价(元/kg)</th>--%>
                            <th coltype="money" width="10%" name="productValue">货值(元)</th>
                            <th coltype="text" width="10%" name="remark">备注</th>
                        </c:when>
                        <c:otherwise>
                            <th coltype="money" accuracy="0" edit="true" width="10%" name="productBox">发货箱数</th>
                            <th coltype="money" accuracy="4" width="10%" name="productQua">发货数量(kg)</th>
                            <th coltype="money" edit="true" width="10%" name="trunkFreight">干线运费(元/kg)</th>
                            <th coltype="money" edit="true" width="10%" name="cif">到岸价(元/kg)</th>
                            <th coltype="money" edit="true" width="10%" name="settkementStandardPrice">结算标准价(元/kg)</th>
                            <th coltype="money" width="10%" name="productValue">货值(元)</th>
                            <th coltype="text" edit="true" width="15%" name="remark">备注</th>
                            <%--<th coltype="text" edit="true" width="15%" name="ver">VER</th>--%>
                        </c:otherwise>
                    </c:choose>
                    <th coltype="action" width="10%">操作
                        <c:if test="${orderBasic.deliveryStatus != '9' && orderBasic.deliveryStatus != '4'}">
                            <msk:button buttonValue="保存" buttonType="hidden" coltype="save" class="h-button" buttonId="SSC11306.SAVEPD"/>
                            <msk:button buttonValue="删除" buttonType="hidden" coltype="delete" class="h-button" buttonId="SSC11306.DELETE"/>
                        </c:if>
                    </th>
                </tr>
                </thead>
                <tbody></tbody>
                <tbody>
                <tr>
                    <td></td>
                    <td style="text-align: right">合计:</td>
                    <td align="right"><label id="sumProductBox" name="box">${sumProductBox}</label></td>
                    <td align="right">
                        <label id="sumProductQua" style="display: none">${sumProductQua}</label>
                        <label id="sumProductQuaShow" name="weight">${sumProductQua}</label>
                    </td>
                    <td colspan="3"></td>
                    <%--<td><label id="sumStandardPrice">${sumStandardPrice}</label></td>--%>
                    <td align="right">
                        <label id="sumProductValue" style="display: none">${sumProductValue}</label>
                        <label id="sumProductValueShow" name="money">${sumProductValue}</label>
                    </td>
                    <td colspan="2"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
    <c:if test="${!isAdd && orderBasic.deliveryStatus != '9' && orderBasic.deliveryStatus != '4'}">
        <msk:button buttonValue="添加产品" buttonId="SSC11306.ADD" buttonType="button"/>
        <%--<msk:button buttonValue="保存" buttonId="SSC11306.MODIFY" buttonType="button"/>--%>
        <c:if test="${orderBasic.deliveryStatus != '2' && orderBasic.deliveryStatus != '4'}">
            <msk:button buttonValue="甲方(采购商)审核" buttonId="SSC11306.AUDIT" buttonType="button"/>
        </c:if>
        <c:if test="${orderBasic.deliveryStatus != '3' && orderBasic.deliveryStatus != '4'}">
            <msk:button buttonValue="乙方(生产商)确认" buttonId="SSC11306.CONFIRM" buttonType="button"/>
        </c:if>
    </c:if>
    <c:if test="${orderBasic.deliveryStatus == '4'}">
        <msk:button buttonValue="再修改" buttonId="SSC11306.EDIT" buttonType="button"/>
        <c:if test="${navigation ne 'payment'}">
            <msk:button buttonValue="发起付款申请" buttonId="SSC11306.CREATE" buttonType="button"/>
        </c:if>
    </c:if>
</div>
<script src="<c:url value="/static/js/ssc/SSCCommon.js"/>"></script>
<script src="<c:url value="/static/js/ssc/SSC11306.js"/>"></script>
