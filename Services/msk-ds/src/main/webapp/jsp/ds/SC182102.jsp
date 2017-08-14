<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="发货入库通知单明细管理" backTitleArray="发货入库通知单一览" backUrlArray="${ctx}/SC182101/init"></navigation:header>
<div class="page-content list-page">
    <input type="hidden" id="printUrl" value="${printUrl}">
    <input type="hidden" id="callBackUrl" value="${callBackUrl}">
    <input type="hidden" id="deliveryStockId" value="${sc182101Bean.deliveryStockId}">
    <input type="hidden" id="isEdit" value="${isEdit}">
    <input type="hidden" id="sourceFlg" value="${sc182101Bean.sourceFlg}">
    <form:form action="${ctx}/SC182102/search/${isEdit}" id="SC182102Form" metdod="post">
    <c:if test="${isEdit}">
        <input type="hidden" name="lgcsCode" value="${sc182101Bean.lgcsCode}"/>
        <input type="hidden" name="suppCode" value="${sc182101Bean.suppCode}"/>
        <input type="hidden" name="distMonth" value="${sc182101Bean.distMonth}"/>
        <input type="hidden" name="halfCode" value="${sc182101Bean.halfCode}"/>
    </c:if>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>发货入库基本信息</label>
        </h3>
        <table width="100%">
            <tr>
                <td width="100px" align="right">发货入库申请单号</td>
                <td> <input type="text" name="deliveryStockId" value="${sc182101Bean.deliveryStockId}" readonly="true" style="background:#DBDBDB" /></td>
                <td width="10%" align="right">分销月度</td>
                <td align="left">
                    <c:choose>
                        <c:when test="${isEdit}">
                        <select name="distMonth" id="distMonth" disabled>
                            <option value="${sc182101Bean.distMonth}" selected>${sc182101Bean.distMonth}</option>
                        </c:when>
                        <c:otherwise>
                            <select name="distMonth" id="distMonth">
                            <c:forEach items="${distMonthParam.distMonthList}" var="dist">
                                <c:choose>
                                    <c:when test="${halfParam.distMonth eq dist.distMonth}">
                                        <option value="${dist.distMonth}" selected>${dist.distMonth}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${dist.distMonth}">${dist.distMonth}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </select>
                </td>
                <td width="10%" align="right">地区</td>
                <td align="left">
                    <c:choose>
                        <c:when test="${isEdit}">
                        <select name="lgcsCode" id="areaCode" disabled>
                            <option value="${sc182101Bean.lgcsCode}" selected>${sc182101Bean.lgcsName}</option>
                        </c:when>
                        <c:otherwise>
                        <select name="lgcsCode" id="areaCode">
                            <c:forEach items="${halfParam.areaInfoList}" var="areaInfo">
                                <c:choose>
                                    <c:when test="${halfParam.areaCode eq areaInfo.areaCode}">
                                        <option value="${areaInfo.areaCode}" selected>${areaInfo.areaName}</option>
                                    </c:when>
                                    <c:otherwise>

                                        <option value="${areaInfo.areaCode}">${areaInfo.areaName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </select>
                </td>
                <td width="8%" align="right">供应商</td>
                <td align="left">
                    <c:choose>
                    <c:when test="${isEdit}">
                    <select name="suppCode" id="supplierCode" disabled>
                        <option value="${sc182101Bean.suppCode}" selected>${sc182101Bean.suppName}</option>
                    </c:when>
                    <c:otherwise>
                        <input id="supplierName" name="suppName" type="hidden" value=""/>
                    <select name="suppCode" id="supplierCode">
                        <c:forEach items="${halfParam.supplierInfoList}" var="suppInfo">
                            <c:choose>
                                <c:when test="${halfParam.supplierCode eq suppInfo.supplierCode}">
                                    <option value="${suppInfo.supplierCode}" selected>${suppInfo.supplierName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${suppInfo.supplierCode}">${suppInfo.supplierName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="100px" align="right">半旬</td>
                <td>
                    <c:choose>
                    <c:when test="${isEdit}">
                    <select name="halfCode" id="halfCode" disabled>
                        </c:when>
                        <c:otherwise>
                        <select name="halfCode" id="halfCode">
                            </c:otherwise>
                            </c:choose>
                            <c:forEach items="${halfParam.halfNameList}" var="half">
                                <c:choose>
                                    <c:when test="${halfParam.currentHalfCode eq half.halfCode}">
                                        <option value="${half.halfCode}" selected>${half.halfName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${half.halfCode}">${half.halfName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                </td>
                <td width="100px" align="right">状态</td>
                <td> <input type="text" name="" value="${status}" readonly="true" style="background:#DBDBDB" /></td>
            </tr>
        </table>
    </div>
    <div class="group-accordion" collapsible="true" active="true">
        <h3>
            <label>按本期总计划供应量要求，本次发货执行第 ${sc182101Bean.deliveryNums} 次发货，发货入库通知明细如下：</label>
        </h3>
        <table id="SC182102_Grid">
            <thead>
            <tr>
                <th coltype="sno" width="20px">编号</th>
                <th coltype="text" name="productName">产品名称</th>
                <th coltype="text" name="productCode">产品编码</th>
                <th coltype="text" name="productTypeName">产品品种</th>
                <th coltype="text" name="productFeature">产品特征</th>
                <th coltype="text" name="productLevel">产品等级</th>
                <th coltype="text" name="productSpecifical">外包装规格</th>
                <th coltype="text" name="netWeight">净重</th>
                <th coltype="text" name="packageCode">包装编码</th>
                <th coltype="money" accuracy="0" name="sendNum">计划发货箱数</th>
                <c:choose>
                    <c:when test="${isEdit}">
                        <th coltype="money" accuracy="0" name="sendActualNum">实际发货箱数</th>
                    </c:when>
                    <c:otherwise>
                        <th coltype="number" edit="true" name="sendActualNum" maxlength="14" digits="true" digitsMessage="实际发货箱数必须为整数,且长度不能大于14!">实际发货箱数</th>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${isEdit && status !='待收货'}">
                        <th coltype="money" accuracy="0" width="8%" name="receiveNum">实际收货箱数</th>
                    </c:when>
                    <c:when test="${userType == 1 && isEdit && status =='待收货'&& sourceFlg == '1'}">
                        <th coltype="number" width="8%" edit="true" name="receiveNum" maxlength="14" digits="true" digitsMessage="实际收货箱数必须为整数,且长度不能大于14!">实际收货箱数</th>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
                <th coltype="money" accuracy="2" name="sendPlanQty">计划发货重量(KG)</th>
                <th coltype="money" accuracy="2" name="sendQty">实际发货重量(KG)</th>
                <c:choose>
                    <c:when test="${isEdit && status !='待收货'}">
                        <th coltype="money" accuracy="2" name="recriveQty">实际收货重量(KG)</th>
                    </c:when>
                    <c:when test="${userType == 1 && isEdit && status =='待收货'}">
                        <th coltype="money" accuracy="2" name="recriveQty">实际收货重量(KG)</th>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${isEdit}">
                        <th coltype="money" accuracy="0" name="differNum">差异箱数</th>
                        <th coltype="money" accuracy="2" name="differQty">差异重量(KG)</th>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tr role="row">
                <td class="text" style="border: 1px solid #b1b1b1;border-bottom:1px solid #b1b1b1" colspan="8"></td>
                <td class="text" style="border: 1px solid #b1b1b1; background-color: #F5FFFA">合计:</td>
                <td class="text"  name="currentsendPlanNum" id="currentsendPlanNum" style="border: 1px solid #b1b1b1;text-align: right"></td>
                <td class="text" name="currentsendActualNum" id="currentsendActualNum" style="border: 1px solid #b1b1b1;text-align: right"></td>
                <c:choose>
                <c:when test="${isEdit && status !='待收货'}">
                <td class="text" name="currentreceiveNum" id="currentreceiveNum" style="border: 1px solid #b1b1b1;text-align: right"></td>
                </c:when>
                <c:when test="${userType == 1 && isEdit && status =='待收货' && sourceFlg == '1'}">
                <td class="text" edit="true" name="currentreceiveNum" id="currentreceiveNum" style="border: 1px solid #b1b1b1;text-align: right"></td>
                </c:when>
                <c:otherwise>
                </c:otherwise>
                </c:choose>
                    <%--<td class="text" name="currentReceiveNum" id="currentReceiveNum" style="border: 1px solid #b1b1b1;text-align: left"></td>--%>
                <td class="text" name="currentsendPlanQty" id="currentsendPlanQty" style="border: 1px solid #b1b1b1;text-align: right"></td>
                <td class="text" name="currentsendActualQty" id="currentsendActualQty" style="border: 1px solid #b1b1b1;text-align: right"></td>
                <c:choose>
                <c:when test="${isEdit && status !='待收货'}">
                <td class="text" name="currentrecriveQty" id="currentrecriveQty" style="border: 1px solid #b1b1b1;text-align: right"></td>
                </c:when>
                <c:when test="${userType == 1 && isEdit && status =='待收货'}">
                <td class="text" name="currentrecriveQty" id="currentrecriveQty" style="border: 1px solid #b1b1b1;text-align: right"></td>
                </c:when>
                <c:otherwise>
                </c:otherwise>
                </c:choose>
                <c:choose>
                <c:when test="${isEdit}">
                <td class="text" name="currentdifferNum" id="currentdifferNum" style="border: 1px solid #b1b1b1;text-align: right"></td>
                <td class="text" name="currentdifferQty" id="currentdifferQty" style="border: 1px solid #b1b1b1;text-align: right"></td>
                </c:when>
                <c:otherwise>
                </c:otherwise>
                </c:choose>
                    <%--<td class="text" name="currentRecriveQty" id="currentRecriveQty" style="border: 1px solid #b1b1b1;text-align: left"></td>--%>
                    <%--<td class="text" name="currentDifferNum" id="currentDifferNum" style="border: 1px solid #b1b1b1;text-align: left"></td>
                    <td class="text" name="currentDifferQty" id="currentDifferQty" style="border: 1px solid #b1b1b1;text-align: left"></td>--%>
            <tr role="row">
        </table>
    </div>
        <c:if test="${!isEdit}">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>发货信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">发货仓库地点</td>
                        <td> <input type="text" name="deliveryWarehouseAddr" value="${sc182101Bean.deliveryWarehouseAddr}" maxlength="255" maxlengthMessage="'发货仓库地点'长度不能大于255!"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">发货责任人</td>
                        <td><input type="text" name="deliveryResponName" value="${sc182101Bean.deliveryResponName}" maxlength="255" maxlengthMessage="'发货责任人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="deliveryResponTel" value="${sc182101Bean.deliveryResponTel}" maxlength="15"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">发货执行人</td>
                        <td><input type="text" name="deliveryExecuteName" value="${sc182101Bean.deliveryExecuteName}" maxlength="255" maxlengthMessage="'发货执行人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="deliveryExecuteTel" value="${sc182101Bean.deliveryExecuteTel}" maxlength="15"/></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>运输信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">运输单位名称</td>
                        <td> <input type="text" name="transportUnitName" value="${sc182101Bean.transportUnitName}" maxlength="255" maxlengthMessage="'运输单位名称'长度不能大于255!"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运输单位负责人</td>
                        <td><input type="text" name="transportUnitResponName" value="${sc182101Bean.transportUnitResponName}" maxlength="255" maxlengthMessage="'运输单位负责人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="transportUnitResponTel" value="${sc182101Bean.transportUnitResponTel}" maxlength="15"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运输单位执行人</td>
                        <td><input type="text" name="transportUnitExecuteName" value="${sc182101Bean.transportUnitExecuteName}" maxlength="255" maxlengthMessage="'运输单位执行人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="transportUnitExecuteTel" value="${sc182101Bean.transportUnitExecuteTel}" maxlength="15"/></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>仓库信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">发货入库时间要求</td>
                        <td><input type="text" name="deliveryStockTimeReq" id="deliveryStockTimeReq" value="${sc182101Bean.deliveryStockTimeReq}" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运抵仓库地址</td>
                        <td> <input type="text" name="stockAddr" value="${sc182101Bean.stockAddr}" maxlength="255" maxlengthMessage="'运抵仓库地址'长度不能大于255!"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">仓管负责人</td>
                        <td><input type="text" name="stockResponName" value="${sc182101Bean.stockResponName}" maxlength="255" maxlengthMessage="'仓库负责人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="stockResponTel" value="${sc182101Bean.stockResponTel}" maxlength="15"/></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">验收负责人</td>
                        <td><input type="text" name="stockExecuteName" value="${sc182101Bean.stockExecuteName}" maxlength="255" maxlengthMessage="'验收负责人'长度不能大于255!"/></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="stockExecuteTel" value="${sc182101Bean.stockExecuteTel}" maxlength="15"/></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>发货入库通知备注</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">供应商发货备注</td>
                        <td><textarea name="deliveryMemo" cols=40 rows=4 maxlength="255" maxlengthMessage="'供应商发货备注'长度不能大于255!">${sc182101Bean.deliveryMemo}</textarea></td>
                        <c:if test="${userType == 1 || status =='已收货'}">
                            <td width="100px" align="right">仓库收货备注</td>
                            <td><textarea name="stockMemo" cols=40 rows=4 maxlength="255" maxlengthMessage="'仓库收货备注'长度不能大于255!">${sc182101Bean.stockMemo}</textarea></td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:if test="${isEdit}">
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>发货信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">发货仓库地点</td>
                        <td> <input type="text" name="deliveryWarehouseAddr" disabled="true" value="${sc182101Bean.deliveryWarehouseAddr}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">发货责任人</td>
                        <td><input type="text" name="deliveryResponName" disabled="true" value="${sc182101Bean.deliveryResponName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="deliveryResponTel" disabled="true" value="${sc182101Bean.deliveryResponTel}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">发货执行人</td>
                        <td><input type="text" name="deliveryExecuteName" disabled="true" value="${sc182101Bean.deliveryExecuteName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="deliveryExecuteTel" disabled="true" value="${sc182101Bean.deliveryExecuteTel}" /></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>运输信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">运输单位名称</td>
                        <td> <input type="text" name="transportUnitName" disabled="true" value="${sc182101Bean.transportUnitName}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运输单位负责人</td>
                        <td><input type="text" name="transportUnitResponName" disabled="true" value="${sc182101Bean.transportUnitResponName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="transportUnitResponTel" disabled="true" value="${sc182101Bean.transportUnitResponTel}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运输单位执行人</td>
                        <td><input type="text" name="transportUnitExecuteName" disabled="true" value="${sc182101Bean.transportUnitExecuteName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="transportUnitExecuteTel" disabled="true" value="${sc182101Bean.transportUnitExecuteTel}" /></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>仓库信息</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">发货入库时间要求</td>
                        <td><input type="text" name="deliveryStockTimeReq" disabled="true" value="${sc182101Bean.deliveryStockTimeReq}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">运抵仓库地址</td>
                        <td> <input type="text" name="stockAddr" disabled="true" value="${sc182101Bean.stockAddr}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">仓管负责人</td>
                        <td><input type="text" name="stockResponName" disabled="true" value="${sc182101Bean.stockResponName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="stockResponTel" disabled="true" value="${sc182101Bean.stockResponTel}" /></td>
                    </tr>
                    <tr>
                        <td width="100px" align="right">验收负责人</td>
                        <td><input type="text" name="stockExecuteName" disabled="true" value="${sc182101Bean.stockExecuteName}" /></td>
                        <td width="100px" align="right">联系电话</td>
                        <td><input type="text" name="stockExecuteTel" disabled="true" value="${sc182101Bean.stockExecuteTel}" /></td>
                    </tr>
                </table>
            </div>
            <div class="group-accordion" collapsible="true" active="true">
                <h3>
                    <label>发货入库通知备注</label>
                </h3>
                <table width="100%">
                    <tr>
                        <td width="100px" align="right">供应商发货备注</td>
                        <td><textarea name="deliveryMemo" cols=40 rows=4 disabled="true">${sc182101Bean.deliveryMemo}</textarea></td>
                        <c:if test="${userType == 1 && status !='已收货' && sourceFlg == '1'}">
                            <td width="100px" align="right">仓库收货备注</td>
                            <td><textarea name="stockMemo" cols=40 rows=4 maxlength="255" maxlengthMessage="'仓库收货备注'长度不能大于255!">${sc182101Bean.stockMemo}</textarea></td>
                        </c:if>
                        <c:if test="${userType == 1 && status =='已收货'}">
                            <td width="100px" align="right">仓库收货备注</td>
                            <td><textarea name="stockMemo" cols=40 rows=4 disabled="true">${sc182101Bean.stockMemo}</textarea></td>
                        </c:if>
                        <c:if test="${userType == 2 && status =='已收货'}">
                            <td width="100px" align="right">仓库收货备注</td>
                            <td><textarea name="stockMemo" cols=40 rows=4 disabled="true">${sc182101Bean.stockMemo}</textarea></td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </c:if>

    </form:form>

    <c:if test="${userType == 2 && !isEdit}">
        <msk:button buttonId="SC182102.SAVE" buttonType="button" buttonValue="供应商发货签字" />
    </c:if>
    <c:if test="${userType == 1 && status !='已收货' && sourceFlg == '1'}">
        <msk:button buttonId="SC182102.MODIFY" buttonType="button" buttonValue="仓库收货签字" />
    </c:if>
    <msk:button buttonId="SC182102CONFIRM.RETURN" buttonType="button" buttonValue="返回" url="${ctx}/SC182101/init"/>
    <c:if  test="${userType != 2 || isEdit }">
        <msk:button buttonValue="页面打印" buttonId="SC182102.PRINT" buttonType="button" align="left"/>
    </c:if>

</div>
<script type="text/javascript" src="${ctx}/static/ds/js/SC182102.js"></script>
