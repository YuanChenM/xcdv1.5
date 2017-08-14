<%--
  Created by IntelliJ IDEA.
  User: sun_jiaju
  Date: 2016/7/4
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="navigation" uri="/msk" %>
<%@ include file="/common/taglib.jsp" %>
<c:if test="${type==1}">
    <navigation:header title="订单发货确认单详细" backTitleArray="订单发货确认单一览" backUrlArray="${pageContext.request.contextPath}/SSC11314/init"></navigation:header>
</c:if>
<c:if test="${type==2}">
    <navigation:header title="订单发货确认单详细" backTitleArray="预入库通知单一览,预入库通知单详细" backUrlArray="../SSC11316/init/,../SSC11317/init/1"
                       backParamArray='/{"deliveryPreIntoId":"${ssc11315Param.deliveryPreIntoId}","deliveryConfirmCode":"${ssc11315Param.deliveryConfirmCode}"}' />
</c:if>

<div class="page-content list-page">
    <form action='<c:url value="/SSC11315/search"/>' id="SSC11315Form" method="post">
        <input type="hidden" id="deliveryConfirmCode" name="deliveryConfirmCode" value="${deliveryConfirm.deliveryConfirmCode}">
        <input type="hidden" id="deliveryConfirmStatus" name="deliveryConfirmStatus" value="${deliveryConfirm.deliveryConfirmStatus}">
        <input type="hidden" id="contractName" name="contractName" value="${deliveryConfirm.contractName}">
        <input type="hidden" id="ver" name="ver" value="${deliveryConfirm.ver}">
        <input type="hidden" id="deliveryConfirmId" name="deliveryConfirmId" value="${deliveryConfirm.deliveryConfirmId}">
        <input type="hidden" id="deliveryCode" name="deliveryCode" value="${deliveryConfirm.deliveryCode}">
        <input type="hidden" id="deliveryBatch" name="deliveryBatch" value="${deliveryConfirm.deliveryBatch}">
        <input type="hidden" id="deliveryId" name="deliveryId" value="${deliveryConfirm.deliveryId}">
        <input type="hidden" id="whConfirmStatus" value="${deliveryConfirm.whConfirmStatus}" >
        <input type="hidden" id="byConfirmStatus" value="${deliveryConfirm.byConfirmStatus}" >
        <input type="hidden" id="pdConfirmStatus" value="${deliveryConfirm.pdConfirmStatus}" >

        <div class="group-accordion" active="true">
            <h3>
                <label>基本信息</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right" width="15%" style="white-space:pre;">发货确认单编号：</td>
                    <td width="20%">${deliveryConfirm.deliveryConfirmCode}</td>
                    <td width="10%" align="right">到货车次：</td>
                    <td colspan="3">${deliveryConfirm.deliveryBatch}</td>
                </tr>
                <tr>
                    <td align="right">合同编号：</td>
                    <td> ${deliveryConfirm.contractCode}</td>
                    <td align="right" style="white-space:pre;">发货订单编号：</td>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${type != 2 && deliveryConfirm.deliveryConfirmStatus != 9}">
                                <a style="color:blue;" title="查看发货订单详情" href="javascript:void(0)" onclick="goToDeliveryOrderDetail('${deliveryConfirm.deliveryId}');">${deliveryConfirm.deliveryCode}</a>
                            </c:when>
                            <c:otherwise>
                                ${deliveryConfirm.deliveryCode}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td align="right">采购商：</td>
                    <td style="white-space:pre;">${deliveryConfirm.purchaserName}</td>
                    <td align="right">生产商：</td>
                    <td style="white-space:pre;" colspan="3">${deliveryConfirm.supplierName}</td>
                </tr>
                <tr>
                    <td align="right">
                        物流区：
                    </td>
                    <td>
                        ${deliveryConfirm.lgcsAreaName}
                    </td>
                    <td align="right">到货仓库：</td>
                    <td width="20%">
                        <c:choose>
                            <c:when test="${deliveryConfirm.deliveryConfirmStatus != 4 && deliveryConfirm.deliveryConfirmStatus != 9}">
                                <input name="arriveWarehouse" onchange="SSC11315.onchange()" maxlength="100" type="text" value="${deliveryConfirm.arriveWarehouse}" style="width: 250px"/>
                            </c:when>
                            <c:otherwise>
                                ${deliveryConfirm.arriveWarehouse}
                                <input name="arriveWarehouse" type="hidden" value="${deliveryConfirm.arriveWarehouse}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td width="10%" align="right">到货仓库地址：</td>
                    <td>
                        <c:choose>
                            <c:when test="${deliveryConfirm.deliveryConfirmStatus != 4 && deliveryConfirm.deliveryConfirmStatus != 9}">
                                <input name="arriveWarehouseAddr" type="text" onchange="SSC11315.onchange()" maxlength="100" value="${deliveryConfirm.arriveWarehouseAddr}" style="width: 250px"/>
                            </c:when>
                            <c:otherwise>
                                ${deliveryConfirm.arriveWarehouseAddr}
                                <input name="arriveWarehouseAddr" type="hidden" value="${deliveryConfirm.arriveWarehouseAddr}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        三方确认状态：
                    </td>
                    <td colspan="5">
                        <msk:codemaster id="deliveryConfirmStatus" codeType="DeliveryConfirmStatus" viewType="label" modelName="SSC"  codeValue="${deliveryConfirm.deliveryConfirmStatus}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        采购方确认状态：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.byConfirmStatus eq 1}">采购方已确认</c:if>
                        <c:if test="${deliveryConfirm.byConfirmStatus eq 0}">采购方未确认</c:if>
                        <c:if test="${deliveryConfirm.byConfirmStatus eq 2}">采购方不同意</c:if>
                    </td>
                    <td align="right">
                        采购方确认人：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.byConfirmStatus ne 0}">
                        ${deliveryConfirm.byConfirmName}
                        </c:if>
                    </td>
                    <td style="white-space:nowrap;text-align:right;">
                        采购方确认时间：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.byConfirmStatus ne 0}">
                        <fmt:formatDate value="${deliveryConfirm.byConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        仓库方确认状态：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.whConfirmStatus eq 1}">仓库方已确认</c:if>
                        <c:if test="${deliveryConfirm.whConfirmStatus eq 0}">仓库方未确认</c:if>
                        <c:if test="${deliveryConfirm.whConfirmStatus eq 2}">仓库方不同意</c:if>
                    </td>
                    <td align="right">
                        仓库方确认人：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.whConfirmStatus ne 0}">
                        ${deliveryConfirm.whConfirmName}
                        </c:if>
                    </td>
                    <td align="right">
                        仓库方确认时间：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.whConfirmStatus ne 0}">
                        <fmt:formatDate value="${deliveryConfirm.whConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        生产商确认状态：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.pdConfirmStatus eq 1}">生产商已确认</c:if>
                        <c:if test="${deliveryConfirm.pdConfirmStatus eq 0}">生产商未确认</c:if>
                        <c:if test="${deliveryConfirm.pdConfirmStatus eq 2}">生产商不同意</c:if>
                    </td>
                    <td align="right">
                        生产商确认人：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.pdConfirmStatus ne 0}">
                        ${deliveryConfirm.pdConfirmName}
                        </c:if>
                    </td>
                    <td align="right">
                        生产商确认时间：
                    </td>
                    <td>
                        <c:if test="${deliveryConfirm.pdConfirmStatus ne 0}">
                        <fmt:formatDate value="${deliveryConfirm.pdConfirmTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>

        <div class="group-accordion" active="true">
            <h3>
                <label>发货时间、到货时间确认</label>
            </h3>
            <table width="100%">
                <tr>
                    <td align="right" width="15%">发货时间：</td>
                    <td colspan="3">
                        <input type="text" id="etd"<c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> name="etd" onchange="SSC11315.onchange()" value="<fmt:formatDate value='${deliveryConfirm.etd}' pattern='yyyy-MM-dd' />"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="15%">到货时间：</td>
                    <td colspan="3">
                        <input type="text" id="eta" name="eta" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> onchange="SSC11315.onchange()" value="<fmt:formatDate value='${deliveryConfirm.eta}' pattern='yyyy-MM-dd' />"/>
                    </td>
                </tr>
            </table>
        </div>

        <div class="group-accordion" active="true">
            <h3>
                <label>发货品种、数量确认</label>
            </h3>
            <div>
                <table id="SSC11315_list_grid" width="100%">
                    <thead>
                    <tr>
                        <th coltype="sno" width="5%">序号</th>
                        <th coltype="text" width="40%" name="pdName">产品信息</th>
                        <c:if test="${deliveryConfirm.deliveryConfirmStatus ne 1}">
                        <th coltype="money" accuracy="0" width="10%" name="productConfirmBox" edit="false">发货箱数</th>
                        </c:if>
                        <c:if test="${deliveryConfirm.deliveryConfirmStatus eq 1}">
                        <th coltype="money" accuracy="0" width="10%" name="productConfirmBox" edit="true">发货箱数</th>
                        </c:if>
                        <th coltype="money" width="10%" accuracy="4" name="productQua"  >发货重量(kg)</th>
                        <c:if test="${deliveryConfirm.deliveryConfirmStatus ne 1}">
                        <th coltype="money" width="10%" name="settkementStandardPrice"  edit="false">结算标准价(元/kg)</th>
                        </c:if>
                        <c:if test="${deliveryConfirm.deliveryConfirmStatus eq 1}">
                        <th coltype="money" width="10%" name="settkementStandardPrice"  edit="true">结算标准价(元/kg)</th>
                        </c:if>
                        <th coltype="money" width="10%" name="productValue">货值(元)</th>
                        <th coltype="action" width="10%">操作
                            <msk:button buttonType="hidden" buttonId="SSC11315.SAVEBTN" coltype="save" buttonValue="保存" class="h-button" useable="can_abolish"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tbody>
                    <tr>
                        <td align="center" colspan="2" width="45%">合计</td>
                        <td align="right" width="8%">
                            <label id="allProductConfirmBox"><fmt:formatNumber value="${allInfo.productConfirmBox}" pattern="#,###"/></label>
                            <%--<input type="text" id="allProductConfirmBox" value="${allInfo.productConfirmBox}" readonly="readonly" style="border-style: solid; border-width: 0;">--%>
                        </td>
                        <td align="right" width="20%">
                            <label id="allProductQua" ><fmt:formatNumber value="${allInfo.productQua}" pattern="#,##0.0000"/></label>
                            <%--<input type="text" id="allProductQua" value="${allInfo.productQua}" readonly="readonly" style="width : 200px; border-style: solid; border-width: 0;">--%>
                        </td>
                        <td></td>
                        <td align="right" width="17%">
                            <label id="allProductValue" name="money">${allInfo.productValue}</label>
                            <%--<input type="text" id="allProductValue" value="${allInfo.productValue}" readonly="readonly" style="width : 200px; border-style: solid; border-width: 0;">--%>
                        </td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="group-accordion" active="true">
            <h3>
                <label>确认结果</label>
            </h3>
            <div>
                <table width="100%">
                    <tr>
                        <td align="center" width="8%" style="white-space:pre;">采购方确认：</td>
                        <td width="14%">
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.byConfirmStatus=='1'}">checked="checked"</c:if> name="byConfirmStatus" value="1"/>同意
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.byConfirmStatus=='2'||deliveryConfirm.byConfirmStatus=='0'}">checked="checked"</c:if> name="byConfirmStatus" value="2"/>不同意
                        </td>
                        <td align="center" width="5%" style="white-space:pre;">原因</td>
                        <td width="45%">
                            <input type="text" maxlength="100" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> id="byConfirmReason" style="width : 500px" name="byConfirmReason" value="${deliveryConfirm.byConfirmReason}">
                        </td>
                        <td>
                            <c:if test="${deliveryConfirm.deliveryConfirmStatus == 1||deliveryConfirm.deliveryConfirmStatus == 0}">
                            <msk:button buttonType="button" buttonId="SSC11315.PURCHASERCONFIRM" coltype="button" buttonValue="采购方确认"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">仓库方确认：</td>
                        <td>
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.whConfirmStatus=='1'}">checked="checked"</c:if> name="whConfirmStatus" value="1"/>同意
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.whConfirmStatus=='2'||deliveryConfirm.whConfirmStatus=='0'}">checked="checked"</c:if> name="whConfirmStatus" value="2"/>不同意
                        </td>
                        <td align="center" width="5%">原因</td>
                        <td>
                            <input type="text" maxlength="100" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> id="whConfirmReason" style="width :500px" name="whConfirmReason" value="${deliveryConfirm.whConfirmReason}">
                        </td>
                        <td>
                            <c:if test="${deliveryConfirm.deliveryConfirmStatus == 1||deliveryConfirm.deliveryConfirmStatus == 0}">
                            <msk:button buttonType="button" buttonId="SSC11315.WHCONFIRM" coltype="button" buttonValue="仓库方确认"/>
                                </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">生产商确认：</td>
                        <td>
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.pdConfirmStatus=='1'}">checked="checked"</c:if> name="pdConfirmStatus" value="1"/>同意
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> <c:if test="${deliveryConfirm.pdConfirmStatus=='2'||deliveryConfirm.pdConfirmStatus=='0'}">checked="checked"</c:if> name="pdConfirmStatus" value="2"/>不同意
                        </td>
                        <td align="center" width="5%">原因</td>
                        <td width="30%">
                            <input type="text" maxlength="100" <c:if test="${deliveryConfirm.deliveryConfirmStatus != 1&&deliveryConfirm.deliveryConfirmStatus != 0}"> disabled="true" </c:if> id="pdConfirmReason" style="width : 500px" name="pdConfirmReason" value="${deliveryConfirm.pdConfirmReason}">
                        </td>
                        <td>
                            <c:if test="${deliveryConfirm.deliveryConfirmStatus == 1||deliveryConfirm.deliveryConfirmStatus == 0}">
                            <msk:button buttonType="button" buttonId="SSC11315.PDCONFIRM" coltype="button" buttonValue="生产商确认"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div>
            <table>
                <tr>
                    <c:if test="${deliveryConfirm.deliveryConfirmStatus ne 4 && deliveryConfirm.deliveryConfirmStatus ne 9}">
                        <td><msk:button buttonType="button" buttonValue="保存" buttonId="SSC11315.CONFIRM"/></td>
                    </c:if>
                    <c:if test="${deliveryConfirm.deliveryConfirmStatus eq 4 && deliveryConfirm.deliveryConfirmStatus ne 9}">
                        <td><msk:button buttonValue="生成预入库通知单" buttonId="SSC11315.SAVEPRE" buttonType="button"/></td>
                        <td><msk:button buttonValue="再修改" buttonId="SSC11315.EDIT" buttonType="button"/></td>
                    </c:if>
                    <c:if test="${deliveryConfirm.deliveryConfirmStatus ne 9}">
                        <td><msk:button buttonType="button" buttonId="SSC11315.HISTORY" buttonValue="查看三方确认履历"/></td>
                    </c:if>
                </tr>
            </table>
        </div>
    </form>
</div>

<script src="<c:url value='/static/js/ssc/SSCCommon.js'/>"/>
<script src="<c:url value='/static/js/ssc/SSC11315.js'/>"/>
